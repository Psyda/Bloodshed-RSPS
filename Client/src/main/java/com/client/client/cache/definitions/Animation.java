package com.client.client.cache.definitions;

import com.client.client.CacheArchive;
import com.client.client.FrameReader;
import com.client.client.Stream;

public final class Animation {

	public static void unpackConfig(CacheArchive streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
		int length = stream.readUnsignedWord();
		if (anims == null)
			anims = new Animation[length+2000];
		for (int j = 0; j < length; j++) {
			if (anims[j] == null)
				anims[j] = new Animation();
			anims[j].readValues(stream);
			switch (j) {
			case 733:
				anims[j].rightHandItem = -1;

				break;
			case 7608:
				anims[j].frameCount = 30;
				anims[j].frameIDs = new int[] { 124584076, 124583999, 124584059, 124583984, 124584125, 124584056,
						124584041, 124583969, 124584020, 124584036, 124584062, 124584025, 124584110, 124584094,
						124584043, 124583939, 124583952, 124583968, 124584006, 124584063, 124584097, 124584106,
						124583978, 124583967, 124583947, 124584137, 124584085, 124584072, 124584141, 124583986 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;
			case 7609:
				anims[j].frameCount = 12;
				anims[j].frameIDs = new int[] { 124584088, 124584071, 124584129, 124584131, 124584078, 124584103,
						124584069, 124584127, 124583960, 124584009, 124583949, 124584090 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 3, 3, 2, 2, 2, 3, 3, 2, 2, 2, 2, 3 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 4;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;

			case 7610:
				anims[j].frameCount = 30;
				anims[j].frameIDs = new int[] { 124583995, 124584011, 124584042, 124583996, 124583946, 124584026,
						124584067, 124583989, 124583948, 124584014, 124584100, 124584114, 124583972, 124583993,
						124584139, 124584136, 124584121, 124584038, 124584031, 124584074, 124584117, 124584003,
						124584081, 124584064, 124584045, 124584128, 124583940, 124584116, 124584048, 124583994 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;

			case 7611:
				anims[j].frameIDs = new int[] { 124584061, 124584035, 124584024, 124583938, 124583970, 124583977,
						124584083, 124584005, 124584075, 124584052, 124584032, 124584111, 124584077, 124584033,
						124584001, 124584068, 124584039, 124583950, 124584050, 124583987, 124584091, 124584109,
						124583956, 124583998, 124583962, 124584028, 124584034, 124584065, 124583966, 124583988,
						124584049, 124584102, 124584104, 124583964, 124583954, 124584060, 124584016, 124584040,
						124584099, 124584093, 124584029, 124584021, 124584000, 124584030, 124584112, 124583980,
						124583974, 124584086, 124584018, 124583957, 124583971, 124583958, 124583965, 124584120,
						124584037, 124584080, 124584070, 124584123, 124583975, 124584079, 124584058, 124584057,
						124584101, 124584087, 124584113, 124583959, 124584095, 124584126, 124584130, 124584082,
						124584047, 124584092 };
				anims[j].delays = new int[] { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3,
						3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
				anims[j].forcedPriority = 8;
				anims[j].frameCount = 72;
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;

			case 7612:
				anims[j].frameIDs = new int[] { 124584004, 124584134, 124583973, 124583944, 124584119, 124584013,
						124584105, 124583937, 124584124, 124584046, 124583951, 124583981, 124583942, 124584107,
						124584053, 124583997, 124584023, 124584115, 124584089, 124584054, 124584098, 124583992,
						124584015, 124584066, 124583963, 124584073, 124584007, 124584051, 124584138, 124584084 };
				anims[j].delays = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2 };
				anims[j].forcedPriority = 6;
				anims[j].frameCount = 30;
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;

			case 7613:
				anims[j].frameIDs = new int[] { 124584019, 124584140, 124584132, 124583991, 124583941, 124583936,
						124583953, 124583983, 124584044, 124584055, 124584122, 124584012, 124584022, 124583976,
						124583955, 124583985, 124584096, 124583979, 124584135, 124584118, 124584027, 124583990,
						124584010, 124584002, 124583945, 124584133, 124583961, 124584017, 124583982, 124583943 };
				anims[j].delays = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2 };
				anims[j].forcedPriority = 10;
				anims[j].frameCount = 30;
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 1;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				break;

			// Inferno boss
		case 7562:
			anims[j].frameIDs = new int[] { 124911715, 124911807, 124911664, 124911735, 124911781, 124911737,
					124911728, 124911756, 124911805, 124911642, 124911722, 124911822, 124911703, 124911789,
					124911705, 124911778, 124911747, 124911818, 124911793, 124911660, 124911713, 124911785,
					124911698, 124911673, 124911796, 124911665, 124911757, 124911691, 124911813, 124911780,
					124911736, 124911714, 124911677, 124911690, 124911708, 124911671, 124911668, 124911712,
					124911819, 124911627, 124911692, 124911689, 124911810, 124911634, 124911710, 124911790,
					124911762, 124911676, 124911758, 124911700 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 50;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7563:
			anims[j].frameIDs = new int[] { 124911654, 124911729, 124911733, 124911750, 124911630, 124911725,
					124911616, 124911738, 124911681, 124911804, 124911622, 124911782, 124911658, 124911644,
					124911812, 124911724, 124911821, 124911709, 124911631, 124911806, 124911823, 124911768,
					124911727, 124911638, 124911817, 124911783, 124911751, 124911662, 124911752, 124911744,
					124911794, 124911619, 124911788, 124911637, 124911773, 124911624, 124911711, 124911639,
					124911645, 124911625, 124911717, 124911766, 124911659, 124911798, 124911652, 124911765,
					124911784, 124911730, 124911618, 124911706, 124911669, 124911820, 124911769, 124911734,
					124911743, 124911775, 124911755, 124911650, 124911764, 124911795, 124911684, 124911741,
					124911753, 124911648, 124911745, 124911663, 124911802, 124911646, 124911693, 124911653 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 70;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7564:
			anims[j].frameIDs = new int[] { 124911748, 124911770, 124911656, 124911696, 124911635, 124911682,
					124911704, 124911687, 124911672, 124911797, 124911719, 124911628, 124911776, 124911629,
					124911759, 124911633, 124911617, 124911754, 124911667, 124911695, 124911649, 124911702,
					124911723, 124911739, 124911803, 124911815, 124911686, 124911800 };
			anims[j].delays = new int[] { 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 4, 4 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 28;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7565:
			anims[j].frameIDs = new int[] { 124911641, 124911651, 124911740, 124911799, 124911814, 124911636,
					124911763, 124911761, 124911767, 124911678, 124911720, 124911620, 124911786, 124911716,
					124911801, 124911779, 124911824, 124911742, 124911670, 124911791, 124911774, 124911688,
					124911726, 124911643, 124911808, 124911626, 124911666, 124911674, 124911731, 124911749 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 6;
			anims[j].frameCount = 30;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7566:
			anims[j].frameIDs = new int[] { 124911732, 124911721, 124911623, 124911760, 124911746, 124911811,
					124911640, 124911675, 124911683, 124911621, 124911707, 124911771, 124911685, 124911680,
					124911809, 124911679, 124911777, 124911699, 124911657, 124911701, 124911661, 124911772,
					124911632, 124911647, 124911718, 124911697, 124911694, 124911816, 124911655, 124911787,
					124911792 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3,
					3, 3, 3, 3, 3, 2 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 31;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;
			// Jal-Xil
		case 7602:
			anims[j].frameIDs = new int[] { 125042725, 125042740, 125042735, 125042709, 125042778, 125042700,
					125042731, 125042771, 125042751, 125042747, 125042766, 125042763, 125042715, 125042690,
					125042782, 125042796, 125042723, 125042752 };
			anims[j].delays = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
			anims[j].forcedPriority = 5;
			anims[j].frameCount = 18;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7603:
			anims[j].frameIDs = new int[] { 125042746, 125042726, 125042739, 125042757, 125042711, 125042758,
					125042774, 125042738, 125042764, 125042765, 125042791, 125042795, 125042755, 125042701,
					125042753, 125042688, 125042716, 125042718, 125042760, 125042744 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 5;
			anims[j].frameCount = 20;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7604:
			anims[j].frameIDs = new int[] { 125042698, 125042806, 125042779, 125042792, 125042708, 125042734,
					125042743, 125042737, 125042794, 125042780, 125042720, 125042745, 125042781, 125042727,
					125042802, 125042689, 125042756, 125042729, 125042710, 125042767, 125042693 };
			anims[j].delays = new int[] { 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2 };
			anims[j].forcedPriority = 6;
			anims[j].frameCount = 21;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7605:
			anims[j].frameIDs = new int[] { 125042733, 125042695, 125042799, 125042697, 125042768, 125042717,
					125042705, 125042712, 125042801, 125042807, 125042728, 125042777, 125042741, 125042812,
					125042776, 125042803, 125042788, 125042775, 125042805, 125042793, 125042696, 125042699,
					125042714, 125042732, 125042691, 125042692, 125042702, 125042784, 125042808, 125042800,
					125042724 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3,
					3, 3, 3, 3, 3, 2 };
			anims[j].forcedPriority = 6;
			anims[j].frameCount = 31;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7606:
			anims[j].frameIDs = new int[] { 125042786, 125042790, 125042704, 125042809, 125042754, 125042730,
					125042798, 125042772, 125042761, 125042770, 125042742, 125042719, 125042769, 125042736,
					125042804, 125042707, 125042706, 125042773, 125042759, 125042787, 125042749, 125042721,
					125042722, 125042694, 125042762, 125042789, 125042810, 125042797, 125042785, 125042783 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 30;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7607:
			anims[j].frameIDs = new int[] { 125042713, 125042750, 125042811, 125042703, 125042748, 125042703,
					125042811, 125042750 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 9, 3, 3, 3 };
			anims[j].forcedPriority = 5;
			anims[j].frameCount = 8;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		// Glyph
		case 7567:
			anims[j].frameIDs = new int[] { 125304882, 125304904, 125304886, 125304895, 125304858, 125304837,
					125304843, 125304862, 125304877, 125304859, 125304841, 125304879, 125304907, 125304880,
					125304860, 125304864, 125304848, 125304865, 125304884, 125304867 };
			anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 20;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7568:
			anims[j].frameIDs = new int[] { 125304883, 125304910, 125304891, 125304872, 125304905, 125304868,
					125304852, 125304846, 125304898, 125304889, 125304833, 125304900, 125304847, 125304873,
					125304857, 125304871, 125304856, 125304903, 125304850, 125304874 };

			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 6;
			anims[j].frameCount = 20;
			anims[j].priority = 2;
			anims[j].resetWhenWalk = 2;
			break;

		case 7569:
			anims[j].frameIDs = new int[] { 125304854, 125304845, 125304851, 125304842, 125304863, 125304878,
					125304908, 125304885, 125304912, 125304835, 125304909, 125304834, 125304836, 125304839,
					125304881, 125304892, 125304838, 125304840, 125304897, 125304896, 125304876, 125304869,
					125304899, 125304902, 125304906, 125304901, 125304853, 125304855, 125304849, 125304890,
					125304844, 125304870, 125304887, 125304888, 125304875, 125304894, 125304832, 125304913,
					125304911, 125304861, 125304893, 125304866 };

			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 42;
			anims[j].frameStep = 1;

			break;
			
		case 7590:
			anims[j].frameIDs = new int[] { 124977210, 124977355, 124977255, 124977270, 124977240, 124977287,
					124977156, 124977211, 124977219, 124977165, 124977268, 124977184, 124977317, 124977166,
					124977336, 124977337, 124977357, 124977262, 124977194, 124977221, 124977290, 124977344,
					124977264, 124977178 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 9;
			anims[j].frameCount = 24;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7591:
			anims[j].frameIDs = new int[] { 124977342, 124977318, 124977231, 124977288, 124977320, 124977323,
					124977239, 124977309, 124977356, 124977249, 124977321, 124977159, 124977192, 124977325,
					124977204, 124977162, 124977208, 124977292 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 4;
			anims[j].frameCount = 18;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7592:
			anims[j].priority = 2;
			anims[j].frameIDs = new int[] { 124977245, 124977324, 124977235, 124977277, 124977213, 124977226,
					124977188, 124977312, 124977157, 124977176, 124977214, 124977311, 124977215, 124977266,
					124977332, 124977195, 124977351, 124977341, 124977348, 124977275, 124977341, 124977283,
					124977351, 124977209, 124977195, 124977223, 124977202, 124977174, 124977250, 124977164,
					124977224, 124977328, 124977158, 124977310, 124977170, 124977237, 124977238, 124977241,
					124977301, 124977280, 124977185, 124977343, 124977196, 124977339, 124977331, 124977233,
					124977353, 124977291, 124977216, 124977261 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4,
					4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 50;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 2;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;

		case 7593:
			anims[j].frameIDs = new int[] { 124977218, 124977347, 124977234, 124977298, 124977282, 124977326,
					124977199, 124977203, 124977201, 124977171, 124977180, 124977183, 124977278, 124977340,
					124977248, 124977181, 124977352, 124977217 };
			anims[j].delays = new int[] { 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].priority = 2;
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 18;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 2;
			anims[j].delayType = 2;
			break;

		case 7594:
			anims[j].frameIDs = new int[] { 124977256, 124977179, 124977330, 124977236, 124977152, 124977300,
					124977190, 124977307, 124977222, 124977177, 124977333, 124977267, 124977173, 124977200,
					124977349, 124977229, 124977269, 124977182, 124977304, 124977186, 124977299, 124977308,
					124977244, 124977253, 124977272, 124977225, 124977296, 124977154, 124977293, 124977281,
					124977319, 124977228, 124977345, 124977350, 124977335, 124977276, 124977212, 124977297,
					124977168, 124977271, 124977232, 124977243, 124977265, 124977220, 124977322, 124977346,
					124977285, 124977172, 124977155, 124977160, 124977316 };
			anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3 };
			anims[j].forcedPriority = 10;
			anims[j].frameCount = 51;
			anims[j].loopDelay = -1;
			anims[j].animationFlowControl = null;
			anims[j].oneSquareAnimation = false;
			anims[j].leftHandItem = -1;
			anims[j].rightHandItem = -1;
			anims[j].frameStep = 1;
			anims[j].resetWhenWalk = 0;
			anims[j].priority = 0;
			anims[j].delayType = 2;
			break;
}
			anims[ 15460] = new Animation();
			anims[ 15460].frameCount = 14;
			anims[ 15460].frameIDs = new int[] {852178, 852085, 852210, 851982, 852014, 852101, 852183, 852219, 852202, 852009, 852240, 852203, 852044, 852222};
			anims[ 15460].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15460].delays = new int[] {6, 7, 5, 5, 4, 3, 4, 2, 4, 3, 4, 3, 5, 5};
			anims[ 15460].loopDelay = -1;
			anims[ 15460].oneSquareAnimation = false;
			anims[ 15460].forcedPriority = 5;
			anims[ 15460].leftHandItem = 65535;
			anims[ 15460].rightHandItem = 65535;
			anims[ 15460].frameStep = 99;
			anims[ 15460].resetWhenWalk = 1;
			anims[ 15460].priority = 1;
			anims[ 15460].delayType = 1;

			anims[ 15461] = new Animation();
			anims[ 15461].frameCount = 50;
			anims[ 15461].frameIDs = new int[] {852243, 852077, 852011, 852123, 852094, 852226, 852199, 852168, 851980, 852115, 852155, 852217, 852201, 852229, 852150, 852231, 852241, 852188, 852135, 852068, 852157, 852098, 852039, 852020, 852136, 852185, 852147, 852212, 852134, 851981, 852117, 852131, 852220, 852167, 852074, 852215, 852043, 852049, 852198, 852015, 851985, 852213, 852067, 852130, 852081, 851975, 852065, 852095, 852223, 852148};
			anims[ 15461].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15461].delays = new int[] {5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 3, 3, 3, 4, 2, 2, 2, 1, 1, 2, 3, 3, 2, 2, 2, 3, 2, 1, 1, 3, 1, 1, 1, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4};
			anims[ 15461].loopDelay = 1;
			anims[ 15461].oneSquareAnimation = false;
			anims[ 15461].forcedPriority = 6;
			anims[ 15461].leftHandItem = 65535;
			anims[ 15461].rightHandItem = 65535;
			anims[ 15461].frameStep = 99;
			anims[ 15461].resetWhenWalk = 1;
			anims[ 15461].priority = 1;
			anims[ 15461].delayType = 2;

			anims[ 15462] = new Animation();
			anims[ 15462].frameCount = 32;
			anims[ 15462].frameIDs = new int[] {95879199, 95879172, 95879191, 95879180, 95879192, 95879176, 95879183, 95879182, 95879169, 95879171, 95879173, 95879184, 95879185, 95879186, 95879193, 95879168, 95879174, 95879181, 95879187, 95879177, 95879189, 95879190, 95879170, 95879195, 95879198, 95879175, 95879194, 95879196, 95879178, 95879188, 95879179, 95879197};
			anims[ 15462].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15462].delays = new int[] {4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
			anims[ 15462].loopDelay = -1;
			anims[ 15462].oneSquareAnimation = false;
			anims[ 15462].forcedPriority = 5;
			anims[ 15462].leftHandItem = -1;
			anims[ 15462].rightHandItem = -1;
			anims[ 15462].frameStep = 99;
			anims[ 15462].resetWhenWalk = 0;
			anims[ 15462].priority = 0;
			anims[ 15462].delayType = 2;

			anims[ 15463] = new Animation();
			anims[ 15463].frameCount = 165;
			anims[ 15463].frameIDs = new int[] {206046240, 206046222, 206046263, 206046241, 206046231, 206046246, 206046216, 206046249, 206046247, 206046250, 206046270, 206046270, 206046270, 206046270, 206046250, 206046272, 206046253, 206046185, 206046223, 206046269, 206046199, 206046225, 206046213, 206046240, 206046240, 206046197, 206046237, 206046232, 206046264, 206046227, 206046243, 206046244, 206046275, 206046262, 206046215, 206046276, 206046257, 206046273, 206046271, 206046186, 206046236, 206046267, 206046278, 206046268, 206046208, 206046221, 206046228, 206046222, 206046263, 206046241, 206046231, 206046246, 206046216, 206046249, 206046247, 206046250, 206046270, 206046270, 206046270, 206046270, 206046250, 206046272, 206046253, 206046185, 206046223, 206046269, 206046199, 206046225, 206046213, 206046240, 206046240, 206046222, 206046263, 206046241, 206046231, 206046246, 206046216, 206046249, 206046247, 206046250, 206046270, 206046270, 206046270, 206046270, 206046250, 206046272, 206046253, 206046185, 206046223, 206046269, 206046199, 206046225, 206046213, 206046240, 206046240, 206046197, 206046237, 206046232, 206046264, 206046227, 206046243, 206046244, 206046275, 206046262, 206046215, 206046276, 206046240, 206046235, 206046214, 206046224, 206046204, 206046187, 206046194, 206046210, 206046205, 206046280, 206046248, 206046260, 206046201, 206046233, 206046191, 206046206, 206046206, 206046198, 206046196, 206046192, 206046203, 206046279, 206046261, 206046212, 206046255, 206046277, 206046211, 206046229, 206046234, 206046230, 206046252, 206046274, 206046217, 206046218, 206046184, 206046254, 206046265, 206046245, 206046200, 206046202, 206046256, 206046220, 206046242, 206046193, 206046238, 206046183, 206046251, 206046207, 206046219, 206046189, 206046188, 206046239, 206046190, 206046258, 206046209, 206046226, 206046259, 206046195, 206046266};
			anims[ 15463].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15463].delays = new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6, 6, 8, 8, 8, 12, 12, 12, 12, 12, 4, 4, 4, 4, 4, 4, 4, 4, 4, 14, 14, 14, 14, 14, 14, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
			anims[ 15463].loopDelay = -1;
			anims[ 15463].oneSquareAnimation = false;
			anims[ 15463].forcedPriority = 5;
			anims[ 15463].leftHandItem = -1;
			anims[ 15463].rightHandItem = -1;
			anims[ 15463].frameStep = 99;
			anims[ 15463].resetWhenWalk = 0;
			anims[ 15463].priority = 0;
			anims[ 15463].delayType = 2;

			anims[ 15464] = new Animation();
			anims[ 15464].frameCount = 24;
			anims[ 15464].frameIDs = new int[] {250609690, 250609706, 250609808, 250609726, 250609712, 250609765, 250609680, 250609705, 250609738, 250609709, 250609687, 250609748, 250609722, 250609766, 250609739, 250609747, 250609741, 250609771, 250609778, 250609780, 250609720, 250609727, 250609784, 250609814};
			anims[ 15464].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15464].delays = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
			anims[ 15464].loopDelay = -1;
			anims[ 15464].oneSquareAnimation = false;
			anims[ 15464].forcedPriority = 5;
			anims[ 15464].leftHandItem = -1;
			anims[ 15464].rightHandItem = -1;
			anims[ 15464].frameStep = 99;
			anims[ 15464].resetWhenWalk = 0;
			anims[ 15464].priority = 0;
			anims[ 15464].delayType = 2;

			anims[ 15465] = new Animation();
			anims[ 15465].frameCount = 28;
			anims[ 15465].frameIDs = new int[] {250609792, 250609730, 250609769, 250609806, 250609801, 250609760, 250609787, 250609749, 250609734, 250609696, 250609689, 250609685, 250609700, 250609764, 250609671, 250609731, 250609681, 250609774, 250609821, 250609677, 250609711, 250609742, 250609758, 250609703, 250609721, 250609804, 250609666, 250609737};
			anims[ 15465].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15465].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15465].loopDelay = -1;
			anims[ 15465].oneSquareAnimation = false;
			anims[ 15465].forcedPriority = 5;
			anims[ 15465].leftHandItem = -1;
			anims[ 15465].rightHandItem = -1;
			anims[ 15465].frameStep = 99;
			anims[ 15465].resetWhenWalk = 0;
			anims[ 15465].priority = 0;
			anims[ 15465].delayType = 2;

			anims[ 15466] = new Animation();
			anims[ 15466].frameCount = 20;
			anims[ 15466].frameIDs = new int[] {250609789, 250609819, 250609762, 250609728, 250609793, 250609818, 250609718, 250609704, 250609740, 250609672, 250609756, 250609686, 250609719, 250609753, 250609682, 250609674, 250609668, 250609708, 250609678, 250609746};
			anims[ 15466].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15466].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15466].loopDelay = -1;
			anims[ 15466].oneSquareAnimation = false;
			anims[ 15466].forcedPriority = 6;
			anims[ 15466].leftHandItem = -1;
			anims[ 15466].rightHandItem = -1;
			anims[ 15466].frameStep = 99;
			anims[ 15466].resetWhenWalk = 0;
			anims[ 15466].priority = 0;
			anims[ 15466].delayType = 2;

			anims[ 15467] = new Animation();
			anims[ 15467].frameCount = 20;
			anims[ 15467].frameIDs = new int[] {250872047, 250871849, 250871863, 250871904, 250872019, 250871860, 250872043, 250871973, 250871966, 250871933, 250871930, 250872040, 250871836, 250871993, 250872007, 250871988, 250871893, 250871869, 250871825, 250871996};
			anims[ 15467].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15467].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15467].loopDelay = -1;
			anims[ 15467].oneSquareAnimation = false;
			anims[ 15467].forcedPriority = 5;
			anims[ 15467].leftHandItem = -1;
			anims[ 15467].rightHandItem = -1;
			anims[ 15467].frameStep = 99;
			anims[ 15467].resetWhenWalk = 0;
			anims[ 15467].priority = 0;
			anims[ 15467].delayType = 2;

			anims[ 15468] = new Animation();
			anims[ 15468].frameCount = 23;
			anims[ 15468].frameIDs = new int[] {250609744, 250609798, 250609698, 250609675, 250609669, 250609750, 250609694, 250609807, 250609803, 250609811, 250609810, 250609714, 250609776, 250609800, 250609710, 250609665, 250609713, 250609691, 250609781, 250609743, 250609797, 250609777, 250609794};
			anims[ 15468].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15468].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15468].loopDelay = -1;
			anims[ 15468].oneSquareAnimation = false;
			anims[ 15468].forcedPriority = 5;
			anims[ 15468].leftHandItem = -1;
			anims[ 15468].rightHandItem = -1;
			anims[ 15468].frameStep = 99;
			anims[ 15468].resetWhenWalk = 0;
			anims[ 15468].priority = 0;
			anims[ 15468].delayType = 2;

			anims[ 15469] = new Animation();
			anims[ 15469].frameCount = 23;
			anims[ 15469].frameIDs = new int[] {250871854, 250872008, 250871937, 250872010, 250871967, 250871859, 250871960, 250871812, 250871911, 250872002, 250872023, 250872055, 250871944, 250872036, 250871917, 250872012, 250872022, 250872025, 250871951, 250871931, 250872014, 250872026, 250871884};
			anims[ 15469].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15469].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15469].loopDelay = -1;
			anims[ 15469].oneSquareAnimation = false;
			anims[ 15469].forcedPriority = 5;
			anims[ 15469].leftHandItem = -1;
			anims[ 15469].rightHandItem = -1;
			anims[ 15469].frameStep = 99;
			anims[ 15469].resetWhenWalk = 0;
			anims[ 15469].priority = 0;
			anims[ 15469].delayType = 2;

			anims[ 15470] = new Animation();
			anims[ 15470].frameCount = 19;
			anims[ 15470].frameIDs = new int[] {250609667, 250609763, 250609755, 250609779, 250609745, 250609799, 250609770, 250609802, 250609767, 250609702, 250609785, 250609679, 250609790, 250609816, 250609717, 250609815, 250609809, 250609759, 250609725};
			anims[ 15470].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15470].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15470].loopDelay = -1;
			anims[ 15470].oneSquareAnimation = false;
			anims[ 15470].forcedPriority = 7;
			anims[ 15470].leftHandItem = -1;
			anims[ 15470].rightHandItem = -1;
			anims[ 15470].frameStep = 99;
			anims[ 15470].resetWhenWalk = 0;
			anims[ 15470].priority = 0;
			anims[ 15470].delayType = 2;

			anims[ 15471] = new Animation();
			anims[ 15471].frameCount = 19;
			anims[ 15471].frameIDs = new int[] {250872035, 250871817, 250871984, 250871875, 250871989, 250871955, 250871934, 250871919, 250871818, 250871916, 250872038, 250871838, 250871888, 250871902, 250871927, 250871950, 250871898, 250871814, 250871843};
			anims[ 15471].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15471].delays = new int[] {15, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15471].loopDelay = -1;
			anims[ 15471].oneSquareAnimation = false;
			anims[ 15471].forcedPriority = 5;
			anims[ 15471].leftHandItem = -1;
			anims[ 15471].rightHandItem = -1;
			anims[ 15471].frameStep = 99;
			anims[ 15471].resetWhenWalk = 0;
			anims[ 15471].priority = 0;
			anims[ 15471].delayType = 2;

			anims[ 15472] = new Animation();
			anims[ 15472].frameCount = 19;
			anims[ 15472].frameIDs = new int[] {250871956, 250872030, 250871835, 250872013, 250871862, 250872020, 250871828, 250871929, 250871999, 250871997, 250872018, 250872006, 250871899, 250871848, 250872000, 250872051, 250871948, 250871816, 250871827};
			anims[ 15472].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15472].delays = new int[] {15, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15472].loopDelay = -1;
			anims[ 15472].oneSquareAnimation = false;
			anims[ 15472].forcedPriority = 5;
			anims[ 15472].leftHandItem = -1;
			anims[ 15472].rightHandItem = -1;
			anims[ 15472].frameStep = 99;
			anims[ 15472].resetWhenWalk = 0;
			anims[ 15472].priority = 0;
			anims[ 15472].delayType = 2;

			anims[ 15473] = new Animation();
			anims[ 15473].frameCount = 43;
			anims[ 15473].frameIDs = new int[] {250609813, 250609795, 250609699, 250609754, 250609736, 250609732, 250609775, 250609688, 250609664, 250609683, 250609786, 250609707, 250609724, 250609768, 250609761, 250609697, 250609782, 250609796, 250609757, 250609805, 250609791, 250609715, 250609735, 250609701, 250609693, 250609817, 250609692, 250609788, 250609783, 250609670, 250609751, 250609752, 250609723, 250609695, 250609684, 250609716, 250609673, 250609812, 250609729, 250609772, 250609676, 250609820, 250609773};
			anims[ 15473].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15473].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3};
			anims[ 15473].loopDelay = 1;
			anims[ 15473].oneSquareAnimation = false;
			anims[ 15473].forcedPriority = 10;
			anims[ 15473].leftHandItem = -1;
			anims[ 15473].rightHandItem = -1;
			anims[ 15473].frameStep = 99;
			anims[ 15473].resetWhenWalk = 0;
			anims[ 15473].priority = 0;
			anims[ 15473].delayType = 2;

			anims[ 15474] = new Animation();
			anims[ 15474].frameCount = 29;
			anims[ 15474].frameIDs = new int[] {250871840, 250871845, 250871842, 250871968, 250871832, 250871976, 250872056, 250871889, 250871942, 250872029, 250872049, 250871879, 250871975, 250871811, 250871857, 250871965, 250871887, 250871946, 250872005, 250871982, 250871865, 250871959, 250872054, 250871876, 250872048, 250871949, 250872034, 250871834, 250871841};
			anims[ 15474].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15474].delays = new int[] {15, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15474].loopDelay = -1;
			anims[ 15474].oneSquareAnimation = false;
			anims[ 15474].forcedPriority = 5;
			anims[ 15474].leftHandItem = -1;
			anims[ 15474].rightHandItem = -1;
			anims[ 15474].frameStep = 99;
			anims[ 15474].resetWhenWalk = 0;
			anims[ 15474].priority = 0;
			anims[ 15474].delayType = 2;

			anims[ 15475] = new Animation();
			anims[ 15475].frameCount = 24;
			anims[ 15475].frameIDs = new int[] {250675320, 250675227, 250675235, 250675318, 250675289, 250675274, 250675214, 250675239, 250675253, 250675220, 250675243, 250675278, 250675225, 250675329, 250675231, 250675327, 250675228, 250675209, 250675298, 250675323, 250675269, 250675207, 250675248, 250675255};
			anims[ 15475].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15475].delays = new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
			anims[ 15475].loopDelay = -1;
			anims[ 15475].oneSquareAnimation = false;
			anims[ 15475].forcedPriority = 5;
			anims[ 15475].leftHandItem = -1;
			anims[ 15475].rightHandItem = -1;
			anims[ 15475].frameStep = 99;
			anims[ 15475].resetWhenWalk = 0;
			anims[ 15475].priority = 0;
			anims[ 15475].delayType = 2;

			anims[ 15476] = new Animation();
			anims[ 15476].frameCount = 10;
			anims[ 15476].frameIDs = new int[] {250675240, 250675263, 250675250, 250675234, 250675226, 250675212, 250675286, 250675265, 250675245, 250675236};
			anims[ 15476].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15476].delays = new int[] {7, 7, 7, 7, 7, 7, 7, 3, 3, 1};
			anims[ 15476].loopDelay = -1;
			anims[ 15476].oneSquareAnimation = false;
			anims[ 15476].forcedPriority = 5;
			anims[ 15476].leftHandItem = -1;
			anims[ 15476].rightHandItem = -1;
			anims[ 15476].frameStep = 99;
			anims[ 15476].resetWhenWalk = 0;
			anims[ 15476].priority = 0;
			anims[ 15476].delayType = 2;

			anims[ 15477] = new Animation();
			anims[ 15477].frameCount = 25;
			anims[ 15477].frameIDs = new int[] {250675268, 250675315, 250675316, 250675288, 250675203, 250675270, 250675293, 250675325, 250675304, 250675299, 250675202, 250675205, 250675251, 250675331, 250675261, 250675291, 250675317, 250675322, 250675297, 250675279, 250675332, 250675295, 250675275, 250675292, 250675219};
			anims[ 15477].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15477].delays = new int[] {3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15477].loopDelay = -1;
			anims[ 15477].oneSquareAnimation = false;
			anims[ 15477].forcedPriority = 6;
			anims[ 15477].leftHandItem = -1;
			anims[ 15477].rightHandItem = -1;
			anims[ 15477].frameStep = 99;
			anims[ 15477].resetWhenWalk = 0;
			anims[ 15477].priority = 0;
			anims[ 15477].delayType = 2;

			anims[ 15478] = new Animation();
			anims[ 15478].frameCount = 22;
			anims[ 15478].frameIDs = new int[] {250675290, 250675201, 250675213, 250675310, 250675216, 250675333, 250675307, 250675276, 250675266, 250675273, 250675301, 250675252, 250675321, 250675282, 250675215, 250675308, 250675281, 250675294, 250675302, 250675210, 250675328, 250675218};
			anims[ 15478].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15478].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15478].loopDelay = -1;
			anims[ 15478].oneSquareAnimation = false;
			anims[ 15478].forcedPriority = 5;
			anims[ 15478].leftHandItem = -1;
			anims[ 15478].rightHandItem = -1;
			anims[ 15478].frameStep = 99;
			anims[ 15478].resetWhenWalk = 0;
			anims[ 15478].priority = 0;
			anims[ 15478].delayType = 2;

			anims[ 15479] = new Animation();
			anims[ 15479].frameCount = 20;
			anims[ 15479].frameIDs = new int[] {250675287, 250675300, 250675247, 250675262, 250675249, 250675305, 250675258, 250675324, 250675319, 250675259, 250675238, 250675230, 250675222, 250675284, 250675211, 250675208, 250675330, 250675272, 250675206, 250675233};
			anims[ 15479].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15479].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15479].loopDelay = -1;
			anims[ 15479].oneSquareAnimation = false;
			anims[ 15479].forcedPriority = 6;
			anims[ 15479].leftHandItem = -1;
			anims[ 15479].rightHandItem = -1;
			anims[ 15479].frameStep = 99;
			anims[ 15479].resetWhenWalk = 0;
			anims[ 15479].priority = 0;
			anims[ 15479].delayType = 2;

			anims[ 15480] = new Animation();
			anims[ 15480].frameCount = 34;
			anims[ 15480].frameIDs = new int[] {250675257, 250675224, 250675326, 250675237, 250675256, 250675244, 250675217, 250675221, 250675229, 250675241, 250675232, 250675204, 250675283, 250675271, 250675267, 250675314, 250675264, 250675246, 250675303, 250675306, 250675260, 250675313, 250675312, 250675277, 250675223, 250675285, 250675296, 250675334, 250675254, 250675242, 250675309, 250675311, 250675200, 250675280};
			anims[ 15480].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15480].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 8};
			anims[ 15480].loopDelay = 1;
			anims[ 15480].oneSquareAnimation = false;
			anims[ 15480].forcedPriority = 10;
			anims[ 15480].leftHandItem = -1;
			anims[ 15480].rightHandItem = -1;
			anims[ 15480].frameStep = 99;
			anims[ 15480].resetWhenWalk = 0;
			anims[ 15480].priority = 0;
			anims[ 15480].delayType = 2;

			anims[ 15481] = new Animation();
			anims[ 15481].frameCount = 120;
			anims[ 15481].frameIDs = new int[] {250740890, 250740745, 250740861, 250740757, 250740862, 250740957, 250740845, 250740945, 250740949, 250740812, 250740848, 250740741, 250740892, 250740972, 250740824, 250740790, 250740866, 250740971, 250740811, 250740901, 250740938, 250740867, 250740875, 250740889, 250740915, 250740765, 250740772, 250740953, 250740761, 250740909, 250740913, 250740939, 250740837, 250740758, 250740962, 250740780, 250740739, 250740976, 250740737, 250740744, 250740831, 250740868, 250740830, 250740842, 250740974, 250740746, 250740893, 250740847, 250740792, 250740747, 250740911, 250740796, 250740888, 250740907, 250740930, 250740928, 250740890, 250740745, 250740861, 250740757, 250740862, 250740957, 250740845, 250740945, 250740949, 250740812, 250740848, 250740741, 250740892, 250740972, 250740824, 250740790, 250740866, 250740971, 250740811, 250740901, 250740938, 250740867, 250740875, 250740889, 250740915, 250740765, 250740772, 250740953, 250740761, 250740909, 250740913, 250740939, 250740890, 250740745, 250740861, 250740757, 250740862, 250740957, 250740845, 250740945, 250740949, 250740812, 250740848, 250740741, 250740892, 250740972, 250740824, 250740790, 250740866, 250740971, 250740811, 250740901, 250740938, 250740867, 250740875, 250740889, 250740915, 250740765, 250740772, 250740953, 250740761, 250740909, 250740913, 250740939};
			anims[ 15481].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15481].delays = new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
			anims[ 15481].loopDelay = -1;
			anims[ 15481].oneSquareAnimation = false;
			anims[ 15481].forcedPriority = 5;
			anims[ 15481].leftHandItem = -1;
			anims[ 15481].rightHandItem = -1;
			anims[ 15481].frameStep = 99;
			anims[ 15481].resetWhenWalk = 0;
			anims[ 15481].priority = 0;
			anims[ 15481].delayType = 2;

			anims[ 15482] = new Animation();
			anims[ 15482].frameCount = 16;
			anims[ 15482].frameIDs = new int[] {250740795, 250740882, 250740854, 250740912, 250740966, 250740941, 250740836, 250740960, 250740768, 250740809, 250740876, 250740857, 250740740, 250740828, 250740940, 250740978};
			anims[ 15482].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15482].delays = new int[] {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
			anims[ 15482].loopDelay = -1;
			anims[ 15482].oneSquareAnimation = false;
			anims[ 15482].forcedPriority = 5;
			anims[ 15482].leftHandItem = -1;
			anims[ 15482].rightHandItem = -1;
			anims[ 15482].frameStep = 99;
			anims[ 15482].resetWhenWalk = 0;
			anims[ 15482].priority = 0;
			anims[ 15482].delayType = 2;

			anims[ 15483] = new Animation();
			anims[ 15483].frameCount = 16;
			anims[ 15483].frameIDs = new int[] {250740902, 250740950, 250740797, 250740931, 250740855, 250740785, 250740878, 250740839, 250740896, 250740820, 250740806, 250740886, 250740766, 250740770, 250740743, 250740833};
			anims[ 15483].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15483].delays = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
			anims[ 15483].loopDelay = -1;
			anims[ 15483].oneSquareAnimation = false;
			anims[ 15483].forcedPriority = 5;
			anims[ 15483].leftHandItem = -1;
			anims[ 15483].rightHandItem = -1;
			anims[ 15483].frameStep = 99;
			anims[ 15483].resetWhenWalk = 0;
			anims[ 15483].priority = 0;
			anims[ 15483].delayType = 2;

			anims[ 15484] = new Animation();
			anims[ 15484].frameCount = 34;
			anims[ 15484].frameIDs = new int[] {250740786, 250740898, 250740871, 250740801, 250740884, 250740798, 250740916, 250740965, 250740934, 250740903, 250740943, 250740817, 250740753, 250740863, 250740952, 250740864, 250740813, 250740821, 250740771, 250740814, 250740899, 250740933, 250740946, 250740829, 250740816, 250740935, 250740968, 250740961, 250740763, 250740851, 250740885, 250740773, 250740927, 250740810};
			anims[ 15484].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15484].delays = new int[] {3, 3, 2, 2, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 3, 2, 2, 2, 2, 1};
			anims[ 15484].loopDelay = -1;
			anims[ 15484].oneSquareAnimation = false;
			anims[ 15484].forcedPriority = 6;
			anims[ 15484].leftHandItem = -1;
			anims[ 15484].rightHandItem = -1;
			anims[ 15484].frameStep = 99;
			anims[ 15484].resetWhenWalk = 0;
			anims[ 15484].priority = 0;
			anims[ 15484].delayType = 2;

			anims[ 15485] = new Animation();
			anims[ 15485].frameCount = 19;
			anims[ 15485].frameIDs = new int[] {250740923, 250740784, 250740932, 250740924, 250740917, 250740872, 250740751, 250740791, 250740925, 250740738, 250740883, 250740906, 250740754, 250740755, 250740887, 250740749, 250740834, 250740736, 250740923};
			anims[ 15485].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15485].delays = new int[] {4, 2, 2, 3, 3, 3, 2, 3, 4, 4, 4, 4, 4, 3, 3, 4, 4, 3, 1};
			anims[ 15485].loopDelay = -1;
			anims[ 15485].oneSquareAnimation = false;
			anims[ 15485].forcedPriority = 5;
			anims[ 15485].leftHandItem = -1;
			anims[ 15485].rightHandItem = -1;
			anims[ 15485].frameStep = 99;
			anims[ 15485].resetWhenWalk = 0;
			anims[ 15485].priority = 0;
			anims[ 15485].delayType = 2;

			anims[ 15486] = new Animation();
			anims[ 15486].frameCount = 34;
			anims[ 15486].frameIDs = new int[] {250740838, 250740956, 250740742, 250740895, 250740951, 250740944, 250740822, 250740969, 250740800, 250740860, 250740819, 250740948, 250740843, 250740778, 250740858, 250740975, 250740826, 250740769, 250740841, 250740846, 250740756, 250740904, 250740752, 250740936, 250740954, 250740852, 250740804, 250740921, 250740779, 250740873, 250740783, 250740827, 250740825, 250740905};
			anims[ 15486].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15486].delays = new int[] {2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 3, 3, 3, 3, 3, 2, 1};
			anims[ 15486].loopDelay = -1;
			anims[ 15486].oneSquareAnimation = false;
			anims[ 15486].forcedPriority = 7;
			anims[ 15486].leftHandItem = -1;
			anims[ 15486].rightHandItem = -1;
			anims[ 15486].frameStep = 99;
			anims[ 15486].resetWhenWalk = 0;
			anims[ 15486].priority = 0;
			anims[ 15486].delayType = 2;

			anims[ 15487] = new Animation();
			anims[ 15487].frameCount = 34;
			anims[ 15487].frameIDs = new int[] {250740970, 250740777, 250740782, 250740762, 250740922, 250740805, 250740891, 250740926, 250740853, 250740920, 250740900, 250740859, 250740787, 250740856, 250740818, 250740964, 250740959, 250740802, 250740759, 250740880, 250740967, 250740977, 250740973, 250740823, 250740881, 250740793, 250740750, 250740764, 250740789, 250740807, 250740775, 250740937, 250740815, 250740970};
			anims[ 15487].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15487].delays = new int[] {2, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 2, 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 1};
			anims[ 15487].loopDelay = -1;
			anims[ 15487].oneSquareAnimation = false;
			anims[ 15487].forcedPriority = 7;
			anims[ 15487].leftHandItem = -1;
			anims[ 15487].rightHandItem = -1;
			anims[ 15487].frameStep = 99;
			anims[ 15487].resetWhenWalk = 0;
			anims[ 15487].priority = 0;
			anims[ 15487].delayType = 2;

			anims[ 15488] = new Animation();
			anims[ 15488].frameCount = 30;
			anims[ 15488].frameIDs = new int[] {250740942, 250740897, 250740788, 250740869, 250740748, 250740958, 250740929, 250740832, 250740767, 250740835, 250740955, 250740914, 250740794, 250740894, 250740799, 250740919, 250740808, 250740910, 250740947, 250740760, 250740840, 250740850, 250740849, 250740908, 250740803, 250740781, 250740865, 250740774, 250740874, 250740879};
			anims[ 15488].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15488].delays = new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 2, 2, 4, 4, 6, 12, 12, 20, 15, 15, 15, 12};
			anims[ 15488].loopDelay = 1;
			anims[ 15488].oneSquareAnimation = false;
			anims[ 15488].forcedPriority = 10;
			anims[ 15488].leftHandItem = -1;
			anims[ 15488].rightHandItem = -1;
			anims[ 15488].frameStep = 99;
			anims[ 15488].resetWhenWalk = 0;
			anims[ 15488].priority = 0;
			anims[ 15488].delayType = 2;

			anims[ 15489] = new Animation();
			anims[ 15489].frameCount = 48;
			anims[ 15489].frameIDs = new int[] {250806427, 250806310, 250806452, 250806304, 250806406, 250806360, 250806289, 250806367, 250806361, 250806335, 250806287, 250806374, 250806427, 250806310, 250806452, 250806304, 250806406, 250806360, 250806289, 250806367, 250806361, 250806335, 250806287, 250806374, 250806427, 250806310, 250806452, 250806304, 250806406, 250806360, 250806289, 250806367, 250806361, 250806335, 250806287, 250806374, 250806427, 250806310, 250806452, 250806304, 250806406, 250806360, 250806289, 250806367, 250806361, 250806335, 250806287, 250806374};
			anims[ 15489].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15489].delays = new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
			anims[ 15489].loopDelay = -1;
			anims[ 15489].oneSquareAnimation = false;
			anims[ 15489].forcedPriority = 5;
			anims[ 15489].leftHandItem = -1;
			anims[ 15489].rightHandItem = -1;
			anims[ 15489].frameStep = 99;
			anims[ 15489].resetWhenWalk = 0;
			anims[ 15489].priority = 0;
			anims[ 15489].delayType = 2;

			anims[ 15490] = new Animation();
			anims[ 15490].frameCount = 18;
			anims[ 15490].frameIDs = new int[] {250806326, 250806500, 250806368, 250806490, 250806283, 250806432, 250806324, 250806354, 250806340, 250806502, 250806438, 250806337, 250806483, 250806477, 250806313, 250806460, 250806414, 250806430};
			anims[ 15490].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15490].delays = new int[] {3, 3, 3, 3, 3, 3, 2, 1, 3, 3, 3, 3, 3, 3, 3, 2, 1, 3};
			anims[ 15490].loopDelay = -1;
			anims[ 15490].oneSquareAnimation = false;
			anims[ 15490].forcedPriority = 5;
			anims[ 15490].leftHandItem = -1;
			anims[ 15490].rightHandItem = -1;
			anims[ 15490].frameStep = 99;
			anims[ 15490].resetWhenWalk = 0;
			anims[ 15490].priority = 0;
			anims[ 15490].delayType = 2;

			anims[ 15491] = new Animation();
			anims[ 15491].frameCount = 42;
			anims[ 15491].frameIDs = new int[] {250806370, 250806453, 250806446, 250806474, 250806302, 250806309, 250806292, 250806327, 250806280, 250806431, 250806285, 250806342, 250806457, 250806393, 250806385, 250806467, 250806442, 250806433, 250806291, 250806386, 250806465, 250806314, 250806378, 250806365, 250806445, 250806321, 250806278, 250806503, 250806466, 250806450, 250806353, 250806484, 250806281, 250806332, 250806407, 250806306, 250806328, 250806454, 250806317, 250806436, 250806284, 250806275};
			anims[ 15491].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15491].delays = new int[] {4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 3, 2, 2, 2, 1};
			anims[ 15491].loopDelay = -1;
			anims[ 15491].oneSquareAnimation = false;
			anims[ 15491].forcedPriority = 8;
			anims[ 15491].leftHandItem = -1;
			anims[ 15491].rightHandItem = -1;
			anims[ 15491].frameStep = 99;
			anims[ 15491].resetWhenWalk = 2;
			anims[ 15491].priority = 2;
			anims[ 15491].delayType = 2;

			anims[ 15492] = new Animation();
			anims[ 15492].frameCount = 21;
			anims[ 15492].frameIDs = new int[] {250806448, 250806488, 250806295, 250806437, 250806307, 250806456, 250806312, 250806388, 250806355, 250806486, 250806351, 250806493, 250806475, 250806409, 250806277, 250806412, 250806299, 250806356, 250806303, 250806322, 250806398};
			anims[ 15492].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15492].delays = new int[] {4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15492].loopDelay = -1;
			anims[ 15492].oneSquareAnimation = false;
			anims[ 15492].forcedPriority = 6;
			anims[ 15492].leftHandItem = -1;
			anims[ 15492].rightHandItem = -1;
			anims[ 15492].frameStep = 99;
			anims[ 15492].resetWhenWalk = 2;
			anims[ 15492].priority = 2;
			anims[ 15492].delayType = 2;

			anims[ 15493] = new Animation();
			anims[ 15493].frameCount = 17;
			anims[ 15493].frameIDs = new int[] {250871819, 250871935, 250871938, 250872044, 250871962, 250871928, 250871907, 250871986, 250871822, 250871970, 250872033, 250871924, 250871915, 250871987, 250872028, 250871943, 250872003};
			anims[ 15493].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15493].delays = new int[] {26, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 5};
			anims[ 15493].loopDelay = -1;
			anims[ 15493].oneSquareAnimation = false;
			anims[ 15493].forcedPriority = 5;
			anims[ 15493].leftHandItem = -1;
			anims[ 15493].rightHandItem = -1;
			anims[ 15493].frameStep = 99;
			anims[ 15493].resetWhenWalk = 0;
			anims[ 15493].priority = 0;
			anims[ 15493].delayType = 2;

			anims[ 15494] = new Animation();
			anims[ 15494].frameCount = 20;
			anims[ 15494].frameIDs = new int[] {250806396, 250806461, 250806341, 250806429, 250806330, 250806443, 250806399, 250806491, 250806344, 250806397, 250806346, 250806444, 250806364, 250806505, 250806481, 250806300, 250806373, 250806315, 250806380, 250806396};
			anims[ 15494].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15494].delays = new int[] {2, 2, 4, 3, 3, 4, 4, 3, 2, 5, 3, 3, 3, 3, 4, 3, 3, 3, 2, 1};
			anims[ 15494].loopDelay = -1;
			anims[ 15494].oneSquareAnimation = false;
			anims[ 15494].forcedPriority = 5;
			anims[ 15494].leftHandItem = -1;
			anims[ 15494].rightHandItem = -1;
			anims[ 15494].frameStep = 99;
			anims[ 15494].resetWhenWalk = 0;
			anims[ 15494].priority = 1;
			anims[ 15494].delayType = 2;

			anims[ 15495] = new Animation();
			anims[ 15495].frameCount = 22;
			anims[ 15495].frameIDs = new int[] {250806286, 250806468, 250806403, 250806482, 250806506, 250806449, 250806499, 250806345, 250806348, 250806308, 250806296, 250806331, 250806382, 250806459, 250806494, 250806400, 250806369, 250806320, 250806447, 250806480, 250806319, 250806411};
			anims[ 15495].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15495].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2};
			anims[ 15495].loopDelay = -1;
			anims[ 15495].oneSquareAnimation = false;
			anims[ 15495].forcedPriority = 7;
			anims[ 15495].leftHandItem = -1;
			anims[ 15495].rightHandItem = -1;
			anims[ 15495].frameStep = 99;
			anims[ 15495].resetWhenWalk = 2;
			anims[ 15495].priority = 2;
			anims[ 15495].delayType = 2;

			anims[ 15496] = new Animation();
			anims[ 15496].frameCount = 18;
			anims[ 15496].frameIDs = new int[] {250871971, 250871872, 250871958, 250871906, 250872037, 250871990, 250871921, 250871900, 250871839, 250872011, 250871992, 250871851, 250871995, 250871826, 250871815, 250871870, 250872052, 250871998};
			anims[ 15496].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15496].delays = new int[] {15, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2};
			anims[ 15496].loopDelay = -1;
			anims[ 15496].oneSquareAnimation = false;
			anims[ 15496].forcedPriority = 5;
			anims[ 15496].leftHandItem = -1;
			anims[ 15496].rightHandItem = -1;
			anims[ 15496].frameStep = 99;
			anims[ 15496].resetWhenWalk = 0;
			anims[ 15496].priority = 0;
			anims[ 15496].delayType = 2;

			anims[ 15497] = new Animation();
			anims[ 15497].frameCount = 27;
			anims[ 15497].frameIDs = new int[] {250806390, 250806426, 250806276, 250806479, 250806395, 250806288, 250806478, 250806376, 250806462, 250806334, 250806384, 250806366, 250806428, 250806476, 250806392, 250806451, 250806294, 250806349, 250806372, 250806441, 250806492, 250806416, 250806352, 250806495, 250806371, 250806336, 250806487};
			anims[ 15497].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15497].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			anims[ 15497].loopDelay = -1;
			anims[ 15497].oneSquareAnimation = false;
			anims[ 15497].forcedPriority = 7;
			anims[ 15497].leftHandItem = -1;
			anims[ 15497].rightHandItem = -1;
			anims[ 15497].frameStep = 99;
			anims[ 15497].resetWhenWalk = 2;
			anims[ 15497].priority = 2;
			anims[ 15497].delayType = 2;

			anims[ 15498] = new Animation();
			anims[ 15498].frameCount = 8;
			anims[ 15498].frameIDs = new int[] {250806504, 250806274, 250806469, 250806357, 250806279, 250806298, 250806359, 250806333};
			anims[ 15498].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15498].delays = new int[] {8, 7, 8, 7, 8, 7, 8, 7};
			anims[ 15498].loopDelay = -1;
			anims[ 15498].oneSquareAnimation = false;
			anims[ 15498].forcedPriority = 5;
			anims[ 15498].leftHandItem = -1;
			anims[ 15498].rightHandItem = -1;
			anims[ 15498].frameStep = 99;
			anims[ 15498].resetWhenWalk = 0;
			anims[ 15498].priority = 0;
			anims[ 15498].delayType = 2;

			anims[ 15499] = new Animation();
			anims[ 15499].frameCount = 22;
			anims[ 15499].frameIDs = new int[] {250806381, 250806485, 250806421, 250806415, 250806423, 250806305, 250806425, 250806419, 250806440, 250806470, 250806496, 250806417, 250806379, 250806301, 250806472, 250806282, 250806464, 250806435, 250806293, 250806439, 250806273, 250806325};
			anims[ 15499].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
			anims[ 15499].delays = new int[] {4, 4, 4, 4, 8, 8, 7, 6, 5, 4, 3, 5, 7, 5, 4, 5, 5, 5, 5, 10, 35, 7};
			anims[ 15499].loopDelay = 1;
			anims[ 15499].oneSquareAnimation = false;
			anims[ 15499].forcedPriority = 10;
			anims[ 15499].leftHandItem = -1;
			anims[ 15499].rightHandItem = -1;
			anims[ 15499].frameStep = 99;
			anims[ 15499].resetWhenWalk = 0;
			anims[ 15499].priority = 0;
			anims[ 15499].delayType = 2;
