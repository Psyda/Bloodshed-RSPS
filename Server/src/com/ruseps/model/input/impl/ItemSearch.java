package com.ruseps.model.input.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.model.definitions.NPCDrops;
import com.ruseps.model.definitions.NPCDrops.NpcDropItem;
import com.ruseps.model.definitions.NpcDefinition;
import com.ruseps.model.input.Input;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.dialogue.impl.ExplorerJack;
import com.ruseps.world.content.dropchecker.NPCDropTableChecker;
import com.ruseps.world.entity.impl.player.Player;

public class ItemSearch extends Input {
	
	public final static List<String> dropTableNpcNames = new ArrayList<String>();

	public final static List<Integer> npcIds = new ArrayList<Integer>();

	private String getNPCName(int npcId) {
		NpcDefinition def = NpcDefinition.forId(npcId);
		if(def == null) {
			return "null";
		}
		return def.getName();
	}
	@Override
	public void handleSyntax(Player player, final String syntax) {
		player.getMovementQueue().setLockMovement(true);
		TaskManager.submit(new Task(1, player, false) {
			@Override
			protected void execute() {
				try {
					String syntaxCopy = syntax;
					Object[] data = getFixedSyntax(syntaxCopy);
					syntaxCopy = (String) data[0];

					if (syntaxCopy.length() <= 3) {
						player.getPA().sendMessage("@red@You must search the full items name");
						stop();
						return;
					}

					int itemId = (int) data[1];
					int npcDropId = -1;

					for (ItemDefinition def : ItemDefinition.getDefinitions()) {
						if (def != null) {
							if (def.getName().contains(syntaxCopy) && itemId == -1) {
								itemId = def.getId();
							}
							if (def.getName().equalsIgnoreCase(syntaxCopy)) {
								itemId = def.getId();
								break;
							}
						}
					}

					if (itemId > 0 && ItemDefinition.forId(itemId).isNoted()) {
						itemId--;
					}

					if (itemId == 14486) {
						itemId = 14484;
					}

					if (itemId > 0) {
						for (NPCDrops npcDrops : NPCDrops.getDrops().values()) {
							if (npcDrops != null) {
								for (NpcDropItem item : npcDrops.getDropList()) {
									if (item != null && item.getId() == itemId) {
										for (int npcId : npcDrops.getNpcIds()) {
											if (npcId == -1)
												continue;
											if (NpcDefinition.forId(npcId) != null
													&& !NpcDefinition.forId(npcId).getName().equalsIgnoreCase("null")) {
												npcDropId = npcId;
												npcIds.add(npcId);
												break;
											}
										}
									}
								}
							}
						}
					}

					if (itemId == -1 || npcDropId == -1) {
						player.getPA().sendMessage("@red@You must search the full items name");

					//	player.getPA().sendMessage("No thanks");
					} else {
						
						player.setsearchedNpc(true);
						//player.getPacketSender().sendMessage(npcIds.toString());
						//player.getPacketSender().sendMessage(npcIds);

						//player.getPacketSender().sendMessage(ItemDefinition.forId(itemId).getName());
						//player.getPacketSender().sendMessage(NpcDefinition.forId(npcDropId).getName());
						
						Collections.sort(npcIds, (Integer s1, Integer s2) -> getNPCName(s1).compareTo(getNPCName(s2)));
						for (int npcId : npcIds) {
							dropTableNpcNames.add(getNPCName(npcId));
						}
						
						//player.getPacketSender().sendMessage(dropTableNpcNames.toString());

							int childId = 37651;
							for (String npcName : dropTableNpcNames) {
								player.getPacketSender().sendFrame126(npcName, childId++);
							}
							for (int child = childId; child < 37821; child++) {
								player.getPacketSender().sendFrame126("", child);
							}
						
					//	NPCDropTableChecker.showNPCDropTable(player, npcDropId);
					}
				} catch (Exception e) {
				}

				stop();
			}

			@Override
			public void stop() {
				setEventRunning(false);
				player.getMovementQueue().setLockMovement(false);
			}
		});
	}

	public static Object[] getFixedSyntax(String searchSyntax) {
		searchSyntax = searchSyntax.toLowerCase();
		switch (searchSyntax) {
		case "ags":
			return new Object[] { "armadyl godsword", 11694 };
		case "sgs":
			return new Object[] { "saradomin godsword", 11698 };
		case "bgs":
			return new Object[] { "bandos godsword", 11696 };
		case "zgs":
			return new Object[] { "zamorak godsword", 11700 };
		case "dclaws":
		case "d claws":
			return new Object[] { "dragon claws", 14484 };
		case "bcp":
			return new Object[] { "bandos chestplate", 11724 };
		case "dds":
			return new Object[] { "dragon dagger", 1215 };
		case "sol":
			return new Object[] { "staff of light", 15486 };
		case "vls":
			return new Object[] { "vesta's longsword", 13899 };
		case "tassy":
			return new Object[] { "bandos tassets", 11726 };
		case "swh":
			return new Object[] { "statius's warhammer", 13902 };
		case "steads":
			return new Object[] { "steadfast boots", 20000 };
		case "obby maul":
			return new Object[] { "Tthaar-ket-om", 6528 };
		case "g maul":
		case "gmaul":
			return new Object[] { "granite maul", 4153 };
		case "nat":
			return new Object[] { "nature rune", 561 };
		case "ely":
			return new Object[] { "elysian spirit shield", 13742 };
		case "dfs":
			return new Object[] { "dragonfire shield", 11283 };
		case "dkite":
			return new Object[] { "dragon kiteshield", 11613 };
		case "dbones":
			return new Object[] { "dragon bones", 536 };
		case "fury":
			return new Object[] { "amulet of fury", 6585 };
		case "dboots":
		case "d boots":
			return new Object[] { "dragon boots", 11732 };
		case "whip":
		case "abby whip":
		case "abbysal whip":
		case "abbyssal whip":
			return new Object[] { "abyssal whip", 4151 };
		case "abbyssal vine whip":
			return new Object[] { "abyssal vine whip", 4705 };
		case "vine whip":
			return new Object[] { "vine whip", 4705 };
		}
		return new Object[] { searchSyntax, -1 };
	}
}
