package mod.acgaming.spackenmobs.entities;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityWolfMZTE extends EntityWolf {
    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityWolf.class,
	    DataSerializers.FLOAT);
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityWolf.class,
	    DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.<Integer>createKey(EntityWolf.class,
	    DataSerializers.VARINT);
    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isWet;
    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntityWolfMZTE(World worldIn) {
	super(worldIn);
	this.setSize(0.6F, 0.85F);
	this.setTamed(false);
    }

    @Override
    public EntityWolfMZTE createChild(EntityAgeable ageable) {
	EntityWolfMZTE entitywolfmzte = new EntityWolfMZTE(this.world);
	UUID uuid = this.getOwnerId();

	if (uuid != null) {
	    entitywolfmzte.setOwnerId(uuid);
	    entitywolfmzte.setTamed(true);
	}

	return entitywolfmzte;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
	if (otherAnimal == this) {
	    return false;
	} else if (!this.isTamed()) {
	    return false;
	} else if (!(otherAnimal instanceof EntityWolfMZTE)) {
	    return false;
	} else {
	    EntityWolfMZTE entitywolfmzte = (EntityWolfMZTE) otherAnimal;

	    if (!entitywolfmzte.isTamed()) {
		return false;
	    } else if (entitywolfmzte.isSitting()) {
		return false;
	    } else {
		return this.isInLove() && entitywolfmzte.isInLove();
	    }
	}
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
	if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
	    if (target instanceof EntityWolfMZTE) {
		EntityWolfMZTE entitywolfmzte = (EntityWolfMZTE) target;

		if (entitywolfmzte.isTamed() && entitywolfmzte.getOwner() == owner) {
		    return false;
		}
	    }

	    if (target instanceof EntityPlayer && owner instanceof EntityPlayer
		    && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
		return false;
	    } else {
		return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
	    }
	} else {
	    return false;
	}
    }

    class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
	private final EntityWolfMZTE wolf;

	public AIAvoidEntity(EntityWolfMZTE wolfIn, Class<T> p_i47251_3_, float p_i47251_4_, double p_i47251_5_,
		double p_i47251_7_) {
	    super(wolfIn, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
	    this.wolf = wolfIn;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
	    if (super.shouldExecute() && this.closestLivingEntity instanceof EntityLlama) {
		return !this.wolf.isTamed() && this.avoidLlama((EntityLlama) this.closestLivingEntity);
	    } else {
		return false;
	    }
	}

	private boolean avoidLlama(EntityLlama p_190854_1_) {
	    return p_190854_1_.getStrength() >= EntityWolfMZTE.this.rand.nextInt(5);
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
	    EntityWolfMZTE.this.setAttackTarget((EntityLivingBase) null);
	    super.startExecuting();
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	@Override
	public void updateTask() {
	    EntityWolfMZTE.this.setAttackTarget((EntityLivingBase) null);
	    super.updateTask();
	}
    }
}