package com.ruseps.util;

import java.io.File;

import com.ruseps.model.PlayerRights;
import com.ruseps.model.container.impl.Bank;
import com.ruseps.model.input.impl.SetTitle;
import com.ruseps.world.World;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.world.entity.impl.player.PlayerLoading;
import com.ruseps.world.entity.impl.player.PlayerSaving;

/*
 */

public class StaffReset {

	public static void main(String[] args) {

		/*
		 * Loading character files
		 */
		for (File file : new File("data/saves/characters/").listFiles()) {
			Player player = new Player(null);
			player.setUsername(file.getName().substring(0, file.getName().length()-5));
		
			PlayerLoading.getResult(player); //comment out line 78-81 in playerloading.java for this
			
			if (player.getRights().isStaff()) {
				player.setRights(PlayerRights.PLAYER);
			
			}
			/*
			 * Save File
			 */
			PlayerSaving.save(player);
			System.out.println("Account Reset Successfully");
		}
	}
}
