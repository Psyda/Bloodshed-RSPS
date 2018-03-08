package com.ruseps.world.entity.impl.npc.bosses.zulrah;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.RegionInstance;
import com.ruseps.net.packet.PacketSender;
import com.ruseps.world.World;
import com.ruseps.world.content.dialogue.Dialogue;
import com.ruseps.world.content.dialogue.DialogueExpression;
import com.ruseps.world.content.dialogue.DialogueManager;
import com.ruseps.world.content.dialogue.DialogueType;
import com.ruseps.world.entity.impl.player.Player;

/**
 * Created by Stan van der Bend on 19/07/2017.
 *
 * https://www.rune-server.ee/members/icebrick/
 */
public class ZulrahInstance extends RegionInstance {

    public ZulrahInstance(Player p) {
        super(p, RegionInstanceType.ZULRAH);
    }

    public static void startInstance(final Player p, final boolean restart) {

        if(p.getRegionInstance() != null && !restart){
            DialogueManager.start(p, ALREADY_INSTANCED);
            return;
        }

        final int height = p.getIndex() * 4;

        ZulrahInstance instance = new ZulrahInstance(p);
        Zulrah zulrah = new Zulrah(height);

        zulrah.setSpawnedFor(p);

        p.setRegionInstance(instance);
        zulrah.setRegionInstance(instance);

        instance.add(zulrah);

        TaskManager.submit(initiateZulrahInstance(p, zulrah, height, restart));
   

    }

    private static Task initiateZulrahInstance(final Player player, final Zulrah zulrah, final int height, final boolean restart){

        if(restart)
            player.sendMessage("Zulrah will arise soon...");

        final int ticketDelay = restart ? 2 : 1;

        final Task task = new Task(ticketDelay, false) {
            int ticks = 0;
            @Override
            protected void execute() {
                if(!player.isRegistered() || !(player.getRegionInstance() instanceof ZulrahInstance)){
                    stop();
                    return;
                }
                if(!restart) {
                    if (ticks == 0) {
                        final PacketSender sender = player.getPacketSender();
                        sender.removeAllWindows();
                        sender.sendScreenFade("Welcome to Zulrah's shrine");
                    }

                    if (ticks == 7) {
                        player.moveTo(Zulrah.getStartLocation().copy().setZ(height));
                    }
                }
                if(ticks == 12){
                    World.register(zulrah);
                    zulrah.performAnimation(Zulrah.ZULRAH_RISE_ANIMATION);
                }
                if(ticks == 13){
                    zulrah.setState(ZulrahState.NORMAL);
                    zulrah.getCombatBuilder().setAttackTimer(0);
                    stop();
                }

                ticks++;
            }
        };
        return task.bind(zulrah);
    }

    private final static Dialogue ALREADY_INSTANCED = new Dialogue() {
        @Override public DialogueType type() {return DialogueType.NPC_STATEMENT;}
        @Override public int npcId() {return 13301;}
        @Override public DialogueExpression animation() {return DialogueExpression.CONFUSED;}
        @Override public String[] dialogue() {return new String[] {"It seems that a zulrah instance for you is already created.", "If you think this is wrong then please re-log."};}
    };
}