// JalTok
		if (j == 7588) {
			anims[7588].frameCount = 32;
			anims[7588].frameIDs = new int[] { 124977329, 124977260, 124977193, 124977273, 124977295, 124977227,
					124977167, 124977302, 124977205, 124977254, 124977257, 124977161, 124977197, 124977153,
					124977314, 124977189, 124977315, 124977305, 124977198, 124977258, 124977207, 124977327,
					124977242, 124977187, 124977251, 124977338, 124977175, 124977169, 124977303, 124977279,
					124977191, 124977163 };
			anims[7588].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
			anims[7588].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
					3, 3, 3, 3, 3, 3, 3, 3 };
			anims[7588].loopDelay = -1;
			anims[7588].animationFlowControl = null;
			anims[7588].oneSquareAnimation = false;
			anims[7588].forcedPriority = 4;
			anims[7588].leftHandItem = -1;
			anims[7588].rightHandItem = -1;
			anims[7588].frameStep = 99;
			anims[7588].resetWhenWalk = 0;
			anims[7588].priority = 0;
			anims[7588].delayType = 2;
		}

		if (j == 7589) {
			anims[7589].frameCount = 16;
			anims[7589].frameIDs = new int[] { 124977284, 124977230, 124977206, 124977334, 124977259, 124977289,
					124977263, 124977306, 124977246, 124977286, 124977274, 124977354, 124977247, 124977294,
					124977313, 124977252 };
			anims[7589].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
			anims[7589].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4 };
			anims[7589].loopDelay = -1;
			anims[7589].animationFlowControl = null;
			anims[7589].oneSquareAnimation = false;
			anims[7589].forcedPriority = 3;
			anims[7589].leftHandItem = -1;
			anims[7589].rightHandItem = -1;
			anims[7589].frameStep = 99;
			anims[7589].resetWhenWalk = 0;
			anims[7589].priority = 0;
			anims[7589].delayType = 2;
		}
			
			if (j == 884) {
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
			}
			//cerb pet
			if (j == 6560) {
				anims[ 6560].frameCount = 16;
				anims[ 6560].frameIDs = new int[] {108920934, 108921278, 108921271, 108921264, 108921272, 108921265, 108921273, 108921266, 108921274, 108921267, 108921275, 108921268, 108921276, 108921269, 108921277, 108921270};
				anims[ 6560].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
				anims[ 6560].delays = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
				anims[ 6560].loopDelay = -1;
				anims[ 6560].animationFlowControl = null;
				anims[ 6560].oneSquareAnimation = false;
				anims[ 6560].forcedPriority = 5;
				anims[ 6560].leftHandItem = -1;
				anims[ 6560].rightHandItem = -1;
				anims[ 6560].frameStep = 99;
				anims[ 6560].resetWhenWalk = 0;
				anims[ 6560].priority = 0;
				anims[ 6560].delayType = 1;
				}
				//cerb bpet
				if (j == 6561) {
				anims[ 6561].frameCount = 89;
				anims[ 6561].frameIDs = new int[] {108921223, 108921263, 108921259, 108921224, 108921234, 108921225, 108921260, 108921226, 108921245, 108921227, 108921261, 108921228, 108921256, 108921229, 108921262, 108921230, 108921223, 108921263, 108921259, 108921224, 108921234, 108921225, 108921260, 108921226, 108921245, 108921227, 108921261, 108921228, 108921256, 108921229, 108921262, 108921230, 108921223, 108921263, 108921259, 108921224, 108921234, 108921225, 108921260, 108921226, 108921245, 108921227, 108921261, 108921228, 108921256, 108921229, 108921262, 108921230, 108921238, 108921236, 108921235, 108921237, 108921239, 108921240, 108921231, 108921244, 108921246, 108921247, 108921248, 108921241, 108921242, 108921243, 108921232, 108921249, 108921233, 108921254, 108921250, 108921258, 108921255, 108921257, 108921252, 108921251, 108921253, 108921223, 108921263, 108921259, 108921224, 108921234, 108921225, 108921260, 108921226, 108921245, 108921227, 108921261, 108921228, 108921256, 108921229, 108921262, 108921230};
				anims[ 6561].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
				anims[ 6561].delays = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
				anims[ 6561].loopDelay = 89;
				anims[ 6561].animationFlowControl = null;
				anims[ 6561].oneSquareAnimation = false;
				anims[ 6561].forcedPriority = 5;
				anims[ 6561].leftHandItem = -1;
				anims[ 6561].rightHandItem = -1;
				anims[ 6561].frameStep = 99;
				anims[ 6561].resetWhenWalk = 0;
				anims[ 6561].priority = 0;
				anims[ 6561].delayType = 1;
				}
			// kraken pet
				if (j == 3989) {
					anims[ 3989].frameCount = 11;
					anims[ 3989].frameIDs = new int[] {50987045, 50987048, 50987049, 50987050, 50987051, 50987052, 50987053, 50987054, 50987055, 50987046, 50987047};
					anims[ 3989].frameIDs2 = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
					anims[ 3989].delays = new int[] {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
					anims[ 3989].loopDelay = -1;
					anims[ 3989].animationFlowControl = null;
					anims[ 3989].oneSquareAnimation = false;
					anims[ 3989].forcedPriority = 5;
					anims[ 3989].leftHandItem = -1;
					anims[ 3989].rightHandItem = -1;
					anims[ 3989].frameStep = 99;
					anims[ 3989].resetWhenWalk = 0;
					anims[ 3989].priority = 0;
					anims[ 3989].delayType = 1;
					}
			//rock golem walk
            if (j == 7181) {
            	anims[ 7181].frameCount = 15;
            	anims[ 7181].frameIDs = new int[]{120389634, 120389653, 120389642, 120389632, 120389655, 120389658, 120389641, 120389650, 120389654, 120389659, 120389637, 120389652, 120389651, 120389640, 120389636};
            	anims[ 7181].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7181].delays = new int[]{4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 3, 3};
            	anims[ 7181].loopDelay = -1;
            	anims[ 7181].animationFlowControl = null;
            	anims[ 7181].oneSquareAnimation = false;
            	anims[ 7181].forcedPriority = 5;
            	anims[ 7181].leftHandItem = -1;
            	anims[ 7181].rightHandItem = -1;
            	anims[ 7181].frameStep = 99;
            	anims[ 7181].resetWhenWalk = 0;
            	anims[ 7181].priority = 0;
            	anims[ 7181].delayType = 1;
            	}
            //rock golem stand
            if (j == 7180) {
            	anims[ 7180].frameCount = 14;
            	anims[ 7180].frameIDs = new int[]{120389639, 120389660, 120389649, 120389657, 120389647, 120389643, 120389635, 120389656, 120389648, 120389633, 120389645, 120389638, 120389644, 120389646};
            	anims[ 7180].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7180].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1};
            	anims[ 7180].loopDelay = -1;
            	anims[ 7180].animationFlowControl = null;
            	anims[ 7180].oneSquareAnimation = false;
            	anims[ 7180].forcedPriority = 5;
            	anims[ 7180].leftHandItem = -1;
            	anims[ 7180].rightHandItem = -1;
            	anims[ 7180].frameStep = 99;
            	anims[ 7180].resetWhenWalk = 0;
            	anims[ 7180].priority = 0;
            	anims[ 7180].delayType = 1;
            	}
            //heron walk
            if (j == 6774) {
            	anims[ 6774].frameCount = 8;
            	anims[ 6774].frameIDs = new int[]{112263444, 112263449, 112263296, 112263371, 112263427, 112263287, 112263170, 112263272};
            	anims[ 6774].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 6774].delays = new int[]{3, 3, 3, 3, 3, 3, 3, 3};
            	anims[ 6774].loopDelay = -1;
            	anims[ 6774].animationFlowControl = null;
            	anims[ 6774].oneSquareAnimation = false;
            	anims[ 6774].forcedPriority = 5;
            	anims[ 6774].leftHandItem = -1;
            	anims[ 6774].rightHandItem = -1;
            	anims[ 6774].frameStep = 99;
            	anims[ 6774].resetWhenWalk = 0;
            	anims[ 6774].priority = 0;
            	anims[ 6774].delayType = 1;
            	}
            //heron stand
            if (j == 6772) {
            	anims[ 6772].frameCount = 90;
            	anims[ 6772].frameIDs = new int[]{112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263234, 112263329, 112263278, 112263214, 112263313, 112263191, 112263433, 112263360, 112263431, 112263362, 112263236, 112263380, 112263364, 112263276, 112263252, 112263228, 112263259, 112263351, 112263271, 112263422, 112263203, 112263443, 112263171, 112263330, 112263286, 112263225, 112263328, 112263196, 112263339, 112263341, 112263382, 112263416, 112263267, 112263359};
            	anims[ 6772].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 6772].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4};
            	anims[ 6772].loopDelay = -1;
            	anims[ 6772].animationFlowControl = null;
            	anims[ 6772].oneSquareAnimation = false;
            	anims[ 6772].forcedPriority = 5;
            	anims[ 6772].leftHandItem = -1;
            	anims[ 6772].rightHandItem = -1;
            	anims[ 6772].frameStep = 99;
            	anims[ 6772].resetWhenWalk = 0;
            	anims[ 6772].priority = 0;
            	anims[ 6772].delayType = 1;
            	}
            //beaver walk
            if (j == 7178) {
            	anims[ 7178].frameCount = 8;
            	anims[ 7178].frameIDs = new int[]{118620210, 118620173, 118620185, 118620167, 118620183, 118620192, 118620162, 118620205};
            	anims[ 7178].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7178].delays = new int[]{3, 3, 3, 3, 3, 3, 3, 3};
            	anims[ 7178].loopDelay = -1;
            	anims[ 7178].animationFlowControl = null;
            	anims[ 7178].oneSquareAnimation = false;
            	anims[ 7178].forcedPriority = 5;
            	anims[ 7178].leftHandItem = -1;
            	anims[ 7178].rightHandItem = -1;
            	anims[ 7178].frameStep = 99;
            	anims[ 7178].resetWhenWalk = 0;
            	anims[ 7178].priority = 0;
            	anims[ 7178].delayType = 1;
            	}
           //beaver stand
            if (j == 7177) {
            	anims[ 7177].frameCount = 90;
            	anims[ 7177].frameIDs = new int[]{118620170, 118620195, 118620211, 118620186, 118620168, 118620169, 118620206, 118620177, 118620198, 118620207, 118620189, 118620202, 118620180, 118620166, 118620161, 118620203, 118620176, 118620194, 118620196, 118620174, 118620190, 118620178, 118620209, 118620170, 118620201, 118620164, 118620191, 118620208, 118620204, 118620163, 118620193, 118620175, 118620184, 118620160, 118620199, 118620197, 118620188, 118620182, 118620181, 118620160, 118620165, 118620188, 118620179, 118620171, 118620170, 118620195, 118620211, 118620186, 118620168, 118620169, 118620206, 118620177, 118620198, 118620207, 118620189, 118620202, 118620180, 118620166, 118620161, 118620203, 118620176, 118620194, 118620196, 118620174, 118620190, 118620178, 118620209, 118620170, 118620195, 118620211, 118620186, 118620168, 118620169, 118620206, 118620177, 118620198, 118620207, 118620189, 118620202, 118620180, 118620166, 118620161, 118620203, 118620176, 118620194, 118620196, 118620174, 118620190, 118620178, 118620209};
            	anims[ 7177].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7177].delays = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
            	anims[ 7177].loopDelay = -1;
            	anims[ 7177].animationFlowControl = null;
            	anims[ 7177].oneSquareAnimation = false;
            	anims[ 7177].forcedPriority = 5;
            	anims[ 7177].leftHandItem = -1;
            	anims[ 7177].rightHandItem = -1;
            	anims[ 7177].frameStep = 99;
            	anims[ 7177].resetWhenWalk = 0;
            	anims[ 7177].priority = 0;
            	anims[ 7177].delayType = 1;
            	}
            //tanglerood stand
            if (j == 7312) {
            	anims[ 7312].frameCount = 8;
            	anims[ 7312].frameIDs = new int[]{121831448, 121831450, 121831445, 121831437, 121831453, 121831443, 121831425, 121831424};
            	anims[ 7312].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7312].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            	anims[ 7312].loopDelay = -1;
            	anims[ 7312].animationFlowControl = null;
            	anims[ 7312].oneSquareAnimation = false;
            	anims[ 7312].forcedPriority = 5;
            	anims[ 7312].leftHandItem = -1;
            	anims[ 7312].rightHandItem = -1;
            	anims[ 7312].frameStep = 99;
            	anims[ 7312].resetWhenWalk = 0;
            	anims[ 7312].priority = 0;
            	anims[ 7312].delayType = 1;
            	}
            //tanglerood walk
            	if (j == 7313) {
            	anims[ 7313].frameCount = 12;
            	anims[ 7313].frameIDs = new int[]{121831440, 121831436, 121831433, 121831452, 121831434, 121831430, 121831438, 121831428, 121831426, 121831441, 121831439, 121831449};
            	anims[ 7313].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            	anims[ 7313].delays = new int[]{3, 3, 4, 4, 3, 3, 3, 3, 4, 4, 3, 3};
            	anims[ 7313].loopDelay = -1;
            	anims[ 7313].animationFlowControl = null;
            	anims[ 7313].oneSquareAnimation = false;
            	anims[ 7313].forcedPriority = 5;
            	anims[ 7313].leftHandItem = -1;
            	anims[ 7313].rightHandItem = -1;
            	anims[ 7313].frameStep = 99;
            	anims[ 7313].resetWhenWalk = 0;
            	anims[ 7313].priority = 0;
            	anims[ 7313].delayType = 1;
            	}
            	//rocky stand
            	if (j == 7315) {
            		anims[ 7315].frameCount = 8;
            		anims[ 7315].frameIDs = new int[]{121896993, 121896974, 121896961, 121896987, 121896979, 121896966, 121896963, 121896972};
            		anims[ 7315].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            		anims[ 7315].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            		anims[ 7315].loopDelay = -1;
            		anims[ 7315].animationFlowControl = null;
            		anims[ 7315].oneSquareAnimation = false;
            		anims[ 7315].forcedPriority = 5;
            		anims[ 7315].leftHandItem = -1;
            		anims[ 7315].rightHandItem = -1;
            		anims[ 7315].frameStep = 99;
            		anims[ 7315].resetWhenWalk = 0;
            		anims[ 7315].priority = 0;
            		anims[ 7315].delayType = 1;
            		}
