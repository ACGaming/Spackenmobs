package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
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
import net.minecraft.item.DyeColor;
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

import javax.annotation.Nullable;
import java.util.*;

public class SchalkerEntity extends GolemEntity implements IMob
{
	protected static final DataParameter<Direction> ATTACHED_FACE = EntityDataManager.createKey(SchalkerEntity.class, DataSerializers.DIRECTION);
	protected static final DataParameter<Optional<BlockPos>> ATTACHED_BLOCK_POS = EntityDataManager.createKey(SchalkerEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
	protected static final DataParameter<Byte> PEEK_TICK = EntityDataManager.createKey(SchalkerEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> COLOR = EntityDataManager.createKey(SchalkerEntity.class, DataSerializers.BYTE);
	private static final UUID COVERED_ARMOR_BONUS_ID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
	private static final AttributeModifier COVERED_ARMOR_BONUS_MODIFIER = new AttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 20.0D, AttributeModifier.Operation.ADDITION);
	private float prevPeekAmount;
	private float peekAmount;
	private BlockPos currentAttachmentPosition = null;
	private int clientSideTeleportInterpolation;

	public SchalkerEntity(EntityType<? extends SchalkerEntity> p_i50196_1_, World p_i50196_2_)
	{
		super(p_i50196_1_, p_i50196_2_);
		this.experienceValue = 5;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new SchalkerEntity.AttackGoal());
		this.goalSelector.addGoal(7, new SchalkerEntity.PeekGoal());
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.targetSelector.addGoal(2, new SchalkerEntity.AttackNearestGoal(this));
		this.targetSelector.addGoal(3, new SchalkerEntity.DefenseAttackGoal(this));
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	public SoundCategory getSoundCategory()
	{
		return SoundCategory.HOSTILE;
	}

	protected SoundEvent getAmbientSound()
	{
		return SpackenmobsRegistry.ENTITY_SCHALKER_AMBIENT.get();
	}

	public void playAmbientSound()
	{
		if (!this.isClosed())
		{
			super.playAmbientSound();
		}
	}

	protected float getSoundVolume()
	{
		return 0.6F;
	}

	protected SoundEvent getDeathSound()
	{
		return SpackenmobsRegistry.ENTITY_SCHALKER_DEATH.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return this.isClosed() ? SoundEvents.ENTITY_SHULKER_HURT_CLOSED : SoundEvents.ENTITY_SHULKER_HURT;
	}

	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(ATTACHED_FACE, Direction.DOWN);
		this.dataManager.register(ATTACHED_BLOCK_POS, Optional.empty());
		this.dataManager.register(PEEK_TICK, (byte) 0);
		this.dataManager.register(COLOR, (byte) 16);
	}

