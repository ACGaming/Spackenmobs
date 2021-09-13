package mod.acgaming.spackenmobs.entity.ai.goal;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.entity.JensEntity;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;

public class JensEatDroppedFishGoal extends Goal
{
    private final JensEntity jens;
    private final World world;
    private final int searchDistance = ConfigurationHandler.GENERAL.jens_search_distance.get();

    public JensEatDroppedFishGoal(JensEntity jens)
    {
        this.jens = jens;
        this.world = jens.world;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    public ItemEntity getNearbyFood()
    {
        List<ItemEntity> items = getItems();
        for (ItemEntity item : items)
        {
            return item;
        }
        return null;
    }

    public boolean shouldExecute()
    {
        ItemEntity getNearbyFood = getNearbyFood();
        if (getNearbyFood != null && !this.jens.isChild() && !this.jens.digesting
            && this.jens.isFishItem(getNearbyFood.getItem()))
        {
            execute(this.jens, getNearbyFood);
        }
        return false;
    }

    public boolean execute(JensEntity jens, ItemEntity item)
    {
        if (jens.getNavigator().tryMoveToXYZ(item.getPosX(), item.getPosY(), item.getPosZ(), 1.25D))
        {
            if (jens.getDistance(item) < 1.5F)
            {
                eatItem(item);
                jens.digestFish();
            }
        }
        return true;
    }

    public void eatItem(ItemEntity item)
    {
        ItemStack stack = item.getItem();
        stack.setCount(stack.getCount() - 1);
        if (stack.getCount() == 0)
        {
            item.remove();
        }
    }

    List<ItemEntity> getItems()
    {
        return this.world.getEntitiesWithinAABB(ItemEntity.class,
            new AxisAlignedBB(this.jens.getPosX() - this.searchDistance, this.jens.getPosY() - this.searchDistance,
                this.jens.getPosZ() - this.searchDistance, this.jens.getPosX() + this.searchDistance,
                this.jens.getPosY() + this.searchDistance, this.jens.getPosZ() + this.searchDistance));
    }
}