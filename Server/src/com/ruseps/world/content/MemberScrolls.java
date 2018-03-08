package com.ruseps.world.content;

import com.ruseps.model.GameMode;
import com.ruseps.model.PlayerRights;
import com.ruseps.util.Misc;
import com.ruseps.world.content.dialogue.Dialogue;
import com.ruseps.world.content.dialogue.DialogueExpression;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.dialogue.DialogueType;
import com.ruseps.world.entity.impl.player.Player;

public class MemberScrolls {
	
	public static void checkForRankUpdate(Player player) {
		if(player.getRights().isStaff()) {
			return;
		}
		if(player.getGameMode() == GameMode.IRONMAN) {
			player.getPacketSender().sendMessage("@red@You did not recieve donator rank because you are an iron man!");
			return;
		}
		if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			player.getPacketSender().sendMessage("You did not recieve donator rank because you are an iron man!");
			return;
		}
		PlayerRights rights = null;
		if(player.getAmountDonated() >= 20)
			rights = PlayerRights.BRONZE_MEMBER;
		if(player.getAmountDonated() >= 50)
			rights = PlayerRights.SILVER_MEMBER;
		if(player.getAmountDonated() >= 50)
			rights = PlayerRights.GOLD_MEMBER;
		if(player.getAmountDonated() >= 250)
			rights = PlayerRights.PLATINUM_MEMBER;
		if(player.getAmountDonated() >= 350)
			rights = PlayerRights.DIAMOND_MEMBER;
		if(player.getAmountDonated() >= 700)
			rights = PlayerRights.RUBY_MEMBER;
		if(rights != null && rights != player.getRights()) {
			player.getPacketSender().sendMessage("You've become a "+Misc.formatText(rights.toString().toLowerCase())+"! Congratulations!");
			player.setRights(rights);
			player.getPacketSender().sendRights();
		}
	}//u need any more help while im on? where do i check if my donator benfits are working, like drop rate

	public static void giveWarning(Player player) {
		DialogueManager.start(player, 391);
		player.setDialogueActionId(391);
	}
	

	
	public static void handleScrollClaim(Player player) {
		player.getPacketSender().sendInterfaceRemoval();
			if(player.getScrollAmount() == 1) {
				player.getInventory().delete(10942, 1);
				player.incrementAmountDonated(10);
				player.getPacketSender().sendMessage("Your account has gained funds worth $10. Your total is now at $"+player.getAmountDonated()+".");
				checkForRankUpdate(player);
				PlayerPanel.refreshPanel(player);
			}
			if(player.getScrollAmount() == 2) {
				player.getInventory().delete(10934, 1);
				player.incrementAmountDonated(20);
				player.getPacketSender().sendMessage("Your account has gained funds worth $20. Your total is now at $"+player.getAmountDonated()+".");
				checkForRankUpdate(player);
				PlayerPanel.refreshPanel(player);	
			}
			if(player.getScrollAmount() == 3) {
				player.getInventory().delete(10935, 1);
				player.incrementAmountDonated(50);
				player.getPacketSender().sendMessage("Your account has gained funds worth $50. Your total is now at $"+player.getAmountDonated()+".");
				checkForRankUpdate(player);
				PlayerPanel.refreshPanel(player);
			}
			if(player.getScrollAmount() == 4) {
				player.getInventory().delete(10943, 1);
				player.incrementAmountDonated(100);
				player.getPacketSender().sendMessage("Your account has gained funds worth $100. Your total is now at $"+player.getAmountDonated()+".");
				checkForRankUpdate(player);
				PlayerPanel.refreshPanel(player);
			}
				
	}
	
	public static Dialogue getTotalFunds(final Player player) {
		return new Dialogue() {

			@Override
			public DialogueType type() {
				return DialogueType.NPC_STATEMENT;
			}

			@Override
			public DialogueExpression animation() {
				return DialogueExpression.NORMAL;
			}
			
			@Override
			public int npcId() {
				return 4657;
			}

			@Override
			public String[] dialogue() {
				return player.getAmountDonated() > 0 ? new String[]{"Your account has claimed scrolls worth $"+player.getAmountDonated()+" in total.", "Thank you for supporting us!"} : new String[]{"Your account has claimed scrolls worth $"+player.getAmountDonated()+" in total."};
			}
			
			@Override
			public Dialogue nextDialogue() {
				return DialogueManager.getDialogues().get(5);
			}
		};
	}
}
