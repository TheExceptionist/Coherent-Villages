package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.Village;
import net.theexceptionist.coherentvillages.main.entity.IEntityVillager;

public class EntityAIStayInBorders extends EntityAIBase
{
    private final IEntityVillager creature;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private final double movementSpeed;

    public EntityAIStayInBorders(IEntityVillager creatureIn, double speedIn)
    {
        this.creature = creatureIn;
        this.movementSpeed = speedIn;
        this.setMutexBits(1);
    }

	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	Village village = this.creature.getVillage();
    	
    	if(village != null)
    	{
    		BlockPos pos = village.getCenter();
    		int dist = (int) Math.floor(Math.sqrt(pos.distanceSq(this.creature.getLiving().getPos())));
    		int radius = village.getVillageRadius();
    		
    		if(dist > radius)
    		{
    			//System.out.println(this.creature.getCustomNameTag()+" - "+dist+" "+radius);
    			Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.creature.getLiving(), 16, 7, new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()));
    		
    			if (vec3d == null)
                {
                    return false;
                }
                else
                {
                	this.movePosX = vec3d.x;
                    this.movePosY = vec3d.y;
                    this.movePosZ = vec3d.z;
                    return true;
                }
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
    	return !this.creature.getLiving().getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.creature.getLiving().getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
    }
}
