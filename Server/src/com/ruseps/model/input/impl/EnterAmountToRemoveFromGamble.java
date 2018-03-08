package com.ruseps.model.input.impl;

import com.ruseps.model.input.EnterAmount;
import com.ruseps.world.entity.impl.player.Player;

public class EnterAmountToRemoveFromGamble extends EnterAmount {

	public EnterAmountToRemoveFromGamble(int item) {
		super(item);
	}
	
	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getGamble().inGamble() && getItem() > 0) 
			player.getGamble().removeGambledItem(getItem(), amount);
		else
			player.getPacketSender().sendInterfaceRemoval();
	}
	
	
}
