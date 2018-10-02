package net.theexceptionist.coherentvillages.entity.bandit;

import java.util.Calendar;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.coherentvillages.entity.ai.EntityAISearchHouse;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIStayInBorders;
import net.theexceptionist.coherentvillages.entity.followers.EntitySkeletonMinion;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;
import net.theexceptionist.coherentvillages.main.Main;

public  abstract class AbstractVillagerBandit extends AbstractVillagerSoldier {
	public AbstractVillagerBandit(World worldIn) {
		super(worldIn);
		this.className = "Bandit";
		this.faction = Main.BANDIT_FACTION;
		this.isHostile = true;
		//this.canSpawn = Main.villager_spawn.get(Main.Soldier.Militia.ordinal()).spawn;
	}
	
	 public void onDeath(DamageSource cause)
	    {
	        super.onDeath(cause);

	        if(world.rand.nextInt(100) < 25) this.dropEquipment(true, 0);
	        this.dropItem(Items.EMERALD, world.rand.nextInt(3) + 1);
	    }
	
	 protected boolean canDropLoot()
	    {
	        return true;
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
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere() && this.isCanSpawn();
	    }
	
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.addTask(5, new EntityAISearchHouse(this, 90, false));
	       
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
	
	 public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
	    {
	        super.setRevengeTarget(livingBase);

	        if (this.village != null && livingBase != null)
	        {
	            this.village.addOrRenewAgressor(livingBase);

	            if (livingBase instanceof EntityPlayer)
	            {
	                int i = -1;

	                if (this.isChild())
	                {
	                    i = -3;
	                }

	                //this.village.modifyPlayerReputation(livingBase.getUniqueID(), i);

	                if (this.isEntityAlive())
	                {
	                    this.world.setEntityState(this, (byte)13);
	                }
	            }
	        }
	    }
	

	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
        
        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
        {
            Calendar calendar = this.world.getCurrentDate();

            /*if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }*/
        }
        
		this.setAlwaysRenderNameTag(false);
		this.setDropItemsWhenDead(true);

        return livingdata;
    }
	
	
}
