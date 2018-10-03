package net.theexceptionist.coherentvillages.entity.archer;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithBow;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIMoveThroughOutskirts;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerMageArcher extends AbstractVillagerArcher{

	public EntityVillagerMageArcher(World worldIn) {
		super(worldIn);
		this.className = "Mage Archer";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Mage_Archer.ordinal()).spawn;

		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void setUpgrade() {
		this.upgrade = null;
	}
	
	
	@Override
	protected EntityArrow getArrow(float p_190726_1_) {
		EntitySpectralArrow entitytippedarrow = new EntitySpectralArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.1D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.1D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
//        EntityHorse horse = new EntityHorse(this.world);
//        horse.setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
		
    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
	    }
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackWithBow(this, 1.0D, 60, 10.0F));
		this.tasks.addTask(5, new EntityAIMoveThroughOutskirts(this, 0.6D, true));
    }

}
