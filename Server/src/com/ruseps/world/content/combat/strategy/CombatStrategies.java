package com.ruseps.world.content.combat.strategy;

import java.util.HashMap;

import java.util.Map;
import com.ruseps.world.content.combat.strategy.impl.Aviansie;
import com.ruseps.world.content.combat.strategy.impl.BandosAvatar;
import com.ruseps.world.content.combat.strategy.impl.ChaosElemental;
import com.ruseps.world.content.combat.strategy.impl.Cobra;
import com.ruseps.world.content.combat.strategy.impl.CorporealBeast;
import com.ruseps.world.content.combat.strategy.impl.DagannothSupreme;
import com.ruseps.world.content.combat.strategy.impl.Death;
import com.ruseps.world.content.combat.strategy.impl.DefaultMagicCombatStrategy;
import com.ruseps.world.content.combat.strategy.impl.DefaultMeleeCombatStrategy;
import com.ruseps.world.content.combat.strategy.impl.DefaultRangedCombatStrategy;
import com.ruseps.world.content.combat.strategy.impl.Dragon;
import com.ruseps.world.content.combat.strategy.impl.Fear;
import com.ruseps.world.content.combat.strategy.impl.Geerin;
import com.ruseps.world.content.combat.strategy.impl.GiantMole;
import com.ruseps.world.content.combat.strategy.impl.Glacor;
import com.ruseps.world.content.combat.strategy.impl.Graardor;
import com.ruseps.world.content.combat.strategy.impl.Grimspike;
import com.ruseps.world.content.combat.strategy.impl.Gritch;
import com.ruseps.world.content.combat.strategy.impl.Growler;
import com.ruseps.world.content.combat.strategy.impl.Jad;
import com.ruseps.world.content.combat.strategy.impl.KalphiteQueen;
import com.ruseps.world.content.combat.strategy.impl.KreeArra;
import com.ruseps.world.content.combat.strategy.impl.Kreeyath;
import com.ruseps.world.content.combat.strategy.impl.LizardMan;
import com.ruseps.world.content.combat.strategy.impl.Nex;
import com.ruseps.world.content.combat.strategy.impl.Nomad;
import com.ruseps.world.content.combat.strategy.impl.PlaneFreezer;
import com.ruseps.world.content.combat.strategy.impl.Revenant;
import com.ruseps.world.content.combat.strategy.impl.Sire;
import com.ruseps.world.content.combat.strategy.impl.Skotizo;
import com.ruseps.world.content.combat.strategy.impl.Spinolyp;
import com.ruseps.world.content.combat.strategy.impl.Steelwill;
import com.ruseps.world.content.combat.strategy.impl.Thermonuclear;
import com.ruseps.world.content.combat.strategy.impl.TormentedDemon;
import com.ruseps.world.content.combat.strategy.impl.Tsutsuroth;
import com.ruseps.world.content.combat.strategy.impl.UnholyCursebearer;
import com.ruseps.world.content.combat.strategy.impl.WingmanSkree;
import com.ruseps.world.content.combat.strategy.impl.WiseOldMan;
import com.ruseps.world.content.combat.strategy.impl.Venenatis;
import com.ruseps.world.content.combat.strategy.impl.Vetion;
import com.ruseps.world.content.combat.strategy.impl.WildyWyrm;
import com.ruseps.world.content.combat.strategy.impl.Callisto;
import com.ruseps.world.content.combat.strategy.impl.Cerberus;
import com.ruseps.world.content.combat.strategy.impl.Zilyana;
import com.ruseps.world.content.combat.strategy.impl.Inferno.Healer;
import com.ruseps.world.content.combat.strategy.impl.Inferno.JalTokJad;
import com.ruseps.world.content.combat.strategy.impl.Inferno.JalXil1;
import com.ruseps.world.content.combat.strategy.impl.Inferno.JalZek1;
import com.ruseps.world.content.combat.strategy.impl.kraken.Kraken;
import com.ruseps.world.content.combat.strategy.impl.kraken.Tentacles;


public class CombatStrategies {

	private static final DefaultMeleeCombatStrategy defaultMeleeCombatStrategy = new DefaultMeleeCombatStrategy();
	private static final DefaultMagicCombatStrategy defaultMagicCombatStrategy = new DefaultMagicCombatStrategy();
	private static final DefaultRangedCombatStrategy defaultRangedCombatStrategy = new DefaultRangedCombatStrategy();
	private static final Map<Integer, CombatStrategy> STRATEGIES = new HashMap<Integer, CombatStrategy>();
	
