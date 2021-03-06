package net.theexceptionist.coherentvillages.entity.ai;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIHealAllies extends EntityAIBase{
	/** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final EntityHumanVillager rangedAttackEntityHost;
    private Class <? extends EntityVillager >  attackTarget;
    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int range;
    private final double entityMoveSpeed;
    private int seeTime;
    /** The maximum time the AI has to wait before peforming another ranged attack. */
    private final double attackRadius;
    private final double maxAttackDistance;
	private EntityLivingBase target;
	//private int coolDown = 0;

    public EntityAIHealAllies(EntityHumanVillager entityHumanVillager, double movespeed, int range, double maxAttackDistanceIn, Class <? extends EntityVillager > villager)
    {
        this(entityHumanVillager, movespeed, range, maxAttackDistanceIn);
        this.attackTarget = villager;
        
    }

    public EntityAIHealAllies(EntityHumanVillager entityVillagerHealer, double movespeed, int range, double maxAttackDistanceIn)
    {
        if (!(entityVillagerHealer instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = entityVillagerHealer;
            this.entityHost = (EntityLiving)entityVillagerHealer;
            this.entityMoveSpeed = movespeed;
          //  this.attackIntervalMin = p_i1650_4_;
            this.range = range;
            this.attackRadius = maxAttackDistanceIn;
            this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
            this.setMutexBits(1);
        }
    }


	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	double d0 = attackRadius * 2;
    	//System.out.println("Working 1");
        List<EntityVillager> list = this.rangedAttackEntityHost.world.getEntitiesWithinAABB(EntityVillager.class, this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 8.0D, d0).offset(-d0/2, -4, -d0/2));
       // Collections.sort(list, this.sorter);
         //System.out.println(this.entityHost.getCustomNameTag()+" Searching: "+d0+" Axis X: "+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).minX+"/"+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).maxX+" Axis Y: "+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).minY+"/"+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).maxY+" Axis Z: "+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).minZ+"/"+this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0).maxZ);
        if (list.isEmpty())
        {
        	System.out.println("empty");
            return false;
        }
        else
        {
        	for(int i = 0; i < list.size(); i++){
        		//list = this.rangedAttackEntityHost.world.getEntitiesWithinAABB(EntityVillager.class, this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0));
	            this.target = (EntityLivingBase)list.get(i);
	        	
	        	if(this.target.getHealth() < this.target.getMaxHealth()){
		            
	            //	 System.out.println("Searching: "+target.getName()+" Health: "+target.getHealth()+"/"+target.getMaxHealth());
	            	return true;
	            }
        	}
        }
		return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attackTarget = null;
        this.seeTime = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	if(this.target != null){
	        double d0 = this.entityHost.getDistanceSq(this.target.posX, this.target.getEntityBoundingBox().minY, this.target.posZ);
	        boolean flag = true;//this.entityHost.getEntitySenses().canSee(this.attackTarget);
	       //Unused for now
	        //System.out.println(this.entityHost.getCustomNameTag()+" healing start");
	        //this.rangedAttackEntityHost.setClient(target);
	
	        /*if (d0 <= (double)this.maxAttackDistance)
	        {
	            this.entityHost.getNavigator().clearPath();
	            System.out.println("Time: "+this.seeTime+" Distance: "+d0+" Max Distance: "+this.maxAttackDistance);
	        }
	        else
	        {
	            this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, this.entityMoveSpeed);
	            System.out.println("moving start");
	        }*/
	        this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, this.entityMoveSpeed);
	
	        this.entityHost.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	
	        if (d0 < this.range)
	        {
	
	            double f = MathHelper.sqrt(d0) / this.attackRadius;
	            float lvt_5_1_ = (float)MathHelper.clamp(f, 0.1F, 1.0F);
	            this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, 0.5D);
	            this.rangedAttackEntityHost.healEntityWithRangedAttack(this.target, lvt_5_1_);
	            //System.out.println("Healing: "+this.target);
	           // this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
	        }
	    	}
    }
}
