package net.theexceptionist.coherentvillages.entity.soldier;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerPeasant extends AbstractVillagerSoldier{
	
	public EntityVillagerPeasant(World worldIn) {
		super(worldIn);
		this.className = "Peasant";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Peasant.ordinal()).spawn;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUpgrade() {
		this.upgrade = new EntityVillagerSergeant(world);
	}
	
	@Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			if(this.world.rand.nextInt(100) < 50)this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.WOODEN_SWORD));
			else this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.WOODEN_AXE));
			
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
	    }
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, false));
	       
    }
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.1D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	    }

}
