package net.theexceptionist.coherentvillages.entity.ai;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveThroughOutskirts extends EntityAIBase {
	 private final EntityCreature entity;
	    private final double movementSpeed;
	    /** The PathNavigate of our entity. */
	    private Path path;
	    private VillageDoorInfo doorInfo;
	    private final boolean isNocturnal;

	    public EntityAIMoveThroughOutskirts(EntityCreature entityIn, double movementSpeedIn, boolean isNocturnalIn)
	    {
	        this.entity = entityIn;
	        this.movementSpeed = movementSpeedIn;
	        this.isNocturnal = isNocturnalIn;
	        this.setMutexBits(1);

	        if (!(entityIn.getNavigator() instanceof PathNavigateGround))
	        {
	            throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
	        }
	    }

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute()
	    {
	        if (this.isNocturnal && this.entity.world.isDaytime())
	        {
	            return false;
	        }
	        else
	        {
	            Village village = this.entity.world.getVillageCollection().getNearestVillage(new BlockPos(this.entity), 0);

	            if (village == null)
	            {
	                return false;
	            }
	            else
	            {
	            	List<VillageDoorInfo> doorList = village.getVillageDoorInfoList();
	                this.doorInfo = doorList.get(this.entity.world.rand.nextInt(doorList.size()));

	                if (this.doorInfo == null)
	                {
	                    return false;
	                }
	                else
	                {
	                    PathNavigateGround pathnavigateground = (PathNavigateGround)this.entity.getNavigator();
	                    boolean flag = pathnavigateground.getEnterDoors();
	                    pathnavigateground.setBreakDoors(false);
	                    this.path = pathnavigateground.getPathToPos(this.doorInfo.getDoorBlockPos());
	                    pathnavigateground.setBreakDoors(flag);

	                    if (this.path != null)
	                    {
	                        return true;
	                    }
	                    else
	                    {
	                        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 10, 7, new Vec3d((double)this.doorInfo.getDoorBlockPos().getX(), (double)this.doorInfo.getDoorBlockPos().getY(), (double)this.doorInfo.getDoorBlockPos().getZ()));

	                        if (vec3d == null)
	                        {
	                            return false;
	                        }
	                        else
	                        {
	                            pathnavigateground.setBreakDoors(false);
	                            this.path = this.entity.getNavigator().getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
	                            pathnavigateground.setBreakDoors(flag);
	                            return this.path != null;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean shouldContinueExecuting()
	    {
	        if (this.entity.getNavigator().noPath())
	        {
	            return false;
	        }
	        else
	        {
	            float f = this.entity.width + 4.0F;
	            return this.entity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
	        }
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	        this.entity.getNavigator().setPath(this.path, this.movementSpeed);
	    }
}
