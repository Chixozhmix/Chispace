package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.entity.custom.MordanServantEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ServantAttackGoal extends MeleeAttackGoal {
    private final MordanServantEntity servant;

    public ServantAttackGoal(MordanServantEntity mob) {
        super(mob, 1.0D, true);
        this.servant = mob;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double attackRange = this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + enemy.getBbWidth();

        if (distToEnemySqr <= attackRange && this.getTicksUntilNextAttack() <= 0) {
            this.servant.doHurtTarget(enemy);
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
