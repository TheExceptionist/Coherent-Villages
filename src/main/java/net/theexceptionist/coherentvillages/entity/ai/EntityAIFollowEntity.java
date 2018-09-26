package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;

public class EntityAIFollowEntity extends EntityAIBase
{
    private final IEntityFollower theVillager;
    private EntityVillager toFollow;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;
    private int waitTime;

    public EntityAIFollowEntity(IEntityFollower theVillagerIn, EntityVillager merchant)
    {
        this.theVillager = theVillagerIn;
        this.toFollow = merchant;
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
        this.theVillager.getLiving().getNavigator().getPathToEntityLiving(toFollow);
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
    		if(	this.theVillager.getLiving().getNavigator().noPath()){
    	//	System.out.println("Following New Path");
    		this.theVillager.getLiving().getNavigator().tryMoveToXYZ(toFollow.posX + this.theVillager.getLiving().world.rand.nextInt(20) - 10, toFollow.posY, toFollow.posZ + this.theVillager.getLiving().world.rand.nextInt(20) - 10, 0.5D);//(merchant, 0.5D);
    		//this.waitTime = 100 + this.theVillager.world.rand.nextInt(100);
    	}
    }
}
