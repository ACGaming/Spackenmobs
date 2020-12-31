package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ApoRedEntity extends AbstractApoRedEntity
{
	public ApoRedEntity(EntityType<? extends ApoRedEntity> p_i50194_1_, World p_i50194_2_)
	{
		super(p_i50194_1_, p_i50194_2_);
	}

	protected SoundEvent getAmbientSound()
	{
		return SpackenmobsRegistry.ENTITY_APORED_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SpackenmobsRegistry.ENTITY_APORED_HURT.get();
	}

	protected SoundEvent getDeathSound()
	{
		return SpackenmobsRegistry.ENTITY_APORED_DEATH.get();
	}

	protected SoundEvent getStepSound()
	{
		return SoundEvents.ENTITY_ZOMBIE_STEP;
	}

	protected float getSoundVolume()
	{
		return 0.5F;
	}

	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn)
	{
		super.dropSpecialItems(source, looting, recentlyHitIn);
		Entity entity = source.getTrueSource();
		if (entity instanceof SmavaCreeperEntity)
		{
			SmavaCreeperEntity smavacreeperentity = (SmavaCreeperEntity) entity;
			if (smavacreeperentity.ableToCauseSkullDrop())
			{
				smavacreeperentity.incrementDroppedSkulls();
				this.entityDropItem(Items.SKELETON_SKULL);
			}
		}
	}
}