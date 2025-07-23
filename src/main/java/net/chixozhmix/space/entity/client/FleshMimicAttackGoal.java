package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class FleshMimicAttackGoal extends MeleeAttackGoal {
    private static FleshMimicEntity fleshMimic;

    public FleshMimicAttackGoal(FleshMimicEntity mob) {
        super(mob, 1.0D, true);
        this.fleshMimic = mob;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double attackRange = this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + enemy.getBbWidth();

        if (distToEnemySqr <= attackRange && this.getTicksUntilNextAttack() <= 0) {
            this.fleshMimic.doHurtTarget(enemy);
            this.resetAttackCooldown();
        }
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }
    }
}
