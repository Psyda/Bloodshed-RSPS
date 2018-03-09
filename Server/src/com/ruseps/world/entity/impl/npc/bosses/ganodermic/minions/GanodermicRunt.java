package com.ruseps.world.entity.impl.npc.bosses.ganodermic.minions;

import com.ruseps.util.Misc;
import com.ruseps.world.content.combat.CombatContainer;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.content.combat.strategy.CombatStrategy;
import com.ruseps.world.entity.impl.Character;

/**
 * Created by Stan van der Bend on 07/07/2017.
 */
public class GanodermicRunt implements CombatStrategy {

    @Override
    public boolean canAttack(Character entity, Character victim) {
        return true;
    }
    @Override
    public CombatContainer attack(Character entity, Character victim) {
        if(entity.asNpc().getId() == 2964)
            return new CombatContainer(entity, victim, CombatType.MELEE, true);
         else return new CombatContainer(entity, victim, CombatType.MAGIC, true);
    }
    @Override public boolean customContainerAttack(Character entity, Character victim) {
        return false;
    }
    @Override public int attackDelay(Character entity) {
        return 2+ Misc.random(2);
    }
    @Override public int attackDistance(Character entity) {
        return 2;
    }
    @Override public CombatType getCombatType() {
        return CombatType.MIXED;
    }
}
