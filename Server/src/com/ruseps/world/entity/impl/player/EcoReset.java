package com.ruseps.world.entity.impl.player;

import java.io.File;

import com.ruseps.model.Item;
import com.ruseps.model.PlayerRights;
import com.ruseps.model.container.impl.Bank;
import com.ruseps.model.input.impl.SetTitle;

/*
 */

public class EcoReset {

	public static void main(String[] args) {

		/*
		 * Loading character files
		 */
		for (File file : new File("data/saves/characters/").listFiles()) {
			Player player = new Player(null);
			player.setUsername(file.getName().substring(0, file.getName().length()-5));
		
			PlayerLoading.getResult(player); //comment out line 78-81 in playerloading.java for this
		
			
			/*
			 * Money pouch, inventory, equipment, and dung bound items
			 */
			//player.getBlowpipeLoading().handleUnloadBlowpipe();
			//player.getMagmaBlowpipeLoading().handleUnloadBlowpipe();
			//player.setMoneyInPouch(0);
			//player.getInventory().resetItems();
			//player.getEquipment().resetItems();
			//player.getMinigameAttributes().getDungeoneeringAttributes().setBoundItems(new int[5]);
			
			//player.getPlayerOwnedShopManager().setEarnings(0);
			
			//player.getPointsHandler().setDonationPoints(0, false);
			//player.setRights(PlayerRights.PLAYER);
			//int test = player.getAmountDonated() - (player.getAmountDonated() *2);
			//player.incrementAmountDonated(test);
			/*
			 * Reset Bank
			 */
		for (Bank bank : player.getBanks()) {
				/**if (bank == null) {
					return;
				}**/
			
			

				//bank.resetItems();
			}
		

		//Bank bank = player.getBank();
		//bank.resetItems();
			
			/*
			 * Clear pack yack / beast of burden
			 */
			//if (player.getSummoning().getBeastOfBurden() != null) {
			//	player.getSummoning().getBeastOfBurden().resetItems();
			//}
			

			//player.getPointsHandler().setDungeoneeringTokens(0, true);
			//player.getPointsHandler().setCommendations(0, true);
			//player.getPointsHandler().setPrestigePoints(0, true);
			//player.getPointsHandler().setTriviaPoints(0, true);
			//player.getPointsHandler().setSlayerPoints(0, true);
			//player.getPointsHandler().setVotingPoints(0, true);
			
			/*
			 * Save File
			 */
			PlayerSaving.save(player);
			System.out.println("Account Reset Successfully");
		}
	}
}
