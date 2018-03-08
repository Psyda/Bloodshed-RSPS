package com.ruseps.world.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.entity.impl.player.Player;

public class Scoreboards {

	public static Scoreboard TOP_PKERS;
	public static Scoreboard TOP_KILLSTREAKS;
	public static Scoreboard TOP_ACHIEVER;
	public static Scoreboard TOP_TOTAL_LEVEL;
	public static Scoreboard TOP_TOTAL_EXP;
	public static Scoreboard TOP_TOTAL_KILLS;
	public static Scoreboard TOP_MONEY_POUCH;
	public static Scoreboard TOP_BOSS_KILLS;

	public static abstract class Scoreboard {

		public Scoreboard(String title, String file) {
			this.title = title;
			//this.file = file;
		}

		public abstract void resort(ArrayList<ScoreboardEntry> entries);

		private String title;
		//private String file;
	}

	public static class ScoreboardEntry {

		public ScoreboardEntry(String player, String[] value) {
			this.player = player;
			this.value = value;
		}

		private String player;
		private String[] value;
	}

	public static void init() {
		TOP_PKERS = new Scoreboard("Elysian-PS' Top Pkers", "top-pkers.txt") {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "double");
			}
		};

		TOP_KILLSTREAKS = new Scoreboard("Elysian-PS' Top Killstreaks", "top-killstreaks.txt") {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "integer");
			}
		};

		TOP_ACHIEVER = new Scoreboard("Elysian-PS' Top Achiever", "top-achievers.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "integer");
			}
		};
		
		TOP_TOTAL_LEVEL = new Scoreboard("Elysian-PS' Top Total Level", "top-level.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "integer");
			}
		};

		TOP_TOTAL_EXP = new Scoreboard("Elysian-PS' Top Total Exp", "top-exp.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "long");
			}
		};
		
		TOP_TOTAL_KILLS = new Scoreboard("Elysian-PS Top Total Kills", "top-kills.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "integer");
			}
		};
		
		TOP_BOSS_KILLS = new Scoreboard("Elysian-PS Top Boss Kills", "top-bosskills.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "integer");
			}
		};
		
		TOP_MONEY_POUCH = new Scoreboard("Elysian-PS Top Money Pouch", "top-moneypouch.txt")  {
			@Override
			public void resort(ArrayList<ScoreboardEntry> entries) {
				Scoreboards.resort(entries, "long");
			}
		};
	}

	public static ArrayList<ScoreboardEntry> getUpdatedList(Scoreboard scoreboard) {
		ArrayList<ScoreboardEntry> entries = new ArrayList<ScoreboardEntry>();
		for(Player p : World.getPlayers()) {
			if(p == null)
				continue;
			if(scoreboard == Scoreboards.TOP_PKERS) {
				int kc = p.getPlayerKillingAttributes().getPlayerKills();
				int dc = p.getPlayerKillingAttributes().getPlayerDeaths();
				double kdr = (double) ((kc < 2 || dc < 2) ? (0) : (kc/dc));
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(kdr), String.valueOf(kc), String.valueOf(dc)}));
			} else if(scoreboard == Scoreboards.TOP_KILLSTREAKS) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getPlayerKillingAttributes().getPlayerKillStreak())}));
			} else if(scoreboard == Scoreboards.TOP_TOTAL_EXP) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getSkillManager().getTotalExp())}));
			} else if(scoreboard == Scoreboards.TOP_TOTAL_LEVEL) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getSkillManager().getTotalLevel())}));
			} else if(scoreboard == Scoreboards.TOP_ACHIEVER) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getPointsHandler().getAchievementPoints())}));
			} else if(scoreboard == Scoreboards.TOP_TOTAL_KILLS) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getKC())}));
			} else if(scoreboard == Scoreboards.TOP_BOSS_KILLS) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getBossKC())}));
			} else if(scoreboard == Scoreboards.TOP_MONEY_POUCH) {
				entries.add(new ScoreboardEntry(p.getUsername(), new String[]{String.valueOf(p.getMoneyInPouch())}));
			}
		}
		scoreboard.resort(entries);
		return entries;
	}

	public static void open(Player player, Scoreboard scoreboard) {
		ArrayList<ScoreboardEntry> entries = getUpdatedList(scoreboard);
		int stringId = 6402;
		for (int i = 0; i <= 50; stringId++, i++) {
			if(i == 10) {
				stringId = 8578;
			}
			ScoreboardEntry entry = i < entries.size() ? entries.get(i) : null;
			String line = "";
			if(entry != null) {
				if(scoreboard == Scoreboards.TOP_KILLSTREAKS) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Killstreak: @or1@"+Integer.parseInt(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_PKERS) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - KDR: @or1@"+Double.valueOf(entry.value[0])+" @whi@Kills: @or1@"+Integer.parseInt(entry.value[1])+"@whi@ Deaths:@or1@ "+Integer.parseInt(entry.value[2])+"";
				} else if(scoreboard == Scoreboards.TOP_TOTAL_EXP) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Total Exp: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_ACHIEVER) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Achievement Points: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_TOTAL_LEVEL) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Total Level: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_TOTAL_KILLS) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Total Kills: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_BOSS_KILLS) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Total Boss Kills: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				} else if(scoreboard == Scoreboards.TOP_MONEY_POUCH) {
					line = "@whi@Rank @or1@"+(i+1)+"@whi@ - "+entry.player.replaceAll("_", " ")+" - Money Pouch: @or1@"+Misc.insertCommasToNumber(entry.value[0]);
				}
			}
			player.getPacketSender().sendString(stringId, line);
		}
		entries.clear();
		player.getPacketSender().sendInterface(6308).sendString(6400, "Scoreboard - "+scoreboard.title+"").sendString(6399, "").sendString(6401, "Close");
	}

	private static void resort(ArrayList<ScoreboardEntry> entries, String type) {
		Collections.sort(entries, new Comparator<ScoreboardEntry>() {
			@Override
			public int compare(ScoreboardEntry player1, ScoreboardEntry player2) {
				if(type.equals("integer")) {
					int v1 = Integer.parseInt(player1.value[0]);
					int v2 = Integer.parseInt(player2.value[0]);
					if (v1 == v2) {
						return 0;
					} else if (v1 > v2) {
						return -1;
					} else {
						return 1;
					}
				} else if(type.equals("long")) {
					long v1 = Long.parseLong(player1.value[0]);
					long v2 = Long.parseLong(player2.value[0]);
					if (v1 == v2) {
						return 0;
					} else if (v1 > v2) {
						return -1;
					} else {
						return 1;
					}
				} else if(type.equals("double")) {
					double v1 = Double.parseDouble(player1.value[0]);
					double v2 = Double.parseDouble(player2.value[0]);
					if (v1 == v2) {
						return 0;
					} else if (v1 > v2) {
						return -1;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		});
	}
}
