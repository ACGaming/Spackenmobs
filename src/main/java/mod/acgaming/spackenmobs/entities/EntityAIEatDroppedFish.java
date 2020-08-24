package mod.acgaming.spackenmobs.entities;

import java.util.List;
import java.util.Random;

import mod.acgaming.spackenmobs.misc.ModConfigs;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIEatDroppedFish extends EntityAIBase
{
	private EntityJens jens;
	private Random rand = new Random();
	private World world = null;
	double searchDistance = ModConfigs.Jens_search_distance;

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
			EntityItem stack = item;
			if (items != null)
			{
				return stack;
			}
		}
		return null;
	}

	List<EntityItem> getItems()
	{
		return this.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.jens.posX - this.searchDistance, this.jens.posY - this.searchDistance, this.jens.posZ - this.searchDistance,
				this.jens.posX + this.searchDistance, this.jens.posY + this.searchDistance, this.jens.posZ + this.searchDistance));
	}

	@Override
	public boolean shouldExecute()
	{
		EntityItem nearbyFood = getNearbyFood();
		if (nearbyFood != null && !this.jens.isChild() && this.jens.digesting == false && this.jens.isFishItem(nearbyFood.getItem()))
		{
			execute(this.jens, nearbyFood);
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
}