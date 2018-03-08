package com.ruseps.world.content;

import java.util.concurrent.CopyOnWriteArrayList;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Animation;
import com.ruseps.model.GameMode;
import com.ruseps.model.Graphic;
import com.ruseps.model.Item;
import com.ruseps.model.Locations;
import com.ruseps.model.PlayerRights;
import com.ruseps.model.Locations.Location;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.entity.impl.player.Player;

public class Gamble {

	private Player player;
	public Gamble(Player p) {
		this.player = p;
	}

	public void requestGamble(Player player2) {
		if(player == null || player2 == null || player.getConstitution() <= 0 || player2.getConstitution() <= 0 || player.isTeleporting() || player2.isTeleporting())
			return;
		
		if (!player.getInventory().contains(15084) && !player2.getInventory().contains(15084)) {
			player.getPacketSender().sendMessage("Neither you nor the other player has a dice bag.");
			return;
		} else if (player.getInventory().contains(15084) && player2.getInventory().contains(15084)) {
			player.getPacketSender().sendMessage("Both of you are holding a dice bag therefore you can't gamble with each other.");
			return;
		} else {
			if (player.getInventory().contains(15084)) {
				host = player;
				gambler = player2;
			} else {
				host = player2;
				gambler = player;
			}
		}
			
		
		if(player.getGameMode() == GameMode.IRONMAN) {
			player.getPacketSender().sendMessage("Ironman-players are not allowed to gamble.");
			return;
		}
		if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			player.getPacketSender().sendMessage("Hardcore-ironman-players are not allowed to gamble.");
			return;
		}
		if (player.getRights() == PlayerRights.MANAGER || player.getRights() == PlayerRights.ADMINISTRATOR) {
			player.getPacketSender().sendMessage("You are not allowed to trade.");
			return;
		}
		if (player2.getRights() == PlayerRights.MANAGER || player2.getRights() == PlayerRights.ADMINISTRATOR) {
			player.getPacketSender().sendMessage("You are not allowed to trade.");
			return;
		}
		if(player2.getGameMode() == GameMode.IRONMAN) {
			player.getPacketSender().sendMessage("That player is a Hardcore-ironman-player and can therefore not gamble.");
			return;
		}
		if(player2.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			player.getPacketSender().sendMessage("That player is an Ironman player and can therefore not gamble.");
			return;
		}
		if(player.getLocation() == Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("You are far too busy to gamble at the moment!");
			return;
		}
		if(player2.getLocation() == Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("You are far too busy to gamble at the moment!");
			return;
		}
		
