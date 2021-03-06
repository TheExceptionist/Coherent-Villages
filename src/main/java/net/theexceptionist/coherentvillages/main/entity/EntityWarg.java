package net.theexceptionist.coherentvillages.main.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIHideFromHarm;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;

public class EntityWarg extends EntityWolf implements IMob{

	public EntityWarg(World worldIn) {
		super(worldIn);
	}

	protected void initEntityAI()
    {
		this.tasks.addTask(0, new EntityAISwimming(this));
	        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
	        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
	       // this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.6D, true));
	        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
	        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
	        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
	        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	        this.tasks.addTask(11, new EntityAILookIdle(this));
	        
	        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
			//this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, true));
		    this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySheep.class, true));
		    this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChicken.class, true));
		    this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityBjornserker.class, 8.0F, 0.6D, 0.6D));
	        
	        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
	        {
	            public boolean apply(@Nullable EntityLiving p_apply_1_)
	            {
	            	if(p_apply_1_ instanceof EntityHumanVillager)
	            	{
	            		EntityHumanVillager villager = (EntityHumanVillager) p_apply_1_;
	            		if(villager.getVocation().getType() == AttributeVocation.CLASS_BANDIT) return false;
	            		else return true;
	            	}
	            	return p_apply_1_ != null && ((p_apply_1_ instanceof IAnimals) && !(p_apply_1_ instanceof EntityTameable)) && !(p_apply_1_ instanceof EntityVillager) && !(p_apply_1_ instanceof IMob);// /*|| (p_apply_1_ instanceof IAnimal)*/);
	            }
	        }));
	        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
    }
    
    @Override
    public void setTamed(boolean tamed)
    {
        
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        super.setAttackTarget(entitylivingbaseIn);
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	return false;
    }
    
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if(!this.world.isRemote)
        {
	        this.dropItem(Items.FLINT, world.rand.nextInt(3) + 1);
        }
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
        if (this.isAngry())
        {
            this.setAngry(true);
        }
    }
    
	 protected SoundEvent getAmbientSound()
	    {
	        return SoundEvents.ENTITY_WOLF_GROWL;
	    }

	    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	    {
	        return SoundEvents.ENTITY_WOLF_HOWL;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_WOLF_DEATH;
	    }
	    
	    protected boolean isValidLightLevel()
	    {
	        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

	        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
	        {
	            return false;
	        }
	        else
	        {
	            int i = this.world.getLightFromNeighbors(blockpos);

	            if (this.world.isThundering())
	            {
	                int j = this.world.getSkylightSubtracted();
	                this.world.setSkylightSubtracted(10);
	                i = this.world.getLightFromNeighbors(blockpos);
	                this.world.setSkylightSubtracted(j);
	            }

	            return i <= this.rand.nextInt(8);
	        }
	    }
		
		 public boolean getCanSpawnHere()
		 {
		     return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && (this.isValidLightLevel() && this.world.rand.nextInt(100) <= 2) && super.getCanSpawnHere() /*&& this.isCanSpawn() && rand.nextInt(100) < Main.bandit_spawn*/;
		 }
}
