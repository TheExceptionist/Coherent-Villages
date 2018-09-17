package net.theexceptionist.main.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.theexceptionist.main.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.main.entity.ai.EntityAIFollowMerchant;
import net.theexceptionist.main.entity.ai.EntityAIShareTarget;

public class EntityMerchantGuard extends EntityVillagerSoldier{
	private int attack = 10, armor = 10;
	private boolean shouldFollow = true;
	private EntityVillagerMerchant master;
	//Placeholder for now
	private String title = "Merchant Guard";

	public EntityMerchantGuard(World worldIn) {
		super(worldIn);

	}
	
	public EntityMerchantGuard(World worldIn,  EntityVillagerMerchant master) {
		super(worldIn);
		this.setMaster(master);
		this.title = master.title+"'s Guard";
		this.setCustomNameTag(title);
		
	}
	
	
	public void initBackground(){
		this.type = -1;
		
	}
	
	public void setMaster(EntityVillagerMerchant master){
		//System.out.println(this+" : "+this.master);
		this.master = master;
		this.tasks.addTask(2, new EntityAIFollowMerchant(this, master));
		this.targetTasks.addTask(0, new EntityAIShareTarget(this, master, false));
		
	}

	public boolean isShouldFollow() {
		return shouldFollow;
	}

	public void setShouldFollow(boolean shouldFollow) {
		this.shouldFollow = shouldFollow;
	}
	
	protected void initEntityAI()
    {
		
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
	

		//this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        
        
        
        
       // this.tasks.addTask(5, new EntityAIHangAroundFence(this, this.world));
        
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
       //this.tasks.addTask(3, new EntityAIRoutedPatrol(this));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        
        
        this.targetTasks.addTask(3, new EntityAIAttackBackExclude(this, false, new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }
	//this.title = this.getRandomName()+" - "+master.title+"'s Guard";
	//this.setCustomNameTag(this.title);
	 protected void updateAITasks()
	    {
		 super.updateAITasks();
		 if(this.getAttackTarget() instanceof EntityVillager){
			 this.setAttackTarget(null);
		 }
	    }

	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
	    	
			
	    }

}
