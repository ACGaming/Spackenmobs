package mod.acgaming.spackenmobs.entities;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;

public class EntityMrBean extends EntityZombie
{
    public EntityMrBean(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.ENTITY_MRBEAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSoundEvents.ENTITY_MRBEAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSoundEvents.ENTITY_MRBEAN_DEATH;
    }
}