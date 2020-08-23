package mod.acgaming.spackenmobs.entities;

import java.util.Set;

import com.google.common.collect.Sets;

import mod.acgaming.spackenmobs.misc.ModItems;
import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityJens extends EntityAnimal {
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(ModItems.RAM);
    private static final Set<Item> FISH_ITEMS = Sets.newHashSet(Items.FISH);

    public boolean yummy_in_tummy = false;
    public int time_until_surstroemming = 0;

    Minecraft MINECRAFT = Minecraft.getMinecraft();

    public EntityJens(World worldIn) {
	super(worldIn);
	setSize(0.6F, 2.2F);
    }

    @Override
    protected void initEntityAI() {
	this.tasks.addTask(0, new EntityAISwimming(this));
	this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
	this.tasks.addTask(2, new EntityAIEatDroppedFish(this));
	this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
	this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
	this.tasks.addTask(4, new EntityAITempt(this, 1.2D, ModItems.RAM_ON_A_STICK, false));
	this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
	this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
	this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
	return TEMPTATION_ITEMS.contains(stack.getItem());
    }

    public boolean isFishItem(ItemStack stack) {
	return FISH_ITEMS.contains(stack.getItem());
    }

    @Override
    public boolean canBeSteered() {
	Entity entity = this.getControllingPassenger();

	if (!(entity instanceof EntityPlayer)) {
	    return false;
	} else {
	    EntityPlayer entityplayer = (EntityPlayer) entity;
	    return entityplayer.getHeldItemMainhand().getItem() == ModItems.RAM_ON_A_STICK
		    || entityplayer.getHeldItemOffhand().getItem() == ModItems.RAM_ON_A_STICK;
	}
    }

    @Override
    public EntityJens createChild(EntityAgeable ageable) {
	return new EntityJens(this.world);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
	ItemStack itemstack = player.getHeldItem(hand);

	if (itemstack.getItem() == Items.FISH && !player.capabilities.isCreativeMode && !this.isChild()
		&& this.yummy_in_tummy == false) {
	    itemstack.shrink(1);
	    digestFish();
	    return true;
	} else {
	    return super.processInteract(player, hand);
	}
    }

    @Override
    public void onLivingUpdate() {
	super.onLivingUpdate();

	if (!this.world.isRemote && this.yummy_in_tummy == true && this.time_until_surstroemming > 0) {
	    this.time_until_surstroemming--;
	}

	if (!this.world.isRemote && this.yummy_in_tummy == true && this.time_until_surstroemming <= 0) {
	    for (int i = 0; i < 7; ++i) {
		double d0 = this.rand.nextGaussian() * 0.02D;
		double d1 = this.rand.nextGaussian() * 0.02D;
		double d2 = this.rand.nextGaussian() * 0.02D;
		MINECRAFT.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,
			this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
			this.posY + 0.5D + this.rand.nextFloat() * this.height,
			this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
	    }
	    this.playSound(ModSoundEvents.ENTITY_JENS_POOP, 1.0F,
		    (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
	    this.dropItem(ModItems.SURSTROEMMING, 1);
	    this.yummy_in_tummy = false;
	    this.time_until_surstroemming = 0;
	}
    }

    public void digestFish() {
	this.playSound(ModSoundEvents.ENTITY_JENS_EAT, 1.0F, 1.0F);

	this.yummy_in_tummy = true;
	this.time_until_surstroemming = 200;

	for (int i = 0; i < 7; ++i) {
	    double d0 = this.rand.nextGaussian() * 0.02D;
	    double d1 = this.rand.nextGaussian() * 0.02D;
	    double d2 = this.rand.nextGaussian() * 0.02D;
	    MINECRAFT.world.spawnParticle(EnumParticleTypes.HEART,
		    this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
		    this.posY + 0.5D + this.rand.nextFloat() * this.height,
		    this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
	}
    }

    @Override
    protected SoundEvent getAmbientSound() {
	return ModSoundEvents.ENTITY_JENS_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	return ModSoundEvents.ENTITY_JENS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
	return ModSoundEvents.ENTITY_JENS_DEATH;
    }
}