		/*if(Misc.getMinutesPlayed(player) < 15) {
			player.getPacketSender().sendMessage("You must have played for at least 15 minutes in order to Gamble someone.");
			return;
		}*/
		if(player.getBankPinAttributes().hasBankPin() && !player.getBankPinAttributes().hasEnteredBankPin()) {
			BankPin.init(player, true);
			return;
		}
		if(player.getHostAddress().equals(player2.getHostAddress()) && player.getRights() != PlayerRights.OWNER && player.getRights() != PlayerRights.DEVELOPER) {
			player.getPacketSender().sendMessage("Same IP-adress found. You cannot Gamble yourself from the same IP.");
			return;
		}
		if(System.currentTimeMillis() - lastGambleSent < 5000 && !inGamble()) {
			player.getPacketSender().sendMessage("You're sending gamble requests too frequently. Please slow down.");
			return;
		}
		if(player.getLocation() == Location.DUEL_ARENA && player.getDueling().duelingStatus == 5) {
			player.getPacketSender().sendMessage("You are far too busy to gamble at the moment!");
			return;
		}
		if(inGamble()) {
			declineGamble(true);
			return;
		}
		if(player.getLocation() == Location.GODWARS_DUNGEON && player.getMinigameAttributes().getGodwarsDungeonAttributes().hasEnteredRoom() && !player2.getMinigameAttributes().getGodwarsDungeonAttributes().hasEnteredRoom()) {
			player.getPacketSender().sendMessage("You cannot reach that.");
			return;
		}
		if(player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		if(player.busy()) {
			return;
		}
		if(player2.busy() || player2.getInterfaceId() > 0 || player2.getGamble().inGamble() || player2.isBanking() || player2.isShopping()/* || player2.getDueling().inDuelScreen || FightPit.inFightPits(player2)*/) {
			player.getPacketSender().sendMessage("The other player is currently busy.");
			return;
		}
		if(player.getInterfaceId() > 0 || inGamble() || player.isBanking() || player.isShopping()/* || player.getDueling().inDuelScreen || FightPit.inFightPits(player)*/) {
			player.getPacketSender().sendMessage("You are currently unable to gamble another player.");
			if(player.getInterfaceId() > 0)
				player.getPacketSender().sendMessage("Please close all open interfaces before requesting to open another one.");
			return;
		}
		gambleWith = player2.getIndex();
		if(getGambleWith() == player.getIndex())
			return;
		if(!Locations.goodDistance(player.getPosition().getX(), player.getPosition().getY(), player2.getPosition().getX(), player2.getPosition().getY(), 2)) {
			player.getPacketSender().sendMessage("Please get closer to request a gamble.");
			return;
		}
		if(!inGamble() && player2.getGamble().gambleRequested() && player2.getGamble().getGambleWith() == player.getIndex()) {
			openGamble();
			player2.getGamble().openGamble();
		} else if(!inGamble()) {
			setGambleRequested(true);
			player.getPacketSender().sendMessage("You've sent a gamble request to "+player2.getUsername()+".");
			player2.getPacketSender().sendMessage(player.getUsername() + ":gamblereq:");
		}
		lastGambleSent = System.currentTimeMillis();
	}

	public void openGamble() {
		player.getPacketSender().sendClientRightClickRemoval();
		Player player2 = World.getPlayers().get(getGambleWith());
		if(player == null || player2 == null || getGambleWith() == player.getIndex() || player.isBanking())
			return;
		setGamble(true);
		setGambleRequested(false);
		setCanOffer(true);
		setGambleStatus(1);
		player.getPacketSender().sendInterfaceItems(3415, offeredItems);
		player2.getPacketSender().sendInterfaceItems(3415, player2.getGamble().offeredItems);
		sendText(player2);
		player.getInventory().refreshItems();
		player.getPacketSender().sendInterfaceItems(3415, offeredItems);
		player.getPacketSender().sendInterfaceItems(3416, player2.getGamble().offeredItems);
		player.getMovementQueue().reset();
		inGambleWith = player2.getIndex();
	}

	public void declineGamble(boolean tellOther) {
		Player player2 = getGambleWith() >= 0 && !(getGambleWith() > World.getPlayers().capacity()) ? World.getPlayers().get(getGambleWith()) : null;
		for (Item item : offeredItems) {
			if (item.getAmount() < 1)
				continue;
			player.getInventory().add(item);
		}
		offeredItems.clear();
		if(tellOther && getGambleWith() > -1) {
			if(player2 == null)
				return;
			player2.getGamble().declineGamble(false);
			player2.getPacketSender().sendMessage("Other player declined the gamble.");
		}
		resetGamble();
	}

	public void sendText(Player player2) {
		if(player2 == null)
			return;
		player2.getPacketSender().sendString(3451, "" + Misc.formatPlayerName(player.getUsername()) + "");
		player2.getPacketSender().sendString(3417, "Gambling with: " + Misc.formatPlayerName(player.getUsername()) + " @whi@|@red@ Game : 55x2");
		player.getPacketSender().sendString(3451, "" + Misc.formatPlayerName(player2.getUsername()) + "");
		player.getPacketSender().sendString(3417, "Gambling with: " + Misc.formatPlayerName(player2.getUsername()) + " @whi@|@red@ Game : 55x2");
		player.getPacketSender().sendString(3431, "");
		player.getPacketSender().sendString(3533, "Your items");
		player.getPacketSender().sendString(3534, "Opponent's items");		
		player.getPacketSender().sendString(3535, "Are you sure you want to make this gamble?");
		player.getPacketSender().sendString(3536, "There is NO WAY to reverse a gamble if you change your mind");
		player.getPacketSender().sendInterfaceSet(3323, 3321);
		player.getPacketSender().sendItemContainer(player.getInventory(), 3322);
	}

