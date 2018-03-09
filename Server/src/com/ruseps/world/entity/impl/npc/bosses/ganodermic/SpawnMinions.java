package com.ruseps.world.entity.impl.npc.bosses.ganodermic;

import com.ruseps.engine.task.Task;
import com.ruseps.model.*;
import com.ruseps.model.definitions.WeaponAnimations;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.content.combat.prayer.PrayerHandler;
import com.ruseps.world.entity.Entity;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.player.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicBeastCombat.GANO;
import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicBeastCombat.MINIONS;


/**
 * Created by stanbend on 29/06/2017.
 */
public class SpawnMinions extends Task {

    private final static Collection<Position> optionalSpawnSpots = new HashSet<>();

    private boolean started = false;
    private int ticks = 0;


    public SpawnMinions() {
        super(true);
        bind(GANO);
    }

    @Override
    public void stop() {
        GANO.setChargingAttack(false);
        started = false;
        super.stop();
    }
    private static Projectile getDrainProjectile(Entity entity) {
        return new Projectile(entity, GANO, 2263, 44, 3, 43, 43, 0);
    }
    @Override
    protected void execute() {

        if(getPlayers().isEmpty())
            stop();

        if(!GANO.isChargingAttack()){
            GANO.getCombatBuilder().reset(true);
            GANO.setChargingAttack(true);
            GANO.performAnimation(GanodermicConstants.CHARGE);
            GANO.setNpcTransformationId(GanodermicConstants.GANDOMERIC_BEAST_MOB_ID);
            getPlayers().forEach(SEND_CHARGE_NOTIFICATION);
            optionalSpawnSpots.clear();
            getPlayers().forEach(player -> optionalSpawnSpots.add(player.getPosition().copy().add(Misc.random(2), Misc.random(2))));
            started = true;
        }
        if(started){
            final int previous_trans_id = GANO.getTransformationId();
            switch (previous_trans_id){
                case GanodermicConstants.GANDOMERIC_BEAST_MOB_ID:
                    GANO.performGraphic(new Graphic(1555));
                    GANO.setTransformationId(GanodermicConstants.GANDOMERIC_BEAST_MOB_ID_RESIZED);
                    break;
                case GanodermicConstants.GANDOMERIC_BEAST_MOB_ID_RESIZED:
                    GANO.setTransformationId(GanodermicConstants.GANDOMERIC_BEAST_TRANSFORMED_MOB_ID);
                    break;
                case GanodermicConstants.GANDOMERIC_BEAST_TRANSFORMED_MOB_ID:
                    GANO.performAnimation((new Animation(15465)));
                    getPlayers().forEach(player -> new Projectile(GANO, player, 2001, 44, 3, 43, 43, 0).send());
                    getPlayers().forEach(player -> new Projectile(GANO, player, 2001, 34, 2, 48, 43, 5).send());
                    getPlayers().forEach(player -> new Projectile(GANO, player, 2001, 24, 1, 39, 43, 10).send());
                    GANO.performGraphic(new Graphic(1555));
                    GANO.setTransformationId(GanodermicConstants.GANDOMERIC_BEAST_TRANSFORMED_MOB_ID_RESIZED);
                    break;
                case GanodermicConstants.GANDOMERIC_BEAST_TRANSFORMED_MOB_ID_RESIZED:
                    GANO.performGraphic(new Graphic(1730));
                    GANO.setTransformationId(GanodermicConstants.GANDOMERIC_BEAST_MOB_ID);
                    getPlayers().forEach(player -> {
                        if(Misc.random(10) > 5) {
                            player.performGraphic(new Graphic(606));
                            player.dealDamage(new Hit(100+Misc.random(300), Hitmask.CRITICAL, CombatIcon.MAGIC));
                            player.performAnimation(GanodermicConstants.FALL);
                            player.sendMessage("You got hit extremely hard, your faith is destroyed.");
                            PrayerHandler.deactivateAll(player);
                        } else {
                            player.performGraphic(new Graphic(607));
                            player.sendMessage("Ganodermic Beast just drained your prayer!");
                            PrayerHandler.drain(player,Misc.random((int) ((player.getSkillManager().getCurrentLevel(Skill.PRAYER)/10D)+1)) );
                            getDrainProjectile(player).send();
                        }
                    });
                    break;
            }
            if(previous_trans_id != GANO.getTransformationId())
                GANO.getUpdateFlag().flag(Flag.TRANSFORM);
            switch (ticks){
                case 7:
                    final NPC runt_1 = new NPC(GanodermicConstants.GANODERMIC_RUNT_1, Misc.random(optionalSpawnSpots).copy().add(-1, 0));
                    final NPC runt_2 = new NPC(GanodermicConstants.GANODERMIC_RUNT_2, Misc.random(optionalSpawnSpots).copy().add(1, 0));
                    World.register(runt_1);
                    World.register(runt_2);
                    runt_1.getDefinition().setAttackAnimation(WeaponAnimations.getAttackAnimation("whip"));
                    runt_2.getDefinition().setAttackAnimation(WeaponAnimations.getAttackAnimation("staff"));
                    runt_1.performAnimation(new Animation(11043));
                    runt_2.performAnimation(new Animation(11043));
                    runt_1.performGraphic(GanodermicConstants.MAGIC_DAMAGE_GRAPHIC);
                    runt_2.performGraphic(GanodermicConstants.MAGIC_DAMAGE_GRAPHIC);
                    getDrainProjectile(runt_1).send();
                    getDrainProjectile(runt_2).send();
                    MINIONS.add(runt_1);
                    MINIONS.add(runt_2);
                    break;
                case 15:
                    stop();
                    break;
            }
            ticks++;
        }
    }
    private static final Consumer<Player> SEND_CHARGE_NOTIFICATION = player -> player.sendMessage("@red@The Ganodermic Beast is charging!");

    private Set<Player> getPlayers() {
        return GANO.getCombatBuilder().getDamageMap().keySet();
    }
}