//rocky walk
            		if (j == 7316) {
            		anims[ 7316].frameCount = 16;
            		anims[ 7316].frameIDs = new int[]{121896976, 121896977, 121896960, 121896994, 121896990, 121896964, 121896989, 121896995, 121896975, 121896968, 121896988, 121896962, 121896982, 121896984, 121896981, 121896967};
            		anims[ 7316].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            		anims[ 7316].delays = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            		anims[ 7316].loopDelay = -1;
            		anims[ 7316].animationFlowControl = null;
            		anims[ 7316].oneSquareAnimation = false;
            		anims[ 7316].forcedPriority = 5;
            		anims[ 7316].leftHandItem = -1;
            		anims[ 7316].rightHandItem = -1;
            		anims[ 7316].frameStep = 99;
            		anims[ 7316].resetWhenWalk = 0;
            		anims[ 7316].priority = 0;
            		anims[ 7316].delayType = 1;
            		}
            		//squirrel stand
            		if (j == 7309) {
            			anims[ 7309].frameCount = 8;
            			anims[ 7309].frameIDs = new int[]{122028039, 122028035, 122028062, 122028038, 122028059, 122028041, 122028058, 122028047};
            			anims[ 7309].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            			anims[ 7309].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            			anims[ 7309].loopDelay = -1;
            			anims[ 7309].animationFlowControl = null;
            			anims[ 7309].oneSquareAnimation = false;
            			anims[ 7309].forcedPriority = 5;
            			anims[ 7309].leftHandItem = -1;
            			anims[ 7309].rightHandItem = -1;
            			anims[ 7309].frameStep = 99;
            			anims[ 7309].resetWhenWalk = 0;
            			anims[ 7309].priority = 0;
            			anims[ 7309].delayType = 1;
            			}
