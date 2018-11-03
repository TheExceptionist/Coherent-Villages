package net.theexceptionist.coherentvillages.main.entity;

import java.util.Calendar;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.theexceptionist.coherentvillages.entity.ai.test.EntityAITestTarget;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;

public class EntityBloodBat extends EntityCreature implements IMob{
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte>createKey(EntityBat.class, DataSerializers.BYTE);
    /** Coordinates of where the bat spawned. */
    private BlockPos spawnPosition;
	private int lifespan = -1;

    public EntityBloodBat(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
        this.setIsBatHanging(true);
    }
    
    protected void initEntityAI()
    {
    	super.initEntityAI();
    	
    	this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));

    	
    	this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		//this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, true));
		//this.targetTasks.addTask(3, new EntityAITestTarget(this, EntityGolem.class, true));

    	this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
            	if(p_apply_1_ instanceof EntityHumanVillager)
            	{
            		EntityHumanVillager villager = (EntityHumanVillager) p_apply_1_;
            		
            		if(villager.getRace().getType() == AttributeRace.RACE_TYPE_VAMPIRE || villager.isVampire()) return false;
            		else return true;
            	}
            	return p_apply_1_ != null && ((p_apply_1_ instanceof IAnimals) && !(p_apply_1_ instanceof EntityTameable)) && !(p_apply_1_ instanceof IMob);// /*|| (p_apply_1_ instanceof IAnimal)*/);
            }
        }));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

    }
    

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(HANGING, Byte.valueOf((byte)0));
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	boolean flag = super.attackEntityAsMob(entityIn);
    	float f = 4;
    	
    	if(flag) 
    	{
    		flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
    		
    		((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
        	if(this.getRNG().nextInt(100) < 5) ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 150));

        	if(entityIn instanceof EntityHumanVillager) 
        	{
        		if(this.getRNG().nextInt(100) < Main.german_vampire_turn_chance) ((EntityHumanVillager)entityIn).setVampire(true);
        	}
    	}
    	
    	return flag;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.95F;
    }

    @Nullable
    public SoundEvent getAmbientSound()
    {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn)
    {
    }

    protected void collideWithNearbyEntities()
    {
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    public boolean getIsBatHanging()
    {
        return (((Byte)this.dataManager.get(HANGING)).byteValue() & 1) != 0;
    }

    public void setIsBatHanging(boolean isHanging)
    {
        byte b0 = ((Byte)this.dataManager.get(HANGING)).byteValue();

        if (isHanging)
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if(!this.world.isRemote)
        {
        	if(this.getAttackTarget() == null || this.getAttackingEntity() == null)
        	{
		        if (this.getIsBatHanging())
		        {
		            this.motionX = 0.0D;
		            this.motionY = 0.0D;
		            this.motionZ = 0.0D;
		            this.posY = (double)MathHelper.floor(this.posY) + 1.0D - (double)this.height;
		        }
		        else
		        {
		            this.motionY *= 0.6000000238418579D;
		        }
        	}
        }
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.up();
        
        if(!this.world.isRemote)
        {  
	        if(this.lifespan == 0)
	        {
	        	this.setDead();
	        }
	        else if(this.lifespan != -1)
	        {
	        	this.lifespan--;
	        }
	        
	        //System.out.println("Bat Target: "+this.getAttackTarget());
	        
	        if(this.getAttackTarget() == null || this.getAttackingEntity() == null)
	        {
		        if(this.world.isDaytime())
				{
		            float f2 = this.getBrightness();
		
		            if (f2 > 0.5F && this.rand.nextFloat() * 30.0F < (f2 - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ)))
		            {
		                boolean flag = true;
		                ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		
		                if (!itemstack.isEmpty())
		                {
		                    if (itemstack.isItemStackDamageable())
		                    {
		                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));
		
		                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
		                        {
		                            this.renderBrokenItemStack(itemstack);
		                            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
		                        }
		                    }
		
		                    flag = false;
		                }
		
		                if (flag)
		                {
		                    this.setFire(3);
		                }
		            }
				}
		
		        if (this.getIsBatHanging())
		        {
		            if (this.world.getBlockState(blockpos1).isNormalCube())
		            {
		                if (this.rand.nextInt(200) == 0)
		                {
		                    this.rotationYawHead = (float)this.rand.nextInt(360);
		                }
		
		                if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null)
		                {
		                    this.setIsBatHanging(false);
		                    this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
		                }
		            }
		            else
		            {
		                this.setIsBatHanging(false);
		                this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
		            }
		        }
		        else
		        {
		            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
		            {
		                this.spawnPosition = null;
		            }
		
		            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
		            {
		                this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		            }
		
		            double d0 = (double)this.spawnPosition.getX() + 0.5D - this.posX;
		            double d1 = (double)this.spawnPosition.getY() + 0.1D - this.posY;
		            double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
		            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
		            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
		            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
		            float f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
		            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
		            this.moveForward = 0.5F;
		            this.rotationYaw += f1;
		
		            if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube())
		            {
		                this.setIsBatHanging(true);
		            }
		        }
	        }
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote && this.getIsBatHanging())
            {
                this.setIsBatHanging(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public static void registerFixesBat(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityBat.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(HANGING, Byte.valueOf(compound.getByte("BatFlags")));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("BatFlags", ((Byte)this.dataManager.get(HANGING)).byteValue());
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (blockpos.getY() >= this.world.getSeaLevel())
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);
            int j = 4;

            if (this.isDateAroundHalloween(this.world.getCurrentDate()))
            {
                j = 7;
            }
            else if (this.rand.nextBoolean())
            {
                return false;
            }

            return i > this.rand.nextInt(j) ? false : super.getCanSpawnHere();
        }
    }

    private boolean isDateAroundHalloween(Calendar p_175569_1_)
    {
        return p_175569_1_.get(2) + 1 == 10 && p_175569_1_.get(5) >= 20 || p_175569_1_.get(2) + 1 == 11 && p_175569_1_.get(5) <= 3;
    }

    public float getEyeHeight()
    {
        return this.height / 2.0F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_BAT;
    }

	public void setLifespan(int lifespan) {
		this.lifespan  = lifespan;
	}
}