	public void gambleItem(int itemId, int amount, int slot) {
		if(slot < 0)
			return;
		if(!getCanOffer())
			return;
		Player player2 = World.getPlayers().get(getGambleWith());
		if(player2 == null || player == null)
			return;
	/*	if(player.getNewPlayerDelay() > 0 && player.getRights().ordinal() == 0) {
			player.getPacketSender().sendMessage("You must wait another "+player.getNewPlayerDelay() / 60+" minutes before being able to Gamble items.");
			return;
		}*/
		if(player.getRights() != PlayerRights.DEVELOPER && player2.getRights() != PlayerRights.DEVELOPER && !(itemId == 1419 && player.getRights().isStaff())) {
			if (!new Item(itemId).tradeable()) {
				player.getPacketSender().sendMessage("This item is currently untradeable and cannot be Gambled.");
				return;
			}
		}
		falseGambleConfirm();
		player.getPacketSender().sendClientRightClickRemoval();
		if(!inGamble() || !canOffer) {
			declineGamble(true);
			return;
		}
		if(!player.getInventory().contains(itemId))
			return;
		if(slot >= player.getInventory().capacity() || player.getInventory().getItems()[slot].getId() != itemId || player.getInventory().getItems()[slot].getAmount() <= 0)
			return;
		Item itemToGamble = player.getInventory().getItems()[slot];
		if(itemToGamble.getId() != itemId)
			return;
		if (player.getInventory().getAmount(itemId) < amount) {
			amount = player.getInventory().getAmount(itemId);
			if (amount == 0 || player.getInventory().getAmount(itemId) < amount) {
				return;
			}
		}
		if (!itemToGamble.getDefinition().isStackable()) {
			for (int a = 0; a < amount && a < 28; a++) {
				if (player.getInventory().getAmount(itemId) >= 1) {
					offeredItems.add(new Item(itemId, 1));
					player.getInventory().delete(itemId, 1);
				}
			}
		} else
			if (itemToGamble.getDefinition().isStackable()) {
				boolean itemInGamble = false;
				for (Item item : offeredItems) {
					if (item.getId() == itemId) {
						itemInGamble = true;
						item.setAmount(item.getAmount() + amount);
						player.getInventory().delete(itemId, amount);
						break;
					}
				}
				if (!itemInGamble) {
					offeredItems.add(new Item(itemId, amount));
					player.getInventory().delete(itemId, amount);
				}
			}
		player.getInventory().refreshItems();
		player.getPacketSender().sendInterfaceItems(3416, player2.getGamble().offeredItems);
		player.getPacketSender().sendInterfaceItems(3415, offeredItems);
		player.getPacketSender().sendString(3431, "");
		acceptedGamble = false;
		gambleConfirmed = false;
		gambleConfirmed2 = false;
		player2.getPacketSender().sendInterfaceItems(3416, offeredItems);
		player2.getPacketSender().sendString(3431, "");
		player2.getGamble().acceptedGamble = false;
		player2.getGamble().gambleConfirmed = false;
		player2.getGamble().gambleConfirmed2 = false;
		sendText(player2);
	}

