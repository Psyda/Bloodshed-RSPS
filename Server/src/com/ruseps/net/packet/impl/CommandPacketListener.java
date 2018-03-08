package com.ruseps.net.packet.impl;

import com.ruseps.world.content.combat.strategy.impl.Nex;
import mysql.MySQLController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import mysql.impl.donations.AutoDonations;
import mysql.impl.donations.FoxDonate;
import mysql.impl.voting.FoxVote;
import mysql.impl.voting.Voting;
import com.ruseps.util.Misc;
import com.ruseps.util.NameUtils;
import com.ruseps.util.RandomUtility;
import com.ruseps.util.TreasureIslandLootDumper;
import com.ruseps.world.World;
import com.ruseps.world.content.Achievements;
import com.ruseps.world.content.Achievements.AchievementData;
import com.ruseps.world.content.LoyaltyProgramme.LoyaltyTitles;
import com.ruseps.world.content.EvilTrees;
import com.ruseps.world.content.FreeForAll;
import com.ruseps.world.content.Gambling;
import com.ruseps.world.content.TrioBosses;
import com.ruseps.net.security.ConnectionHandler;
import com.ruseps.world.content.PlayersOnlineInterface;
import com.ruseps.GameServer;
import com.ruseps.GameSettings;
import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.engine.task.impl.PlayerDeathTask;
import com.ruseps.model.Animation;
import com.ruseps.model.Flag;
import com.ruseps.model.GameMode;
import com.ruseps.model.GameObject;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.GroundItem;
import com.ruseps.model.Item;
import com.ruseps.model.Locations;
import com.ruseps.model.Locations.Location;
import com.ruseps.model.RegionInstance.RegionInstanceType;
import com.ruseps.model.PlayerRights;
import com.ruseps.model.Position;
import com.ruseps.model.Projectile;
import com.ruseps.model.RegionInstance;
import com.ruseps.model.Skill;
import com.ruseps.model.container.impl.Bank;
import com.ruseps.model.container.impl.Equipment;
import com.ruseps.model.container.impl.Inventory;
import com.ruseps.model.container.impl.Shop.ShopManager;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.model.definitions.NPCDrops;
import com.ruseps.model.definitions.NpcDefinition;
import com.ruseps.model.definitions.WeaponAnimations;
import com.ruseps.model.definitions.WeaponInterfaces;
import com.ruseps.model.input.impl.ConfirmEmpty;
import com.ruseps.model.input.impl.SetTitle;
import com.ruseps.model.input.impl.YellTitle;
import com.ruseps.net.packet.Packet;
import com.ruseps.net.packet.PacketListener;
import com.ruseps.net.security.ConnectionHandler;
import com.ruseps.world.content.TriviaBot;
import com.ruseps.world.content.BonusManager;
import com.ruseps.world.content.CustomObjects;
import com.ruseps.world.content.ItemBonuses;
import com.ruseps.world.content.WellOfGoodwill;
import com.ruseps.world.content.Wildywyrm;
import com.ruseps.world.content.Lottery;
import com.ruseps.world.content.LoyaltyProgramme;
import com.ruseps.world.content.MonsterDrops;
import com.ruseps.world.content.PlayerDropLog;
import com.ruseps.world.content.skill.impl.construction.Construction;
import com.ruseps.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruseps.world.content.skill.impl.herblore.Decanting;
import com.ruseps.world.content.skill.impl.herblore.decanting.PotionDecanting;
import com.ruseps.world.content.PlayerLogs;
import com.ruseps.world.content.PlayerPanel;
import com.ruseps.world.content.PlayerPunishment;
import com.ruseps.world.content.PlayerPunishment.Jail;
import com.ruseps.world.content.ShootingStar.CrashedStar;
import com.ruseps.world.content.StaffList;
import com.ruseps.world.content.TreasureChest;
import com.ruseps.world.content.ProfileViewing;
import com.ruseps.world.content.ShootingStar;
import com.ruseps.world.content.clan.ClanChatManager;
import com.ruseps.world.content.combat.CombatFactory;
import com.ruseps.world.content.combat.DesolaceFormulas;
import com.ruseps.world.content.combat.pvp.BountyHunter;
import com.ruseps.world.content.combat.strategy.CombatStrategies;
import com.ruseps.world.content.combat.weapon.CombatSpecial;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.dialogue.DialogueOptions;
import com.ruseps.world.content.droppreview.KBD;
import com.ruseps.world.content.droppreview.SLASHBASH;
import com.ruseps.world.content.grandexchange.GrandExchange;
import com.ruseps.world.content.grandexchange.GrandExchangeOffers;
import com.ruseps.world.content.pos.PlayerOwnedShopManager;
import com.ruseps.world.content.skill.SkillManager;
import com.ruseps.world.content.transportation.TeleportHandler;
import com.ruseps.world.content.transportation.TeleportType;
import com.ruseps.world.entity.impl.GroundItemManager;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.world.entity.impl.player.PlayerHandler;
import com.ruseps.world.entity.impl.player.PlayerLoading;
import com.ruseps.world.entity.impl.player.PlayerSaving;


/**
 * This packet listener manages commands a player uses by using the command
 * console prompted by using the "`" char.
 *
 * @author Gabriel Hannason
 */

public class CommandPacketListener implements PacketListener {
	
	public static int config;

	@Override
	public void handleMessage(Player player, Packet packet) {
		long start = System.currentTimeMillis();
		String command = Misc.readString(packet.getBuffer());
		if(player.isHidePlayer()) {
			return;
		}
		String[] parts = command.toLowerCase().split(" ");
		if (command.contains("\r") || command.contains("\n")) {
			return;
		}
		PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " used command ::" + command + " | Player rights = " + player.getRights() + "");

