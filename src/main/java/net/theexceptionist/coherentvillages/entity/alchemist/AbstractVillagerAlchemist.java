package net.theexceptionist.coherentvillages.entity.alchemist;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public abstract class AbstractVillagerAlchemist extends AbstractVillagerSoldier implements IRangedAttackMob{
	protected static final UUID MODIFIER_UUID = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    protected static final AttributeModifier MODIFIER = (new AttributeModifier(MODIFIER_UUID, "Drinking speed penalty", -0.25D, 0)).setSaved(false);
    protected static final DataParameter<Boolean> IS_AGGRESSIVE = EntityDataManager.<Boolean>createKey(EntityVillagerAlchemist.class, DataSerializers.BOOLEAN);
   
    protected int attackTimer;
    protected int coolDown = 0;
    protected EntityLivingBase client;
    
	public AbstractVillagerAlchemist(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	public AbstractVillagerAlchemist(World worldIn, boolean hostile, boolean creeperHunter, boolean undeadHunter, boolean livingHunter) {
		super(worldIn, hostile, creeperHunter, undeadHunter, livingHunter);
	}
	

	protected void initEntityAI()
    {
		super.initEntityAI();
		//this.areAdditionalTasksSet = true;
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        
		 for(Object task : this.tasks.taskEntries.toArray())
			{
				 EntityAIBase ai = ((EntityAITaskEntry) task).action;
				 if(ai instanceof EntityAIAttackMelee)
					 this.tasks.removeTask(ai);	
				 //System.out.println("Removed");
			}
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
    }
	
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.setProfession(1);
        
        //this.setHeldItem(EnumHand.MAIN_HAND, null);
        
//        EntityHorse horse = new EntityHorse(this.world);
//        setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
    }
	
	@Override
	 protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		 
	    }

	@Override
	public abstract void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor);


    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(IS_AGGRESSIVE, Boolean.valueOf(false));
    }
/*
     * Set whether this witch is aggressive at an entity.
     */
    public void setAggressive(boolean aggressive)
    {
        this.getDataManager().set(IS_AGGRESSIVE, Boolean.valueOf(aggressive));
    }

    public boolean isDrinkingPotion()
    {
        return ((Boolean)this.getDataManager().get(IS_AGGRESSIVE)).booleanValue();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 15)
        {
            for (int i = 0; i < this.rand.nextInt(35) + 10; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.rand.nextGaussian() * 0.12999999523162842D, this.getEntityBoundingBox().maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    /**
     * Reduces damage, depending on potions
     */
    protected float applyPotionDamageCalculations(DamageSource source, float damage)
    {
        damage = super.applyPotionDamageCalculations(source, damage);

        if (source.getTrueSource() == this)
        {
            damage = 0.0F;
        }

        if (source.isMagicDamage())
        {
            damage = (float)((double)damage * 0.15D);
        }

        return damage;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_WITCH;
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
   /* public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        if (!this.isDrinkingPotion())
        {
            double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
            double d1 = target.posX + target.motionX - this.posX;
            double d2 = d0 - this.posY;
            double d3 = target.posZ + target.motionZ - this.posZ;
            float f = MathHelper.sqrt(d1 * d1 + d3 * d3);
            PotionType potiontype = PotionTypes.HARMING;
            
            if(world.rand.nextInt(100) < 10)
            	potiontype = PotionTypes.STRONG_HARMING;

           if (f >= 8.0F && !target.isPotionActive(MobEffects.SLOWNESS))
            {
                potiontype = PotionTypes.SLOWNESS;
            }/*else if(target instanceof EntityPigZombie){
            	 potiontype = PotionTypes.HARMING;
            }
            else if (target. >= 8.0F && !target.isPotionActive(MobEffects.POISON))
            {
                potiontype = PotionTypes.REGENERATION;
            }
            else if (target.isEntityUndead())
            {
                potiontype = PotionTypes.HEALING;
                if(world.rand.nextInt(100) < 10)
                	potiontype = PotionTypes.STRONG_HEALING;
            }else if (target.getHealth() >= 8.0F && !target.isPotionActive(MobEffects.POISON) && !(target instanceof EntitySpider))
            {
                potiontype = PotionTypes.POISON;
                if(world.rand.nextInt(100) < 10)
                	potiontype = PotionTypes.STRONG_POISON;
            }
           

            EntityPotion entitypotion = new EntityPotion(this.world, this, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potiontype));
            entitypotion.rotationPitch -= -20.0F;
            entitypotion.shoot(d1, d2 + (double)(f * 0.2F), d3, 0.75F, 8.0F);
            this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
            this.world.spawnEntity(entitypotion);
        }
    }*/
    
    @Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        //super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
	    }
    
    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

    public float getEyeHeight()
    {
        return 1.62F;
    }

	public void healEntityWithRangedAttack(EntityLivingBase target, float lvt_5_1_) {
		// TODO Auto-generated method stub
		
	}

	public void setClient(EntityLivingBase target) {
		this.client = target;
	}

	public EntityLivingBase getClient()
	{
		return client;
	}
}
