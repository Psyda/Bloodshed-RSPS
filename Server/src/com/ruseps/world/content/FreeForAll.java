package com.ruseps.world.content;

import com.ruseps.GameSettings;
import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Flag;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.Item;
import com.ruseps.model.Locations.Location;
import com.ruseps.model.MagicSpellbook;
import com.ruseps.model.Position;
import com.ruseps.model.Prayerbook;
import com.ruseps.model.Projectile;
import com.ruseps.model.Skill;
import com.ruseps.model.container.impl.Equipment;
import com.ruseps.model.definitions.WeaponAnimations;
import com.ruseps.model.definitions.WeaponInterfaces;
import com.ruseps.world.World;
import com.ruseps.world.content.Achievements.AchievementData;
import com.ruseps.world.content.combat.magic.Autocasting;
import com.ruseps.world.content.combat.weapon.CombatSpecial;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.transportation.TeleportHandler;
import com.ruseps.world.entity.impl.player.Player;

/*
 * @author Ajw

 * -Handles a fun free for all system for events-
 */
public class FreeForAll {
	
	//Booleans
	public static boolean lobbyOpened = false;
	public static boolean portalOpened = false;
	//Strings	
	public static String gameName;
	//Ints
	public static int gameType;
	
	public static void initiateEvent(Player player) {
		DialogueManager.start(player, 287);
		player.setDialogueActionId(133);
	}

