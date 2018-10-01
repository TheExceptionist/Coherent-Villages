package net.theexceptionist.coherentvillages.entity.archer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithBow;
import net.theexceptionist.coherentvillages.entity.ai.EntityAISearchHouse;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerMarksman extends AbstractVillagerArcher{

	public EntityVillagerMarksman(World worldIn) {
		super(worldIn);
		this.className = "Marksman";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Marksman.ordinal()).spawn;

		// TODO Auto-generated constructor stub
	}
	
	protected EntityArrow getArrow(float p_190726_1_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
       this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
        
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.1D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.4D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(8.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
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
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 60, 60.0F));
        this.tasks.addTask(5, new EntityAISearchHouse(this, 50, true));
    }
	
	public void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor) {
		//if(!this.world.isRemote){
		//System.out.println("Attacking");
		EntityArrow entityarrow = this.getArrow(distanceFactor);
		 entityarrow.setDamage((float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		//if(creeperHunter)
			//entityarrow.setDamage(40D);
		
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 2.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2) / 2;
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 3.2F, 0.0F);
       
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        //System.out.println(entityarrow);
        //if(!this.world.isRemote){
        	this.world.spawnEntity(entityarrow);
      //  }
		//}
	}

}
