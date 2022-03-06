package mod.acgaming.spackenmobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class MarcellDAvisEntity extends ZombieEntity
{
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.23F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    public MarcellDAvisEntity(EntityType<? extends MarcellDAvisEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_MARCELLDAVIS_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_MARCELLDAVIS_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_MARCELLDAVIS_DEATH.get();
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }
}