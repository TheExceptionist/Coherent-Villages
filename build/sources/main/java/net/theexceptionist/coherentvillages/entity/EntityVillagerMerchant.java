package net.theexceptionist.coherentvillages.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackBackExclude;

public class EntityVillagerMerchant extends EntityVillager{
	protected String title;
	private Village home, destination;
	private int homeCheckTimer;
	private boolean traveling = false;
	private int rich;
	private boolean wasInit = false;
	private EntityMerchantGuard[] guards;
	
	public EntityVillagerMerchant(World worldIn) {
		super(worldIn);
		/*if( worldIn.getVillageCollection().getVillageList().size() > 0){
			this.home = worldIn.getVillageCollection().getNearestVillage(new BlockPos(this), 32);
		}*/
		
	}
	
	protected void initEntityAI()
    {
		
		//this.areAdditionalTasksSet = true;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
       // this.tasks.addTask(5, new EntityAIHangAroundFence(this, this.world));
        
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
      //  this.tasks.addTask(5, new EntityAITravelToDest(this));
        //this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
       //this.tasks.addTask(3, new EntityAIRoutedPatrol(this));
       // this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
        //this.areAdditionalTasksSet = true;
        
        
        
        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, false, new Class[0]));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 3, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }
	
	protected void init(){
		this.rich = 1 + world.rand.nextInt(5);
		guards = new EntityMerchantGuard[rich];
		//this.setCustomNameTag(title);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
		this.spawnGuards();
		//this.
		wasInit  = true;
	}
	
	 private void spawnGuards() {
		// System.out.println(rich);
		// TODO Auto-generated method stub
		for(int i = 0; i < rich; i++){
			EntityMerchantGuard entityvillager = new EntityMerchantGuard(world, this);
        	entityvillager.setLocationAndAngles((double)this.posX + 0.5D, (double)this.posY, (double)this.posZ + 0.5D, 0.0F, 0.0F);
        //    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k + 5D, (double)l + 0.5D);
            //entityvillager.setProfession(null);
            
         //   entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
            world.spawnEntity(entityvillager);
            guards[i] = entityvillager;
            //System.out.println(entityvillager);
		}
	}

	protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		 if(this.getAttackTarget() instanceof EntityVillager){
			 this.setAttackTarget(null);
		 }
		 
		 if(!wasInit){
			 init();
		 }
		 //System.out.println(this.getMaximumHomeDistance());
		 for(Object task : this.tasks.taskEntries.toArray())
			{
				 EntityAIBase ai = ((EntityAITaskEntry) task).action;
				 if(ai instanceof EntityAIHarvestFarmland)
					 this.tasks.removeTask(ai);	
				 //System.out.println("Removed");
			}
		 
		 if(this.getHealth() <= 4 && this.rich > 0)
		 {
			 this.dropItem(this.getDropItem(), this.rich);
			 this.rich = -1;
		 }
		 
	    }

	public boolean isTraveling() {
		return traveling;
	}

	public void setTraveling(boolean traveling) {
		this.traveling = traveling;
	}

	public Village getDestination() {
		return destination;
	}

	public void setDestination(Village destination) {
		this.destination = destination;
	}
	
	public void despawnGuards()
	{
		for(int i = 0; i < guards.length; i++)
		{
			guards[i].setDead();
		}
	}
	
    protected boolean canDespawn()
    {
        return true;
    }

    public int getMaxSpawnedInChunk()
    {
        return 4;
    }
    
    protected Item getDropItem()
    {
        return Item.getItemById(388);
    }
}
