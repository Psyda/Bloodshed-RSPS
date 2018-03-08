package com.ruseps.world.entity.impl.npc.bosses.zulrah;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.*;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.content.combat.CombatType;
import com.ruseps.world.content.combat.effect.EquipmentBonus;
import com.ruseps.world.content.combat.strategy.CombatStrategy;
import com.ruseps.world.entity.Entity;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.player.Player;

import java.util.ArrayList;

/**
 * Created by Stan van der Bend on 19/07/2017.
 * <p>
 * https://www.rune-server.ee/members/icebrick/
 */
public class Zulrah extends NPC {

    public static final Animation ZULRAH_SPAWN_POISON = new Animation(5069);
    public static final Animation SNAKELING_SPAWN_ANIMATION = new Animation(5069);
    public static final Animation MELEE_TARGET = new Animation(5806);
    public static final Animation ZULRAH_DIVE_ANIMATION = new Animation(5072);
    public static final Animation ZULRAH_RISE_ANIMATION = new Animation(5073);
    public static final Animation PROJECTILE_ATTACK = SNAKELING_SPAWN_ANIMATION;
    private static int ID = 2042;
    public static final Position SPAWN_LOCATION = new Position(2268, 3072, 0);
    public static final Position START_LOCATION = new Position(2268, 3069, 0);
    private static final int[][] VENOM_CLOUD_LOCATIONS = new int[][]{
            {2267, 3068},
            {2264, 3069},
            {2262, 3073},
            {2272, 3069},
            {2272, 3073}
    };

    private ZulrahColour colour = ZulrahColour.GREEN;
    private ZulrahState state = ZulrahState.ANIMATING;
    private Behaviour behaviour;

    private boolean meleeAttacking;
    private boolean meleeAttack;
    private boolean jadSwitch = false;

    /**
     * Creates a new {@link Zulrah} {@link NPC} at {@link Position} {SPAWN_LOCATION}
     */
    public Zulrah(int height) {
        super(ID, SPAWN_LOCATION.copy().setZ(height));
        getNewBehaviour();
        strategy = ZulrahCombat.RANGED_ATTACK;

    }

    public void hit(Entity target) {
        if (target instanceof Player) {
            Player player = (Player) target;

            if (diveTimer != -1)
                return;
            if (getConstitution() == 0)
                return;
            if (state == ZulrahState.ANIMATING || state == ZulrahState.SHOOTING_FUMES)
                return;
            if ((Misc.random(3) == 1 && colour != ZulrahColour.RED)) {
                if (spawnPoison(player))
                    return;
            }
            setEntityInteraction(player);
            if (behaviourStage == behaviour.switchPosition) {
                jadSwitch = !jadSwitch;
                if (jadSwitch)
                    rangedAttack();
                else
                    magicAttack();
                return;
            }
            if (colour == ZulrahColour.GREEN) {
                rangedAttack();
            }
            if (colour == ZulrahColour.BLUE) {
                magicAttack();
            }
            if (colour == ZulrahColour.RED)
                meleeAttack();
        }
    }

    private ZulrahCombat strategy;

    @Override
    public CombatStrategy determineStrategy() {

        switch (colour) {
            case RED:
                return ZulrahCombat.MELEE_ATTACK;
            case GREEN:
                return ZulrahCombat.RANGED_ATTACK;
            case BLUE:
                return Misc.random(4) == 1 ? ZulrahCombat.RANGED_ATTACK : ZulrahCombat.MAGIC_ATTACK;
        }

        return strategy;
    }

    private void meleeAttack() {
        this.setChargingAttack(false);
        strategy = ZulrahCombat.MELEE_ATTACK;
    }

    private void rangedAttack() {
        strategy = ZulrahCombat.RANGED_ATTACK;
    }

    private void magicAttack() {
        strategy = ZulrahCombat.MAGIC_ATTACK;
    }

