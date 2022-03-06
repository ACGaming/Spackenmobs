package mod.acgaming.spackenmobs.entity;

import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.ai.goal.MZTEWolfBegGoal;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class MZTEWolfEntity extends TameableEntity implements IAngerable
{
    public static final Predicate<LivingEntity> TARGET_ENTITIES = (p_213440_0_) ->
    {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
    };
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.defineId(MZTEWolfEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.defineId(MZTEWolfEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(MZTEWolfEntity.class, DataSerializers.INT);
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isWet;
    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    private UUID persistentAngerTarget;

    public MZTEWolfEntity(EntityType<? extends MZTEWolfEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setTame(false);
    }

    public void aiStep()
    {
        super.aiStep();
        if (!this.level.isClientSide && this.isWet && !this.isShaking && !this.isPathFinding() && this.onGround)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.level.broadcastEntityEvent(this, (byte) 8);
        }

        if (!this.level.isClientSide)
        {
            this.updatePersistentAnger((ServerWorld) this.level, true);
        }

    }

    public boolean hurt(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    public boolean isFood(ItemStack stack)
    {
        Item item = stack.getItem();
        return item.isEdible() && item.getFoodProperties().isMeat();
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_)
    {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.level.isClientSide)
        {
            boolean flag = this.isOwnedBy(p_230254_1_) || this.isTame() || item == Items.BONE && !this.isTame() && !this.isAngry();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        }
        else
        {
            if (this.isTame())
            {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth())
                {
                    if (!p_230254_1_.abilities.instabuild)
                    {
                        itemstack.shrink(1);
                    }

                    this.heal((float) item.getFoodProperties().getNutrition());
                    return ActionResultType.SUCCESS;
                }

                if (!(item instanceof DyeItem))
                {
                    ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
                    if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(p_230254_1_))
                    {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return ActionResultType.SUCCESS;
                    }

                    return actionresulttype;
                }

                DyeColor dyecolor = ((DyeItem) item).getDyeColor();
                if (dyecolor != this.getCollarColor())
                {
                    this.setCollarColor(dyecolor);
                    if (!p_230254_1_.abilities.instabuild)
                    {
                        itemstack.shrink(1);
                    }

                    return ActionResultType.SUCCESS;
                }
            }
            else if (item == Items.BONE && !this.isAngry())
            {
                if (!p_230254_1_.abilities.instabuild)
                {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_230254_1_))
                {
                    this.tame(p_230254_1_);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                }
                else
                {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }

                return ActionResultType.SUCCESS;
            }

            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public boolean canMate(AnimalEntity otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!this.isTame())
        {
            return false;
        }
        else if (!(otherAnimal instanceof MZTEWolfEntity))
        {
            return false;
        }
        else
        {
            MZTEWolfEntity wolfentity = (MZTEWolfEntity) otherAnimal;
            if (!wolfentity.isTame())
            {
                return false;
            }
            else if (wolfentity.isInSittingPose())
            {
                return false;
            }
            else
            {
                return this.isInLove() && wolfentity.isInLove();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isWolfWet()
    {
        return this.isWet;
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadingWhileWet(float partialTicks)
    {
        return Math.min(0.5F + MathHelper.lerp(partialTicks, this.prevTimeWolfIsShaking, this.timeWolfIsShaking) / 2.0F * 0.5F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public float getShakeAngle(float partialTicks, float offset)
    {
        float f = (MathHelper.lerp(partialTicks, this.prevTimeWolfIsShaking, this.timeWolfIsShaking) + offset) / 1.8F;
        if (f < 0.0F)
        {
            f = 0.0F;
        }
        else if (f > 1.0F)
        {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

    @OnlyIn(Dist.CLIENT)
    public float getInterestedAngle(float partialTicks)
    {
        return MathHelper.lerp(partialTicks, this.headRotationCourseOld, this.headRotationCourse) * 0.15F * (float) Math.PI;
    }

    @OnlyIn(Dist.CLIENT)
    public float getTailRotation()
    {
        if (this.isAngry())
        {
            return 1.5393804F;
        }
        else
        {
            return this.isTame() ? (0.55F - (this.getMaxHealth() - this.getHealth()) * 0.02F) * (float) Math.PI : ((float) Math.PI / 5F);
        }
    }

    public int getRemainingPersistentAngerTime()
    {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int time)
    {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, time);
    }

    @Nullable
    public UUID getPersistentAngerTarget()
    {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID target)
    {
        this.persistentAngerTarget = target;
    }

    public void startPersistentAngerTimer()
    {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
    }

    public DyeColor getCollarColor()
    {
        return DyeColor.byId(this.entityData.get(COLLAR_COLOR));
    }

    public void setCollarColor(DyeColor collarcolor)
    {
        this.entityData.set(COLLAR_COLOR, collarcolor.getId());
    }

    public MZTEWolfEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {
        return SpackenmobsRegistry.MZTEWOLF.get().create(p_241840_1_);
    }

    public boolean isBegging()
    {
        return this.entityData.get(BEGGING);
    }

    public void setBegging(boolean beg)
    {
        this.entityData.set(BEGGING, beg);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, LlamaEntity.class, 24.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new MZTEWolfBegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, TARGET_ENTITIES));
        this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
    }

    public void tick()
    {
        super.tick();
        if (this.isAlive())
        {
            this.headRotationCourseOld = this.headRotationCourse;
            if (this.isBegging())
            {
                this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
            }
            else
            {
                this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
            }

            if (this.isInWaterRainOrBubble())
            {
                this.isWet = true;
                if (this.isShaking && !this.level.isClientSide)
                {
                    this.level.broadcastEntityEvent(this, (byte) 56);
                    this.cancelShake();
                }
            }
            else if ((this.isWet || this.isShaking) && this.isShaking)
            {
                if (this.timeWolfIsShaking == 0.0F)
                {
                    this.playSound(SoundEvents.WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                }

                this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
                this.timeWolfIsShaking += 0.05F;
                if (this.prevTimeWolfIsShaking >= 2.0F)
                {
                    this.isWet = false;
                    this.isShaking = false;
                    this.prevTimeWolfIsShaking = 0.0F;
                    this.timeWolfIsShaking = 0.0F;
                }

                if (this.timeWolfIsShaking > 0.4F)
                {
                    float f = (float) this.getY();
                    int i = (int) (MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);
                    Vector3d vector3d = this.getDeltaMovement();

                    for (int j = 0; j < i; ++j)
                    {
                        float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                        float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                        this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double) f1, f + 0.8F, this.getZ() + (double) f2, vector3d.x, vector3d.y, vector3d.z);
                    }
                }
            }

        }
    }

    protected SoundEvent getAmbientSound()
    {
        if (this.isAngry())
        {
            return SoundEvents.WOLF_GROWL;
        }
        else if (this.random.nextInt(3) == 0)
        {
            return this.isTame() && this.getHealth() < 10.0F ? SoundEvents.WOLF_WHINE : SoundEvents.WOLF_PANT;
        }
        else
        {
            return SpackenmobsRegistry.ENTITY_MZTEWOLF_AMBIENT.get();
        }
    }

    public int getMaxHeadXRot()
    {
        return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
    }

    public int getMaxSpawnClusterSize()
    {
        return 8;
    }

    public boolean doHurtTarget(Entity entityIn)
    {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag)
        {
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }

    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(BEGGING, false);
        this.entityData.define(COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putByte("CollarColor", (byte) this.getCollarColor().getId());
        this.addPersistentAngerSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        if (compound.contains("CollarColor", 99))
        {
            this.setCollarColor(DyeColor.byId(compound.getInt("CollarColor")));
        }

        this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
    }

    public boolean canBeLeashed(PlayerEntity player)
    {
        return !this.isAngry() && super.canBeLeashed(player);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id)
    {
        if (id == 8)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else if (id == 56)
        {
            this.cancelShake();
        }
        else
        {
            super.handleEntityEvent(id);
        }

    }

    public void setTame(boolean tamed)
    {
        super.setTame(tamed);
        if (tamed)
        {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        }
        else
        {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public boolean wantsToAttack(LivingEntity target, LivingEntity owner)
    {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity))
        {
            if (target instanceof MZTEWolfEntity)
            {
                MZTEWolfEntity wolfentity = (MZTEWolfEntity) target;
                return !wolfentity.isTame() || wolfentity.getOwner() != owner;
            }
            else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity) owner).canHarmPlayer((PlayerEntity) target))
            {
                return false;
            }
            else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity) target).isTamed())
            {
                return false;
            }
            else
            {
                return !(target instanceof TameableEntity) || !((TameableEntity) target).isTame();
            }
        }
        else
        {
            return false;
        }
    }

    public void die(DamageSource cause)
    {
        this.isWet = false;
        this.isShaking = false;
        this.prevTimeWolfIsShaking = 0.0F;
        this.timeWolfIsShaking = 0.0F;
        super.die(cause);
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset()
    {
        return new Vector3d(0.0D, 0.6F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.WOLF_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.WOLF_DEATH;
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
    {
        return sizeIn.height * 0.8F;
    }

    private void cancelShake()
    {
        this.isShaking = false;
        this.timeWolfIsShaking = 0.0F;
        this.prevTimeWolfIsShaking = 0.0F;
    }

    class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T>
    {
        private final MZTEWolfEntity wolf;

        public AvoidEntityGoal(MZTEWolfEntity wolfIn, Class<T> entityClassToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
        {
            super(wolfIn, entityClassToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
            this.wolf = wolfIn;
        }

        public boolean canUse()
        {
            if (super.canUse() && this.toAvoid instanceof LlamaEntity)
            {
                return !this.wolf.isTame() && this.avoidLlama((LlamaEntity) this.toAvoid);
            }
            else
            {
                return false;
            }
        }

        public void start()
        {
            MZTEWolfEntity.this.setTarget(null);
            super.start();
        }

        public void tick()
        {
            MZTEWolfEntity.this.setTarget(null);
            super.tick();
        }

        private boolean avoidLlama(LlamaEntity llamaIn)
        {
            return llamaIn.getStrength() >= MZTEWolfEntity.this.random.nextInt(5);
        }
    }
}