//squirrel walk
            			if (j == 7310) {
            			anims[ 7310].frameCount = 8;
            			anims[ 7310].frameIDs = new int[]{122028054, 122028032, 122028033, 122028043, 122028056, 122028042, 122028053, 122028040};
            			anims[ 7310].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            			anims[ 7310].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            			anims[ 7310].loopDelay = -1;
            			anims[ 7310].animationFlowControl = null;
            			anims[ 7310].oneSquareAnimation = false;
            			anims[ 7310].forcedPriority = 5;
            			anims[ 7310].leftHandItem = -1;
            			anims[ 7310].rightHandItem = -1;
            			anims[ 7310].frameStep = 99;
            			anims[ 7310].resetWhenWalk = 0;
            			anims[ 7310].priority = 0;
            			anims[ 7310].delayType = 1;
            			}
            			//rift stand
            			if (j == 7306) {
            				anims[ 7306].frameCount = 8;
            				anims[ 7306].frameIDs = new int[]{121962505, 121962506, 121962518, 121962513, 121962508, 121962503, 121962497, 121962511};
            				anims[ 7306].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            				anims[ 7306].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            				anims[ 7306].loopDelay = -1;
            				anims[ 7306].animationFlowControl = null;
            				anims[ 7306].oneSquareAnimation = false;
            				anims[ 7306].forcedPriority = 5;
            				anims[ 7306].leftHandItem = -1;
            				anims[ 7306].rightHandItem = -1;
            				anims[ 7306].frameStep = 99;
            				anims[ 7306].resetWhenWalk = 0;
            				anims[ 7306].priority = 0;
            				anims[ 7306].delayType = 1;
            				}
