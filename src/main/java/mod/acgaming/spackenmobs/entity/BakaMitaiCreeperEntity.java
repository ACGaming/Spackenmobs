package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.entity.ai.goal.BakaMitaiCreeperSwellGoal;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IChargeableMob.class
)
public class BakaMitaiCreeperEntity extends MonsterEntity implements IChargeableMob
{
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(BakaMitaiCreeperEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(BakaMitaiCreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(BakaMitaiCreeperEntity.class, DataSerializers.BOOLEAN);
	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 100;
	private int explosionRadius = 12;
	private int droppedSkulls;

	public BakaMitaiCreeperEntity(EntityType<? extends BakaMitaiCreeperEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new BakaMitaiCreeperSwellGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	public int getMaxFallHeight()
	{
		return this.getAttackTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
	}

	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		boolean flag = super.onLivingFall(distance, damageMultiplier);
		this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + distance * 1.5F);
		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}

		return flag;
	}

	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(STATE, -1);
		this.dataManager.register(POWERED, false);
		this.dataManager.register(IGNITED, false);
	}

	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(POWERED))
		{
			compound.putBoolean("powered", true);
		}

		compound.putShort("Fuse", (short) this.fuseTime);
		compound.putByte("ExplosionRadius", (byte) this.explosionRadius);
		compound.putBoolean("ignited", this.hasIgnited());
	}

	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(POWERED, compound.getBoolean("powered"));
		if (compound.contains("Fuse", 99))
		{
			this.fuseTime = compound.getShort("Fuse");
		}

		if (compound.contains("ExplosionRadius", 99))
		{
			this.explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited"))
		{
			this.ignite();
		}

	}

	public void tick()
	{
		if (this.isAlive())
		{
			this.lastActiveTime = this.timeSinceIgnited;
			if (this.hasIgnited())
			{
				this.setCreeperState(1);
			}

			int i = this.getCreeperState();
			if (i > 0 && this.timeSinceIgnited == 0)
			{
				this.playSound(SpackenmobsRegistry.ENTITY_BAKAMITAI_CREEPER_FUSE.get(), 1.0F, 1.0F);
			}

			this.timeSinceIgnited += i;
			if (this.timeSinceIgnited < 0)
			{
				this.timeSinceIgnited = 0;
			}

			if (this.timeSinceIgnited >= this.fuseTime)
			{
				this.timeSinceIgnited = this.fuseTime;
				this.explode();
			}
		}

		super.tick();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn)
	{
		super.dropSpecialItems(source, looting, recentlyHitIn);
		Entity entity = source.getTrueSource();
		if (entity != this && entity instanceof BakaMitaiCreeperEntity)
		{
			BakaMitaiCreeperEntity creeperentity = (BakaMitaiCreeperEntity) entity;
			if (creeperentity.ableToCauseSkullDrop())
			{
				creeperentity.incrementDroppedSkulls();
				this.entityDropItem(Items.CREEPER_HEAD);
			}
		}
	}

	public boolean attackEntityAsMob(Entity entityIn)
	{
		return true;
	}

	public boolean isCharged()
	{
		return this.dataManager.get(POWERED);
	}

	@OnlyIn(Dist.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks)
	{
		return MathHelper.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
	}

	public int getCreeperState()
	{
		return this.dataManager.get(STATE);
	}

	public void setCreeperState(int state)
	{
		this.dataManager.set(STATE, state);
	}

	public void func_241841_a(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_)
	{
		super.func_241841_a(p_241841_1_, p_241841_2_);
		this.dataManager.set(POWERED, true);
	}

	protected ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_)
	{
		ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
		if (itemstack.getItem() == Items.FLINT_AND_STEEL)
		{
			this.world.playSound(p_230254_1_, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
			if (!this.world.isRemote)
			{
				this.ignite();
				itemstack.damageItem(1, p_230254_1_, (player) ->
				{
					player.sendBreakAnimation(p_230254_2_);
				});
			}

			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else
		{
			return super.func_230254_b_(p_230254_1_, p_230254_2_);
		}
	}

	private void explode()
	{
		if (!this.world.isRemote)
		{
			Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			float f = this.isCharged() ? 2.0F : 1.0F;
			this.dead = true;
			this.playSound(SpackenmobsRegistry.ENTITY_BAKAMITAI_CREEPER_BLOW.get(), 2.0F, 1.0F);
			this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float) this.explosionRadius * f, explosion$mode);
			this.remove();
			this.spawnLingeringCloud();
		}

	}

	private void spawnLingeringCloud()
	{
		Collection<EffectInstance> collection = this.getActivePotionEffects();
		if (!collection.isEmpty())
		{
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ());
			areaeffectcloudentity.setRadius(2.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

			for (EffectInstance effectinstance : collection)
			{
				areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
			}

			this.world.addEntity(areaeffectcloudentity);
		}

	}

	public boolean hasIgnited()
	{
		return this.dataManager.get(IGNITED);
	}

	public void ignite()
	{
		this.dataManager.set(IGNITED, true);
	}

	public boolean ableToCauseSkullDrop()
	{
		return this.isCharged() && this.droppedSkulls < 1;
	}

	public void incrementDroppedSkulls()
	{
		++this.droppedSkulls;
	}
}