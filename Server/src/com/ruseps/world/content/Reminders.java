package com.ruseps.world.content;

import com.ruseps.util.Misc;
import com.ruseps.util.Stopwatch;
import com.ruseps.world.World;

/*
 * @author Aj
 */

public class Reminders {
	
	
    private static final int TIME = 300000; //5 minutes
	private static Stopwatch timer = new Stopwatch().reset();
	public static String currentMessage;
	
	/*
	 * Random Message Data
	 */
	private static final String[][] MESSAGE_DATA = { 
			{"<img=10>@blu@[SERVER]@bla@ Remember to ::vote for rewards every 12 hours!"},
			//{"<img=17>@blu@[SERVER]Price Guide - @red@ ::prices"},
			{"<img=10>@blu@[SERVER]@bla@ Type ::drops to open the drop table interface."},
			{"<img=10>@blu@[SERVER]@bla@ Use the ::help command to ask staff for help"},
			{"<img=10>@blu@[SERVER]@bla@ Make sure to read the forums for information @ ::forums"},
			{"<img=10>@blu@[SERVER]@bla@ Remember to spread the word and invite your friends to play!"},
			{"<img=10>@blu@[SERVER]@bla@ Use ::commands to find a list of commands"},
			{"<img=10>@blu@[SERVER]@bla@ See where you stand on the Highscores @ ::highscores"},
			{"<img=10>@blu@[SERVER]@or2@ Donate to help the server grow!"},
			{"<img=10>@blu@[SERVER]@bla@ Register and post on the forums to keep them active! ::forums"},
			{"<img=10>@blu@[SERVER]@bla@ Donators+ can use ::title to set a custom loyalty title"},
			//{"<img=10>@blu@[SERVER]@bla@ Type ::guides to check out the guides made by other players!"},
			{"<img=10>@blu@[SERVER] @bla@ Type ::gamble to visit the gambling zone!"},
		//	{"<img=17>@blu@[SERVER] Donator benefits - @red@ ::thread 27"},
		//	{"<img=17>@blu@[SERVER] Massive donation deals - @red@ ::thread 79"},
		//	{"<img=17>@blu@[SERVER] Price Guide - @red@ ::prices"},
			{"<img=10>@blu@[SERVER]@gre@ You can also donate OSRS/07 GP! Contact Ace or Conor."},

		
	};

	/*
	 * Sequence called in world.java
	 * Handles the main method
	 * Grabs random message and announces it
	 */
	public static void sequence() {
		if(timer.elapsed(TIME)) {
			timer.reset();
			{
				
			currentMessage = MESSAGE_DATA[Misc.getRandom(MESSAGE_DATA.length - 1)][0];
			World.sendMessage(currentMessage);
			
					
					
				}
				
			new Thread(() -> { World.savePlayers(); }).start();
			}
		

          }
  }
