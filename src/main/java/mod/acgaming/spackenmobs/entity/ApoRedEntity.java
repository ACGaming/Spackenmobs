package mod.acgaming.spackenmobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

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

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn)
    {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        Entity entity = source.getEntity();
        if (entity instanceof SmavaCreeperEntity)
        {
            SmavaCreeperEntity smavacreeperentity = (SmavaCreeperEntity) entity;
            if (smavacreeperentity.ableToCauseSkullDrop())
            {
                smavacreeperentity.incrementDroppedSkulls();
                this.spawnAtLocation(Items.SKELETON_SKULL);
            }
        }
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
        return SoundEvents.ZOMBIE_STEP;
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }
}