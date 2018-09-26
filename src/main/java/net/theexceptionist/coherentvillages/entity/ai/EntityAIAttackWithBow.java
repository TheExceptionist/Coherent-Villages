package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;

public class EntityAIAttackWithBow extends EntityAIBase {
	 /** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final IRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;
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
    
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public EntityAIAttackWithBow(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn)
    {
        this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
    }

    public EntityAIAttackWithBow(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn)
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
        EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
            this.attackTarget = entitylivingbase;
            return true;
        }
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
        double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
        boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);

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
            this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
        }
	            boolean flag1 = this.seeTime > 0;

	            if (flag != flag1)
	            {
	                this.seeTime = 0;
	            }

	            if (flag)
	            {
	                ++this.seeTime;
	            }
	            else
	            {
	                --this.seeTime;
	            }

	            if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
	            {
	                this.entityHost.getNavigator().clearPath();
	                ++this.strafingTime;
	            }
	            else
	            {
	                this.entityHost.getNavigator().tryMoveToEntityLiving(attackTarget, 0.5D);
	                this.strafingTime = -1;
	            }

	            if (this.strafingTime >= 20)
	            {
	                if ((double)this.entityHost.getRNG().nextFloat() < 0.3D)
	                {
	                    this.strafingClockwise = !this.strafingClockwise;
	                }

	                if ((double)this.entityHost.getRNG().nextFloat() < 0.3D)
	                {
	                    this.strafingBackwards = !this.strafingBackwards;
	                }

	                this.strafingTime = 0;
	            }

	            if (this.strafingTime > -1)
	            {
	                if (d0 > (double)(this.maxAttackDistance * 0.75F))
	                {
	                    this.strafingBackwards = false;
	                }
	                else if (d0 < (double)(this.maxAttackDistance * 0.25F))
	                {
	                    this.strafingBackwards = true;
	                }

	                this.entityHost.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
	                this.entityHost.faceEntity(attackTarget, 30.0F, 30.0F);
	            }
	            else
	            {
	                this.entityHost.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
	            }

	            if (this.entityHost.isHandActive())
	            {
	                if (!flag && this.seeTime < -60)
	                {
	                    this.entityHost.resetActiveHand();
	                }
	                else if (flag)
	                {
	                    int i = this.entityHost.getItemInUseMaxCount();

	                    if (i >= 20)
	                    {
	                        this.entityHost.resetActiveHand();
	                        ((IRangedAttackMob) this.entityHost).attackEntityWithRangedAttack(attackTarget, ItemBow.getArrowVelocity(i));
	                        this.rangedAttackTime = this.attackIntervalMin;
	                    }
	                }
	            }
	            else if (--this.rangedAttackTime <= 0 && this.seeTime >= -60)
	            {
	                this.entityHost.setActiveHand(EnumHand.MAIN_HAND);
	            }
	        }
	    
	 }
