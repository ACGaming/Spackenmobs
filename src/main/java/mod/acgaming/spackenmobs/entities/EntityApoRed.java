package mod.acgaming.spackenmobs.entities;

import javax.annotation.Nullable;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityApoRed extends EntitySkeleton
{
	public static void registerFixesApoRed(DataFixer fixer)
	{
		EntityLiving.registerFixesMob(fixer, EntityApoRed.class);
	}

	public EntityApoRed(World worldIn)
	{
		super(worldIn);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.ENTITY_APORED_AMBIENT;
	}

	@Override
	protected EntityArrow getArrow(float p_190726_1_)
	{
		ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

		if (itemstack.getItem() == Items.SPECTRAL_ARROW)
		{
			EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(this.world, this);
			entityspectralarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
			return entityspectralarrow;
		}
		else
		{
			EntityArrow entityarrow = super.getArrow(p_190726_1_);

			if (itemstack.getItem() == Items.TIPPED_ARROW && entityarrow instanceof EntityTippedArrow)
			{
				((EntityTippedArrow) entityarrow).setPotionEffect(itemstack);
			}

			return entityarrow;
		}
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ENTITY_APORED_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.ENTITY_APORED_HURT;
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable()
	{
		return LootTableList.ENTITIES_SKELETON;
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);

		if (cause.getTrueSource() instanceof EntityCreeper)
		{
			EntityCreeper entitycreeper = (EntityCreeper) cause.getTrueSource();

			if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop())
			{
				entitycreeper.incrementDroppedSkulls();
				this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
			}
		}
	}
}