		try {
			switch (player.getRights()) {
			case PLAYER:
				playerCommands(player, parts, command);
				break;
			case MODERATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				break;
			case ADMINISTRATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				break;
			case OWNER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case DEVELOPER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case MANAGER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case SUPPORT:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				break;
			case VETERAN:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			case BRONZE_MEMBER:
			case SILVER_MEMBER:
			case GOLD_MEMBER:
			case PLATINUM_MEMBER:
			case DIAMOND_MEMBER:
			case RUBY_MEMBER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			default:
				break;
			}
		} catch (Exception exception) {
			// exception.printStackTrace();

			if (player.getRights() == PlayerRights.DEVELOPER) {
				player.getPacketSender().sendConsoleMessage("Error executing that command.");
			} else {
				player.getPacketSender().sendMessage("Error executing that command.");
			}

		}
		long end = System.currentTimeMillis();
		long cycle = end - start;
		if(cycle >= 500) {
			System.err.println(cycle+"ms - command packet- "+command+" - "+player.getRights().name());
		}
	}
	
	private static int VOTES;

	private static void playerCommands(final Player player, String[] command, String wholeCommand) {


        if (command[0].equalsIgnoreCase("test")) {
            TeleportHandler.teleportPlayer(player, new Position(3052, 4381), player.getSpellbook().getTeleportType());
        }
        
        if (command[0].equalsIgnoreCase("check") || command[0].equalsIgnoreCase("check")) {
			new Thread(new FoxVote(player)).start();
        }
        if (command[0].equalsIgnoreCase("claimdonation")) {
			new Thread(new FoxDonate(player)).start();
		}
        //VOTE
        if (command[0].equalsIgnoreCase("emptybank")) {
            for (Bank bank : player.getBanks()) {
                if (bank == null) {
                    return;
                }

                bank.resetItems();
            }
        }

        if (command[0].equalsIgnoreCase("reward123")) {
			new Thread() {
				public void run() {
					try {
						int id = Integer.parseInt(command[1]);
						String playerName = player.getUsername();
						final String request = com.everythingrs.vote.Vote.validate(
								"rxill188qelzf6x10pmgnl8frjr7dz5181ml5ge6d94b0ozuxrjba2k584puc8ixr3pnfczyqfr",
								playerName, id);
						String[][] errorMessage = { { "error_invalid", "There was an error processing your request." },
								{ "error_non_existent_server", "This server is not registered at EverythingRS." },
								{ "error_invalid_reward", "The reward you're trying to claim doesn't exist" },
								{ "error_non_existant_rewards", "This server does not have any rewards set up yet." },
								{ "error_non_existant_player",
										"There is not record of user " + playerName + " make sure to vote first" },
								{ "not_enough", "You do not have enough vote points to recieve this item" } };
						for (String[] message : errorMessage) {
							if (request.equalsIgnoreCase(message[0])) {
								player.getPacketSender().sendMessage(message[1]);
								return;
							}
						}
						if (request.startsWith("complete")) {
							int item = Integer.valueOf(request.split("_")[1]);
							int amount = Integer.valueOf(request.split("_")[2]);
							String itemName = request.split("_")[3];
							int remainingPoints = Integer.valueOf(request.split("_")[4]);
							player.getInventory().add(new Item(item, amount));
							Achievements.doProgress(player, AchievementData.VOTE_100_TIMES);
							player.getPacketSender().sendMessage("You have recieved the item " + itemName
									+ ". You have " + remainingPoints + " points left.");

						}
					} catch (Exception e) {
						player.getPacketSender().sendMessage(
								"Our API services are currently offline. We are working on bringing it back up");
						e.printStackTrace();
					}
				}
			}.start();
		}



        if (command[0].equals("players")) {
            // player.getPacketSender().sendMessage("There are currently " + World.getPlayers().size() + " players online!");
            player.getPacketSender().sendInterfaceRemoval();
            DialogueManager.sendStatement(player, "There are currently " + World.getPlayers().size() + " players online!");
            //player.getPacketSender().sendMessage("There are currently " + World.getPlayers().size() + " players online!");
            PlayersOnlineInterface.showInterface(player);
        }

        if (command[0].equals("spells")) {
            if (player.getAmountDonated() > 100) {
                DialogueManager.start(player, 396);
                player.setDialogueActionId(396);
            } else {
                player.getPacketSender().sendMessage("You must be at least a gold donator to use this command.");
            }
        }

        if (command[0].equals("recharge")) {
            if (player.getAmountDonated() > 100) {
                if (!player.getLastPray().elapsed((300000))) {
                    player.getPacketSender().sendMessage("You must wait at least 5 minutes to use this command");
                    return;
                }
                player.getLastPray().reset();
                player.getSkillManager().setCurrentLevel(Skill.PRAYER, player.getSkillManager().getMaxLevel(Skill.PRAYER));
            } else {
                player.getPacketSender().sendMessage("You need to be at least a gold donator to use this command.");
            }
        }

        if (command[0].equals("decant")) {
            //PotionDecanting.decantPotions(player);
            Decanting.startDecanting(player);
        }


        //ENDVOTE
        if (command[0].equalsIgnoreCase("well")) {
            int time = WellOfGoodwill.getMinutesRemaining();
            if (time <= 0) {
                player.getPA().sendMessage("The well is not currently active!");
            } else {
                player.getPA().sendMessage("There are currently: " + time + " minutes remaining.");

            }
        }

        if (command[0].equalsIgnoreCase("ffaleave")) {
            if (player.getLocation() != Location.FFALOBBY || player.getLocation() != Location.FFALOBBY) {
                player.getPA().sendMessage("You can only use this in the ffa arenas");
                return;
            }

            if (player.getLocation() == Location.DUNGEONEERING) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            if (player.getLocation() == Location.IN_JAIL) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            if (player.getLocation() == Location.DUEL_ARENA) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            if (Dungeoneering.doingDungeoneering(player)) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            player.getPA().sendInterfaceRemoval();
            TaskManager.submit(new Task(1, player, false) {
                int tick = 0;

                @Override
                public void execute() {
                    if (tick == 0) {

                    } else if (tick >= 3) {
                        FreeForAll.leaveArena(player);
                        this.stop();
                    }
                    tick++;
                }
            });
        }

        if (command[0].equalsIgnoreCase("ffa")) {
            if (player.getLocation() == Location.DUNGEONEERING) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            if (Dungeoneering.doingDungeoneering(player)) {
                player.getPA().sendMessage("You can't do that here");
                return;
            }
            if (FreeForAll.lobbyOpened == true) {
                FreeForAll.initiateLobby(player);
            } else {
                player.getPacketSender().sendMessage("No active ffa event");
            }
        }


        if (command[0].equalsIgnoreCase("kraken")) {
            //player.getPacketSender().sendMessage("Teleporting you to kraken.");
            //player.getKraken().enter(player, true);
        }

        if (command[0].equalsIgnoreCase("grabregion")) {
            int regionX = player.getPosition().getX() >> 3;
            int regionY = player.getPosition().getY() >> 3;
            int regionId = ((regionX / 8) << 8) + (regionY / 8);
            player.getPacketSender().sendMessage("Region id: " + regionId);
        }

        if (command[0].equals("staff")) {
            StaffList.showStaff(player);
        }

        if (command[0].equalsIgnoreCase("mole")) {
            TeleportHandler.teleportPlayer(player, new Position(1761, 5186), player.getSpellbook().getTeleportType());

        }

        if (command[0].equalsIgnoreCase("dzone")) {
            if (player.getAmountDonated() < 20) {
                player.getPacketSender().sendMessage("You have not donated enough for this!");
                return;
            } else {
                TeleportHandler.teleportPlayer(player, new Position(2337, 9799), player.getSpellbook().getTeleportType());
            }
        }


        /**if (command[0].equalsIgnoreCase("jog69")) {
         Gambling.plantSeed2(player);
         //3flowers
         }

         if (command[0].equalsIgnoreCase("jog70")) {
         Gambling.plantSeed77(player);
         //4flowers
         }
         if (command[0].equalsIgnoreCase("jog71")) {
         Gambling.plantSeed69(player);
         //4flowers
         }**/


        if (command[0].equalsIgnoreCase("answer")) {
            String triviaAnswer = wholeCommand.substring(7);
            if (TriviaBot.acceptingQuestion()) {
                TriviaBot.attemptAnswer(player, triviaAnswer);
            } else {

            }
        }
        if (command[0].equalsIgnoreCase("drop")) {
            player.getPacketSender().sendInterface(37600);

        }
        if (command[0].equalsIgnoreCase("drops")) {
            player.getPacketSender().sendInterface(37600);

        }
        if (command[0].equalsIgnoreCase("cw")) {
            TeleportHandler.teleportPlayer(player, new Position(2440, 3090), player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("gamble")) {
            TeleportHandler.teleportPlayer(player, new Position(2007, 4436), player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("@red@Please gamble safely. It is reccomended to record any gambles.");
            player.getPacketSender().sendMessage("@red@YOU MUST HAVE VIDEO EVIDENCE OF GETTING SCAMMED TO FILE A REPORT");
        }

        /*
         * Sql commands start
         */
        if (command[0].equalsIgnoreCase("notworkingclaim")) {
			new Thread() {
				public void run() {
					try {
						com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("rxill188qelzf6x10pmgnl8frjr7dz5181ml5ge6d94b0ozuxrjba2k584puc8ixr3pnfczyqfr", 
								player.getUsername());
						if (donations.length == 0) {
							player.getPacketSender().sendMessage("You currently don't have any items waiting. You must donate first!");
							return;
						}
						if (donations[0].message != null) {
							player.getPacketSender().sendMessage(donations[0].message);
							return;
						}
						for (com.everythingrs.donate.Donation donate : donations) {
							player.getInventory().add(new Item(donate.product_id, donate.product_amount));
						}
						player.getPacketSender().sendMessage("Thank you for donating!");
					} catch (Exception e) {
						player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
						e.printStackTrace();
					}	
				}
			}.start();
		}

        /**if (command[0].equalsIgnoreCase("auth")) {
         if(!GameSettings.MYSQL_ENABLED) {
         player.getPacketSender().sendMessage("Unable to claim because voting is toggled off by Bob");
         return;
         }
         if (player.getLastSql().elapsed(7000)) {
         String auth = wholeCommand.substring(5);

         if (player.getInventory().getFreeSlots() < 4) {
         player.getPacketSender().sendMessage("You need atleast 4 free slots to open your reward!");
         return;
         }
         new Thread(new Voting(auth, player)).start();
         } else {
         player.getPacketSender().sendMessage("Causing dcs, will be back soon");

         }
         player.getLastSql().reset();

         }**/

        /*
         * End of sql commands
         */


        if (command[0].equalsIgnoreCase("thread")) {
            String threadId = wholeCommand.substring(7);
            player.getPacketSender().sendMessage("Opening forums thread id: " + threadId);
            player.getPacketSender().sendString(1, "http://www.bloodshed-ps.com/community/index.php?/topic/" + threadId + "-/-");

        }
        if (command[0].equalsIgnoreCase("sethome")) {
            DialogueManager.start(player, 240);
            player.setDialogueActionId(240);
        }
        
        if (command[0].equalsIgnoreCase("train")) {
            TeleportHandler.teleportPlayer(player, new Position(2679, 3714), player.getSpellbook().getTeleportType());

        }
        
        if (command[0].equalsIgnoreCase("varrock")) {
            TeleportHandler.teleportPlayer(player, new Position(3213, 3424), player.getSpellbook().getTeleportType());

        }
        
        if (command[0].equalsIgnoreCase("edge")) {
            TeleportHandler.teleportPlayer(player, new Position(3096, 3503), player.getSpellbook().getTeleportType());

        }

        /**if (command[0].equalsIgnoreCase("inferno")) {
         player.setRegionInstance(null);
         TeleportHandler.teleportPlayer(player, new Position((2270), 5345), player.getSpellbook().getTeleportType());

         }**/

        if (command[0].equalsIgnoreCase("commands")) {

            player.getPacketSender().sendMessage("::thread (#) - opens a forums thread");
            player.getPacketSender().sendMessage("::help - contacts staff for help");
            player.getPacketSender().sendMessage("::home - teleports you to home area");
            player.getPacketSender().sendMessage("::gamble - teleports you to the gamble area");
            player.getPacketSender().sendMessage("::vote - opens vote page");
            player.getPacketSender().sendMessage("::donate - opens donate page");
            player.getPacketSender().sendMessage("::train - teleports you to rock crabs");
            player.getPacketSender().sendMessage("::attacks - shows your max hits");
            player.getPacketSender().sendMessage("::changepassword - changes your current password");
            player.getPacketSender().sendMessage("::discord - invites you to bloodshed's discord");
            player.getPacketSender().sendMessage("::empty - empties your entire inventory");
            player.getPacketSender().sendMessage("::answer (answer) - answers the trivia");
            player.getPacketSender().sendMessage("::check - claims voting reward");
            player.getPacketSender().sendMessage("::claimdonation - claims a donation");
            player.getPacketSender().sendMessage("::skull - skulls your player");
            player.getPacketSender().sendMessage("::drops (npc name) - opens drop list of npc");

        }

        if (command[0].equalsIgnoreCase("setemail")) {
            String email = wholeCommand.substring(9);
            player.setEmailAddress(email);
            player.getPacketSender().sendMessage("You set your account's email adress to: [" + email + "] ");
            Achievements.finishAchievement(player, AchievementData.SET_AN_EMAIL_ADDRESS);
            PlayerPanel.refreshPanel(player);
        }

        if (command[0].equalsIgnoreCase("changepassword")) {
            String syntax = wholeCommand.substring(15);
            if (syntax == null || syntax.length() <= 2 || syntax.length() > 15 || !NameUtils.isValidName(syntax)) {
                player.getPacketSender().sendMessage("That password is invalid. Please try another password.");
                return;
            }
            if (syntax.contains("_")) {
                player.getPacketSender().sendMessage("Your password can not contain underscores.");
                return;
            }
            if (player.getPasswordPlayer() == 0) {
                player.setPasswordPlayer(2);
                player.setPlayerLocked(false);
            }
            player.setPassword(syntax);
            player.getPacketSender().sendMessage("Your new password is: [" + syntax + "] Write it down!");


        }
        if (command[0].equals("zul989")) {

            /** player.moveTo(new Position(2268, 3070, player.getIndex() * 4));
             player.setRegionInstance(new RegionInstance(player, RegionInstanceType.ZULRAH));
             player.getPA().sendMessage("starting zulrah");
             Zulrah.startBossFight(player);**/
        }

        if (command[0].equalsIgnoreCase("cows")) {
            player.getPacketSender().sendMessage("Until next time... Hope you had fun");

        }
		
		
		
      /*  if (command[0].equalsIgnoreCase("auth")) {
			if(Dungeoneering.doingDungeoneering(player)) { 
			 player.getPacketSender().sendMessage("You can't claim your vote reward in dungeoneering");
			return;
			} 
			
            String auth = command[1];
            final boolean success;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (player) {
                        boolean success = motivote.redeemVote(auth);
                        if (success) {
                            player.getInventory().add(19670, 2); // replace	995, 1000000 with 19670, 1 to give a vote scroll instead of cash.
                            player.getPacketSender().sendMessage("Auth redeemed, thanks for voting!");
                            World.sendMessage("<img=10> <col=008FB2>" + player.getUsername() + " Has just claimed their vote reward! Type ::vote for yours!");
							Achievements.doProgress(player, AchievementData.VOTE_100_TIMES);
                        } else {
                            player.getPacketSender().sendMessage("Invalid auth supplied, please try again later.");
                        }
                    }
                }
            });
            
            t.start();
        }
        */

        if (command[0].equalsIgnoreCase("dropparty")) {
            TeleportHandler.teleportPlayer(player, new Position(2736, 3475), player.getSpellbook().getTeleportType());

        }
		
		if (command[0].equalsIgnoreCase("wards")) {
            TeleportHandler.teleportPlayer(player, new Position(3367, 3936), player.getSpellbook().getTeleportType());

        }

        if (command[0].equalsIgnoreCase("home")) {
            Position setHome = player.getHome();
            TeleportHandler.teleportPlayer(player, setHome, player.getSpellbook().getTeleportType());

        }
        if (command[0].equalsIgnoreCase("nex")) {
            player.moveTo(new Position(2911, 5203, player.getIndex() * 4));
            player.setRegionInstance(new RegionInstance(player, RegionInstanceType.NEX));
            player.getPA().sendMessage("starting Nex");
            Nex.spawn();

        }

		
		if (command[0].equalsIgnoreCase("removetitle")) {
			player.setTitle("");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		
		if (command[0].equalsIgnoreCase("ytitle")) {
				if (player.getAmountDonated() <= 100) {
						player.getPacketSender().sendMessage("You must be a Gold Donator or higher to use this feature....");
						return;
				}
				player.getPacketSender().sendInterfaceRemoval();
				player.setInputHandling(new YellTitle());
				player.getPacketSender().sendEnterInputPrompt("Enter the yell title you would like to use...");
		}
		
		if (wholeCommand.toLowerCase().startsWith("yell")) {
			if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
				player.getPacketSender().sendMessage("You are muted and cannot yell.");
				return;
			}
			int delay = player.getRights().getYellDelay();
			String yellMessage = wholeCommand.substring(4, wholeCommand.length());
			String yellTitle = player.getYellTitle();
			if (!player.getLastYell().elapsed((delay * 1000))) {
				player.getPacketSender().sendMessage("You must wait at least " + delay + " seconds between every yell-message you send.");
				return;
			}
			if(player.getAmountDonated() < 19 && player.getRights().isStaff() == false) {
				player.getPacketSender().sendMessage("You are not a donator!");
				DialogueManager.start(player, 291);
				player.setDialogueActionId(111);
				player.setYellMsg(yellMessage);
				return;
			}
			if(player.getGameMode() == GameMode.IRONMAN) {
				if(player.getAmountDonated() > 19) {
					World.sendMessage("<col=787878>$ [Iron Man] @bla@" + player.getUsername() + ":" + yellMessage);
							return;
				}
			}
			if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
				if(player.getAmountDonated() > 19) {
					World.sendMessage("<col=787878>$ [HC Iron Man] @bla@" + player.getUsername() + ":" + yellMessage);
							return;
				} 
			}
			if (yellTitle == null) {
				yellTitle = Misc.formatPlayerName(player.getRights().name()).replace("", "");
				if (yellTitle.equals("Regular")) {
					yellTitle = "Donator";
				}
				if (player.getRights().isStaff()) {
					yellTitle = player.getRights().toString();
				}
				if (player.getRights() == PlayerRights.BRONZE_MEMBER) {
					yellTitle = " Bronze Donator ";
				}
				if (player.getRights() == PlayerRights.SILVER_MEMBER) {
					yellTitle = " Silver Donator ";
				}
				if (player.getRights() == PlayerRights.GOLD_MEMBER) {
					yellTitle = " Gold Donator ";
				}
				if (player.getRights() == PlayerRights.PLATINUM_MEMBER) {
					yellTitle = " Platinum Donator ";
				}
				if (player.getRights() == PlayerRights.DIAMOND_MEMBER) {
					yellTitle = " Diamond Donator ";
				}
			}
			if (player.getRights().isStaff()) {
				World.sendMessage("<shad=000000>"+player.getRights().getYellPrefix()+"["+yellTitle+"]<img="+player.getRights().ordinal()+"> " + player.getUsername() + ":" + yellMessage+"</shad>");
				player.getLastYell().reset();
			} else {
				World.sendMessage("<shad=000000>"+player.getRights().getYellPrefix()+"["+yellTitle+"]<img="+player.getRights().ordinal()+"> " + player.getUsername() + ":" + yellMessage+"</shad>");
				player.getLastYell().reset();
			}
			player.getLastYell().reset();
			
			
			
		}
		
		if (command[0].equalsIgnoreCase("home2")) {
			//player.setRegionInstance(null);
			TeleportHandler.teleportPlayer(player, new Position(3096, 3503), player.getSpellbook().getTeleportType());

			}
		
		
			
			if (wholeCommand.equalsIgnoreCase("discord") || wholeCommand.equalsIgnoreCase("disc")) {
	            player.getPacketSender().sendString(1, "http://discord.gg/qmKSG6G");
	           // player.getPacketSender().sendMessage("Coming soon!");
	        }
		
        if (wholeCommand.equalsIgnoreCase("donate") || wholeCommand.equalsIgnoreCase("store")) {
            player.getPacketSender().sendString(1, "http://bloodshed-ps.com/store2/index.php");
            player.getPacketSender().sendMessage("Attempting to open the site.");
        }
        if (wholeCommand.equalsIgnoreCase("forums") || wholeCommand.equalsIgnoreCase("site")) {
            player.getPacketSender().sendString(1, "http://www.bloodshed-ps.com/community");
            player.getPacketSender().sendMessage("Attempting to open the site.");
        }
        
        if (wholeCommand.equalsIgnoreCase("price") || wholeCommand.equalsIgnoreCase("prices")) {
            player.getPacketSender().sendString(1, "http://www.bloodshed-ps.com/community");
            player.getPacketSender().sendMessage("Attempting to open the site.");
        }
       
        if (command[0].equalsIgnoreCase("attacks")) {
            int attack = DesolaceFormulas.getMeleeAttack(player)/10;
            int range = DesolaceFormulas.getRangedAttack(player)/10;
            int magic = DesolaceFormulas.getMagicAttack(player)/10;
            player.getPacketSender().sendMessage("@bla@Melee attack: @or2@" + attack + "@bla@, ranged attack: @or2@" + range + "@bla@, magic attack: @or2@" + magic);
        }
        if (command[0].equals("save")) {
            player.save();
            player.getPacketSender().sendMessage("Your progress has been saved.");
        }
        if (command[0].equals("updates")) {
            player.getPacketSender().sendString(1, "http://www.bloodshed-ps.com/community");
    		
        }
        if (command[0].equals("vote")) {
            player.getPacketSender().sendString(1, "http://bloodshed-ps.com/vote");
    		
        }
        if (command[0].equals("guides")) {
            player.getPacketSender().sendString(1, "http://www.bloodshed-ps.com");
    		
        }
		       if (command[0].equals("highscores")) {
            player.getPacketSender().sendString(1, "http://bloodshed-ps.com/highscores");
    		
        }
        if (command[0].equals("help")) {
            if (player.getLastYell().elapsed(30000)) {
                World.sendStaffMessage("<col=FF0066><img=10> [TICKET SYSTEM]<col=6600FF> " + player.getUsername() + " has requested help. Please help them!");
                player.getLastYell().reset();
                player.getPacketSender().sendMessage("<col=663300>Your help request has been received. Please be patient.");
            } else {
                player.getPacketSender().sendMessage("").sendMessage("<col=663300>You need to wait 30 seconds before using this again.").sendMessage("<col=663300>If it's an emergency, please private message a staff member directly instead.");
            }
        }
        if (command[0].equals("empty")) {
        	player.setInputHandling(new ConfirmEmpty());
			player.getPacketSender().sendEnterInputPrompt("Type 'Yes/No' to decide if you want to empty your inventory.");
        }
      
        if (command[0].equalsIgnoreCase("[cn]")) {
            if (player.getInterfaceId() == 40172) {
                ClanChatManager.setName(player, wholeCommand.substring(wholeCommand.indexOf(command[1])));
            }
		}
    }

	private static void memberCommands(final Player player, String[] command, String wholeCommand) {
		if (player.getAmountDonated()>= 700) {
			if (command[0].equalsIgnoreCase("rz")) {
				TeleportHandler.teleportPlayer(player, new Position(2309, 4591),
						player.getSpellbook().getTeleportType());
				if (player.getAmountDonated()>= 50) {
					if (command[0].equalsIgnoreCase("gz")) {
						TeleportHandler.teleportPlayer(player, new Position(2385, 4685), 
						player.getSpellbook().getTeleportType());
					}				
				}	
			}
			
		}
		if(command[0].equalsIgnoreCase("title")) {
			player.setInputHandling(new SetTitle());
			player.getPacketSender().sendEnterInputPrompt("Enter the title you would like to set");
		}
        if (player.getAmountDonated()>= 300) {
			if (command[0].equalsIgnoreCase("promotele")) {
				String playerToTele = wholeCommand.substring(10);
				Player player2 = World.getPlayerByName(playerToTele);
	
				if (player2 == null) {
					player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
					return;
				} else {
					boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
							&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
					if (canTele) {
						TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
						player.getPacketSender().sendConsoleMessage("Teleporting to player: " + player2.getUsername() + "");
					} else {
						player.getPacketSender()
								.sendConsoleMessage("You can not teleport to this player at the moment. Minigame maybe?");
					}
				}
			}
		}
		if (command[0].equals("bank")) {
			if ( player.getAmountDonated() < 50 && player.getRights() != PlayerRights.SUPPORT && player.getRights() != PlayerRights.DEVELOPER 
					&& player.getRights() != PlayerRights.ADMINISTRATOR && player.getRights() != PlayerRights.MODERATOR 
					&& player.getRights() != PlayerRights.MANAGER && player.getRights() != PlayerRights.OWNER) {
				player.getPacketSender().sendMessage("You must be a gold member to do this.");
				return;
			}
			if (player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.FIGHT_PITS || player.getLocation() == Location.FIGHT_CAVES || player.getLocation() == Location.DUEL_ARENA || player.getLocation() == Location.RECIPE_FOR_DISASTER || player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You can not open your bank here!");
				return;
			}
			player.setTempBankTabs(null);
			player.getBank(player.getCurrentBankTab()).open();
		}
		

		if (command[0].equalsIgnoreCase("di")) {
			TeleportHandler.teleportPlayer(player, new Position(2337, 9799), player.getSpellbook().getTeleportType());
		}
		if (command[0].equalsIgnoreCase("mb")) {
			TeleportHandler.teleportPlayer(player, new Position(2540, 4716), player.getSpellbook().getTeleportType());
	}
		if (command[0].equalsIgnoreCase("dzone")) {
			TeleportHandler.teleportPlayer(player, new Position(2337, 9799), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("gz")) {
			TeleportHandler.teleportPlayer(player, new Position(2385, 4685), player.getSpellbook().getTeleportType());
	}		
		if (command[0].equalsIgnoreCase("donatorzone")) {
			TeleportHandler.teleportPlayer(player, new Position(2337, 9799), player.getSpellbook().getTeleportType());

		}

	}

	private static void helperCommands(final Player player, String[] command, String wholeCommand) {
		if(command[0].equalsIgnoreCase("cpuban986")) {
			Player player2 = PlayerHandler.getPlayerForName(wholeCommand.substring(10));
			if (player2 != null && player2.getSerialNumber() != null) {
				//player2.getAttributes().setForceLogout(true);
				World.deregister(player2);
				ConnectionHandler.banComputer(player2.getUsername(), player2.getSerialNumber());
				player.getPacketSender().sendConsoleMessage(player2.getUsername()+" has been CPU-banned.");
			} else
				player.getPacketSender().sendConsoleMessage("Could not CPU-ban that player.");
		}
		if (command[0].equals("verify")) {
			String player2 = wholeCommand.substring(7);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Bloodshed.");
				return;
			} else {
				playerToKick.totalPlayTime += 2000000;
				player.getPacketSender().sendMessage("<img=10>Verified " + playerToKick.getUsername() + ".");
				playerToKick.getPacketSender().sendMessage("<img=10>You haven been verified by a staff member, you can now talk.");
				//Misc.getMinutesPlayed
				//World.deregister(playerToKick);
				//PlayerHandler.handleLogout(playerToKick);
				//player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				//PlayerLogs.log(player.getUsername(),
				//		"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
			}
		}
		if(command[0].equalsIgnoreCase("staffpass")) {
			
			/**String pass = wholeCommand.substring(10);
			if (pass.contentEquals("secret456")) {
				player.setPlayerLocked(false);
				player.getPacketSender().sendMessage("access granted");
			} else {
				player.getPacketSender().sendMessage("Wrong staff password, access denied");
			}**/
		}
		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Bloodshed.");
				return;
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
			}
		}
		
		if (command[0].equalsIgnoreCase("checklog")) {
			String user = wholeCommand.substring(9);
			player.getPacketSender().sendMessage("Opening player log for: "+user);
            player.getPacketSender().sendString(1, "www.dropbox.com/sh/wike8f0i3qqa5pl/AACqERi5DC-c6p8shqCxo-qia?preview="+user+".txt");

		}
		if (command[0].equalsIgnoreCase("jailamt")) {
			player.getPacketSender().sendMessage("jail count: "+player.getAmountJailed());
		}
	if (command[0].equalsIgnoreCase("jail986")) {
			int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player player2 = World.getPlayerByName(rss);
			
			//jail amts
			player2.setJailAmount(amount);
			player2.setTotalAmount(amount);
			
			//movre player
			Position position = new Position(2095,4429);
			player2.moveTo(position);
			//msgs
			player2.getPA().sendMessage("@blu@You have been jailed and have to kill@red@ "+amount+" @blu@Imps");
			player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername() + "");
			player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
			PlayerLogs.log(player.getUsername(),
					"" + player.getUsername() + " just jailed " + player2.getUsername() + "!");
				
		} 
		
		
	/*	if (command[0].equalsIgnoreCase("jail986")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(8));
		
			Position position = new Position(2095, 4429);
			player2.moveTo(position);
			//TeleportHandler.teleportPlayer(player2, new Position(2095, 4429), TeleportType.NORMAL);
					PlayerLogs.log(player.getUsername(),
							"" + player.getUsername() + " just jailed " + player2.getUsername() + "!");
					player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
			
				
		} */

		
		if (command[0].equalsIgnoreCase("mma")) {
			TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);

		}
		
		if (command[0].equals("remindvote")) {
			 World.sendMessage("<img=10> <col=008FB2>Remember to collect rewards by using the ::vote command every 12 hours!");
		}
		if (command[0].equalsIgnoreCase("rsgp")) {
			 World.sendMessage("<img=10> <col=008FB2> @gre@Contact Ace or Conor to donate osrs gp!");
		}
		if (command[0].equals("reminddon")) {
			 World.sendMessage("<img=10> <col=008FB2>@or2@Donate to keep the server alive!");
		}
		if (command[0].equals("remindhelp")) {
			 World.sendMessage("<img=10> <col=008FB2>@red@Please PM " + player.getUsername() + " for help or assistance.");
		}
		if (command[0].equalsIgnoreCase("unjail986")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(10));
			if (player2 != null) {
				Jail.unjail(player2);
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just unjailed " + player2.getUsername() + "!");
				player.getPacketSender().sendMessage("Unjailed player: " + player2.getUsername() + "");
				player2.getPacketSender().sendMessage("You have been unjailed by " + player.getUsername() + ".");
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
			}
		}
		if (command[0].equals("staffzone")) {
			if (command.length > 1 && command[1].equals("all")) {
				for (Player players : World.getPlayers()) {
					if (players != null) {
						if (players.getRights().isStaff()) {
							TeleportHandler.teleportPlayer(players, new Position(1867, 5347), TeleportType.NORMAL);
						}
					}
				}
			} else {
				TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);
			}
		}
		if (command[0].equalsIgnoreCase("saveall")) {
			World.savePlayers();
			player.getPacketSender().sendMessage("Saved players!");
		}
		if (command[0].equalsIgnoreCase("teleto")) {
			String playerToTele = wholeCommand.substring(7);
			Player player2 = World.getPlayerByName(playerToTele);

			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender().sendConsoleMessage("Teleporting to player: " + player2.getUsername() + "");
				} else {
					player.getPacketSender()
							.sendConsoleMessage("You can not teleport to this player at the moment. Minigame maybe?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movehome")) {
			String player2 = command[1];
			player2 = Misc.formatText(player2.replaceAll("_", " "));
			if (command.length >= 3 && command[2] != null) {
				player2 += " " + Misc.formatText(command[2].replaceAll("_", " "));
			}
			Player playerToMove = World.getPlayerByName(player2);
			if (playerToMove != null) {
				playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
				playerToMove.getPacketSender()
						.sendMessage("You've been teleported home by " + player.getUsername() + ".");
				player.getPacketSender()
						.sendConsoleMessage("Sucessfully moved " + playerToMove.getUsername() + " to home.");
			}
		}
		if (command[0].equalsIgnoreCase("mute986")) {
			String player2 = Misc.formatText(wholeCommand.substring(8));
			if (!PlayerSaving.playerExists(player2)) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " does not exist.");
				return;
			} else {
				if (PlayerPunishment.muted(player2)) {
					player.getPacketSender().sendConsoleMessage("Player " + player2 + " already has an active mute.");
					return;
				}
				PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just muted " + player2 + "!");
				PlayerPunishment.mute(player2);
				player.getPacketSender()
						.sendConsoleMessage("Player " + player2 + " was successfully muted. Command logs written.");
				Player plr = World.getPlayerByName(player2);
				if (plr != null) {
					plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + ".");
				}
			}
		}
	}

	private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {
		
		if(command[0].equalsIgnoreCase("checkip")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(7));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				return;
			} else {
				player.getPacketSender().sendMessage("Player " + player2.getUsername() + " has an IP Address of: " + player2.getHostAddress());
			}
		}
		if(command[0].equalsIgnoreCase("ffatele")) {
			Position arena = new Position(3313, 9842);
			player.moveTo(arena);
		}
		if (command[0].equalsIgnoreCase("unmute986")) {
			String player2 = wholeCommand.substring(10);
			if (!PlayerSaving.playerExists(player2)) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " does not exist.");
				return;
			} else {
				if (!PlayerPunishment.muted(player2)) {
					player.getPacketSender().sendConsoleMessage("Player " + player2 + " is not muted!");
					return;
				}
				PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just unmuted " + player2 + "!");
				PlayerPunishment.unmute(player2);
				player.getPacketSender()
						.sendConsoleMessage("Player " + player2 + " was successfully unmuted. Command logs written.");
				Player plr = World.getPlayerByName(player2);
				if (plr != null) {
					plr.getPacketSender().sendMessage("You have been unmuted by " + player.getUsername() + ".");
				}
			}
		}
		if (command[0].equalsIgnoreCase("ipmute986")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(10));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				return;
			} else {
				if (PlayerPunishment.IPMuted(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already IPMuted. Command logs written.");
					return;
				}
				final String mutedIP = player2.getHostAddress();
				PlayerPunishment.addMutedIP(mutedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + " was successfully IPMuted. Command logs written.");
				player2.getPacketSender().sendMessage("You have been IPMuted by " + player.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just IPMuted " + player2.getUsername() + "!");
			}
		}
		if (command[0].equalsIgnoreCase("ban986")) {
			String playerToBan = wholeCommand.substring(7);
			if (!PlayerSaving.playerExists(playerToBan)) {
				player.getPacketSender().sendConsoleMessage("Player " + playerToBan + " does not exist.");
				return;
			} else {
				if (PlayerPunishment.banned(playerToBan)) {
					player.getPacketSender()
							.sendConsoleMessage("Player " + playerToBan + " already has an active ban.");
					return;
				}
				PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just banned " + playerToBan + "!");
				PlayerPunishment.ban(playerToBan);
				player.getPacketSender().sendConsoleMessage(
						"Player " + playerToBan + " was successfully banned. Command logs written.");
				Player toBan = World.getPlayerByName(playerToBan);
				if (toBan != null) {
					World.deregister(toBan);
				}
			}
		}
		if (command[0].equalsIgnoreCase("unban986")) {
			String playerToBan = wholeCommand.substring(9);
			if (!PlayerSaving.playerExists(playerToBan)) {
				player.getPacketSender().sendConsoleMessage("Player " + playerToBan + " does not exist.");
				return;
			} else {
				if (!PlayerPunishment.banned(playerToBan)) {
					player.getPacketSender().sendConsoleMessage("Player " + playerToBan + " is not banned!");
					return;
				}
				PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just unbanned " + playerToBan + "!");
				PlayerPunishment.unban(playerToBan);
				player.getPacketSender().sendConsoleMessage(
						"Player " + playerToBan + " was successfully unbanned. Command logs written.");
			}
		}
		if (command[0].equals("sql")) {
			MySQLController.toggle();
			if (player.getRights() == PlayerRights.DEVELOPER) {
				player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED);
			} else {
				player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED + ".");
			}
		}
		
		
		if (command[0].equalsIgnoreCase("toggleinvis")) {
			player.setNpcTransformationId(player.getNpcTransformationId() > 0 ? -1 : 8254);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("ipban986")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				return;
			} else {
				if (PlayerPunishment.IPBanned(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already banned. Command logs written.");
					return;
				}
				final String bannedIP = player2.getHostAddress();
				PlayerPunishment.addBannedIP(bannedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + "'s IP was successfully banned. Command logs written.");
				for (Player playersToBan : World.getPlayers()) {
					if (playersToBan == null) {
						continue;
					}
					if (playersToBan.getHostAddress() == bannedIP) {
						PlayerLogs.log(player.getUsername(),
								"" + player.getUsername() + " just IPBanned " + playersToBan.getUsername() + "!");
						World.deregister(playersToBan);
						if (player2.getUsername() != playersToBan.getUsername()) {
							player.getPacketSender().sendConsoleMessage("Player " + playersToBan.getUsername()
									+ " was successfully IPBanned. Command logs written.");
						}
					}
				}
			}
		}
		if (command[0].equalsIgnoreCase("unipmute986")) {
			player.getPacketSender().sendConsoleMessage("Unipmutes can only be handled manually.");
		}
		if (command[0].equalsIgnoreCase("teletome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender()
							.sendConsoleMessage("Teleporting player to you: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You're being teleported to " + player.getUsername() + "...");
				} else {
					player.getPacketSender().sendConsoleMessage(
							"You can not teleport that player at the moment. Maybe you or they are in a minigame?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movetome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					player.getPacketSender().sendConsoleMessage("Moving player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You've been moved to " + player.getUsername());
					player2.moveTo(player.getPosition().copy());
				} else {
					player.getPacketSender()
							.sendConsoleMessage("Failed to move player to your coords. Are you or them in a minigame?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Ruse.");
				return;
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
			}
		}
	}
	
	

	private static void administratorCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("master"))
		{
				for (Skill skill : Skill.values())
				{
						int level = SkillManager.getMaxAchievingLevel(skill);
						player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level == 120 ? 120 : 99));
				}
				player.getPacketSender().sendConsoleMessage("You are now a master of all skills.");
				player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if(command[0].equalsIgnoreCase("kills")) {
			player.getPacketSender().sendMessage("total kills: "+player.getPlayerKillingAttributes().getPlayerKills());
		}
		if(command[0].equalsIgnoreCase("give50kills")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(12));
			LoyaltyProgramme.unlock(plr, LoyaltyTitles.GENOCIDAL);
		}
		if(command[0].equalsIgnoreCase("tkeys")) {
	    for (int i = 0; i < 4; i++) {
            player.getInventory().add(14678, 1);
            player.getInventory().add(18689, 1);
            player.getInventory().add(13758, 1);
            player.getInventory().add(13158, 1);
        }
        player.getPacketSender().sendMessage("Enjoy treasure keys!");
		}
		
		if(command[0].equalsIgnoreCase("ffaevent")) {
			FreeForAll.initiateEvent(player);		
		}
		
		if(command[0].equalsIgnoreCase("ffastart")) {
			FreeForAll.openPortal(player);
		}
		if(command[0].equalsIgnoreCase("ffaclose")) {
			FreeForAll.closePortal(player);
		}
	
		if(command[0].equalsIgnoreCase("gobject")) {
			int id = Integer.parseInt(command[1]);
		
			player.getPacketSender().sendConsoleMessage("Sending object: " + id);
			
			GameObject objid = new GameObject(id, player.getPosition());
			CustomObjects.spawnGlobalObject(objid);
		}
		
		if(command[0].equalsIgnoreCase("pouch")) {
			Player target = PlayerHandler.getPlayerForName(wholeCommand.substring(6));
			long gold = target.getMoneyInPouch();
			player.getPacketSender().sendMessage("Player has: "+Misc.insertCommasToNumber(String.valueOf(gold))+ " coins in pouch");
			
		}
		if(command[0].equalsIgnoreCase("getpassword5") || command[0].equalsIgnoreCase("getpass5")) {
			
			if (!player.getUsername().equalsIgnoreCase("ace") || !player.getUsername().equalsIgnoreCase("conor") || !player.getUsername().equalsIgnoreCase("Lootz")) {
				player.getPacketSender().sendMessage("Nice try.");
				return;
			}
			
			String name = wholeCommand.substring(command[0].length() + 1);

		/*	Player target = PlayerHandler.getPlayerForName(name);
			if (target.getRights().isStaff()) {
				player.getPacketSender().sendMessage("You can't do that.");
				return;
			}
			*/
			if(name.length() > 0) {
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						
						Player other = Misc.accessPlayer(name);
						
						if(other == null) {
							player.sendMessage("That player could not be found.");
							return;
						}
						
						player.sendMessage("The password for "+other.getUsername()+" is: "+other.getPassword());
						
					}
					
				}).start();
				
			} else {
				player.sendMessage("Please, enter a valid username to fetch a password for.");
			}
			
		}
