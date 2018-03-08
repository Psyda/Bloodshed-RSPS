package com.ruseps.world.entity.impl.npc.bosses.zulrah;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.Projectile;
import com.ruseps.util.Misc;
import com.ruseps.world.content.combat.CombatContainer;
import com.ruseps.world.content.combat.CombatHitTask;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.content.combat.strategy.CombatStrategy;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.world.entity.impl.Character;
/**
 * Created by Stan van der Bend on 19/07/2017.
 *
 * https://www.rune-server.ee/members/icebrick/
 */
public enum  ZulrahCombat implements CombatStrategy{

    MELEE_ATTACK {
        @Override
        public boolean customContainerAttack(Zulrah zulrah, Player player) {

            if(!zulrah.isMeleeAttack())
                TaskManager.submit(ZulrahTasks.createMeleeHit(zulrah, player));
            else zulrah.forceChat("Already attacking");
            return false;
        }

        @Override
        public int attackDelay(Character entity) {
            return 13;
        }

        @Override
        public CombatType getCombatType() {
            return CombatType.MELEE;
        }
    },
    RANGED_ATTACK {

        @Override
        public boolean customContainerAttack(Zulrah zulrah, Player player) {

            if(Misc.random(4) == 1){
                zulrah.performAnimation(Zulrah.PROJECTILE_ATTACK);
                FX.spawnSnakelings(zulrah, player);
            } else {

                zulrah.performAnimation(Zulrah.PROJECTILE_ATTACK);

                new Projectile(zulrah, player, 676, 68, 33, 56, 28, 45).sendProjectile();

                TaskManager.submit(new Task(2, false) {
                    @Override
                    protected void execute() {
                        player.performGraphic(new Graphic(677, GraphicHeight.HIGH));
                        stop();
                    }
                });

                TaskManager.submit(new CombatHitTask(zulrah.getCombatBuilder(),
                        createCombatContainer
                                (zulrah, player, CombatType.RANGED), 2, false));
            }
            return false;
        }

        @Override
        public int attackDelay(Character entity) {
            return 6;
        }

        @Override
        public CombatType getCombatType() {
            return CombatType.RANGED;
        }
    },
    MAGIC_ATTACK {

        @Override
        public boolean customContainerAttack(Zulrah zulrah, Player player) {

            if(Misc.random(3) == 1){
                zulrah.spawnPoison(player);
            } else {

                TaskManager.submit(new Task(false) {
                    @Override
                    protected void execute() {
                        zulrah.performAnimation(Zulrah.PROJECTILE_ATTACK);
                        stop();
                    }
                });

                new Projectile(zulrah, player, 135, 68, 33, 56, 28, 45).sendProjectile();

                TaskManager.submit(new CombatHitTask(zulrah.getCombatBuilder(),
                        createCombatContainer(zulrah, player, CombatType.MAGIC), 2, false));
            }
            return false;
        }

        @Override
        public int attackDelay(Character entity) {
            return 6;
        }

        @Override
        public CombatType getCombatType() {
            return CombatType.MAGIC;
        }

    }

    ;

    public abstract boolean customContainerAttack(final Zulrah zulrah, final Player player);

    static CombatContainer createCombatContainer(final Character attacker, final Character victim, final CombatType combatType){
        return new CombatContainer(attacker, victim, 1, combatType, true);
    }

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {
        if(victim.isPlayer())
            if (entity.isNpc())
                if (entity instanceof Zulrah)
                    customContainerAttack((Zulrah) entity, victim.asPlayer());
        return false;
    }

    @Override
    public boolean canAttack(Character entity, Character victim) {
        if(victim.isPlayer())
            if(entity.isNpc())
                if (entity instanceof Zulrah)
                    return victim.getRegionInstance() instanceof ZulrahInstance && ((Zulrah)entity).getState().equals(ZulrahState.NORMAL);
        return false;
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }

    @Override
    public int attackDistance(Character entity) {
        return 25;
    }


}
