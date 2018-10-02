package net.theexceptionist.coherentvillages.entity.archer;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithBow;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerArcher extends AbstractVillagerArcher{

	public EntityVillagerArcher(World worldIn) {
		super(worldIn);
		this.className = "Archer";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Archer.ordinal()).spawn;

		// TODO Auto-generated constructor stub
	}
	
	public EntityVillagerArcher(World worldIn, boolean hunter, boolean hostile) {
		super(worldIn, hunter, hostile);
		// TODO Auto-generated constructor stub
	}
	
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
//        EntityHorse horse = new EntityHorse(this.world);
//        horse.setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
		
    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
	    }
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackWithBow(this, 1.0D, 60, 10.0F));
		  this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, true));
	       // this.tasks.addTask(6, new EntityAISearchHouse(this, 50));
	        this.tasks.addTask(7, new EntityAIMoveTowardsRestriction(this, 1.0D));
	       
    }

	@Override
	protected EntityArrow getArrow(float p_190726_1_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
	}
}
