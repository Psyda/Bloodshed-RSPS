package com.ruseps.model.input.impl;

import com.ruseps.model.Item;
import com.ruseps.model.container.impl.BeastOfBurden;
import com.ruseps.model.input.EnterAmount;
import com.ruseps.world.entity.impl.player.Player;

public class EnterAmountToStore extends EnterAmount {

	public EnterAmountToStore(int item, int slot) {
		super(item, slot);
	}

	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getSummoning().getBeastOfBurden() == null || player.getInterfaceId() != BeastOfBurden.INTERFACE_ID)
			return;
		int item = player.getInventory().getItems()[getSlot()].getId();
		if(item != getItem())
			return;
		int invAmount = player.getInventory().getAmount(item);
		if(amount > invAmount) 
			amount = invAmount;
		if(amount <= 0)
			return;
		player.getInventory().switchItem(player.getSummoning().getBeastOfBurden(), new Item(item, amount), getSlot(), false, true);
	}
}