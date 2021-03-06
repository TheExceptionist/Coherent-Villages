package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityVillager;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;

public class EntityAIShareTarget extends EntityAITarget {
	IEntityFollower host;
	EntityVillager master;
	
	public EntityAIShareTarget(EntityCreature creature, EntityVillager master, boolean checkSight) {
		super(creature, checkSight);
		this.host = (IEntityFollower) creature;
		this.master = master;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub

		//System.out.println(host.getLiving().getName()+" Not Working");
		if(host == null || master == null) return false;
		
		//System.out.println(host.getLiving().getName()+" Working");
		
		if(host.getLiving().getAttackTarget() != this.master.getAttackTarget()){
			return true;
		}else if(host.getLiving().getAttackTarget() != this.master.getAttackingEntity()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		// TODO Auto-generated method stub
		if(host == null || master == null) return false;
		
		if(host.getLiving().getAttackTarget() != this.master.getAttackTarget()){
			return true;
		}else if(host.getLiving().getAttackTarget() != this.master.getAttackingEntity()){
			return true;
		}else{
			return false;
		}
	}
	
	public void updateTask()
    {
		if(this.master.getAttackingEntity() != null){
			this.host.getLiving().setAttackTarget(this.master.getAttackingEntity());
		}else if(this.master.getAttackTarget() != null){
			this.host.getLiving().setAttackTarget(this.master.getAttackTarget());
		}

    }

}
