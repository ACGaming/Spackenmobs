package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMrBean extends EntityZombie
{
	public EntityMrBean(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.95F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.ENTITY_MRBEAN_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ENTITY_MRBEAN_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.ENTITY_MRBEAN_HURT;
	}
}