    private ArrayList<Position> poisonSpots = new ArrayList<>();

    public boolean spawnPoison(Player player) {

        if (getConstitution() <= 0)
            return false;

        if (poisonSpots.size() != 0)
            return false;

        if (state == ZulrahState.ANIMATING || state == ZulrahState.SHOOTING_FUMES)
            return false;

        state = ZulrahState.SHOOTING_FUMES;
        meleeAttacking = true;

        final Zulrah zulrah = this;

        TaskManager.submit(new Task(5) {
            int ticks = 0;

            @Override
            protected void execute() {
                if (!player.isRegistered() || !(player.getRegionInstance() instanceof ZulrahInstance)
                        || getConstitution() <= 0) {
                    stop();
                    return;
                }

                if (ticks == 5) {
                    state = ZulrahState.NORMAL;
                    meleeAttacking = false;
                    stop();
                    return;
                }

                zulrah.forceChat("SPAWING POISON");

                zulrah.performAnimation(ZULRAH_SPAWN_POISON);

                int randomX = VENOM_CLOUD_LOCATIONS[ticks][0] - 1 + Misc.random(2);
                int randomY = VENOM_CLOUD_LOCATIONS[ticks][1] - 1 + Misc.random(2);

                final Position location = new Position(randomX, randomY, zulrah.getPosition().getZ());
                zulrah.setPositionToFace(location);

                final NPC dummy = new NPC(1, location);
                final GameObject object = new GameObject(11700, location, 10, 3);

                TaskManager.submit(new Task() {
                    int ticks2 = 0;

                    @Override
                    public void execute() {
                        if (!player.isRegistered() || !(player.getRegionInstance() instanceof ZulrahInstance)) {
                            stop();
                            return;
                        }
                        if (ticks2 == 0) {
                            World.register(dummy);
                            zulrah.setEntityInteraction(dummy);
                            zulrah.setPositionToFace(dummy.getPosition());
                            FX.createToxicProjectile(zulrah, dummy).sendProjectile();
                        }

                        if (ticks2 == 3)
                            dummy.performGraphic(new Graphic(677, GraphicHeight.LOW));

                        if (ticks2 == 4) {
                            poisonSpots.add(location);
                            World.deregister(dummy);
                            World.register(object);
                            // System.out.println(" registering");
                        }
                        if (ticks2 == 35) {
                            poisonSpots.remove(location);
                            World.deregister(object);
                            stop();
                        } else if (ticks2 > 4) {
                            if (player.getPosition().getX() >= location.getX() && player.getPosition().getX() <= location.getX() + 2
                                    && player.getPosition().getY() >= location.getY() && player.getPosition().getY() <= location.getY() + 2) {
                                zulrah.venom(player);
                                player.dealDamage(new Hit(10 + Misc.random(30), Hitmask.DARK_GREEN, CombatIcon.NONE));
                                player.sendMessage("You are hit by the venom.");
                            }
                            // recently rewrote dis, uhm. Might be easier to just fix
                            // what changes were made? when did it last get worked on? ben is telling me the smoke it does dont appear but still takes affect
                        }
                        ticks2++;
                    }
                });
                ticks++;
            }
        });
        return true;
    }

    public void venom(Player player) {
        if (player.isVenomed())
            return;
        if (EquipmentBonus.isWearingVenomProtection(player))
            return;
        player.sendMessage("You have been infected with venom.");
        venomVictim(player, CombatType.MAGIC);
        TaskManager.submit(new Task() {
            int ticks;

            @Override
            public void execute() {
                if (!player.isRegistered() || !(player.getRegionInstance() instanceof ZulrahInstance)) {
                    stop();
                    return;
                }
                if (!player.isVenomed()) {
                    stop();
                    return;
                }
                player.setVenomDamage(player.getVenomDamage() + 6);

                if (player.getVenomDamage() > 20)
                    player.setVenomDamage(20);

                if (ticks == 84)
                    stop();

                ticks++;
            }

        });
    }