//rift walk
            				if (j == 7307) {
            				anims[ 7307].frameCount = 8;
            				anims[ 7307].frameIDs = new int[]{121962517, 121962496, 121962516, 121962510, 121962498, 121962500, 121962501, 121962509};
            				anims[ 7307].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
            				anims[ 7307].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5};
            				anims[ 7307].loopDelay = -1;
            				anims[ 7307].animationFlowControl = null;
            				anims[ 7307].oneSquareAnimation = false;
            				anims[ 7307].forcedPriority = 5;
            				anims[ 7307].leftHandItem = -1;
            				anims[ 7307].rightHandItem = -1;
            				anims[ 7307].frameStep = 99;
            				anims[ 7307].resetWhenWalk = 0;
            				anims[ 7307].priority = 0;
            				anims[ 7307].delayType = 1;
            				}
            				//olmelet stand
            				if (j == 7395) {
            					anims[ 7395].frameCount = 16;
            					anims[ 7395].frameIDs = new int[]{123797515, 123797513, 123797525, 123797544, 123797511, 123797540, 123797542, 123797534, 123797504, 123797548, 123797524, 123797547, 123797541, 123797538, 123797517, 123797521};
            					anims[ 7395].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            					anims[ 7395].delays = new int[]{3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4};
            					anims[ 7395].loopDelay = -1;
            					anims[ 7395].animationFlowControl = null;
            					anims[ 7395].oneSquareAnimation = false;
            					anims[ 7395].forcedPriority = 5;
            					anims[ 7395].leftHandItem = -1;
            					anims[ 7395].rightHandItem = -1;
            					anims[ 7395].frameStep = 99;
            					anims[ 7395].resetWhenWalk = 0;
            					anims[ 7395].priority = 0;
            					anims[ 7395].delayType = 1;
            					}