	protected BodyController createBodyController()
	{
		return new BodyHelperController(this);
	}

	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(ATTACHED_FACE, Direction.byIndex(compound.getByte("AttachFace")));
		this.dataManager.set(PEEK_TICK, compound.getByte("Peek"));
		this.dataManager.set(COLOR, compound.getByte("Color"));
		if (compound.contains("APX"))
		{
			int i = compound.getInt("APX");
			int j = compound.getInt("APY");
			int k = compound.getInt("APZ");
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
		} else
		{
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.empty());
		}

	}

	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putByte("AttachFace", (byte) this.dataManager.get(ATTACHED_FACE).getIndex());
		compound.putByte("Peek", this.dataManager.get(PEEK_TICK));
		compound.putByte("Color", this.dataManager.get(COLOR));
		BlockPos blockpos = this.getAttachmentPos();
		if (blockpos != null)
		{
			compound.putInt("APX", blockpos.getX());
			compound.putInt("APY", blockpos.getY());
			compound.putInt("APZ", blockpos.getZ());
		}

	}

	public void tick()
	{
		super.tick();
		BlockPos blockpos = this.dataManager.get(ATTACHED_BLOCK_POS).orElse(null);
		if (blockpos == null && !this.world.isRemote)
		{
			blockpos = this.getPosition();
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
		}

		if (this.isPassenger())
		{
			blockpos = null;
			float f = Objects.requireNonNull(this.getRidingEntity()).rotationYaw;
			this.rotationYaw = f;
			this.renderYawOffset = f;
			this.prevRenderYawOffset = f;
			this.clientSideTeleportInterpolation = 0;
		} else if (!this.world.isRemote)
		{
			assert blockpos != null;
			BlockState blockstate = this.world.getBlockState(blockpos);
			if (!blockstate.isAir(this.world, blockpos))
			{
				if (blockstate.isIn(Blocks.MOVING_PISTON))
				{
					Direction direction = blockstate.get(PistonBlock.FACING);
					if (this.world.isAirBlock(blockpos.offset(direction)))
					{
						blockpos = blockpos.offset(direction);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
					} else
					{
						this.tryTeleportToNewPosition();
					}
				} else if (blockstate.isIn(Blocks.PISTON_HEAD))
				{
					Direction direction3 = blockstate.get(PistonHeadBlock.FACING);
					if (this.world.isAirBlock(blockpos.offset(direction3)))
					{
						blockpos = blockpos.offset(direction3);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
					} else
					{
						this.tryTeleportToNewPosition();
					}
				} else
				{
					this.tryTeleportToNewPosition();
				}
			}

			Direction direction4 = this.getAttachmentFacing();
			if (!this.func_234298_a_(blockpos, direction4))
			{
				Direction direction1 = this.func_234299_g_(blockpos);
				if (direction1 != null)
				{
					this.dataManager.set(ATTACHED_FACE, direction1);
				} else
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
		} else if (this.peekAmount < f1)
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
				} else
				{
					this.currentAttachmentPosition = blockpos;
				}
			}

			this.forceSetPosition((double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D);
			double d2 = 0.5D - (double) MathHelper.sin((0.5F + this.peekAmount) * (float) Math.PI) * 0.5D;
			double d0 = 0.5D - (double) MathHelper.sin((0.5F + this.prevPeekAmount) * (float) Math.PI) * 0.5D;
			if (this.isAddedToWorld() && this.world instanceof net.minecraft.world.server.ServerWorld)
				((net.minecraft.world.server.ServerWorld) this.world).chunkCheck(this); // Forge - Process chunk registration after moving.
			Direction direction2 = this.getAttachmentFacing().getOpposite();
			this.setBoundingBox((new AxisAlignedBB(this.getPosX() - 0.5D, this.getPosY(), this.getPosZ() - 0.5D, this.getPosX() + 0.5D, this.getPosY() + 1.0D, this.getPosZ() + 0.5D)).expand((double) direction2.getXOffset() * d2, (double) direction2.getYOffset() * d2, (double) direction2.getZOffset() * d2));
			double d1 = d2 - d0;
			if (d1 > 0.0D)
			{
				List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox());
				if (!list.isEmpty())
				{
					for (Entity entity : list)
					{
						if (!(entity instanceof SchalkerEntity) && !entity.noClip)
						{
							entity.move(MoverType.SHULKER, new Vector3d(d1 * (double) direction2.getXOffset(), d1 * (double) direction2.getYOffset(), d1 * (double) direction2.getZOffset()));
						}
					}
				}
			}
		}

	}

	public void move(MoverType typeIn, Vector3d pos)
	{
		if (typeIn == MoverType.SHULKER_BOX)
		{
			this.tryTeleportToNewPosition();
		} else
		{
			super.move(typeIn, pos);
		}

	}

	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
		if (this.dataManager != null && this.ticksExisted != 0)
		{
			Optional<BlockPos> optional = this.dataManager.get(ATTACHED_BLOCK_POS);
			if (this.isAddedToWorld() && this.world instanceof net.minecraft.world.server.ServerWorld)
				((net.minecraft.world.server.ServerWorld) this.world).chunkCheck(this); // Forge - Process chunk registration after moving.
			Optional<BlockPos> optional1 = Optional.of(new BlockPos(x, y, z));
			if (!optional1.equals(optional))
			{
				this.dataManager.set(ATTACHED_BLOCK_POS, optional1);
				this.dataManager.set(PEEK_TICK, (byte) 0);
				this.isAirBorne = true;
			}

		}
	}

	@Nullable
	protected Direction func_234299_g_(BlockPos p_234299_1_)
	{
		for (Direction direction : Direction.values())
		{
			if (this.func_234298_a_(p_234299_1_, direction))
			{
				return direction;
			}
		}

		return null;
	}

	private boolean func_234298_a_(BlockPos p_234298_1_, Direction p_234298_2_)
	{
		return this.world.isDirectionSolid(p_234298_1_.offset(p_234298_2_), this, p_234298_2_.getOpposite()) && this.world.hasNoCollisions(this, ShulkerAABBHelper.getOpenedCollisionBox(p_234298_1_, p_234298_2_.getOpposite()));
	}

	protected boolean tryTeleportToNewPosition()
	{
		if (!this.isAIDisabled() && this.isAlive())
		{
			BlockPos blockpos = this.getPosition();

			for (int i = 0; i < 5; ++i)
			{
				BlockPos blockpos1 = blockpos.add(8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17));
				if (blockpos1.getY() > 0 && this.world.isAirBlock(blockpos1) && this.world.getWorldBorder().contains(blockpos1) && this.world.hasNoCollisions(this, new AxisAlignedBB(blockpos1)))
				{
					Direction direction = this.func_234299_g_(blockpos1);
					if (direction != null)
					{
						net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), 0);
						if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) direction = null;
						blockpos1 = new BlockPos(event.getTargetX(), event.getTargetY(), event.getTargetZ());
					}

					if (direction != null)
					{
						this.dataManager.set(ATTACHED_FACE, direction);
						this.playSound(SoundEvents.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos1));
						this.dataManager.set(PEEK_TICK, (byte) 0);
						this.setAttackTarget(null);
						return true;
					}
				}
			}

			return false;
		} else
		{
			return true;
		}
	}

	public void livingTick()
	{
		super.livingTick();
		this.setMotion(Vector3d.ZERO);
		if (!this.isAIDisabled())
		{
			this.prevRenderYawOffset = 0.0F;
			this.renderYawOffset = 0.0F;
		}

	}

	public void notifyDataManagerChange(DataParameter<?> key)
	{
		if (ATTACHED_BLOCK_POS.equals(key) && this.world.isRemote && !this.isPassenger())
		{
			BlockPos blockpos = this.getAttachmentPos();
			if (blockpos != null)
			{
				if (this.currentAttachmentPosition == null)
				{
					this.currentAttachmentPosition = blockpos;
				} else
				{
					this.clientSideTeleportInterpolation = 6;
				}

				this.forceSetPosition((double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D);
			}
		}

		super.notifyDataManagerChange(key);
	}

	@OnlyIn(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
	{
		this.newPosRotationIncrements = 0;
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isClosed())
		{
			Entity entity = source.getImmediateSource();
			if (entity instanceof AbstractArrowEntity)
			{
				return false;
			}
		}

		if (super.attackEntityFrom(source, amount))
		{
			if ((double) this.getHealth() < (double) this.getMaxHealth() * 0.5D && this.rand.nextInt(4) == 0)
			{
				this.tryTeleportToNewPosition();
			}

			return true;
		} else
		{
			return false;
		}
	}

	private boolean isClosed()
	{
		return this.getPeekTick() == 0;
	}

	public boolean func_241845_aY()
	{
		return this.isAlive();
	}

	public Direction getAttachmentFacing()
	{
		return this.dataManager.get(ATTACHED_FACE);
	}

	@Nullable
	public BlockPos getAttachmentPos()
	{
		return this.dataManager.get(ATTACHED_BLOCK_POS).orElse(null);
	}

	public void setAttachmentPos(@Nullable BlockPos pos)
	{
		this.dataManager.set(ATTACHED_BLOCK_POS, Optional.ofNullable(pos));
	}

	public int getPeekTick()
	{
		return this.dataManager.get(PEEK_TICK);
	}

	public void updateArmorModifier(int p_184691_1_)
	{
		if (!this.world.isRemote)
		{
			Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).removeModifier(COVERED_ARMOR_BONUS_MODIFIER);
			if (p_184691_1_ == 0)
			{
				Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).applyPersistentModifier(COVERED_ARMOR_BONUS_MODIFIER);
				this.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 1.0F, 1.0F);
			} else
			{
				this.playSound(SpackenmobsRegistry.ENTITY_SCHALKER_OPEN.get(), 1.0F, 1.0F);
			}
		}

		this.dataManager.set(PEEK_TICK, (byte) p_184691_1_);
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

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 0.5F;
	}

	public int getVerticalFaceSpeed()
	{
		return 180;
	}

	public int getHorizontalFaceSpeed()
	{
		return 180;
	}

	public void applyEntityCollision(Entity entityIn)
	{
	}

	public float getCollisionBorderSize()
	{
		return 0.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isAttachedToBlock()
	{
		return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor()
	{
		byte obyte = this.dataManager.get(COLOR);
		return obyte <= 15 ? DyeColor.byId(obyte) : null;
	}

	static class DefenseAttackGoal extends NearestAttackableTargetGoal<LivingEntity>
	{
		public DefenseAttackGoal(SchalkerEntity schalker)
		{
			super(schalker, LivingEntity.class, 10, true, false, (p_200826_0_) ->
					p_200826_0_ instanceof IMob);
		}

		public boolean shouldExecute()
		{
			return this.goalOwner.getTeam() != null && super.shouldExecute();
		}

		protected AxisAlignedBB getTargetableArea(double targetDistance)
		{
			Direction direction = ((SchalkerEntity) this.goalOwner).getAttachmentFacing();
			if (direction.getAxis() == Direction.Axis.X)
			{
				return this.goalOwner.getBoundingBox().grow(4.0D, targetDistance, targetDistance);
			} else
			{
				return direction.getAxis() == Direction.Axis.Z ? this.goalOwner.getBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.goalOwner.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
			}
		}
	}

	static class BodyHelperController extends BodyController
	{
		public BodyHelperController(MobEntity p_i50612_2_)
		{
			super(p_i50612_2_);
		}

		public void updateRenderAngles()
		{
		}
	}

	class AttackGoal extends Goal
	{
		private int attackTime;

		public AttackGoal()
		{
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean shouldExecute()
		{
			LivingEntity livingentity = SchalkerEntity.this.getAttackTarget();
			if (livingentity != null && livingentity.isAlive())
			{
				return SchalkerEntity.this.world.getDifficulty() != Difficulty.PEACEFUL;
			} else
			{
				return false;
			}
		}

		public void startExecuting()
		{
			this.attackTime = 20;
			SchalkerEntity.this.updateArmorModifier(100);
		}

		public void resetTask()
		{
			SchalkerEntity.this.updateArmorModifier(0);
		}

		public void tick()
		{
			if (SchalkerEntity.this.world.getDifficulty() != Difficulty.PEACEFUL)
			{
				--this.attackTime;
				LivingEntity livingentity = SchalkerEntity.this.getAttackTarget();
				assert livingentity != null;
				SchalkerEntity.this.getLookController().setLookPositionWithEntity(livingentity, 180.0F, 180.0F);
				double d0 = SchalkerEntity.this.getDistanceSq(livingentity);
				if (d0 < 400.0D)
				{
					if (this.attackTime <= 0)
					{
						this.attackTime = 20 + SchalkerEntity.this.rand.nextInt(10) * 20 / 2;
						SchalkerEntity.this.world.addEntity(new ShulkerBulletEntity(SchalkerEntity.this.world, SchalkerEntity.this, livingentity, SchalkerEntity.this.getAttachmentFacing().getAxis()));
						SchalkerEntity.this.playSound(SpackenmobsRegistry.ENTITY_SCHALKER_SHOOT.get(), 2.0F, (SchalkerEntity.this.rand.nextFloat() - SchalkerEntity.this.rand.nextFloat()) * 0.2F + 1.0F);
					}
				} else
				{
					SchalkerEntity.this.setAttackTarget(null);
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

		public boolean shouldExecute()
		{
			return SchalkerEntity.this.world.getDifficulty() != Difficulty.PEACEFUL && super.shouldExecute();
		}

		protected AxisAlignedBB getTargetableArea(double targetDistance)
		{
			Direction direction = ((SchalkerEntity) this.goalOwner).getAttachmentFacing();
			if (direction.getAxis() == Direction.Axis.X)
			{
				return this.goalOwner.getBoundingBox().grow(4.0D, targetDistance, targetDistance);
			} else
			{
				return direction.getAxis() == Direction.Axis.Z ? this.goalOwner.getBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.goalOwner.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
			}
		}
	}

	class PeekGoal extends Goal
	{
		private int peekTime;

		private PeekGoal()
		{
		}

		public boolean shouldExecute()
		{
			return SchalkerEntity.this.getAttackTarget() == null && SchalkerEntity.this.rand.nextInt(40) == 0;
		}

		public boolean shouldContinueExecuting()
		{
			return SchalkerEntity.this.getAttackTarget() == null && this.peekTime > 0;
		}

		public void startExecuting()
		{
			this.peekTime = 20 * (1 + SchalkerEntity.this.rand.nextInt(3));
			SchalkerEntity.this.updateArmorModifier(30);
		}

		public void resetTask()
		{
			if (SchalkerEntity.this.getAttackTarget() == null)
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