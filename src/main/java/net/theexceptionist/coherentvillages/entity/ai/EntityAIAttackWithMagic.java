package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIAttackWithMagic extends EntityAIBase {
    /** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;

    /**
     * The entity (as a RangedAttackMob) the AI instance has been applied to.
     */
    private final EntityHumanVillager rangedAttackEntityHost;
    private EntityLivingBase attackTarget;

    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int rangedAttackTime;
    private double entityMoveSpeed;
    private int field_75318_f;
    private int field_96561_g;

    /**
     * The maximum time the AI has to wait before peforming another ranged attack.
     */
    private int maxRangedAttackTime;
    private float field_96562_i;
    private float field_82642_h;

	private float distance;

    public EntityAIAttackWithMagic(EntityHumanVillager par1IRangedAttackMob, double par2, int par4, float par5, float distance)
    {
        this(par1IRangedAttackMob, par2, par4, par4, par5, distance);
    }

    public EntityAIAttackWithMagic(EntityHumanVillager par1IRangedAttackMob, double par2, int par4, int par5, float par6, float distance)
    {
        this.rangedAttackTime = -1;

        if (!(par1IRangedAttackMob instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = par1IRangedAttackMob;
            this.entityHost = (EntityLiving)par1IRangedAttackMob;
            this.entityMoveSpeed = par2;
            this.field_96561_g = par4;
            this.maxRangedAttackTime = par5;
            this.field_96562_i = par6;
            this.field_82642_h = par6 * par6;
            this.distance = distance;
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
        this.field_75318_f = 0;
        this.rangedAttackTime = -1;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.posY, this.attackTarget.posZ);
        boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);

        //System.out.println("Executing");
        
        if (flag)
        {
            ++this.field_75318_f;
        }
        else
        {
            this.field_75318_f = 0;
        }

        if (d0 <= (double)this.field_82642_h && this.field_75318_f >= 20)
        {
            this.entityHost.getNavigator().clearPath();
        }
        else
        {
        	BlockPos pos = this.attackTarget.getPosition();
        	float radius = distance;
        	double rad = Math.toRadians(this.attackTarget.getRNG().nextInt(360));
        	
        	double x = radius * Math.cos(rad);
        	double z = radius * Math.sin(rad);

        	BlockPos targetPos = pos.add(new BlockPos(x, 0, z));
        	double y = this.attackTarget.world.getTopSolidOrLiquidBlock(targetPos).getY();

            this.entityHost.getNavigator().tryMoveToXYZ(targetPos.getX(), y, targetPos.getZ(), this.entityMoveSpeed);
        }

        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
        float f;

        if (--this.rangedAttackTime == 0)
        {
            if (d0 > (double)this.field_82642_h || !flag)
            {
                return;
            }

            f = MathHelper.sqrt(d0) / this.field_96562_i;
            float f1 = f;
            this.rangedAttackEntityHost.swingArm(EnumHand.MAIN_HAND);

            if (f < 0.1F)
            {
                f1 = 0.1F;
            }

            if (f1 > 1.0F)
            {
                f1 = 1.0F;
            }

            this.rangedAttackEntityHost.attackEntityWithMagicAttack(this.attackTarget, f1);
            this.rangedAttackTime = 10;// MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
        else if (this.rangedAttackTime < 0)
        {
            f = MathHelper.sqrt(d0) / this.field_96562_i;
            this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
    }
}
