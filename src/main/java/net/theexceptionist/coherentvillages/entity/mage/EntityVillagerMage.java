package net.theexceptionist.coherentvillages.entity.mage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
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

public class EntityVillagerMage extends AbstractVillagerMage {
	public EntityVillagerMage(World worldIn) {
		super(worldIn);
		this.burstCount = 5;
		// TODO Auto-generated constructor stub
	}
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		//this.areAdditionalTasksSet = true;
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        
			/*this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));*/
	    }
	 
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.1D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
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

}
