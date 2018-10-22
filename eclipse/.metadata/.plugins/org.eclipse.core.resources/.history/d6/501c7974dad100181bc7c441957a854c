package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;

public class EntityAIFollowEntity extends EntityAIBase
{
    private final IEntityFollower theVillager;
    private EntityLivingBase toFollow;
    private int takeGolemRoseTick;
    private boolean tookGolemRose;
    private int waitTime;
    private boolean isPlayer;

    public EntityAIFollowEntity(IEntityFollower theVillagerIn, EntityLivingBase master, boolean isPlayer)
    {
        this.theVillager = theVillagerIn;
        this.toFollow = master;
        this.isPlayer = isPlayer;
        this.setMutexBits(3);
    }

	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	//System.out.println("Can Follow: "+this.theVillager.isShouldFollow());
        if (this.theVillager.isShouldFollow() && this.theVillager.getLiving().getAttackingEntity() == null && this.theVillager.getLiving().getAttackTarget() == null)
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
        if (this.theVillager.isShouldFollow() && this.theVillager.getLiving().getAttackingEntity() == null && this.theVillager.getLiving().getAttackTarget() == null)
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
    	//System.out.println("Start!");
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
    		if(!this.isPlayer)this.theVillager.getLiving().getNavigator().tryMoveToXYZ(toFollow.posX + this.theVillager.getLiving().world.rand.nextInt(20) - 10, toFollow.posY, toFollow.posZ + this.theVillager.getLiving().world.rand.nextInt(20) - 10, 0.5D);//(merchant, 0.5D);
    		else this.theVillager.getLiving().getNavigator().tryMoveToXYZ(toFollow.posX, toFollow.posY, toFollow.posZ + 2, 0.7D);//(merchant, 0.5D);
    		
    			//this.waitTime = 100 + this.theVillager.world.rand.nextInt(100);
    	}
    }
}