    private int behaviourStage = 0;
    private int behaviourTimer = 0;
    private int diveTimer = -1;

    @Override
    public void sequence() {

        super.sequence();

        if (getRegionInstance() == null) {
            World.deregister(this);
            return;
        }

        if (!meleeAttacking)
            if (!getCombatBuilder().isAttacking())
                getCombatBuilder().attack(getSpawnedFor());


        if (getTransformationId() != colour.npc) {
            setTransformationId(colour.npc);
            getUpdateFlag().flag(Flag.TRANSFORM);
            getCombatBuilder().determineStrategy();
        }
        //forceChat("dive_timer["+diveTimer+"], behaviour_timer["+behaviourTimer+"] behaviour_stage["+behaviourStage+"], state["+state+"]");

        if (diveTimer != -1) {

            if (diveTimer == 7) {

                if (behaviourStage >= behaviour.locations.length - 1) {
                    behaviourStage = -1;
                    getNewBehaviour();
                }

                behaviourStage++;

                moveTo(new Position(behaviour.locations[behaviourStage].x, behaviour.locations[behaviourStage].y).setZ(getPosition().getZ()));

                colour = behaviour.colours[behaviourStage];
            }

            if (diveTimer == 8)
                performAnimation(ZULRAH_RISE_ANIMATION);

            if (diveTimer == 10) {
                state = ZulrahState.NORMAL;
                diveTimer = -1;
            } else
                diveTimer++;
        }

        if (getState().equals(ZulrahState.NORMAL) && getConstitution() > 0) {

            if (behaviourTimer >= 25 + Misc.random(20)) {
                behaviourTimer = 0;

                performAnimation(ZULRAH_DIVE_ANIMATION);

                state = ZulrahState.ANIMATING;
                diveTimer = 0;
            } else
                behaviourTimer++;
        }

        if (getCombatBuilder().getVictim() == null)
            getCombatBuilder().setVictim(getEnemy());

        if (getCombatBuilder().getVictim() instanceof Player && state == ZulrahState.NORMAL)
            setEntityInteraction(getCombatBuilder().getVictim());

        if (getCombatBuilder().getAttackTimer() <= 0 && state == ZulrahState.NORMAL)
            hit(getCombatBuilder().getVictim());

    }

    private Player getEnemy() {
        if (getRegionInstance() == null)
            return null;
        if (getRegionInstance().getPlayersList() == null)
            return null;
        if (getRegionInstance().getPlayersList().size() == 0)
            return null;
        Player p = getRegionInstance().getPlayersList().get(0);

        if (!p.isRegistered())
            return null;

        return p;
    }

    private void getNewBehaviour() {
        Behaviour b = Behaviour.behaviours[Misc.random(Behaviour.behaviours.length - 1)];
        while (b == behaviour)
            b = Behaviour.behaviours[Misc.random(Behaviour.behaviours.length - 1)];
        behaviour = b;
    }


    /**
     * -   Getters    -
     */

    public static Position getStartLocation() {
        return START_LOCATION;
    }

    public ZulrahState getState() {
        return state;
    }

    public boolean isMeleeAttack() {
        return meleeAttack;
    }

    /**
     * -   Setters    -
     */

    public void setState(ZulrahState state) {
        this.state = state;
    }

