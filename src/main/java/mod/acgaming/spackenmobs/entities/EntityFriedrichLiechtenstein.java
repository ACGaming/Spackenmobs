package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.misc.ModItems;
import mod.acgaming.spackenmobs.misc.ModLootTableList;
import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityFriedrichLiechtenstein extends EntityCreature implements IMerchant
{
	public EntityFriedrichLiechtenstein(World worldIn)
	{
		super(worldIn);
		setSize(0.6F, 1.8F);
		setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.AHOJ_BRAUSE));
		setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.AHOJ_BRAUSE_DRINK));
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == Items.GLASS_BOTTLE)
		{
			player.playSound(ModSoundEvents.ENTITY_FRIEDRICH_DEATH, 1.0F, 1.0F);
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			for (int i = 0; i < 7; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
			}
			itemstack.shrink(1);

			if (itemstack.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(ModItems.AHOJ_BRAUSE_DRINK));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.AHOJ_BRAUSE_DRINK)))
			{
				player.dropItem(new ItemStack(ModItems.AHOJ_BRAUSE_DRINK), false);
			}

			return true;
		} else if (itemstack.getItem() == Items.PAPER)
		{
			player.playSound(ModSoundEvents.ENTITY_FRIEDRICH_AMBIENT, 1.0F, 1.0F);
			for (int i = 0; i < 7; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
			}
			itemstack.shrink(1);

			if (itemstack.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(ModItems.AHOJ_BRAUSE));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.AHOJ_BRAUSE)))
			{
				player.dropItem(new ItemStack(ModItems.AHOJ_BRAUSE), false);
			}

			return true;
		} else
		{
			return super.processInteract(player, hand);
		}
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return ModLootTableList.ENTITIES_FRIEDRICH;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.ENTITY_FRIEDRICH_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.ENTITY_FRIEDRICH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ENTITY_FRIEDRICH_DEATH;
	}

	@Override
	public EntityPlayer getCustomer()
	{
		return null;
	}

	@Override
	public void setCustomer(EntityPlayer player)
	{

	}

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player)
	{
		return null;
	}

	@Override
	public void setRecipes(MerchantRecipeList recipeList)
	{

	}

	@Override
	public void useRecipe(MerchantRecipe recipe)
	{

	}

	@Override
	public void verifySellingItem(ItemStack stack)
	{

	}

	@Override
	public World getWorld()
	{
		return null;
	}

	@Override
	public BlockPos getPos()
	{
		return null;
	}
}