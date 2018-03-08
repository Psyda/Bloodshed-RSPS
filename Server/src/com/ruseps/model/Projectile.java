package com.ruseps.model;

import com.ruseps.net.packet.PacketBuilder;
import com.ruseps.net.packet.ValueType;
import com.ruseps.util.TimeUtil;
import com.ruseps.world.World;
import com.ruseps.world.entity.Entity;
import com.ruseps.world.entity.impl.Character;
import com.ruseps.world.entity.impl.player.Player;

import java.util.Objects;

/**
 * Represents a graphic propelled through the air as result of a spell, weapon, or other miscellaneous force.
 *
 * @author lare96
 * @author Stan van der Bend
 */
public final class Projectile {

    private Position
            startPosition,
            destination;

    private int
            coveredTilesBySource,
            startPositionOffset,
            initialHeight,
            finalHeight,
            duration,
            delay,
            initialSlope;

    private final int
            propelledGraphicID,
            entityLockID;

    public Projectile(
            Position startPosition, Position end,
            int entityLockID, int propelledGraphicID,
            int delay,
            int duration,
            int initialHeight, int endHeight,
            int initialSlope,
            int coveredTilesBySource, int startPositionOffset) {
        this.startPosition = startPosition;
        this.destination = end;
        this.coveredTilesBySource = coveredTilesBySource;
        this.startPositionOffset = startPositionOffset;
        this.entityLockID = entityLockID;
        this.propelledGraphicID = propelledGraphicID;
        this.duration = duration;
        this.delay = delay;
        this.initialHeight = initialHeight;
        this.finalHeight = endHeight;
        this.initialSlope = initialSlope;
    }

    public Projectile(Entity source, Character victim, int propelledGraphicID, int delay, int duration, int initialHeight, int endHeight, int initialSlope) {
        this(source.getPosition(), victim.getPosition(), victim.getLockIndex(), propelledGraphicID, delay, duration, initialHeight, endHeight, initialSlope, source.getSize(), 0);
    }

    public Projectile(Position startPosition, Position end, int propelledGraphicID, int delay, int duration, int initialHeight, int endHeight, int initialSlope) {
        this(startPosition, end, 0, propelledGraphicID, delay, duration, initialHeight, endHeight, initialSlope);
    }

    public Projectile(Position startPosition, Position end, int entityLockID, int propelledGraphicID, int delay, int duration, int initialHeight, int endHeight, int initialSlope) {
        this(startPosition, end, entityLockID, propelledGraphicID, delay, duration, initialHeight, endHeight, initialSlope, 1, 0);
    }

    public Projectile(int propelledGraphicID) {
        this(null, null, 0, propelledGraphicID, 3, 0, 0, 0, 16);
    }

    public Projectile send() {
        for (Player player : World.getPlayers()) {

            if (Objects.isNull(player))
                continue;

            if (startPosition.isViewableFrom(player.getPosition())) {
                //player.sendDebug("[Projectile::send] last = " + player.getLastKnownRegion().getDistance(destination));
                //player.sendDebug("[Projectile::send] entityLockID = " + entityLockID + ", gfx = " + propelledGraphicID + ", slope = " + initialSlope + ", speed = " + delay + ", delay = " + duration);
                player.getPacketSender().sendProjectile(this);
            }
        }
        return this;
    }

    public PacketBuilder construct() {
        final boolean isEntityLocked = entityLockID != 0;
        final PacketBuilder builder = new PacketBuilder(isEntityLocked ? 117 : 118);

        final Position offset = isEntityLocked
                ? destination.calculateRelativeOffset(startPosition)
                : new Position(destination.getX() - 8 * startPosition.getRegionX(), destination.getY() - 8 * startPosition.getRegionY());

        if (isEntityLocked) {
            builder.put(0);
            builder.put(offset.getX());
            builder.put(offset.getY());
            builder.putShort(entityLockID);
        } else {
            builder.put(offset.getX(), ValueType.C);
            builder.put(offset.getY(), ValueType.C);
        }
        builder.putShort(propelledGraphicID);
        builder.put(initialHeight);
        builder.put(finalHeight);
        builder.putShort(isEntityLocked ? duration : Math.toIntExact(delay));
        builder.putShort(isEntityLocked ? Math.toIntExact(delay) : duration);
        builder.put(initialSlope);
        builder.put((coveredTilesBySource * 64) + (startPositionOffset * 64));
        return builder;
    }

    public int calculateTimeTillArrival() {
        return calculateTimeTillArrival(calculateTravelDistance());
    }

    public int calculateTrajectoryDuration() {
        return calculateTrajectoryDuration(calculateTravelDistance());
    }

    /**
     * Calculates the distance between the {@link Projectile#startPosition} and {@link Projectile#destination}.
     *
     * @return distance measured in tiles.
     */
    private int calculateTravelDistance() {
        return startPosition.getDistance(destination);
    }

    /**
     * Calculates the duration of this {@link Projectile}'s trajectory.
     *
     * @param distanceToTravel the distance to travel.
     * @return a time duration in {@link TimeUtil#CLIENT_CYCLES};
     */
    private int calculateTrajectoryDuration(int distanceToTravel) {
        if (distanceToTravel > 0)
            return duration + delay + distanceToTravel * 5;
        return 0;
    }

    /**
     * Calculates the amount of time it will take for the projectile to arrive at its {@link Projectile#destination}.
     *
     * @param distanceToTravel the distance between the {@link Projectile#startPosition} and {@link Projectile#destination}.
     * @return a time delay in {@link TimeUtil#CLIENT_CYCLES};
     */
    private int calculateTimeTillArrival(int distanceToTravel) {
        return (int) Math.floor(calculateTrajectoryDuration(distanceToTravel));
    }

    public Projectile setPositions(Position startPosition, Position destination) {
        return setStartPosition(startPosition).setDestination(destination);
    }

    public Projectile setHeights(int initialHeight, int finalHeight) {
        return setInitialHeight(initialHeight).setFinalHeight(finalHeight);
    }

    private Projectile setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    private Projectile setDestination(Position destination) {
        this.destination = destination;
        return this;
    }

    private Projectile setInitialHeight(int initialHeight) {
        this.initialHeight = initialHeight;
        return this;
    }

    private Projectile setFinalHeight(int finalHeight) {
        this.finalHeight = finalHeight;
        return this;
    }

    public Projectile setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Projectile setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public Projectile setCoveredTilesBySource(int coveredTilesBySource) {
        this.coveredTilesBySource = coveredTilesBySource;
        return this;
    }

    public Projectile setStartPositionOffset(int startPositionOffset) {
        this.startPositionOffset = startPositionOffset;
        return this;
    }

    public Position getDestination() {
        return destination;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public int getStartPositionOffset() {
        return startPositionOffset;
    }

    public int getPropelledGraphicID() {
        return propelledGraphicID;
    }

    public int getInitialSlope() {
        return initialSlope;
    }

    public int getInitialHeight() {
        return initialHeight;
    }

    public int getEntityLockID() {
        return entityLockID;
    }

    public int getProjectileID() {
        return propelledGraphicID;
    }

    public int getDuration() {
        return duration;
    }

    public int getCoveredTilesBySource() {
        return coveredTilesBySource;
    }

    public int getFinalHeight() {
        return finalHeight;
    }

    public int getDelay() {
        return delay;
    }

    public void sendProjectile() {
        send();
    }

}

