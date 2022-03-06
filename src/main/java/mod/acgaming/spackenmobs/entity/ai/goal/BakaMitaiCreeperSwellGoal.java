package mod.acgaming.spackenmobs.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import mod.acgaming.spackenmobs.entity.BakaMitaiCreeperEntity;

public class BakaMitaiCreeperSwellGoal extends Goal
{
    private final BakaMitaiCreeperEntity swellingCreeper;
    private LivingEntity creeperAttackTarget;

    public BakaMitaiCreeperSwellGoal(BakaMitaiCreeperEntity entitybakamitaicreeperIn)
    {
        this.swellingCreeper = entitybakamitaicreeperIn;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse()
    {
        LivingEntity livingentity = this.swellingCreeper.getTarget();
        return this.swellingCreeper.getCreeperState() > 0 || livingentity != null && this.swellingCreeper.distanceToSqr(livingentity) < 9.0D;
    }

    public void start()
    {
        this.swellingCreeper.getNavigation().stop();
        this.creeperAttackTarget = this.swellingCreeper.getTarget();
    }

    public void stop()
    {
        this.creeperAttackTarget = null;
    }

    public void tick()
    {
        if (this.creeperAttackTarget == null)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (this.swellingCreeper.distanceToSqr(this.creeperAttackTarget) > 49.0D)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (!this.swellingCreeper.getSensing().canSee(this.creeperAttackTarget))
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else
        {
            this.swellingCreeper.setCreeperState(1);
        }
    }
}