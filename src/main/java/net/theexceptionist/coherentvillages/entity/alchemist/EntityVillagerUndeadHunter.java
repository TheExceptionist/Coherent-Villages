package net.theexceptionist.coherentvillages.entity.alchemist;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.EntityVillagerHorse;

public class EntityVillagerUndeadHunter extends AbstractVillagerAlchemist {

	public EntityVillagerUndeadHunter(World worldIn) {
		super(worldIn);
		this.undeadHunter = true;
		// TODO Auto-generated constructor stub
	}
	
	public EntityVillagerUndeadHunter(World worldIn, boolean hostile, boolean creeperHunter, boolean undeadHunter, boolean livingHunter) {
		super(worldIn, hostile, creeperHunter, undeadHunter, livingHunter);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		 if (!this.isDrinkingPotion())
	        {
	            double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
	            double d1 = target.posX + target.motionX - this.posX;
	            double d2 = d0 - this.posY;
	            double d3 = target.posZ + target.motionZ - this.posZ;
	            float f = MathHelper.sqrt(d1 * d1 + d3 * d3);
	            PotionType potiontype = null;
	            
	            if(target.isEntityUndead()){
		            potiontype = PotionTypes.HEALING;
		            
		            if(world.rand.nextInt(100) < 10)
		            	potiontype = PotionTypes.STRONG_HEALING;
		            
		            if(target.isEntityUndead() && this.world.rand.nextInt(100) < 50 && !target.isPotionActive(MobEffects.STRENGTH))
		            {
		            	potiontype = PotionTypes.STRENGTH;
		                if(world.rand.nextInt(100) < 10)
		                	potiontype = PotionTypes.STRONG_STRENGTH;
		            }
	            }
	            else if (!target.isEntityUndead() && !target.isPotionActive(MobEffects.POISON))
	            {
	                potiontype = PotionTypes.POISON;
	            }
	            
	            if(potiontype != null){
		            EntityPotion entitypotion = new EntityPotion(this.world, this, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potiontype));
		            entitypotion.rotationPitch -= -20.0F;
		            entitypotion.shoot(d1, d2 + (double)(f * 0.2F), d3, 0.75F, 8.0F);
		            this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
		            this.world.spawnEntity(entitypotion);
	            }
	        }
	}
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		 this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 60, 10.0F));
		//this.areAdditionalTasksSet = true;
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
    }
	
	public void onLivingUpdate()
    {
        if (!this.world.isRemote)
        {
        	//this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.POTIONITEM));
            if (this.isDrinkingPotion())
            {
                if (this.attackTimer-- <= 0)
                {
                    this.setAggressive(false);
                    ItemStack itemstack = this.getHeldItemMainhand();
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);

                    if (itemstack.getItem() == Items.POTIONITEM)
                    {
                        List<PotionEffect> list = PotionUtils.getEffectsFromStack(itemstack);

                        if (list != null)
                        {
                            for (PotionEffect potioneffect : list)
                            {
                                this.addPotionEffect(new PotionEffect(potioneffect));
                            }
                        }
                    }

                    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(MODIFIER);
                }
            }
            else
            {
                PotionType potiontype = null;

                if(this.rand.nextFloat() < 0.15F && !this.isPotionActive(MobEffects.INVISIBILITY))
                {
                	potiontype =  PotionTypes.INVISIBILITY;
                }else
               /* if (this.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(Material.WATER) && !this.isPotionActive(MobEffects.WATER_BREATHING))
                {
                    potiontype = PotionTypes.WATER_BREATHING;
                }
                else if (this.rand.nextFloat() < 0.15F && (this.isBurning() || this.getLastDamageSource() != null && this.getLastDamageSource().isFireDamage()) && !this.isPotionActive(MobEffects.FIRE_RESISTANCE))
                {
                    potiontype = PotionTypes.FIRE_RESISTANCE;
                }
                else */if (this.rand.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth())
                {
                    potiontype = PotionTypes.HEALING;
                }
                else if (this.rand.nextFloat() < 0.5F && this.getAttackTarget() != null && !this.isPotionActive(MobEffects.SPEED) && this.getAttackTarget().getDistanceSq(this) > 121.0D)
                {
                    potiontype = PotionTypes.SWIFTNESS;
                    
                    //PotionTypes.
                }
                
                //PotionTypes.INVISIBILITY

                if (potiontype != null)
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potiontype));
                    this.attackTimer = this.getHeldItemMainhand().getMaxItemUseDuration();
                    this.setAggressive(true);
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_WITCH_DRINK, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
                    IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                    iattributeinstance.removeModifier(MODIFIER);
                    iattributeinstance.applyModifier(MODIFIER);
                }
            }

            if (this.rand.nextFloat() < 7.5E-4F)
            {
                this.world.setEntityState(this, (byte)15);
            }
        }

        super.onLivingUpdate();
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.setProfession(1);
        
        //this.setHeldItem(EnumHand.MAIN_HAND, null);
        
//        EntityHorse horse = new EntityHorse(this.world);
//        setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	    }

}
