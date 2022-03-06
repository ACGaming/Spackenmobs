package mod.acgaming.spackenmobs.entity;

import java.util.*;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class SchalkerEntity extends GolemEntity implements IMob
{
    protected static final DataParameter<Direction> ATTACHED_FACE = EntityDataManager.defineId(SchalkerEntity.class, DataSerializers.DIRECTION);
    protected static final DataParameter<Optional<BlockPos>> ATTACHED_BLOCK_POS = EntityDataManager.defineId(SchalkerEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
    protected static final DataParameter<Byte> PEEK_TICK = EntityDataManager.defineId(SchalkerEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Byte> COLOR = EntityDataManager.defineId(SchalkerEntity.class, DataSerializers.BYTE);
    private static final UUID COVERED_ARMOR_BONUS_ID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
    private static final AttributeModifier COVERED_ARMOR_BONUS_MODIFIER = new AttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 20.0D, AttributeModifier.Operation.ADDITION);

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D);
    }

    private float prevPeekAmount;
    private float peekAmount;
    private BlockPos currentAttachmentPosition = null;
    private int clientSideTeleportInterpolation;

    public SchalkerEntity(EntityType<? extends SchalkerEntity> p_i50196_1_, World p_i50196_2_)
    {
        super(p_i50196_1_, p_i50196_2_);
        this.xpReward = 5;
    }

    public void setPos(double x, double y, double z)
    {
        super.setPos(x, y, z);
        if (this.entityData != null && this.tickCount != 0)
        {
            Optional<BlockPos> optional = this.entityData.get(ATTACHED_BLOCK_POS);
            if (this.isAddedToWorld() && this.level instanceof net.minecraft.world.server.ServerWorld)
                ((net.minecraft.world.server.ServerWorld) this.level).updateChunkPos(this); // Forge - Process chunk registration after moving.
            Optional<BlockPos> optional1 = Optional.of(new BlockPos(x, y, z));
            if (!optional1.equals(optional))
            {
                this.entityData.set(ATTACHED_BLOCK_POS, optional1);
                this.entityData.set(PEEK_TICK, (byte) 0);
                this.hasImpulse = true;
            }

        }
    }

    public void move(MoverType typeIn, Vector3d pos)
    {
        if (typeIn == MoverType.SHULKER_BOX)
        {
            this.tryTeleportToNewPosition();
        }
        else
        {
            super.move(typeIn, pos);
        }

    }

    protected boolean isMovementNoisy()
    {
        return false;
    }

    public float getPickRadius()
    {
        return 0.0F;
    }

    public SoundCategory getSoundSource()
    {
        return SoundCategory.HOSTILE;
    }

    public boolean hurt(DamageSource source, float amount)
    {
        if (this.isClosed())
        {
            Entity entity = source.getDirectEntity();
            if (entity instanceof AbstractArrowEntity)
            {
                return false;
            }
        }

        if (super.hurt(source, amount))
        {
            if ((double) this.getHealth() < (double) this.getMaxHealth() * 0.5D && this.random.nextInt(4) == 0)
            {
                this.tryTeleportToNewPosition();
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }

    public void push(Entity entityIn)
    {
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        this.lerpSteps = 0;
    }

    public boolean isPickable()
    {
        return this.isAlive();
    }

    public void onSyncedDataUpdated(DataParameter<?> key)
    {
        if (ATTACHED_BLOCK_POS.equals(key) && this.level.isClientSide && !this.isPassenger())
        {
            BlockPos blockpos = this.getAttachmentPos();
            if (blockpos != null)
            {
                if (this.currentAttachmentPosition == null)
                {
                    this.currentAttachmentPosition = blockpos;
                }
                else
                {
                    this.clientSideTeleportInterpolation = 6;
                }

                this.setPosAndOldPos((double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D);
            }
        }

        super.onSyncedDataUpdated(key);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
    {
        return 0.5F;
    }

    public Direction getAttachmentFacing()
    {
        return this.entityData.get(ATTACHED_FACE);
    }

    @Nullable
    public BlockPos getAttachmentPos()
    {
        return this.entityData.get(ATTACHED_BLOCK_POS).orElse(null);
    }

    public void setAttachmentPos(@Nullable BlockPos pos)
    {
        this.entityData.set(ATTACHED_BLOCK_POS, Optional.ofNullable(pos));
    }

    public int getPeekTick()
    {
        return this.entityData.get(PEEK_TICK);
    }

    public void updateArmorModifier(int p_184691_1_)
    {
        if (!this.level.isClientSide)
        {
            Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).removeModifier(COVERED_ARMOR_BONUS_MODIFIER);
            if (p_184691_1_ == 0)
            {
                Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).addPermanentModifier(COVERED_ARMOR_BONUS_MODIFIER);
                this.playSound(SoundEvents.SHULKER_CLOSE, 1.0F, 1.0F);
            }
            else
            {
                this.playSound(SpackenmobsRegistry.ENTITY_SCHALKER_OPEN.get(), 1.0F, 1.0F);
            }
        }

        this.entityData.set(PEEK_TICK, (byte) p_184691_1_);
    }

    @OnlyIn(Dist.CLIENT)
    public float getClientPeekAmount(float p_184688_1_)
    {
        return MathHelper.lerp(p_184688_1_, this.prevPeekAmount, this.peekAmount);
    }

    @OnlyIn(Dist.CLIENT)
    public int getClientTeleportInterp()
    {
        return this.clientSideTeleportInterpolation;
    }

    @OnlyIn(Dist.CLIENT)
    public BlockPos getOldAttachPos()
    {
        return this.currentAttachmentPosition;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isAttachedToBlock()
    {
        return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new SchalkerEntity.AttackGoal());
        this.goalSelector.addGoal(7, new SchalkerEntity.PeekGoal());
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new SchalkerEntity.AttackNearestGoal(this));
        this.targetSelector.addGoal(3, new SchalkerEntity.DefenseAttackGoal(this));
    }

    protected BodyController createBodyControl()
    {
        return new BodyHelperController(this);
    }

    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ATTACHED_FACE, Direction.DOWN);
        this.entityData.define(ATTACHED_BLOCK_POS, Optional.empty());
        this.entityData.define(PEEK_TICK, (byte) 0);
        this.entityData.define(COLOR, (byte) 16);
    }

    public void playAmbientSound()
    {
        if (!this.isClosed())
        {
            super.playAmbientSound();
        }
    }

    public void tick()
    {
        super.tick();
        BlockPos blockpos = this.entityData.get(ATTACHED_BLOCK_POS).orElse(null);
        if (blockpos == null && !this.level.isClientSide)
        {
            blockpos = this.blockPosition();
            this.entityData.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
        }

        if (this.isPassenger())
        {
            blockpos = null;
            float f = Objects.requireNonNull(this.getVehicle()).yRot;
            this.yRot = f;
            this.yBodyRot = f;
            this.yBodyRotO = f;
            this.clientSideTeleportInterpolation = 0;
        }
        else if (!this.level.isClientSide)
        {
            assert blockpos != null;
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (!blockstate.isAir(this.level, blockpos))
            {
                if (blockstate.is(Blocks.MOVING_PISTON))
                {
                    Direction direction = blockstate.getValue(PistonBlock.FACING);
                    if (this.level.isEmptyBlock(blockpos.relative(direction)))
                    {
                        blockpos = blockpos.relative(direction);
                        this.entityData.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
                    }
                    else
                    {
                        this.tryTeleportToNewPosition();
                    }
                }
                else if (blockstate.is(Blocks.PISTON_HEAD))
                {
                    Direction direction3 = blockstate.getValue(PistonHeadBlock.FACING);
                    if (this.level.isEmptyBlock(blockpos.relative(direction3)))
                    {
                        blockpos = blockpos.relative(direction3);
                        this.entityData.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
                    }
                    else
                    {
                        this.tryTeleportToNewPosition();
                    }
                }
                else
                {
                    this.tryTeleportToNewPosition();
                }
            }

            Direction direction4 = this.getAttachmentFacing();
            if (!this.canAttachOnBlockFace(blockpos, direction4))
            {
                Direction direction1 = this.findAttachableFace(blockpos);
                if (direction1 != null)
                {
                    this.entityData.set(ATTACHED_FACE, direction1);
                }
                else
                {
                    this.tryTeleportToNewPosition();
                }
            }
        }

        float f1 = (float) this.getPeekTick() * 0.01F;
        this.prevPeekAmount = this.peekAmount;
        if (this.peekAmount > f1)
        {
            this.peekAmount = MathHelper.clamp(this.peekAmount - 0.05F, f1, 1.0F);
        }
        else if (this.peekAmount < f1)
        {
            this.peekAmount = MathHelper.clamp(this.peekAmount + 0.05F, 0.0F, f1);
        }

        if (blockpos != null)
        {
            if (this.level.isClientSide)
            {
                if (this.clientSideTeleportInterpolation > 0 && this.currentAttachmentPosition != null)
                {
                    --this.clientSideTeleportInterpolation;
                }
                else
                {
                    this.currentAttachmentPosition = blockpos;
                }
            }

            this.setPosAndOldPos((double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D);
            double d2 = 0.5D - (double) MathHelper.sin((0.5F + this.peekAmount) * (float) Math.PI) * 0.5D;
            double d0 = 0.5D - (double) MathHelper.sin((0.5F + this.prevPeekAmount) * (float) Math.PI) * 0.5D;
            if (this.isAddedToWorld() && this.level instanceof net.minecraft.world.server.ServerWorld)
                ((net.minecraft.world.server.ServerWorld) this.level).updateChunkPos(this); // Forge - Process chunk registration after moving.
            Direction direction2 = this.getAttachmentFacing().getOpposite();
            this.setBoundingBox((new AxisAlignedBB(this.getX() - 0.5D, this.getY(), this.getZ() - 0.5D, this.getX() + 0.5D, this.getY() + 1.0D, this.getZ() + 0.5D)).expandTowards((double) direction2.getStepX() * d2, (double) direction2.getStepY() * d2, (double) direction2.getStepZ() * d2));
            double d1 = d2 - d0;
            if (d1 > 0.0D)
            {
                List<Entity> list = this.level.getEntities(this, this.getBoundingBox());
                if (!list.isEmpty())
                {
                    for (Entity entity : list)
                    {
                        if (!(entity instanceof SchalkerEntity) && !entity.noPhysics)
                        {
                            entity.move(MoverType.SHULKER, new Vector3d(d1 * (double) direction2.getStepX(), d1 * (double) direction2.getStepY(), d1 * (double) direction2.getStepZ()));
                        }
                    }
                }
            }
        }

    }

    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putByte("AttachFace", (byte) this.entityData.get(ATTACHED_FACE).get3DDataValue());
        compound.putByte("Peek", this.entityData.get(PEEK_TICK));
        compound.putByte("Color", this.entityData.get(COLOR));
        BlockPos blockpos = this.getAttachmentPos();
        if (blockpos != null)
        {
            compound.putInt("APX", blockpos.getX());
            compound.putInt("APY", blockpos.getY());
            compound.putInt("APZ", blockpos.getZ());
        }

    }

    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.entityData.set(ATTACHED_FACE, Direction.from3DDataValue(compound.getByte("AttachFace")));
        this.entityData.set(PEEK_TICK, compound.getByte("Peek"));
        this.entityData.set(COLOR, compound.getByte("Color"));
        if (compound.contains("APX"))
        {
            int i = compound.getInt("APX");
            int j = compound.getInt("APY");
            int k = compound.getInt("APZ");
            this.entityData.set(ATTACHED_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
        }
        else
        {
            this.entityData.set(ATTACHED_BLOCK_POS, Optional.empty());
        }

    }

    public void aiStep()
    {
        super.aiStep();
        this.setDeltaMovement(Vector3d.ZERO);
        if (!this.isNoAi())
        {
            this.yBodyRotO = 0.0F;
            this.yBodyRot = 0.0F;
        }

    }

    public int getMaxHeadXRot()
    {
        return 180;
    }

    public int getMaxHeadYRot()
    {
        return 180;
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_SCHALKER_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return this.isClosed() ? SoundEvents.SHULKER_HURT_CLOSED : SoundEvents.SHULKER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_SCHALKER_DEATH.get();
    }

    @Nullable
    protected Direction findAttachableFace(BlockPos p_234299_1_)
    {
        for (Direction direction : Direction.values())
        {
            if (this.canAttachOnBlockFace(p_234299_1_, direction))
            {
                return direction;
            }
        }

        return null;
    }

    protected boolean tryTeleportToNewPosition()
    {
        if (!this.isNoAi() && this.isAlive())
        {
            BlockPos blockpos = this.blockPosition();

            for (int i = 0; i < 5; ++i)
            {
                BlockPos blockpos1 = blockpos.offset(8 - this.random.nextInt(17), 8 - this.random.nextInt(17), 8 - this.random.nextInt(17));
                if (blockpos1.getY() > 0 && this.level.isEmptyBlock(blockpos1) && this.level.getWorldBorder().isWithinBounds(blockpos1) && this.level.noCollision(this, new AxisAlignedBB(blockpos1)))
                {
                    Direction direction = this.findAttachableFace(blockpos1);
                    if (direction != null)
                    {
                        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), 0);
                        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) direction = null;
                        blockpos1 = new BlockPos(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                    }

                    if (direction != null)
                    {
                        this.entityData.set(ATTACHED_FACE, direction);
                        this.playSound(SoundEvents.SHULKER_TELEPORT, 1.0F, 1.0F);
                        this.entityData.set(ATTACHED_BLOCK_POS, Optional.of(blockpos1));
                        this.entityData.set(PEEK_TICK, (byte) 0);
                        this.setTarget(null);
                        return true;
                    }
                }
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean canAttachOnBlockFace(BlockPos p_234298_1_, Direction p_234298_2_)
    {
        return this.level.loadedAndEntityCanStandOnFace(p_234298_1_.relative(p_234298_2_), this, p_234298_2_.getOpposite()) && this.level.noCollision(this, ShulkerAABBHelper.openBoundingBox(p_234298_1_, p_234298_2_.getOpposite()));
    }

    private boolean isClosed()
    {
        return this.getPeekTick() == 0;
    }

    static class DefenseAttackGoal extends NearestAttackableTargetGoal<LivingEntity>
    {
        public DefenseAttackGoal(SchalkerEntity schalker)
        {
            super(schalker, LivingEntity.class, 10, true, false, (p_200826_0_) ->
                p_200826_0_ instanceof IMob);
        }

        public boolean canUse()
        {
            return this.mob.getTeam() != null && super.canUse();
        }

        protected AxisAlignedBB getTargetSearchArea(double targetDistance)
        {
            Direction direction = ((SchalkerEntity) this.mob).getAttachmentFacing();
            if (direction.getAxis() == Direction.Axis.X)
            {
                return this.mob.getBoundingBox().inflate(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return direction.getAxis() == Direction.Axis.Z ? this.mob.getBoundingBox().inflate(targetDistance, targetDistance, 4.0D) : this.mob.getBoundingBox().inflate(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    static class BodyHelperController extends BodyController
    {
        public BodyHelperController(MobEntity p_i50612_2_)
        {
            super(p_i50612_2_);
        }

        public void clientTick()
        {
        }
    }

    class AttackGoal extends Goal
    {
        private int attackTime;

        public AttackGoal()
        {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse()
        {
            LivingEntity livingentity = SchalkerEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive())
            {
                return SchalkerEntity.this.level.getDifficulty() != Difficulty.PEACEFUL;
            }
            else
            {
                return false;
            }
        }

        public void start()
        {
            this.attackTime = 20;
            SchalkerEntity.this.updateArmorModifier(100);
        }

        public void stop()
        {
            SchalkerEntity.this.updateArmorModifier(0);
        }

        public void tick()
        {
            if (SchalkerEntity.this.level.getDifficulty() != Difficulty.PEACEFUL)
            {
                --this.attackTime;
                LivingEntity livingentity = SchalkerEntity.this.getTarget();
                assert livingentity != null;
                SchalkerEntity.this.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
                double d0 = SchalkerEntity.this.distanceToSqr(livingentity);
                if (d0 < 400.0D)
                {
                    if (this.attackTime <= 0)
                    {
                        this.attackTime = 20 + SchalkerEntity.this.random.nextInt(10) * 20 / 2;
                        SchalkerEntity.this.level.addFreshEntity(new ShulkerBulletEntity(SchalkerEntity.this.level, SchalkerEntity.this, livingentity, SchalkerEntity.this.getAttachmentFacing().getAxis()));
                        SchalkerEntity.this.playSound(SpackenmobsRegistry.ENTITY_SCHALKER_SHOOT.get(), 2.0F, (SchalkerEntity.this.random.nextFloat() - SchalkerEntity.this.random.nextFloat()) * 0.2F + 1.0F);
                    }
                }
                else
                {
                    SchalkerEntity.this.setTarget(null);
                }

                super.tick();
            }
        }
    }

    class AttackNearestGoal extends NearestAttackableTargetGoal<PlayerEntity>
    {
        public AttackNearestGoal(SchalkerEntity schalker)
        {
            super(schalker, PlayerEntity.class, true);
        }

        public boolean canUse()
        {
            return SchalkerEntity.this.level.getDifficulty() != Difficulty.PEACEFUL && super.canUse();
        }

        protected AxisAlignedBB getTargetSearchArea(double targetDistance)
        {
            Direction direction = ((SchalkerEntity) this.mob).getAttachmentFacing();
            if (direction.getAxis() == Direction.Axis.X)
            {
                return this.mob.getBoundingBox().inflate(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return direction.getAxis() == Direction.Axis.Z ? this.mob.getBoundingBox().inflate(targetDistance, targetDistance, 4.0D) : this.mob.getBoundingBox().inflate(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    class PeekGoal extends Goal
    {
        private int peekTime;

        private PeekGoal()
        {
        }

        public boolean canUse()
        {
            return SchalkerEntity.this.getTarget() == null && SchalkerEntity.this.random.nextInt(40) == 0;
        }

        public boolean canContinueToUse()
        {
            return SchalkerEntity.this.getTarget() == null && this.peekTime > 0;
        }

        public void start()
        {
            this.peekTime = 20 * (1 + SchalkerEntity.this.random.nextInt(3));
            SchalkerEntity.this.updateArmorModifier(30);
        }

        public void stop()
        {
            if (SchalkerEntity.this.getTarget() == null)
            {
                SchalkerEntity.this.updateArmorModifier(0);
            }

        }

        public void tick()
        {
            --this.peekTime;
        }
    }
}