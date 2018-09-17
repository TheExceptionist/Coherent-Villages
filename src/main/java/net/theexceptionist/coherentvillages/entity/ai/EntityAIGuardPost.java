package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.theexceptionist.coherentvillages.entity.EntityVillagerSoldier;

public class EntityAIGuardPost extends EntityAITarget {
	EntityVillagerSoldier soldier;
    /** The aggressor of the iron golem's village which is now the golem's attack target. */
    BlockPos post;
    
    EntityLivingBase targetEntity;
	private EntityLivingBase villageAgressorTarget;
	private int timer;
    protected BlockDoor doorBlock;
    protected BlockPos doorPosition = BlockPos.ORIGIN;
	private boolean dayTime;

    public EntityAIGuardPost(EntityVillagerSoldier soldier, boolean dayTime)
    {
        super(soldier, false, true);
        this.soldier = soldier;
        this.dayTime = dayTime;
        this.timer = 1000 + soldier.world.rand.nextInt(500);
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        Village village = this.soldier.getVillage();
//
        if(this.dayTime == this.soldier.world.isDaytime()){
	        if (village == null)
	        {
	            return false;
	        }
	        else
	        {
	            this.villageAgressorTarget = village.findNearestVillageAggressor(this.soldier);
	            
	            if (this.villageAgressorTarget instanceof EntityCreeper)
	            {
	                return false;
	            }
	            else if (this.isSuitableTarget(this.villageAgressorTarget, false))
	            {
	                return true;
	            }else if(this.timer >= 0){
	            	//post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(20) - 10, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(20) - 10);
	            	return true;
	            }
	            else if (this.taskOwner.getRNG().nextInt(20) == 0)
	            {
	                this.villageAgressorTarget = village.getNearestTargetPlayer(this.soldier);
	                return this.isSuitableTarget(this.villageAgressorTarget, false);
	            }
	            else if(this.soldier.getWorld().rand.nextInt(100) < 50){
	            	//post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2);
	            	this.timer = 1000 + soldier.world.rand.nextInt(500);
	            	return true;
	            }else
	            {
	                return false;
	            }
	        }
        }else{
        	return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
             PathNavigateGround pathnavigateground = (PathNavigateGround)this.soldier.getNavigator();
             Path path = pathnavigateground.getPath();

             if (path != null && !path.isFinished() && pathnavigateground.getEnterDoors())
             {
                 for (int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i)
                 {
                     PathPoint pathpoint = path.getPathPointFromIndex(i);
                     this.doorPosition = new BlockPos(pathpoint.x + 1, pathpoint.y + 1, pathpoint.z + 1);

                     if (this.soldier.getDistanceSq((double)this.doorPosition.getX(), this.soldier.posY, (double)this.doorPosition.getZ()) <= 2.25D)
                     {
                         this.doorBlock = this.getBlockDoor(this.doorPosition);

                         if (this.doorBlock != null)
                         {
                             return;
                         }
                     }
                 }

                 this.doorPosition = (new BlockPos(this.soldier)).up();
                 this.doorBlock = this.getBlockDoor(this.doorPosition);
             }

         
    }
    
             private BlockDoor getBlockDoor(BlockPos pos)
             {
                 IBlockState iblockstate = this.soldier.world.getBlockState(pos);
                 Block block = iblockstate.getBlock();
                 return block instanceof BlockDoor && iblockstate.getMaterial() == Material.WOOD ? (BlockDoor)block : null;
             }
             
    public boolean continueExecuting()
    {
        if (this.soldier.getNavigator().noPath())
        {
            return false;
        }
        else if (this.soldier.getRevengeTarget() != null)
        {	
        	this.soldier.setAttackTarget(this.soldier.getRevengeTarget());
        	return false;
        }
        else
        {
            timer--;
            
            //System.out.println(timer);
            
            if(timer <= 0)
            {
            	return false;
            }
            else
            {
            	return true;
            }
            //return this.entity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
        }
    }

}
