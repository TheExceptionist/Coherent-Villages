package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;

public class EntityAIShareTargetPlayer extends EntityAITarget {
	IEntityFollower host;
	EntityLivingBase master;
	
	public EntityAIShareTargetPlayer(EntityCreature creature, EntityLivingBase player, boolean checkSight) {
		super(creature, checkSight);
		this.host = (IEntityFollower) creature;
		this.master = player;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub

		//System.out.println(host.getLiving().getName()+" Not Working");
		if(host == null || master == null) return false;
		
		//System.out.println(host.getLiving().getName()+" Working");
		
		if(host.getLiving().getAttackTarget() != this.master.getAttackingEntity()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		// TODO Auto-generated method stub
		if(host == null || master == null) return false;
		
		if(host.getLiving().getAttackTarget() != this.master.getAttackingEntity()){
			return true;
		}else{
			return false;
		}
	}
	
	public void updateTask()
    {
		if(this.master.getAttackingEntity() != null){
			this.host.getLiving().setAttackTarget(this.master.getAttackingEntity());
		}

    }
}
