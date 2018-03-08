package com.ruseps.world.entity.impl.npc.bosses.zulrah;

import com.ruseps.engine.task.Task;
import com.ruseps.model.Animation;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.Position;
import com.ruseps.model.Skill;
import com.ruseps.util.Misc;
import com.ruseps.world.content.combat.CombatContainer;
import com.ruseps.world.content.combat.CombatHitTask;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.world.entity.impl.Character;
/**
 * Created by Stan van der Bend on 19/07/2017.
 */
public final class  ZulrahTasks {

    final static Object key = new Object();

    static CombatContainer createCombatContainer(final Character attacker, final Character victim, final CombatType combatType){
        return new CombatContainer(attacker, victim, 1, combatType, true);
    }
    static CombatHitTask createCombatHitTask(final Character attacker, final Character victim){
        return new CombatHitTask(attacker.getCombatBuilder(), createCombatContainer(attacker, victim, CombatType.MELEE));
    }
    public static Task createMeleeHit(final Zulrah zulrah, final Player player){
        final Position meleeHitPosition = player.getPosition().copy();
        zulrah.setMeleeAttack(true);
        zulrah.setChargingAttack(true);
        final Task task = new Task() {

            int ticks = 0;

            @Override
            protected void execute() {

                if(!player.isRegistered() || !(player.getRegionInstance() instanceof ZulrahInstance) || zulrah.getConstitution() <= 0){
                    stop();
                    return;
                }
               // zulrah.forceChat("cycle "+ticks);

                zulrah.setPositionToFace(player.getPosition());

                if(ticks == 0) {
                    zulrah.performAnimation(Zulrah.MELEE_TARGET);
                }

                if(ticks == 5){
                   // zulrah.forceChat("strike");

                    final boolean shouldHitPlayer = player.getPosition().getDistance(meleeHitPosition) <= 3;

                    if(shouldHitPlayer){
                        player.performGraphic(new Graphic(254, GraphicHeight.HIGH));
                        final int decrease = (int) (0.10 * (player.getSkillManager().getCurrentLevel(Skill.ATTACK)));
                        player.getSkillManager().setCurrentLevel(Skill.ATTACK, player.getSkillManager().getCurrentLevel(Skill.ATTACK) - decrease);
                        player.getSkillManager().updateSkill(Skill.ATTACK);
                        player.getPacketSender().sendMessage(
                                "You feel slightly weakened.");

                        zulrah.setPositionToFace(player.getPosition());
                       if(Misc.random(10) > 5){
                           player.performAnimation(new Animation(424));
                           player.performGraphic(new Graphic(80, 5, GraphicHeight.HIGH));
                           player.getPacketSender().sendMessage("You've been stunned.");
                           player.lock(10);
                       }
                        createCombatHitTask(zulrah, player).handleAttack();
                    }
                    zulrah.setMeleeAttack(false);
                    zulrah.setChargingAttack(false);
                    stop();
                }
                ticks++;
            }
        };
        return task.bind(zulrah);
    }


}
