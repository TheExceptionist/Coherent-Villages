package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.theexceptionist.coherentvillages.entity.EntityMerchantGuard;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMage;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMerchant;
import net.theexceptionist.coherentvillages.entity.EntityVillagerSoldier;

public class EntityAIFollowMerchant extends EntityAIBase
{
    private final EntityVillagerSoldier theVillager;
    private EntityVillager merchant;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;
    private int waitTime;

    public EntityAIFollowMerchant(EntityVillagerSoldier theVillagerIn, EntityVillagerMage merchant)
    {
        this.theVillager = theVillagerIn;
        this.merchant = merchant;
        this.setMutexBits(3);
    }

	public EntityAIFollowMerchant(EntityMerchantGuard theVillagerIn, EntityVillagerMerchant merchant) {
		this.theVillager = theVillagerIn;
        this.merchant = merchant;
        this.setMutexBits(3);
	}

	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theVillager.isShouldFollow())
        {
        	this.waitTime = 0;
            return true;
        }
        
        else
        {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	if (this.theVillager.isShouldFollow())
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theVillager.getNavigator().getPathToEntityLiving(merchant);
    }
    

    /**
     * Resets the task
     */

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	//if(this.waitTime > 0){
    		//waitTime--;
    	//System.out.println("Following");
    	//}else
    		if(	this.theVillager.getNavigator().noPath()){
    	//	System.out.println("Following New Path");
    		this.theVillager.getNavigator().tryMoveToXYZ(merchant.posX + this.theVillager.world.rand.nextInt(20) - 10, merchant.posY, merchant.posZ + this.theVillager.world.rand.nextInt(20) - 10, 0.5D);//(merchant, 0.5D);
    		//this.waitTime = 100 + this.theVillager.world.rand.nextInt(100);
    	}
    }
}
