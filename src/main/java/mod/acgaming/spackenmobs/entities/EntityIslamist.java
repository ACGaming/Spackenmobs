package mod.acgaming.spackenmobs.entities;

import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityIslamist extends EntityCreeper {
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 6;

    public EntityIslamist(World worldIn) {
	super(worldIn);
	this.setSize(0.6F, 1.7F);
    }

    @Override
    public void onUpdate() {
	if (this.isEntityAlive()) {
	    this.lastActiveTime = this.timeSinceIgnited;

	    if (this.hasIgnited()) {
		this.setCreeperState(1);
	    }

	    int i = this.getCreeperState();

	    if (i > 0 && this.timeSinceIgnited == 0) {
		this.playSound(ModSoundEvents.ENTITY_ISLAMIST_FUSE, 1.0F, 0.5F);
	    }

	    this.timeSinceIgnited += i;

	    if (this.timeSinceIgnited < 0) {
		this.timeSinceIgnited = 0;
	    }

	    if (this.timeSinceIgnited >= this.fuseTime) {
		this.timeSinceIgnited = this.fuseTime;
		this.explode();
	    }
	}

	super.onUpdate();
    }

    private void explode() {
	if (!this.world.isRemote) {
	    boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
	    float f = this.getPowered() ? 2.0F : 1.0F;
	    this.dead = true;
	    this.world.playSound(null, getPosition(), ModSoundEvents.ENTITY_ISLAMIST_BLOW, getSoundCategory(), 1.0F,
		    1.0F);
	    this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * f, flag);
	    this.setDead();
	}
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	return ModSoundEvents.ENTITY_ISLAMIST_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
	return ModSoundEvents.ENTITY_ISLAMIST_AMBIENT;
    }
}