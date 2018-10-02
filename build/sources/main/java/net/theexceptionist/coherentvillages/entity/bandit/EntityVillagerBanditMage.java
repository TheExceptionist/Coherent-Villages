package net.theexceptionist.coherentvillages.entity.bandit;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithMagic;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerBanditMage extends AbstractVillagerBandit implements IRangedAttackMob{
	protected int coolDown = 0;
	protected List currentSpawns;
	protected int burstCount;
	
	public EntityVillagerBanditMage(World worldIn)
	{
		super(worldIn);
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Bandit_Mage.ordinal()).spawn;
	}
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.2D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(16.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	    }
	 
		protected void initEntityAI()
	    {
			super.initEntityAI();
			//this.areAdditionalTasksSet = true;
	        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
	        this.tasks.addTask(1, new  EntityAIAttackWithMagic(this, 1.0D, 20, 15.0F));
	        
	   	 for(Object task : this.tasks.taskEntries.toArray())
			{
				 EntityAIBase ai = ((EntityAITaskEntry) task).action;
				 if(ai instanceof EntityAIAttackMelee)
					 this.tasks.removeTask(ai);	
				 //System.out.println("Removed");
			}
	       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
	    }
	 
		@Override
		 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		    {
		        super.setEquipmentBasedOnDifficulty(difficulty);
		        
		        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
		    }
		
		@Override
		public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
			if(coolDown > 0)
			{
				coolDown--;
			}
			else
			{
			
			 double d1 = 4.0D;
	         Vec3d vec3d = this.getLook(1.0F);
	         EntityLivingBase entitylivingbase = this.getAttackTarget();
	         
	         double d2 = entitylivingbase.posX - (this.posX + vec3d.x * 4.0D);
	         double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.posY + (double)(this.height / 2.0F));
	         double d4 = entitylivingbase.posZ - (this.posZ + vec3d.z * 4.0D);
	         
	         this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		       
	         EntityFireball entitylargefireball = new EntitySmallFireball(world, this, d2, d3, d4);
	         if(world.rand.nextInt() < 25) 
	        	 {
	        	 entitylargefireball = new EntityLargeFireball(world, this, d2, d3, d4);
	        	 ((EntityLargeFireball)entitylargefireball).explosionPower = 0;
	        	 }
	         
	         entitylargefireball.posX = this.posX + vec3d.x * 4.0D;
	         entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
	         entitylargefireball.posZ = this.posZ + vec3d.z * 4.0D;
	         world.spawnEntity(entitylargefireball);
	         
	         burstCount--;
			}
			
			if(burstCount <= 0)
			{
				coolDown = 3;
				burstCount = 5;
			}
		}


		@Override
		public void setSwingingArms(boolean swingingArms) {
			// TODO Auto-generated method stub
			
		}
}
