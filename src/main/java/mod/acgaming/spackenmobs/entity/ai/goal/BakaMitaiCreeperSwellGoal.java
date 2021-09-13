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
        this.setMutexFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean shouldExecute()
    {
        LivingEntity livingentity = this.swellingCreeper.getAttackTarget();
        return this.swellingCreeper.getCreeperState() > 0 || livingentity != null && this.swellingCreeper.getDistanceSq(livingentity) < 9.0D;
    }

    public void startExecuting()
    {
        this.swellingCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
    }

    public void resetTask()
    {
        this.creeperAttackTarget = null;
    }

    public void tick()
    {
        if (this.creeperAttackTarget == null)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (this.swellingCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0D)
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget))
        {
            this.swellingCreeper.setCreeperState(-1);
        }
        else
        {
            this.swellingCreeper.setCreeperState(1);
        }
    }
}