    public void setColour(ZulrahColour colour) {
        this.colour = colour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void setMeleeAttack(boolean meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public enum ZulrahColour {

        GREEN(2042), BLUE(2044), RED(2043);

        public int getNpc() {
            return npc;
        }

        public int npc;

        ZulrahColour(int npc) {
            this.npc = npc;
        }
    }

    public enum Behaviour {
        FIRST(new PossibleLocation[]{
                PossibleLocation.MIDDLE, PossibleLocation.MIDDLE,
                PossibleLocation.MIDDLE, PossibleLocation.DOWN,
                PossibleLocation.MIDDLE, PossibleLocation.LEFT,
                PossibleLocation.DOWN, PossibleLocation.DOWN,
                PossibleLocation.LEFT, PossibleLocation.MIDDLE,
        }, new ZulrahColour[]{
                ZulrahColour.GREEN, ZulrahColour.RED, ZulrahColour.BLUE,
                ZulrahColour.GREEN, ZulrahColour.RED, ZulrahColour.BLUE,
                ZulrahColour.GREEN, ZulrahColour.BLUE, ZulrahColour.GREEN,
                ZulrahColour.RED,
        }, 8),
        SECOND(new PossibleLocation[]{
                PossibleLocation.MIDDLE, PossibleLocation.MIDDLE,
                PossibleLocation.MIDDLE, PossibleLocation.LEFT,
                PossibleLocation.DOWN, PossibleLocation.MIDDLE,
                PossibleLocation.RIGHT, PossibleLocation.DOWN,
                PossibleLocation.LEFT, PossibleLocation.MIDDLE,
        }, new ZulrahColour[]{
                ZulrahColour.GREEN, ZulrahColour.RED, ZulrahColour.BLUE,
                ZulrahColour.GREEN, ZulrahColour.BLUE, ZulrahColour.RED,
                ZulrahColour.GREEN, ZulrahColour.BLUE, ZulrahColour.GREEN,
                ZulrahColour.RED,
        }, 8),
        THIRD(new PossibleLocation[]{
                PossibleLocation.MIDDLE, PossibleLocation.RIGHT,
                PossibleLocation.MIDDLE, PossibleLocation.LEFT,
                PossibleLocation.DOWN, PossibleLocation.RIGHT,
                PossibleLocation.MIDDLE, PossibleLocation.LEFT,
                PossibleLocation.MIDDLE, PossibleLocation.RIGHT,
                PossibleLocation.MIDDLE
        }, new ZulrahColour[]{
                ZulrahColour.GREEN, ZulrahColour.GREEN, ZulrahColour.RED,
                ZulrahColour.BLUE, ZulrahColour.GREEN, ZulrahColour.BLUE,
                ZulrahColour.GREEN, ZulrahColour.GREEN, ZulrahColour.BLUE,
                ZulrahColour.GREEN, ZulrahColour.BLUE
        }, 9),
        FOURTH(new PossibleLocation[]{
                PossibleLocation.MIDDLE, PossibleLocation.RIGHT,
                PossibleLocation.DOWN, PossibleLocation.LEFT,
                PossibleLocation.MIDDLE, PossibleLocation.RIGHT,
                PossibleLocation.DOWN, PossibleLocation.LEFT,
                PossibleLocation.MIDDLE, PossibleLocation.MIDDLE,
                PossibleLocation.RIGHT, PossibleLocation.MIDDLE
        }, new ZulrahColour[]{
                ZulrahColour.GREEN, ZulrahColour.BLUE, ZulrahColour.GREEN,
                ZulrahColour.BLUE, ZulrahColour.RED, ZulrahColour.GREEN,
                ZulrahColour.GREEN, ZulrahColour.BLUE, ZulrahColour.GREEN,
                ZulrahColour.BLUE, ZulrahColour.GREEN, ZulrahColour.BLUE,
        }, 8);
        public PossibleLocation[] locations;
        public ZulrahColour[] colours;
        public int switchPosition;

        Behaviour(PossibleLocation[] locations, ZulrahColour[] colours, int switchPosition) {
            this.locations = locations;
            this.colours = colours;
            this.switchPosition = switchPosition;
        }

        public static Behaviour[] behaviours = values();
    }

    enum PossibleLocation {
        MIDDLE(2268, 3072),
        LEFT(2258, 3073),
        RIGHT(2275, 3070),
        DOWN(2268, 3065);
        public int x, y;

        PossibleLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
