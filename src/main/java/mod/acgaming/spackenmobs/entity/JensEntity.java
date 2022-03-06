package mod.acgaming.spackenmobs.entity;

import javax.annotation.Nullable;

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

import mod.acgaming.spackenmobs.entity.ai.goal.JensDanceGoal;
import mod.acgaming.spackenmobs.entity.ai.goal.JensEatDroppedFishGoal;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;

public class JensEntity extends AnimalEntity implements IRideable, IEquipable
{
    private static final DataParameter<Boolean> DIGESTING = EntityDataManager.defineId(JensEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DIGEST_TIME = EntityDataManager.defineId(JensEntity.class, DataSerializers.INT);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, SpackenmobsRegistry.RAM.get());
    private static final Ingredient BREEDING_ITEMS = Ingredient.of(SpackenmobsRegistry.RAM.get());
    private static final Ingredient FISH_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    private final BoostHelper steering = new BoostHelper(this.entityData, DIGEST_TIME, DIGESTING);
    public boolean digesting;
    public int digestTime;

    public JensEntity(EntityType<? extends JensEntity> p_i50250_1_, World p_i50250_2_)
    {
        super(p_i50250_1_, p_i50250_2_);
    }

    public boolean isSaddleable()
    {
        return this.isAlive() && !this.isBaby();
    }

    public void equipSaddle(@Nullable SoundCategory p_230266_1_)
    {
        this.steering.setSaddle(true);
        if (p_230266_1_ != null)
        {
            this.level.playSound(null, this, SoundEvents.PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled()
    {
        return true;
    }

    public void aiStep()
    {
        super.aiStep();

        if (this.digesting && this.digestTime > 0)
        {
            this.digestTime--;
            this.entityData.set(DIGEST_TIME, this.digestTime);
        }

        if (this.digesting && this.digestTime <= 0)
        {
            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
            this.playSound(SpackenmobsRegistry.ENTITY_JENS_POOP.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(SpackenmobsRegistry.SURSTROEMMING.get());
            this.removeAllEffects();

            this.digesting = false;
            this.entityData.set(DIGESTING, false);

            this.digestTime = 0;
            this.entityData.set(DIGEST_TIME, 0);
        }
    }

    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Digesting", this.digesting);
        compound.putInt("DigestTime", this.digestTime);
    }

    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.digesting = compound.getBoolean("Digesting");
        this.digestTime = compound.getInt("DigestTime");
    }

    public boolean isFood(ItemStack stack)
    {
        return BREEDING_ITEMS.test(stack);
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        boolean breeding_item = this.isFood(player.getItemInHand(hand));
        boolean fish_item = this.isFishItem(player.getItemInHand(hand));

        if (!breeding_item && !this.isVehicle() && !player.isSecondaryUseActive())
        {
            if (!this.level.isClientSide)
            {
                player.startRiding(this);
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else
        {
            ActionResultType actionresulttype = super.mobInteract(player, hand);
            if (fish_item && !this.isBaby() && !this.digesting && !actionresulttype.consumesAction())
            {
                ItemStack itemstack = player.getItemInHand(hand);
                itemstack.interactLivingEntity(player, this, hand);
                digestFish();
                return ActionResultType.PASS;
            }
            else
            {
                return actionresulttype;
            }
        }
    }

    public void digestFish()
    {
        this.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
        this.playSound(SpackenmobsRegistry.ENTITY_JENS_EAT.get(), 1.0F, 1.0F);

        this.digesting = true;
        this.entityData.set(DIGESTING, true);

        this.digestTime = (ConfigurationHandler.GENERAL.jens_digest_time.get() * 20);
        this.entityData.set(DIGEST_TIME, this.digestTime);

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }

        this.addEffect(new EffectInstance(Effects.CONFUSION, ConfigurationHandler.GENERAL.jens_digest_time.get() * 20, 1));
    }

    public boolean boost()
    {
        return this.steering.boost(this.getRandom());
    }

    public void travelWithInput(Vector3d travelVec)
    {
        super.travel(travelVec);
    }

    public float getSteeringSpeed()
    {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.5F;
    }

    public JensEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {
        return SpackenmobsRegistry.JENS.get().create(p_241840_1_);
    }

    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(DIGESTING, false);
        this.entityData.define(DIGEST_TIME, 0);
    }

    public void onSyncedDataUpdated(DataParameter<?> key)
    {
        super.onSyncedDataUpdated(key);
    }

    public boolean isFishItem(ItemStack stack)
    {
        return FISH_ITEMS.test(stack);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new JensDanceGoal(this));
        this.goalSelector.addGoal(2, new JensEatDroppedFishGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(SpackenmobsRegistry.RAM_ON_A_STICK.get()), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_JENS_AMBIENT.get();
    }

    public boolean canBeControlledByRider()
    {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity))
        {
            return false;
        }
        else
        {
            PlayerEntity playerentity = (PlayerEntity) entity;
            return playerentity.getMainHandItem().getItem() == SpackenmobsRegistry.RAM_ON_A_STICK.get() || playerentity.getOffhandItem().getItem() == SpackenmobsRegistry.RAM_ON_A_STICK.get();
        }
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset()
    {
        return new Vector3d(0.0D, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }

    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public Vector3d getDismountLocationForPassenger(LivingEntity livingEntity)
    {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y)
        {
            return super.getDismountLocationForPassenger(livingEntity);
        }
        else
        {
            int[][] aint = TransportationHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (Pose pose : livingEntity.getDismountPoses())
            {
                AxisAlignedBB axisalignedbb = livingEntity.getLocalBoundsForPose(pose);

                for (int[] aint1 : aint)
                {
                    blockpos$mutable.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level.getBlockFloorHeight(blockpos$mutable);
                    if (TransportationHelper.isBlockFloorValid(d0))
                    {
                        Vector3d vector3d = Vector3d.upFromBottomCenterOf(blockpos$mutable, d0);
                        if (TransportationHelper.canDismountTo(this.level, livingEntity, axisalignedbb.move(vector3d)))
                        {
                            livingEntity.setPose(pose);
                            return vector3d;
                        }
                    }
                }
            }
            return super.getDismountLocationForPassenger(livingEntity);
        }
    }

    protected void dropEquipment()
    {
        super.dropEquipment();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_JENS_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_JENS_DEATH.get();
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }

    public void travel(Vector3d travelVector)
    {
        this.travel(this, this.steering, travelVector);
    }
}