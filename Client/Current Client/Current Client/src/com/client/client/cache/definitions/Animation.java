package com.client.client.cache.definitions;

import com.client.client.CacheArchive;
import com.client.client.FrameReader;
import com.client.client.Stream;

public final class Animation {

	public static void unpackConfig(CacheArchive streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
		int length = stream.readUnsignedWord();
		if (anims == null)
			anims = new Animation[length];
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