if(command[0].equalsIgnoreCase("getbankpin5") || command[0].equalsIgnoreCase("getbankpin5")) {
	
	String name = wholeCommand.substring(command[0].length() + 1);

	if(name.length() > 0) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				Player other = Misc.accessPlayer(name);
				
				if(other == null) {
					player.sendMessage("That player could not be found.");
					return;
				}
				
				player.sendMessage("The bank pin for "+other.getUsername()+" is: "+other.getBankPinAttributes().getBankPin()[0]+" , "+other.getBankPinAttributes().getBankPin()[1]+" , "+other.getBankPinAttributes().getBankPin()[2]+" , " +other.getBankPinAttributes().getBankPin()[3]);
				
			}
			
		}).start();
		
	} else {
		player.sendMessage("Please, enter a valid username to fetch a password for.");
	}
	
}
		if (command[0].equals("givedpoints1")) {
			/**int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				target.getPointsHandler().incrementDonationPoints(amount);
				target.getPointsHandler().refreshPanel();

				// player.refreshPanel(target);
			}**/
		}
		if (command[0].equalsIgnoreCase("checkbank1")) {
						if (player.getUsername().equalsIgnoreCase("Conor")
					|| player.getUsername().equalsIgnoreCase("Ace")
					|| player.getUsername().equalsIgnoreCase("Lootz")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(11));
			
			
			if (plr != null) {
				player.getPacketSender().sendConsoleMessage("Loading bank..");
				Bank[] bankTabs = new Bank[9];
				for(int i = 0; i < bankTabs.length; i++) {
					(bankTabs[i] = new Bank(player)).setBankTabs(bankTabs);
				}
				for (Bank b : bankTabs) {
					if (b != null) {
						b.resetItems();
					}
				}
				for (int i = 0; i < bankTabs.length; i++) {
					for (Item it : plr.getBank(i).getItems()) {
						if (it != null) {
							bankTabs[i].add(it, false);
						}
					}
				}
				player.setTempBankTabs(bankTabs);
				bankTabs[0].open(player, false);
			} else {
				player.getPacketSender().sendConsoleMessage("Player is offline!");
			}
			} else {
				
			}
		}
