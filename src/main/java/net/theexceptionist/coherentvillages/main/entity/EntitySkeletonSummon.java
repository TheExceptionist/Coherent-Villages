package net.theexceptionist.coherentvillages.main.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIFollowEntity;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIShareTargetPlayer;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;

public class EntitySkeletonSummon extends EntitySkeleton implements IEntityFollower{
	private EntityLivingBase master = null;
	private int lifespan = 0;
	
	public EntitySkeletonSummon(World worldIn) {
		super(worldIn);
		
		this.onInitialSpawn(worldIn.getDifficultyForLocation(this.getPosition()), null);
		
		if(worldIn.rand.nextInt(100) <= 50) 
		{
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
		}
	}

	@Override
	public boolean isShouldFollow() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if(this.ticksExisted == this.lifespan)
		{
			this.setDead();
		}
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
