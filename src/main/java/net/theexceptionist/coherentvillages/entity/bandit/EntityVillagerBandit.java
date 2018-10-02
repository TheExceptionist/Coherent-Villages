package net.theexceptionist.coherentvillages.entity.bandit;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerBandit extends AbstractVillagerBandit{
	public EntityVillagerBandit(World worldIn)
	{
		super(worldIn);
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Bandit.ordinal()).spawn;

	}
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.8D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(16.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
	    }
	 
		@Override
		 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		    {
		        super.setEquipmentBasedOnDifficulty(difficulty);
		        
		        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
				this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
				this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
		    }
}