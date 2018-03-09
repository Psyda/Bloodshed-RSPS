package com.ruseps.world.entity.impl.npc.bosses.ganodermic;

import com.ruseps.model.Animation;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.Projectile;
import com.ruseps.world.content.combat.CombatBuilder;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.content.combat.prayer.PrayerHandler;
import com.ruseps.world.entity.impl.player.Player;
import com.ruseps.GameSettings;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.IntPredicate;

import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicBeastCombat.*;
import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicConstants.PRAYER_DAMAGE_BOUNDARY;
import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicConstants.PRAYER_DRAIN_AMOUNT_BOUNDARY;
import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicConstants.PRAYER_DRAIN_ODDS;


/**
 * Created by Stan van der Bend on 17/05/17.
 * <p>
 * Project: near-reality-server
 * <p>
 * Package: arrav.world.global.content.combat.strategy.external.gandomeric
 * Time:    18:59
 * Year:    2017
 * Month:   05
 */

enum GandomericPhases implements Consumer<Player> {

        MELEE(100, GanodermicConstants.GLOBAL_COMBAT_MESSAGES[0], GanodermicConstants.MELEE_ATTACK_ANIMATION) {
            @Override public void onStart() { }
            @Override public void accept(Player player) {
                MELEE_ATTACK.accept(player);
                if(PrayerHandler.isActivated(player, PrayerHandler.getProtectingPrayer(CombatType.MELEE))) {
                    if (GanodermicBeastCombat.getLastHitMap().containsKey(player)) {
                        final int damage = GanodermicBeastCombat.getLastHitMap().get(player);
                        if (Misc.random(PRAYER_DRAIN_ODDS) == 1) {
                            final CombatBuilder combatBuilder = player.getCombatBuilder();
                            combatBuilder.reset(true);
                            combatBuilder.setAttackTimer(DELAY);
                            if (damage > PRAYER_DAMAGE_BOUNDARY) {
                                PrayerHandler.deactivateAll(player);
                                player.performAnimation(GanodermicConstants.FALL);
                                player.sendMessage(GanodermicConstants.PRAYER_DISABLED);
                            } else {
                                final int amount = PRAYER_DRAIN_AMOUNT_BOUNDARY + Misc.random(damage);
                                player.performAnimation(GanodermicConstants.DRAINED);
                                player.sendMessage(GanodermicConstants.PRAYER_DRAINED_BY + amount + " points!");
                                PrayerHandler.drain(player, amount);
                                getDrainProjectile(player).send();
                            }
                        }
                    }
                }
            }
        },
        MAGIC(30, GanodermicConstants.GLOBAL_COMBAT_MESSAGES[1], GanodermicConstants.CHARGE) {
            @Override public void onStart() {}
            @Override public void accept(Player player) {
                if(Misc.random(10) > 7)
                    MAGIC_ATTACK_3.accept(player);
                else
                    MAGIC_ATTACK.accept(player);

            }
        },
        MAGIC_2(100, "@red@Ganodermic Beast unleashes his energy!", new Animation(15465)) {
            @Override public void onStart() {
                GANO.performGraphic(new Graphic(2009, GraphicHeight.MIDDLE));
            }
            @Override public void accept(Player player) {
                MAGIC_ATTACK_2.accept(player);
            }
        },
        RANGED(100, GanodermicConstants.GLOBAL_COMBAT_MESSAGES[2], GanodermicConstants.CHARGE) {
            @Override public void onStart() {}
            @Override public void accept(Player player) {
                RANGED_ATTACK.accept(player);
            }
        },
        MINIONS(0, "@red@Ganodermic Beast summons his runts!"){
            @Override public void onStart() {
                if(!GANO.isChargingAttack())
                    TaskManager.submit(new SpawnMinions());
            }
            @Override public void accept(Player player) {}
        };

    private String message;
    private Animation animation;
    private int minimumDamageBound;

    GandomericPhases(int minimumDamageBound, String message, Animation animation) {
        this.minimumDamageBound = minimumDamageBound;
        this.message = message;
        this.animation = animation;
    }
    GandomericPhases(int minimumDamageBound, String message) {
        this.minimumDamageBound = minimumDamageBound;
        this.message = message;
    }

    public abstract void onStart();

    public static final int DELAY = 5;
    private final static Map<Integer, GandomericPhases> PHASES = new HashMap<>();

    static {
        PHASES.clear();
        PHASES.put(100, MELEE);
        PHASES.put(95, MAGIC_2);
        PHASES.put(90, MAGIC);
        PHASES.put(86, MINIONS);
        PHASES.put(80, RANGED);
        PHASES.put(70, MAGIC);
        PHASES.put(64, MELEE);
        PHASES.put(58, MAGIC_2);
        PHASES.put(55, MINIONS);
        PHASES.put(50, RANGED);
        PHASES.put(40, MELEE);
        PHASES.put(30, MAGIC);
        PHASES.put(20, RANGED);
        PHASES.put(13, MAGIC_2);
        PHASES.put(10, MAGIC);
        PHASES.put(8, MINIONS);
        PHASES.put(5, MELEE);
    }

    private static int getPercentage(double hp){
        return (int)Math.abs(((hp- GanodermicConstants.CONSTITUTION)/ GanodermicConstants.CONSTITUTION)*100);
    }

    private IntPredicate findClosest(int x) {
        return n -> n > x;
    }

    private static Projectile getDrainProjectile(Player player) {
        return new Projectile(GANO, player, 2263, 44, 3, 43, 43, 0);
    }

    protected GandomericPhases getPhaseFor(int currentHp){

        if(GANO.isChargingAttack())
            return MINIONS;

        final int percentage = getPercentage(currentHp);
        final OptionalInt optionalInt = PHASES.keySet().stream().mapToInt(Integer::intValue).filter(findClosest(percentage)).min();

        if(optionalInt.isPresent())
        {
            final int value = optionalInt.getAsInt();
            if(GameSettings.GAME_PORT == 43596){
                World.sendMessage("[Debug] -> Ganodermic phase = "+this.name()+" | optional = "+value+", HP "+currentHp+" | perc = "+percentage);
            }
            final Optional<GandomericPhases> phases = Optional.ofNullable(PHASES.get(value));
            if(phases.isPresent()){
                final GandomericPhases nextPhase = phases.get();
                if (nextPhase != this) {
                    World.sendMessage(message +" ("+getPercentage(currentHp)+"/100)");
                    GANO.forceChat(GanodermicConstants.SHOUTS[Misc.random(GanodermicConstants.SHOUTS.length-1)]);
                    nextPhase.onStart();
                    return nextPhase;
                }
            }
        }
        return this;
    }

    public Animation getAnimation() {
        return animation;
    }
    public String getMessage() {
        return message;
    }

    public int minDamage() {
        return minimumDamageBound;
    }

}