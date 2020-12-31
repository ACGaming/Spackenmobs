package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FriedrichLiechtensteinEntity extends AnimalEntity
{
	public FriedrichLiechtensteinEntity(EntityType<? extends FriedrichLiechtensteinEntity> type, World worldIn)
	{
		super(type, worldIn);
		setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()));
		setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2F);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.SUGAR), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	protected SoundEvent getAmbientSound()
	{
		return SpackenmobsRegistry.ENTITY_FRIEDRICH_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SpackenmobsRegistry.ENTITY_FRIEDRICH_HURT.get();
	}

	protected SoundEvent getDeathSound()
	{
		return SpackenmobsRegistry.ENTITY_FRIEDRICH_DEATH.get();
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		if (itemstack.getItem() == Items.GLASS_BOTTLE && !this.isChild())
		{
			player.playSound(SpackenmobsRegistry.ENTITY_FRIEDRICH_DEATH.get(), 1.0F, 1.0F);
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			for (int i = 0; i < 7; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
			}
			itemstack.shrink(1);
			if (itemstack.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get())))
			{
				player.dropItem(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()), false);
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else if (itemstack.getItem() == Items.PAPER && !this.isChild())
		{
			player.playSound(SpackenmobsRegistry.ENTITY_FRIEDRICH_AMBIENT.get(), 1.0F, 1.0F);
			for (int i = 0; i < 7; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
			}
			itemstack.shrink(1);
			if (itemstack.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get())))
			{
				player.dropItem(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()), false);
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else
		{
			return super.func_230254_b_(player, hand);
		}
	}

	public FriedrichLiechtensteinEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
	{
		return null;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return this.isChild() ? sizeIn.height * 0.95F : 1.3F;
	}
}