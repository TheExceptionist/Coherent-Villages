package net.theexceptionist.coherentvillages.entity.bandit;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerBanditArcher extends AbstractVillagerBandit implements IRangedAttackMob{
	public EntityVillagerBanditArcher(World worldIn)
	{
		super(worldIn);
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Bandit_Archer.ordinal()).spawn;
	}
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(16.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	    }
	 
	 protected void initEntityAI()
	    {
			super.initEntityAI();
	        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
			
			 for(Object task : this.tasks.taskEntries.toArray())
				{
					 EntityAIBase ai = ((EntityAITaskEntry) task).action;
					 if(ai instanceof EntityAIAttackMelee)
						 this.tasks.removeTask(ai);	
					 //System.out.println("Removed");
				}
	    }
	 
		@Override
		 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		    {
		        super.setEquipmentBasedOnDifficulty(difficulty);
		        
		        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
				this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
				this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
		    }
		
		@Override
		public void attackEntityWithRangedAttack(EntityLivingBase target,
				float distanceFactor) {
			//if(!this.world.isRemote){
			//System.out.println("Attacking");
			EntityArrow entityarrow = this.getArrow(distanceFactor);
			 entityarrow.setDamage((float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
			//if(creeperHunter)
				//entityarrow.setDamage(40D);
			
	        double d0 = target.posX - this.posX;
	        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
	        double d2 = target.posZ - this.posZ;
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
	       
	        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        //System.out.println(entityarrow);
	        //if(!this.world.isRemote){
	        	this.world.spawnEntity(entityarrow);
	      //  }
			//}
		}

		protected EntityArrow getArrow(float p_190726_1_) {
			EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
	        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
	        return entitytippedarrow;
		}
		
		@Override
		public void setSwingingArms(boolean swingingArms) {
			// TODO Auto-generated method stub
			
		}
}