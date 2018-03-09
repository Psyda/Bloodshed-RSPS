package com.client.client.cache.definitions;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.client.Configuration;
import com.client.client.*;

public final class ItemDefinition {

    private static int[] prices;
    private static List<Integer> untradeableItems = new ArrayList<Integer>();

    public static void nullLoader() {
        modelCache = null;
        spriteCache = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }


    public boolean dialogueModelFetched(int j) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (j == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(k)) {
            flag = false;
        }
        if (l != -1 && !Model.modelIsFetched(l)) {
            flag = false;
        }
        return flag;
    }

    public Model getDialogueModel(int gender) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (gender == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return null;
        }
        Model model = Model.fetchModel(k);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l);
            Model models[] = {model, model_1};
            model = new Model(2, models);
        }
        if (editedModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }
        return model;
    }

    public boolean equipModelFetched(int gender) {
        int fistModel = maleModel0;
        int secondModel = maleEquip2;
        int thirdModel = maleEquip3;
        if (gender == 1) {
            fistModel = femaleModel0;
            secondModel = femaleEquip2;
            thirdModel = femaleEquip3;
        }
        if (fistModel == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(fistModel)) {
            flag = false;
        }
        if (secondModel != -1 && !Model.modelIsFetched(secondModel)) {
            flag = false;
        }
        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel)) {
            flag = false;
        }
        return flag;
    }

    public Model getEquipModel(int gender) {
        int j = maleModel0;
        int k = maleEquip2;
        int l = maleEquip3;
        if (gender == 1) {
            j = femaleModel0;
            k = femaleEquip2;
            l = femaleEquip3;
        }
        if (j == -1) {
            return null;
        }
        Model model = Model.fetchModel(j);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k);
                Model model_3 = Model.fetchModel(l);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }
        //if (j == 62367)
        //model.translate(68, 7, -8);
        if (id == 19112) {
			model.translate(0, 0, 7);
		} 
        if (gender == 0 && maleYOffset != 0) {
            model.translate(0, maleYOffset, 0);
        } else if (gender == 1 && femaleYOffset != 0) {
            model.translate(0, femaleYOffset, 0);
        }
        if (editedModelColor != null && newModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }
        return model;
    }

    public void setDefaults() {
        untradeable = false;
        inventoryModel = 0;
        name = null;
        description = null;
        editedModelColor = null;
        newModelColor = null;
        zoom2d = 2000;
        rotationY = 0;
        rotationX = 0;
        modelOffsetX = 0;
        xOffset2d = 0;
        yOffset2d = 0;
        stackable = false;
        value = 0;
        membersObject = false;
        groundActions = null;
        actions = null;
        maleModel0 = -1;
        maleEquip2 = -1;
        maleYOffset = 0;
        maleXOffset = 0;
        femaleModel0 = -1;
        femaleEquip2 = -1;
        femaleYOffset = 0;
        maleEquip3 = -1;
        femaleEquip3 = -1;
        maleDialogue = -1;
        maleDialogueModel = -1;
        femaleDialogue = -1;
        femaleDialogueModel = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        sizeX = 128;
        sizeY = 128;
        sizeZ = 128;
        shadow = 0;
        lightness = 0;
        team = 0;
        lendID = -1;
        lentItemID = -1;
    }

    public static void unpackConfig(CacheArchive streamLoader) {
        /*
         * stream = new Stream(FileOperations.ReadFile("./Cache/obj.dat"));
		 * Stream stream = new
		 * Stream(FileOperations.ReadFile("./Cache/obj.idx"));
         */
        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
        totalItems = stream.readUnsignedWord();
        streamIndices = new int[totalItems + 1000];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }
        cache = new ItemDefinition[10];
        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDefinition();
        }
        setSettings();
    }

    public static ItemDefinition forID(int i) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == i) {
            	if(Configuration.DISCO_ITEMS) {
	            	if (i == 5572 || i == 5573 || i == 640 || i == 650 || i == 630) {
	            		ItemDefinition.cache[j].newModelColor[0] = RandomColor.currentColour;
	                }
            	}
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDefinition itemDef = cache[cacheIndex];
        if (i >= streamIndices.length) {
            itemDef.id = 1;
            itemDef.setDefaults();
            return itemDef;
        }
        stream.currentOffset = streamIndices[i];
        itemDef.id = i;
        itemDef.setDefaults();
        itemDef.readValues(stream);
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (itemDef.id == i && itemDef.editedModelColor == null) {
            itemDef.editedModelColor = new int[1];
            itemDef.newModelColor = new int[1];
            itemDef.editedModelColor[0] = 0;
            itemDef.newModelColor[0] = 1;
        }
        if (untradeableItems.contains(itemDef.id)) {
            itemDef.untradeable = true;
        }
        itemDef.value = prices[itemDef.id];
        switch (i) {
            case 22482:
                itemDef.name = "Ganodermic visor";
                itemDef.description = "It's an Ganodermic visor";
                itemDef.actions = new String[] { null, "Wear", "Check", "Clean", "drop"};
                itemDef.groundActions = new String[] { null, null, "take", null, null};
                itemDef.inventoryModel = 10935;
                itemDef.maleModel0 = 10523;
                itemDef.femaleModel0 = 10523;
                itemDef.zoom2d = 1118;
                itemDef.rotationY = 215;
                itemDef.rotationX = 175;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -30;
                break;
            case 22490:
                itemDef.name = "Ganodermic poncho";
                itemDef.description = "It's an Ganodermic poncho";
                itemDef.actions = new String[] { null, "Wear", "Check", "Clean", "drop"};
                itemDef.groundActions = new String[] { null, null, "take", null, null};
                itemDef.inventoryModel = 10919;
                itemDef.maleModel0 = 10490;
                itemDef.femaleModel0 = 10490;
                itemDef.zoom2d = 1513;
                itemDef.rotationY = 485;
                itemDef.rotationX = 13;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -3;
                break;
            case 22486:
                itemDef.name = "Ganodermic leggings";
                itemDef.description = "It's an Ganodermic leggings";
                itemDef.actions = new String[] { null, "Wear", "Check", "Clean", "drop"};
                itemDef.groundActions = new String[] { null, null, "take", null, null};
                itemDef.inventoryModel = 10937;
                itemDef.maleModel0 = 10486;
                itemDef.femaleModel0 = 10486;
                itemDef.zoom2d = 1513;
                itemDef.rotationY = 498;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 8;
                itemDef.yOffset2d = -18;
                break;
            case 22494:
                itemDef.name = "Polypore staff";
                itemDef.description = "It's an Polypore staff";
                itemDef.actions = new String[] { null, "Wield", "Check", "Clean", "drop"};
                itemDef.groundActions = new String[] { null, null, "take", null, null};
                itemDef.inventoryModel = 13426;
                itemDef.maleModel0 = 13417;
                itemDef.femaleModel0 = 13417;
                itemDef.zoom2d = 3750;
                itemDef.rotationY = 1454;
                itemDef.rotationX = 997;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 8;
                break;
        case 15670:
        	itemDef.editedModelColor = new int[]{8741, 4888};
            itemDef.newModelColor = new int[]{929, 914};
            itemDef.maleDialogue = 63500;
            itemDef.femaleDialogue = 63500;
        	itemDef.femaleModel0 = 63501;
        	itemDef.maleModel0 = 63502;
        	itemDef.inventoryModel = 63503;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
        	itemDef.name = "Elder chaos hood";
        	itemDef.rotationY = 346;
        	itemDef.xOffset2d = -4;
        	itemDef.rotationX = 55;
        	itemDef.yOffset2d = -7;
        	itemDef.zoom2d = 789;
        	break;
        case 15671:
        	itemDef.editedModelColor = new int[]{127};
            itemDef.newModelColor = new int[]{929};
        	itemDef.femaleModel0 = 63504;
        	itemDef.femaleEquip2 = 63505;
        	itemDef.maleModel0 = 63506;
        	itemDef.maleEquip2 = 63507;
        	itemDef.inventoryModel = 63508;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
        	itemDef.name = "Elder chaos top";
        	itemDef.rotationX = 10;
        	itemDef.rotationY = 553;
        	itemDef.xOffset2d = -1;
        	itemDef.yOffset2d = 3;
        	itemDef.zoom2d = 1503;
        	break;
        case 15672:
        	itemDef.femaleModel0 = 63509;
        	itemDef.maleModel0 = 63510;
        	itemDef.inventoryModel = 63511;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
        	itemDef.name = "Elder chaos robe";
        	itemDef.rotationX = 2000;
        	itemDef.rotationY = 450;
        	itemDef.xOffset2d = -3;
        	itemDef.yOffset2d = -11;
        	itemDef.zoom2d = 2281;
        	break;
        case 15673:
			itemDef.name = "Elder maul";
			itemDef.inventoryModel = 63512;
			itemDef.maleModel0 = 63513;
			itemDef.femaleModel0 = 63513;
			itemDef.zoom2d = 1744;
			itemDef.rotationY = 237;
			itemDef.rotationX = 429;
			itemDef.modelOffsetX = 0;
			itemDef.yOffset2d = -58;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
        case 15674:
			itemDef.name = "Kodai wand";
			itemDef.zoom2d = 1417;
			itemDef.rotationY = 552;
			itemDef.rotationX = 1006;
			itemDef.yOffset2d = 1;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.inventoryModel = 63514;
			itemDef.maleModel0 = 63515;
			itemDef.femaleModel0 = 63515;
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 0;
			itemDef.newModelColor[0] = 4;
			break;
        case 15675:
        	itemDef.femaleModel0 = 63516;
        	itemDef.femaleYOffset = 6;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.inventoryModel = 63517;
        	itemDef.maleModel0 = 63516;
        	itemDef.name = "Heavy ballista";
        	itemDef.rotationY = 189;
        	itemDef.modelOffsetX = 8;
        	itemDef.rotationX = 148;
        	itemDef.yOffset2d = -18;
        	itemDef.zoom2d = 1284;
        break;
        case 15676:
        	itemDef.femaleModel0 = 63518;
        	itemDef.femaleYOffset = 6;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.inventoryModel = 63519;
			itemDef.maleModel0 = 63518;
			itemDef.name = "Light ballista";
			itemDef.rotationY = 189;
			itemDef.modelOffsetX = 8;
			itemDef.rotationX = 148;
			itemDef.yOffset2d = -18;
			itemDef.zoom2d = 1250;
        break;
        case 15677:
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.maleDialogue = 63520;
            itemDef.femaleDialogue = 63520;
        	itemDef.femaleModel0 = 63521;
        	itemDef.maleModel0 = 63522;
        	itemDef.inventoryModel = 63523;
        	itemDef.name = "Ancestral hat";
        	itemDef.rotationY = 118;
        	itemDef.rotationX = 10;
        	itemDef.yOffset2d = -12;
        	itemDef.zoom2d = 1236;
        break;
        case 15678:
        	itemDef.femaleModel0 = 63524;
        	itemDef.femaleEquip2 = 63525;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.inventoryModel = 63526;
			itemDef.maleModel0 = 63527;
			itemDef.maleEquip2 = 63528;
			itemDef.name = "Ancestral robe top";
			itemDef.rotationY = 514;
			itemDef.rotationX = 2041;
			itemDef.yOffset2d = -3;
			itemDef.zoom2d = 1358;
        break;
        case 15679:
        	itemDef.lightness = 30;
        	itemDef.shadow = 20;
        	itemDef.femaleModel0 = 63529;
        	itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.inventoryModel = 63530;
			itemDef.maleModel0 = 63531;
			itemDef.name = "Ancestral robe bottom";
			itemDef.rotationY = 435;
			itemDef.xOffset2d = -1;
			itemDef.rotationX = 9;
			itemDef.yOffset2d = 7;
			itemDef.zoom2d = 1690;
        break;
        case 15680:
			itemDef.name = "Dragon javelin";
			itemDef.zoom2d = 1872;
			itemDef.rotationX = 2009;
			itemDef.rotationY = 282;
			itemDef.xOffset2d = -1;
			itemDef.yOffset2d = -1;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop", };
			itemDef.inventoryModel = 63532;
			break;
		case 8421:
			itemDef.setDefaults();
			itemDef.name = "Dragonbone spirit shield";
			itemDef.zoom2d = 1616; // Model Zoom
			itemDef.maleModel0 = 38941; // Male Equip 1
			itemDef.femaleModel0 = 38941; // Male Equip 2
			itemDef.inventoryModel = 38942; // Model ID
			itemDef.rotationY = 396; // Model Rotation 1
			itemDef.rotationX = 1050; // Model Rotation 2
			itemDef.xOffset2d = -3; // Model Offset 1
			itemDef.yOffset2d = 16; // Model Offset 2
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
     case 5573:
			ItemDefinition def = forID(1019);
			itemDef.name = "Disco cape";
			itemDef.description = def.description;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 7700;
			itemDef.newModelColor[0] = RandomColor.currentColour; 
			itemDef.inventoryModel = def.inventoryModel;
			itemDef.zoom2d = def.zoom2d;
			itemDef.rotationY = def.rotationY;
			itemDef.rotationX = def.rotationX;
			itemDef.xOffset2d = def.xOffset2d;
			itemDef.yOffset2d = def.yOffset2d;
			itemDef.maleModel0 = def.maleModel0;
			itemDef.femaleModel0 = def.femaleModel0;
		//	System.out.print("model "+def.maleEquip1);
			break;

        /**
         * Treasure Island Keys
         */
        case 18689:
            itemDef.name = "Key of blitz";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 14678:
            itemDef.name = "Key of cobra";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 13158:
            itemDef.name = "Key of fear";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 13758:
            itemDef.name = "Key of death";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
      
        case 640:
        	itemDef.name = "Disco Robe Top";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
        case 630:
        	itemDef.name = "Disco Boots";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
        case 650:
        	itemDef.name = "Disco Robe Bottom";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
		case 5572:
			itemDef.name = "Disco partyhat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = RandomColor.currentColour; 
			itemDef.inventoryModel = 2635;
			itemDef.zoom2d = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.yOffset2d = 1;
			itemDef.xOffset2d = 1;
			itemDef.maleModel0 = 187;
			itemDef.femaleModel0 = 363;
			break;

			
            /** OSRS && NEW ITEMS **/
            case 13247:
                itemDef.inventoryModel = 29240;
                itemDef.name = "Hellpuppy";
                itemDef.description = "It's a Hellpuppy.";
                itemDef.zoom2d = 1616;
                itemDef.rotationY = 3;
                itemDef.rotationX = 213;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 12646:
                itemDef.inventoryModel = 12073;
                itemDef.name = "Baby mole";
                itemDef.description = "It's a Baby mole.";
                itemDef.zoom2d = 2256;
                itemDef.rotationY = 369;
                itemDef.rotationX = 1874;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 12655:
                itemDef.inventoryModel = 28869;
                itemDef.name = "Pet kraken";
                itemDef.description = "It's a Pet kraken.";
                itemDef.zoom2d = 1744;
                itemDef.rotationY = 330;
                itemDef.rotationX = 1940;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 20061:
                itemDef.inventoryModel = 10247;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "Abyssal vine whip";
                itemDef.zoom2d = 848;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.xOffset2d = 5;
                itemDef.yOffset2d = 38;
                itemDef.maleModel0 = 10253;
                itemDef.femaleModel0 = 10253;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 20010:
                itemDef.name = "Trickster robe";
                itemDef.description = "Its a Trickster robe";
                itemDef.maleModel0 = 44786;
                itemDef.femaleModel0 = 44786;
                itemDef.inventoryModel = 45329;
                itemDef.rotationY = 593;
                itemDef.rotationX = 2041;
                itemDef.zoom2d = 1420;
                itemDef.yOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20011:
                itemDef.name = "Trickster robe legs";
                itemDef.description = "Its a Trickster robe";
                itemDef.maleModel0 = 44770;
                itemDef.femaleModel0 = 44770;
                itemDef.inventoryModel = 45335;
                itemDef.rotationY = 567;
                itemDef.rotationX = 1023;
                itemDef.zoom2d = 2105;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20012:
                itemDef.name = "Trickster helm";
                itemDef.description = "Its a Trickster helm";
                itemDef.maleModel0 = 44764;
                itemDef.femaleModel0 = 44764;
                itemDef.inventoryModel = 45328;
                itemDef.rotationY = 5;
                itemDef.rotationX = 1889;
                itemDef.zoom2d = 738;
                itemDef.yOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20013:
                itemDef.inventoryModel = 44633;
                itemDef.name = "Vanguard helm";
                itemDef.zoom2d = 855;
                itemDef.rotationY = 1966;
                itemDef.rotationX = 5;
                itemDef.yOffset2d = 4;
                itemDef.xOffset2d = -1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44769;
                itemDef.femaleModel0 = 44769;
                break;
            case 20014:
                itemDef.inventoryModel = 44627;
                itemDef.name = "Vanguard body";
                itemDef.zoom2d = 1513;
                itemDef.rotationX = 2041;
                itemDef.rotationY = 593;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = -11;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44812;
                itemDef.femaleModel0 = 44812;
                break;
          
            case 14062: 
                itemDef.inventoryModel = 50011;
		        itemDef.name = "Vanguard legs";
		        itemDef.zoom2d = 1711;
		        itemDef.rotationX = 0;
		        itemDef.rotationY = 360;
		        itemDef.xOffset2d = 3;
		        itemDef.yOffset2d = -11;
	            itemDef.groundActions = new String[] { null, null, "Take", null, null };
		        itemDef.actions = new String[5];
		        itemDef.actions[1] = "Wear";
		        itemDef.maleModel0 = 44771;
		        itemDef.femaleModel0 = 44771;
		        break;
            case 20016:
                itemDef.inventoryModel = 44704;
                itemDef.name = "Battle-mage helm";
                itemDef.zoom2d = 658;
                itemDef.rotationX = 1898;
                itemDef.rotationY = 2;
                itemDef.xOffset2d = 12;
                itemDef.yOffset2d = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44767;
                itemDef.femaleModel0 = 44767;
                break;
            case 20017:
                itemDef.inventoryModel = 44631;
                itemDef.name = "Battle-mage robe";
                itemDef.zoom2d = 1382;
                itemDef.rotationX = 3;
                itemDef.rotationY = 488;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44818;
                itemDef.femaleModel0 = 44818;
                break;
            case 20018:
                itemDef.inventoryModel = 44672;
                itemDef.name = "Battle-mage robe legs";
                itemDef.zoom2d = 1842;
                itemDef.rotationX = 1024;
                itemDef.rotationY = 498;
                itemDef.xOffset2d = 4;
                itemDef.yOffset2d = -1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44775;
                itemDef.femaleModel0 = 44775;
                break;
            case 20019:
                itemDef.inventoryModel = 45316;
                itemDef.name = "Trickster boots";
                itemDef.zoom2d = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.xOffset2d = -9;
                itemDef.yOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44757;
                itemDef.femaleModel0 = 44757;
                break;
            case 20020:
                itemDef.inventoryModel = 45317;
                itemDef.name = "Trickster gloves";
                itemDef.zoom2d = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44761;
                itemDef.femaleModel0 = 44761;
                break;
            case 20021:
                itemDef.inventoryModel = 44662;
                itemDef.name = "Battle-mage boots";
                itemDef.zoom2d = 987;
                itemDef.rotationX = 1988;
                itemDef.rotationY = 188;
                itemDef.xOffset2d = -8;
                itemDef.yOffset2d = 5;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44755;
                itemDef.femaleModel0 = 44755;
                break;
            case 20022:
                itemDef.inventoryModel = 44573;
                itemDef.name = "Battle-mage gloves";
                itemDef.zoom2d = 1053;
                itemDef.rotationX = 0;
                itemDef.rotationY = 536;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleModel0 = 44762;
                itemDef.femaleModel0 = 44762;
                break;
            case 11554:
                itemDef.name = "Abyssal tentacle";
                itemDef.zoom2d = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 121;
                itemDef.yOffset2d = 56;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.inventoryModel = 28439;
                itemDef.maleModel0 = 45006;
                itemDef.femaleModel0 = 43500;
                break;
            case 11926:
                itemDef.name = "Odium ward";
                itemDef.zoom2d = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.yOffset2d = 3;
                itemDef.newModelColor = new int[]{15252};
                itemDef.editedModelColor = new int[]{908};
                itemDef.inventoryModel = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleModel0 = 9347;
                itemDef.femaleModel0 = 9347;
                break;
                case 11290:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.inventoryModel = 2438;
                itemDef.zoom2d = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -10;
                itemDef.maleModel0 = 3188;
                itemDef.femaleModel0 = 3192;
                itemDef.name = "Sky Blue h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11291:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.inventoryModel = 2438;
                itemDef.zoom2d = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -10;
                itemDef.maleModel0 = 3188;
                itemDef.femaleModel0 = 3192;
                itemDef.name = "Orange h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11292:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 11199;
                itemDef.inventoryModel = 2438;
                itemDef.zoom2d = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -10;
                itemDef.maleModel0 = 3188;
                itemDef.femaleModel0 = 3192;
                itemDef.name = "Yellow h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11293:
                itemDef.name = "Yellow Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare yellow santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 11191;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 11294:
                itemDef.name = "Sky Blye Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare sky blue santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 689484;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12426:
                itemDef.inventoryModel = 28633;
                itemDef.name = "3rd age longsword";
                itemDef.description = "3rd age longsword";
                itemDef.zoom2d = 1726;
                itemDef.rotationY = 1576;
                itemDef.rotationX = 242;
                itemDef.xOffset2d = 5;
                itemDef.yOffset2d = 4;
                itemDef.maleModel0 = 28618;
                itemDef.femaleModel0 = 28618;
                itemDef.femaleYOffset = 4;
                itemDef.maleYOffset = 4;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;

            case 12437:
                itemDef.inventoryModel = 28648;
                itemDef.name = "3rd age cloak";
                itemDef.description = "3rd age cloak";
                itemDef.zoom2d = 2000;
                itemDef.rotationY = 282;
                itemDef.rotationX = 962;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.maleModel0 = 28483;
                itemDef.femaleModel0 = 28560;
                itemDef.maleXOffset = -3;
                itemDef.maleYOffset = -3;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 11295:
                itemDef.name = "White Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare white santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 11289:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 9583;
                itemDef.inventoryModel = 2438;
                itemDef.zoom2d = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -10;
                itemDef.maleModel0 = 3188;
                itemDef.femaleModel0 = 3192;
                itemDef.name = "White h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11288:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 196608;
                itemDef.inventoryModel = 2438;
                itemDef.zoom2d = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -10;
                itemDef.maleModel0 = 3188;
                itemDef.femaleModel0 = 3192;
                itemDef.name = "Black h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11924:
                itemDef.name = "Malediction ward";
                itemDef.zoom2d = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.yOffset2d = 3;
                itemDef.newModelColor = new int[]{-21608};
                itemDef.editedModelColor = new int[]{908};
                itemDef.inventoryModel = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleModel0 = 9347;
                itemDef.femaleModel0 = 9347;
                break;
            case 4660:
            	itemDef.name = "Odium shard 1";
            	itemDef.inventoryModel = 28316;
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1469;
            	itemDef.rotationX = 1100;
            	itemDef.modelOffsetX = 3;
            	itemDef.yOffset2d = 1;
            	itemDef.newModelColor = new int[]{15252};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 4666:
            	itemDef.name = "Odium shard 2";
            	itemDef.inventoryModel = 28316;
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1100;
            	itemDef.rotationX = 969;
            	itemDef.modelOffsetX = 6;
            	itemDef.yOffset2d = -5;
            	itemDef.newModelColor = new int[]{15252};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 4670:
            	itemDef.name = "Odium shard 3";
            	itemDef.inventoryModel = 28316;
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1269;
            	itemDef.rotationX = 900;
            	itemDef.modelOffsetX = 7;
            	itemDef.yOffset2d = -4;
            	itemDef.newModelColor = new int[]{15252};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 4672:
            	itemDef.name = "Malediction shard 1";
            	itemDef.inventoryModel = 28316;
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1469;
            	itemDef.rotationX = 1100;
            	itemDef.modelOffsetX = 3;
            	itemDef.yOffset2d = 1;
            	itemDef.newModelColor = new int[]{-21608};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 4680:
            	itemDef.name = "Malediction shard 2";//this 1?yep brokeo
            	itemDef.inventoryModel = 37425;//lets try 1 before bath ready i do it for u cause i love uhahahahah so show me one of these that dont work in
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1100;
            	itemDef.rotationX = 969;
            	itemDef.modelOffsetX = 6;
            	itemDef.yOffset2d = -5;
            	itemDef.newModelColor = new int[]{-21608};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 4686:
            	itemDef.name = "Malediction shard 3";
            	itemDef.inventoryModel = 28316;
            	itemDef.zoom2d = 1000;
            	itemDef.rotationY = 1269;
            	itemDef.rotationX = 900;
            	itemDef.modelOffsetX = 7;
            	itemDef.yOffset2d = -4;
            	itemDef.newModelColor = new int[]{-21608};
            	itemDef.editedModelColor = new int[]{908};
            	break;
            case 6195:
            	itemDef.name = "Magic fang";
            	itemDef.inventoryModel = 19227;
            	itemDef.zoom2d = 1095;
            	itemDef.rotationX = 579;
            	itemDef.rotationY = 1832;
            	itemDef.modelOffsetX = 7;
            	break;
            case 12282:
                itemDef.name = "Serpentine helm";
                itemDef.inventoryModel = 19220;
                itemDef.zoom2d = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.femaleModel0 = 14398;
                itemDef.maleModel0 = 14395;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12279:
                itemDef.name = "Magma helm";
                itemDef.inventoryModel = 29205;
                itemDef.zoom2d = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleModel0 = 14426;
                itemDef.maleModel0 = 14424;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12278:
                itemDef.name = "Tanzanite helm";
                itemDef.inventoryModel = 29213;
                itemDef.zoom2d = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleModel0 = 23994;
                itemDef.maleModel0 = 14421;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case -1:
                itemDef.name = "Pet King black dragon";
                ItemDefinition itemDef2 = ItemDefinition.forID(12458);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 13239:
                itemDef.name = "Primordial boots";
                itemDef.inventoryModel = 29397;
                itemDef.zoom2d = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.yOffset2d = 5;
                itemDef.maleModel0 = 29250;
                itemDef.femaleModel0 = 29255;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 12708:
                itemDef.name = "Pegasian boots";
                itemDef.inventoryModel = 29396;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.groundActions = new String[5];
                itemDef.groundActions[1] = "Take";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 3;
                itemDef.yOffset2d = -7;
                itemDef.maleModel0 = 29252;
                itemDef.femaleModel0 = 29253;
                break;
            case 13235:
                itemDef.name = "Eternal boots";
                itemDef.inventoryModel = 29394;
                itemDef.zoom2d = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.yOffset2d = 5;
                itemDef.maleModel0 = 29249;
                itemDef.femaleModel0 = 29254;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 20059:
                itemDef.name = "Drygore rapier";
                itemDef.zoom2d = 1145;
                itemDef.rotationX = 2047;
                itemDef.rotationY = 254;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -45;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.inventoryModel = 14000;
                itemDef.maleModel0 = 14001;
                itemDef.femaleModel0 = 14001;
                break;
            case 20057:
                itemDef.name = "Drygore longsword";
                itemDef.zoom2d = 1645;
                itemDef.rotationX = 377;
                itemDef.rotationY = 444;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.inventoryModel = 14022;
                itemDef.maleModel0 = 14023;
                itemDef.femaleModel0 = 14023;
                break;
            case 20058:
                itemDef.name = "Drygore mace";
                itemDef.zoom2d = 1118;
                itemDef.rotationX = 28;
                itemDef.rotationY = 235;
                itemDef.xOffset2d = -1;
                itemDef.yOffset2d = -47;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.inventoryModel = 14024;
                itemDef.maleModel0 = 14025;
                itemDef.femaleModel0 = 14025;
                break;
            /**END OF OSRS ITEMS**/
            case 19670:
                itemDef.name = "Vote scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 6821:
                itemDef.name = "Magic Coin Orb";
                break;
            case 10034:
            case 10033:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 13727:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 6500:
                itemDef.setDefaults();
                itemDef.imitate(forID(9952));
                itemDef.name = "Charming imp";
                itemDef.stackable = false;
                // itemDef.rotationX = 85;
                // itemDef.rotationY = 1867;
                itemDef.actions = new String[]{null, null, "Check", "Config", "Drop"};
                break;
            case 13045:
                itemDef.name = "Abyssal bludgeon";
                itemDef.zoom2d = 2611;
                itemDef.rotationY = 552;
                itemDef.rotationX = 1508;
                itemDef.yOffset2d = 3;
                itemDef.xOffset2d = -17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.inventoryModel = 29597;
                itemDef.maleModel0 = 29424;
                itemDef.femaleModel0 = 29424;
                break;
            case 13047:
                itemDef.name = "Abyssal dagger";
                itemDef.zoom2d = 1347;
                itemDef.rotationY = 1589;
                itemDef.rotationX = 781;
                itemDef.yOffset2d = 3;
                itemDef.xOffset2d = -5;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.inventoryModel = 29598;
                itemDef.maleModel0 = 29425;
                itemDef.femaleModel0 = 29425;
                break;
            case 500:
                itemDef.inventoryModel = 2635;
                itemDef.name = "Black Party Hat";
                itemDef.description = "Black Party Hat";
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1845;
                itemDef.rotationY = 121;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 1;
                itemDef.stackable = false;
                itemDef.value = 1;
                itemDef.membersObject = true;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[]{926};
                break;
            case 11551:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor[0] = 6020;
                itemDef.editedModelColor[0] = 933;
                itemDef.inventoryModel = 2537;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 72;
                itemDef.rotationY = 136;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.name = "Black santa hat";
                itemDef.description = "It's a Black santa hat.";
                break;
            case 13655:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Red Dragon Kiteshield";
                itemDef.description = "A rare, protective kiteshield.";
                itemDef.inventoryModel = 13701;
                itemDef.zoom2d = 1560;
                itemDef.rotationY = 344;
                itemDef.rotationX = 1104;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = -6;
                itemDef.yOffset2d = -14;
                itemDef.maleModel0 = 13700;
                itemDef.femaleModel0 = 13700;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                //itemDef.anInt188 = -1;
                //itemDef.anInt164 = -1;
                itemDef.maleDialogue = -1;
                itemDef.femaleDialogue = -1;
                break;
            case 12284:
                itemDef.name = "Toxic staff of the dead";
                itemDef.inventoryModel = 19224;
                itemDef.zoom2d = 2150;
                itemDef.rotationX = 1010;
                itemDef.rotationY = 512;
                itemDef.femaleModel0 = 14402;
                itemDef.maleModel0 = 49001;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                itemDef.actions[4] = "Uncharge";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.editedModelColor = new int[1];
                itemDef.editedModelColor[0] = 17467;
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;

            case 12605:
                itemDef.name = "Treasonous ring";
                itemDef.zoom2d = 750;
                itemDef.rotationY = 342;
                itemDef.rotationX = 252;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.inventoryModel = 43900;
                break;

            /** clue scrolls **/
            case 2714:
                itemDef.name = "Casket";
                break;
            case 6183:
                itemDef.name = "@red@Donation Box";
                break;
            case 12632:
                itemDef.name = "100m Note";
                itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
                break;
            case 5023:
                itemDef.name = "@red@ 1B Note";
                itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
                break;
            case 4202:
                itemDef.name = "Ring of Coins";
            	break;
            case 2568:
            	itemDef.name = "Ring of Wealthier";
            	break;
            
            case 2677:
            case 2678:
            case 2679:
            case 2680:
            case 2681:
            case 2682:
            case 2683:
            case 2684:
            case 2685:
            case 2686:
            case 2687:
            case 2688:
            case 2689:
            case 2690:
            case 2691:
                itemDef.name = "Clue Scroll";
                break;


            case 13051:
                itemDef.name = "Armadyl crossbow";
                itemDef.zoom2d = 1325;
                itemDef.rotationY = 240;
                itemDef.rotationX = 110;
                itemDef.xOffset2d = -6;
                itemDef.yOffset2d = -40;
                itemDef.newModelColor = new int[]{115, 107, 10167, 10171};
                itemDef.editedModelColor = new int[]{5409, 5404, 6449, 7390};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.inventoryModel = 19967;
                itemDef.maleModel0 = 19839;
                itemDef.femaleModel0 = 19839;
                break;
            case 12927:
                itemDef.name = "Magma blowpipe";
                itemDef.zoom2d = 1158;
                itemDef.rotationY = 768;
                itemDef.rotationX = 189;
                itemDef.xOffset2d = -7;
                itemDef.yOffset2d = 4;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Uncharge"};
                itemDef.newModelColor = new int[]{8134, 5058, 926, 957, 3008, 1321, 86, 41, 49, 7110, 3008, 1317};
                itemDef.editedModelColor = new int[]{48045, 49069, 48055, 49083, 50114, 33668, 29656, 29603, 33674, 33690, 33680, 33692};
                itemDef.inventoryModel = 19219;
                itemDef.maleModel0 = 14403;
                itemDef.femaleModel0 = 14403;
                break;
            case 12926:
                itemDef.inventoryModel = 25000;
                itemDef.name = "Toxic blowpipe";
                itemDef.description = "It's a Toxic blowpipe";
                itemDef.zoom2d = 1158;
                itemDef.rotationY = 768;
                itemDef.rotationX = 189;
                itemDef.xOffset2d = -7;
                itemDef.yOffset2d = 4;
                itemDef.maleModel0 = 14403;
                itemDef.femaleModel0 = 14403;
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 13058:
                itemDef.name = "Trident of the seas";
                itemDef.description = "A weapon from the deep.";
                itemDef.femaleModel0 = 28230;
                itemDef.maleModel0 = 28230;
                itemDef.inventoryModel = 28232;
                itemDef.rotationY = 420;
                itemDef.rotationX = 1130;
                itemDef.zoom2d = 2755;
                itemDef.yOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[3] = "Check";
                break;
            case 12601:
                itemDef.name = "Ring of the gods";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 393;
                itemDef.rotationX = 1589;
                itemDef.xOffset2d = -9;
                itemDef.yOffset2d = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.inventoryModel = 28824;
                break;
            case 12603:
                itemDef.name = "Tyrannical ring";
                itemDef.zoom2d = 592;
                itemDef.rotationY = 285;
                itemDef.rotationX = 1163;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.inventoryModel = 28823;
                break;
            case 20555:
                itemDef.name = "Dragon warhammer";
                itemDef.inventoryModel = 4041;
                itemDef.rotationY = 1450;
                itemDef.rotationX = 1900;
                itemDef.zoom2d = 1605;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.maleModel0 = 4037;
                itemDef.femaleModel0 = 4038;
                break;
            case 11613:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.inventoryModel = 13701;
                itemDef.zoom2d = 1560;
                itemDef.rotationY = 344;
                itemDef.rotationX = 1104;
                itemDef.yOffset2d = -14;
                itemDef.modelOffsetX = 0;
                itemDef.maleModel0 = 13700;
                itemDef.femaleModel0 = 13700;
                itemDef.name = "Dragon Kiteshield";
                itemDef.description = "A Dragon Kiteshield!";
                break;
            case 4151:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Abyssal whip";
                itemDef.description = "Lowest powered whip";
                itemDef.inventoryModel = 5412;//Inv & Ground
                itemDef.zoom2d = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = 56;
                itemDef.maleModel0 = 5409;//Male Wield View
                itemDef.femaleModel0 = 5409;//Female Wield View
                break;
            case 80:
                itemDef.actions = new String[5];
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.actions[1] = "Wield";
                itemDef.inventoryModel = 5412;
                itemDef.maleModel0 = 5409;
                itemDef.femaleModel0 = 5409;
                itemDef.zoom2d = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 0;
                itemDef.yOffset2d = -2;
                itemDef.modelOffsetX = 56;
                itemDef.name = "Lime Whip";
                itemDef.description = "A Lime Abyssal Whip";
                itemDef.editedModelColor[0] = 399;
                itemDef.newModelColor[0] = 17350;
    		break;
            case 11614:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.inventoryModel = 3288;
                itemDef.zoom2d = 2000;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.yOffset2d = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleModel0 = 3287;
                itemDef.femaleModel0 = 3287;
                itemDef.name = "Death Cape";
                break;
            case 11995:
                itemDef.name = "Pet Chaos elemental";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1284;
                itemDef.rotationX = 175;
                itemDef.rotationY = 0;
                itemDef.inventoryModel = 40852;
                itemDef.xOffset2d = -66;
                itemDef.yOffset2d = 75;
                itemDef.modelOffsetX = 1939;
                break;
            case 11996:
                itemDef.name = "Pet King black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 2000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.inventoryModel = 40858;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11997:
                itemDef.name = "Pet General graardor";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.inventoryModel = 40853;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11978:
                itemDef.name = "Pet TzTok-Jad";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.inventoryModel = 40854;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12001:
                itemDef.name = "Pet Corporeal beast";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.inventoryModel = 40955;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12002:
                itemDef.name = "Pet Kree'arra";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 976;
                itemDef.rotationX = 1892;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 40855;
                itemDef.xOffset2d = -20;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12003:
                itemDef.name = "Pet K'ril tsutsaroth";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1168;
                itemDef.rotationX = 0;
                itemDef.rotationY = 2012;
                itemDef.inventoryModel = 40856;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12004:
                itemDef.name = "Pet Commander zilyana";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1000;
                itemDef.rotationX = 1931;
                itemDef.rotationY = 9;
                itemDef.inventoryModel = 40857;
                itemDef.xOffset2d = 5;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12005:
                itemDef.name = "Pet Dagannoth supreme";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 9941;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12006:
                itemDef.name = "Pet Dagannoth prime";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 9941;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{5931, 1688, 21530, 21534};
                itemDef.editedModelColor = new int[]{11930, 27144, 16536, 16540};
                break;
            case 11990:
                itemDef.name = "Pet Dagannoth rex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 9941;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{7322, 7326, 10403, 2595};
                itemDef.editedModelColor = new int[]{16536, 16540, 27144, 2477};
                break;
            case 11991:
                itemDef.name = "Pet Frost dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 56767;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11992:
                itemDef.name = "Pet Tormented demon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 44733;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11993:
                itemDef.name = "Pet Kalphite queen";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 24597;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11994:
                itemDef.name = "Pet Slash bash";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 46141;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11989:
                itemDef.name = "Pet Phoenix";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 45412;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11988:
                itemDef.name = "Pet Bandos avatar";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 46058;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11987:
                itemDef.name = "Pet Nex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 62717;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11986:
                itemDef.name = "Pet Jungle strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 51852;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11985:
                itemDef.name = "Pet Desert strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 51848;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11984:
                itemDef.name = "Pet Ice strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 51847;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11983:
                itemDef.name = "Pet Green dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 49142;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11982:
                itemDef.name = "Pet Baby blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 3060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 57937;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11981:
                itemDef.name = "Pet Blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 49137;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11979:
                itemDef.name = "Pet Black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 14294;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 11967:
                itemDef.name = "Pet Skotizo";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 13000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 31653;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11969:
                itemDef.name = "Pet Lizardman Shaman";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 8060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 4039;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11970:
                itemDef.name = "Pet WildyWyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 63604;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11971:
                itemDef.name = "Pet Bork";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 6560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 40590;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11972:
                itemDef.name = "Pet Barrelchest";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 5560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 22790;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11973:
                itemDef.name = "Pet Abyssal Sire";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 12060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 29477;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11974:
                itemDef.name = "Pet Rock Crab";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 2060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.inventoryModel = 4400;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11975:
                itemDef.name = "Pet Scorpia";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 3347;
                itemDef.rotationX = 189;
                itemDef.rotationY = 121;
                itemDef.inventoryModel = 28293;
                itemDef.xOffset2d = 12;
                itemDef.yOffset2d = -100;
                itemDef.modelOffsetX = 0;
                break;

            case 11976:
                itemDef.name = "Pet Venenatis";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 4080;
                itemDef.rotationX = 67;
                itemDef.rotationY = 67;
                itemDef.inventoryModel = 28294;
                itemDef.xOffset2d = 9;
                itemDef.yOffset2d = -4;
                itemDef.modelOffsetX = 0;
                break;
            case 14667:
                itemDef.name = "Zombie fragment";
                itemDef.inventoryModel = ItemDefinition.forID(14639).inventoryModel;
                break;
            case 15182:
                itemDef.actions[0] = "Bury";
                break;
            case 15084:
                itemDef.actions[0] = "Roll";
                itemDef.name = "Dice (up to 100)";
                itemDef2 = ItemDefinition.forID(15098);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                break;
            case 2996:
                itemDef.name = "Agility ticket";
                break;
            case 5510:
            case 5512:
            case 5509:
                itemDef.actions = new String[]{"Fill", null, "Empty", "Check", null, null};
                break;
            case 11998:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 11999:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 350;
                itemDef.inventoryModel = 2429;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 11998;
                itemDef.certTemplateID = 799;
                break;
            case 10025:
                itemDef.name = "Charm Box";
                itemDef.actions = new String[]{"Open", null, null, null, null};
                break;
            case 1389:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 1390:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 17401:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 17402:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.inventoryModel = 2429;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 17401;
                itemDef.certTemplateID = 799;
                break;
            case 15009:
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 20787:
    			itemDef.inventoryModel = 35118;
    			itemDef.name = "Golden mining gloves";
    			itemDef.zoom2d = 1049;
    			itemDef.rotationX = 377;
    			itemDef.rotationY = 970;
    			itemDef.xOffset2d = -1;
    			itemDef.yOffset2d = -1;
    			itemDef.maleModel0 = 35119;
    			itemDef.femaleModel0 = 35120;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 20788:
    			itemDef.inventoryModel = 35121;
    			itemDef.name = "Golden mining boots";
    			itemDef.zoom2d = 848;
    			itemDef.rotationX = 177;
    			itemDef.rotationY = 195;
    			itemDef.xOffset2d = 7;
    			itemDef.yOffset2d = -20;
    			itemDef.maleModel0 = 35122;
    			itemDef.femaleModel0 = 35123;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 20789:
    			itemDef.inventoryModel = 35124;
    			itemDef.name = "Golden mining helmet";
    			itemDef.zoom2d = 976;
    			itemDef.rotationX = 132;
    			itemDef.rotationY = 165;
    			itemDef.xOffset2d = 0;
    			itemDef.yOffset2d = 0;
    			itemDef.maleModel0 = 35125;
    			itemDef.femaleModel0 = 35126;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 20790:
    			itemDef.inventoryModel = 35127;
    			itemDef.name = "Golden mining trousers";
    			itemDef.zoom2d = 1616;
    			itemDef.rotationX = 276;
    			itemDef.rotationY = 1829;
    			itemDef.xOffset2d = 0;
    			itemDef.yOffset2d = 11;
    			itemDef.maleModel0 = 35128;
    			itemDef.femaleModel0 = 35129;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 13356:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65495;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffsetX = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65496;
    			itemDef.femaleModel0 = 65496;
    			itemDef.stackable = false;
    			itemDef.name = "Master agility cape";
    			itemDef.description = "Master agility cape";
    			break;
    		 case 11350: 
   		      ItemDefinition itemDef21 = forID(11732);
   		      itemDef.inventoryModel = itemDef21.inventoryModel;
   		      itemDef.maleModel0 = itemDef21.maleModel0;
   		      itemDef.femaleModel0 = itemDef21.femaleModel0;
   		      itemDef.xOffset2d = itemDef21.xOffset2d;
   		      itemDef.modelOffsetX = itemDef21.modelOffsetX;
   		      itemDef.yOffset2d = itemDef21.yOffset2d;
   		      itemDef.rotationY = itemDef21.rotationY;
   		      itemDef.rotationX = itemDef21.rotationX;
   		      itemDef.zoom2d = itemDef21.zoom2d;
   		      itemDef.name = "Green Dragon boots ";
   		      itemDef.actions = itemDef21.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor = new int[] { 419770 };
   		     
   		      itemDef.stackable = false;
   		   
   		      break;
   		 case 11352: 
   		      ItemDefinition itemDef2111 = forID(11732);
   		      itemDef.inventoryModel = itemDef2111.inventoryModel;
   		      itemDef.maleModel0 = itemDef2111.maleModel0;
   		      itemDef.femaleModel0 = itemDef2111.femaleModel0;
   		      itemDef.xOffset2d = itemDef2111.xOffset2d;
   		      itemDef.modelOffsetX = itemDef2111.modelOffsetX;
   		      itemDef.yOffset2d = itemDef2111.yOffset2d;
   		      itemDef.rotationY = itemDef2111.rotationY;
   		      itemDef.rotationX = itemDef2111.rotationX;
   		      itemDef.zoom2d = itemDef2111.zoom2d;
   		      itemDef.name = "Blue Dragon boots ";
   		      itemDef.actions = itemDef2111.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 302770 };
   		      itemDef.stackable = false;
   		    
   		      break;
   		 case 11351: 
   		      ItemDefinition itemDef211 = forID(11732);
   		      itemDef.inventoryModel = itemDef211.inventoryModel;
   		      itemDef.maleModel0 = itemDef211.maleModel0;
   		      itemDef.femaleModel0 = itemDef211.femaleModel0;
   		      itemDef.xOffset2d = itemDef211.xOffset2d;
   		      itemDef.modelOffsetX = itemDef211.modelOffsetX;
   		      itemDef.yOffset2d = itemDef211.yOffset2d;
   		      itemDef.rotationY = itemDef211.rotationY;
   		      itemDef.rotationX = itemDef211.rotationX;
   		      itemDef.zoom2d = itemDef211.zoom2d;
   		      itemDef.name = "Lava Dragon boots ";
   		      itemDef.actions = itemDef211.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 461770 };
   		      itemDef.stackable = false;
   		    
   		      break;
   
   		 case 11353: 
   		      ItemDefinition itemDef211111 = forID(11732);
   		      itemDef.inventoryModel = itemDef211111.inventoryModel;
   		      itemDef.maleModel0 = itemDef211111.maleModel0;
   		      itemDef.femaleModel0 = itemDef211111.femaleModel0;
   		      itemDef.xOffset2d = itemDef211111.xOffset2d;
   		      itemDef.modelOffsetX = itemDef211111.modelOffsetX;
   		      itemDef.yOffset2d = itemDef211111.yOffset2d;
   		      itemDef.rotationY = itemDef211111.rotationY;
   		      itemDef.rotationX = itemDef211111.rotationX;
   		      itemDef.zoom2d = itemDef211111.zoom2d;
   		      itemDef.name = "Pink Dragon boots ";
   		      itemDef.actions = itemDef211111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 123770 };
   		      itemDef.stackable = false;		    
   		      break;
   		case 19481:
			itemDef.name = "Heavy ballista";
			itemDef.description = "It's a Heavy ballista.";
			itemDef.zoom2d = 1284;
			itemDef.rotationY = 189;
			itemDef.rotationX = 148;
			itemDef.xOffset2d = 8;
			itemDef.yOffset2d = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.inventoryModel = 31523;
			itemDef.maleModel0 = 31236;
			itemDef.femaleModel0 = 31236;
			break;
   	//case 13329:
	//		itemDef.name = "Fire max cape";
	//		itemDef.modelZoom = 2232;
	//		rotationY = 687;
	//		itemDef.rotationX = 27;
			//		itemDef.modelOffsetY = 0;
			//		itemDef.modelOffset1 = 27;			
			//		itemDef.newModelColor = new int[] { 668,675,673,815,784 };
			//        itemDef.editedModelColor = new int[] { 947,960,7104,8146,0 };
			//		itemDef.groundActions = new String[] { null, null, "Take", null, null };
			//		itemDef.actions = new String[] { null, "Wear", "Teleports", "Features", "Drop", };
			//		itemDef.modelID = 10888;
			//		itemDef.maleEquip1 = 10889;
			//		itemDef.femaleEquip1 = 11080;
			//		break;
			
		case 13329:
			itemDef.name = "Fire max cape";
			itemDef.zoom2d = 2232;
			itemDef.rotationY = 687;
			itemDef.rotationX = 27;
			itemDef.yOffset2d = 0;
			itemDef.newModelColor = new int[] { 668,675,673,815,784 };
	        itemDef.editedModelColor = new int[] { 947,960,7104,8146,0 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", "Teleports", "Features", "Drop", };
			itemDef.inventoryModel = 10888;
            itemDef.maleModel0 = 65300;
            itemDef.femaleModel0 = 65322;
			break;	
   		case 20998:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.inventoryModel = 32799;
			itemDef.name = "Twisted bow";
			itemDef.zoom2d = 2000;
			itemDef.rotationY = 720;
			itemDef.rotationX = 1500;
			itemDef.xOffset2d = 3;
			itemDef.yOffset2d = 1;
			itemDef.femaleModel0 = 32674;
			itemDef.maleModel0 = 32674;
			itemDef.description = "A mystical bow carved from the twisted remains of the Great Olm.";
			break;
   		case 8465:
		    itemDef.name = "Red slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.inventoryModel = 20861;
		    itemDef.maleModel0 = 20862;
		    itemDef.femaleModel0 = 20862;
		    itemDef.zoom2d = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.xOffset2d = -4;
		    itemDef.yOffset2d = -3;
		
		    break;
		    
		case 8467:
		    itemDef.name = "Green slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.inventoryModel = 20859;
		    itemDef.maleModel0 = 20860;
		    itemDef.femaleModel0 = 20860;
		    itemDef.zoom2d = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.xOffset2d = -4;
		    itemDef.yOffset2d = -3;
		   
		    break;
		case 8469:
		    itemDef.name = "Black slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.inventoryModel = 20863;
		    itemDef.maleModel0 = 20864;
		    itemDef.femaleModel0 = 20864;
		    itemDef.zoom2d = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.xOffset2d = -4;
		    itemDef.yOffset2d = -3;
	
		    break;
   		 case 11354: 
   		      ItemDefinition itemDef2111111 = forID(11732);
   		      itemDef.inventoryModel = itemDef2111111.inventoryModel;
   		      itemDef.maleModel0 = itemDef2111111.maleModel0;
   		      itemDef.femaleModel0 = itemDef2111111.femaleModel0;
   		      itemDef.xOffset2d = itemDef2111111.xOffset2d;
   		      itemDef.modelOffsetX = itemDef2111111.modelOffsetX;
   		      itemDef.yOffset2d = itemDef2111111.yOffset2d;
   		      itemDef.rotationY = itemDef2111111.rotationY;
   		      itemDef.rotationX = itemDef2111111.rotationX;
   		      itemDef.zoom2d = itemDef2111111.zoom2d;
   		      itemDef.name = "Yellow Dragon boots ";
   		      itemDef.actions = itemDef2111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 76770 };
   		      itemDef.stackable = false;		    
   		      break;
   		 case 11355: 
   		      ItemDefinition itemDef21111111 = forID(11732);
   		      itemDef.inventoryModel = itemDef21111111.inventoryModel;
   		      itemDef.maleModel0 = itemDef21111111.maleModel0;
   		      itemDef.femaleModel0 = itemDef21111111.femaleModel0;
   		      itemDef.xOffset2d = itemDef21111111.xOffset2d;
   		      itemDef.modelOffsetX = itemDef21111111.modelOffsetX;
   		      itemDef.yOffset2d = itemDef21111111.yOffset2d;
   		      itemDef.rotationY = itemDef21111111.rotationY;
   		      itemDef.rotationX = itemDef21111111.rotationX;
   		      itemDef.zoom2d = itemDef21111111.zoom2d;
   		    //itemDef.name = "White Dragon boots ";
   		      itemDef.actions = itemDef21111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 266770 };
   		      itemDef.stackable = false;		    
   		      break;
   		 case 11356: 
   		      ItemDefinition itemDef211111111 = forID(11732);
   		      itemDef.inventoryModel = itemDef211111111.inventoryModel;
   		      itemDef.maleModel0 = itemDef211111111.maleModel0;
   		      itemDef.femaleModel0 = itemDef211111111.femaleModel0;
   		      itemDef.xOffset2d = itemDef211111111.xOffset2d;
   		      itemDef.modelOffsetX = itemDef211111111.modelOffsetX;
   		      itemDef.yOffset2d = itemDef211111111.yOffset2d;
   		      itemDef.rotationY = itemDef211111111.rotationY;
   		      itemDef.rotationX = itemDef211111111.rotationX;
   		      itemDef.zoom2d = itemDef211111111.zoom2d;
   		      itemDef.name = "White Dragon boots ";
   		      itemDef.actions = itemDef211111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 100 };
   		      itemDef.stackable = false;		    
   		      break;
    		case 13994:
    			   itemDef.inventoryModel = 44699;
    			   itemDef.name = "Vanguard gloves";
    			   itemDef.zoom2d = 830;
    			   itemDef.rotationX = 536;
    			   itemDef.rotationY = 0;
    			   itemDef.xOffset2d = 9;
    			   itemDef.yOffset2d = 3;
    			   itemDef.stackable = false;
    			   itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
    			   itemDef.maleModel0 = 44758;
    			   itemDef.femaleModel0 = 44758;
    			   break;

    			  case 13995:
    			   itemDef.inventoryModel = 44700;
    			   itemDef.name = "Vanguard boots";
    			   itemDef.zoom2d = 848;
    			   itemDef.rotationY = 141;
    			   itemDef.rotationX = 141;
    			   itemDef.xOffset2d = 4;
    			   itemDef.yOffset2d = 0;
    			   itemDef.stackable = false;
    			   itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
    			   itemDef.maleModel0 = 44752;
    			   itemDef.femaleModel0 = 44752;
    			   break;
    	    		case 13657:
    	    			itemDef.actions = new String[5];
    	    			itemDef.actions[1] = "Wear";
    	    			itemDef.inventoryModel = 65497;
    	    			itemDef.zoom2d = 2000;
    	    			itemDef.rotationY = 504;
    	    			itemDef.rotationX = 1000;
    	    			itemDef.xOffset2d = 30;
    	    			itemDef.yOffset2d = 35;
    	    			itemDef.maleModel0 = 65498;
    	    			itemDef.femaleModel0 = 65498;
    	    			itemDef.stackable = false;
    	    			itemDef.name = "Master attack cape";
    	    			itemDef.description = "Master attack cape";
    	    			break;
    		case 13658:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65499;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65500;
    			itemDef.femaleModel0 = 65500;
    			itemDef.stackable = false;
    			itemDef.name = "Master const. cape";
    			itemDef.description = "Master const. cape";
    			break;
    		case 13359:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65501;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65502;
    			itemDef.femaleModel0 = 65502;
    			itemDef.stackable = false;
    			itemDef.name = "master cooking cape";
    			itemDef.description = "Master cooking cape";
    			break;
    		case 13360:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65503;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65504;
    			itemDef.femaleModel0 = 65504;
    			itemDef.stackable = false;
    			itemDef.name = "master crafting cape";
    			itemDef.description = "Master crafting cape";
    			break;
    		case 13361:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65505;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65506;
    			itemDef.femaleModel0 = 65506;
    			itemDef.stackable = false;
    			itemDef.name = "master defence cape";
    			itemDef.description = "Master defence cape";
    			break;
    		case 13662:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65507;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65508;
    			itemDef.femaleModel0 = 65508;
    			itemDef.stackable = false;
    			itemDef.name = "master farming cape";
    			itemDef.description = "Master farming cape";
    			break;
    		case 13664:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65509;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65510;
    			itemDef.femaleModel0 = 65510;
    			itemDef.stackable = false;
    			itemDef.name = "master firemaking cape";
    			itemDef.description = "Master firemaking cape";
    			break;
    		case 13665:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65511;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65512;
    			itemDef.femaleModel0 = 65512;
    			itemDef.stackable = false;
    			itemDef.name = "master fishing cape";
    			itemDef.description = "Master fishing cape";
    			break;
    		case 13666:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65513;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65514;
    			itemDef.femaleModel0 = 65514;
    			itemDef.stackable = false;
    			itemDef.name = "master fletching cape";
    			itemDef.description = "Master fletching cape";
    			break;
    		case 13667:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65515;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65516;
    			itemDef.femaleModel0 = 65516;
    			itemDef.stackable = false;
    			itemDef.name = "master herblore cape";
    			itemDef.description = "Master herblore cape";
    			break;
    		case 13668:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65517;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65518;
    			itemDef.femaleModel0 = 65518;
    			itemDef.stackable = false;
    			itemDef.name = "master hitpoints cape";
    			itemDef.description = "Master hitpoints cape";
    			break;
    		case 13669:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65519;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65520;
    			itemDef.femaleModel0 = 65520;
    			itemDef.stackable = false;
    			itemDef.name = "master hunter cape";
    			itemDef.description = "Master hunter cape";
    			break;
    		case 13670:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65521;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65522;
    			itemDef.femaleModel0 = 65522;
    			itemDef.stackable = false;
    			itemDef.name = "master magic cape";
    			itemDef.description = "Master magic cape";
    			break;
    		case 13671:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65523;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65524;
    			itemDef.femaleModel0 = 65524;
    			itemDef.stackable = false;
    			itemDef.name = "master mining cape";
    			itemDef.description = "Master mining cape";
    			break;
    		case 13672:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65525;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65526;
    			itemDef.femaleModel0 = 65526;
    			itemDef.stackable = false;
    			itemDef.name = "master prayer cape";
    			itemDef.description = "Master prayer cape";
    			break;
    		case 13673:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65527;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65528;
    			itemDef.femaleModel0 = 65528;
    			itemDef.stackable = false;
    			itemDef.name = "master ranged cape";
    			itemDef.description = "Master ranged cape";
    			break;
    		case 13674:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65529;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65530;
    			itemDef.femaleModel0 = 65530;
    			itemDef.stackable = false;
    			itemDef.name = "master runecrafting cape";
    			itemDef.description = "Master runecrafting cape";
    			break;
    		case 13675:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65531;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65532;
    			itemDef.femaleModel0 = 65532;
    			itemDef.stackable = false;
    			itemDef.name = "master slayer cape";
    			itemDef.description = "Master slayer cape";
    			break;
    		case 13676:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65533;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 65534;
    			itemDef.femaleModel0 = 65534;
    			itemDef.stackable = false;
    			itemDef.name = "master smithing cape";
    			itemDef.description = "Master smithing cape";
    			break;
    		case 13690:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 65535;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 64536;
    			itemDef.femaleModel0 = 64536;
    			itemDef.stackable = false;
    			itemDef.name = "Master strength cape";
    			itemDef.description = "Master strength cape";
    			break;
    		case 13678:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 64537;
    			itemDef.zoom2d = 2203;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 64538;
    			itemDef.femaleModel0 = 64538;
    			itemDef.stackable = false;
    			itemDef.name = "master summoning cape";
    			itemDef.description = "Master summoning cape";
    			break;
    		case 13679:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 64539;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 64540;
    			itemDef.femaleModel0 = 64540;
    			itemDef.stackable = false;
    			itemDef.name = "master thieving cape";
    			itemDef.description = "Master thieving cape";
    			break;
    		case 13680:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.inventoryModel = 64541;
    			itemDef.zoom2d = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.xOffset2d = 30;
    			itemDef.yOffset2d = 35;
    			itemDef.maleModel0 = 64542;
    			itemDef.femaleModel0 = 64542;
    			itemDef.stackable = false;
    			itemDef.name = "master woodcutting cape";
    			itemDef.description = "Master woodcutting cape";
    			break;
    		case 20791:
    			itemDef.inventoryModel = 35130;
    			itemDef.name = "Golden mining top";
    			itemDef.zoom2d = 1360;
    			itemDef.rotationX = 609;
    			itemDef.rotationY = 0;
    			itemDef.xOffset2d = 0;
    			itemDef.yOffset2d = -1;
    			itemDef.maleModel0 = 35131;
    			itemDef.femaleModel0 = 351329;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
            case 15010:
                itemDef.inventoryModel = 2429;
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 15009;
                itemDef.certTemplateID = 799;
                break;
            //start osrs pets
            case 13321:
                itemDef.name = "Rock Golem";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 2640;
                itemDef.rotationX = 1829;
                itemDef.rotationY = 42;
                itemDef.inventoryModel = 29755;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13320:
                itemDef.name = "Heron";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 2256;
                itemDef.rotationX = 1757;
                itemDef.rotationY = 381;
                itemDef.inventoryModel = 29756;
                itemDef.modelOffsetX = 10;
                itemDef.xOffset2d = 25;
                break;
            case 13322:
                itemDef.name = "Beaver";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.inventoryModel = 29754;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13323:
                itemDef.name = "Tangleroot";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.inventoryModel = 32202;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13324:
                itemDef.name = "Rocky";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.inventoryModel = 32203;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13325:
                itemDef.name = "Giant squirrel";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.inventoryModel = 32205;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13326:
                itemDef.name = "Rift Guardian";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.inventoryModel = 32204;
                itemDef.modelOffsetX = 0;
                itemDef.xOffset2d = 0;
                break;
            case 13327:
                itemDef.name = "Olmlet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.zoom2d = 968;
                itemDef.rotationX = 1778;
                itemDef.rotationY = 67;
                itemDef.inventoryModel = 32798;
                itemDef.yOffset2d = 16;
                itemDef.xOffset2d = 1;
                break;
            //end

            case 11884:
                itemDef.actions = new String[]{"Open", null, null, null, null, null};
                break;

            /**
             * Flasks
             */
            case 14136:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (4)";
                itemDef.description = "4 doses of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14134:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14132:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14130:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14428:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14427;
                itemDef.certTemplateID = 799;
                break;
            case 14427:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14426:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14425;
                itemDef.certTemplateID = 799;
                break;
            case 14425:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14424:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14423;
                itemDef.certTemplateID = 799;
                break;
            case 14423:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14422:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14421;
                itemDef.certTemplateID = 799;
                break;
            case 14421:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14420:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14419;
                itemDef.certTemplateID = 799;
                break;
            case 14419:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14418:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14417;
                itemDef.certTemplateID = 799;
                break;
            case 14417:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of saradomin brew.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.xOffset2d = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14416:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14415;
                itemDef.certTemplateID = 799;
                break;
            case 14415:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14414:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14413;
                itemDef.certTemplateID = 799;
                break;
            case 14413:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14412:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14411;
                itemDef.certTemplateID = 799;
                break;
            case 14411:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14410:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14409;
                itemDef.certTemplateID = 799;
                break;
            case 14409:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14408:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14407;
                itemDef.certTemplateID = 799;
                break;
            case 14407:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14406:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super restore flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14405;
                itemDef.certTemplateID = 799;
                break;
            case 14405:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of super restore potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14404:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14403;
                itemDef.certTemplateID = 799;
                break;
            case 14403:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Magic flask (6)";
                itemDef.description = "6 doses of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 3046:
            case 3044:
            case 3042:
            case 3040:
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{61};
                break;
            case 14402:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14401;
                itemDef.certTemplateID = 799;
                break;
            case 14401:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14400:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14399;
                itemDef.certTemplateID = 799;
                break;
            case 14399:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14398:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14397;
                itemDef.certTemplateID = 799;
                break;
            case 14397:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14396:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14395;
                itemDef.certTemplateID = 799;
                break;
            case 14395:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Magic flask (2)";
                itemDef.description = "2 doses of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14394:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Magic flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14393;
                itemDef.certTemplateID = 799;
                break;
            case 14393:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14385:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Recover special flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14383:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Recover special flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14381:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (4)";
                itemDef.description = "4 doses of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14379:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (3)";
                itemDef.description = "3 doses of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14377:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (2)";
                itemDef.description = "2 doses of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14375:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (1)";
                itemDef.description = "1 dose of recover special.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14373:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (6)";
                itemDef.description = "6 doses of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14371:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (5)";
                itemDef.description = "5 doses of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14369:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (4)";
                itemDef.description = "4 doses of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14367:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (3)";
                itemDef.description = "3 doses of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14365:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (2)";
                itemDef.description = "2 doses of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14363:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (1)";
                itemDef.description = "1 dose of extreme attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14361:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (6)";
                itemDef.description = "6 doses of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14359:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (5)";
                itemDef.description = "5 doses of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14357:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (4)";
                itemDef.description = "4 doses of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14355:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (3)";
                itemDef.description = "3 doses of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14353:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (2)";
                itemDef.description = "2 doses of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14351:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (1)";
                itemDef.description = "1 dose of extreme strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14349:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (6)";
                itemDef.description = "6 doses of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14347:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (5)";
                itemDef.description = "5 doses of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14345:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (4)";
                itemDef.description = "4 doses of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14343:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme defence flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14341:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (2)";
                itemDef.description = "2 doses of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14339:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (1)";
                itemDef.description = "1 dose of extreme defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14337:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (6)";
                itemDef.description = "6 doses of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14335:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (5)";
                itemDef.description = "5 doses of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14333:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (4)";
                itemDef.description = "4 doses of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14331:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14329:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14327:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme magic flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of extreme magic potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14325:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of extreme ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14323:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (5)";
                itemDef.description = "5 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14321:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (4)";
                itemDef.description = "4 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14319:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (3)";
                itemDef.description = " 3 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14317:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (2)";
                itemDef.description = "2 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14315:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (1)";
                itemDef.description = "1 dose of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14313:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (6)";
                itemDef.description = "6 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14311:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (5)";
                itemDef.description = "5 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14309:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (4)";
                itemDef.description = "4 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14307:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (3)";
                itemDef.description = "3 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14305:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (2)";
                itemDef.description = "2 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14303:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (1)";
                itemDef.description = "1 dose of super prayer potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14301:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (6)";
                itemDef.description = "6 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14299:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (5)";
                itemDef.description = "5 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14297:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (4)";
                itemDef.description = "4 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14295:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (3)";
                itemDef.description = "3 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14293:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (2)";
                itemDef.description = "2 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14291:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (1)";
                itemDef.description = "1 dose of overload potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.groundActions[2] = "Take";

                itemDef.inventoryModel = 61812;
                break;
            case 14289:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (6)";
                itemDef.description = "6 doses of prayer renewal.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14287:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (5)";
                itemDef.description = "5 doses of prayer renewal.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 15123:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (4)";
                itemDef.description = "4 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 15121:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (3)";
                itemDef.description = "3 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 15119:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (2)";
                itemDef.description = "2 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 7340:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (1)";
                itemDef.description = "1 dose of prayer renewal potion";
                itemDef.stackable = false;
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14196:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (4)";
                itemDef.description = "4 doses of prayer potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14194:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (3)";
                itemDef.description = "3 doses of prayer potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14192:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (2)";
                itemDef.description = "2 doses of prayer potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14190:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (1)";
                itemDef.description = "1 dose of prayer potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14189:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14188;
                itemDef.certTemplateID = 799;
                break;
            case 14188:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (6)";
                itemDef.description = "6 doses of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14187:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14186;
                itemDef.certTemplateID = 799;
                break;
            case 14186:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (5)";
                itemDef.description = "5 doses of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14185:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14184;
                itemDef.certTemplateID = 799;
                break;
            case 14184:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (4)";
                itemDef.description = "4 doses of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14183:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14182;
                itemDef.certTemplateID = 799;
                break;
            case 14182:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (3)";
                itemDef.description = "3 doses of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14181:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14180;
                itemDef.certTemplateID = 799;
                break;
            case 14180:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (2)";
                itemDef.description = "2 doses of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14179:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super attack flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14178;
                itemDef.certTemplateID = 799;
                break;
            case 14178:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (1)";
                itemDef.description = "1 dose of super attack potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14177:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14176;
                itemDef.certTemplateID = 799;
                break;
            case 14176:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (6)";
                itemDef.description = "6 doses of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14175:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14174;
                itemDef.certTemplateID = 799;
                break;
            case 14174:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (5)";
                itemDef.description = "5 doses of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14173:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14172;
                itemDef.certTemplateID = 799;
                break;
            case 14172:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (4)";
                itemDef.description = "4 doses of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14171:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14170;
                itemDef.certTemplateID = 799;
                break;
            case 14170:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (3)";
                itemDef.description = "3 doses of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14169:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14168;
                itemDef.certTemplateID = 799;
                break;
            case 14168:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (2)";
                itemDef.description = "2 doses of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14167:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Super strength flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14166;
                itemDef.certTemplateID = 799;
                break;
            case 14166:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (1)";
                itemDef.description = "1 dose of super strength potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14164:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (6)";
                itemDef.description = "6 doses of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14162:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (5)";
                itemDef.description = "5 doses of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14160:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (4)";
                itemDef.description = "4 doses of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61764;
                break;
            case 14158:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (3)";
                itemDef.description = "3 doses of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14156:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (2)";
                itemDef.description = "2 doses of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14154:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (1)";
                itemDef.description = "1 dose of super defence potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14153:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14152;
                itemDef.certTemplateID = 799;
                break;
            case 14152:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (6)";
                itemDef.description = "6 doses of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
            case 14151:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14150;
                itemDef.certTemplateID = 799;
                break;
            case 14150:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (5)";
                itemDef.description = "5 doses of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 14149:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14148;
                itemDef.certTemplateID = 799;
                break;
            case 14148:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (4)";
                itemDef.description = "4 doses of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.groundActions[2] = "Take";
                itemDef.inventoryModel = 61764;
                break;
            case 14147:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14146;
                itemDef.certTemplateID = 799;
                break;
            case 14146:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (3)";
                itemDef.description = "3 doses of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61727;
                break;
            case 14145:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14144;
                itemDef.certTemplateID = 799;
                break;
            case 14144:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (2)";
                itemDef.description = "2 doses of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61731;
                break;
            case 14143:
                itemDef.setDefaults();
                itemDef.inventoryModel = 2429;
                itemDef.name = "Ranging flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.zoom2d = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.xOffset2d = 0;
                itemDef.stackable = true;
                itemDef.certID = 14142;
                itemDef.certTemplateID = 799;
                break;
            case 14142:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (1)";
                itemDef.description = "1 dose of ranging potion.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61812;
                break;
            case 14140:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (6)";
                itemDef.description = "6 doses of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61732;
                break;
                
            case 14138:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (5)";
                itemDef.description = "5 doses of super antipoison.";
                itemDef.zoom2d = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.inventoryModel = 61729;
                break;
            case 4706:
                itemDef.inventoryModel = 62692;
                itemDef.name = "Zaryte bow";
                itemDef.zoom2d = 1703;
                itemDef.rotationY = 221;
                itemDef.rotationX = 404;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -13;
                itemDef.maleModel0 = 62750;
                itemDef.femaleModel0 = 62750;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                break;
            case 4705:
                itemDef.inventoryModel = 6701;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "A weapon from the Abyss, interlaced with a vicious jade vine.";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 3;
                itemDef.maleModel0 = 6700;
                itemDef.femaleModel0 = 6700;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                break;
            case 15262:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[2] = "Open-All";
                break;
            case 6570:
                itemDef.actions[2] = "Upgrade";
                break;
            case 4155:
                itemDef.name = "Slayer gem";
                itemDef.actions = new String[]{"Activate", null, "Social-Slayer", null, "Destroy"};
                break;
            case 13663:
                itemDef.name = "Stat reset cert.";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Open";
                break;
            case 13653:
                itemDef.name = "Energy fragment";
                break;
            case 292:
                itemDef.name = "Ingredients book";
                break;
            case 15707:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[0] = "Create Party";
                break;
        /*    case 14044:
                itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.editedModelColor = new int[1];
    			itemDef.newModelColor = new int[1];
    			itemDef.editedModelColor[0] = 926;
    			itemDef.newModelColor[0] = 0;
    			itemDef.modelID = 2635;
    			itemDef.modelZoom = 440;
    			itemDef.rotationX = 76;
    			itemDef.rotationY = 1850;
    			
    			itemDef.modelOffsetX = 1;
    			itemDef.modelOffsetY = 1;
    			itemDef.maleEquip1 = 187;
    			itemDef.femaleEquip1 = 363;
    			itemDef.anInt175 = 29;
    			itemDef.stackable = false;
    			itemDef.anInt197 = 87;
    			itemDef.name = "Black Party Hat";
    			itemDef.description = "A Party Hat.";
    			break; */
            case 11667:
            	itemDef.name = "Ironman helmet";
            	 itemDef.zoom2d = 842;
     		 //   itemDef.rotationX = 525;
     		    itemDef.rotationY = 525;
     		 //   itemDef.modelOffset1 = 1;
     		    itemDef.yOffset2d = -3;
         //  itemDef.editedModelColor = new int[] { 61, 33, 24 };
           // 	itemDef.newModelColor = new int[] { 24, 61, 41 };
            	itemDef.groundActions = new String[] { null, null, "Take", null, null };
            	itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
            	itemDef.inventoryModel = 2509;
            	itemDef.maleModel0 = 27148;
            	itemDef.femaleModel0 = 27148;
            	break;
            case 14044:
                itemDef.name = "Black Party Hat";
                itemDef.inventoryModel = 2635;
                itemDef.description = "A rare black party hat";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 0;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14045:
                itemDef.name = "Lime Party Hat";
                itemDef.inventoryModel = 2635;
                itemDef.description = "A rare lime party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14046:
                itemDef.name = "Pink Party Hat";
                itemDef.inventoryModel = 2635;
                itemDef.description = "A rare pink party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 57300;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14047:
                itemDef.name = "Sky Blue Party Hat";
                itemDef.inventoryModel = 2635;
                itemDef.description = "A rare sky blue party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14048:
                itemDef.name = "Lava Party Hat";
                itemDef.inventoryModel = 2635;
                itemDef.description = "A rare lava party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;
                itemDef.femaleModel0 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14049:
                itemDef.name = "Pink Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare pink santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 57300;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14050:
                itemDef.name = "Black Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare black santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 0;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14051:
                itemDef.name = "Lime Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare lime santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 17350;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14052:
                itemDef.name = "Lava Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14053:
                itemDef.name = "Lava Santa Hat";
                itemDef.inventoryModel = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.zoom2d = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 189;
                itemDef.femaleModel0 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 15152:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.inventoryModel = 2635;
                itemDef.zoom2d = 440;
                itemDef.rotationX = 76;
                itemDef.rotationY = 1850;

                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 187;

                itemDef.femaleModel0 = 363;

                itemDef.name = "Lime Party Hat";
                itemDef.description = "A Party Hat.";
            case 14501:
                itemDef.inventoryModel = 44574;
                itemDef.maleModel0 = 43693;
                itemDef.femaleModel0 = 43693;
                break;
            case 19112:
    			itemDef.setDefaults();
    			itemDef.name = "Infernal cape";
    			itemDef.inventoryModel = 33144;
    			itemDef.femaleModel0 = 33111;
    			itemDef.maleModel0 = 33103;
    			itemDef.zoom2d = 2086;
    			itemDef.rotationX = 2031;
    			itemDef.rotationY = 567;
    			itemDef.xOffset2d = -4;
    			itemDef.yOffset2d = 0;
    			itemDef.actions = new String[] { null, "Wear", null, null, null };
    			break;
            case 19111:
                itemDef.name = "TokHaar-Kal";
                itemDef.value = 60000;
                itemDef.maleModel0 = 62575;
                itemDef.femaleModel0 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.xOffset2d = -4;
                itemDef.inventoryModel = 62592;
                itemDef.stackable = false;
                itemDef.description = "A cape made of ancient, enchanted rocks.";
                itemDef.zoom2d = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.xOffset2d = 0;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                break;
            case 13262:
                itemDef2 = ItemDefinition.forID(20072);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.maleModel0 = itemDef2.maleModel0;
                itemDef.femaleModel0 = itemDef2.femaleModel0;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.zoom2d = itemDef2.zoom2d;
                itemDef.name = itemDef2.name;
                itemDef.actions = itemDef2.actions;
                break;
            case 10942:
                itemDef.name = "$10 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                break;
            case 10934:
                itemDef.name = "$20 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                break;
            case 10935:
                itemDef.name = "$50 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                break;
            case 10943:
                itemDef.name = "$100 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.inventoryModel = itemDef2.inventoryModel;
                itemDef.xOffset2d = itemDef2.xOffset2d;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.yOffset2d = itemDef2.yOffset2d;
                itemDef.zoom2d = itemDef2.zoom2d;
                break;
            case 995:
                itemDef.name = "Coins";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
               itemDef.actions[3] = "Add-to-pouch";
                break;
            case 17291:
                itemDef.name = "Blood necklace";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 20084:
                itemDef.name = "Golden Maul";
                break;
            case 6199:
                itemDef.name = "Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 15501:
                itemDef.name = "Legendary Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 6568: // To replace Transparent black with opaque black.
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 996:
            case 997:
            case 998:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            case 1003:
            case 1004:
                itemDef.name = "Coins";
                break;

            case 14017:
                itemDef.name = "Brackish blade";
                itemDef.zoom2d = 1488;
                itemDef.rotationY = 276;
                itemDef.rotationX = 1580;
                itemDef.yOffset2d = 1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.inventoryModel = 64593;
                itemDef.maleModel0 = 64704;
                itemDef.femaleEquip2 = 64704;
                break;

            case 15220:
                itemDef.name = "Berserker ring (i)";
                itemDef.zoom2d = 600;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1916;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = -15;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.inventoryModel = 7735; // if it doesn't work try 7735
                itemDef.maleModel0 = -1;
                // itemDefinition.maleArm = -1;
                itemDef.femaleModel0 = -1;
                // itemDefinition.femaleArm = -1;
                break;

            case 14019:
                itemDef.inventoryModel = 65262;
                itemDef.name = "Max Cape";
                itemDef.description = "A cape worn by those who've achieved 99 in all skills.";
                itemDef.zoom2d = 1385;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleModel0 = 65300;
                itemDef.femaleModel0 = 65322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 14020:
                itemDef.name = "Veteran hood";
                itemDef.description = "A hood worn by Chivalry's veterans.";
                itemDef.zoom2d = 760;
                itemDef.rotationY = 11;
                itemDef.rotationX = 81;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.inventoryModel = 65271;
                itemDef.maleModel0 = 65289;
                itemDef.femaleModel0 = 65314;
                break;
            case 14021:
                itemDef.inventoryModel = 65261;
                itemDef.name = "Veteran Cape";
                itemDef.description = "A cape worn by Chivalry's veterans.";
                itemDef.zoom2d = 760;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleModel0 = 65305;
                itemDef.femaleModel0 = 65318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 14022:
                itemDef.inventoryModel = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.";
                itemDef.zoom2d = 1385;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleModel0 = 65297;
                itemDef.femaleModel0 = 65297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 9666:
            case 11814:
            case 11816:
            case 11818:
            case 11820:
            case 11822:
            case 11824:
            case 11826:
            case 11828:
            case 11830:
            case 11832:
            case 11834:
            case 11836:
            case 11838:
            case 11840:
            case 11842:
            case 11844:
            case 11846:
            case 11848:
            case 11850:
            case 11852:
            case 11854:
            case 11856:
            case 11858:
            case 11860:
            case 11862:
            case 11864:
            case 11866:
            case 11868:
            case 11870:
            case 11874:
            case 11876:
            case 11878:
            case 11882:
            case 11886:
            case 11890:
            case 11894:
            case 11898:
            case 11902:
            case 11904:
            case 11906:
            case 11928:
            case 11930:
            case 11938:
            case 11942:
            case 11944:
            case 11946:
            case 14525:
            case 14527:
            case 14529:
            case 14531:
            case 19588:
            case 19592:
            case 19596:
            case 11908:
            case 11910:
            case 11912:
            case 11914:
            case 11916:
            case 11618:
            case 11920:
            case 11922:
            case 11960:
            case 11962:

            case 19586:
            case 19584:
            case 19590:
            case 19594:
            case 19598:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 14004:
                itemDef.name = "Staff of light";
                itemDef.inventoryModel = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 38310;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 38310;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 38310;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 38310;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 38310;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 38310;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 38310;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 38310;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 38310;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 38310;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 38310;
                itemDef.zoom2d = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.xOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.maleModel0 = 51795;
                itemDef.femaleModel0 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14005:
                itemDef.name = "Staff of light";
                itemDef.inventoryModel = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 432;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 432;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 432;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 432;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 432;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 432;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 432;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 432;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 432;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 432;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 432;
                itemDef.zoom2d = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.xOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.maleModel0 = 51795;
                itemDef.femaleModel0 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14006:
                itemDef.name = "Staff of light";
                itemDef.inventoryModel = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 24006;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 24006;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 24006;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 24006;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 24006;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 24006;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 24006;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 24006;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 24006;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 24006;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 24006;
                itemDef.zoom2d = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.xOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.maleModel0 = 51795;
                itemDef.femaleModel0 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14007:
                itemDef.name = "Staff of light";
                itemDef.inventoryModel = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 14285;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 14285;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 14285;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 14285;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 14285;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 14285;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 14285;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 14285;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 14285;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 14285;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 14285;
                itemDef.zoom2d = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.xOffset2d = 0;
                itemDef.xOffset2d = 0;
                itemDef.maleModel0 = 51795;
                itemDef.femaleModel0 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14003:
                itemDef.name = "Robin hood hat";
                itemDef.inventoryModel = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 30847;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 32895;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 30847;
                itemDef.zoom2d = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -5;
                itemDef.maleModel0 = 3378;
                itemDef.femaleModel0 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14001:
                itemDef.name = "Robin hood hat";
                itemDef.inventoryModel = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 10015;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 7730;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 7973;
                itemDef.zoom2d = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -5;
                itemDef.maleModel0 = 3378;
                itemDef.femaleModel0 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14002:
                itemDef.name = "Robin hood hat";
                itemDef.inventoryModel = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 35489;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 37774;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 35732;
                itemDef.zoom2d = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.xOffset2d = -3;
                itemDef.yOffset2d = -5;
                itemDef.maleModel0 = 3378;
                itemDef.femaleModel0 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14000:
                itemDef.name = "Robin hood hat";
                itemDef.inventoryModel = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 3745;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 3982;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 3988;
                itemDef.zoom2d = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffsetX = 1;
                itemDef.yOffset2d = -5;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleModel0 = 3378;
                itemDef.femaleModel0 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                break;

            /*case 19111:
			itemDef.name = "TokHaar-Kal";
			// itemDef.femaleOffset = 0;
			itemDef.value = 60000;
			itemDef.maleEquip1 = 62575;
			itemDef.femaleEquip1 = 62582;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 62592;
			itemDef.stackable = false;
			itemDef.description = "A cape made of ancient, enchanted obsidian.";
			itemDef.modelZoom = 2086;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffsetY = 0;
			itemDef.rotationY = 533;
			itemDef.rotationX = 333;
			break;
             */
            case 20000:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.inventoryModel = 53835;
                itemDef.name = "Steadfast boots";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = -7;
                itemDef.maleModel0 = 53327;
                itemDef.femaleModel0 = 53643;
                itemDef.description = "A pair of Steadfast boots.";
                break;

            case 20001:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.inventoryModel = 53828;
                itemDef.name = "Glaiven boots";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = -7;
                itemDef.femaleModel0 = 53309;
                itemDef.maleModel0 = 53309;
                itemDef.description = "A pair of Glaiven boots.";
                break;

            case 20002:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.description = "A pair of Ragefire boots.";
                itemDef.inventoryModel = 53897;
                itemDef.name = "Ragefire boots";
                itemDef.zoom2d = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.xOffset2d = 3;
                itemDef.yOffset2d = -7;
                itemDef.maleModel0 = 53330;
                itemDef.femaleModel0 = 53651;
                break;

            case 14018:
                itemDef.inventoryModel = 5324;
                itemDef.name = "Ornate katana";
                itemDef.zoom2d = 1520;
                itemDef.rotationY = 1570;
                itemDef.rotationX = 157;
                itemDef.xOffset2d = 5;
                itemDef.yOffset2d = 1;
                itemDef.value = 50000;
                itemDef.membersObject = true;
                itemDef.maleModel0 = 5324;
                itemDef.femaleModel0 = 5324;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Destroy";
                break;
            case 19476:
                itemDef.inventoryModel = 6555;
                itemDef.name = "Torva platebody";
                itemDef.description = "Torva platebody";
                itemDef.zoom2d = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.maleModel0 = 6556;
                itemDef.femaleModel0 = 6557;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Change";
                itemDef.actions[4] = "Drop";
                break;
            case 19478:
            	itemDef.inventoryModel = 6561;
        		itemDef.name = "Torva platelegs";
        		itemDef.description = "Torva platelegs.";
        		itemDef.zoom2d = 1550;
        		itemDef.rotationY = 344;
        		itemDef.rotationX = 186;
        		itemDef.xOffset2d = 5;
        		itemDef.yOffset2d = 11;
        		itemDef.maleModel0 = 6562;
                itemDef.femaleModel0 = 6563;
        		itemDef.groundActions = new String[5];
        		itemDef.groundActions[2] = "Take";
        		itemDef.actions = new String[5];
        		itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Change";
                itemDef.actions[4] = "Drop";
        	break;
            case 19477:
            	 itemDef.inventoryModel = 6558;
                 itemDef.name = "Torva full helm";
                 itemDef.description = "Torva full helm";
                 itemDef.zoom2d = 672;
                 itemDef.rotationY = 85;
                 itemDef.rotationX = 1867;
                 itemDef.xOffset2d = 0;
                 itemDef.yOffset2d = -3;
                 itemDef.maleModel0 = 6559;
                 itemDef.femaleModel0 = 62754;
                 itemDef.groundActions = new String[5];
                 itemDef.groundActions[2] = "Take";
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wear";
                 itemDef.actions[2] = "Change";
                 itemDef.actions[4] = "Drop";
                 itemDef.maleDialogue = 62729;
                 itemDef.femaleDialogue = 62729;
            	break;

            case 14008:
                itemDef.inventoryModel = 62714;
                itemDef.name = "Torva full helm";
                itemDef.description = "Torva full helm";
                itemDef.zoom2d = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -3;
                itemDef.maleModel0 = 62738;
                itemDef.femaleModel0 = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Change";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 14009:
                itemDef.inventoryModel = 62699;
                itemDef.name = "Torva platebody";
                itemDef.description = "Torva platebody";
                itemDef.zoom2d = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = 0;
                itemDef.maleModel0 = 62746;
                itemDef.femaleModel0 = 62762;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Change";
                itemDef.actions[4] = "Drop";
                break;

            case 14010:
                itemDef.inventoryModel = 62701;
                itemDef.name = "Torva platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.zoom2d = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.xOffset2d = 0;
                itemDef.yOffset2d = -5;
                itemDef.maleModel0 = 62743;
                itemDef.femaleModel0 = 62760;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Change";
                itemDef.actions[4] = "Drop";
                break;

            case 14011:
                itemDef.inventoryModel = 62693;
                itemDef.name = "Pernix cowl";
                itemDef.description = "Pernix cowl";
                itemDef.zoom2d = 800;
                itemDef.rotationY = 532;
                itemDef.rotationX = 14;
                itemDef.xOffset2d = -1;
                itemDef.yOffset2d = 1;
                itemDef.maleModel0 = 62739;
                itemDef.femaleModel0 = 62756;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62731;
                itemDef.femaleDialogue = 62727;
                itemDef.editedModelColor = new int[2];
                itemDef.newModelColor = new int[2];
                itemDef.editedModelColor[0] = 4550;
                itemDef.newModelColor[0] = 0;
                itemDef.editedModelColor[1] = 4540;
                itemDef.newModelColor[1] = 0;
                break;

            case 14012:
                itemDef.inventoryModel = 62709;
                itemDef.name = "Pernix body";
                itemDef.description = "Pernix body";
                itemDef.zoom2d = 1378;
                itemDef.rotationY = 485;
                itemDef.rotationX = 2042;
                itemDef.xOffset2d = -1;
                itemDef.yOffset2d = 7;
                itemDef.maleModel0 = 62744;
                itemDef.femaleModel0 = 62765;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14013:
                itemDef.inventoryModel = 62695;
                itemDef.name = "Pernix chaps";
                itemDef.description = "Pernix chaps";
                itemDef.zoom2d = 1740;
                itemDef.rotationY = 504;
                itemDef.rotationX = 0;
                itemDef.xOffset2d = 4;
                itemDef.yOffset2d = 3;
                itemDef.maleModel0 = 62741;
                itemDef.femaleModel0 = 62757;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 14014:
                itemDef.inventoryModel = 62710;
                itemDef.name = "Virtus mask";
                itemDef.description = "Virtus mask";
                itemDef.zoom2d = 928;
                itemDef.rotationY = 406;
                itemDef.rotationX = 2041;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = -5;
                itemDef.maleModel0 = 62736;
                itemDef.femaleModel0 = 62755;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62728;
                itemDef.femaleDialogue = 62728;
                break;

            case 14015:
                itemDef.inventoryModel = 62704;
                itemDef.name = "Virtus robe top";
                itemDef.description = "Virtus robe top";
                itemDef.zoom2d = 1122;
                itemDef.rotationY = 488;
                itemDef.rotationX = 3;
                itemDef.xOffset2d = 1;
                itemDef.yOffset2d = 0;
                itemDef.maleModel0 = 62748;
                itemDef.femaleModel0 = 62764;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14016:
                itemDef.inventoryModel = 62700;
                itemDef.name = "Virtus robe legs";
                itemDef.description = "Virtus robe legs";
                itemDef.zoom2d = 1740;
                itemDef.rotationY = 498;
                itemDef.rotationX = 2045;
                itemDef.xOffset2d = -1;
                itemDef.yOffset2d = 4;
                itemDef.maleModel0 = 62742;
                itemDef.femaleModel0 = 62758;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 9924:
            case 9923:
            case 9925:
            case 9921:
            case 9922:
                itemDef.editedModelColor = new int[]{1};
                break;
        }
        return itemDef;
    }
    

    public void imitate(ItemDefinition other) {
        name = other.name;
        description = other.description;
        editedModelColor = other.editedModelColor;
        newModelColor = other.newModelColor;
        sizeX = other.sizeX;
        sizeY = other.sizeY;
        sizeZ = other.sizeZ;
        rotationY = other.rotationY;
        rotationX = other.rotationX;
        xOffset2d = other.xOffset2d;
        yOffset2d = other.yOffset2d;
        modelOffsetX = other.modelOffsetX;
        zoom2d = other.zoom2d;
        inventoryModel = other.inventoryModel;
        actions = other.actions;
        maleModel0 = other.maleModel0;
        maleEquip2 = other.maleEquip2;
        maleEquip3 = other.maleEquip3;
        femaleModel0 = other.femaleModel0;
        femaleEquip2 = other.femaleEquip2;
        femaleEquip3 = other.femaleEquip3;
        maleDialogue = other.maleDialogue;
        maleDialogueModel = other.maleDialogueModel;
        femaleDialogue = other.femaleDialogue;
        femaleDialogue = other.femaleDialogueModel;
    }

    private void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                inventoryModel = stream.readUnsignedWord();
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readString();
            } else if (i == 4) {
                zoom2d = stream.readUnsignedWord();
            } else if (i == 5) {
                rotationY = stream.readUnsignedWord();
            } else if (i == 6) {
                rotationX = stream.readUnsignedWord();
            } else if (i == 7) {
                xOffset2d = stream.readUnsignedWord();
                if (xOffset2d > 32767) {
                    xOffset2d -= 0x10000;
                }
            } else if (i == 8) {
                yOffset2d = stream.readUnsignedWord();
                if (yOffset2d > 32767) {
                    yOffset2d -= 0x10000;
                }
            } else if (i == 10) {
                stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = stream.readUnsignedWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                maleModel0 = stream.readUnsignedWord();
                maleYOffset = stream.readSignedByte();
            } else if (i == 24) {
                maleEquip2 = stream.readUnsignedWord();
            } else if (i == 25) {
                femaleModel0 = stream.readUnsignedWord();
                femaleYOffset = stream.readSignedByte();
            } else if (i == 26) {
                femaleEquip2 = stream.readUnsignedWord();
            } else if (i >= 30 && i < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[i - 30] = stream.readString();
                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
                    groundActions[i - 30] = null;
                }
            } else if (i >= 35 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 35] = stream.readString();
                if (actions[i - 35].equalsIgnoreCase("null")) {
                    actions[i - 35] = null;
                }
            } else if (i == 40) {
                int j = stream.readUnsignedByte();
                editedModelColor = new int[j];
                newModelColor = new int[j];
                for (int k = 0; k < j; k++) {
                    editedModelColor[k] = stream.readUnsignedWord();
                    newModelColor[k] = stream.readUnsignedWord();
                }
            } else if (i == 78) {
                maleEquip3 = stream.readUnsignedWord();
            } else if (i == 79) {
                femaleEquip3 = stream.readUnsignedWord();
            } else if (i == 90) {
                maleDialogue = stream.readUnsignedWord();
            } else if (i == 91) {
                femaleDialogue = stream.readUnsignedWord();
            } else if (i == 92) {
                maleDialogueModel = stream.readUnsignedWord();
            } else if (i == 93) {
                femaleDialogueModel = stream.readUnsignedWord();
            } else if (i == 95) {
                modelOffsetX = stream.readUnsignedWord();
            } else if (i == 97) {
                certID = stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord();
                stackAmounts[i - 100] = stream.readUnsignedWord();
            } else if (i == 110) {
                sizeX = stream.readUnsignedWord();
            } else if (i == 111) {
                sizeY = stream.readUnsignedWord();
            } else if (i == 112) {
                sizeZ = stream.readUnsignedWord();
            } else if (i == 113) {
                shadow = stream.readSignedByte();
            } else if (i == 114) {
                lightness = stream.readSignedByte() * 5;
            } else if (i == 115) {
                team = stream.readUnsignedByte();
            } else if (i == 116) {
                lendID = stream.readUnsignedWord();
            } else if (i == 117) {
                lentItemID = stream.readUnsignedWord();
            }
        } while (true);
    }

    public static void setSettings() {
        try {
            prices = new int[22694];
            int index = 0;
            for (String line : Files.readAllLines(Paths.get(signlink.findcachedir() + "data/data.txt"), Charset.defaultCharset())) {
                prices[index] = Integer.parseInt(line);
                index++;
            }
            for (int i : UNTRADEABLE_ITEMS) {
                untradeableItems.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toNote() {
        ItemDefinition itemDef = forID(certTemplateID);
        inventoryModel = itemDef.inventoryModel;
        zoom2d = itemDef.zoom2d;
        rotationY = itemDef.rotationY;
        rotationX = itemDef.rotationX;
        modelOffsetX = itemDef.modelOffsetX;
        xOffset2d = itemDef.xOffset2d;
        yOffset2d = itemDef.yOffset2d;
        editedModelColor = itemDef.editedModelColor;
        newModelColor = itemDef.newModelColor;
        ItemDefinition itemDef_1 = forID(certID);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        char c = itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    private void toLend() {
        ItemDefinition itemDef = forID(lentItemID);
        actions = new String[5];
        inventoryModel = itemDef.inventoryModel;
        xOffset2d = itemDef.xOffset2d;
        rotationX = itemDef.rotationX;
        yOffset2d = itemDef.yOffset2d;
        zoom2d = itemDef.zoom2d;
        rotationY = itemDef.rotationY;
        modelOffsetX = itemDef.modelOffsetX;
        value = 0;
        ItemDefinition itemDef_1 = forID(lendID);
        maleDialogueModel = itemDef_1.maleDialogueModel;
        editedModelColor = itemDef_1.editedModelColor;
        maleEquip3 = itemDef_1.maleEquip3;
        maleEquip2 = itemDef_1.maleEquip2;
        femaleDialogueModel = itemDef_1.femaleDialogueModel;
        maleDialogue = itemDef_1.maleDialogue;
        groundActions = itemDef_1.groundActions;
        maleModel0 = itemDef_1.maleModel0;
        name = itemDef_1.name;
        femaleModel0 = itemDef_1.femaleModel0;
        membersObject = itemDef_1.membersObject;
        femaleDialogue = itemDef_1.femaleDialogue;
        femaleEquip2 = itemDef_1.femaleEquip2;
        femaleEquip3 = itemDef_1.femaleEquip3;
        newModelColor = itemDef_1.newModelColor;
        team = itemDef_1.team;
        if (itemDef_1.actions != null) {
            for (int i_33_ = 0; i_33_ < 4; i_33_++) {
                actions[i_33_] = itemDef_1.actions[i_33_];
            }
        }
        actions[4] = "Discard";
    }

    public static Sprite getSprite(int i, int j, int k, int zoom) {
        if (k == 0 && zoom != -1) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lendID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.zoom2d;
        if (zoom != -1 && zoom != 0) {
            k3 = (itemDef.zoom2d * 100) / zoom;
        }
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.xOffset2d, l3 + model.modelHeight / 2 + itemDef.yOffset2d, i4 + itemDef.yOffset2d);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] != 0) {
                    continue;
                }
                if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                }
            }

        }

        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] != 0) {
                        continue;
                    }
                    if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                    }
                }

            }

        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }

            }

        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lendID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        sprite2.maxWidth = itemDef.stackable ? 33 : 32;
        sprite2.maxHeight = j;
        return sprite2;
    }

    public static Sprite getSprite(int i, int j, int k) {
        if (k == 0) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }
            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lentItemID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.zoom2d;
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.xOffset2d, l3 + model.modelHeight / 2 + itemDef.yOffset2d, i4 + itemDef.yOffset2d);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] == 0) {
                    if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    }
                }
            }
        }
        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] == 0) {
                        if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        }
                    }
                }
            }
        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }
            }
        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lentItemID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
          spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (itemDef.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }
        sprite2.maxHeight = j;
        return sprite2;
    }

    public Model getItemModelFinalised(int amount) {
        if (stackIDs != null && amount > 1) {
            int stackId = -1;
            for (int k = 0; k < 10; k++) {
                if (amount >= stackAmounts[k] && stackAmounts[k] != 0) {
                    stackId = stackIDs[k];
                }
            }
            if (stackId != -1) {
                return forID(stackId).getItemModelFinalised(1);
            }
        }
        Model model = (Model) modelCache.get(id);
        if (model != null) {
            return model;
        }
        model = Model.fetchModel(inventoryModel);
        if (model == null) {
            return null;
        }
        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
            model.scaleT(sizeX, sizeZ, sizeY);
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
        model.rendersWithinOneTile = true;
        if (id != 5572 && id != 5573 && id != 640 && id != 650 && id != 630) {
        modelCache.put(model, id);
        }
        return model;
    }

    public Model getItemModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).getItemModel(1);
            }
        }
        Model model = Model.fetchModel(inventoryModel);
        if (model == null) {
            return null;
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        return model;
    }

    public static final int[] UNTRADEABLE_ITEMS
            = {13661, 13262,
            6529, 6950, 1464, 2996, 2677, 2678, 2679, 2680, 2682,
            2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690,
            6570, 12158, 12159, 12160, 12163, 12161, 12162,
            19143, 19149, 19146, 19157, 19162, 19152, 4155,
            8850, 10551, 8839, 8840, 8842, 11663, 11664,
            11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
            8848, 8849, 8850, 10551, 7462, 7461, 7460,
            7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839,
            8840, 8842, 11663, 11664, 11665, 10499, 9748,
            9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
            9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
            9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770,
            9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806,
            9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812,
            9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
            9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
            9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
            9950, 12169, 12170, 12171, 20671, 14641, 14642,
            6188, 10954, 10956, 10958,
            3057, 3058, 3059, 3060, 3061,
            7594, 7592, 7593, 7595, 7596,
            14076, 14077, 14081,
            10840, 10836, 6858, 6859, 10837, 10838, 10839,
            9925, 9924, 9923, 9922, 9921,
            4084, 4565, 20046, 20044, 20045,
            1050, 14595, 14603, 14602, 14605, 11789,
            19708, 19706, 19707,
            4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
            4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
            4926, 4968, 4994, 4980, 4986, 4992, 4998,
            18778, 18779, 18780, 18781,
            13450, 13444, 13405, 15502,
            10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
            20747,
            18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017,
            757, 8851,
            13855, 13848, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
            19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335
    };

    public ItemDefinition() {
        id = -1;
    }

    public byte femaleYOffset;
    public int value;
    public int[] editedModelColor;
    public int id;
    public static MemCache spriteCache = new MemCache(100);
    public static MemCache modelCache = new MemCache(50);
    public int[] newModelColor;
    public boolean membersObject;
    public int femaleEquip3;
    public int certTemplateID;
    public int femaleEquip2;
    public int maleModel0;
    public int maleDialogueModel;
    public int sizeX;
    public String groundActions[];
    public int xOffset2d;
    public String name;
    public static ItemDefinition[] cache;
    public int femaleDialogueModel;
    public int inventoryModel;
    public int maleDialogue;
    public boolean stackable;
    public String description;
    public int certID;
    public static int cacheIndex;
    public int zoom2d;
    public static Stream stream;
    public int lightness;
    public int maleEquip3;
    public int maleEquip2;
    public String actions[];
    public int rotationY;
    public int sizeZ;
    public int sizeY;
    public int[] stackIDs;
    public int yOffset2d;
    public static int[] streamIndices;
    public int shadow;
    public int femaleDialogue;
    public int rotationX;
    public int femaleModel0;
    public int[] stackAmounts;
    public int team;
    public static int totalItems;
    public int modelOffsetX;
    public byte maleYOffset;
    public byte maleXOffset;
    public int lendID;
    public int lentItemID;
    public boolean untradeable;
}
