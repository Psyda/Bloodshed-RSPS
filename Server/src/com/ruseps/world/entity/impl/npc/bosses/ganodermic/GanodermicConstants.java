package com.ruseps.world.entity.impl.npc.bosses.ganodermic;


import com.ruseps.model.Animation;
import com.ruseps.model.Graphic;
import com.ruseps.model.Position;
import com.ruseps.model.definitions.NPCDrops;
import com.ruseps.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Stan van der Bend on 24/01/2018.
 *
 * project: runeworld-game-server
 * package: runeworld.world.entity.animate.combat.strategy.impl.gandomeric
 */
public final class GanodermicConstants {

    public final static int GANDOMERIC_BEAST_MOB_ID = 2962;
    public final static double CONSTITUTION = 57000D;

    public final static int
            GANODERMIC_RUNT_1 = 2964,
            GANODERMIC_RUNT_2 = 2965;

    public static final String
            PRAYER_DRAINED_BY = "@red@The Ganodermic Beast drained your prayers by ",
            PRAYER_DISABLED = "@red@The Ganodermic beast has disabled your prayers!";

    final static int
            GANDOMERIC_BEAST_TRANSFORMED_MOB_ID = 2963,
            GANDOMERIC_BEAST_MOB_ID_RESIZED = 2966,
            GANDOMERIC_BEAST_TRANSFORMED_MOB_ID_RESIZED = 2967;

    final static Graphic
            MAGIC_DAMAGE_GRAPHIC = new Graphic(1194),
            MAGIC_DAMAGE_GRAPHIC_2 = new Graphic(2007),
            RANGED_DAMAGE_GRAPHIC = new Graphic(542);
    static final int RESPAWN_INTERVAL_DELAY = 6666;

    final static int
            DRUMMER_MOB_ID_1 = 2054,
            DRUMMER_MOB_ID_2 = 2055;
    final static int
            PRAYER_DRAIN_ODDS = 20,
            PRAYER_DAMAGE_BOUNDARY = 30,
            PRAYER_DRAIN_AMOUNT_BOUNDARY = 20;

    final static int ATTACK_DISTANCE = 15;

    final static List<NPCDrops.NpcDropItem>
            COMMON = new ArrayList<>(),
            MEDIUM = new ArrayList<>(),
            RARE = new ArrayList<>(),
            SUPER_RARE = new ArrayList<>();

    final static Animation
            MELEE_ATTACK_ANIMATION = new Animation(15466),
            DRAINED = new Animation(10841),
            CHARGE = new Animation(15470),
            FALL = new Animation(11051);

    static final Predicate<Player> NON_NULL = Objects::nonNull;

    final static String[]
            SHOUTS = {
                    "RAaaarggh!", "Die!", "Arghh...",
                    "I will devour all!", "Mortals...",
                    "Hehehe","I will destroy you","I pity thou all",
                    "Is that all?", "Weaklings..",
                    "I am a god!"},
            GLOBAL_COMBAT_MESSAGES = {
                    "@red@Ganodermic Beast yells \"Ahhh... new victims!\"",
                    "@red@Ganodermic Beast yells \"I have not come this far to be stopped!\"",
                    "@red@Ganodermic Beast yells \"The future I have planned will not be jeopardized!\"",
                    "@red@Ganodermic Beast yells \"Now, you will taste true power!\"",
                    "@red@Ganodermic Beast yells \"DIE DIE DIE NOW!\"" },
            LOCATIONS = {
                    "A Castle",
                    "Scorpia's Lair",
                    "Kbd's Lair",
                    "The Buried Dead",
                    "Ruined City",
                    "Forgotten Camp"
            },
            GLOBAL_SPAWN_MESSAGES = {
                    "@red@A demonic beast has spawned near the wilderness castle!",
                    "@red@A demonic beast has spawned near scorpia's lair!",
                    "@red@A demonic beast has spawned near the king black dragon's entrance!!",
                    "@red@A demonic beast has spawned near the buried dead!",
                    "@red@A demonic beast has spawned near a ruined city!",
                    "@red@A demonic beast has spawned near a forgotten camp!!",
            };

    final static Position[] SPAWN_POSITIONS = {
                    new Position(3030, 3631),
                    new Position(3156, 3937),
                    new Position(3099, 3839),
                    new Position(3172, 3665),
                    new Position(3160, 3743),
                    new Position(3244, 3791),
            };

    final static Position[][] DRUMMER_SPAWN_POSITIONS =
            {
                    {
                            new Position(3011, 3628),
                            new Position(3011, 3635),},
                    {
                            new Position(3162, 3959),
                            new Position(3155, 3959),},
                    {
                            new Position(3052, 3847),
                            new Position(3048, 3843),},
                    {
                            new Position(3181, 3672),
                            new Position(3151, 3670),},
                    {
                            new Position(3159, 3721),
                            new Position(3182, 3745),},
                    {
                            new Position(3258, 3777),
                            new Position(3224, 3778),},
            };
}