	public static void init() {
		DefaultMagicCombatStrategy defaultMagicStrategy = new DefaultMagicCombatStrategy();
		STRATEGIES.put(13, defaultMagicStrategy);
		STRATEGIES.put(172, defaultMagicStrategy);
		STRATEGIES.put(174, defaultMagicStrategy);
		STRATEGIES.put(2025, defaultMagicStrategy);
		STRATEGIES.put(3495, defaultMagicStrategy);
		STRATEGIES.put(3496, defaultMagicStrategy);
		STRATEGIES.put(3491, defaultMagicStrategy);
		STRATEGIES.put(2882, defaultMagicStrategy);
		STRATEGIES.put(13451, defaultMagicStrategy);
		STRATEGIES.put(13452, defaultMagicStrategy);
		STRATEGIES.put(13453, defaultMagicStrategy);
		STRATEGIES.put(13454, defaultMagicStrategy);
		STRATEGIES.put(1643, defaultMagicStrategy);
		STRATEGIES.put(6254, defaultMagicStrategy);
		STRATEGIES.put(6257, defaultMagicStrategy);
		STRATEGIES.put(6278, defaultMagicStrategy);
		STRATEGIES.put(6221, defaultMagicStrategy);
		STRATEGIES.put(133, defaultMagicStrategy);
	;
		
		DefaultRangedCombatStrategy defaultRangedStrategy = new DefaultRangedCombatStrategy();
		STRATEGIES.put(688, defaultRangedStrategy);
		STRATEGIES.put(2028, defaultRangedStrategy);
		STRATEGIES.put(6220, defaultRangedStrategy);
		STRATEGIES.put(6256, defaultRangedStrategy);
		STRATEGIES.put(6276, defaultRangedStrategy);
		STRATEGIES.put(6252, defaultRangedStrategy);
		STRATEGIES.put(27, defaultRangedStrategy);
		
		STRATEGIES.put(3847, new Kraken());
		STRATEGIES.put(148, new Tentacles());
		STRATEGIES.put(2745, new Jad());
		STRATEGIES.put(8528, new Nomad());
		STRATEGIES.put(8349, new TormentedDemon());
		STRATEGIES.put(3200, new ChaosElemental());
		STRATEGIES.put(4540, new BandosAvatar());
		STRATEGIES.put(8133, new CorporealBeast());
		STRATEGIES.put(13447, new Nex());
		STRATEGIES.put(2896, new Spinolyp());
		STRATEGIES.put(3334, new WildyWyrm());
		STRATEGIES.put(2881, new DagannothSupreme());
		STRATEGIES.put(6260, new Graardor());
		STRATEGIES.put(6263, new Steelwill());
		STRATEGIES.put(6265, new Grimspike());
		STRATEGIES.put(6222, new KreeArra());
		STRATEGIES.put(6223, new WingmanSkree());
		STRATEGIES.put(6225, new Geerin());
		STRATEGIES.put(6203, new Tsutsuroth());
		STRATEGIES.put(3340, new GiantMole());
		STRATEGIES.put(6208, new Kreeyath());
		STRATEGIES.put(6206, new Gritch());
		STRATEGIES.put(6247, new Zilyana());
		STRATEGIES.put(6250, new Growler());
		STRATEGIES.put(1382, new Glacor());
		STRATEGIES.put(9939, new PlaneFreezer());
		STRATEGIES.put(135, new Fear());
		STRATEGIES.put(133, new Cobra());
		STRATEGIES.put(1472, new Death());
		STRATEGIES.put(132, new Death());
		Dragon dragonStrategy = new Dragon();
		STRATEGIES.put(50, dragonStrategy);
		STRATEGIES.put(941, dragonStrategy);
		STRATEGIES.put(55, dragonStrategy);
		STRATEGIES.put(53, dragonStrategy);
		STRATEGIES.put(54, dragonStrategy);
		STRATEGIES.put(51, dragonStrategy);
		STRATEGIES.put(1590, dragonStrategy);
		STRATEGIES.put(1591, dragonStrategy);
		STRATEGIES.put(1592, dragonStrategy);
		STRATEGIES.put(5362, dragonStrategy);
		STRATEGIES.put(5363, dragonStrategy);
		
		Aviansie aviansieStrategy = new Aviansie();
		STRATEGIES.put(6246, aviansieStrategy);
		STRATEGIES.put(6230, aviansieStrategy);
		STRATEGIES.put(6231, aviansieStrategy);
		
		KalphiteQueen kalphiteQueenStrategy = new KalphiteQueen();
		STRATEGIES.put(1158, kalphiteQueenStrategy);
		STRATEGIES.put(1160, kalphiteQueenStrategy);
		
		Revenant revenantStrategy = new Revenant();
		STRATEGIES.put(6715, revenantStrategy);
		STRATEGIES.put(6716, revenantStrategy);
		STRATEGIES.put(6701, revenantStrategy);
		STRATEGIES.put(6725, revenantStrategy);
		STRATEGIES.put(6691, revenantStrategy);
		
		STRATEGIES.put(2000, new Venenatis());
		STRATEGIES.put(2006, new Vetion());
		STRATEGIES.put(2010, new Callisto());
		STRATEGIES.put(1999, new Cerberus());
		STRATEGIES.put(6766, new LizardMan());
		STRATEGIES.put(499, new Thermonuclear());
		STRATEGIES.put(7286, new Skotizo());
		STRATEGIES.put(5886, new Sire());
		STRATEGIES.put(10126, new UnholyCursebearer());
		STRATEGIES.put(7005, new WiseOldMan());
		STRATEGIES.put(7010, new WiseOldMan());
		
		
		STRATEGIES.put(7704, new JalTokJad());
		STRATEGIES.put(7702, new JalXil1());
		STRATEGIES.put(7703, new JalZek1());
		STRATEGIES.put(7750, new Healer());
		
	}
	
	public static CombatStrategy getStrategy(int npc) {
		if(STRATEGIES.get(npc) != null) {
			return STRATEGIES.get(npc);
		}
		return defaultMeleeCombatStrategy;
	}
	
	public static CombatStrategy getDefaultMeleeStrategy() {
		return defaultMeleeCombatStrategy;
	}

	public static CombatStrategy getDefaultMagicStrategy() {
		return defaultMagicCombatStrategy;
	}


	public static CombatStrategy getDefaultRangedStrategy() {
		return defaultRangedCombatStrategy;
	}
}