	public static void startDh(Player player) {
		gameType = 1;
		startEvent(player);
	}
	public static void startZerk(Player player) {
		gameType = 2;
		startEvent(player);
	}
	public static void startPure(Player player) {
		gameType = 3;
		startEvent(player);
	}
	public static void startF2p(Player player) {
		gameType = 4;
		startEvent(player);
	}
	public static void startDDS(Player player) {
		gameType = 5;
		startEvent(player);
	}
	public static void startRange(Player player) {
		gameType = 6;
		startEvent(player);
	}
	public static void startMonk(Player player) {
		gameType = 7;
		startEvent(player);
	}
	public static void startEvent(Player player) {
		player.getPacketSender().sendInterfaceRemoval();
		lobbyOpened = true;
		
		if(gameType == 1) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A dharok free for all event has started! Type ::ffa to join!");
			gameName = "Dharok";
		}
		if(gameType == 2) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A zerker free for all event has started! Type ::ffa to join!");
			gameName = "Zerker";
		}
		if(gameType == 3) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A pure free for all event has started! Type ::ffa to join!");
			gameName = "Pure";
		}
		if(gameType == 4) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A f2p free for all event has started! Type ::ffa to join!");
			gameName = "F2p";
		}
		if(gameType == 5) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A DDS only free for all event has started! Type ::ffa to join!");
			gameName = "DDS";
		}
		if(gameType == 6) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A range only free for all event has started! Type ::ffa to join!");
			gameName = "Range";
		}
		if(gameType == 7) {
			World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ A Monk Robe free for all event has started! Type ::ffa to join!");
			gameName = "Monkrobe";
		}
	}
	
	/*
	 * Initiate the lobby teleport
	 * Check for inventory or items
	 */
	public static void initiateLobby(Player player) {
		if(player.getSummoning().getFamiliar() != null) {
			player.getPacketSender().sendMessage("You must disable your summoning familiar to enter!");
			return;
		}
		for(Item t : player.getEquipment().getItems()) {
			if(t != null && t.getId() > 0) {
			player.getPacketSender().sendMessage("You must bank your equipment");
			return;
			}
		}
		for(Item t : player.getInventory().getItems()) {
			if(t != null && t.getId() > 0) {
			player.getPacketSender().sendMessage("You must bank your invetory");
			return;
			} 
	     }
		 
		
		TeleportHandler.cancelCurrentActions(player);
		startLobbyTeleport(player);
	}
	
	/*
	 * Checks are complete
	 * Move the player to the lobby
	 */
	public static void startLobbyTeleport(Player player) {
		Position position = new Position(2525,4776);
		player.moveTo(position);
		player.getPA().sendInterfaceRemoval();
		Autocasting.resetAutocast(player, false);
		player.getPacketSender().sendMessage("@blu@Welcome to the @red@"+gameName+ " FFA Lobby. @blu@The portal will be opened soon.");
		
		
		
		player.setSpecialPercentage(100);
		CombatSpecial.updateBar(player);
		player.getSkillManager().setCurrentLevel(Skill.PRAYER, player.getSkillManager().getMaxLevel(Skill.PRAYER), true);
		player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, player.getSkillManager().getMaxLevel(Skill.CONSTITUTION), true);
		player.getPacketSender().sendMessage("Your special attack, prayer, and health has been restored.");	
		giveGear(player);
		 
		for(int i = 0; i < 7; i++) {
			if(i == 3 || i == 5)
				continue;
			if(player.getSkillManager().getCurrentLevel(Skill.forId(i)) > 99) {
				player.getSkillManager().setCurrentLevel(Skill.forId(i), 99);
				player.setOverloadPotionTimer(0);

				
			}
		}
	}
	
	/*
	 * Handle lobby portal
	 */
	public static void handlePortal(Player player) {
		if(portalOpened == true) {
			Position gamepos = new Position(3313,9843);
			player.moveTo(gamepos);
		} else {
			player.getPacketSender().sendMessage("The portal is not opened yet! The game will start shortly...");
		}
	}
	
	/*
	 * Opens portal to allow players to access game
	 */
	public static void openPortal(Player player){
		portalOpened = true;
		World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ The ::ffa portal has opened! You have 1 minute to enter!");
		World.sendStaffMessage("<img=10> @red@[FFA EVENT-STAFF]@bla@ This is a reminder to close the ffa portal when ready! ::ffaclose");

	}
	public static void closePortal(Player player){
		portalOpened = false;
		lobbyOpened = false;
		World.sendMessage("<img=10> @red@[FFA EVENT]@bla@ The ::ffa portal has closed! Good luck to the contestors!");
		World.sendStaffMessage("<img=10> @red@[FFA EVENT-STAFF]@bla@ Portal has been closed! Game is live");

	}
	
	/*
	 * Give the gear based on the game type
	 * 
	 */
	public static void giveGear(Player player) {
		
		if(gameType == 1) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 4716 }, { Equipment.CAPE_SLOT, 19111 },
				{ Equipment.AMULET_SLOT, 6585 }, { Equipment.WEAPON_SLOT, 4151 }, { Equipment.BODY_SLOT, 4720 },
				{ Equipment.SHIELD_SLOT, 20072 }, { Equipment.LEG_SLOT, 4722 }, { Equipment.HANDS_SLOT, 7462 },
				{ Equipment.FEET_SLOT, 11732 }, { Equipment.RING_SLOT, 6737 }, { Equipment.AMMUNITION_SLOT, 9244 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		player.setSpellbook(MagicSpellbook.LUNAR);
		player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(14484, 1).add(4718,1).add(2442,1).add(2436,1).add(2440,1).add(6685,1).add(3024,2).add(9075,200).add(560,100).add(557,500).add(391,20);

		}
		if(gameType == 2) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 3751 }, { Equipment.CAPE_SLOT, 19111 },
				{ Equipment.AMULET_SLOT, 6585 }, { Equipment.WEAPON_SLOT, 4151 }, { Equipment.BODY_SLOT, 10551 },
				{ Equipment.SHIELD_SLOT, 8850 }, { Equipment.LEG_SLOT, 1079 }, { Equipment.HANDS_SLOT, 7462 },
				{ Equipment.FEET_SLOT, 4131 }, { Equipment.RING_SLOT, 6737 }, { Equipment.AMMUNITION_SLOT, 9244 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		player.setSpellbook(MagicSpellbook.LUNAR);
		player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(11694, 1).add(18353,1).add(2442,1).add(2436,1).add(2440,1).add(6685,1).add(3024,2).add(9075,200).add(560,100).add(557,500).add(391,20);

		}
		if(gameType == 3) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 1153 }, { Equipment.CAPE_SLOT, 6570 },
				{ Equipment.AMULET_SLOT, 6585 }, { Equipment.WEAPON_SLOT, 4151 }, { Equipment.BODY_SLOT, 1115 },
				{ Equipment.SHIELD_SLOT, 3842 }, { Equipment.LEG_SLOT, 2497 }, { Equipment.HANDS_SLOT, 7459 },
				{ Equipment.FEET_SLOT, 2577 }, { Equipment.RING_SLOT, 2550 }, { Equipment.AMMUNITION_SLOT, 9244 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(5680, 1).add(9185, 1).add(2442,1).add(2436,1).add(2440,1).add(6685,1).add(3024,2).add(391,20);

		}
		if(gameType == 4) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 1169 }, { Equipment.CAPE_SLOT, 6568 },
				{ Equipment.AMULET_SLOT, 1725 }, { Equipment.WEAPON_SLOT, 853 }, { Equipment.BODY_SLOT, 544 },
				 { Equipment.LEG_SLOT, 1099 }, { Equipment.HANDS_SLOT, 1065 },
				{ Equipment.FEET_SLOT, 1061 }, { Equipment.RING_SLOT, 2550 }, { Equipment.AMMUNITION_SLOT, 890 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 890 ? 200 : 1));
		}
		BonusManager.update(player);
		player.setPrayerbook(Prayerbook.NORMAL);
		player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB, player.getPrayerbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(1333, 1).add(1319, 1).add(113,1).add(2434,1).add(373,24);

		}
		if(gameType == 5) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, -1 }, { Equipment.CAPE_SLOT, -1 },
				{ Equipment.AMULET_SLOT, -1 }, { Equipment.WEAPON_SLOT, 1215 }, { Equipment.BODY_SLOT, -1 },
				{ Equipment.SHIELD_SLOT, -1 }, { Equipment.LEG_SLOT, -1 }, { Equipment.HANDS_SLOT, 7462 },
				{ Equipment.FEET_SLOT, -1 }, { Equipment.RING_SLOT, -1 }, { Equipment.AMMUNITION_SLOT, -1 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		player.setSpellbook(MagicSpellbook.LUNAR);
		player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(2440, 1).add(2436,1);

		}
		if(gameType == 6) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 3749 }, { Equipment.CAPE_SLOT, 10499 },
				{ Equipment.AMULET_SLOT, 15126 }, { Equipment.WEAPON_SLOT, 13051 }, { Equipment.BODY_SLOT, 4736 },
				{ Equipment.SHIELD_SLOT, 3842 }, { Equipment.LEG_SLOT, 4738 }, { Equipment.HANDS_SLOT, 7462 },
				{ Equipment.FEET_SLOT, 10696 }, { Equipment.RING_SLOT, 15019 }, { Equipment.AMMUNITION_SLOT, 9244 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		player.setSpellbook(MagicSpellbook.LUNAR);
		player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(11235,1).add(11212,50).add(15324,1).add(6685,1).add(3024,2).add(9075,200).add(560,100).add(557,500).add(391,20);

		}
		if(gameType == 7) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, -1 }, { Equipment.CAPE_SLOT, 6570 },
				{ Equipment.AMULET_SLOT, 6585 }, { Equipment.WEAPON_SLOT, 4151 }, { Equipment.BODY_SLOT, 544 },
				{ Equipment.SHIELD_SLOT, 20072 }, { Equipment.LEG_SLOT, 542 }, { Equipment.HANDS_SLOT, 7462 },
				{ Equipment.FEET_SLOT, 11732 }, { Equipment.RING_SLOT, 6737 }, { Equipment.AMMUNITION_SLOT, 9244 } };
		for (int i = 0; i < data.length; i++) {
			int slot = data[i][0], id = data[i][1];
			player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 100 : 1));
		}
		BonusManager.update(player);
		player.setSpellbook(MagicSpellbook.LUNAR);
		player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId());
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		player.getEquipment().refreshItems();
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		player.getInventory().resetItems();
		player.getInventory().add(1215,1).add(3024,2).add(2436,1).add(2440,1).add(6685,1).add(557,200).add(560,100).add(9075,500).add(391,20);

		}
	}
	
	
	/*
	 * Leading the arena on death
	 * Reset the items and location
	 */
	public static void leaveArena(Player player) {
		player.getEquipment().resetItems().refreshItems();
		player.getInventory().resetItems().refreshItems();
		 player.getUpdateFlag().flag(Flag.APPEARANCE);
		
		Position leave = new Position(3094,3503);
		player.moveTo(leave);
	}
	/*
	 * Leaving the arena on teleportation
	 */
	public static void leaveArenaTeleportation(Player player) {
	
		player.getEquipment().resetItems().refreshItems();
		player.getInventory().resetItems().refreshItems();
		 player.getUpdateFlag().flag(Flag.APPEARANCE);
	}
}
