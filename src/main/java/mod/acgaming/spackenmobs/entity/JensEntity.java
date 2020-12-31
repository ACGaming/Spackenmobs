package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.entity.ai.goal.JensDanceGoal;
import mod.acgaming.spackenmobs.entity.ai.goal.JensEatDroppedFishGoal;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class JensEntity extends AnimalEntity implements IRideable, IEquipable
{
	private static final DataParameter<Boolean> DIGESTING = EntityDataManager.createKey(JensEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> DIGEST_TIME = EntityDataManager.createKey(JensEntity.class, DataSerializers.VARINT);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.COD, Items.SALMON, Items.TROPICAL_FISH, SpackenmobsRegistry.RAM.get());
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(SpackenmobsRegistry.RAM.get());
	private static final Ingredient FISH_ITEMS = Ingredient.fromItems(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
	private final BoostHelper field_234214_bx_ = new BoostHelper(this.dataManager, DIGEST_TIME, DIGESTING);
	public boolean digesting;
	public int digestTime;

	public JensEntity(EntityType<? extends JensEntity> p_i50250_1_, World p_i50250_2_)
	{
		super(p_i50250_1_, p_i50250_2_);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new JensDanceGoal(this));
		this.goalSelector.addGoal(2, new JensEatDroppedFishGoal(this));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.fromItems(SpackenmobsRegistry.RAM_ON_A_STICK.get()), false));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();
		if (!(entity instanceof PlayerEntity))
		{
			return false;
		} else
		{
			PlayerEntity playerentity = (PlayerEntity) entity;
			return playerentity.getHeldItemMainhand().getItem() == SpackenmobsRegistry.RAM_ON_A_STICK.get() || playerentity.getHeldItemOffhand().getItem() == SpackenmobsRegistry.RAM_ON_A_STICK.get();
		}
	}

	public void notifyDataManagerChange(DataParameter<?> key)
	{
		super.notifyDataManagerChange(key);
	}

	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(DIGESTING, false);
		this.dataManager.register(DIGEST_TIME, 0);
	}

	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putBoolean("Digesting", this.digesting);
		compound.putInt("DigestTime", this.digestTime);
	}

	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.digesting = compound.getBoolean("Digesting");
		this.digestTime = compound.getInt("DigestTime");
	}

	protected SoundEvent getAmbientSound()
	{
		return SpackenmobsRegistry.ENTITY_JENS_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SpackenmobsRegistry.ENTITY_JENS_HURT.get();
	}

	protected SoundEvent getDeathSound()
	{
		return SpackenmobsRegistry.ENTITY_JENS_DEATH.get();
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	protected float getSoundVolume()
	{
		return 0.6F;
	}

	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand)
	{
		boolean breeding_item = this.isBreedingItem(player.getHeldItem(hand));
		boolean fish_item = this.isFishItem(player.getHeldItem(hand));

		if (!breeding_item && !this.isBeingRidden() && !player.isSecondaryUseActive())
		{
			if (!this.world.isRemote)
			{
				player.startRiding(this);
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else
		{
			ActionResultType actionresulttype = super.func_230254_b_(player, hand);
			if (fish_item && !this.isChild() && !this.digesting && !actionresulttype.isSuccessOrConsume())
			{
				ItemStack itemstack = player.getHeldItem(hand);
				itemstack.interactWithEntity(player, this, hand);
				digestFish();
				return ActionResultType.PASS;
			} else
			{
				return actionresulttype;
			}
		}
	}

	public boolean func_230264_L__()
	{
		return this.isAlive() && !this.isChild();
	}

	protected void dropInventory()
	{
		super.dropInventory();
	}

	public void func_230266_a_(@Nullable SoundCategory p_230266_1_)
	{
		this.field_234214_bx_.setSaddledFromBoolean(true);
		if (p_230266_1_ != null)
		{
			this.world.playMovingSound(null, this, SoundEvents.ENTITY_PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
		}
	}

	@Override
	public boolean isHorseSaddled()
	{
		return true;
	}

	public Vector3d func_230268_c_(LivingEntity livingEntity)
	{
		Direction direction = this.getAdjustedHorizontalFacing();
		if (direction.getAxis() == Direction.Axis.Y)
		{
			return super.func_230268_c_(livingEntity);
		} else
		{
			int[][] aint = TransportationHelper.func_234632_a_(direction);
			BlockPos blockpos = this.getPosition();
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			for (Pose pose : livingEntity.getAvailablePoses())
			{
				AxisAlignedBB axisalignedbb = livingEntity.getPoseAABB(pose);

				for (int[] aint1 : aint)
				{
					blockpos$mutable.setPos(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
					double d0 = this.world.func_242403_h(blockpos$mutable);
					if (TransportationHelper.func_234630_a_(d0))
					{
						Vector3d vector3d = Vector3d.copyCenteredWithVerticalOffset(blockpos$mutable, d0);
						if (TransportationHelper.func_234631_a_(this.world, livingEntity, axisalignedbb.offset(vector3d)))
						{
							livingEntity.setPose(pose);
							return vector3d;
						}
					}
				}
			}
			return super.func_230268_c_(livingEntity);
		}
	}

	public void livingTick()
	{
		super.livingTick();

		if (this.digesting && this.digestTime > 0)
		{
			this.digestTime--;
			this.dataManager.set(DIGEST_TIME, this.digestTime);
		}

		if (this.digesting && this.digestTime <= 0)
		{
			for (int i = 0; i < 7; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
			}
			this.playSound(SpackenmobsRegistry.ENTITY_JENS_POOP.get(), 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.entityDropItem(SpackenmobsRegistry.SURSTROEMMING.get());
			this.clearActivePotions();

			this.digesting = false;
			this.dataManager.set(DIGESTING, false);

			this.digestTime = 0;
			this.dataManager.set(DIGEST_TIME, 0);
		}
	}

	public void digestFish()
	{
		this.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 1.0F);
		this.playSound(SpackenmobsRegistry.ENTITY_JENS_EAT.get(), 1.0F, 1.0F);

		this.digesting = true;
		this.dataManager.set(DIGESTING, true);

		this.digestTime = (ConfigurationHandler.GENERAL.jens_digest_time.get() * 20);
		this.dataManager.set(DIGEST_TIME, this.digestTime);

		for (int i = 0; i < 7; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.world.addParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
		}

		this.addPotionEffect(new EffectInstance(Effects.NAUSEA, ConfigurationHandler.GENERAL.jens_digest_time.get() * 20, 1));
	}

	public void travel(Vector3d travelVector)
	{
		this.ride(this, this.field_234214_bx_, travelVector);
	}

	public float getMountedSpeed()
	{
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.5F;
	}

	public void travelTowards(Vector3d travelVec)
	{
		super.travel(travelVec);
	}

	public boolean boost()
	{
		return this.field_234214_bx_.boost(this.getRNG());
	}

	public JensEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
	{
		return SpackenmobsRegistry.JENS.get().create(p_241840_1_);
	}

	public boolean isBreedingItem(ItemStack stack)
	{
		return BREEDING_ITEMS.test(stack);
	}

	public boolean isFishItem(ItemStack stack)
	{
		return FISH_ITEMS.test(stack);
	}

	@OnlyIn(Dist.CLIENT)
	public Vector3d func_241205_ce_()
	{
		return new Vector3d(0.0D, 0.6F * this.getEyeHeight(), this.getWidth() * 0.4F);
	}
}