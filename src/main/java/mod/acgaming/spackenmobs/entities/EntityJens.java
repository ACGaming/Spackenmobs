package mod.acgaming.spackenmobs.entities;
import java.util.Set;

import com.google.common.collect.Sets;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityJens extends EntityPig
{
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(ModItems.RAM);
	private static final DataParameter<Integer> Cooldown = EntityDataManager.createKey(EntityJens.class, DataSerializers.VARINT);
	private boolean yummy_in_tummy = false;
	private int timeUntilSurstroemming = 0;
	
    public EntityJens(World worldIn)
    {
        super(worldIn);
        setSize(0.6F, 2.2F);
    }
    
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, ModItems.RAM_ON_A_STICK, false));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    
    public boolean canBeSteered()
    {
        Entity entity = this.getControllingPassenger();

        if (!(entity instanceof EntityPlayer))
        {
            return false;
        }
        else
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            return entityplayer.getHeldItemMainhand().getItem() == ModItems.RAM_ON_A_STICK || entityplayer.getHeldItemOffhand().getItem() == ModItems.RAM_ON_A_STICK;
        }
    }
    
    public EntityJens createChild(EntityAgeable ageable)
    {
        return new EntityJens(this.world);
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        if (itemstack.getItem() == Items.FISH && !this.isChild() && this.yummy_in_tummy == false)
        {
            player.playSound(Spackenmobs.ENTITY_JENS_EAT, 1.0F, 1.0F);
            itemstack.shrink(1);
            this.yummy_in_tummy = true;
            this.timeUntilSurstroemming = 100;
            
            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }
            return true;
        }
        else
        {
    		return this.processInteract(player, hand);
        }
    }
    
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (!this.world.isRemote && this.yummy_in_tummy == true && this.timeUntilSurstroemming > 0)
        {
        	this.timeUntilSurstroemming--;
        }
        
        if (!this.world.isRemote && this.yummy_in_tummy == true && this.timeUntilSurstroemming <= 0)
        {
            this.playSound(Spackenmobs.ENTITY_JENS_POOP, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(ModItems.SURSTROEMMING, 1);
            this.yummy_in_tummy = false;
            this.timeUntilSurstroemming = 0;
        }
    }
    
    protected SoundEvent getAmbientSound()
    {
        return Spackenmobs.ENTITY_JENS_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return Spackenmobs.ENTITY_JENS_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return Spackenmobs.ENTITY_JENS_DEATH;
    }
}