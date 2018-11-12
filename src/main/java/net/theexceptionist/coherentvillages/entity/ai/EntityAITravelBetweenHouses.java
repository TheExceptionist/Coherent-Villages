package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAITravelBetweenHouses extends EntityAIBase
{
    private final EntityCreature entity;
    private VillageDoorInfo doorInfo;
    private int insidePosX = -1;
    private int insidePosZ = -1;

    public EntityAITravelBetweenHouses(EntityCreature entityIn)
    {
        this.entity = entityIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        BlockPos blockpos = new BlockPos(this.entity);
        Village village = this.entity.world.getVillageCollection().getNearestVillage(blockpos, 14);
        

        if (this.entity.world.isDaytime() && village != null)
        {
        	this.doorInfo =  village.getNearestDoor(blockpos);
        	BlockPos doorPos = this.doorInfo.getDoorBlockPos();
        	
        	switch(this.doorInfo.getInsideDirection())
        	{
			case DOWN:
				break;
			case EAST:
				doorPos.add(1, 0, 0);
				break;
			case NORTH:
				break;
			case SOUTH:
				break;
			case UP:
				break;
			case WEST:
				break;
			default:
				break;
        	
        	}
        	
            if (this.entity.getRNG().nextInt(50) != 0)
            {
                return false;
            }
            else
            {
               // Village village = this.entity.world.getVillageCollection().getNearestVillage(blockpos, 14);

                if (village == null)
                {
                    return false;
                }
                else
                {
                    this.doorInfo = village.getDoorInfo(blockpos);
                    return this.doorInfo != null;
                }
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
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.insidePosX = -1;
        BlockPos blockpos = this.doorInfo.getInsideBlockPos();
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();

        if (this.entity.getDistanceSq(blockpos) > 256.0D)
        {
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 14, 3, new Vec3d((double)i + 0.5D, (double)j, (double)k + 0.5D));

            if (vec3d != null)
            {
                this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            }
        }
        else
        {
            this.entity.getNavigator().tryMoveToXYZ((double)i + 0.5D, (double)j, (double)k + 0.5D, 1.0D);
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.insidePosX = this.doorInfo.getInsideBlockPos().getX();
        this.insidePosZ = this.doorInfo.getInsideBlockPos().getZ();
        this.doorInfo = null;
    }
}
