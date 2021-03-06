package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIRest extends EntityAIBase
{
    private final EntityHumanVillager creature;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private final boolean restTime;
	private int attackTimer;

    public EntityAIRest(EntityHumanVillager creatureIn, boolean restInDay)
    {
        this.creature = creatureIn;
        this.restTime = restInDay;
        this.setMutexBits(1);
    }

	/**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	Village village = this.creature.getVillage();
    	
    	if(village != null)
    	{
    		BlockPos pos = village.getCenter();
    		int dist = (int) Math.floor(Math.sqrt(pos.distanceSq(this.creature.getPos())));
    		int radius = village.getVillageRadius();
    		
    		//System.out.println(this.creature.getCustomNameTag()+" Supply: "+this.creature.getSupply());
    		
    		if(dist < radius && this.creature.world.isDaytime() == restTime && this.creature.getHealth() < this.creature.getMaxHealth() && this.creature.getAttackTarget() == null && this.creature.getAttackingEntity() == null)
    		{
    			//System.out.println(creature.getCustomNameTag()+" - Executing");
    			//Must be within a village
    			//this.creature.reSupply(this.creature.getKills() * 2);
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
    	return false;//return this.creature.getHealth() < this.creature.getMaxHealth() && this.creature.getAttackTarget() == null && this.creature.getAttackingEntity() == null && this.creature.getSupply() > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	int type = this.creature.world.rand.nextInt(2);
    	PotionType potiontype = null;
        if(this.creature.world.rand.nextFloat() < 0.15F && !this.creature.isPotionActive(MobEffects.REGENERATION))
        {
        	 potiontype = PotionTypes.REGENERATION;
        	 type = 0;
        }
        else if (this.creature.getHealth() < this.creature.getMaxHealth())
        {
            potiontype = PotionTypes.HEALING;
            type = 1;
        }

        if(attackTimer > 0)
        {
        	attackTimer--;
        }
        
      /*  if (potiontype != null && attackTimer <= 0 && this.creature.getSupply() > 0)
        {
        	//ItemStack mainHand = this.creature.getHeldItemMainhand();
           // this.creature.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potiontype));
            this.attackTimer = 5;
            this.creature.world.playSound((EntityPlayer)null, this.creature.posX, this.creature.posY, this.creature.posZ, SoundEvents.ENTITY_WITCH_DRINK, this.creature.getSoundCategory(), 1.0F, 0.8F + this.creature.world.rand.nextFloat() * 0.4F);
            this.creature.setHealth(this.creature.getHealth() + 5);
            this.creature.reSupply(-1);
           // System.out.println(this.creature.getCustomNameTag()+" Healing, Supply: "+this.creature.getSupply());
            
           // this.creature.setHeldItem(EnumHand.MAIN_HAND, mainHand);
        }*/
    }
    
    public void updateTask()
    {
    	startExecuting();
    }
}
