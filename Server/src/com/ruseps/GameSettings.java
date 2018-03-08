package com.ruseps;

import java.math.BigInteger;

import com.ruseps.model.Position;
import com.ruseps.net.security.ConnectionHandler;
import com.ruseps.util.Misc;

public class GameSettings {
	
	/**
	 * 
	 */
	public static final String[] CLIENT_HASH = { "d41d8cd98f00b204e9800998ecf8427e" };
	
	/**
	 * Dzone activation
	 */
	public static boolean 
	DZONEON = false;
	
	/**
	 * The game port
	 */
	public static final int GAME_PORT = 43594; //dev world = port 43595

	/**
	 * The game version
	 */
	public static final int GAME_VERSION = 14;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGIN_THRESHOLD = 15;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGOUT_THRESHOLD = 25;
	
	/**
	 * The maximum amount of players who can receive rewards on a single game
	 * sequence.
	 */
	public static final int VOTE_REWARDING_THRESHOLD = 15;

	/**
	 * The maximum amount of connections that can be active at a time, or in
	 * other words how many clients can be logged in at once per connection.
	 * (0 is counted too)
	 */
	public static final int CONNECTION_AMOUNT = 3;

	/**
	 * The throttle interval for incoming connections accepted by the
	 * {@link ConnectionHandler}.
	 */
	public static final long CONNECTION_INTERVAL = 1000;
	
	/**
	 * The throttle interval for incoming connections accepted by the
	 * {@link ConnectionHandler}.
	 */
	public static final int MAX_INT = 2147483647;

	/**
	 * The number of seconds before a connection becomes idle.
	 */
	public static final int IDLE_TIME = 15;
	
	/**
	 * The keys used for encryption on login
	 */
	public static final BigInteger RSA_MODULUS = new BigInteger("141038977654242498796653256463581947707085475448374831324884224283104317501838296020488428503639086635001378639378416098546218003298341019473053164624088381038791532123008519201622098961063764779454144079550558844578144888226959180389428577531353862575582264379889305154355721898818709924743716570464556076517");
	public static final BigInteger RSA_EXPONENT = new BigInteger("73062137286746919055592688968652930781933135350600813639315492232042839604916461691801305334369089083392538639347196645339946918717345585106278208324882123479616835538558685007295922636282107847991405620139317939255760783182439157718323265977678194963487269741116519721120044892805050386167677836394617891073");

	/**
	 * The maximum amount of messages that can be decoded in one sequence.
	 */
	public static final int DECODE_LIMIT = 30;
	
	/** GAME **/

	/**
	 * Processing the engine
	 */
	public static final int ENGINE_PROCESSING_CYCLE_RATE = 600;
	public static final int GAME_PROCESSING_CYCLE_RATE = 600;

	/**
	 * Are the MYSQL services enabled?
	 */
	public static boolean MYSQL_ENABLED = false;

	/**
	 * Is it currently bonus xp?
	 */
	public static boolean BONUS_EXP = true;//Misc.isWeekend();
	/**
	 * 
	 * The default position
	 */
	public static final Position DEFAULT_POSITION = new Position((2717 + Misc.getRandom(2)), 5313 + Misc.getRandom(2));

	
	public static final int MAX_STARTERS_PER_IP = 2;
	
	/**
	 * Untradeable items
	 * Items which cannot be traded or staked
	 */
	public static final int[] UNTRADEABLE_ITEMS = 
		{
				11120, 13444, 19112, 19111, 6570, 18349, 18351, 18353, 18355, 18357, 18359, 18361, 18363, 8841, 8839, 8840, 8842, 
				19785, 19786, 11665, 11664, 11663, 16733, 16667, 17361, 17143, 15773, 16403, 16425, 17259, 16689, 16359, 16711, 
				16299, 16995, 16909, 10548, 10550, 10551, 13262, 14019, 14021, 14022, 20072
		};

	/**
	 * Unsellable items
	 * Items which cannot be sold to shops
	 */
	public static int UNSELLABLE_ITEMS[] = new int[] {
			11120, 13444, 19112, 19111, 6570, 10548, 10550, 10551, 13262, 14019, 14021, 14022, 20072
	};

	public static final int 
	ATTACK_TAB = 0, 
	SKILLS_TAB = 1, 
	QUESTS_TAB = 2, 
	ACHIEVEMENT_TAB = 14,
	INVENTORY_TAB = 3, 
	EQUIPMENT_TAB = 4, 
	PRAYER_TAB = 5, 
	MAGIC_TAB = 6,

	SUMMONING_TAB = 13, 
	FRIEND_TAB = 8, 
	IGNORE_TAB = 9, 
	CLAN_CHAT_TAB = 7,
	LOGOUT = 10,
	OPTIONS_TAB = 11,
	EMOTES_TAB = 12;
}
