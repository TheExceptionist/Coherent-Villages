package net.theexceptionist.coherentvillages.main.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIFollowEntity;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIShareTargetPlayer;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;

public class EntitySkeletonSummon extends EntitySkeleton implements IEntityFollower{
	private EntityLivingBase master = null;
	private int lifespan;
	
	public EntitySkeletonSummon(World worldIn) {
		super(worldIn);
	}

	@Override
	public boolean isShouldFollow() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if(this.lifespan <= 0)
		{
			this.setDead();
		}
		
		this.lifespan--;
	}

	@Override
	public EntityLiving getLiving() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setMaster(EntityLivingBase villager) {
		// TODO Auto-generated method stub
		this.master = villager;
		this.tasks.addTask(3, new EntityAIFollowEntity(this, master, true));	
		this.targetTasks.addTask(0, new EntityAIShareTargetPlayer(this, master, false));
	}
	
	public void setLifespan(int lifespan)
	{
		this.lifespan = lifespan;
	}

}