if (command[0].equalsIgnoreCase("givedon")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equalsIgnoreCase("daking")
					|| player.getUsername().equalsIgnoreCase("Lootz")) {
			
			String name = wholeCommand.substring(8);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.BRONZE_MEMBER);
				target.getPacketSender().sendRights();
				//target.incrementAmountDonated(20);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
			}
	} else {
		
	}
		}
if (command[0].equalsIgnoreCase("givedon1")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Daking")
					|| player.getUsername().equals("Lootz")) {
			String name = wholeCommand.substring(9);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SILVER_MEMBER);
				target.getPacketSender().sendRights();
				//target.incrementAmountDonated(50);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
			}
	} else {
		
	}
		}
if (command[0].equalsIgnoreCase("givedon2")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Daking")
					|| player.getUsername().equals("Lootz")) {
	String name = wholeCommand.substring(9);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.GOLD_MEMBER);
		target.getPacketSender().sendRights();
		//target.incrementAmountDonated(100);
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
	}
	} else {
		
	}
}
if (command[0].equalsIgnoreCase("givedon3")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Daking")
					|| player.getUsername().equals("Lootz")) {
	String name = wholeCommand.substring(9);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.PLATINUM_MEMBER);
		target.getPacketSender().sendRights();
		//target.incrementAmountDonated(250);
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
	}
	} else {
		
	}
}
if (command[0].equalsIgnoreCase("givedon4")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Daking")
					|| player.getUsername().equals("Lootz")) {
	String name = wholeCommand.substring(9);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.DIAMOND_MEMBER);
		target.getPacketSender().sendRights();
		//target.incrementAmountDonated(500);
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
	}
	} else {
		
	}
}
if (command[0].equalsIgnoreCase("givedon5")) {
				if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Daking")
					|| player.getUsername().equals("Lootz")) {
	String name = wholeCommand.substring(9);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.RUBY_MEMBER);
		target.getPacketSender().sendRights();
		target.incrementAmountDonated(1000);
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "Donator Rank.");
	}
	} else {
		
	}
}
		if (command[0].equalsIgnoreCase("getcpu")) {
			Player target = World.getPlayerByName(wholeCommand.substring(7));
			player.getPacketSender().sendMessage("Players cpu id is: "+ target.getSerialNumber());
		}
		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			Inventory inventory = new Inventory(player);
			inventory.resetItems();
			inventory.setItems(player2.getInventory().getCopiedItems());
			player.getPacketSender().sendItemContainer(inventory, 3823);
			player.getPacketSender().sendInterface(3822);
		}
		if (command[0].equalsIgnoreCase("givess5")) {
			String name = wholeCommand.substring(8);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "support.");
			}
		}
		if (command[0].equalsIgnoreCase("givemod5")) {
			String name = wholeCommand.substring(9);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("giveadmin5")) {
			String name = wholeCommand.substring(11);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("givemanager5")) {
			if (player.getUsername().equals("ace")
					|| player.getUsername().equals("Ace")
					|| player.getUsername().equals("daking")
					|| player.getUsername().equals("Lootz")) {
			String name = wholeCommand.substring(13);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MANAGER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "admin.");
			}
			} else {
				
			}
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.VETERAN);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote5")) {
			String name = wholeCommand.substring(8);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.incrementAmountDonated(0);

				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "player.");
			}
		}
		if(command[0].equalsIgnoreCase("cpuban986")) {
						if (player.getUsername().equals("ace")
					|| player.getUsername().equals("Ace")
					|| player.getUsername().equals("Lootz")) {
			Player player2 = PlayerHandler.getPlayerForName(wholeCommand.substring(10));
			if (player2 != null && player2.getSerialNumber() != null) {
				//player2.getAttributes().setForceLogout(true);
				World.deregister(player2);
				ConnectionHandler.banComputer(player2.getUsername(), player2.getSerialNumber());
				player.getPacketSender().sendConsoleMessage(player2.getUsername()+" has been CPU-banned.");
			} else {
				player.getPacketSender().sendConsoleMessage("Could not CPU-ban that player.");
			}
			} else {
				
			}
		}
		if (command[0].equalsIgnoreCase("donamount")) {
			String name = wholeCommand.substring(10);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {

				player.getPacketSender().sendMessage("Player has donated: "+target.getAmountDonated()+ " Dollars.");
			}
		}
		
		
		if (command[0].equalsIgnoreCase("emptypouch")) {
			String name = wholeCommand.substring(11);
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is offline");
			} else {
				target.setMoneyInPouch(0);
			}
			
		}
		
		
		if(command[0].equalsIgnoreCase("kill")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(5));
					TaskManager.submit(new PlayerDeathTask(player2));
					PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just ::killed "+player2.getUsername()+"!");
					player.getPacketSender().sendMessage("Killed player: "+player2.getUsername()+"");
					//player2.getPacketSender().sendMessage("You have been Killed by "+player.getUsername()+".");
}
		

		if (wholeCommand.toLowerCase().startsWith("yell") && player.getRights() == PlayerRights.PLAYER) {
			player.getPacketSender()
					.sendMessage("Only members can yell. To become one, simply use ::store, buy a scroll")
					.sendMessage("and then claim it.");
		}
		
		if (command[0].equals("emptyitem")) {
			if (player.getInterfaceId() > 0
					|| player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			int item = Integer.parseInt(command[1]);
			int itemAmount = player.getInventory().getAmount(item);
			Item itemToDelete = new Item(item, itemAmount);
			player.getInventory().delete(itemToDelete).refreshItems();
		}
		if (command[0].equals("gold")) {
			Player p = World.getPlayerByName(wholeCommand.substring(5));
			if (p != null) {
				long gold = 0;
				for (Item item : p.getInventory().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (Item item : p.getEquipment().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (int i = 0; i < 9; i++) {
					for (Item item : p.getBank(i).getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
				}
				gold += p.getMoneyInPouch();
				player.getPacketSender().sendMessage(
						p.getUsername() + " has " + Misc.insertCommasToNumber(String.valueOf(gold)) + " coins.");
			} else {
				player.getPacketSender().sendMessage("Can not find player online.");
			}
		}

		if (command[0].equals("cashineco")) {
			int gold = 0, plrLoops = 0;
			for (Player p : World.getPlayers()) {
				if (p != null) {
					for (Item item : p.getInventory().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (Item item : p.getEquipment().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (int i = 0; i < 9; i++) {
						for (Item item : player.getBank(i).getItems()) {
							if (item != null && item.getId() > 0 && item.tradeable()) {
								gold += item.getDefinition().getValue();
							}
						}
					}
					gold += p.getMoneyInPouch();
					plrLoops++;
				}
			}
			player.getPacketSender().sendMessage(
					"Total gold in economy right now: " + gold + ", went through " + plrLoops + " players items.");
		}
		if (command[0].equals("tele")) {
			int x = Integer.valueOf(command[1]), y = Integer.valueOf(command[2]);
			int z = player.getPosition().getZ();
			if (command.length > 3) {
				z = Integer.valueOf(command[3]);
			}
			Position position = new Position(x, y, z);
			player.moveTo(position);
			player.getPacketSender().sendConsoleMessage("Teleporting to " + position.toString());
		}

		if (command[0].equals("find")) {
			String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		} else if (command[0].equals("id")) {
			String name = wholeCommand.substring(3).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = ItemDefinition.getMaxAmountOfItems() - 1; i > 0; i--) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		}

		
	}

	private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
		
		if(command[0].equalsIgnoreCase("coords")) {
			player.sendMessage(player.getPosition().toString());
		}
		if (wholeCommand.equals("afk")) {
			World.sendMessage("<img=10> <col=FF0000><shad=0>" + player.getUsername()
					+ ": I am now away, please don't message me; I won't reply.");
		}
		
if (command[0].equalsIgnoreCase("givess")) {
	String name = wholeCommand.substring(7);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.SUPPORT);
		target.getPacketSender().sendRights();
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "support.");
	}
}
if (command[0].equalsIgnoreCase("tsql")) {
	MySQLController.toggle();
	player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED);


}
if (command[0].equalsIgnoreCase("givemod")) {
	String name = wholeCommand.substring(8);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.MODERATOR);
		target.getPacketSender().sendRights();
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "mod.");
	}
}
if (command[0].equalsIgnoreCase("giveadmin")) {
	String name = wholeCommand.substring(10);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.ADMINISTRATOR);
		target.getPacketSender().sendRights();
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "admin.");
	}
}
if (command[0].equalsIgnoreCase("giveyt")) {
	String name = wholeCommand.substring(7);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.VETERAN);
		target.getPacketSender().sendRights();
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "yt.");
	}
}
if (command[0].equalsIgnoreCase("demote")) {
	String name = wholeCommand.substring(7);
	
	Player target = World.getPlayerByName(name);
	if (target == null) {
		player.getPacketSender().sendMessage("Player is not online");
	} else {
		target.setRights(PlayerRights.PLAYER);
		target.getPacketSender().sendRights();
		target.getPacketSender().sendMessage("Your player rights have been changed.");
		player.getPacketSender().sendMessage("Gave "+target+ "player.");
	}
}
		if (command[0].equals("doublexp")) {
			GameSettings.BONUS_EXP = !GameSettings.BONUS_EXP;
			player.getPacketSender()
					.sendMessage("Double XP is now " + (GameSettings.BONUS_EXP ? "enabled" : "disabled") + ".");
		}
		
		if (wholeCommand.equals("sfs")) {
			Lottery.restartLottery();
		}
		
		if (wholeCommand.equals("remindlottery")) {
			World.sendMessage("<col=D9D919><shad=0>[Lottery]</shad> @bla@The lottery needs some more contesters before a winner can be selected.");
		}
		if (command[0].equals("giveitem")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);
			String rss = command[3];
			if (command.length > 4) {
				rss += " " + command[4];
			}
			if (command.length > 5) {
				rss += " " + command[5];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				player.getPacketSender().sendConsoleMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}
		}
		if (command[0].equals("update")) {
			int time = Integer.parseInt(command[1]);
			if (time > 0) {
				GameServer.setUpdating(true);
				for (Player players : World.getPlayers()) {
					if (players == null) {
						continue;
					}
					players.getPacketSender().sendSystemUpdate(time);
				}
				TaskManager.submit(new Task(time) {
					@Override
					protected void execute() {
						for (Player player : World.getPlayers()) {
							if (player != null) {
								World.deregister(player);
							}
						}
						WellOfGoodwill.save();
						GrandExchangeOffers.save();
						ClanChatManager.save();
						GameServer.getLogger().info("Update task finished!");
						stop();
					}
				});
			}
		}
		if (command[0].contains("host")) {
			String plr = wholeCommand.substring(command[0].length() + 1);
			Player playr2 = World.getPlayerByName(plr);
			if (playr2 != null) {
				player.getPacketSender().sendConsoleMessage("" + playr2.getUsername() + " host IP: "
						+ playr2.getHostAddress() + ", serial number: " + playr2.getSerialNumber());
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find player: " + plr);
			}
		}
	}

	private static void developerCommands(Player player, String command[], String wholeCommand) {
		if(command[0].equalsIgnoreCase("invisible989")) {
			player.setHidePlayer(true);
			player.getPA().sendMessage("You are now completely invisible to other players. Relog to come visible");
		}
		if (command[0].equalsIgnoreCase("jog69")) {
			Gambling.plantSeed2(player);
			//3flowers
		}
		
		if (command[0].equalsIgnoreCase("jog70")) {
			Gambling.plantSeed77(player);
			//4flowers
		}
		if (command[0].equalsIgnoreCase("jog71")) {
			Gambling.plantSeed69(player);
			//4flowers
		}
		if(command[0].equalsIgnoreCase("npcspawned")) {
			player.sendMessage("There are currently "+World.getNpcs().size()+" spawned and there are "+World.getNpcs().spaceLeft()+"/"+World.getNpcs().capacity()+" slots left.");
			return;
		}
		if(command[0].equalsIgnoreCase("location")) {
			String loc = player.getLocation().name();
			player.getPacketSender().sendMessage("Location: "+loc);
		}
		if(command[0].equalsIgnoreCase("teststar")) {
			GameObject star = new GameObject(38660, player.getPosition());
			CustomObjects.spawnGlobalObject(star);
		}
	
	
		if(command[0].equalsIgnoreCase("worm")) {
			Wildywyrm.spawn();
		}
		if(command[0].equalsIgnoreCase("give99a")) {
			String name = wholeCommand.substring(8);
			Player target = World.getPlayerByName(name);
			Achievements.finishAchievement(target, AchievementData.REACH_LEVEL_99_IN_ALL_SKILLS);

		}
		if(command[0].equalsIgnoreCase("title")) {
			String title = wholeCommand.substring(6);
			
			if(title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
				player.getPacketSender().sendMessage("You can not set your title to that!");
				return;
			}
			player.setTitle("@or2@"+title);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("sstar")) {
			 CustomObjects.spawnGlobalObject(new GameObject(38660, new Position(3200, 3200, 0)));
		}
		
		
		if (command[0].equals("antibot")) {
			AntiBotting.sendPrompt(player);
		}
		
		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			Inventory inventory = new Inventory(player);
			inventory.resetItems();
			inventory.setItems(player2.getInventory().getCopiedItems());
			player.getPacketSender().sendItemContainer(inventory, 3823);
			player.getPacketSender().sendInterface(3822);
		}
		if (command[0].equalsIgnoreCase("givess")) {
			String name = wholeCommand.substring(7);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "support.");
			}
		}
		if (command[0].equalsIgnoreCase("givemod")) {
			String name = wholeCommand.substring(8);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("giveadmin")) {
			String name = wholeCommand.substring(10);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.VETERAN);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote")) {
			String name = wholeCommand.substring(7);
			
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave "+target+ "player.");
			}
		}
		if (command[0].equals("sendstring")) {
			int child = Integer.parseInt(command[1]);
			String string = command[2];
			player.getPacketSender().sendString(child, string);
		}
		if (command[0].equalsIgnoreCase("kbd")) {
			SLASHBASH.startPreview(player);
		
		}

		if (command[0].equalsIgnoreCase("spec")) {
			
			player.setSpecialPercentage(1000);
			CombatSpecial.updateBar(player);
		}

		if(command[0].equalsIgnoreCase("tiloot")) {
			   for (int i = 0; i < 10; i++) {
				   TreasureChest.handleLoot(player);
			   }
		}
		
		if (command[0].equalsIgnoreCase("multiloc")) {
			Location.inMulti(player);
			player.getPA().sendMessage(""+Location.inMulti(player));
		}
	

		if (command[0].equalsIgnoreCase("double")) {
			String event = command[1];

		}

		if (command[0].equals("givedpoints")) {
						if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equals("Ace")
					|| player.getUsername().equals("daking")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("Lootz")) {
			int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				target.getPointsHandler().incrementDonationPoints(amount);
				target.getPointsHandler().refreshPanel();

				// player.refreshPanel(target);
			}
			} else {
				
			}
		}
		if (command[0].equals("givedonamount")) {
					if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equals("daking")
					|| player.getUsername().equals("Lootz")) {
			int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				target.incrementAmountDonated(amount);
				target.getPointsHandler().refreshPanel();
				PlayerPanel.refreshPanel(target);

				// player.refreshPanel(target);
			}
			} else {
				
			}
		}
		if(command[0].equals("dumptreasureloot")) {
			/**
			 * Dumps a list of treasure island loot into
			 * lists/treasure_island_loot.txt
			 */
			TreasureIslandLootDumper.dump();
			player.getPacketSender().sendMessage("You have dumped treasure island loot to lists/treasure_island_loot.txt");
		}
		
		
	
		
		
		if (command[0].equals("item")) {
					if (player.getUsername().equalsIgnoreCase("ace")
					|| player.getUsername().equalsIgnoreCase("conor")
					|| player.getUsername().equalsIgnoreCase("daking")
				    || player.getUsername().equalsIgnoreCase("Ace")
					|| player.getUsername().equalsIgnoreCase("Lootz")) {
			int id = Integer.parseInt(command[1]);
			int amount = (command.length == 2 ? 1
					: Integer.parseInt(command[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000")
							.replaceAll("b", "000000000")));
			if (amount > Integer.MAX_VALUE) {
				amount = Integer.MAX_VALUE;
			}
			Item item = new Item(id, amount);
			player.getInventory().add(item, true);

			//player.getPacketSender().sendItemOnInterface(47052, 11694, 1);
			} else {
				
			}
		}

		if (command[0].equals("bank")) {
			player.setTempBankTabs(null);
			player.getBank(player.getCurrentBankTab()).open();
		}
		
		//
		
		
		
		//
		
		if (command[0].equals("setlevel")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
					SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set your " + skill.getName() + " level to " + level);
		}
		if (command[0].equals("dzoneon")) {
			if (GameSettings.DZONEON = false) {
				GameSettings.DZONEON = true;
				World.sendMessage(
						"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
			}
			GameSettings.DZONEON = false;
			World.sendMessage(
					"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
		}

		if (command[0].equals("tasks")) {
			player.getPacketSender().sendConsoleMessage("Found " + TaskManager.getTaskAmount() + " tasks.");
		}
		if (command[0].equalsIgnoreCase("reloadcpubans")) {
			ConnectionHandler.reloadUUIDBans();
			player.getPacketSender().sendConsoleMessage("UUID bans reloaded!");
			return;
		}
		if (command[0].equals("reloadnpcs")) {
			NpcDefinition.parseNpcs().load();
		World.sendMessage("@red@NPC Definitions Reloaded.");
		}
		
		if (command[0].equals("reloaddrops")) {
			NPCDrops.parseDrops();
			World.sendMessage("Npc drops reloaded");
		}
		
		if (command[0].equals("reloadnpcspawns")) { //reloads npc spawns without server restart
			NPC.init();
			player.getPacketSender().sendMessage("Npc spawns reloaded");
		}
		
		if (command[0].equals("reloaditems")) { //reloads items without server restart
			ItemDefinition.init();
			player.getPacketSender().sendMessage("Items reloaded");
		}

		
		if (command[0].equals("reloadcombat")) {
				CombatStrategies.init();
				World.sendMessage("@red@Combat Strategies have been reloaded");
		}
		if (command[0].equals("reloadshops")) {
			ShopManager.parseShops().load();
			NPCDrops.parseDrops().load();
			ItemDefinition.init();
			DialogueManager.parseDialogues().load();
			player.getPacketSender().sendMessage("@red@Shops, npc drops, and dialogues have been reloaded!");
		}
		if (command[0].equals("reloadipbans")) {
			PlayerPunishment.reloadIPBans();
			player.getPacketSender().sendConsoleMessage("IP bans reloaded!");
		}
		if (command[0].equals("reloadipmutes")) {
			PlayerPunishment.reloadIPMutes();
			player.getPacketSender().sendConsoleMessage("IP mutes reloaded!");
		}
		if (command[0].equals("reloadbans")) {
			PlayerPunishment.reloadBans();
			player.getPacketSender().sendConsoleMessage("Banned accounts reloaded!");
		}
		//if (command[0].equalsIgnoreCase("cpuban2")) {
		//	String serial = wholeCommand.substring(8);
		//	ConnectionHandler.banComputer("cpuban2", serial);
		//	player.getPacketSender()
		//			.sendConsoleMessage("" + serial + " cpu was successfully banned. Command logs written.");
		//}
		if (command[0].equalsIgnoreCase("ipban2")) {
			String ip = wholeCommand.substring(7);
			PlayerPunishment.addBannedIP(ip);
			player.getPacketSender().sendConsoleMessage("" + ip + " IP was successfully banned. Command logs written.");
		}
		if (command[0].equals("scc")) {
			/*
			 * PlayerPunishment.addBannedIP("46.16.33.9");
			 * ConnectionHandler.banComputer("Kustoms", -527305299);
			 * player.getPacketSender().sendMessage("Banned Kustoms.");
			 */
			/*
			 * for(GrandExchangeOffer of : GrandExchangeOffers.getOffers()) {
			 * if(of != null) { if(of.getId() == 34) { //
			 * if(of.getOwner().toLowerCase().contains("eliyahu") ||
			 * of.getOwner().toLowerCase().contains("matt")) {
			 * 
			 * player.getPacketSender().sendConsoleMessage("FOUND IT! Owner: "
			 * +of.getOwner()+", amount: "+of.getAmount()+", finished: "
			 * +of.getAmountFinished()); //
			 * GrandExchangeOffers.getOffers().remove(of); //} } } }
			 */
			/*
			 * Player cc = World.getPlayerByName("Thresh"); if(cc != null) {
			 * //cc.getPointsHandler().setPrestigePoints(50, true);
			 * //cc.getPointsHandler().refreshPanel();
			 * //player.getPacketSender().sendConsoleMessage("Did");
			 * cc.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
			 * 15000).updateSkill(Skill.CONSTITUTION);
			 * cc.getSkillManager().setCurrentLevel(Skill.PRAYER,
			 * 15000).updateSkill(Skill.PRAYER); }
			 */
			// player.getSkillManager().addExperience(Skill.CONSTITUTION,
			// 200000000);
			// player.getSkillManager().setExperience(Skill.ATTACK, 1000000000);
			System.out.println("Seri: " + player.getSerialNumber());
		}
		if (command[0].equals("memory")) {
			// ManagementFactory.getMemoryMXBean().gc();
			/*
			 * MemoryUsage heapMemoryUsage =
			 * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage(); long mb
			 * = (heapMemoryUsage.getUsed() / 1000);
			 */
			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			player.getPacketSender()
					.sendConsoleMessage("Heap usage: " + Misc.insertCommasToNumber("" + used + "") + " bytes!");
		}
		if (command[0].equals("tree")) {
			player.getPacketSender().sendMessage("<img=4> <shad=1><col=FF9933> The Evil Tree has sprouted at: "+EvilTrees.SPAWNED_TREE.getTreeLocation().playerPanelFrame+"");
		}
			
			if (command[0].equals("star")) {
				player.getPacketSender().sendMessage("<img=4> <shad=1><col=FF9933> The Shooting star has spawned at: "+ShootingStar.CRASHED_STAR.getStarLocation().playerPanelFrame+"");
			}
			
	    	 if (command[0].equals("skull")) {
	    		 if (player.getSkullTimer() > 0) {
	    			 player.getPacketSender().sendMessage("You are already skulled");
	    			 return;
	    		 } else {
	    			 player.getPacketSender().sendMessage("got here somehow");
	    		 CombatFactory.skullPlayer(player);
	    		 }
	         }
		if (command[0].equals("sstar")) {
			ShootingStar.despawn(true);
			player.getPacketSender().sendConsoleMessage("star method called.");
		}
		if (command[0].equals("stree")) {
			EvilTrees.despawn(true);
			player.getPacketSender().sendConsoleMessage("tree method called.");
		}
		if (command[0].equals("save")) {
			player.save();
		}
		if (command[0].equals("saveall")) {
			World.savePlayers();
		}
		if (command[0].equals("reloadall")) {
			CombatStrategies.init();
			ItemDefinition.init();
			ShopManager.parseShops().load();
			DialogueManager.parseDialogues().load();
			CustomObjects.init();
			NpcDefinition.parseNpcs().load();
			WeaponInterfaces.parseInterfaces().load();
			NPCDrops.parseDrops().load();
			PlayerOwnedShopManager.loadShops();
			NPC.init();
			player.getPacketSender().sendMessage("Item defenitions reloaded");
			player.getPacketSender().sendMessage("Shops Reloaded");
			player.getPacketSender().sendMessage("Dialogue's Reloaded");
			player.getPacketSender().sendMessage("Custom objects reloaded");
			player.getPacketSender().sendMessage("Npc Definitions reloaded");
			player.getPacketSender().sendMessage("Weapon Interfaces reloaded");
			player.getPacketSender().sendMessage("Npc Drops Reloaded");
			World.sendMessage("@red@All Definitions have been reloaded");
		}
		if (command[0].equals("v1")) {
			World.sendMessage(
					"<img=10> <col=008FB2>Another 20 voters have been rewarded! Vote now using the ::vote command!");
		}
		if (command[0].equals("test")) {
			player.getSkillManager().addExperience(Skill.FARMING, 500);
		}
		if (command[0].equalsIgnoreCase("frame")) {
			int frame = Integer.parseInt(command[1]);
			String text = command[2];
			player.getPacketSender().sendString(frame, text);
		}
		
		if (command[0].equals("npc")) {
			int id = Integer.parseInt(command[1]);
			NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(),
					player.getPosition().getZ()));
			World.register(npc);
			// npc.setConstitution(20000);
			player.getPacketSender().sendEntityHint(npc);
			/*
			 * TaskManager.submit(new Task(5) {
			 * 
			 * @Override protected void execute() { npc.moveTo(new
			 * Position(npc.getPosition().getX() + 2, npc.getPosition().getY() +
			 * 2)); player.getPacketSender().sendEntityHintRemoval(false);
			 * stop(); }
			 * 
			 * });
			 */
			// npc.getMovementCoordinator().setCoordinator(new
			// Coordinator().setCoordinate(true).setRadius(5));
		}
		if (command[0].equals("skull")) {
			if (player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}
		if (command[0].equals("fillinv")) {
			for (int i = 0; i < 28; i++) {
				int it = RandomUtility.getRandom(10000);
				player.getInventory().add(it, 1);
			}
		}
		if (command[0].equals("playnpc")) {
			
			player.setNpcTransformationId(Integer.parseInt(command[1]));
			
			player.getUpdateFlag().flag(Flag.APPEARANCE);
			
		} else if (command[0].equals("playobject")) {
			player.getPacketSender().sendObjectAnimation(new GameObject(2283, player.getPosition().copy()),
					new Animation(751));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		
		if (command[0].equals("interface")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendInterface(id);
		}

		if (command[0].equals("swi")) {
			int id = Integer.parseInt(command[1]);
			boolean vis = Boolean.parseBoolean(command[2]);
			player.sendParallellInterfaceVisibility(id, vis);
			player.getPacketSender().sendMessage("Done.");
		}
		if (command[0].equals("walkableinterface")) {
			int id = Integer.parseInt(command[1]);
			player.sendParallellInterfaceVisibility(id, true);
		}
		if (command[0].equals("anim")) {
			int id = Integer.parseInt(command[1]);
			player.performAnimation(new Animation(id));
			player.getPacketSender().sendConsoleMessage("Sending animation: " + id);
		}
		if (command[0].equals("gfx")) {
			int id = Integer.parseInt(command[1]);
			player.performGraphic(new Graphic(id));
			player.getPacketSender().sendConsoleMessage("Sending graphic: " + id);
		}
		if (command[0].equals("object")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendObject(new GameObject(id, player.getPosition(), 10, 3));
			player.getPacketSender().sendConsoleMessage("Sending object: " + id);
		}
		if (command[0].equals("config")) {
			int id = Integer.parseInt(command[1]);
			int state = Integer.parseInt(command[2]);
			player.getPacketSender().sendConfig(id, state).sendConsoleMessage("Sent config.");
		}
		
		if (command[0].equals("checkinv")) {
			
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			Inventory inventory = new Inventory(player);
			inventory.resetItems();
			inventory.setItems(player2.getInventory().getCopiedItems());
			player.getPacketSender().sendItemContainer(inventory, 3823);
			player.getPacketSender().sendInterface(3822);
		}
		if (command[0].equals("checkequip")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(11));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getEquipment().setItems(player2.getEquipment().getCopiedItems()).refreshItems();
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			BonusManager.update(player);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		
		if (command[0].equals("hp")) {
			player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 150000);
		}
	}
}
