package mod.acgaming.spackenmobs.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.entity.MZTEWolfEntity;

public class MZTEWolfBegGoal extends Goal
{
    private final MZTEWolfEntity wolf;
    private final World world;
    private final float minPlayerDistance;
    private final EntityPredicate playerPredicate;
    private PlayerEntity player;
    private int timeoutCounter;

    public MZTEWolfBegGoal(MZTEWolfEntity wolf, float minDistance)
    {
        this.wolf = wolf;
        this.world = wolf.level;
        this.minPlayerDistance = minDistance;
        this.playerPredicate = (new EntityPredicate()).range(minDistance).allowInvulnerable().allowSameTeam().allowNonAttackable();
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean canUse()
    {
        this.player = this.world.getNearestPlayer(this.playerPredicate, this.wolf);
        return this.player != null && this.hasTemptationItemInHand(this.player);
    }

    public boolean canContinueToUse()
    {
        if (!this.player.isAlive())
        {
            return false;
        }
        else if (this.wolf.distanceToSqr(this.player) > (double) (this.minPlayerDistance * this.minPlayerDistance))
        {
            return false;
        }
        else
        {
            return this.timeoutCounter > 0 && this.hasTemptationItemInHand(this.player);
        }
    }

    public void start()
    {
        this.wolf.setBegging(true);
        this.timeoutCounter = 40 + this.wolf.getRandom().nextInt(40);
    }

    public void stop()
    {
        this.wolf.setBegging(false);
        this.player = null;
    }

    public void tick()
    {
        this.wolf.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float) this.wolf.getMaxHeadXRot());
        --this.timeoutCounter;
    }

    private boolean hasTemptationItemInHand(PlayerEntity player)
    {
        for (Hand hand : Hand.values())
        {
            ItemStack itemstack = player.getItemInHand(hand);
            if (this.wolf.isTame() && itemstack.getItem() == Items.BONE)
            {
                return true;
            }
            if (this.wolf.isFood(itemstack))
            {
                return true;
            }
        }
        return false;
    }
}