	public void removeGambledItem(int itemId, int amount) {
		if(!getCanOffer())
			return;
		Player player2 = World.getPlayers().get(getGambleWith());
		if(player2 == null)
			return;
		if(!inGamble() || !canOffer) {
			declineGamble(false);
			return;
		}
		falseGambleConfirm();
		ItemDefinition def = ItemDefinition.forId(itemId);
		if (!def.isStackable()) {
			if (amount > 28)
				amount = 28;
			for (int a = 0; a < amount; a++) {
				for (Item item : offeredItems) {
					if (item.getId() == itemId) {
						if (!item.getDefinition().isStackable()) {
							offeredItems.remove(item);
							player.getInventory().add(itemId, 1);
						}
						break;
					}
				}
			}
		} else
			for (Item item : offeredItems) {
				if (item.getId() == itemId) {
					if (item.getDefinition().isStackable()) {
						if (item.getAmount() > amount) {
							item.setAmount(item.getAmount() - amount);
							player.getInventory().add(itemId, amount);
						} else {
							amount = item.getAmount();
							offeredItems.remove(item);
							player.getInventory().add(itemId, amount);
						}
					}
					break;
				}
			}
		falseGambleConfirm();
		player.getInventory().refreshItems();
		player.getPacketSender().sendInterfaceItems(3416, player2.getGamble().offeredItems);
		player.getPacketSender().sendInterfaceItems(3415, offeredItems);
		player2.getPacketSender().sendInterfaceItems(3416, offeredItems);
		sendText(player2);
		player.getPacketSender().sendString(3431, "");
		player2.getPacketSender().sendString(3431, "");
		player.getPacketSender().sendClientRightClickRemoval();
	}

	public void acceptGamble(int stage) {
		if(!player.getClickDelay().elapsed(1000))
			return;
//		if (gambleType == -1) { //there is only one game mode rigth now
//			player.getPacketSender().sendString(3431, "Select a game to proceed");			
//			return;
//		}
		if(getGambleWith() < 0) {
			declineGamble(false);
			return;
		}
		Player player2 = World.getPlayers().get(getGambleWith());
		if(player == null || player2 == null) {
			declineGamble(false);
			return;
		}
		if(!twoGamblers(player, player2)) {
			player.getPacketSender().sendMessage("An error has occured. Please try re-gambling the player.");
			return;
		}
		if(stage == 2) {
			if(!inGamble() || !player2.getGamble().inGamble() || !player2.getGamble().gambleConfirmed) {
				declineGamble(true);
				return;
			}
			if(!gambleConfirmed)
				return;
			acceptedGamble = true;
			gambleConfirmed2 = true;
			player2.getPacketSender().sendString(3535, "Other player has accepted.");
			player.getPacketSender().sendString(3535, "Waiting for other player...");
			if (inGamble() && player2.getGamble().gambleConfirmed2) {
				acceptedGamble = true;
				player.getPacketSender().sendMessage("Gamble accepted.");
				player2.getGamble().acceptedGamble = true;
				player2.getPacketSender().sendMessage("Gamble accepted.");		
				host.forceChat("Good luck!");
				startGame();
			}
		} else if(stage == 1) {
			player2.getGamble().goodGamble = true;
			player2.getPacketSender().sendString(3431, "Other player has accepted.");
			goodGamble = true;
			player.getPacketSender().sendString(3431, "Waiting for other player...");
			gambleConfirmed = true;
			if (inGamble() && player2.getGamble().gambleConfirmed && player2.getGamble().goodGamble && goodGamble) {
				confirmScreen();
				player2.getGamble().confirmScreen();
			}
		}
		player.getClickDelay().reset();
	}

