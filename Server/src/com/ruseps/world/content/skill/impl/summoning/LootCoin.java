package com.ruseps.world.content.skill.impl.summoning;

import com.ruseps.model.Skill;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.world.entity.impl.player.Player;

/**
 * Ring of Wealth looting coins automatically
 * @author Arlo+
 * 
 */
public class LootCoin {

	public static final int GREEN_COIN = 995;
	public static final int GOLD_COIN = 996;
	public static final int CRIM_COIN = 997;
	public static final int BLUE_COIN = 998;

	public static void changeConfig(Player player, int index, int config) {
		player.getSummoning().setCharmImpConfig(index, config);
		player.getPacketSender().sendInterfaceRemoval().sendMessage("<img=11> <col=996633>Your configuration for "+ItemDefinition.forId(getCharmForIndex(index)).getName()+"s has been saved.");
	}

	public static boolean handleCharmDrop(Player player, int itemId, int amount) {
		int index = getIndexForCharm(itemId);
		if(index == -1) {
			return false;
		}
		switch (player.getSummoning().getCharmImpConfig(index)) {
		case 0:
			sendToInvo(player, itemId, amount);
			return true;
		case 1:
			turnIntoXp(player, itemId, amount);
			return true;
		}
		return false;
	}

	
	private static boolean sendToInvo(Player player, int itemId, int amount) {
		if (!player.getInventory().contains(itemId) && player.getInventory().getFreeSlots() == 0) {
			player.getPacketSender().sendMessage("Your inventory is full, the Ring of Wealth is unable to pick up any charms!");
			return false;
		}
		sendMessage(player, 0, itemId, amount);
		
		if (itemId == 995) {
			player.setMoneyInPouch(player.getMoneyInPouch() + (long) amount);
			player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
			String text = null;
			if (amount < 2) {
				text = "coin has";
			} else {
				text = "coins have";
			}
			player.getPacketSender().sendMessage("@red@" +amount+" " +text+" been added to your money pouch.");
		} else {
		player.getInventory().add(itemId, amount);
		}
		return true;
	}

	private static void turnIntoXp(Player player, int itemId, int amount) {
		switch (itemId) {
		case GOLD_COIN:
			//player.getInventory().add(itemId, amount);
			player.setMoneyInPouch((long) amount);
			player.getPacketSender().sendString(8135, ""+amount);
			String text = null;
			if (amount < 2) {
				text = "coin has";
			} else {
				text = "coins have";
			}
			player.getPacketSender().sendMessage("@red@" +amount+" " +text+" been added to your money pouch.");
		//	player.getSkillManager().addExperience(Skill.SUMMONING, 17544 * amount);
			break;
		case GREEN_COIN:
			player.getInventory().add(itemId, amount);
			//player.getSkillManager().addExperience(Skill.SUMMONING, 21444 * amount);
			break;
		case CRIM_COIN:
			player.getInventory().add(itemId, amount);
			//player.getSkillManager().addExperience(Skill.SUMMONING, 28877 * amount);
			break;
		case BLUE_COIN:
			player.getInventory().add(itemId, amount);
			//player.getSkillManager().addExperience(Skill.SUMMONING, 36544 * amount);
			break;
		}
		sendMessage(player, 1, itemId, amount);
	}

	private static void sendMessage(Player player, int config, int itemId, int amount) {
		String itemName = ItemDefinition.forId(itemId).getName();
		if(amount > 1) {
			itemName += "s";
		}
		switch (config) {
		case 0:
			player.getPacketSender().sendMessage("<col=550569>Ring of Wealth</col>@bla@ has found <col=b0551d>" + amount + "</col> " + itemName + ".");
			break;
		case 1:
			//player.getPacketSender().sendMessage("Your Charming imp has found <col=ff0000>" + amount + "</col> " + itemName + " and turned it into Summoning exp.");
			break;
		}
	}
	
	public static void sendConfig(Player player) {
		for(int i = 0; i < 4; i++) {
			int state = player.getSummoning().getCharmImpConfig(i);
			int charm = getCharmForIndex(i);
			switch(state) {
			case 0:
				player.getPacketSender().sendMessage("<img=10> <col=996633>Your Charming imp is placing all "+ItemDefinition.forId(charm).getName()+"s it finds in your inventory.");
				break;
			case 1:
				player.getPacketSender().sendMessage("<img=10> <col=996633>Your Charming imp is turning all "+ItemDefinition.forId(charm).getName()+"s it finds into Summoning exp.");
				break;
			}
		}
	}

	private static int getIndexForCharm(int charm) {
		switch(charm) {
		case GOLD_COIN:
			return 0;
		case GREEN_COIN:
			return 1;
		case CRIM_COIN:
			return 2;
		case BLUE_COIN:
			return 3;
		}
		return -1;
	}
	
	private static int getCharmForIndex(int index) {
		switch(index) {
		case 0:
			return GOLD_COIN;
		case 1:
			return GREEN_COIN;
		case 2:
			return CRIM_COIN;
		case 3:
			return BLUE_COIN;
		}
		return -1;
	}
}
