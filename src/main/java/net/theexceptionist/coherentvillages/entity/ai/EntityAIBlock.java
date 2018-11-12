package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIBlock extends EntityAIBase
{
	protected EntityHumanVillager host;
	protected double distance;
	private boolean alwaysBlock;
	
	public EntityAIBlock(EntityHumanVillager host, final double distance, final boolean alwaysBlock)
	{
		this.host = host;
		this.distance = distance;
		this.alwaysBlock = alwaysBlock;
		this.setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		//System.out.println("Trying to execute: "+host.inCombat());
		if(host.inCombat() && (host.getHostileEntity().getDistance(host) > distance || alwaysBlock))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void startExecuting()
    {
        host.setBlocking(true);
    }
	
	public boolean continueExecuting()
    {
		boolean continueBlocking = this.shouldExecute() || !this.host.getNavigator().noPath();
		host.setBlocking(continueBlocking);
		//System.out.println("Contine Executing: "+continueBlocking);
        return continueBlocking;
    }
	
	 public void resetTask()
	 {
		 super.resetTask();
		 host.setBlocking(false);
	 }

}
