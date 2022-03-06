package mod.acgaming.spackenmobs.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class AbstractApoRedEntity extends MonsterEntity implements IRangedAttackMob
{
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    private final RangedBowAttackGoal<AbstractApoRedEntity> aiArrowAttack = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal aiAttackOnCollide = new MeleeAttackGoal(this, 1.2D, false)
    {

        public void start()
        {
            super.start();
            AbstractApoRedEntity.this.setAggressive(true);
        }

        public void stop()
        {
            super.stop();
            AbstractApoRedEntity.this.setAggressive(false);
        }
    };

    protected AbstractApoRedEntity(EntityType<? extends AbstractApoRedEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setCombatTask();
    }

    public CreatureAttribute getMobType()
    {
        return CreatureAttribute.UNDEAD;
    }

    public void rideTick()
    {
        super.rideTick();
        if (this.getVehicle() instanceof CreatureEntity)
        {
            CreatureEntity creatureentity = (CreatureEntity) this.getVehicle();
            this.yBodyRot = creatureentity.yBodyRot;
        }

    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
    {
        return 1.74F;
    }

    public void aiStep()
    {
        boolean flag = this.isSunBurnTick();
        if (flag)
        {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
            if (!itemstack.isEmpty())
            {
                if (itemstack.isDamageableItem())
                {
                    itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                    if (itemstack.getDamageValue() >= itemstack.getMaxDamage())
                    {
                        this.broadcastBreakEvent(EquipmentSlotType.HEAD);
                        this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                    }
                }

                flag = false;
            }

            if (flag)
            {
                this.setSecondsOnFire(8);
            }
        }

        super.aiStep();
    }

    public void setCombatTask()
    {
        if (this.level != null && !this.level.isClientSide)
        {
            this.goalSelector.removeGoal(this.aiAttackOnCollide);
            this.goalSelector.removeGoal(this.aiArrowAttack);
            ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW));
            if (itemstack.getItem() instanceof net.minecraft.item.BowItem)
            {
                int i = 20;
                if (this.level.getDifficulty() != Difficulty.HARD)
                {
                    i = 40;
                }

                this.aiArrowAttack.setMinAttackInterval(i);
                this.goalSelector.addGoal(4, this.aiArrowAttack);
            }
            else
            {
                this.goalSelector.addGoal(4, this.aiAttackOnCollide);
            }

        }
    }

    public void performRangedAttack(LivingEntity target, float distanceFactor)
    {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrowEntity abstractarrowentity = this.fireArrow(itemstack, distanceFactor);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
            abstractarrowentity = ((net.minecraft.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.3333333333333333D) - abstractarrowentity.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrowentity);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    public boolean canFireProjectileWeapon(ShootableItem p_230280_1_)
    {
        return p_230280_1_ == Items.BOW;
    }

    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.setCombatTask();
    }

    public void setItemSlot(EquipmentSlotType slotIn, ItemStack stack)
    {
        super.setItemSlot(slotIn, stack);
        if (!this.level.isClientSide)
        {
            this.setCombatTask();
        }

    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
    {
        super.populateDefaultEquipmentSlots(difficulty);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.populateDefaultEquipmentSlots(difficultyIn);
        this.populateDefaultEquipmentEnchantments(difficultyIn);
        this.setCombatTask();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * difficultyIn.getSpecialMultiplier());
        if (this.getItemBySlot(EquipmentSlotType.HEAD).isEmpty())
        {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F)
            {
                this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        }

        return spawnDataIn;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public double getMyRidingOffset()
    {
        return -0.6D;
    }

    protected abstract SoundEvent getStepSound();

    protected AbstractArrowEntity fireArrow(ItemStack arrowStack, float distanceFactor)
    {
        return ProjectileHelper.getMobArrow(this, arrowStack, distanceFactor);
    }
}