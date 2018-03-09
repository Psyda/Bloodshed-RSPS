package com.ruseps.world.entity.impl.npc.bosses.ganodermic;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.*;
import com.ruseps.model.definitions.ItemDefinition;
import com.ruseps.model.definitions.NPCDrops;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.content.combat.*;
import com.ruseps.world.content.combat.strategy.CombatStrategy;
import com.ruseps.world.entity.impl.Character;
import com.ruseps.world.entity.impl.GroundItemManager;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.npc.NPCMovementCoordinator;
import com.ruseps.world.entity.impl.player.Player;
import io.netty.util.internal.ConcurrentSet;

import java.util.*;
import java.util.function.Consumer;

import static com.ruseps.world.entity.impl.npc.bosses.ganodermic.GanodermicConstants.GANDOMERIC_BEAST_MOB_ID;

/**
 * Created by Stan van der Bend on 17/05/17.
 * <p>
 * Project: near-reality-server
 * <p>
 * Package: arrav.world.global.content.combat.strategy.external.gandomeric
 * Time:    16:59
 * Year:    2017
 * Month:   05
 */
public class GanodermicBeastCombat implements CombatStrategy {

    public static NPC GANO;
    private static NPC DRUMMER_1;
    private static NPC DRUMMER_2;

    private static int spawnIndex;

    private static GandomericPhases attack;

    final static Set<NPC> MINIONS = new ConcurrentSet<>();

    public static void main(String[] args){
        ItemDefinition.init();
        NPCDrops.parseDrops().load();
        Arrays.stream(NPCDrops.forId(GANDOMERIC_BEAST_MOB_ID).getDropList())
                .forEach(npcDropItem -> {

                    ItemDefinition itemDefinition = ItemDefinition.forId(npcDropItem.getId());
                    System.out.println("Drop - "+itemDefinition.getName()+", chance - "+npcDropItem.getChance().name());

                });
    }

