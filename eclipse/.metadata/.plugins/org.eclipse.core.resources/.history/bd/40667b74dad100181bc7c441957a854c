package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIUseLadder extends EntityAIBase{
	private EntityVillager villager;
	private World world;
	private BlockPos ladder;
	
	public EntityAIUseLadder(EntityVillager villager, World world){
		this.villager = villager;
		this.world = world;
		this.setMutexBits(1);
	}
	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		//return false;
	
		for(int x = (int) villager.posX - 3; x < 3 + (int) villager.posX; x++){
			for(int y = (int) villager.posY - 3; y < 3 + (int) villager.posY; y++){
				for(int z = (int) villager.posZ - 3; z < 3 + (int) villager.posZ; z++){
					BlockPos block = new BlockPos(x, y, z);
					
					if(world.getBlockState(block).getBlock() == Blocks.LADDER){
						ladder = block;
						
						/*int i =0;
						while(world.getBlockState(new BlockPos(ladder.getX(), ladder.getY() + i, ladder.getZ())).getBlock() == Blocks.LADDER){
							ladder = new BlockPos(ladder.getX(), ladder.getY() + i, ladder.getZ());
							i++;
						}*/
						return true;
					}
				}	
			}
		}
		
		return false;
	}
	
	public boolean continueExecuting()
    {
        if (this.villager.getPosition() == ladder)
        {
            return true;
        }
        else
        {
          //  float f = this.theEntity.width + 4.0F;
            return false;//this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
        }
    }

    /**155 73 122
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	//System.out.println(villager+" is Using a Ladder at: "+ladder.getX()+" "+ ladder.getY() +" "+ ladder.getZ());
		
        //this.villager.getNavigator().tryMoveToXYZ(ladder.getX(), ladder.getY(), ladder.getZ(), 0.4D);
    }


}
