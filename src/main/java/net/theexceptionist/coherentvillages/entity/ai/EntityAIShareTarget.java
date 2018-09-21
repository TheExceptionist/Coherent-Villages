package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityVillager;

public class EntityAIShareTarget extends EntityAITarget {
	EntityVillager host;
	EntityVillager master;
	
	public EntityAIShareTarget(EntityCreature creature, EntityVillager master, boolean checkSight) {
		super(creature, checkSight);
		this.host = (EntityVillager) creature;
		this.master = master;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		
		if(host.getAttackTarget() != this.master.getAttackTarget()){
			return true;
		}else if(host.getAttackTarget() != this.master.getAttackingEntity()){
			return true;
		}else{
			return false;
		}
	}
	
	public void updateTask()
    {
		if(this.master.getAttackingEntity() != null){
			this.host.setAttackTarget(this.master.getAttackingEntity());
		}else{
			this.host.setAttackTarget(this.master.getAttackTarget());
		}

    }

}
