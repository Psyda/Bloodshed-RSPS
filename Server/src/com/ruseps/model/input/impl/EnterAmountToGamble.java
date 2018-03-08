package com.ruseps.model.input.impl;

import com.ruseps.model.input.EnterAmount;
import com.ruseps.world.entity.impl.player.Player;

public class EnterAmountToGamble extends EnterAmount {

	public EnterAmountToGamble(int item, int slot) {
		super(item, slot);
	}
	
	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getGamble().inGamble() && getItem() > 0 && getSlot() >= 0 && getSlot() < 28)
			player.getGamble().gambleItem(getItem(), amount, getSlot());
		else
			player.getPacketSender().sendInterfaceRemoval();
	}

}
