package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMarcellDAvis extends EntityZombie
{
	public EntityMarcellDAvis(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.95F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.ENTITY_MARCELLDAVIS_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.ENTITY_MARCELLDAVIS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ENTITY_MARCELLDAVIS_DEATH;
	}
}