	public void confirmScreen() {
		Player player2 = World.getPlayers().get(getGambleWith());
		if (player2 == null)
			return;
		setCanOffer(false);
		player.getInventory().refreshItems();
		String SendGamble = "Absolutely nothing!";
		String SendAmount;
		int Count = 0;
		for (Item item : offeredItems) {
			if (item.getAmount() >= 1000 && item.getAmount() < 1000000) {
				SendAmount = "@cya@" + (item.getAmount() / 1000) + "K @whi@(" + Misc.format(item.getAmount()) + ")";
			} else if (item.getAmount() >= 1000000) {
				SendAmount = "@gre@" + (item.getAmount() / 1000000) + " million @whi@(" + Misc.format(item.getAmount()) + ")";
			} else {
				SendAmount = "" + Misc.format(item.getAmount());
			}
			if (Count == 0) {
				SendGamble = item.getDefinition().getName().replaceAll("_", " ");
			} else
				SendGamble = SendGamble + "\\n" + item.getDefinition().getName().replaceAll("_", " ");
			if (item.getDefinition().isStackable())
				SendGamble = SendGamble + " x " + SendAmount;
			Count++;
		}

		player.getPacketSender().sendString(3557, SendGamble);
		SendGamble = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (Item item : player2.getGamble().offeredItems) {
			if (item.getAmount() >= 1000 && item.getAmount() < 1000000) {
				SendAmount = "@cya@" + (item.getAmount() / 1000) + "K @whi@(" + Misc.format(item.getAmount()) + ")";
			} else if (item.getAmount() >= 1000000) {
				SendAmount = "@gre@" + (item.getAmount() / 1000000) + " million @whi@(" + Misc.format(item.getAmount()) + ")";
			} else {
				SendAmount = "" + Misc.format(item.getAmount());
			}
			if (Count == 0) {
				SendGamble = item.getDefinition().getName().replaceAll("_", " ");
			} else
				SendGamble = SendGamble + "\\n" + item.getDefinition().getName().replaceAll("_", " ");
			if (item.getDefinition().isStackable())
				SendGamble = SendGamble + " x " + SendAmount;
			Count++;
		}
		player.getPacketSender().sendString(3558, SendGamble);
		player.getPacketSender().sendInterfaceSet(3443, 3321);
		player.getPacketSender().sendItemContainer(player.getInventory(), 3322);
		/*
		 * Remove all tabs!
		 */
		//player.getPacketSender().sendInterfaceSet(3443, Inventory.INTERFACE_ID);
		//player.getPacketSender().sendItemContainer(player.getInventory(), Inventory.INTERFACE_ID);
	}

	public void startGame() {
		Player player2 = World.getPlayers().get(getGambleWith());
		if (player2 == null)
			return;
		if(!inGamble() || !player2.getGamble().inGamble())
			return;
		try {

			switch(gambleType) {
				default://55x2
					
					//Roll a number
					host.getGamble().numberRolled = Math.round((int) (Math.random() * 100));
					host.performAnimation(new Animation(11900));
					host.performGraphic(new Graphic(2075));
					
					//Show the result
					host.setCurrentTask(new Task(5, false) {
						@Override
					      public void stop() {
					       setEventRunning(false);
					       
					       if (!offeredItems.isEmpty() || !player2.getGamble().offeredItems.isEmpty()) {
					        player.forceChat("Never mind, I changed my mind.");
					        declineGamble(false);
					        player2.getGamble().declineGamble(false); 
					       }
					      }
						@Override
						public void execute() {
							host.forceChat("I have rolled a " +  host.getGamble().numberRolled);
							host.getPacketSender().sendMessage("You have rolled a " + host.getGamble().numberRolled);
							gambler.getPacketSender().sendMessage("Host have rolled a " + host.getGamble().numberRolled);	
							
							if (host.getGamble().numberRolled > 55) {
								winner = gambler;
								loser = host;
								gambler.forceChat("I won!");
							} else {
								winner = host;
								loser = gambler;
								gambler.forceChat("I lost!");
							}
							
							//Reward the winner
							giveItems();
															
							stop();
						}
					});
					TaskManager.submit(host.getCurrentTask());
					
					break;
			}
			
			//Reset
			resetGamble();
			player2.getGamble().resetGamble();
			
			//logs
			for (Item item : player.getGamble().offeredItems) {
				PlayerLogs.log(player.getUsername(), "Gambled this items with "+player2.getUsername()+". Id: "+item.getId()+", amount: "+item.getAmount());
			}
			for (Item item : player2.getGamble().offeredItems) {
				PlayerLogs.log(player2.getUsername(), "Gambled this items with "+player.getUsername()+". Id: "+item.getId()+", amount: "+item.getAmount());
			}
		} catch (Exception ignored) {
		}
	}

