package net.theexceptionist.coherentvillages.entity.followers;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIFollowEntity;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIShareTarget;
import net.theexceptionist.coherentvillages.entity.mage.AbstractVillagerMage;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public class EntityVillagerGuardian extends AbstractVillagerSoldier {
	private boolean areAdditionalTasksSet;
	public boolean wasSpawned = false;
	public BlockPos spawnPos;
	protected int homeCheckTimer; 
	protected Village village;
	protected Object buyingList;
	protected int type;
	
	
	private World world;
	private AbstractVillagerMage master;
	


	public EntityVillagerGuardian(World worldIn) {
		super(worldIn);
		this.world = worldIn;
		this.className = "Ancient Villager Guardian";

	}
	
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
		return false;
    }
	
	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player)
    {
        if (this.buyingList == null)
        {
            //this.populateBuyingList();
        }

        return null;
    }
	
	protected void initEntityAI()
    {
		this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
       // this.tasks.addTask(5, new EntityAIHangAroundFence(this, this.world));
        
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        
        if(this.master != null){
        	this.tasks.addTask(3, new EntityAIFollowEntity(this, master));
        	this.targetTasks.addTask(0, new EntityAIShareTarget(this, master, false));
        }
        
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        

        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
    }
	
	protected void applyEntityAttributesAgain()
    {
        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        this.setProfession(1);
	    }
	 
	 protected void damageEntity(DamageSource damageSrc, float damageAmount)
	    {
		 super.damageEntity(damageSrc, damageAmount);
	    }
	 
	 protected void updateAITasks()
	    {
		 super.updateAITasks();
		 if(this.ticksExisted > 1000){
			 this.setDead();
			 for (int k = 0; k < 20; ++k)
		        {
		            double d2 = this.rand.nextGaussian() * 0.02D;
		            double d0 = this.rand.nextGaussian() * 0.02D;
		            double d1 = this.rand.nextGaussian() * 0.02D;
		            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1, new int[0]);
		        }
		 }
		 
		 if(this.master != null){
			 for (int k = 0; k < 20; ++k)
		        {
		            double d2 = this.rand.nextGaussian() * 0.02D;
		            double d0 = this.rand.nextGaussian() * 0.02D;
		            double d1 = this.rand.nextGaussian() * 0.02D;
		            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1, new int[0]);
		        }
	        	this.tasks.addTask(3, new EntityAIFollowEntity(this, master));
	        }
		 //System.out.println(this.getMaximumHomeDistance());
	    }
	 
	 
	 public void onStruckByLightning(EntityLightningBolt lightningBolt)
	    {
		for(PotionEffect potion : PotionUtils.getFullEffectsFromItem(new ItemStack(Items.GOLDEN_APPLE, 1))){
	       // this.set
		 this.addPotionEffect(new PotionEffect(potion));
		}
		
		
	    }
	

	 public boolean attackEntityAsMob(Entity entityIn)
	    {
		 return super.attackEntityAsMob(entityIn);
	    }

	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
	    }
	 
	public void setSpawnPoint(double d, double k, double e) {
		this.spawnPos = new BlockPos(d, k, e);
	}


	public Village getVillage() {
		// TODO Auto-generated method stub
		return this.village;
	}

	public int getType() {
		//System.out.println(this+" 1 "+title+" : "+type);
		return this.type;
	}
	
	public void setMaster(AbstractVillagerMage entityVillagerMage) {
		this.master = entityVillagerMage;
		
	}
	
	public AbstractVillagerMage getMaster() {
		return this.master;//= entityVillagerMage;
		
	}

	@Override
	public void setMaster(AbstractVillagerSoldier villager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isShouldFollow() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public EntityLiving getLiving() {
		// TODO Auto-generated method stub
		return this;
	}
}