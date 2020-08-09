package mod.acgaming.spackenmobs.entities;
import mod.acgaming.spackenmobs.Spackenmobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityApoRed extends EntitySkeleton
{  
    public EntityApoRed(World worldIn)
    {
        super(worldIn);
    }
	
    protected SoundEvent getAmbientSound()
    {
        return Spackenmobs.ENTITY_APORED_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return Spackenmobs.ENTITY_APORED_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return Spackenmobs.ENTITY_APORED_DEATH;
    }
}