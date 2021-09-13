package mod.acgaming.spackenmobs.entities;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;

public class EntitySchalker extends EntityGolem implements IMob
{
    public static final EnumDyeColor DEFAULT_COLOR = EnumDyeColor.PURPLE;
    protected static final DataParameter<EnumFacing> ATTACHED_FACE = EntityDataManager
        .createKey(EntitySchalker.class, DataSerializers.FACING);
    protected static final DataParameter<Optional<BlockPos>> ATTACHED_BLOCK_POS = EntityDataManager
        .createKey(EntitySchalker.class, DataSerializers.OPTIONAL_BLOCK_POS);
    protected static final DataParameter<Byte> PEEK_TICK = EntityDataManager.createKey(EntitySchalker.class,
        DataSerializers.BYTE);
    protected static final DataParameter<Byte> COLOR = EntityDataManager.createKey(EntitySchalker.class,
        DataSerializers.BYTE);
    private static final UUID COVERED_ARMOR_BONUS_ID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
    private static final AttributeModifier COVERED_ARMOR_BONUS_MODIFIER = (new AttributeModifier(COVERED_ARMOR_BONUS_ID,
        "Covered armor bonus", 20.0D, 0)).setSaved(false);
    private float prevPeekAmount;
    private float peekAmount;
    private BlockPos currentAttachmentPosition;
    private int clientSideTeleportInterpolation;

    public EntitySchalker(World worldIn)
    {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        this.isImmuneToFire = true;
        this.currentAttachmentPosition = null;
        this.experienceValue = 5;
    }

    @Override
    public void setPosition(double x, double y, double z)
    {
        super.setPosition(x, y, z);

        if (this.dataManager != null && this.ticksExisted != 0)
        {
            Optional<BlockPos> optional = this.dataManager.get(ATTACHED_BLOCK_POS);
            Optional<BlockPos> optional1 = Optional.of(new BlockPos(x, y, z));

            if (!optional1.equals(optional))
            {
                this.dataManager.set(ATTACHED_BLOCK_POS, optional1);
                this.dataManager.set(PEEK_TICK, (byte) 0);
                this.isAirBorne = true;
            }
        }
    }

    @Override
    public void move(MoverType type, double x, double y, double z)
    {
        if (type == MoverType.SHULKER_BOX)
        {
            this.tryTeleportToNewPosition();
        }
        else
        {
            super.move(type, x, y, z);
        }
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
    }

    @Override
    public void applyEntityCollision(Entity entityIn)
    {
    }

    @Override
    public float getCollisionBorderSize()
    {
        return 0.0F;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.5F;
    }

