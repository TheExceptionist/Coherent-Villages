package net.theexceptionist.coherentvillages.entity.mage;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithMagic;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public abstract class AbstractVillagerMage extends AbstractVillagerSoldier implements IRangedAttackMob{
	protected int coolDown = 0;
	protected List currentSpawns;
	protected int burstCount;
	
	
	public AbstractVillagerMage(World worldIn) {
			super(worldIn);
		}

	public AbstractVillagerMage(World worldIn, boolean hostile) {
		super(worldIn, hostile);
	}
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		//this.areAdditionalTasksSet = true;
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(1, new  EntityAIAttackWithMagic(this, 1.0D, 20, 15.0F));
        
   	 for(Object task : this.tasks.taskEntries.toArray())
		{
			 EntityAIBase ai = ((EntityAITaskEntry) task).action;
			 if(ai instanceof EntityAIAttackMelee)
				 this.tasks.removeTask(ai);	
			 //System.out.println("Removed");
		}
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.setProfession(1);
        
        //this.setHeldItem(EnumHand.MAIN_HAND, null);
        
//        EntityHorse horse = new EntityHorse(this.world);
//        setPosition(this.posX, this.posY, this.posZ);
//        this.world.spawnEntity(horse);
//        this.startRiding(horse);
    }
	
	@Override
	 protected void updateAITasks()
	    {
		
		 super.updateAITasks();
		 
	    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        //super.setEquipmentBasedOnDifficulty(difficulty);
	    }


	@Override
	public abstract void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor); 
		/*
		if(!this.world.isRemote){
			List currentSpawns = this.world.getEntities(EntityVillagerGuardian.class, EntitySelectors.IS_ALIVE);
			
			if(coolDown > 0)
			{
				coolDown--;
			}
			
			if(this.world.rand.nextInt(100) <= 80 && coolDown <= 0){
				
				this.world.addWeatherEffect(new EntityVillagerLighting(this.world, target.posX, target.posY, target.posZ, true));
				coolDown = 3;
			} else if (this.world.rand.nextInt(100) <= 40) {
				boolean cont = true;
				for(int i = 0; i < currentSpawns.size(); i++){
					EntityVillagerGuardian g = (EntityVillagerGuardian) currentSpawns.get(i);
					
					if(g.getMaster() == this){
						cont = false;
					}
				}
				
				if(cont){
				      this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
				       
					int amount = 1 + this.world.rand.nextInt(4);
					BlockPos spawn = new BlockPos(this.posX, this.posY, this.posZ);
					for(int i =0; i < amount; i++){
						EntityVillagerGuardian entityvillager = new EntityVillagerGuardian(this.world);
			            entityvillager.setLocationAndAngles(spawn.getX() + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5), 0.0F, 0.0F);
			            //entityvillager.setSpawnPoint(spawn.getX()  + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5));
			            //entityvillager.setProfession(null); 
			            entityvillager.setMaster(this);
			            
			            //entityvillager.finalizeMobSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
			            this.world.spawnEntity(entityvillager);
					}
				}
				else if(this.world.rand.nextInt(100) <= 40)
				{
					 double d1 = 4.0D;
                     Vec3d vec3d = this.getLook(1.0F);
                     EntityLivingBase entitylivingbase = this.getAttackTarget();
                     
                     double d2 = entitylivingbase.posX - (this.posX + vec3d.x * 4.0D);
                     double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.posY + (double)(this.height / 2.0F));
                     double d4 = entitylivingbase.posZ - (this.posZ + vec3d.z * 4.0D);
                     
                     this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
				       
                     EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this, d2, d3, d4);
                     entitylargefireball.explosionPower = 0;
                     entitylargefireball.posX = this.posX + vec3d.x * 4.0D;
                     entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                     entitylargefireball.posZ = this.posZ + vec3d.z * 4.0D;
                     world.spawnEntity(entitylargefireball);
				}
				else{
				      this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
				       
				}
			}
		}*/
	

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
	

}
