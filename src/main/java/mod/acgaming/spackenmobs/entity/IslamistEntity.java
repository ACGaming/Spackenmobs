package mod.acgaming.spackenmobs.entity;

import java.util.Collection;

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

import mod.acgaming.spackenmobs.entity.ai.goal.IslamistSwellGoal;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

@OnlyIn(
    value = Dist.CLIENT,
    _interface = IChargeableMob.class
)
public class IslamistEntity extends MonsterEntity implements IChargeableMob
{
    private static final DataParameter<Integer> STATE = EntityDataManager.defineId(IslamistEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> POWERED = EntityDataManager.defineId(IslamistEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.defineId(IslamistEntity.class, DataSerializers.BOOLEAN);

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 6;
    private int droppedSkulls;

    public IslamistEntity(EntityType<? extends IslamistEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier)
    {
        boolean flag = super.causeFallDamage(distance, damageMultiplier);
        this.timeSinceIgnited = (int) ((float) this.timeSinceIgnited + distance * 1.5F);
        if (this.timeSinceIgnited > this.fuseTime - 5)
        {
            this.timeSinceIgnited = this.fuseTime - 5;
        }

        return flag;
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }

    public boolean isPowered()
    {
        return this.entityData.get(POWERED);
    }

    @OnlyIn(Dist.CLIENT)
    public float getCreeperFlashIntensity(float partialTicks)
    {
        return MathHelper.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
    }

    public int getCreeperState()
    {
        return this.entityData.get(STATE);
    }

    public void setCreeperState(int state)
    {
        this.entityData.set(STATE, state);
    }

    public void thunderHit(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_)
    {
        super.thunderHit(p_241841_1_, p_241841_2_);
        this.entityData.set(POWERED, true);
    }

    public boolean hasIgnited()
    {
        return this.entityData.get(IGNITED);
    }

    public void ignite()
    {
        this.entityData.set(IGNITED, true);
    }

    public boolean ableToCauseSkullDrop()
    {
        return this.isPowered() && this.droppedSkulls < 1;
    }

    public void incrementDroppedSkulls()
    {
        ++this.droppedSkulls;
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new IslamistSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.8F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, -1);
        this.entityData.define(POWERED, false);
        this.entityData.define(IGNITED, false);
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
                this.playSound(SpackenmobsRegistry.ENTITY_ISLAMIST_FUSE.get(), 1.0F, 0.5F);
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

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_ISLAMIST_AMBIENT.get();
    }

    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        if (this.entityData.get(POWERED))
        {
            compound.putBoolean("powered", true);
        }

        compound.putShort("Fuse", (short) this.fuseTime);
        compound.putByte("ExplosionRadius", (byte) this.explosionRadius);
        compound.putBoolean("ignited", this.hasIgnited());
    }

    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.entityData.set(POWERED, compound.getBoolean("powered"));
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

    public int getMaxFallDistance()
    {
        return this.getTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn)
    {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        Entity entity = source.getEntity();
        if (entity != this && entity instanceof IslamistEntity)
        {
            IslamistEntity creeperentity = (IslamistEntity) entity;
            if (creeperentity.ableToCauseSkullDrop())
            {
                creeperentity.incrementDroppedSkulls();
                this.spawnAtLocation(Items.CREEPER_HEAD);
            }
        }
    }

    protected ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_)
    {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.FLINT_AND_STEEL)
        {
            this.level.playSound(p_230254_1_, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level.isClientSide)
            {
                this.ignite();
                itemstack.hurtAndBreak(1, p_230254_1_, (player) ->
                    player.broadcastBreakEvent(p_230254_2_));
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else
        {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public boolean doHurtTarget(Entity entityIn)
    {
        return true;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_ISLAMIST_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_ISLAMIST_AMBIENT.get();
    }

    private void explode()
    {
        if (!this.level.isClientSide)
        {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.playSound(SpackenmobsRegistry.ENTITY_ISLAMIST_BLOW.get(), 1.0F, 1.0F);
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
            this.remove();
            this.spawnLingeringCloud();
        }

    }

    private void spawnLingeringCloud()
    {
        Collection<EffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty())
        {
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloudentity.setRadius(2.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

            for (EffectInstance effectinstance : collection)
            {
                areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
            }

            this.level.addFreshEntity(areaeffectcloudentity);
        }

    }
}