    private static void handleDrop(NPC npc) {

        World.getPlayers().forEach(p -> p.getPacketSender().sendString(26708, "@or2@Ganodermic Beast: @red@N/A"));

        if(npc.getCombatBuilder().getDamageMap().size() == 0)
            return;

        final Map<Player, Integer> killers = new HashMap<>();

        for(Map.Entry<Player, CombatBuilder.CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

            if(entry == null)
                continue;

            final long timeout = entry.getValue().getStopwatch().elapsed();

            if(timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT)
                continue;

            final Player player = entry.getKey();

            if(player.getConstitution() <= 0 || !player.isRegistered())
                continue;

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Map.Entry<Player, Integer>>result = sortEntries(killers);
        int count = 0;

        for(Map.Entry<Player, Integer> entry : result) {

            final Player killer = entry.getKey();
            final int damage = entry.getValue();

            handleDrop(npc, killer, damage);

            if(++count >= 5)
                break;
        }
    }

    private static void handleDrop(NPC npc, Player player, int damage) {
        final Position pos = npc.getPosition();
        giveLoot(player, npc, pos);
    }

    public static void loadDrops(){
        NPCDrops ganodrops = NPCDrops.getDrops().get(GANDOMERIC_BEAST_MOB_ID);
        if(ganodrops==null){
            System.out.println("[DROPS] Could not init Ganodermic drops!");
            return;
        }
        GanodermicConstants.COMMON.clear();
        GanodermicConstants.MEDIUM.clear();
        GanodermicConstants.RARE.clear();
        GanodermicConstants.SUPER_RARE.clear();
        Arrays.stream(ganodrops.getDropList()).forEach(drop -> {
            switch (drop.getChance()){
                case COMMON:
                case ALMOST_ALWAYS:
                case VERY_COMMON:
                    GanodermicConstants.COMMON.add(drop);
                case UNCOMMON:
                case NOTTHATRARE:
                    GanodermicConstants.MEDIUM.add(drop);
                case RARE:
                    GanodermicConstants.RARE.add(drop);
                case LEGENDARY:
                case LEGENDARY_2:
                case LEGENDARY_3:
                case LEGENDARY_4:
                case LEGENDARY_5:
                    GanodermicConstants.SUPER_RARE.add(drop);
            }
        });
        final int totalDrops = GanodermicConstants.COMMON.size() + GanodermicConstants.MEDIUM.size() + GanodermicConstants.RARE.size()+ GanodermicConstants.SUPER_RARE.size();
        System.out.println("[DROPS] Successfully loaded "+totalDrops+" Ganodermic Beast drops ["+ GANDOMERIC_BEAST_MOB_ID+"]");
    }

    private static void giveLoot(Player player, NPC npc, Position pos) {

        int chance = Misc.getRandom(15);



        GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(995, 7500000 + Misc.getRandom(15000000)), pos, player.getUsername(), false, 150, true, 200));
        if(chance > 14){
            //super RARE
            World.sendMessage("<col=B20000>"+player.getUsername()+ " received a super rare drop from the  Ganodermic Beast!");

            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.RARE).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.COMMON).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.SUPER_RARE).getItem(), pos, player.getUsername(), false, 150, true, 200));
            return;
        }
        if(chance > 13) {
            //RARE
            World.sendMessage("<col=CC0000>"+player.getUsername()+ " received a rare drop from the Ganodermic Beast!");

            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.RARE).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.RARE).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.RARE).getItem(), pos, player.getUsername(), false, 150, true, 200));
            return;
        }
        if(chance > 8) {
            //MEDIUM
            World.sendMessage("<col=E50000>"+player.getUsername()+ " received an uncommon drop from the Ganodermic Beast!");

            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.MEDIUM).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.MEDIUM).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.MEDIUM).getItem(), pos, player.getUsername(), false, 150, true, 200));
            return;
        }
        if(chance >= 0){
            //common
            World.sendMessage("<col=FF0000>"+player.getUsername()+ " received a common drop from the Ganodermic Beast!");

            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.COMMON).getItem(), pos, player.getUsername(), false, 150, true, 200));
            GroundItemManager.spawnGroundItem(player, new GroundItem(Misc.randomElement(GanodermicConstants.COMMON).getItem(), pos, player.getUsername(), false, 150, true, 200));
        }

    }
    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortEntries(Map<K, V> map) {
        final List<Map.Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }

    private final static Map<Player, Integer> PLAYER_DAMAGE_MAP = new HashMap<>();

    static Map<Player, Integer> getLastHitMap() {
        return LAST_HIT_MAP;
    }

    private final static Map<Player, Integer> LAST_HIT_MAP = new HashMap<>();


    private static Consumer<Player> performHitGraphic(final Graphic graphic){
        return character ->
                character.performGraphic(graphic);
    }

    /**
     * Creates a custom hit task that will <b>not</b> take into account item bonuses
     * and general stats of the involved characters while making the necessary calculations..
     *
     * @param icon  the {@link CombatIcon} used for displaying the correct {@link Hitmask}.
     * @param damageRange   the maximum damage range this hit can reach (on top of the {@link GandomericPhases}'s minimum damage boundary.)
     * @return custom damage behaviour that <b>does not</b> taking into account any variables in damage calculations.
     */
    private static Consumer<Player> createHitTask(final CombatIcon icon, final int damageRange){
        return player ->
                player.dealDamage(
                        new Hit(attack.minDamage() + Misc.getRandom(damageRange), Hitmask.RED, icon));}

    /**
     * Creates an {@link CombatHitTask} that takes into account the involved characters their stats
     * and item bonuses while doing the necessary damage calculations.
     *
     * @param type  the {@link CombatType} used for the {@link CombatIcon} and bonus calculations.
     * @return a {@link CombatHitTask} that <b>does</b> take into account variables in damage calculations.
     */
    private static Consumer<Player> createAccurateHitTask(final CombatType type){
        return player -> new CombatContainer(GANO, player, 1, type, true).dealDamage();
    }

    static final Consumer<Player>
            MELEE_ATTACK = createAccurateHitTask(CombatType.MELEE),
            MAGIC_ATTACK = performHitGraphic(GanodermicConstants.MAGIC_DAMAGE_GRAPHIC)
                            .andThen(
                                    createHitTask(CombatIcon.MAGIC, 200)),
            SHADOW_RANGED = performHitGraphic(GanodermicConstants.MAGIC_DAMAGE_GRAPHIC_2)
                    .andThen(player -> new Projectile(GANO, player, 1554, 44, 3, 40, 33, 0).send())
                    .andThen(player -> new Projectile(GANO, player, 1554, 33, 6, 50, 33, 10).send())
                    .andThen(player -> new Projectile(GANO, player, 1554, 22, 9, 60, 33, 20).send())
                    .andThen(createAccurateHitTask(CombatType.RANGED)),
            MAGIC_ATTACK_2 = performHitGraphic(new Graphic(1897))
                    .andThen(player -> new Projectile(GANO, player, 1899, 44, 3, 40, 33, 0).send())
                    .andThen(player -> new Projectile(GANO, player, 1554, 33, 6, 50, 33, 10).send())
                    .andThen(player -> new Projectile(GANO, player, 1899, 22, 9, 60, 33, 20).send())
                    .andThen(createAccurateHitTask(CombatType.RANGED)),
            MAGIC_ATTACK_3 = performHitGraphic(GanodermicConstants.MAGIC_DAMAGE_GRAPHIC)
                    .andThen(player -> new Projectile(GANO, player, 1196, 44, 3, 40, 33, 0).send())
                    .andThen(createAccurateHitTask(CombatType.MAGIC)),
            RANGED_ATTACK = performHitGraphic(GanodermicConstants.RANGED_DAMAGE_GRAPHIC)
                   // .andThen(player -> CustomObjects.zulrahToxicClouds( new GameObject(4926 + Misc.random(1), player.getPosition().copy().add(Misc.random(2), Misc.random(2))), player, 30))
                   // .andThen(player -> CustomObjects.zulrahToxicClouds( new GameObject(4926 + Misc.random(1), player.getPosition().copy().add(Misc.random(2), Misc.random(2))), player, 30))
                            .andThen(
                                    createAccurateHitTask(CombatType.RANGED))
                    ;



    public static void spawn(){

        LAST_HIT_MAP.clear();
        PLAYER_DAMAGE_MAP.clear();

        despawn();
        loadDrops();

        int previousSpawnIndex = spawnIndex;
        while (spawnIndex == previousSpawnIndex)
            spawnIndex = Misc.random(GanodermicConstants.SPAWN_POSITIONS.length-1);

        attack = GandomericPhases.MELEE;

        GANO = new NPC(GANDOMERIC_BEAST_MOB_ID, GanodermicConstants.SPAWN_POSITIONS[spawnIndex]);
        DRUMMER_1 = new NPC(GanodermicConstants.DRUMMER_MOB_ID_1, GanodermicConstants.DRUMMER_SPAWN_POSITIONS[spawnIndex][0]);
        DRUMMER_2 = new NPC(GanodermicConstants.DRUMMER_MOB_ID_2, GanodermicConstants.DRUMMER_SPAWN_POSITIONS[spawnIndex][1]);

        GANO.getMovementCoordinator().setCoordinator(new NPCMovementCoordinator.Coordinator(true, 3));

        World.register(GANO);
        World.register(DRUMMER_1);
        World.register(DRUMMER_2);

        World.sendMessage("@whi@[@red@Server@whi@]"+ GanodermicConstants.GLOBAL_SPAWN_MESSAGES[spawnIndex]);

        World.getPlayers().stream()
                .filter(GanodermicConstants.NON_NULL)
                .forEach(player -> player.getPacketSender()
                        .sendString(26708, "@or2@Ganodermic Beast: @gre@"+ GanodermicBeastCombat.getSpawnLocation()));

        System.out.println(GanodermicConstants.GLOBAL_SPAWN_MESSAGES[spawnIndex]);

    }


    public static void kill(NPC npc) {
        handleDrop(npc);
        despawn();
        TaskManager.submit(new Task(GanodermicConstants.RESPAWN_INTERVAL_DELAY, false) {
            @Override
            protected void execute() {
                if(GANO == null)
                    spawn();
                stop();
            }
        });
    }



    public static void takeDamage(Player from, int damage) {
        LAST_HIT_MAP.put(from, damage);

        if(PLAYER_DAMAGE_MAP.keySet().contains(from))
            PLAYER_DAMAGE_MAP.replace(from, PLAYER_DAMAGE_MAP.get(from) + damage);
        else
            PLAYER_DAMAGE_MAP.put(from, damage);

        setPhase();
    }

    public static boolean checkAttack(Player p, int npc) {
        if(npc == GANDOMERIC_BEAST_MOB_ID) {
            if(p.getSkillManager().getCurrentLevel(Skill.SLAYER) < 99){
                p.performAnimation(GanodermicConstants.FALL);
                p.sendMessage("@red@You need at least 99 slayer to attack the Ganodermic Beast!");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {

        if(victim == null || entity == null || GANO == null)
            return false;

        if(GANO.isChargingAttack())
            return true;

        if(attack.equals(GandomericPhases.MAGIC)) {
            GANO.setTransformationId(GanodermicConstants.GANDOMERIC_BEAST_TRANSFORMED_MOB_ID);
            GANO.performGraphic(new Graphic(1730));
        }

        GANO.setTransformationId(GANDOMERIC_BEAST_MOB_ID);
        GANO.getUpdateFlag().flag(Flag.TRANSFORM);

        GANO.performAnimation(attack.getAnimation());

        if(GANO != null && victim.isPlayer()){
            Misc.getCombinedPlayerList(victim.asPlayer()).stream()
                    .filter(GanodermicConstants.NON_NULL.and(player -> player.getPosition().isViewableFrom(GANO.getPosition())))
                    .forEach(attack);
            return true;
        }

      return false;
    }


    @Override public CombatContainer attack(Character entity, Character victim) {
        return null;
    }
    @Override public CombatType getCombatType() {
        return CombatType.MIXED;
    }

    @Override public boolean canAttack(Character entity, Character victim) { return !entity.asNpc().isChargingAttack(); }

    @Override public int attackDistance(Character entity) {
        return GanodermicConstants.ATTACK_DISTANCE;
    }
    @Override public int attackDelay(Character entity) {
        if(attack.equals(MAGIC_ATTACK))
            return 10;
        return 4+Misc.random(4);
    }

    public static boolean isGandomericNpc(int id){
        return id == GANDOMERIC_BEAST_MOB_ID;
    }
    public static Position getSpawnPosition() {
        return GanodermicConstants.SPAWN_POSITIONS[spawnIndex];
    }
    public static String getSpawnLocation(){
        return Optional.ofNullable(GanodermicConstants.LOCATIONS[spawnIndex]).orElse("N/A");
    }
    public static boolean isSpawned() {
        return GANO != null && GANO.isRegistered();
    }
    private static void setPhase(){
        if(GANO == null)
        {
            despawn();
            TaskManager.cancelTasks(GANO);
            return;
        }
        if(GANO.isChargingAttack() || attack == null){
            attack = GandomericPhases.MINIONS;
            return;
        }
        attack = attack.getPhaseFor(GANO.getConstitution());
    }
    private static void despawn() {

        if(GANO != null && GANO.isRegistered())
            World.deregister(GANO);

        if(DRUMMER_1 != null && DRUMMER_1.isRegistered())
            World.deregister(DRUMMER_1);

        if(DRUMMER_2 != null && DRUMMER_2.isRegistered())
            World.deregister(DRUMMER_2);

        MINIONS.forEach(World::deregister);
        MINIONS.clear();

        GANO = DRUMMER_1 = DRUMMER_2 = null;
    }


}