    @Override
    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isClosed())
        {
            Entity entity = source.getImmediateSource();

            if (entity instanceof EntityArrow)
            {
                return false;
            }
        }

        if (super.attackEntityFrom(source, amount))
        {
            if (this.getHealth() < this.getMaxHealth() * 0.5D && this.rand.nextInt(4) == 0)
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

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
                                             int posRotationIncrements, boolean teleport)
    {
        this.newPosRotationIncrements = 0;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (ATTACHED_BLOCK_POS.equals(key) && this.world.isRemote && !this.isRiding())
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

                this.posX = blockpos.getX() + 0.5D;
                this.posY = blockpos.getY();
                this.posZ = blockpos.getZ() + 0.5D;
                this.prevPosX = this.posX;
                this.prevPosY = this.posY;
                this.prevPosZ = this.posZ;
                this.lastTickPosX = this.posX;
                this.lastTickPosY = this.posY;
                this.lastTickPosZ = this.posZ;
            }
        }

        super.notifyDataManagerChange(key);
    }

    public EnumFacing getAttachmentFacing()
    {
        return this.dataManager.get(ATTACHED_FACE);
    }

    @Nullable
    public BlockPos getAttachmentPos()
    {
        return this.dataManager.get(ATTACHED_BLOCK_POS).orNull();
    }

    public int getPeekTick()
    {
        return this.dataManager.get(PEEK_TICK);
    }

    public void updateArmorModifier(int p_184691_1_)
    {
        if (!this.world.isRemote)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(COVERED_ARMOR_BONUS_MODIFIER);

            if (p_184691_1_ == 0)
            {
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier(COVERED_ARMOR_BONUS_MODIFIER);
                this.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 1.0F, 1.0F);
            }
            else
            {
                this.playSound(ModSoundEvents.ENTITY_SCHALKER_OPEN, 1.0F, 1.0F);
            }
        }

        this.dataManager.set(PEEK_TICK, (byte) p_184691_1_);
    }

    @SideOnly(Side.CLIENT)
    public float getClientPeekAmount(float p_184688_1_)
    {
        return this.prevPeekAmount + (this.peekAmount - this.prevPeekAmount) * p_184688_1_;
    }

    @SideOnly(Side.CLIENT)
    public int getClientTeleportInterp()
    {
        return this.clientSideTeleportInterpolation;
    }

    @SideOnly(Side.CLIENT)
    public BlockPos getOldAttachPos()
    {
        return this.currentAttachmentPosition;
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttachedToBlock()
    {
        return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
    }

    @SideOnly(Side.CLIENT)
    public EnumDyeColor getColor()
    {
        return EnumDyeColor.byMetadata(this.dataManager.get(COLOR));
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntitySchalker.AIAttack());
        this.tasks.addTask(7, new EntitySchalker.AIPeek());
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntitySchalker.AIAttackNearest(this));
        this.targetTasks.addTask(3, new EntitySchalker.AIDefenseAttack(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected EntityBodyHelper createBodyHelper()
    {
        return new BodyHelper(this);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ATTACHED_FACE, EnumFacing.DOWN);
        this.dataManager.register(ATTACHED_BLOCK_POS, Optional.absent());
        this.dataManager.register(PEEK_TICK, (byte) 0);
        this.dataManager.register(COLOR, (byte) DEFAULT_COLOR.getMetadata());
    }

    @Override
    public void playLivingSound()
    {
        if (!this.isClosed())
        {
            super.playLivingSound();
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        BlockPos blockpos = this.dataManager.get(ATTACHED_BLOCK_POS).orNull();

        if (blockpos == null && !this.world.isRemote)
        {
            blockpos = new BlockPos(this);
            this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
        }

        if (this.isRiding())
        {
            blockpos = null;
            float f = this.getRidingEntity().rotationYaw;
            this.rotationYaw = f;
            this.renderYawOffset = f;
            this.prevRenderYawOffset = f;
            this.clientSideTeleportInterpolation = 0;
        }
        else if (!this.world.isRemote)
        {
            IBlockState iblockstate = this.world.getBlockState(blockpos);

            if (iblockstate.getMaterial() != Material.AIR)
            {
                if (iblockstate.getBlock() == Blocks.PISTON_EXTENSION)
                {
                    EnumFacing enumfacing = iblockstate.getValue(BlockDirectional.FACING);

                    if (this.world.isAirBlock(blockpos.offset(enumfacing)))
                    {
                        blockpos = blockpos.offset(enumfacing);
                        this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
                    }
                    else
                    {
                        this.tryTeleportToNewPosition();
                    }
                }
                else if (iblockstate.getBlock() == Blocks.PISTON_HEAD)
                {
                    EnumFacing enumfacing3 = iblockstate.getValue(BlockDirectional.FACING);

                    if (this.world.isAirBlock(blockpos.offset(enumfacing3)))
                    {
                        blockpos = blockpos.offset(enumfacing3);
                        this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
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

            BlockPos blockpos1 = blockpos.offset(this.getAttachmentFacing());

            if (!this.world.isBlockNormalCube(blockpos1, false))
            {
                boolean flag = false;

                for (EnumFacing enumfacing1 : EnumFacing.values())
                {
                    blockpos1 = blockpos.offset(enumfacing1);

                    if (this.world.isBlockNormalCube(blockpos1, false))
                    {
                        this.dataManager.set(ATTACHED_FACE, enumfacing1);
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                {
                    this.tryTeleportToNewPosition();
                }
            }

            BlockPos blockpos2 = blockpos.offset(this.getAttachmentFacing().getOpposite());

            if (this.world.isBlockNormalCube(blockpos2, false))
            {
                this.tryTeleportToNewPosition();
            }
        }

        float f1 = this.getPeekTick() * 0.01F;
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
            if (this.world.isRemote)
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

            this.posX = blockpos.getX() + 0.5D;
            this.posY = blockpos.getY();
            this.posZ = blockpos.getZ() + 0.5D;
            if (this.isAddedToWorld() && !this.world.isRemote)
                this.world.updateEntityWithOptionalForce(this, false); // Forge
            // -
            // Process
            // chunk
            // registration
            // after
            // moving.
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.lastTickPosX = this.posX;
            this.lastTickPosY = this.posY;
            this.lastTickPosZ = this.posZ;
            double d3 = 0.5D - MathHelper.sin((0.5F + this.peekAmount) * (float) Math.PI) * 0.5D;
            double d4 = 0.5D - MathHelper.sin((0.5F + this.prevPeekAmount) * (float) Math.PI) * 0.5D;
            double d5 = d3 - d4;
            double d0 = 0.0D;
            double d1 = 0.0D;
            double d2 = 0.0D;
            EnumFacing enumfacing2 = this.getAttachmentFacing();

            switch (enumfacing2)
            {
                case DOWN:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D,
                        this.posX + 0.5D, this.posY + 1.0D + d3, this.posZ + 0.5D));
                    d1 = d5;
                    break;
                case UP:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY - d3, this.posZ - 0.5D,
                        this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
                    d1 = -d5;
                    break;
                case NORTH:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D,
                        this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D + d3));
                    d2 = d5;
                    break;
                case SOUTH:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D - d3,
                        this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
                    d2 = -d5;
                    break;
                case WEST:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D,
                        this.posX + 0.5D + d3, this.posY + 1.0D, this.posZ + 0.5D));
                    d0 = d5;
                    break;
                case EAST:
                    this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D - d3, this.posY, this.posZ - 0.5D,
                        this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
                    d0 = -d5;
            }

            if (d5 > 0.0D)
            {
                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

                if (!list.isEmpty())
                {
                    for (Entity entity : list)
                    {
                        if (!(entity instanceof EntitySchalker) && !entity.noClip)
                        {
                            entity.move(MoverType.SHULKER, d0, d1, d2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("AttachFace", (byte) this.dataManager.get(ATTACHED_FACE).getIndex());
        compound.setByte("Peek", this.dataManager.get(PEEK_TICK));
        compound.setByte("Color", this.dataManager.get(COLOR));
        BlockPos blockpos = this.getAttachmentPos();

        if (blockpos != null)
        {
            compound.setInteger("APX", blockpos.getX());
            compound.setInteger("APY", blockpos.getY());
            compound.setInteger("APZ", blockpos.getZ());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(ATTACHED_FACE, EnumFacing.getFront(compound.getByte("AttachFace")));
        this.dataManager.set(PEEK_TICK, compound.getByte("Peek"));
        this.dataManager.set(COLOR, compound.getByte("Color"));

        if (compound.hasKey("APX"))
        {
            int i = compound.getInteger("APX");
            int j = compound.getInteger("APY");
            int k = compound.getInteger("APZ");
            this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
        }
        else
        {
            this.dataManager.set(ATTACHED_BLOCK_POS, Optional.absent());
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_SHULKER;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        this.rotationYaw = 180.0F;
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        return 180;
    }

    @Override
    public int getHorizontalFaceSpeed()
    {
        return 180;
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.renderYawOffset = 180.0F;
        this.prevRenderYawOffset = 180.0F;
        this.rotationYaw = 180.0F;
        this.prevRotationYaw = 180.0F;
        this.rotationYawHead = 180.0F;
        this.prevRotationYawHead = 180.0F;
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.ENTITY_SCHALKER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return this.isClosed() ? SoundEvents.ENTITY_SHULKER_HURT_CLOSED : SoundEvents.ENTITY_SHULKER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSoundEvents.ENTITY_SCHALKER_DEATH;
    }

    protected boolean tryTeleportToNewPosition()
    {
        if (!this.isAIDisabled() && this.isEntityAlive())
        {
            BlockPos blockpos = new BlockPos(this);

            for (int i = 0; i < 5; ++i)
            {
                BlockPos blockpos1 = blockpos.add(8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17),
                    8 - this.rand.nextInt(17));

                if (blockpos1.getY() > 0 && this.world.isAirBlock(blockpos1) && this.world.isInsideWorldBorder(this)
                    && this.world.getCollisionBoxes(this, new AxisAlignedBB(blockpos1)).isEmpty())
                {
                    boolean flag = false;

                    for (EnumFacing enumfacing : EnumFacing.values())
                    {
                        if (this.world.isBlockNormalCube(blockpos1.offset(enumfacing), false))
                        {
                            this.dataManager.set(ATTACHED_FACE, enumfacing);
                            flag = true;
                            break;
                        }
                    }

                    if (flag)
                    {
                        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(
                            this, blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), 0);
                        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                            flag = false;
                        blockpos1 = new BlockPos(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                    }

                    if (flag)
                    {
                        this.playSound(SoundEvents.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
                        this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos1));
                        this.dataManager.set(PEEK_TICK, (byte) 0);
                        this.setAttackTarget(null);
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

    private boolean isClosed()
    {
        return this.getPeekTick() == 0;
    }

    static class AIDefenseAttack extends EntityAINearestAttackableTarget<EntityLivingBase>
    {
        public AIDefenseAttack(EntitySchalker schalker)
        {
            super(schalker, EntityLivingBase.class, 10, true, false, new Predicate<EntityLivingBase>()
            {
                @Override
                public boolean apply(@Nullable EntityLivingBase p_apply_1_)
                {
                    return p_apply_1_ instanceof IMob;
                }
            });
        }

        @Override
        public boolean shouldExecute()
        {
            return this.taskOwner.getTeam() != null && super.shouldExecute();
        }

        @Override
        protected AxisAlignedBB getTargetableArea(double targetDistance)
        {
            EnumFacing enumfacing = ((EntitySchalker) this.taskOwner).getAttachmentFacing();

            if (enumfacing.getAxis() == EnumFacing.Axis.X)
            {
                return this.taskOwner.getEntityBoundingBox().grow(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return enumfacing.getAxis() == EnumFacing.Axis.Z
                    ? this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, 4.0D)
                    : this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    static class BodyHelper extends EntityBodyHelper
    {
        public BodyHelper(EntityLivingBase theEntity)
        {
            super(theEntity);
        }

        @Override
        public void updateRenderAngles()
        {
        }
    }

    class AIAttack extends EntityAIBase
    {
        private int attackTime;

        public AIAttack()
        {
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = EntitySchalker.this.getAttackTarget();

            if (entitylivingbase != null && entitylivingbase.isEntityAlive())
            {
                return EntitySchalker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
            }
            else
            {
                return false;
            }
        }

        @Override
        public void startExecuting()
        {
            this.attackTime = 20;
            EntitySchalker.this.updateArmorModifier(100);
        }

        @Override
        public void resetTask()
        {
            EntitySchalker.this.updateArmorModifier(0);
        }

        @Override
        public void updateTask()
        {
            if (EntitySchalker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
            {
                --this.attackTime;
                EntityLivingBase entitylivingbase = EntitySchalker.this.getAttackTarget();
                EntitySchalker.this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 180.0F, 180.0F);
                double d0 = EntitySchalker.this.getDistanceSq(entitylivingbase);

                if (d0 < 400.0D)
                {
                    if (this.attackTime <= 0)
                    {
                        this.attackTime = 20 + EntitySchalker.this.rand.nextInt(10) * 20 / 2;
                        EntityShulkerBullet entityshulkerbullet = new EntityShulkerBullet(
                            EntitySchalker.this.world,
                            EntitySchalker.this, entitylivingbase,
                            EntitySchalker.this.getAttachmentFacing().getAxis()
                        );
                        EntitySchalker.this.world.spawnEntity(entityshulkerbullet);
                        EntitySchalker.this.playSound(ModSoundEvents.ENTITY_SCHALKER_SHOOT, 2.0F,
                            (EntitySchalker.this.rand.nextFloat() - EntitySchalker.this.rand.nextFloat()) * 0.2F
                                + 1.0F);
                    }
                }
                else
                {
                    EntitySchalker.this.setAttackTarget(null);
                }

                super.updateTask();
            }
        }
    }

    class AIAttackNearest extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AIAttackNearest(EntitySchalker schalker)
        {
            super(schalker, EntityPlayer.class, true);
        }

        @Override
        public boolean shouldExecute()
        {
            return EntitySchalker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL && super.shouldExecute();
        }

        @Override
        protected AxisAlignedBB getTargetableArea(double targetDistance)
        {
            EnumFacing enumfacing = ((EntitySchalker) this.taskOwner).getAttachmentFacing();

            if (enumfacing.getAxis() == EnumFacing.Axis.X)
            {
                return this.taskOwner.getEntityBoundingBox().grow(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return enumfacing.getAxis() == EnumFacing.Axis.Z
                    ? this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, 4.0D)
                    : this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    class AIPeek extends EntityAIBase
    {
        private int peekTime;

        private AIPeek()
        {
        }

        @Override
        public boolean shouldExecute()
        {
            return EntitySchalker.this.getAttackTarget() == null && EntitySchalker.this.rand.nextInt(40) == 0;
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return EntitySchalker.this.getAttackTarget() == null && this.peekTime > 0;
        }

        @Override
        public void startExecuting()
        {
            this.peekTime = 20 * (1 + EntitySchalker.this.rand.nextInt(3));
            EntitySchalker.this.updateArmorModifier(30);
        }

        @Override
        public void resetTask()
        {
            if (EntitySchalker.this.getAttackTarget() == null)
            {
                EntitySchalker.this.updateArmorModifier(0);
            }
        }

        @Override
        public void updateTask()
        {
            --this.peekTime;
        }
    }
}