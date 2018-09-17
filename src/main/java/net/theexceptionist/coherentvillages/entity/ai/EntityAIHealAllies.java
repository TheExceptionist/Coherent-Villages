package net.theexceptionist.coherentvillages.entity.ai;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.entity.EntityVillagerAlchemist;

public class EntityAIHealAllies extends EntityAIBase{
	/** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final EntityVillagerAlchemist rangedAttackEntityHost;
    private Class <? extends EntityVillager >  attackTarget;
    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int rangedAttackTime;
    private final double entityMoveSpeed;
    private int seeTime;
    private final int attackIntervalMin;
    /** The maximum time the AI has to wait before peforming another ranged attack. */
    private final int maxRangedAttackTime;
    private final float attackRadius;
    private final float maxAttackDistance;
	private EntityLivingBase target;

    public EntityAIHealAllies(EntityVillagerAlchemist attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn, Class <? extends EntityVillager > villager)
    {
        this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
        this.attackTarget = villager;
        
    }

    public EntityAIHealAllies(EntityVillagerAlchemist attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn)
    {
        this.rangedAttackTime = -1;

        if (!(attacker instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = attacker;
            this.entityHost = (EntityLiving)attacker;
            this.entityMoveSpeed = movespeed;
            this.attackIntervalMin = p_i1650_4_;
            this.maxRangedAttackTime = maxAttackTime;
            this.attackRadius = maxAttackDistanceIn;
            this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
            this.setMutexBits(3);
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	double d0 = 40.0D;
    	//System.out.println("Working 1");
        List<EntityVillager> list = this.rangedAttackEntityHost.world.getEntitiesWithinAABB(EntityVillager.class, this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0));
       // Collections.sort(list, this.sorter);
//System.out.println("Searching");
        if (list.isEmpty())
        {
            return false;
        }
        else
        {
        	for(int i = 0; i < list.size(); i++){
        		//list = this.rangedAttackEntityHost.world.getEntitiesWithinAABB(EntityVillager.class, this.rangedAttackEntityHost.getEntityBoundingBox().expand(d0, 4.0D, d0));
	            this.target = (EntityLivingBase)list.get(i);
	           // System.out.println("Searching: "+target+" "+target.getHealth()+"/"+target.getMaxHealth()+" "+this.entityHost);
	            if(this.target.getHealth() < this.target.getMaxHealth()){
	            //	 System.out.println("Searching: "+target.getHealth()+"/"+target.getMaxHealth());
	            	return true;
	            }else{
	            	return false;
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
        this.rangedAttackTime = -1;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	if(this.target != null){
	        double d0 = this.entityHost.getDistanceSq(this.target.posX, this.target.getEntityBoundingBox().minY, this.target.posZ);
	        boolean flag = true;//this.entityHost.getEntitySenses().canSee(this.attackTarget);
	
	        if (flag)
	        {
	            ++this.seeTime;
	        }
	        else
	        {
	            this.seeTime = 0;
	        }
	
	        if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
	        {
	            this.entityHost.getNavigator().clearPath();
	        }
	        else
	        {
	            this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, this.entityMoveSpeed);
	        }
	
	        this.entityHost.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	
	        if (--this.rangedAttackTime == 0)
	        {
	            if (!flag)
	            {
	                return;
	            }
	
	            float f = MathHelper.sqrt(d0) / this.attackRadius;
	            float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
	            this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, 0.5D);
	            this.rangedAttackEntityHost.healEntityWithRangedAttack(this.target, lvt_5_1_);
	            System.out.println("Healing: "+this.target);
	            this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
	            
	        }
	        else if (this.rangedAttackTime < 0)
	        {
	            float f2 = MathHelper.sqrt(d0) / this.attackRadius;
	            this.rangedAttackTime = MathHelper.floor(f2 * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
	        }
	    	}
    }
}
