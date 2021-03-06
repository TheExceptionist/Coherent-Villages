package net.theexceptionist.coherentvillages.main.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityModFireballSmall extends EntitySmallFireball
{
    public EntityModFireballSmall(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityModFireballSmall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityModFireballSmall(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesSmallFireball(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "SmallFireball");
    }
    
    public void setShooter(EntityLivingBase shooter)
    {
    	this.shootingEntity = shooter;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (!result.entityHit.isImmuneToFire())
                {
                	EntityHumanVillager villager = null;
                	boolean flag = false;
                	
                	if(this.shootingEntity instanceof EntityHumanVillager)
                	{
                		villager = (EntityHumanVillager) shootingEntity;
            			//System.out.println("Shooting Entity: "+(this.shootingEntity != null)+" Fireball: "+(this != null)+" Hit: "+(result.entityHit != null));
            			flag = result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), (float) villager.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                		
                	}
                	else
                	{
            			//System.out.println("Shooting Entity: "+(this.shootingEntity != null)+" Fireball: "+(this != null)+" Hit: "+(result.entityHit != null));
            			flag = result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0f);
                	}
                	
                    if (flag)
                    {
                        this.applyEnchantments(this.shootingEntity, result.entityHit);
                        result.entityHit.setFire(5);
                    }
                }
            }
            else
            {
                boolean flag1 = true;

                if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving)
                {
                    flag1 = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
                
                    if(this.shootingEntity instanceof EntityHumanVillager)
                	{
                		if(flag1) flag1 = ((EntityHumanVillager)this.shootingEntity).isDestructive();//) this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, true, true);
                	}
                }

                if (flag1)
                {
                    BlockPos blockpos = result.getBlockPos().offset(result.sideHit);

                    if (this.world.isAirBlock(blockpos))
                    {
                        this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                    }
                }
            }

            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }
}