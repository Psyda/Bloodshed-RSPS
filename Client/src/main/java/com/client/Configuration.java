package com.client;

public class Configuration {

	/**
	 * Connection information
	 */
	/**
	 * Connection information
	 */
	public final static boolean DEVELOPMENT_SERVER = true;
	public final static String HOST = "127.0.0.1";
	public static final int CLIENT_VERSION = 15;
	public static int PORT = DEVELOPMENT_SERVER ? 43594 : 43594;

	/**
	 * Client information
	 */
	public final static String CLIENT_NAME = "Bloodshed";
	public static final boolean SEND_HASH = true;
	public static boolean upscaling = true;
	public static final boolean DISCO_ITEMS = false;

	/**
	 * Misc.
	 */
	public final static String JAGGRAB_HOST = "127.0.0.1";//local
	// public final static String JAGGRAB_HOST = "127.0.0.1";// LIVe
	public static final boolean JAGCACHED_ENABLED = false;

	/**
	 * The NPC bits. 12 = 317/377 14 = 474+ 16 = 600+
	 */
	public final static int NPC_BITS = 18;

	public static final int[] packetSizes = { 0, 0, 0, 1, 6, 0, 0, 0, 4, 0, // 0
			0, 2, -1, 1, 1, -1, 1, 0, 0, 0, // 10
			0, 0, 0, 0, 1, 0, 0, -1, 1, 1, // 20
			0, 0, 0, 0, -2, 4, 3, 0, 2, 0, // 30
			0, 0, 0, 0, 5, 8, 0, 6, 0, 0, // 40
			9, 0, 0, -2, 0, 0, 0, 0, 0, 0, // 50
			-2, 1, 0, 0, 2, -2, 0, 0, 0, 0, // 60
			6, 3, 2, 4, 2, 4, 0, 0, 0, 4, // 70
			0, -2, 0, 0, 7, 2, 1, 6, 6, 0, // 80
			2, 2, 0, 0, 0, 0, 0, 2, 0, 1, // 90
			2, 2, 0, 1, -1, 4, 1, 0, 1, 0, // 100
			1, 1, 1, 1, 2, 1, 0, 15, 0, 0, // 110
			0, 4, 4, -1, 9, 0, -2, 1, 0, 0, // 120 // 9
			-1, 0, 0, 0, 9, 0, 0, 0, 0, 0, // 130
			3, 10, 2, 0, 0, 0, 0, 14, 0, 0, // 140
			0, 4, 5, 3, 0, 0, 3, 0, 0, 0, // 150
			4, 5, 0, 0, 2, 0, 6, 0, 0, 0, // 160
			// 0, 3, /*0*/ -1, 0, 5, 7, 10, 6, 5, 1, //170
			0, 3, -2, -2, 5, 5, 10, 6, 5, -2, // 170
			0, 0, 0, 0, 0, 2, 0, -1, 0, 0, // 180
			0, 0, 0, 0, 0, 2, -1, 0, -1, 0, // 190
			4, 0, 0, 0, 0, -1, 3, 6, 4, 4, // 200
			0, 0, 0, 0, -1, 7, 0, -2, 2, 0, // 210
			0, 1, -2, -2, 0, 0, 0, 0, 0, 0, // 220
			8, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
			2, -2, 0, 0, -1, 0, 6, 0, 4, 3, // 240
			-1, 0, 0, -1, 6, 0, 0// 250

	};

	public static final double CLIENT_VER = 0.1;
	public static final String CLIENT_LOCATION = System.getProperty("user.home") + "/Desktop/" + CLIENT_NAME + ".jar";

	// TODO when on webhost
	public static final String VERSION_TXT = "http://Bloodshed.com/Client/version.txt";
	public static final String CLIENT_LINK = "http://Bloodshed.com/Client/Elysian.jar";

}
