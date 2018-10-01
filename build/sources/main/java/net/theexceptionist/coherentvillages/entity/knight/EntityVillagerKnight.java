package net.theexceptionist.coherentvillages.entity.knight;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerKnight extends AbstractEntityKnight{

	public EntityVillagerKnight(World worldIn) {
		super(worldIn);
		this.className = "Knight";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Knight.ordinal()).spawn;

		// TODO Auto-generated constructor stub
	}
	
	@Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
	    }
	
	protected void initEntityAI()
   {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackMelee(this, SPRINT, true));
		
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
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.8D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
	    }


}
