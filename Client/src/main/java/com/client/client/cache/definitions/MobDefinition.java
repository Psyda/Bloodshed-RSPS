package com.client.client.cache.definitions;

import com.client.client.*;

public final class MobDefinition {

	public int frontLight = 68;
	public int backLight = 820;
	public int rightLight = 0;
	public int middleLight = -1; // Cannot be 0
	public int leftLight = 0;

	public static MobDefinition forID(int i) {
		for (int j = 0; j < 20; j++)
			if (cache[j].type == (long) i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 20;
		MobDefinition npc = cache[cacheIndex] = new MobDefinition();
		if (i >= streamIndices.length)
			return null;
		stream.currentOffset = streamIndices[i];
		npc.type = i;
		npc.readValues(stream);
		if (npc.name != null && npc.name.toLowerCase().contains("bank")) {
			if (npc.options != null) {
				for (int l = 0; l < npc.options.length; l++) {
					if (npc.options[l] != null && npc.options[l].equalsIgnoreCase("Collect"))
						npc.options[l] = null;
				}
			}
		}
		npc.id = i;
		switch (i) {
		case 611:
			npc.options = new String[] { "Exchange", null, null, null, null };
			break;
			case 2962:
				npc.models = new int[] {83542};
				npc.name = "Ganodermic beast";
				npc.tileSpacesOccupied = 3;
				npc.stanceAnimation = 15464;
				npc.walkAnim = 15465;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 280;
				break;

			case 2963:
				npc.models = new int[] {83888};
				npc.name = "Ganodermic beast";
				npc.tileSpacesOccupied = 3;
				npc.stanceAnimation = 15464;
				npc.walkAnim = 15465;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 280;
				break;

			case 2964:
				npc.models = new int[] {10523, 10490, 10486, 6700,62575, ItemDefinition.forID(13740).maleModel0, ItemDefinition.forID(9921).maleModel0, ItemDefinition.forID(9922).maleModel0};
				npc.name = "Ganodermic Minion";
				npc.stanceAnimation = MobDefinition.forID(2).stanceAnimation;
				npc.walkAnim = MobDefinition.forID(2).walkAnim;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.sizeY = 120;
				npc.sizeXZ = 120;
				npc.combatLevel = 140;
				break;

			case 2965:
				npc.models = new int[] {10523, 10490, 10486, 1052,62575, ItemDefinition.forID(13740).maleModel0, ItemDefinition.forID(9921).maleModel0, ItemDefinition.forID(9922).maleModel0};
				npc.name = "Ganodermic Minion";
				npc.stanceAnimation = MobDefinition.forID(2).stanceAnimation;
				npc.walkAnim = MobDefinition.forID(2).walkAnim;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.sizeY = 110;
				npc.sizeXZ = 110;
				npc.combatLevel = 140;
				break;
			case 2966:
				npc.models = new int[] {83542};
				npc.name = "Ganodermic beast";
				npc.tileSpacesOccupied = 3;
				npc.stanceAnimation = 15464;
				npc.walkAnim = 15465;
				npc.sizeXZ *= 1.2;
				npc.sizeY *= 1;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 280;
				break;
			case 2967:
				npc.models = new int[] {83542};
				npc.name = "Ganodermic beast";
				npc.tileSpacesOccupied = 3;
				npc.stanceAnimation = 15464;
				npc.walkAnim = 15465;
				npc.sizeY *= 1.4;
				npc.sizeXZ *= 1.4;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 280;
				break;
			case 2968:
				npc.models = new int[] {83542};
				npc.name = "Ganodermic beast";
				npc.tileSpacesOccupied = 3;
				npc.stanceAnimation = 15464;
				npc.walkAnim = 15465;
				npc.sizeY *= 1.6;
				npc.sizeXZ *= 1.4;
				npc.options = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 280;
				break;
		// INFERNO
		case 7700:
			npc.models = new int[] { 33012 };
			npc.name = "JalTok-Jad";
			npc.stanceAnimation = 7589;
			npc.walkAnim = 7588;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 900;
			npc.tileSpacesOccupied = 5;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			break;
		case 5581:
			npc.options = new String[] { null, "Attack", null, null, null };
			break;

		case 7702:
			npc.models = new int[] { 33014 };
			npc.name = "Jal-Xil";
			npc.stanceAnimation = 7602;
			npc.walkAnim = 7603;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 370;
			npc.tileSpacesOccupied = 3;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			break;

		case 7703:
			npc.models = new int[] { 33000 };
			npc.name = "Jal-Zek";
			npc.stanceAnimation = 7609;
			npc.walkAnim = 7608;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 490;
			npc.tileSpacesOccupied = 4;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			break;

		case 7750:
			npc.models = new int[] { 33099 };
			npc.name = "Jal-MejJak";
			npc.stanceAnimation = 2867;
			npc.walkAnim = 2863;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 250;
			npc.tileSpacesOccupied = 1;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			break;

		case 7706:
			npc.models = new int[] { 33011 };
			npc.name = "TzKal-Zuk";
			npc.stanceAnimation = 7564;
			npc.walkAnim = 7564;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 1400;
			npc.tileSpacesOccupied = 7;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			break;
		case 7893:
			npc.models = new int[] { 33036 };
			npc.name = "@cya@Ancestral Glyph";
			npc.stanceAnimation = 7567;
			npc.walkAnim = 7567;
			npc.options = new String[] { null, null, null, null, null };
			npc.destColours = null;
			npc.originalColours = null;
			npc.combatLevel = 0;
			npc.tileSpacesOccupied = 3;
			npc.sizeXZ = 128;
			npc.sizeY = 128;
			npc.drawMinimapDot = false;
			break;

		// INFERNO END

		case 132:
			npc.name = "Blitz";
			npc.description = "A master attacker of Bloodshed.";
			npc.combatLevel = 913;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[9];
			npc.models[0] = 14395; // Hat
			npc.models[1] = 62746; // Platebody
			npc.models[2] = 62743; // Platelegs
			npc.models[3] = 62582; // Cape
			npc.models[4] = 13307; // Gloves
			npc.models[5] = 53327; // Boots
			npc.models[6] = 9642; // Amulet
			npc.models[7] = 2295; // Weapon
			npc.models[8] = 26423; // Shield
			npc.stanceAnimation = 808;
			npc.walkAnim = 819;
			npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			npc.tileSpacesOccupied = 2;
			break;
		case 133:
			npc.name = "Cobra";
			npc.description = "A master mager of Bloodshed.";
			npc.combatLevel = 903;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[10];
			npc.models[0] = 3188; // Hat
			npc.models[1] = 58366; // Platebody
			npc.models[2] = 58333; // Platelegs
			npc.models[3] = 65297; // Cape
			npc.models[4] = 179; // Gloves
			npc.models[5] = 27738; // Boots
			npc.models[6] = 9642; // Amulet
			npc.models[7] = 56022; // Weapon
			npc.models[8] = 40942; // Shield
			npc.models[9] = 58316;
			npc.stanceAnimation = 808;
			npc.walkAnim = 819;
			npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			npc.tileSpacesOccupied = 2;
			npc.destColours = new int[] { 226770, 34503, 34503, 34503, 34503 };
			npc.originalColours = new int[] { 926, 65214, 65200, 65186, 62995 };
			break;
		case 135:
			npc.name = "Fear";
			npc.description = "A master ranger of Bloodshed.";
			npc.combatLevel = 844;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[9];
			npc.models[0] = 26632; // Hat
			npc.models[1] = 20157; // Platebody
			npc.models[2] = 20139; // Platelegs
			npc.models[3] = 65297; // Cape
			npc.models[4] = 20129; // Gloves
			npc.models[5] = 27738; // Boots
			npc.models[6] = 9642; // Amulet
			npc.models[7] = 58380; // Weapon
			npc.models[8] = 20121;
			npc.stanceAnimation = 808;
			npc.walkAnim = 819;
			npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			npc.destColours = ItemDefinition.forID(10372).newModelColor;
			npc.originalColours = ItemDefinition.forID(10372).editedModelColor;
			npc.tileSpacesOccupied = 2;
			break;
		case 1472:
			npc.name = "Death";
			npc.description = "A master Attacker of Bloodshed.";
			npc.combatLevel = 941;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[9];
			npc.models[0] = 55770; // Hat
			npc.models[1] = 55851; // Platebody
			npc.models[2] = 55815; // Platelegs
			npc.models[3] = 65297; // Cape
			npc.models[4] = 55728; // Gloves
			npc.models[5] = 55673; // Boots
			npc.models[6] = 9642; // Amulet
			npc.models[7] = 56046; // Weapon
			npc.models[8] = 38941; // Shield
			npc.stanceAnimation = 808;
			npc.walkAnim = 819;
			npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			npc.tileSpacesOccupied = 2;
			npc.destColours = new int[] { 127, 127, 127, 127 };
			npc.originalColours = new int[] { 65214, 65200, 65186, 62995 };
			break;

		case 3334:
			npc.name = "WildyWyrm";
			npc.models = new int[] { 63604 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.options = new String[5];
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.sizeXZ = 225;
			npc.sizeY = 200;
			// npc.sizeXZ = 35;
			// npc.sizeY = 75;
			break;
		case 1:
			npc.name = "Poison";
			npc.options = new String[] { null, null, null, null, null };
			npc.sizeXZ = 1;
			npc.sizeY = 1;
			npc.drawMinimapDot = false;
			break;
		case 0:
			npc.name = " ";
			npc.options = new String[] { null, null, null, null, null };
			npc.sizeXZ = 1;
			npc.sizeY = 1;
			npc.drawMinimapDot = false;
			break;
		case 6723:
			npc.name = "Rock Golem";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29755;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7181;
			npc.stanceAnimation = 7180;
			npc.description = "Its a Rock Golem.";
			npc.tileSpacesOccupied = 1;
			npc.sizeXZ = npc.sizeY = 110;
			break;
		case 6724:
			npc.name = "Heron";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29756;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 6774;
			npc.stanceAnimation = 6772;
			npc.description = "Its a Heron.";
			npc.tileSpacesOccupied = 1;
			break;

		case 568:
			npc.name = "Note Trader";
			npc.options = new String[] { "Trade", null, null, null, null };
			break;

		case 6726:
			npc.name = "Beaver";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29754;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7178;
			npc.stanceAnimation = 7177;
			npc.description = "Its a Beaver.";
			npc.tileSpacesOccupied = 1;
			break;

		case 6640:
			npc.name = "Kraken";
			npc.models = new int[] { 28231 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 3989;
			npc.walkAnim = 3989;
			npc.sizeXZ = 25;
			npc.sizeY = 25;
			npc.options = new String[5];
			npc.drawMinimapDot = false;
			npc.options[0] = "Pick-up";
			npc.combatLevel = 0;
			npc.tileSpacesOccupied = 1;
			break;

		case 963:
			npc.name = "Hellpupy";
			npc.models = new int[] { 29240 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 6561;
			npc.walkAnim = 6560;
			npc.originalColours = new int[] { 29270 };
			npc.options = new String[5];
			npc.drawMinimapDot = false;
			npc.options[0] = "Pick-up";
			npc.combatLevel = 0;
			npc.tileSpacesOccupied = 1;
			break;

		case 5781:
			npc.name = "Baby mole";
			npc.models = new int[] { 12073 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 3309;
			npc.walkAnim = 3313;
			npc.options = new String[5];
			npc.drawMinimapDot = false;
			npc.options[0] = "Pick-up";
			npc.combatLevel = 0;
			npc.tileSpacesOccupied = 1;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			break;

		case 6727:
			npc.name = "Tangleroot";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32202;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7313;
			npc.stanceAnimation = 7312;
			npc.description = "Its a Tangleroot.";
			npc.tileSpacesOccupied = 1;
			break;
		case 6728:
			npc.name = "Rocky";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32203;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7316;
			npc.stanceAnimation = 7315;
			npc.description = "Its a Rocky.";
			npc.tileSpacesOccupied = 1;
			break;
		case 6729:
			npc.name = "Giant squirrel";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32206;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7310;
			npc.stanceAnimation = 7309;
			npc.description = "Its a Giant squirrel.";
			npc.tileSpacesOccupied = 1;
			break;
		case 6730:
			npc.name = "Rift guardian";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32204;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.walkAnim = 7306;
			npc.stanceAnimation = 7307;
			npc.description = "Its a Rift guardian.";
			npc.tileSpacesOccupied = 1;
			break;
		case 6731:
			npc.models = new int[1];
			npc.models[0] = 32697;
			npc.name = "Olmlet";
			npc.description = "Its a Olmlet.";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.tileSpacesOccupied = 1;
			npc.stanceAnimation = 7396;
			npc.walkAnim = 7395;
			npc.sizeXZ = npc.sizeY = 65;
			break;

		case 2000:
			npc.models = new int[2];
			npc.models[0] = 28294;
			npc.models[1] = 28295;
			npc.name = "Venenatis";
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			MobDefinition ven = forID(60);
			npc.stanceAnimation = ven.stanceAnimation;
			npc.walkAnim = ven.walkAnim;
			npc.combatLevel = 464;
			npc.tileSpacesOccupied = 3;
			break;
		case 2045:
			npc.name = "Snakeling";
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 14408;
			npc.stanceAnimation = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 1;
			npc.sizeXZ = 30;
			npc.sizeY = 30;
			break;
		case 2042:// turgoise
			npc.name = "Zulrah";
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 14407;
			npc.stanceAnimation = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2043:// regular
			npc.name = "Zulrah";
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 14408;
			npc.stanceAnimation = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2044:// melee
			npc.name = "Zulrah";
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 14409;
			npc.stanceAnimation = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2001:
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorpia";
			npc.options = new String[] { null, "Attack", null, null, null };
			MobDefinition scor = forID(107);
			npc.stanceAnimation = scor.stanceAnimation;
			npc.walkAnim = scor.walkAnim;
			npc.combatLevel = 464;
			npc.tileSpacesOccupied = 3;
			break;
		case 7286:
			npc.name = "Skotizo";
			npc.description = "Badass from the depths of hell";
			npc.combatLevel = 321;
			MobDefinition skotizo = forID(4698);
			npc.stanceAnimation = skotizo.stanceAnimation;
			npc.walkAnim = skotizo.walkAnim;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 31653;
			npc.sizeXZ = 80; // resize if you wish hes a bit small cause personal preference
			npc.sizeY = 80; // resize
			npc.tileSpacesOccupied = 3;
			break;
		case 6766:
			npc.name = "Lizardman shaman";
			npc.description = "It's a lizardman.";
			npc.combatLevel = 150;
			npc.walkAnim = 7195;
			npc.stanceAnimation = 7191;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 4039;
			npc.sizeXZ = 108;
			npc.sizeY = 108;
			npc.tileSpacesOccupied = 3;
			break;
		case 5886:
			npc.name = "Abyssal Sire";
			npc.description = "It's an abyssal sire.";
			npc.combatLevel = 350;
			npc.walkAnim = 4534;
			npc.stanceAnimation = 4533;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 29477;
			npc.sizeXZ = 108;
			npc.sizeY = 108;
			npc.tileSpacesOccupied = 6;
			break;
		case 499:
			npc.name = "Thermonuclear smoke devil";
			npc.description = "It looks like its glowing";
			npc.combatLevel = 301;
			npc.walkAnim = 1828;
			npc.stanceAnimation = 1829;
			npc.options = new String[5];
			npc.options[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 28442;
			npc.sizeXZ = 240;
			npc.sizeY = 240;
			npc.tileSpacesOccupied = 4;
			break;
		case 1999:
			npc.models = new int[2];
			npc.name = "Cerberus";
			npc.models[1] = 29270;
			npc.combatLevel = 318;
			npc.stanceAnimation = 4484;
			npc.walkAnim = 4488;
			npc.options = new String[5];
			npc.originalColours = new int[] { 29270 };
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.sizeXZ = 120;
			npc.sizeY = 120;
			break;
		case 2009:
			npc.name = "Callisto";
			npc.models = new int[] { 28298 };
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.combatLevel = 470;
			MobDefinition callisto = forID(105);
			npc.stanceAnimation = callisto.stanceAnimation;
			npc.walkAnim = callisto.walkAnim;
			npc.options = callisto.options;
			npc.sizeXZ = npc.sizeY = 110;
			npc.tileSpacesOccupied = 4;
			break;
		case 2006:
			npc.models = new int[1];
			npc.models[0] = 28300;
			npc.name = "Vet'ion";
			npc.options = new String[] { null, "Attack", null, null, null };
			MobDefinition vet = forID(90);
			npc.stanceAnimation = vet.stanceAnimation;
			npc.walkAnim = vet.walkAnim;
			npc.combatLevel = 464;
			break;

		case 3847:
			npc.name = "Kraken";
			npc.combatLevel = 291;
			npc.models = new int[] { 28231 };
			npc.stanceAnimation = 3989;
			npc.walkAnim = forID(3847).walkAnim;
			npc.sizeXZ = npc.sizeY = 130;
			npc.lightning = 30;
			npc.shadow = 150;
			break;

		case 2004:
			npc.models = new int[1];
			npc.models[0] = 28231;
			npc.name = "Cave kraken";
			npc.options = new String[] { null, "Attack", null, null, null };
			MobDefinition cave = forID(3847);
			npc.models = new int[1];
			npc.models[0] = 28233;
			npc.combatLevel = 127;
			npc.stanceAnimation = 3989;
			npc.walkAnim = cave.walkAnim;
			npc.sizeXZ = npc.sizeY = 42;
			break;
		case 457:
			npc.name = "Ghost";
			npc.options = new String[] { "Talk-to", null, "Teleport", null, null };
			break;
		case 922:
			npc.options = new String[] { "Talk-to", null, null, null, null };
			break;
		case 241:
			npc.name = "Boss Point Shop";
			break;
		case 543:
			npc.name = "Decanter";
			break;
		case 4902:
			npc.name = "Expert Miner";
			npc.options = new String[] { "Talk-To", null, "Trade", null, null };
			break;
		case 5417:
			npc.combatLevel = 210;
			break;
		case 5418:
			npc.combatLevel = 90;
			break;
		case 6715:
			npc.combatLevel = 91;
			break;
		case 6716:
			npc.combatLevel = 128;
			break;
		case 6701:
			npc.combatLevel = 173;
			break;
		case 6725:
			npc.combatLevel = 224;
			break;
		case 6691:
			npc.tileSpacesOccupied = 2;
			npc.combatLevel = 301;
			break;
		case 1552:
			npc.name = "Donator Shop 1";
			break;
		case 741:
			npc.name = "Donator Shop 2";
			break;
		case 367:
			npc.name = "Item Gambler";
			break;

		case 725:
			npc.models = new int[] { 235, 252, 299, 60010, 10218, 263, 185 };
			npc.name = "Trivia Point Shop";
			break;
		case 8275:
			npc.models = new int[] { 231, 241, 246, 309, 60010, 10218, 254, 181, 3780, 3189 };
			break;
		case 8710:
		case 8707:
		case 8706:
		case 8705:
			npc.name = "Musician";
			npc.options = new String[] { "Listen-to", null, null, null, null };
			break;
		case 7005:
			MobDefinition wiseMan1 = MobDefinition.forID(2253);
			npc.options = new String[5];
			npc.combatLevel = 250;
			npc.name = "Wise Old Man";
			npc.options = new String[5];
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.stanceAnimation = wiseMan1.stanceAnimation;
			npc.walkAnim = wiseMan1.walkAnim;
			npc.models = wiseMan1.models;
			npc.headIcon = MobDefinition.forID(8349).headIcon;
			npc.sizeXZ = wiseMan1.sizeXZ;
			npc.sizeY = wiseMan1.sizeY;
			npc.tileSpacesOccupied = 1;
			npc.drawMinimapDot = true;
			break;
		case 7010:
			MobDefinition wiseMan2 = MobDefinition.forID(2253);
			npc.options = new String[5];
			npc.combatLevel = 1150;
			npc.name = "Grumpy Old Man";
			npc.options = new String[5];
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.stanceAnimation = wiseMan2.stanceAnimation;
			npc.walkAnim = wiseMan2.walkAnim;
			npc.models = wiseMan2.models;
			npc.headIcon = MobDefinition.forID(1158).headIcon;
			npc.sizeXZ = 300;
			npc.sizeY = 300;
			npc.tileSpacesOccupied = 2;
			npc.drawMinimapDot = true;
			break;

		case 947:
			npc.name = "Player Owned Shop Manager";
			npc.options = new String[] { "Talk-to", null, "View Shops", "My Shop", "Claim Earnings" };
			break;
		case 550:
			npc.name = "Dan's Bitch";
			npc.options = new String[] { "@red@Melee Shop", null, "@red@Mage shop", "@red@Range shop", "@red@Pure shop" };
			break;
		case 9939:
			npc.combatLevel = 607;
			break;
		case 149:
			npc.name = "Whirlpool";
			npc.models = new int[] { 26699 };
			npc.options = new String[] { null, "Disturb", null, null, null };
			npc.stanceAnimation = 6737;
			npc.walkAnim = 6737;
			npc.tileSpacesOccupied = 4;
			npc.combatLevel = 0;
			npc.sizeY = 130;
			npc.sizeXZ = 130;
			npc.lightning = 30;
			npc.shadow = 150;
			break;
		case 148:
			npc.name = "Enormous Tentacle";
			npc.models = new int[] { 13201, };
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.stanceAnimation = 3617;
			npc.walkAnim = 3617;
			npc.combatLevel = 0;
			npc.sizeY = 200;
			npc.sizeXZ = 200;
			break;
		case 150:// small
			npc.name = "Whirlpool";
			npc.models = new int[] { 26699 };
			npc.options = new String[] { null, "Disturb", null, null, null };
			npc.stanceAnimation = 6737;
			npc.walkAnim = 6737;
			npc.combatLevel = 0;
			npc.sizeY = 55;
			npc.sizeXZ = 55;
			npc.lightning = 30;
			npc.shadow = 150;
			break;
		case 688:
			npc.name = "Archer";
			break;
		case 4540:
			npc.combatLevel = 299;
			break;
		case 3101:
			npc.sizeY = npc.sizeXZ = 80;
			npc.tileSpacesOccupied = 1;
			npc.options = new String[] { "Talk-to", null, "Start", "Rewards", null };
			break;
		case 6222:
			npc.name = "Kree'arra";
			npc.tileSpacesOccupied = 5;
			npc.stanceAnimation = 6972;
			npc.walkAnim = 6973;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 6203:
			npc.models = new int[] { 27768, 27773, 27764, 27765, 27770 };
			npc.name = "K'ril Tsutsaroth";
			npc.tileSpacesOccupied = 5;
			npc.stanceAnimation = 6943;
			npc.walkAnim = 6942;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 1610:
		case 491:
		case 10216:
			npc.options = new String[] { null, "Attack", null, null, null };
			break;
		case 7969:
			npc.options = new String[] { "Talk-to", null, "Trade", null, null };
			break;
		case 1382:
			npc.name = "Glacor";
			npc.models = new int[] { 58940 };
			npc.tileSpacesOccupied = 3;
			// npc.anInt86 = 475;
			npc.sizeXZ = npc.sizeY = 180;
			npc.stanceAnimation = 10869;
			npc.walkAnim = 10867;
			npc.options = new String[] { null, "Attack", null, null, null };
			npc.combatLevel = 123;
			npc.drawMinimapDot = true;
			npc.combatLevel = 188;
			break;
		/*
		 * case 1383: npc.name = "Unstable glacyte"; npc.models = new int[]{58942};
		 * npc.standAnim = 10867; npc.walkAnim = 10901; npc.actions = new String[]{null,
		 * "Attack", null, null, null}; npc.combatLevel = 101; npc.drawMinimapDot =
		 * false; break; case 1384: npc.name = "Sapping glacyte"; npc.models = new
		 * int[]{58939}; npc.standAnim = 10867; npc.walkAnim = 10901; npc.actions = new
		 * String[]{null, "Attack", null, null, null}; npc.combatLevel = 101;
		 * npc.drawMinimapDot = true; break; case 1385: npc.name = "Enduring glacyte";
		 * npc.models = new int[]{58937}; npc.standAnim = 10867; npc.walkAnim = 10901;
		 * npc.actions = new String[]{null, "Attack", null, null, null}; npc.combatLevel
		 * = 101; npc.drawMinimapDot = true; break;
		 */
		case 4249:
			npc.name = "Gambler";
			break;

		case 710:
			npc.name = "Donator Shop 3";
			break;
		case 6970:
			npc.options = new String[] { "Trade", null, "Exchange Shards", null, null };
			break;
		case 4657:
			npc.options = new String[] { "Talk-to", null, "Claim Items", "Check Total", "Teleport" };
			break;
		case 605:
			npc.options = new String[] { "Talk-to", null, "Vote Rewards", "Loyalty Titles", null };
			break;
		case 8591:
			npc.options = new String[] { "Talk-to", null, "Trade", null, null };
			break;
		case 316:
		case 315:
		case 309:
		case 310:
		case 314:
		case 312:
		case 313:
			npc.sizeXZ = 30;
			break;
		case 318:
			npc.sizeXZ = 30;
			npc.options = new String[] { "Net", null, "Lure", null, null };
			break;
		case 805:
			npc.options = new String[] { "Trade", null, "Tan hide", null, null };
			break;
		case 461:
		case 844:
		case 650:
		case 5112:
		case 3789:
		case 802:
		case 520:
		case 521:
		case 11226:
			npc.options = new String[] { "Trade", null, null, null, null };
			break;
		case 8022:
		case 8028:
			String color = i == 8022 ? "Yellow" : "Green";
			npc.name = "" + color + " energy source";
			npc.options = new String[] { "Siphon", null, null, null, null };
			break;
		case 8444:
			npc.options = new String[5];
			npc.options[0] = "Trade";
			break;
		case 2579:
			npc.name = "Max";
			npc.description = "He's mastered the many skills of Bloodshed.";
			npc.combatLevel = 138;
			npc.options = new String[5];
			npc.options[0] = "Talk-to";
			npc.options[2] = "Trade";
			npc.stanceAnimation = 808;
			npc.walkAnim = 819;
			npc.models = new int[] { 65291, 65322, 506, 529, 252, 9642, 62746, 13307, 62743, 53327 };
			npc.npcHeadModels = new int[] { 39332, 39235 };
			break;
		case 6830:
		case 6841:
		case 6796:
		case 7331:
		case 6831:
		case 7361:
		case 6847:
		case 6872:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7333:
		case 7351:
		case 7367:
		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
		case 9481:
		case 6827:
		case 6889:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6799:
		case 7335:
		case 7347:
		case 6800:
		case 9488:
		case 6804:
		case 6822:
		case 6849:
		case 7355:
		case 7357:
		case 7359:
		case 7341:
		case 7329:
		case 7339:
		case 7349:
		case 7375:
		case 7343:
		case 6820:
		case 6865:
		case 6809:
		case 7363:
		case 7337:
		case 7365:
		case 6991:
		case 6992:
		case 6869:
		case 6818:
		case 6843:
		case 6823:
		case 7377:
		case 6887:
		case 6885:
		case 6883:
		case 6881:
		case 6879:
		case 6877:
		case 6875:
		case 6833:
		case 6851:
		case 5079:
		case 5080:
		case 6824:
			npc.options = new String[] { null, null, null, null, null };
			break;
		case 6806: // thorny snail
		case 6807:
		case 6994: // spirit kalphite
		case 6995:
		case 6867: // bull ant
		case 6868:
		case 6794: // spirit terrorbird
		case 6795:
		case 6815: // war tortoise
		case 6816:
		case 6874:// pack yak
		case 6873: // pack yak
		case 3594: // yak
		case 3590: // war tortoise
		case 3596: // terrorbird
			npc.options = new String[] { "Store", null, null, null, null };
			break;
		case 548:
			npc.options = new String[] { "Trade", null, null, null, null };
			break;
		case 3299:
		case 437:
		case 2313:
			npc.options = new String[] { "Trade", null, null, null, null };
			break;

		case 1267:
		case 8459:
			npc.drawMinimapDot = true;
			break;
		case 961:
			npc.options = new String[] { null, null, "Buy Consumables", "Restore Stats", null };
			npc.name = "Healer";
			break;
		case 705:
			npc.options = new String[] { null, null, "Buy Armour", "Buy Weapons", "Buy Jewelries" };
			npc.name = "Warrior";
			break;
		case 1861:
			npc.options = new String[] { null, null, "Buy Equipment", "Buy Ammunition", null };
			npc.name = "Archer";
			break;
		case 946:
			npc.options = new String[] { null, null, "Buy Equipment", "Buy Runes", null };
			npc.name = "Mage";
			break;
		case 2253:
			npc.options = new String[] { null, null, "Buy Skillcapes", "Buy Skillcapes (t)", "Buy Hoods" };
			break;
		case 2292:
			npc.options = new String[] { "Trade", null, null, null, null };
			npc.name = "Merchant";
			break;
		case 2676:
			npc.options = new String[] { "Makeover", null, null, null, null };
			break;
		case 494:
		case 1360:
			npc.options = new String[] { "Talk-to", null, null, null, null };
			break;
		case 1685:
			npc.name = "Pure";
			npc.options = new String[] { "Trade", null, null, null, null };
			break;
		case 3030:
			npc.name = "King black dragon";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 17414, 17415, 17429, 17422 };
			npc.combatLevel = 276;
			npc.stanceAnimation = 90;
			npc.walkAnim = 4635;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.tileSpacesOccupied = 3;
			break;

		case 3031:
			npc.name = "General graardor";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 27785, 27789 };
			npc.combatLevel = 624;
			npc.stanceAnimation = 7059;
			npc.walkAnim = 7058;
			npc.sizeY = 29;
			npc.sizeXZ = 33;
			break;

		case 3032:
			npc.name = "TzTok-Jad";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 34131 };
			npc.combatLevel = 702;
			npc.stanceAnimation = 9274;
			npc.walkAnim = 9273;
			npc.sizeY = 25;
			npc.sizeXZ = 27;
			npc.tileSpacesOccupied = 1;
			break;

		case 3033:
			npc.name = "Chaos elemental";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 11216 };
			npc.combatLevel = 305;
			npc.stanceAnimation = 3144;
			npc.walkAnim = 3145;
			npc.sizeY = 49;
			npc.sizeXZ = 45;
			break;

		case 3034:
			npc.name = "Corporeal beast";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 40955 };
			npc.combatLevel = 785;
			npc.stanceAnimation = 10056;
			npc.walkAnim = 10055;
			npc.sizeY = 24;
			npc.sizeXZ = 25;
			npc.tileSpacesOccupied = 1;
			break;

		case 3035:
			npc.name = "Kree'arra";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 28003, 28004 };
			npc.combatLevel = 580;
			npc.stanceAnimation = 6972;
			npc.walkAnim = 6973;
			npc.sizeY = 23;
			npc.sizeXZ = 23;
			npc.tileSpacesOccupied = 1;
			break;

		case 3036:
			npc.name = "K'ril tsutsaroth";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 27768, 27773, 27764, 27765, 27770 };
			npc.combatLevel = 650;
			npc.stanceAnimation = 6943;
			npc.walkAnim = 6942;
			npc.sizeY = 24;
			npc.sizeXZ = 24;
			npc.tileSpacesOccupied = 1;
			break;
		case 3037:
			npc.name = "Commander zilyana";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 28057, 28071, 28078, 28056 };
			npc.combatLevel = 596;
			npc.stanceAnimation = 6963;
			npc.walkAnim = 6962;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.tileSpacesOccupied = 1;
			break;
		case 3038:
			npc.name = "Dagannoth supreme";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 9941, 9943 };
			npc.combatLevel = 303;
			npc.stanceAnimation = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.tileSpacesOccupied = 1;
			break;

		case 3039:
			npc.name = "Dagannoth prime"; // 9940, 9943, 9942
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 9940, 9943, 9942 };
			npc.originalColours = new int[] { 11930, 27144, 16536, 16540 };
			npc.destColours = new int[] { 5931, 1688, 21530, 21534 };
			npc.combatLevel = 303;
			npc.stanceAnimation = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.tileSpacesOccupied = 1;
			break;

		case 3040:
			npc.name = "Dagannoth rex";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[] { 9941 };
			npc.originalColours = new int[] { 16536, 16540, 27144, 2477 };
			npc.destColours = new int[] { 7322, 7326, 10403, 2595 };
			npc.combatLevel = 303;
			npc.stanceAnimation = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.tileSpacesOccupied = 1;
			break;
		case 3047:
			npc.name = "Frost dragon";
			npc.combatLevel = 166;
			npc.stanceAnimation = 13156;
			npc.walkAnim = 13157;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			// npc.type = 51;
			npc.degreesToTurn = 32;
			npc.models = new int[] { 56767, 55294 };
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;

		case 3048:
			npc.models = new int[] { 44733 };
			npc.name = "Tormented demon";
			npc.combatLevel = 450;
			npc.stanceAnimation = 10921;
			npc.walkAnim = 10920;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			// npc.type = 8349;
			npc.degreesToTurn = 32;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.tileSpacesOccupied = 1;
			break;
		case 3050:
			npc.models = new int[] { 24602, 24605, 24606 };
			npc.name = "Kalphite queen";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 333;
			npc.stanceAnimation = 6236;
			npc.walkAnim = 6236;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.tileSpacesOccupied = 1;
			break;
		case 3051:
			npc.models = new int[] { 46141 };
			npc.name = "Slash bash";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 111;
			npc.stanceAnimation = 11460;
			npc.walkAnim = 11461;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;
		case 3052:
			npc.models = new int[] { 45412 };
			npc.name = "Phoenix";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 235;
			npc.stanceAnimation = 11074;
			npc.walkAnim = 11075;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;
		case 3053:
			npc.models = new int[] { 46058, 46057 };
			npc.name = "Bandos avatar";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 299;
			npc.stanceAnimation = 11242;
			npc.walkAnim = 11255;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;
		case 3054:
			npc.models = new int[] { 62717 };
			npc.name = "Nex";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 565;
			npc.stanceAnimation = 6320;
			npc.walkAnim = 6319;
			npc.sizeY = 55;
			npc.sizeXZ = 55;
			npc.tileSpacesOccupied = 1;
			break;
		case 3055:
			npc.models = new int[] { 51852, 51853 };
			npc.name = "Jungle strykewyrm";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 110;
			npc.stanceAnimation = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.tileSpacesOccupied = 1;
			break;
		case 3056:
			npc.models = new int[] { 51848, 51850 };
			npc.name = "Desert strykewyrm";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 130;
			npc.stanceAnimation = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.tileSpacesOccupied = 1;
			break;
		case 3057:
			npc.models = new int[] { 51847, 51849 };
			npc.name = "Ice strykewyrm";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 210;
			npc.stanceAnimation = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.tileSpacesOccupied = 1;
			break;
		case 3058:
			npc.models = new int[] { 49142, 49144 };
			npc.name = "Green dragon";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 79;
			npc.stanceAnimation = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.tileSpacesOccupied = 1;
			break;
		case 3059:
			npc.models = new int[] { 57937 };
			npc.name = "Baby blue dragon";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 48;
			npc.stanceAnimation = 14267;
			npc.walkAnim = 14268;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.tileSpacesOccupied = 1;
			break;
		case 3060:
			npc.models = new int[] { 49137, 49144 };
			npc.name = "Blue dragon";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 111;
			npc.stanceAnimation = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;
		case 3061:
			npc.models = new int[] { 14294, 49144 };
			npc.name = "Black dragon";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.combatLevel = 227;
			npc.stanceAnimation = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.tileSpacesOccupied = 1;
			break;
		case 3062:
			npc.models = new int[2];
			npc.models[0] = 28294;
			npc.models[1] = 28295;
			npc.name = "Venenatis";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeXZ = 45;
			npc.sizeY = 45;
			MobDefinition ven2 = forID(60);
			npc.stanceAnimation = ven2.stanceAnimation;
			npc.walkAnim = ven2.walkAnim;
			npc.combatLevel = 464;
			npc.tileSpacesOccupied = 2;
			break;
		case 3063:
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorpia";
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			MobDefinition scor2 = forID(107);
			npc.stanceAnimation = scor2.stanceAnimation;
			npc.walkAnim = scor2.walkAnim;
			npc.sizeXZ = 55;
			npc.sizeY = 55;
			npc.combatLevel = 464;
			npc.tileSpacesOccupied = 1;
			break;
		case 3064:
			npc.name = "Skotizo";
			npc.combatLevel = 321;
			MobDefinition skotizo2 = forID(4698);
			npc.stanceAnimation = skotizo2.stanceAnimation;
			npc.walkAnim = skotizo2.walkAnim;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[1];
			npc.models[0] = 31653;
			npc.sizeXZ = 22;
			npc.sizeY = 22;
			npc.tileSpacesOccupied = 1;
			break;
		case 3065:

			npc.name = "Lizardman Shaman";
			npc.description = "It's a lizardman.";
			npc.combatLevel = 150;
			npc.walkAnim = 7195;
			npc.stanceAnimation = 7191;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[1];
			npc.models[0] = 4039;
			npc.sizeXZ = 38;
			npc.sizeY = 38;
			npc.tileSpacesOccupied = 1;
			break;

		case 3066:
			npc.name = "WildyWyrm";
			npc.models = new int[] { 63604 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeXZ = 30;
			npc.sizeY = 40;
			npc.tileSpacesOccupied = 1;
			// npc.sizeXZ = 35;
			// npc.sizeY = 75;
			break;
		case 3067:
			npc.name = "Bork";
			npc.models = new int[] { 40590 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 8753;
			npc.walkAnim = 8752;
			npc.combatLevel = 267;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeXZ = 40;
			npc.sizeY = 40;
			npc.tileSpacesOccupied = 1;
			// npc.sizeXZ = 35;
			// npc.sizeY = 75;
			break;

		case 3068:
			npc.name = "Barrelchest";
			npc.models = new int[] { 22790 };
			// npc.boundDim = 1;
			npc.stanceAnimation = 5893;
			npc.walkAnim = 5892;
			npc.combatLevel = 267;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeXZ = 40;
			npc.sizeY = 40;
			npc.tileSpacesOccupied = 1;
			break;
		case 3069:
			npc.name = "Rock Crab";
			npc.models = new int[2];
			npc.models[0] = 4399;
			npc.models[1] = 4400;
			// npc.boundDim = 1;
			npc.stanceAnimation = 1310;
			npc.walkAnim = 1311;
			npc.combatLevel = 13;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.tileSpacesOccupied = 1;
			break;
		case 3070:
			npc.name = "Abyssal Sire";
			npc.description = "It's an abyssal sire.";
			npc.combatLevel = 350;
			npc.walkAnim = 4534;
			npc.stanceAnimation = 4533;
			npc.options = new String[5];
			npc.options[0] = "Pick-up";
			npc.models = new int[1];
			npc.models[0] = 29477;
			npc.sizeXZ = 28;
			npc.sizeY = 28;
			npc.tileSpacesOccupied = 1;
			break;

		case 3071:
			npc.name = "Vorago";
			npc.description = "It's an Vorago";
			npc.combatLevel = 1;
			npc.models = new int[1];
			npc.models[0] = 29478;
			npc.sizeXZ = 28;
			npc.sizeY = 28;
			npc.tileSpacesOccupied = 1;
			break;
		/*
		 * case 1265: System.out.println("Models: " + npc.models[1]);
		 * System.out.println("Stand animation: " +npc.standAnim);
		 * System.out.println("Walk animation: " + npc.walkAnim);
		 * 
		 * break;
		 */
		}
		return npc;
	}

	public Model getHeadModel() {
		if (childrenIDs != null) {
			MobDefinition altered = getAlteredNPCDef();
			if (altered == null)
				return null;
			else
				return altered.getHeadModel();
		}
		if (npcHeadModels == null)
			return null;
		boolean everyFetched = false;
		for (int i = 0; i < npcHeadModels.length; i++)
			if (!Model.modelIsFetched(npcHeadModels[i]))
				everyFetched = true;
		if (everyFetched)
			return null;
		Model parts[] = new Model[npcHeadModels.length];
		for (int j = 0; j < npcHeadModels.length; j++)
			parts[j] = Model.fetchModel(npcHeadModels[j]);
		Model completeModel;
		if (parts.length == 1)
			completeModel = parts[0];
		else
			completeModel = new Model(parts.length, parts);
		if (originalColours != null) {
			for (int k = 0; k < originalColours.length; k++)
				completeModel.recolour(originalColours[k], destColours[k]);
		}
		return completeModel;
	}

	public MobDefinition getAlteredNPCDef() {
		try {
			int j = -1;
			if (varbitId != -1) {
				VarBit varBit = VarBit.cache[varbitId];
				int k = varBit.configId;
				int l = varBit.leastSignificantBit;
				int i1 = varBit.mostSignificantBit;
				int j1 = Client.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else if (varSettingsId != -1) {
				j = clientInstance.variousSettings[varSettingsId];
			}
			if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static int NPCAMOUNT = 11599;

	public static void unpackConfig(CacheArchive streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs];
		int i = 2;
		for (int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}
		cache = new MobDefinition[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new MobDefinition();
		// NPCDefThing2.initialize();
	}

	public static void nullLoader() {
		modelCache = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public Model getAnimatedModel(int j, int k, int ai[]) {
		if (childrenIDs != null) {
			MobDefinition npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.getAnimatedModel(j, k, ai);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(k) & FrameReader.isNullFrame(j));
		if (k != -1 && j != -1)
			animatedModel.method471(ai, j, k);
		else if (k != -1)
			animatedModel.applyTransform(k);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.triangleSkin = null;
		animatedModel.vertexSkin = null;
		if (tileSpacesOccupied == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public Model method164(int j, int frame, int ai[], int nextFrame, int idk, int idk2) {
		if (childrenIDs != null) {
			MobDefinition npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.method164(j, frame, ai, nextFrame, idk, idk2);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(frame) & FrameReader.isNullFrame(j));

		if (frame != -1 && j != -1)
			animatedModel.method471(ai, j, frame);
		else if (frame != -1 && nextFrame != -1)
			animatedModel.applyTransform(frame, nextFrame, idk, idk2);
		else if (frame != -1)
			animatedModel.applyTransform(frame);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.triangleSkin = null;
		animatedModel.vertexSkin = null;
		if (tileSpacesOccupied == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				int j = stream.readUnsignedByte();
				models = new int[j];
				for (int j1 = 0; j1 < j; j1++)
					models[j1] = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readNewString();
			else if (i == 3) {
				description = stream.readNewString();
			} else if (i == 12)
				tileSpacesOccupied = stream.readSignedByte();
			else if (i == 13)
				stanceAnimation = stream.readUnsignedWord();
			else if (i == 14) {
				walkAnim = stream.readUnsignedWord();
				runAnim = walkAnim;
			} else if (i == 17) {
				walkAnim = stream.readUnsignedWord();
				turn180AnimIndex = stream.readUnsignedWord();
				turn90CWAnimIndex = stream.readUnsignedWord();
				turn90CCWAnimIndex = stream.readUnsignedWord();
				if (walkAnim == 65535)
					walkAnim = -1;
				if (turn180AnimIndex == 65535)
					turn180AnimIndex = -1;
				if (turn90CWAnimIndex == 65535)
					turn90CWAnimIndex = -1;
				if (turn90CCWAnimIndex == 65535)
					turn90CCWAnimIndex = -1;
			} else if (i >= 30 && i < 40) {
				if (options == null)
					options = new String[5];
				options[i - 30] = stream.readNewString();
				if (options[i - 30].equalsIgnoreCase("hidden"))
					options[i - 30] = null;
			} else if (i == 40) {
				int k = stream.readUnsignedByte();
				destColours = new int[k];
				originalColours = new int[k];
				for (int k1 = 0; k1 < k; k1++) {
					originalColours[k1] = stream.readUnsignedWord();
					destColours[k1] = stream.readUnsignedWord();
				}
			} else if (i == 60) {
				int l = stream.readUnsignedByte();
				npcHeadModels = new int[l];
				for (int l1 = 0; l1 < l; l1++)
					npcHeadModels[l1] = stream.readUnsignedWord();
			} else if (i == 90)
				stream.readUnsignedWord();
			else if (i == 91)
				stream.readUnsignedWord();
			else if (i == 92)
				stream.readUnsignedWord();
			else if (i == 93)
				drawMinimapDot = false;
			else if (i == 95)
				combatLevel = stream.readUnsignedWord();
			else if (i == 97)
				sizeXZ = stream.readUnsignedWord();
			else if (i == 98)
				sizeY = stream.readUnsignedWord();
			else if (i == 99)
				hasRenderPriority = true;
			else if (i == 100)
				lightning = stream.readSignedByte();
			else if (i == 101)
				shadow = stream.readSignedByte() * 5;
			else if (i == 102)
				headIcon = stream.readUnsignedWord();
			else if (i == 103)
				degreesToTurn = stream.readUnsignedWord();
			else if (i == 106) {
				varbitId = stream.readUnsignedWord();
				if (varbitId == 65535)
					varbitId = -1;
				varSettingsId = stream.readUnsignedWord();
				if (varSettingsId == 65535)
					varSettingsId = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for (int i2 = 0; i2 <= i1; i2++) {
					childrenIDs[i2] = stream.readUnsignedWord();
					if (childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}
			} else if (i == 107)
				clickable = false;
		} while (true);
	}

	public MobDefinition() {
		turn90CCWAnimIndex = -1;
		varbitId = -1;
		turn180AnimIndex = -1;
		varSettingsId = -1;
		combatLevel = -1;
		walkAnim = -1;
		tileSpacesOccupied = 1;
		headIcon = -1;
		stanceAnimation = -1;
		type = -1L;
		degreesToTurn = 32;
		turn90CWAnimIndex = -1;
		clickable = true;
		sizeY = 128;
		drawMinimapDot = true;
		sizeXZ = 128;
		hasRenderPriority = false;
	}

	public int turn90CCWAnimIndex;
	public static int cacheIndex;
	public int varbitId;
	public int turn180AnimIndex;
	public int varSettingsId;
	public static Stream stream;
	public int combatLevel;
	public String name;
	public String options[];
	public int walkAnim;
	public int runAnim;
	public byte tileSpacesOccupied;
	public int[] destColours;
	public static int[] streamIndices;
	public int[] npcHeadModels;
	public int headIcon;
	public int[] originalColours;
	public int stanceAnimation;
	public long type;
	public int degreesToTurn;
	public static MobDefinition[] cache;
	public static Client clientInstance;
	public int turn90CWAnimIndex;
	public boolean clickable;
	public int lightning;
	public int sizeY;
	public boolean drawMinimapDot;
	public int childrenIDs[];
	public String description;
	public int sizeXZ;
	public int shadow;
	public boolean hasRenderPriority;
	public int[] models;
	public static MemCache modelCache = new MemCache(30);
	public int id;
}