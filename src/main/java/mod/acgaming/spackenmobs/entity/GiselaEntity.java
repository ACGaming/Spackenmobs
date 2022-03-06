package mod.acgaming.spackenmobs.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class GiselaEntity extends AnimalEntity
{
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 1.25F);
    }

    public GiselaEntity(EntityType<? extends GiselaEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public int getAmbientSoundInterval()
    {
        return 20;
    }

    public GiselaEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {
        return SpackenmobsRegistry.GISELA.get().create(p_241840_1_);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_GISELA_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_GISELA_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_GISELA_HURT.get();
    }

    protected float getSoundVolume()
    {
        return 0.6F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
    {
        return this.isBaby() ? sizeIn.height * 0.95F : 1.3F;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }
}