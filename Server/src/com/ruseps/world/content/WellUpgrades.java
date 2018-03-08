package com.ruseps.world.content;

import com.ruseps.model.Animation;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.entity.impl.player.Player;

public class WellUpgrades {

	public static void handleWellUpgrades(Player player, int itemId) {
		int newItem;
		boolean success = false;

		if(itemId == 1038 || itemId == 1040 || itemId == 1042 || itemId == 1044 || itemId == 1046 || itemId == 1048 || itemId == 14048 || itemId == 14045 || itemId == 14046 || itemId == 14044) {

			newItem = 5572;
			boolean allHats = player.getInventory().containsAll(1038, 1040, 1042, 1044, 1046, 1048, 14048, 14045, 14046, 14044);
			if(allHats) {
				success = true;
				player.getInventory()
						.delete(1038, 1)
						.delete(1040, 1)
						.delete(1042, 1)
						.delete(1044, 1)
						.delete(1046, 1)
						.delete(1048, 1)
						.delete(14048, 1)
						.delete(14045, 1)
						.delete(14046, 1)
						.delete(14044, 1)
						.add(newItem, 1);
			} else {
				player.getPacketSender().sendMessage("You are missing party hats to upgrade this.");
				return;
			}
		}
		if(itemId == 12282) {
			if(Misc.getRandom(10) > 5) {
				newItem = 12279;
			} else {
				newItem = 12278;
			}
			player.getInventory().delete(12282, 1);
			if(Misc.getRandom(10) == 5) {
				player.getInventory().add(newItem, 1);
				player.getPacketSender().sendMessage("You upgraded your serpentine helm into a " + ItemDefinition.forId(newItem).getName() + "!");
			} else {
				player.getPacketSender().sendMessage("You were not lucky this time.");
			}
		}
		if(itemId == 12926) {
			newItem = 12927; //Magma blowpipe id
			if(Misc.getRandom(10) == 5) { //1/10 chance
				//success = true;
				player.performAnimation(new Animation(2400));
				player.getInventory()
						.delete(12926, 1)
						.add(newItem, 1)
						.refreshItems();
			} else {
				player.getPacketSender().sendMessage("You were not lucky this time.");
				player.getInventory()
						.delete(12926, 1)
						.refreshItems();
				return;
			}
		}
		if(!success) {
			sendGlobalNotification(player, itemId);
		}
		if(itemId == 2572) {//row
			newItem = 2568; // wealthier
			if(Misc.getRandom(30) == 15) { //1/30 chance
				//success = true;
				player.getInventory().delete(2572, 1).add(newItem, 1);
				World.sendMessage(
						"<img=10> <col=008FB2>" + player.getUsername() + " Has just upgraded a Ring of wealth into a  RING OF WEALTHIER!");
			} else {
				player.getPacketSender().sendMessage("You were not lucky this time.");
				player.getInventory().delete(2572, 1);
			}

		}
	}

	private static void sendGlobalNotification(Player player, int itemId) {
		switch(itemId) {
			case 12926:
				World.sendMessage("<img=10> <col=008FB2>" + player.getUsername() + " Has just upgraded a Blowpipe into a MAGMA BLOWPIPE!");
				break;
			case 1038: case 1040: case 1042: case 1044: case 1046: case 1048: case 14048: case 14045: case 14046: case 14044:
				World.sendMessage("<img=10> <col=008FB2>" + player.getUsername() + " Has just upgraded all partyhats into a RAINBOW PARTYHAT!");
				break;
		}
	}
}
