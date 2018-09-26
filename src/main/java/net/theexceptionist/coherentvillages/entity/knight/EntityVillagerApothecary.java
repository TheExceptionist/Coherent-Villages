package net.theexceptionist.coherentvillages.entity.knight;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.EntityVillagerHorse;

public class EntityVillagerApothecary extends AbstractEntityKnight implements IRangedAttackMob{

	public EntityVillagerApothecary(World worldIn) {
		super(worldIn);
		this.undeadHunter = true;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STICK));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
	    }
	
	protected void initEntityAI()
   {
		super.initEntityAI();
		
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 60, 10.0F));
		
		//super.initEntityAI();
       //this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, true));
   }
	
	 public void onLivingUpdate()
	    {
		 super.onLivingUpdate();
	       /* if(this.getAtta != null)
	        {
	        	//this.getNavigator();
	        	//getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	        	//this.horse.get.setSprinting(true);
	        	//0.8 trot
	        	//1.6 sprint
	        	//2.4 Running
	        	//this.getMoveHelper().setMoveTo(this.posX + 100, this.world.getTopSolidOrLiquidBlock(new BlockPos(this.posX + 100, 80, this.posZ)).getY(), this.posZ, 2.4D);
	        	//this.setMoveForward(1);
	        	//this.horse.setMoveForward(5);
		        //this.horse.travel(this.posX + 1, this.posY, forward);
		        /*this.horse.turn(yaw, pitch);
		        this.horse.setVelocity(x, y, z);
		        this.horse.move(type, x, y, z);
		        this.horse.setMoveForward(amount);
		        this.horse.setMoveVertical(amount)
		        this.horse.setMoveStrafe(amount);
	        }*/
	    }
	 
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.6D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(16.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		//System.out.println("Working");
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
        else if (!target.isEntityUndead() && !target.isPotionActive(MobEffects.WEAKNESS))
        {
            potiontype = PotionTypes.WEAKNESS;
        }
        
        if(potiontype != null){
            EntityPotion entitypotion = new EntityPotion(this.world, this, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potiontype));
            entitypotion.rotationPitch -= -20.0F;
            entitypotion.shoot(d1, d2 + (double)(f * 0.2F), d3, 0.75F, 8.0F);
            this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
            this.world.spawnEntity(entitypotion);
        }
       
	}
	
	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

}
