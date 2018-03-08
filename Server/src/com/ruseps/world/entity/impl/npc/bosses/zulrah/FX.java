package com.ruseps.world.entity.impl.npc.bosses.zulrah;

import com.ruseps.engine.task.Task;
import com.ruseps.engine.task.TaskManager;
import com.ruseps.model.Flag;
import com.ruseps.model.Graphic;
import com.ruseps.model.GraphicHeight;
import com.ruseps.model.Position;
import com.ruseps.model.Projectile;
import com.ruseps.util.Misc;
import com.ruseps.world.World;
import com.ruseps.world.entity.Entity;
import com.ruseps.world.entity.impl.npc.NPC;
import com.ruseps.world.entity.impl.player.Player;

public class FX {
	   
	private static final Graphic MAGIC_HIT_GRAPHIC = new Graphic(677, GraphicHeight.LOW);

	public static Projectile createSnakelingProjectile(NPC zul, NPC lockon){
	    return new Projectile(zul, lockon, 551, 100,3 ,56 , 0, 45);
	}
    public static Projectile createToxicProjectile(NPC zul, Entity lockon){
        lockon.setPosition(lockon.getPosition().add(-1, -1));
        return new Projectile(676)
                .setPositions(zul.getPosition(),lockon.getPosition())
                .setDelay(50)
                .setCoveredTilesBySource(3)
                .setHeights(56, 0)
                .setDuration(40);
    }
	public static void spawnSnakelings(NPC dummy, Player player) {
        final int randomX = 2265 + Misc.random(7);
        final int randomY = 3069;

        final NPC snakeling = new NPC(2045, new Position(randomX, randomY, player.getPosition().getZ()));

        World.register(snakeling);

        player.getRegionInstance().getNpcsList().add(snakeling);

        dummy.setChargingAttack(true);

        dummy.setEntityInteraction(snakeling);
        dummy.setPositionToFace(snakeling.getPosition());

        snakeling.setTransformationId(1);
        snakeling.getUpdateFlag().flag(Flag.TRANSFORM);

        createSnakelingProjectile(dummy, snakeling).sendProjectile();

        final int delay = snakeling.getPosition().getDistance(dummy.getPosition())/2;

        TaskManager.submit(new Task(1, false) {
            int ticks;
            @Override
            protected void execute() {

                if(ticks == delay)
                    snakeling.performGraphic(MAGIC_HIT_GRAPHIC);

                if(ticks == delay+1){
                    snakeling.setTransformationId(2045);
                    snakeling.getUpdateFlag().flag(Flag.TRANSFORM);

                    snakeling.performAnimation(Zulrah.SNAKELING_SPAWN_ANIMATION);

                    snakeling.getCombatBuilder().attack(player);

                    dummy.setChargingAttack(false);
                    stop();
               }
               ticks++;
            }
        });
    
    }
}
