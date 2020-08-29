package mod.acgaming.spackenmobs.entities;

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

import java.util.UUID;

public class EntityWolfMZTE extends EntityWolf {
    public EntityWolfMZTE(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }

    @Override
    public EntityWolfMZTE createChild(EntityAgeable ageable) {
        EntityWolfMZTE entitywolfmzte = new EntityWolfMZTE(this.world);
        UUID uuid = this.getOwnerId();

        if(uuid != null) {
            entitywolfmzte.setOwnerId(uuid);
            entitywolfmzte.setTamed(true);
        }

        return entitywolfmzte;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if(otherAnimal == this) {
            return false;
        }else if(!this.isTamed()) {
            return false;
        }else if(!(otherAnimal instanceof EntityWolfMZTE)) {
            return false;
        }else {
            EntityWolfMZTE entitywolfmzte = (EntityWolfMZTE)otherAnimal;

            if(!entitywolfmzte.isTamed()) {
                return false;
            }else if(entitywolfmzte.isSitting()) {
                return false;
            }else {
                return this.isInLove() && entitywolfmzte.isInLove();
            }
        }
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if(!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
            if(target instanceof EntityWolfMZTE) {
                EntityWolfMZTE entitywolfmzte = (EntityWolfMZTE)target;

                if(entitywolfmzte.isTamed() && entitywolfmzte.getOwner() == owner) {
                    return false;
                }
            }

            if(target instanceof EntityPlayer && owner instanceof EntityPlayer
                && !((EntityPlayer)owner).canAttackPlayer((EntityPlayer)target)) {
                return false;
            }else {
                return !(target instanceof AbstractHorse) || !((AbstractHorse)target).isTame();
            }
        }else {
            return false;
        }
    }
}