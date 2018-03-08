package com.ruseps.model.input.impl;

import com.ruseps.model.input.Input;
import com.ruseps.world.content.PlayerPanel;
import com.ruseps.world.content.dialogue.Dialogue;
import com.ruseps.world.content.dialogue.DialogueExpression;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.dialogue.DialogueType;
import com.ruseps.world.entity.impl.player.Player;

public class YellTitle extends Input {
	
	public static final String[] DISALLOWED_WORDS = {
		"admin",
		"ad_min",
		"admin_",
		"mod",
		"owner",
		"staff",
		"img=",
		"col=",
		"pussy",
		"fuck",
		"nigger",
		"cunt",
		"shit"
	};

	@Override
	public void handleSyntax(Player player, final String syntax) {
		
		for(int i = 0; i < DISALLOWED_WORDS.length; i++) {
			if(syntax.toLowerCase().contains(DISALLOWED_WORDS[i])) {
				statement(player, "One or more words in the yell title you are trying cannot", "be used. Please, adjust your title and try it again.");
				return;
			}
		}
		
		if(syntax.length() > 16) {
			statement(player, "The maximum length for a yell title is 16 characters.");
			return;
		}
		
		player.setYellTitle("" + syntax + "");
		PlayerPanel.refreshPanel(player);
		player.getPacketSender().sendMessage("<col=FF0000>Your yell title has now been set to: \""+syntax+ "" + "\".");
		
	}
	
	private static void statement(Player player, String... sentences) {
		
		DialogueManager.start(player, new Dialogue() {

			@Override
			public DialogueType type() {
				return DialogueType.STATEMENT;
			}

			@Override
			public DialogueExpression animation() {
				return DialogueExpression.NO_EXPRESSION;
			}

			@Override
			public String[] dialogue() {
				return sentences;
			}
			
		});
		
	}
	
}
