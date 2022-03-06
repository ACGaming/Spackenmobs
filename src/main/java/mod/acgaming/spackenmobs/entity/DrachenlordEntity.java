package mod.acgaming.spackenmobs.entity;

import java.util.UUID;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class DrachenlordEntity extends ZombieEntity
{
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    private int angerLevel;
    private int randomSoundDelay;
    private UUID angerTargetUUID;

    public DrachenlordEntity(EntityType<? extends DrachenlordEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.fireImmune();
    }

    public void setLastHurtByMob(@Nullable LivingEntity livingEntity)
    {
        super.setLastHurtByMob(livingEntity);

        if (livingEntity != null)
        {
            this.angerTargetUUID = livingEntity.getUUID();
        }
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }

    public void becomeAngryAt(Entity entity)
    {
        this.angerLevel = 400 + this.random.nextInt(400);
        this.randomSoundDelay = this.random.nextInt(40);

        if (entity instanceof LivingEntity)
        {
            this.setLastHurtByMob((LivingEntity) entity);
        }
    }

    public boolean isAngry()
    {
        return this.angerLevel > 0;
    }

    public void tick()
    {
        if (this.isAngry())
        {
            --this.angerLevel;
        }
        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
        {
            this.playSound(SpackenmobsRegistry.ENTITY_DRACHENLORD_ANGRY.get(), 1.0F, 1.0F);
        }
        if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getLastHurtByMob() == null)
        {
            PlayerEntity entityplayer = this.level.getPlayerByUUID(this.angerTargetUUID);
            this.setLastHurtByMob(entityplayer);
            this.lastHurtByPlayer = entityplayer;
            this.lastHurtByPlayerTime = this.getLastHurtByMobTimestamp();
        }
        super.tick();
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

            if (entity instanceof PlayerEntity)
            {
                this.becomeAngryAt(entity);
            }

            return super.hurt(source, amount);
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_DRACHENLORD_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_DRACHENLORD_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_DRACHENLORD_DEATH.get();
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
    {
        super.populateDefaultEquipmentSlots(difficulty);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
    }
}