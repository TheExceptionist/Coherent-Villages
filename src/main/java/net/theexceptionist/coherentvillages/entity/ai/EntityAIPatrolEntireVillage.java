package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.math.PolarCoord;

public class EntityAIPatrolEntireVillage extends EntityAIBase {
	    private final EntityCreature entity;
	    private final double movementSpeed;
	    /** The PathNavigate of our entity. */
	    private Path path;
	    private BlockPos targetPos = null;
	    private final boolean isNocturnal;
		private boolean withDoors = true;
		private VillageDoorInfo doorInfo;
		private boolean useHomePosition = true;
	    
	    public EntityAIPatrolEntireVillage(EntityCreature entityIn, double movementSpeedIn, boolean isNocturnalIn, boolean withDoors, boolean useHome)
	    {
	        this.entity = entityIn;
	        this.movementSpeed = movementSpeedIn;
	        this.isNocturnal = isNocturnalIn;
	        this.withDoors  = withDoors;
	        this.useHomePosition = useHome;
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
	    	
	    	//System.out.println("Nocturnal: "+this.isNocturnal);
	        if (this.isNocturnal && this.entity.world.isDaytime())
	        {
	            return false;
	        }
	        else if(this.targetPos != null)
	        {
	        	if(this.path != null)
	        	{
	        		return true;
	        	}
	        	else
	        	{
	        		double distance = this.targetPos.getDistance((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ);
	        		
	        		if(distance < 2)
	        		{
	        			this.targetPos = null;
	        			return false;
	        		}
	        		else
	        		{
	        			this.path = this.entity.getNavigator().getPathToPos(this.targetPos);
		            	
		            	Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 10, 7, new Vec3d((double)this.targetPos.getX(), (double)this.targetPos.getY(), (double)this.targetPos.getZ()));

		            	if (vec3d == null)
	                    {
	                        return false;
	                    }
	                    else
	                    {
	                        this.path = this.entity.getNavigator().getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
	                        return this.path != null;
	                    }
	        		}
	        	}
	        }
	        else
	        {
	            Village village = this.entity.world.getVillageCollection().getNearestVillage(new BlockPos(this.entity), 0);

	            if (village == null)
	            {
	                return false;
	            }
	            else if(!this.withDoors)
	            {
	            	BlockPos center = null;// = village.getCenter();
	            	int radius = 0;// = village.getVillageRadius() * 2;
	            	
	            	if(entity instanceof EntityHumanVillager && this.useHomePosition ) 
	            	{
	            		EntityHumanVillager patrol = (EntityHumanVillager)entity;
	            		center = patrol.getHomePosition();
	            		radius = 25;
	            	}
	            	else
	            	{
	            		center = village.getCenter();
	            		radius = village.getVillageRadius() * 2;
	            	}
	            	
	            	PolarCoord villageArea = new PolarCoord(this.entity.getRNG(), center.getX(), center.getZ(), radius);
	            	villageArea.setDegree(this.entity.getRNG().nextInt(360));
	            	villageArea.setRadius(this.entity.getRNG().nextInt(radius));
	            	
	            	double x = villageArea.getWorldX();
	            	double z = villageArea.getWorldZ();
	            	double y = this.entity.world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();
	            	
	            	this.targetPos = center.add(new BlockPos(x, y, z));
	            	
	            	this.path = this.entity.getNavigator().getPathToPos(this.targetPos);
	            	
	            	Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 10, 7, new Vec3d((double)this.targetPos.getX(), (double)this.targetPos.getY(), (double)this.targetPos.getZ()));
                    if (vec3d == null)
                    {
                        return false;
                    }
                    else
                    {
                        this.path = this.entity.getNavigator().getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                        return this.path != null;
                    }
                  	
	            }
	            else
	            {
	            	int doorNum = village.getVillageDoorInfoList().size();
	                this.doorInfo = village.getVillageDoorInfoList().get(this.entity.world.rand.nextInt(doorNum));

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
	        	this.targetPos = null;
	            return false;
	        }
	        else
	        {
	            float f = this.entity.width + 4.0F;
	            return this.withDoors ? this.entity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f) : this.entity.getDistanceSq(this.targetPos) > (double)(f * f);
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
