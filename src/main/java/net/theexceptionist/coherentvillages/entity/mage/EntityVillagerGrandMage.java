package net.theexceptionist.coherentvillages.entity.mage;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.EntityVillagerLighting;
import net.theexceptionist.coherentvillages.entity.followers.EntityVillagerGuardian;

public class EntityVillagerGrandMage extends AbstractVillagerMage{
	public EntityVillagerGrandMage(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	public EntityVillagerGrandMage(World worldIn, boolean hostile) {
		super(worldIn, hostile);
		// TODO Auto-generated constructor stub
	}
	
	protected void initEntityAI()
    {
		super.initEntityAI();
       // this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
    }
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
	    }
	 
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		currentSpawns = this.world.getEntities(EntityVillagerGuardian.class, EntitySelectors.IS_ALIVE);
		// TODO Auto-generated method stub
		if(!this.world.isRemote){
			if(coolDown > 0)
			{
				coolDown--;
			}
			
			if(this.world.rand.nextInt(100) <= 80 && coolDown <= 0)
			{
				
				this.world.addWeatherEffect(new EntityVillagerLighting(this.world, target.posX, target.posY, target.posZ, true));
				coolDown = 3;
			} 
			else if (this.world.rand.nextInt(100) <= 40) 
			{
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
						entityvillager.onInitialSpawn(this.world.getDifficultyForLocation(getPosition()), null);
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
		}
	}

}
