package mod.acgaming.spackenmobs.entity;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class DrachenlordEntity extends PiglinEntity
{
	public DrachenlordEntity(EntityType<? extends DrachenlordEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 16.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.35F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
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
}