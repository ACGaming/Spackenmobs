package mod.acgaming.spackenmobs.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;

public class FriedrichLiechtensteinEntity extends AnimalEntity
{
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    public FriedrichLiechtensteinEntity(EntityType<? extends FriedrichLiechtensteinEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()));
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.GLASS_BOTTLE && !this.isBaby())
        {
            player.playSound(SpackenmobsRegistry.ENTITY_FRIEDRICH_DEATH.get(), 1.0F, 1.0F);
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
            itemstack.shrink(1);
            if (itemstack.isEmpty())
            {
                player.setItemInHand(hand, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()));
            }
            else if (!player.inventory.add(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get())))
            {
                player.drop(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE_DRINK.get()), false);
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else if (itemstack.getItem() == Items.PAPER && !this.isBaby())
        {
            player.playSound(SpackenmobsRegistry.ENTITY_FRIEDRICH_AMBIENT.get(), 1.0F, 1.0F);
            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
            itemstack.shrink(1);
            if (itemstack.isEmpty())
            {
                player.setItemInHand(hand, new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()));
            }
            else if (!player.inventory.add(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get())))
            {
                player.drop(new ItemStack(SpackenmobsRegistry.AHOJ_BRAUSE.get()), false);
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else
        {
            return super.mobInteract(player, hand);
        }
    }

    public FriedrichLiechtensteinEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {
        return SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get().create(p_241840_1_);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SUGAR), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    protected SoundEvent getAmbientSound()
    {
        return SpackenmobsRegistry.ENTITY_FRIEDRICH_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SpackenmobsRegistry.ENTITY_FRIEDRICH_HURT.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SpackenmobsRegistry.ENTITY_FRIEDRICH_DEATH.get();
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