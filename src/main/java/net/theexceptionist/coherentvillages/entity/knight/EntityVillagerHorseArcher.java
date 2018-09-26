package net.theexceptionist.coherentvillages.entity.knight;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithBow;

public class EntityVillagerHorseArcher extends AbstractEntityKnight implements IRangedAttackMob{

	public EntityVillagerHorseArcher(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
	    }
	
	protected void initEntityAI()
   {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackWithBow(this, 1.0D, 60, 10.0F));
		
		//super.initEntityAI();
       //this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, true));
   }
	
	 public void onLivingUpdate()
	    {
		 super.onLivingUpdate();
	        if(this.horse != null)
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
		        this.horse.setMoveStrafe(amount);*/
	        }
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
