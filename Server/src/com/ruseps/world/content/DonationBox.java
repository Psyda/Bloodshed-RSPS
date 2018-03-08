package com.ruseps.world.content;

import com.ruseps.util.Misc;
import com.ruseps.util.RandomUtility;
import com.ruseps.world.entity.impl.player.Player;

public class DonationBox {
	
	/*
	 * Rewards
	 */
	public static final int [] shitRewards = {4151, 6585, 11133, 15126, 10828, 3751, 3753, 10589, 10564, 6809, 4587, 2581, 2577, 2577, 2577, 2581, 2581,
			18782, 18782, 18782, 1249, 3204, 1305, 1377, 1434, 6528, 7158, 4153, 6, 8, 10,
			6739, 15259, 15332, 2579, 6920, 6922, 15241, 11882, 13867, 6570, 11884, 11906, 11283, 20084,
			12, 4675, 6914, 6889, 19747, 2572, 6199};
	public static final int [] goodRewards = {
			
			20072,//dragon defender			
			15486, //weapon mage
			19336, 19337, 19338, 19339, 19340, //dragon (or) 			
			14014, 14015, 14016, 14009, 14010, 14008, 14011, 14012, 14013, 19476, 19478, 19477, //Nex
			6570, 19111, //capes
			15018, 15019, 15020, 15220,//rings			  
			2568,  //ring of wealthier
			13263, 11613, 11718, 11720, 11722, 11724, 11726, 11283, //Armour			
			1050, //santa
			11235, 13051, //weapons range
			10551, //torso 
			19780, 14484, 11730, 13905, 13902, 13899, 11694, 11696, 11698, 11700, 4151, //weapons melee
			18349, 18363, 18353, 18355, 18357, 18359, //chatoics + dung stuff
			2577, 20000, 20001, 20002, 13239, 13235, 12708}; // boots
	
	/*
	 * Handles the opening of the donation box
	 */
	public static void open (Player player) {
		if (player.getInventory().getFreeSlots() < 3) {
			player.getPacketSender().sendMessage("You need at least 3 inventory spaces");
			return;
		}
			//fk
		player.getInventory().delete(6183, 1);
		giveReward(player);
		player.getPacketSender().sendMessage("Congratulations on your reward!");
		
	}
	
	/*
	 * Gives the reward base on misc Random chance
	 */
	public static void giveReward(Player player) {
		/*
		 * 1/3 Chance for a good reward
		 */
		if (RandomUtility.RANDOM.nextInt(3) == 2) {
			player.getInventory().add(goodRewards[Misc.getRandom(goodRewards.length - 1)], 1);
		} else {
			player.getInventory().add(shitRewards[Misc.getRandom(shitRewards.length - 1)], 1);

		}
		/*
		 * Adds 5m + a random amount up to 100m every box
		 * Max cash reward = 105m
		 */
		player.getInventory().add(995, 40000000 + RandomUtility.RANDOM.nextInt(40000000));
	}

}
