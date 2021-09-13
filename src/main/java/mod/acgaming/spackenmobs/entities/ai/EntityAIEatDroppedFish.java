package mod.acgaming.spackenmobs.entities.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.entities.EntityJens;

public class EntityAIEatDroppedFish extends EntityAIBase
{
    private final EntityJens jens;
    private final World world;
    double searchDistance = 10.0D;

    public EntityAIEatDroppedFish(EntityJens jens)
    {
        this.jens = jens;
        this.world = jens.world;
    }

    public EntityItem getNearbyFood()
    {
        List<EntityItem> items = getItems();
        for (EntityItem item : items)
        {
            return item;
        }
        return null;
    }

    @Override
    public boolean shouldExecute()
    {
        EntityItem getNearbyFood = getNearbyFood();
        if (getNearbyFood != null && !this.jens.isChild() && !this.jens.digesting
            && this.jens.isFishItem(getNearbyFood.getItem()))
        {
            execute(this.jens, getNearbyFood);
        }
        return false;
    }

    public boolean execute(EntityJens jens, EntityItem item)
    {
        if (jens.getNavigator().tryMoveToXYZ(item.posX, item.posY, item.posZ, 1.25D))
        {
            if (jens.getDistance(item) < 1.0F)
            {
                eatItem(item);
                jens.digestFish();
            }
        }
        return true;
    }

    public void eatItem(EntityItem item)
    {
        ItemStack stack = item.getItem();
        stack.setCount(stack.getCount() - 1);
        if (stack.getCount() == 0)
        {
            item.setDead();
        }
    }

    List<EntityItem> getItems()
    {
        return this.world.getEntitiesWithinAABB(EntityItem.class,
            new AxisAlignedBB(this.jens.posX - this.searchDistance, this.jens.posY - this.searchDistance,
                this.jens.posZ - this.searchDistance, this.jens.posX + this.searchDistance,
                this.jens.posY + this.searchDistance, this.jens.posZ + this.searchDistance));
    }
}