//olmet walk
            					if (j == 7396) {
            					anims[ 7396].frameCount = 16;
            					anims[ 7396].frameIDs = new int[]{123797512, 123797506, 123797518, 123797549, 123797545, 123797532, 123797529, 123797514, 123797507, 123797522, 123797533, 123797526, 123797516, 123797527, 123797539, 123797520};
            					anims[ 7396].frameIDs2 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            					anims[ 7396].delays = new int[]{4, 4, 4, 4, 3, 3, 4, 4, 4, 4, 3, 3, 4, 4, 4, 4};
            					anims[ 7396].loopDelay = -1;
            					anims[ 7396].animationFlowControl = null;
            					anims[ 7396].oneSquareAnimation = false;
            					anims[ 7396].forcedPriority = 5;
            					anims[ 7396].leftHandItem = -1;
            					anims[ 7396].rightHandItem = -1;
            					anims[ 7396].frameStep = 99;
            					anims[ 7396].resetWhenWalk = 0;
            					anims[ 7396].priority = 0;
            					anims[ 7396].delayType = 1;
            					}
			if (j == 4495) {// cerberus death anim
				anims[j].frameCount = 14;
				anims[j].frameIDs = new int[] { 117309441, 117309557, 117309612, 117309536, 117309603, 117309563,
						117309609, 117309567, 117309502, 117309607, 117309516, 117309626, 117309463, 117309514 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 5, 5, 5, 5, 5, 5, 3, 3, 5, 5, 3, 3, 4, 4 };
				anims[j].loopDelay = 1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 10;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			}
			if (j == 5070) { // Zulrah
				anims[j] = new Animation();
				anims[j].frameCount = 9;
				anims[j].loopDelay = -1;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				anims[j].oneSquareAnimation = false;
				anims[j].frameIDs = new int[] { 11927608, 11927625, 11927598, 11927678, 11927582, 11927600, 11927669,
						11927597, 11927678 };
				anims[j].delays = new int[] { 5, 4, 4, 4, 5, 5, 5, 4, 4 };
			}
			if (j == 5069) {
				anims[j].frameCount = 15;
				anims[j].loopDelay = -1;
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 1;
				anims[j].oneSquareAnimation = false;
				anims[j].frameIDs = new int[] { 11927613, 11927599, 11927574, 11927659, 11927676, 11927562, 11927626,
						11927628, 11927684, 11927647, 11927602, 11927576, 11927586, 11927653, 11927616 };
				anims[j].delays = new int[] { 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
			}
			if (j == 5072) {
				anims[j].frameCount = 21;
				anims[j].loopDelay = 1;
				anims[j].forcedPriority = 8;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				anims[j].oneSquareAnimation = false;
				anims[j].frameIDs = new int[] { 11927623, 11927595, 11927685, 11927636, 11927670, 11927579, 11927664,
						11927666, 11927661, 11927673, 11927633, 11927624, 11927555, 11927588, 11927692, 11927667,
						11927556, 11927630, 11927695, 11927643, 11927640 };
				anims[j].delays = new int[] { 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			}
			if (j == 5073) {
				anims[j].frameCount = 21;
				anims[j].loopDelay = -1;
				anims[j].forcedPriority = 9;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				anims[j].oneSquareAnimation = false;
				anims[j].frameIDs = new int[] { 11927640, 11927643, 11927695, 11927630, 11927556, 11927667, 11927692,
						11927588, 11927555, 11927624, 11927633, 11927673, 11927661, 11927666, 11927664, 11927579,
						11927670, 11927636, 11927685, 11927595, 11927623 };
				anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2 };
			}
			if (j == 5806) {
				anims[j].frameCount = 55;
				anims[j].loopDelay = -1;
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				anims[j].oneSquareAnimation = true;
				anims[j].frameIDs = new int[] { 11927612, 11927677, 11927615, 11927573, 11927618, 11927567, 11927564,
						11927606, 11927675, 11927657, 11927690, 11927583, 11927672, 11927552, 11927563, 11927683,
						11927639, 11927635, 11927668, 11927614, 11927627, 11927610, 11927693, 11927644, 11927656,
						11927660, 11927629, 11927635, 11927668, 11927614, 11927627, 11927610, 11927693, 11927644,
						11927656, 11927660, 11927635, 11927668, 11927614, 11927560, 11927687, 11927577, 11927569,
						11927557, 11927569, 11927577, 11927687, 11927560, 11927651, 11927611, 11927680, 11927622,
						11927691, 11927571, 11927601 };
				anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
						4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3 };
			}
			if (j == 5807) {
				anims[j].frameCount = 53;
				anims[j].loopDelay = -1;
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
				anims[j].oneSquareAnimation = true;
				anims[j].frameIDs = new int[] { 11927612, 11927677, 11927615, 11927573, 11927618, 11927567, 11927564,
						11927606, 11927675, 11927657, 11927690, 11927583, 11927672, 11927552, 11927563, 11927683,
						11927639, 11927635, 11927668, 11927614, 11927627, 11927610, 11927693, 11927644, 11927656,
						11927660, 11927629, 11927635, 11927668, 11927614, 11927627, 11927610, 11927693, 11927644,
						11927656, 11927604, 11927637, 11927688, 11927696, 11927681, 11927605, 11927681, 11927696,
						11927688, 11927637, 11927604, 11927656, 11927611, 11927680, 11927622, 11927691, 11927571,
						11927601 };
				anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
						4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3 };
			} // End Of Zulrah
			 if (j == 618) {//start fishing
					anims[j].frameIDs = new int[] { 19267634,19267645,19267656,19267658,19267659,19267660,19267661,19267662,19267663,19267635,19267636,19267637,19267638,19267639,19267640,19267641,19267642,19267643,19267644,19267646,19267647,19267648,19267649,19267650,19267651,19267650,19267649,19267648,19267647,19267648,19267649,19267650,19267651,19267652,19267653,19267654,19267655,19267657,19267763,19267764,19267765,19267766 };
				}
				if (j == 619) {
					anims[j].frameIDs = new int[] { 19267664,19267675,19267686,19267691,19267692,19267693,19267694,19267695,19267696,19267665,19267666,19267667,19267668,19267669,19267670,19267671,19267672,19267673,19267674,19267676,19267677,19267678,19267679,19267668,19267669,19267670,19267671,19267672,19267673,19267674,19267676,19267677,19267678,19267679,19267680,19267681,19267682,19267683,19267684,19267685,19267687,19267688,19267689,19267690 };
				}
				if (j == 620) {
					anims[j].frameIDs = new int[] { 19267697,19267708,19267719,19267724,19267725,19267726,19267727,19267728,19267729,19267698,19267699,19267700,19267701,19267702,19267703,19267704,19267705,19267706,19267707,19267709,19267710,19267711,19267712,19267701,19267702,19267703,19267704,19267705,19267706,19267707,19267709,19267710,19267711,19267712,19267713,19267714,19267715,19267716,19267717,19267718,19267720,19267721,19267722,19267723 };
				}
				if (j == 621) {
					anims[j].frameIDs = new int[] {19267697,19267708,19267719,19267724,19267725,19267726,19267727,19267728,19267729,19267698,19267699,19267700,19267701,19267702,19267703,19267704,19267705,19267706,19267707,19267709,19267710,19267711,19267712,19267701,19267702,19267703,19267704,19267705,19267706,19267707,19267709,19267710,19267711,19267712,19267713,19267714,19267715,19267716,19267717,19267718,19267720,19267721,19267722,19267723};
				}
				if (j == 622) {
					anims[j].frameCount = 19;
					anims[j].frameIDs = new int[] {19267585,19267586,19267587,19267588,19267589,19267590,19267591,19267592,19267591,19267592,19267591,19267592,19267591,19267592,19267591,19267591,19267592,19267591,19267592};
					anims[j].delays = new int[] {15,4,4,4,12,4,15,15,15,15,15,15,15,15,15,15,15,15,15};
				}
				if (j == 623) {
					anims[j].frameIDs = new int[] {19267585,19267586,19267587,19267588,19267589,19267590,19267591,19267592,19267591,19267592,19267591,19267592,19267591,19267592,19267591,19267591,19267592,19267591,19267592};
					anims[j].delays = new int[] {15,4,4,4,12,4,15,15,15,15,15,15,15,15,15,15,15,15,15};
				}//end of fishing
			if (j == 5061) { //blowpipe
				anims[j].frameCount = 13;
				anims[j].frameIDs = new int[]{19267601, 19267602, 19267603, 19267604, 19267605, 19267606, 19267607,
						19267606, 19267605, 19267604, 19267603, 19267602, 19267601,};
				anims[j].delays = new int[]{4, 3, 3, 4, 10, 10, 15, 10, 10, 4, 3, 3, 4,};
				// cache[j].animationFlowControl = new int[] { 1, 2, 9, 11, 13,
				// 15, 17, 19, 37, 39, 41, 43, 45, 164, 166, 168, 170, 172, 174,
				// 176, 178, 180, 182, 183, 185, 191, 192, 9999999, };
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = 0;
				anims[j].rightHandItem = 13438;
				anims[j].delayType = 1;
				anims[j].loopDelay = -1;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = -1;
				anims[j].priority = -1;
				}
			if (j == 4484) {// cerberus stand
				anims[j].frameCount = 14;
				anims[j].frameIDs = new int[] { 117309461, 117309547, 117309462, 117309623, 117309548, 117309621,
						117309454, 117309599, 117309473, 117309488, 117309559, 117309541, 117309588, 117309622 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 3, 5, 7, 7, 11, 7, 7, 5, 7, 5, 6, 9, 8, 4 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			}
			if (j == 4488) { // cerberus walk
				anims[j].frameCount = 15;
				anims[j].frameIDs = new int[] { 117309535, 117309468, 117309534, 117309569, 117309581, 117309507,
						117309443, 117309598, 117309444, 117309466, 117309576, 117309551, 117309464, 117309543,
						117309446 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			}
			if (j == 792) {
				anims[j] = new Animation();
				anims[j] = anims[791];
				anims[j].frameCount -= 20;
				System.out.println("frame count: "+anims[j].frameCount);
			}
			if (j == 4492) { // cerberus attack
				anims[j].frameCount = 18;
				anims[j].frameIDs = new int[] { 117309553, 117309490, 117309485, 117309530, 117309592, 117309531,
						117309594, 117309583, 117309458, 117309614, 117309538, 117309524, 117309521, 117309537,
						117309562, 117309513, 117309484, 117309616 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1 };
				anims[j].delays = new int[] { 7, 6, 6, 7, 9, 9, 9, 6, 6, 6, 7, 6, 6, 6, 6, 6, 6, 6 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 6;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			} // end of cerberus
			if (j == 7195) {
				anims[j].frameCount = 14;
				anims[j].frameIDs = new int[] { 120193037, 120193029, 120193052, 120193080, 120193048, 120193117,
						120193047, 120193040, 120193112, 120193025, 120193090, 120193098, 120193071, 120193067 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 4, 4, 5, 5, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 1;
			}
			if (j == 7191) {
				anims[j].frameCount = 12;
				anims[j].frameIDs = new int[] { 120193116, 120193084, 120193032, 120193046, 120193045, 120193102,
						120193068, 120193109, 120193058, 120193086, 120193038, 120193093 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 1;
			}
			if (j == 7192) { // jump
				anims[7192].frameCount = 15;
				anims[7192].frameIDs = new int[] { 120193089, 120193074, 120193105, 120193063, 120193078, 120193049,
						120193104, 120193043, 120193062, 120193054, 120193099, 120193101, 120193085, 120193030,
						120193072 };
				anims[7192].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[7192].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
				anims[7192].loopDelay = -1;
				anims[7192].animationFlowControl = null;
				anims[7192].oneSquareAnimation = false;
				anims[7192].forcedPriority = 6;
				anims[7192].leftHandItem = -1;
				anims[7192].rightHandItem = -1;
				anims[7192].frameStep = 99;
				anims[7192].resetWhenWalk = 0;
				anims[7192].priority = 0;
				anims[7192].delayType = 1;
			}
			if (j == 7193) { // poison
				anims[7193].frameCount = 24;
				anims[7193].frameIDs = new int[] { 120193060, 120193057, 120193113, 120193024, 120193087, 120193031,
						120193070, 120193094, 120193066, 120193083, 120193075, 120193026, 120193061, 120193044,
						120193108, 120193036, 120193096, 120193107, 120193056, 120193065, 120193103, 120193027,
						120193035, 120193053 };
				anims[7193].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1 };
				anims[7193].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 11, 3, 3,
						3 };
				anims[7193].loopDelay = -1;
				anims[7193].animationFlowControl = null;
				anims[7193].oneSquareAnimation = false;
				anims[7193].forcedPriority = 6;
				anims[7193].leftHandItem = -1;
				anims[7193].rightHandItem = -1;
				anims[7193].frameStep = 99;
				anims[7193].resetWhenWalk = 0;
				anims[7193].priority = 0;
				anims[7193].delayType = 1;
			}
			if (j == 4533) { // sire
				anims[j].frameCount = 20;
				anims[j].frameIDs = new int[] { 119406846, 119407068, 119407215, 119406592, 119407105, 119407099,
						119406975, 119407198, 119407023, 119406677, 119407267, 119407258, 119407023, 119406798,
						119406975, 119407218, 119407105, 119406977, 119407215, 119406756 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1 };
				anims[j].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			}
			if (j == 4534) {
				anims[j].frameCount = 19;
				anims[j].frameIDs = new int[] { 119406741, 119406935, 119406823, 119407208, 119406647, 119406777,
						119406623, 119406805, 119407264, 119407008, 119406898, 119406743, 119407040, 119407253,
						119406899, 119407138, 119406901, 119406719, 119406852 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1 };
				anims[j].delays = new int[] { 3, 3, 4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			} // end sire
			if (j == 1828) { // thermonuclear
				anims[j].frameCount = 16;
				anims[j].frameIDs = new int[] { 118095921, 118095916, 118096259, 118096320, 118096299, 118096329,
						118096036, 118095925, 118096180, 118096105, 118096311, 118095880, 118096084, 118096269,
						118095905, 118096227 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			}

			if (j == 1829) {
				anims[j].frameCount = 16;
				anims[j].frameIDs = new int[] { 118096000, 118096073, 118095928, 118095889, 118095894, 118096223,
						118096337, 118095979, 118096087, 118095980, 118096314, 118096202, 118095950, 118096110,
						118096328, 118096221 };
				anims[j].frameIDs2 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
				anims[j].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
				anims[j].loopDelay = -1;
				anims[j].animationFlowControl = null;
				anims[j].oneSquareAnimation = false;
				anims[j].forcedPriority = 5;
				anims[j].leftHandItem = -1;
				anims[j].rightHandItem = -1;
				anims[j].frameStep = 99;
				anims[j].resetWhenWalk = 0;
				anims[j].priority = 0;
				anims[j].delayType = 2;
			} // end of thermo
			if (j == 618) {
				anims[j].frameIDs = new int[] { 19267634, 19267645, 19267656, 19267658, 19267659, 19267660, 19267661,
						19267662, 19267663, 19267635, 19267636, 19267637, 19267638, 19267639, 19267640, 19267641,
						19267642, 19267643, 19267644, 19267646, 19267647, 19267648, 19267649, 19267650, 19267651,
						19267650, 19267649, 19267648, 19267647, 19267648, 19267649, 19267650, 19267651, 19267652,
						19267653, 19267654, 19267655, 19267657, 19267763, 19267764, 19267765, 19267766 };
			}
			if (j == 619) {
				anims[j].frameIDs = new int[] { 19267664, 19267675, 19267686, 19267691, 19267692, 19267693, 19267694,
						19267695, 19267696, 19267665, 19267666, 19267667, 19267668, 19267669, 19267670, 19267671,
						19267672, 19267673, 19267674, 19267676, 19267677, 19267678, 19267679, 19267668, 19267669,
						19267670, 19267671, 19267672, 19267673, 19267674, 19267676, 19267677, 19267678, 19267679,
						19267680, 19267681, 19267682, 19267683, 19267684, 19267685, 19267687, 19267688, 19267689,
						19267690 };
			}
			if (j == 620) {
				anims[j].frameIDs = new int[] { 19267697, 19267708, 19267719, 19267724, 19267725, 19267726, 19267727,
						19267728, 19267729, 19267698, 19267699, 19267700, 19267701, 19267702, 19267703, 19267704,
						19267705, 19267706, 19267707, 19267709, 19267710, 19267711, 19267712, 19267701, 19267702,
						19267703, 19267704, 19267705, 19267706, 19267707, 19267709, 19267710, 19267711, 19267712,
						19267713, 19267714, 19267715, 19267716, 19267717, 19267718, 19267720, 19267721, 19267722,
						19267723 };
			}
			if (j == 621) {
				anims[j].frameIDs = new int[] { 19267697, 19267708, 19267719, 19267724, 19267725, 19267726, 19267727,
						19267728, 19267729, 19267698, 19267699, 19267700, 19267701, 19267702, 19267703, 19267704,
						19267705, 19267706, 19267707, 19267709, 19267710, 19267711, 19267712, 19267701, 19267702,
						19267703, 19267704, 19267705, 19267706, 19267707, 19267709, 19267710, 19267711, 19267712,
						19267713, 19267714, 19267715, 19267716, 19267717, 19267718, 19267720, 19267721, 19267722,
						19267723 };
			}
			if (j == 622) {
				anims[j].frameCount = 19;
				anims[j].frameIDs = new int[] { 19267585, 19267586, 19267587, 19267588, 19267589, 19267590, 19267591,
						19267592, 19267591, 19267592, 19267591, 19267592, 19267591, 19267592, 19267591, 19267591,
						19267592, 19267591, 19267592 };
				anims[j].delays = new int[] { 15, 4, 4, 4, 12, 4, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
			}
			if (j == 623) {
				anims[j].frameIDs = new int[] { 19267585, 19267586, 19267587, 19267588, 19267589, 19267590, 19267591,
						19267592, 19267591, 19267592, 19267591, 19267592, 19267591, 19267592, 19267591, 19267591,
						19267592, 19267591, 19267592 };
				anims[j].delays = new int[] { 15, 4, 4, 4, 12, 4, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
			}

			/*
			 * Glacor anims
			 */
			/*
			 * if(j == 10867) { anims[j].frameCount = 19; anims[j].loopDelay =
			 * 19; anims[j].delays = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
			 * 5, 5, 5, 5, 5, 5, 5, 5}; anims[j].frameIDs = new int[]{244252686,
			 * 244252714, 244252760, 244252736, 244252678, 244252780, 244252817,
			 * 244252756, 244252700, 244252774, 244252834, 244252715, 244252732,
			 * 244252836, 244252776, 244252701, 244252751, 244252743,
			 * 244252685}; }
			 * 
			 * if(j == 10901) { anims[j].frameCount = 19; anims[j].loopDelay =
			 * 19; anims[j].delays = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			 * 3, 3, 3, 3, 3, 3, 3, 3}; anims[j].frameIDs = new int[]{244252826,
			 * 244252833, 244252674, 244252724, 244252793, 244252696, 244252787,
			 * 244252753, 244252703, 244252800, 244252752, 244252744, 244252680,
			 * 244252815, 244252829, 244252769, 244252699, 244252757,
			 * 244252695}; }
			 */
		}
	}

	public int getFrameLength(int i) {
		if (i > delays.length)
			return 1;
		int j = delays[i];
		if (j == 0) {
			FrameReader reader = FrameReader.forID(frameIDs[i]);
			if (reader != null)
				j = delays[i] = reader.displayLength;
		}
		if (j == 0)
			j = 1;
		return j;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				break;
			if (i == 1) {
				frameCount = stream.readUnsignedWord();
				frameIDs = new int[frameCount];
				frameIDs2 = new int[frameCount];
				delays = new int[frameCount];
				for (int i_ = 0; i_ < frameCount; i_++) {
					frameIDs[i_] = stream.readDWord();
					frameIDs2[i_] = -1;
				}
				for (int i_ = 0; i_ < frameCount; i_++)
					delays[i_] = stream.readUnsignedByte();
			} else if (i == 2)
				loopDelay = stream.readUnsignedWord();
			else if (i == 3) {
				int k = stream.readUnsignedByte();
				animationFlowControl = new int[k + 1];
				for (int l = 0; l < k; l++)
					animationFlowControl[l] = stream.readUnsignedByte();
				animationFlowControl[k] = 0x98967f;
			} else if (i == 4)
				oneSquareAnimation = true;
			else if (i == 5)
				forcedPriority = stream.readUnsignedByte();
			else if (i == 6)
				leftHandItem = stream.readUnsignedWord();
			else if (i == 7)
				rightHandItem = stream.readUnsignedWord();
			else if (i == 8)
				frameStep = stream.readUnsignedByte();
			else if (i == 9)
				resetWhenWalk = stream.readUnsignedByte();
			else if (i == 10)
				priority = stream.readUnsignedByte();
			else if (i == 11)
				delayType = stream.readUnsignedByte();
			else
				System.out.println("Unrecognized seq.dat config code: " + i);
		} while (true);
		if (frameCount == 0) {
			frameCount = 1;
			frameIDs = new int[1];
			frameIDs[0] = -1;
			frameIDs2 = new int[1];
			frameIDs2[0] = -1;
			delays = new int[1];
			delays[0] = -1;
		}
		if (resetWhenWalk == -1)
			if (animationFlowControl != null)
				resetWhenWalk = 2;
			else
				resetWhenWalk = 0;
		if (priority == -1) {
			if (animationFlowControl != null) {
				priority = 2;
				return;
			}
			priority = 0;
		}
	}

	private Animation() {
		loopDelay = -1;
		oneSquareAnimation = false;
		forcedPriority = 5;
		leftHandItem = -1;
		rightHandItem = -1;
		frameStep = 99;
		resetWhenWalk = -1;
		priority = -1;
		delayType = 2;
	}

	public static Animation anims[];
	public int frameCount;
	public int frameIDs[];
	public int frameIDs2[];
	public int[] delays;
	public int loopDelay;
	public int animationFlowControl[];
	public boolean oneSquareAnimation;
	public int forcedPriority;
	public int leftHandItem;
	public int rightHandItem;
	public int frameStep;
	public int resetWhenWalk;
	public int priority;
	public int delayType;
	public static int anInt367;
}