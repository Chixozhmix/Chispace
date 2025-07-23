package net.chixozhmix.space.entity.client;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class FollowPlayerGoal extends Goal {
    private final Mob mob;
    private Player targetPlayer;
    private final double speedModifier;
    private final float minDistance;
    private final float maxDistance;

    public FollowPlayerGoal(Mob mob, double speed, float minDist, float maxDist) {
        this.mob = mob;
        this.speedModifier = speed;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public boolean canUse() {
        this.targetPlayer = mob.level().getNearestPlayer(mob, maxDistance);
        return targetPlayer != null;
    }

    @Override
    public void tick() {
        if(targetPlayer != null && mob.distanceToSqr(targetPlayer) > (minDistance * minDistance)) {
            mob.getNavigation().moveTo(targetPlayer, speedModifier);
        }
    }
}
