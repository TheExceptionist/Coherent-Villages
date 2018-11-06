package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.items.ItemWeaponThrowable;

public class EntityAIAttackWithMelee extends EntityAIBase
{
    World world;
    protected EntityCreature attacker;
    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
    protected int attackTick;
    /** The speed with which the mob will approach the target */
    double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    boolean longMemory;
    /** The PathEntity of our entity. */
    Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int attackInterval = 20;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
	private double maxAttackDistance;
	private BlockPos targetPos = null;
	private int locateTime = -1;
	private boolean relocating = false;

    public EntityAIAttackWithMelee(EntityCreature creature, double speedIn, boolean useLongMemory)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        else
        {
            if (canPenalize)
            {
                if (--this.delayCounter <= 0)
                {
                    this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                    this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                    return this.path != null;
                }
                else
                {
                    return true;
                }
            }
            this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);

            if (this.path != null)
            {
                return true;
            }
            else
            {
                return this.getAttackReachSqr(entitylivingbase) >= this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        else if (!this.longMemory)
        {
            return !this.attacker.getNavigator().noPath();
        }
        else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase)))
        {
            return false;
        }
        else
        {
            return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).isSpectator() && !((EntityPlayer)entitylivingbase).isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.delayCounter = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer)entitylivingbase).isSpectator() || ((EntityPlayer)entitylivingbase).isCreative()))
        {
            this.attacker.setAttackTarget((EntityLivingBase)null);
        }

        this.attacker.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        if(!this.attacker.isRiding()) this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        
        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
        --this.delayCounter;

        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
        {
            this.targetX = entitylivingbase.posX;
            this.targetY = entitylivingbase.getEntityBoundingBox().minY;
            this.targetZ = entitylivingbase.posZ;
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

            if (this.canPenalize)
            {
                this.delayCounter += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null)
                {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                }
                else
                {
                    failedPathFindingPenalty += 10;
                }
            }

            if (d0 > 1024.0D)
            {
                this.delayCounter += 10;
            }
            else if (d0 > 256.0D)
            {
                this.delayCounter += 5;
            }
            
            boolean findPath = false;
            
            if(!this.attacker.isRiding())	
            {
                findPath = this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget);
            }
            else 
            {
            	/*if (d0 <= (double)this.maxAttackDistance)
                {
               	 //System.out.println("Clear!");
                    this.attacker.getNavigator().clearPath();
                    ++this.strafingTime;
                }
                else
                {
               	// System.out.println("Start! Distance: "+d0+" Max Distance: "+(double)this.maxAttackDistance);//+" See Time: "+(this.seeTime >= 20));
                    findPath = this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20)
                {
                    if ((double)this.attacker.getRNG().nextFloat() < 0.3D)
                    {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double)this.attacker.getRNG().nextFloat() < 0.3D)
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

                    //this.attacker.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    this.attacker.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    if(!this.attacmer.isRiding()) this.entity.faceEntity(entitylivingbase, 30.0F, 30.0F);
                }  
                else
                {
                    this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
                }*/
            	
            	if(!relocating) findPath = this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget);
            	else 
            	{
            		if(this.locateTime <= -1)
            		{
            			double x = -1 * (attacker.posX - entitylivingbase.posX);// ? entitylivingbase.posX - attacker.posX : attacker.posX - entitylivingbase.posX;// + (this.attacker.world.rand.nextInt(100) <= 50 ? 0 : 5);
	                	double y = entitylivingbase.posY;// > attacker.posX ? entitylivingbase.posX - attacker.posX : attacker.posX - entitylivingbase.posX;
	                	double z = -1 * (attacker.posZ - entitylivingbase.posZ);//> attacker.posZ ? entitylivingbase.posZ - attacker.posZ : attacker.posZ - entitylivingbase.posZ;// + (this.attacker.world.rand.nextInt(100) <= 50 ? 0 : 5);
	                	double radius = Math.sqrt(x * x + z * z);
	                	double rad = -1 * Math.atan2(z, x);
	                	
	                	z = radius * Math.cos(rad);
	                	x = radius * Math.sin(rad);
	                	
	                	targetPos = new BlockPos(x + entitylivingbase.posX, this.world.getTopSolidOrLiquidBlock(new BlockPos(x + entitylivingbase.posX, y, z + entitylivingbase.posZ)).getY(), z + entitylivingbase.posZ);
	                	this.attacker.getNavigator().tryMoveToXYZ(targetPos.getX(), targetPos.getY(), targetPos.getZ(), this.speedTowardsTarget);
	                	
	                	this.locateTime = 1;
            		}
            		else if(this.locateTime > 0)
            		{
            			locateTime--;
            		}
            		else
            		{
            			relocating = false;
            			this.locateTime = -1;
            		}
            		
            		//System.out.println("Relocating: "+relocating+" Time: "+this.locateTime);
            	}
            	/*
            	double x = -1 * (attacker.posX - entitylivingbase.posX);// ? entitylivingbase.posX - attacker.posX : attacker.posX - entitylivingbase.posX;// + (this.attacker.world.rand.nextInt(100) <= 50 ? 0 : 5);
            	double y = entitylivingbase.posY;// > attacker.posX ? entitylivingbase.posX - attacker.posX : attacker.posX - entitylivingbase.posX;
            	double z = -1 * (attacker.posZ - entitylivingbase.posZ);//> attacker.posZ ? entitylivingbase.posZ - attacker.posZ : attacker.posZ - entitylivingbase.posZ;// + (this.attacker.world.rand.nextInt(100) <= 50 ? 0 : 5);
            	double radius = Math.sqrt(x * x + z * z);
            	double rad = -1 * Math.atan2(z, x);
            	
            	z = radius * Math.cos(rad);
            	x = radius * Math.sin(rad);
            	
            	targetPos = new BlockPos(x + entitylivingbase.posX, this.world.getTopSolidOrLiquidBlock(new BlockPos(x + entitylivingbase.posX, y, z + entitylivingbase.posZ)).getY(), z + entitylivingbase.posZ);
            	this.attacker.getNavigator().tryMoveToXYZ(targetPos.getX(), targetPos.getY(), targetPos.getZ(), this.speedTowardsTarget);
            	*/
				/*
            	if(!findPath)
            	{
            		this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget);
            	}*/
            	
            	//System.out.println(" Target: ("+targetPos.getX()+","+targetPos.getY()+","+targetPos.getZ()+") Pos: ("+this.attacker.posX+","+this.attacker.posY+","+this.attacker.posZ+")");
        	

	            /*if(this.attacker.posX < x)
	            {
	            	x += 20;
	            }
	            else
	            {
	            	x -= 20;
	            }
	            	
	            if(this.attacker.posZ < z)
	            {
	            	z += 20;
	            }
	            else
	            {
	            	z -= 20;
	            }
            	

            	
            	findPath = this.attacker.getNavigator().tryMoveToXYZ(x, y, z, this.speedTowardsTarget);
            	*/
            }
            
            
            if (findPath)
            {
                this.delayCounter += 15;
            }
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_)
    {
        double d0 = this.getAttackReachSqr(p_190102_1_);
        double d1 = 0;
        
        if(this.attacker.isRiding()) d1 = 2;
        if (p_190102_2_ - d1 <= d0 && this.attackTick <= 0)
        {
        	this.relocating = true;
            this.attackTick = 20;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(p_190102_1_);
        }
        else if(this.attacker instanceof EntityHumanVillager && p_190102_2_ - d1 <= d0 * 2 && this.attackTick <= 0 && ((EntityHumanVillager) this.attacker).getThrownWeapon() != null)
        {
        	this.attackTick = 20;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            ((EntityHumanVillager) this.attacker).attackWithThrown(p_190102_1_, (float) d0, ((EntityHumanVillager) this.attacker).getThrownWeapon());
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F + attackTarget.width);
    }
}