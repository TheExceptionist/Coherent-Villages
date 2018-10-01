package net.theexceptionist.coherentvillages.entity.ai;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.entity.alchemist.AbstractVillagerAlchemist;

public class EntityAIHealAllies extends EntityAIBase{
	/** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final AbstractVillagerAlchemist rangedAttackEntityHost;
    private Class <? extends EntityVillager >  attackTarget;
    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int range;
    private final double entityMoveSpeed;
    private int seeTime;
    /** The maximum time the AI has to wait before peforming another ranged attack. */
    private final float attackRadius;
    private final float maxAttackDistance;
	private EntityLivingBase target;
	//private int coolDown = 0;

    public EntityAIHealAllies(AbstractVillagerAlchemist entityVillagerPotionMaster, double movespeed, int range, float maxAttackDistanceIn, Class <? extends EntityVillager > villager)
    {
        this(entityVillagerPotionMaster, movespeed, range, maxAttackDistanceIn);
        this.attackTarget = villager;
        
    }

    public EntityAIHealAllies(AbstractVillagerAlchemist entityVillagerHealer, double movespeed, int range, float maxAttackDistanceIn)
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
	            	 //System.out.println("Searching: "+target.getName()+" Health: "+target.getHealth()+"/"+target.getMaxHealth());
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
	       // System.out.println("healing start");
	        this.rangedAttackEntityHost.setClient(target);
	        
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
	          //  System.out.println("moving start");
	        }
	
	        this.entityHost.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	
	        if (d0 < this.range)
	        {
	
	            float f = MathHelper.sqrt(d0) / this.attackRadius;
	            float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
	            this.entityHost.getNavigator().tryMoveToEntityLiving(this.target, 0.5D);
	            this.rangedAttackEntityHost.healEntityWithRangedAttack(this.target, lvt_5_1_);
	            //System.out.println("Healing: "+this.target);
	           // this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
	        }
	    	}
    }
}
