package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIAttackWithBow extends EntityAIBase {
	private final EntityHumanVillager entity;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private final float maxAttackDistance;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
	private EntityLivingBase attackTarget;

    public EntityAIAttackWithBow(EntityHumanVillager p_i47515_1_, double p_i47515_2_, int p_i47515_4_, float p_i47515_5_)
    {
        this.entity = p_i47515_1_;
        this.moveSpeedAmp = p_i47515_2_;
        this.attackCooldown = p_i47515_4_;
        this.maxAttackDistance = p_i47515_5_ * p_i47515_5_;
        this.setMutexBits(3);
    }

    public void setAttackCooldown(int p_189428_1_)
    {
        this.attackCooldown = p_189428_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

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
        return this.shouldExecute() || !this.entity.getNavigator().noPath();
    }
    
    public void startExecuting()
    {
        super.startExecuting();
        ((IRangedAttackMob)this.entity).setSwingingArms(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
        ((IRangedAttackMob)this.entity).setSwingingArms(false);
        this.attackTarget = null;
        this.seeTime = 0;
        this.attackTime = -1;
        
        if(this.entity instanceof EntityHumanVillager)  ((EntityHumanVillager)this.entity).setStrafing(false);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	 EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

         if (entitylivingbase != null)
         {
             double d0 = this.entity.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
             boolean flag = this.entity.getEntitySenses().canSee(entitylivingbase);
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

             if (d0 <= (double)this.maxAttackDistance/* && this.seeTime >= 20*/)
             {
            	 //System.out.println("Clear!");
                 this.entity.getNavigator().clearPath();
                 ++this.strafingTime;
             }
             else if(d0 >= (double)this.maxAttackDistance)
             {
            	// System.out.println("Start! Distance: "+d0+" Max Distance: "+(double)this.maxAttackDistance);//+" See Time: "+(this.seeTime >= 20));
                 this.entity.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.moveSpeedAmp);
                 this.strafingTime = -1;
             }

             if (this.strafingTime >= 20)
             {
                 if ((double)this.entity.getRNG().nextFloat() < 0.3D)
                 {
                     this.strafingClockwise = !this.strafingClockwise;
                 }

                 if ((double)this.entity.getRNG().nextFloat() < 0.3D)
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

                 this.entity.setMoveStrafing(this.strafingClockwise ? 0.5F : -0.5F);
                 //this.entity.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                 if(!this.entity.isRiding()) this.entity.faceEntity(entitylivingbase, 30.0F, 30.0F);
             }
             else
             {
                 this.entity.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
             }

             if (this.entity.isHandActive())
             {
                 if (!flag && this.seeTime < -60)
                 {
                     this.entity.resetActiveHand();
                 }
                 else if (flag)
                 {
                     int i = this.entity.getItemInUseMaxCount();

                     if (i >= 20)
                     {
                         this.entity.resetActiveHand();
                         ((IRangedAttackMob)this.entity).attackEntityWithRangedAttack(entitylivingbase, ItemBow.getArrowVelocity(i));
                         this.attackTime = this.attackCooldown;
                     }
                 }
             }
             else if (--this.attackTime <= 0 && this.seeTime >= -60)
             {
                 this.entity.setActiveHand(EnumHand.MAIN_HAND);
             }
         }
	 }
}