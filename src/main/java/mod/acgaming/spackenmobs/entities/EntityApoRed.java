package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityApoRed extends EntitySkeleton {
    public EntityApoRed(World worldIn) {
	super(worldIn);
    }

    @Override
    protected SoundEvent getAmbientSound() {
	return ModSoundEvents.ENTITY_APORED_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	return ModSoundEvents.ENTITY_APORED_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
	return ModSoundEvents.ENTITY_APORED_DEATH;
    }
}