	public void resetGamble() {
		inGambleWith = -1;
		gambleType = -1;
		//offeredItems.clear();
		setCanOffer(true);
		setGamble(false);
		setGambleWith(-1);
		setGambleStatus(0);
		lastGambleSent = 0;
		acceptedGamble = false;
		gambleConfirmed = false;
		gambleConfirmed2 = false;
		gambleRequested = false;
		canOffer = true;
		goodGamble = false;
		player.getPacketSender().sendString(3535, "Are you sure you want to make this gamble?");
		player.getPacketSender().sendInterfaceRemoval();
		player.getPacketSender().sendInterfaceRemoval();
	}
	
	public void giveItems() {
		
		//Notify the players
		winner.getPacketSender().sendMessage("Congratulations! You are the winner.");
		loser.getPacketSender().sendMessage("Oh no! Better luck next time.");	
		
		for (Item item : winner.getGamble().offeredItems) {
			winner.getInventory().add(item);
		}
		for (Item item : loser.getGamble().offeredItems) {
			winner.getInventory().add(item);
		}
		
		winner.getGamble().offeredItems.clear();
		loser.getGamble().offeredItems.clear();
	}


	private boolean falseGambleConfirm() {
		Player player2 = World.getPlayers().get(getGambleWith());
		return gambleConfirmed = player2.getGamble().gambleConfirmed = false;
	}

	public CopyOnWriteArrayList<Item> offeredItems = new CopyOnWriteArrayList<Item>();
	private boolean inGamble = false;
	private boolean gambleRequested = false;
	private int gambleWith = -1;
	Player host,gambler,winner, loser;
	private int gambleStatus,gambleType = -1, numberRolled = -1;
	public long lastGambleSent, lastAction;
	private boolean canOffer = true; 
	public boolean gambleConfirmed = false;
	public boolean gambleConfirmed2 = false;
	public boolean acceptedGamble = false;
	public boolean goodGamble = false;

	public void setGamble(boolean gamble) {
		this.inGamble = gamble;
	}

	public boolean inGamble() {
		return this.inGamble;
	}

	public void setGambleRequested(boolean gambleRequested) {
		this.gambleRequested = gambleRequested;
	}

	public boolean gambleRequested() {
		return this.gambleRequested;
	}

	public void setGambleWith(int gambleWith) {
		this.gambleWith = gambleWith;
	}

	public int getGambleWith() {
		return this.gambleWith;
	}

	public void setGambleStatus(int status) {
		this.gambleStatus = status;
	}

	public int getGambleStatus() {
		return this.gambleStatus;
	}

	public void setCanOffer(boolean canOffer) {
		this.canOffer = canOffer;
	}

	public boolean getCanOffer() {
		return canOffer && player.getInterfaceId() == 3323 && !player.isBanking() && !player.getPriceChecker().isOpen();
	}

	public int inGambleWith = -1;

	/**
	 * Checks if two players are the only ones in a gamble.
	 * @param p1	Player1 to check if he's 1/2 player in Gamble.
	 * @param p2	Player2 to check if he's 2/2 player in Gamble.
	 * @return		true if only two people are in the Gamble.
	 */
	public static boolean twoGamblers(Player p1, Player p2) {
		int count = 0;
		for(Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if(player.getGamble().inGambleWith == p1.getIndex() || player.getGamble().inGambleWith == p2.getIndex()) {
				count++;
			}
		}
		return count == 2;
	}

	/**
	 * The Gamble interface id.
	 */
	public static final int INTERFACE_ID = 3322;

	/**
	 * The Gamble interface id for removing items.
	 */
	public static final int INTERFACE_REMOVAL_ID = 3415;

}
