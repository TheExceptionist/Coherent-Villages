package net.theexceptionist.coherentvillages.entity.ai;

import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityAIGuardPost  extends EntityAIBase {
	private IVillagerGuard soldier;
	private World worldin;
	private boolean travelSucceed, arrived;

    public EntityAIGuardPost(IVillagerGuard soldier, World worldin)
    {
        this.soldier = soldier;
        this.worldin = worldin;
        this.arrived = false;
        this.setMutexBits(1);
    }

    
    public boolean shouldExecute()
    {
        Village village = this.soldier.getSoldier().getVillage();
        
        if(village == null || this.soldier.getSoldier().getAttackTarget() != null || this.soldier.getSoldier().getAttackingEntity() != null)
        {
        	return false;
        }
        else if(this.soldier.getPost() != null)
        {
        	//System.out.println("Starting guard post");
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
    	BlockPos post =  this.soldier.getPost();
    	
    	if(post != null)
    	{
    		travelToPost(post);
        	//System.out.println("Post- X: "+post.getX()+" Y: "+post.getY()+" Z: "+post.getZ());
    	} 
    }
             
    private void travelToPost(BlockPos post) {
    	if(post != null){
			double speed = (double)this.soldier.getSoldier().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 3;
			travelSucceed = this.soldier.getSoldier().getNavigator().tryMoveToXYZ(post.getX(), post.getY(), post.getZ(), speed);
    	}
    }

	public boolean shouldContinueExecuting()
    {
    	Village village = this.soldier.getSoldier().getVillage();  
   
    	//
    	if(village == null || this.soldier.getSoldier().getAttackTarget() != null || this.soldier.getSoldier().getAttackingEntity() != null)
    	{
    		//System.out.println("Stopping execute..."+village+" | attacking: "+this.soldier.getAttackTarget()+" | attacker: "+this.soldier.getAttackingEntity());
    	   return false;
    	}
    	else if(this.soldier.getPost() == null)
        {
        	return false;
        }
    	else if(this.soldier.getSoldier().getNavigator().noPath())
    	{
    		travelToPost(this.soldier.getPost());
    		//System.out.println("Continue execute... X:"+this.soldier.getSoldier().getNavigator();// "+post.getX()+" Y: "+post.getY()+" Z: "+post.getZ()+" Travel: "+travelSucceed);
    		
    		/*if(this.soldier.getPos() == this.post)
    		{
    			arrived = true;
    		}
    		else if(!arrived)
    		{
    			travelToPost();
    		}*/
    		
    	   return true;
        }
    	else
    	{
    		return false;
    	}
     }

}
