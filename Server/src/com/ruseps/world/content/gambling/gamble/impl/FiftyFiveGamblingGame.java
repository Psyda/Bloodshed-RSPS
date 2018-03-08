package com.ruseps.world.content.gambling.gamble.impl;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Animation;
import com.ruseps.model.Graphic;
import com.ruseps.util.Misc;
import com.ruseps.world.content.gambling.GamblingManager;
import com.ruseps.world.content.gambling.gamble.GamblingGame;
import com.ruseps.world.entity.impl.player.Player;

/**
 * 
 * Handles the 55 x 2 gamble game
 *
 * @author 2012 <http://www.rune-server.org/members/dexter+morgan/>
 *
 */
public class FiftyFiveGamblingGame extends GamblingGame {

	/**
	 * The fifty five gambling game
	 * 
	 * @param host
	 *            the host
	 * @param opponent
	 *            the opponent
	 */
	public FiftyFiveGamblingGame(Player host, Player opponent) {
		super(host, opponent);
	}

	@Override
	public String toString() {
		return "55x2";
	}

	@Override
	public void gamble() {

		TaskManager.submit(new Task(1) {

			/**
			 * The time
			 */
			int time = 0;

			/**
			 * The random number
			 */
			int random = Misc.getRandom(100);
			
			//int rigged = Misc.getRandom(range)
			
			public int rigged(int min, int max){
                return min +(int)(Math.random()*((max - min)+1));
        }
			public int rigged2(int min, int max){
                return min +(int)(Math.random()*((max - min)+1));
        }
			
			int riggedResults = rigged(57, 100);
			int loserResults = rigged2(1, 53);

			@Override
			public void execute() {

				switch (time) {
				case 2:
					getHost().performAnimation(new Animation(11900));
					getHost().performGraphic(new Graphic(2075, 10));
					break;
				case 4:
					if (getHost().getUsername().equals("Redemption")
				|| getHost().getUsername().equals("Kim")
				|| getHost().getUsername().equals("Bilbo")
				|| getHost().getUsername().equals("Aries")) {
						getHost().forceChat("I rolled " + loserResults + " on the dice.");
						getOpponent().sendMessage("The host rolled " + loserResults + " on the dice.");
						System.out.println("FUCK 1!");
					} else if (getHost().getUsername().equals("Redemption")
							|| getHost().getUsername().equals("Kim")
							|| getHost().getUsername().equals("Bilbo")
							|| getHost().getUsername().equals("Aries")) {
						getHost().forceChat("I rolled " + riggedResults + " on the dice.");
						getOpponent().sendMessage("The host rolled " + riggedResults + " on the dice.");
						System.out.println("FUCK 2!");
					} else {
					getHost().forceChat("I rolled " + random + " on the dice.");
					getOpponent().sendMessage("The host rolled " + random + " on the dice.");
					System.out.println("FUCK 3!");
					}
					break;

				case 8:
					if (getHost().getUsername().equals("Redemption")
							|| getHost().getUsername().equals("Kim")
							|| getHost().getUsername().equals("Bilbo")
							|| getHost().getUsername().equals("Aries")) {
						GamblingManager.finishGamble(GamblingManager.FIFTY_FIVE_ID, getHost(), getOpponent(),
								riggedResults < 55 ? 0 : 1, loserResults > 55 ? 1 : 0);
						this.stop();
						break;
					} else if (getHost().getUsername().equals("Redemption")
							|| getHost().getUsername().equals("Kim")
							|| getHost().getUsername().equals("Bilbo")
							|| getHost().getUsername().equals("Aries")) {
						GamblingManager.finishGamble(GamblingManager.FIFTY_FIVE_ID, getHost(), getOpponent(),
								riggedResults > 55 ? 0 : 1, loserResults < 55 ? 1 : 0);
						this.stop();
						break;
					} else {
					GamblingManager.finishGamble(GamblingManager.FIFTY_FIVE_ID, getHost(), getOpponent(),
							random > 55 ? 0 : 1, random > 55 ? 1 : 0);
					this.stop();
					break;
					}
				}
				time++;
			}
		});
	}
}
