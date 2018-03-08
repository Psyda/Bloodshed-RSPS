package com.ruseps.model.input.impl;

import com.ruseps.model.input.Input;
import com.ruseps.world.entity.impl.player.Player;

public class PosInputItem extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		player.getPacketSender().sendClientRightClickRemoval();
		player.getPlayerOwnedShopManager().updateFilterItem(syntax);
	}
}
