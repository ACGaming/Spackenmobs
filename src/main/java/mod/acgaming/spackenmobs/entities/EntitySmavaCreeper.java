package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.entities.ai.EntityAISmavaCreeperSwell;
import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collection;

public class EntitySmavaCreeper extends EntityMob
{
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntitySmavaCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntitySmavaCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntitySmavaCreeper.class, DataSerializers.BOOLEAN);
	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private int explosionRadius = 6;
	private int droppedSkulls;

	public EntitySmavaCreeper(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.7F);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAISmavaCreeperSwell(this));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	public int getMaxFallHeight()
	{
		return this.getAttackTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
	}

	public void fall(float distance, float damageMultiplier)
	{
		super.fall(distance, damageMultiplier);
		this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + distance * 1.5F);

		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(STATE, -1);
		this.dataManager.register(POWERED, Boolean.FALSE);
		this.dataManager.register(IGNITED, Boolean.FALSE);
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		if (this.dataManager.get(POWERED))
		{
			compound.setBoolean("powered", true);
		}

		compound.setShort("Fuse", (short) this.fuseTime);
		compound.setByte("ExplosionRadius", (byte) this.explosionRadius);
		compound.setBoolean("ignited", this.hasIgnited());
	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.dataManager.set(POWERED, compound.getBoolean("powered"));

		if (compound.hasKey("Fuse", 99))
		{
			this.fuseTime = compound.getShort("Fuse");
		}

		if (compound.hasKey("ExplosionRadius", 99))
		{
			this.explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited"))
		{
			this.ignite();
		}
	}

	public void onUpdate()
	{
		if (this.isEntityAlive())
		{
			this.lastActiveTime = this.timeSinceIgnited;

			if (this.hasIgnited())
			{
				this.setCreeperState(1);
			}

			int i = this.getCreeperState();

			if (i > 0 && this.timeSinceIgnited == 0)
			{
				this.playSound(ModSoundEvents.ENTITY_SMAVACREEPER_FUSE, 1.0F, 1.0F);
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
		super.onUpdate();
	}

	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.ENTITY_SMAVACREEPER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.ENTITY_SMAVACREEPER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);

		if (this.world.getGameRules().getBoolean("doMobLoot"))
		{
			if (cause.getTrueSource() instanceof EntitySkeleton)
			{
				int i = Item.getIdFromItem(Items.RECORD_13);
				int j = Item.getIdFromItem(Items.RECORD_WAIT);
				int k = i + this.rand.nextInt(j - i + 1);
				this.dropItem(Item.getItemById(k), 1);
			} else if (cause.getTrueSource() instanceof mod.acgaming.spackenmobs.entities.EntitySmavaCreeper && cause.getTrueSource() != this && ((mod.acgaming.spackenmobs.entities.EntitySmavaCreeper) cause.getTrueSource()).getPowered() && ((mod.acgaming.spackenmobs.entities.EntitySmavaCreeper) cause.getTrueSource()).ableToCauseSkullDrop())
			{
				((mod.acgaming.spackenmobs.entities.EntitySmavaCreeper) cause.getTrueSource()).incrementDroppedSkulls();
				this.entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
			}
		}
	}

	public boolean attackEntityAsMob(Entity entityIn)
	{
		return true;
	}

	public boolean getPowered()
	{
		return this.dataManager.get(POWERED);
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float p_70831_1_)
	{
		return ((float) this.lastActiveTime + (float) (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float) (this.fuseTime - 2);
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return LootTableList.ENTITIES_CREEPER;
	}

	public int getCreeperState()
	{
		return this.dataManager.get(STATE);
	}

	public void setCreeperState(int state)
	{
		this.dataManager.set(STATE, state);
	}

	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		super.onStruckByLightning(lightningBolt);
		this.dataManager.set(POWERED, Boolean.TRUE);
	}

	protected boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == Items.FLINT_AND_STEEL)
		{
			this.world.playSound(player, this.posX, this.posY, this.posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
			player.swingArm(hand);

			if (!this.world.isRemote)
			{
				this.ignite();
				itemstack.damageItem(1, player);
				return true;
			}
		}

		return super.processInteract(player, hand);
	}

	private void explode()
	{
		if (!this.world.isRemote)
		{
			boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
			float f = this.getPowered() ? 2.0F : 1.0F;
			this.dead = true;
			this.world.playSound(null, getPosition(), ModSoundEvents.ENTITY_SMAVACREEPER_BLOW, getSoundCategory(), 5.0F, 1.0F);
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float) this.explosionRadius * f, flag);
			this.setDead();
			this.spawnLingeringCloud();
		}
	}

	private void spawnLingeringCloud()
	{
		Collection<PotionEffect> collection = this.getActivePotionEffects();

		if (!collection.isEmpty())
		{
			EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.posX, this.posY, this.posZ);
			entityareaeffectcloud.setRadius(2.5F);
			entityareaeffectcloud.setRadiusOnUse(-0.5F);
			entityareaeffectcloud.setWaitTime(10);
			entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
			entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float) entityareaeffectcloud.getDuration());

			for (PotionEffect potioneffect : collection)
			{
				entityareaeffectcloud.addEffect(new PotionEffect(potioneffect));
			}

			this.world.spawnEntity(entityareaeffectcloud);
		}
	}

	public boolean hasIgnited()
	{
		return this.dataManager.get(IGNITED);
	}

	public void ignite()
	{
		this.dataManager.set(IGNITED, Boolean.TRUE);
	}

	public boolean ableToCauseSkullDrop()
	{
		return this.droppedSkulls < 1 && this.world.getGameRules().getBoolean("doMobLoot");
	}

	public void incrementDroppedSkulls()
	{
		++this.droppedSkulls;
	}
}