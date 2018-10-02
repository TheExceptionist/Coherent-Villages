package net.theexceptionist.coherentvillages.entity.archer;

import java.util.Calendar;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public abstract class AbstractVillagerArcher extends AbstractVillagerSoldier implements IRangedAttackMob{
	protected boolean hunter;
	//protected boolean creeperHunter = false;
	
	public AbstractVillagerArcher(World worldIn) {
		super(worldIn);
		//this.creeperHunter = worldIn.rand.nextInt(100) < 25;
	}
	
	public AbstractVillagerArcher(World worldIn, boolean hunter) {
		this(worldIn);
		this.hunter = hunter;
		//this.creeperHunter = hunter;
	}
	
	public AbstractVillagerArcher(World worldIn, boolean hunter, boolean hostile)
	{
		super(worldIn, hostile);
		this.hunter = hunter;
	}
	
	protected void setEnchantment(){
		float f = 2;
		if (!this.getHeldItemMainhand().isEmpty() && this.rand.nextFloat() < 0.25F * f)
        {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
        }

        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
        {
            if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
            {
                ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

                if (!itemstack.isEmpty() && this.rand.nextFloat() < 0.5F * f)
                {
                    this.setItemStackToSlot(entityequipmentslot, EnchantmentHelper.addRandomEnchantment(this.rand, itemstack, (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
                }
            }
        }
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
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
      /*  this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.8D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(16.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);*/
//        EntityHorse horse = new EntityHorse(this.world);
//        horse.setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
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
	
	 @Override
	 protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		 
	    }
	 
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

	        this.setEnchantment();
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
			//this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			//this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
	    }
	 
	    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	    {
	        livingdata = super.onInitialSpawn(difficulty, livingdata);
	        this.setEquipmentBasedOnDifficulty(difficulty);
	        this.setEnchantmentBasedOnDifficulty(difficulty);
	        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

	        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
	        {
	            Calendar calendar = this.world.getCurrentDate();

	            /*if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
	            {
	                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
	                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
	            }*/
	        }

	        return livingdata;
	    }
	
	protected abstract EntityArrow getArrow(float p_190726_1_);
    /*{
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
		/*if(this.arrowType == 0){
			EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
	        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
	        return entitytippedarrow;
		}else{
			EntitySpectralArrow entitytippedarrow = new EntitySpectralArrow(this.world, this);
	        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
	        return entitytippedarrow;
		}
    }*/

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
}
