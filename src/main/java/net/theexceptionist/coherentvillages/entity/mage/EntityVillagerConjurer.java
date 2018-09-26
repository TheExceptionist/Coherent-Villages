package net.theexceptionist.coherentvillages.entity.mage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.followers.EntityVillagerGuardian;

public class EntityVillagerConjurer extends AbstractVillagerMage {

	public EntityVillagerConjurer(World worldIn) {
		super(worldIn);
		this.burstCount = worldIn.rand.nextInt(3) + 3;
	}
	
	
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        
			//this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
	    }
	 
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	    }

	//Todo allow conjurer to summon bowmen
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		currentSpawns = this.world.getEntities(EntityVillagerGuardian.class, EntitySelectors.IS_ALIVE);
		if(coolDown > 0)
		{
			coolDown--;
		}
		if(coolDown == 0){
			for(int i = 0; i < currentSpawns.size(); i++){
				EntityVillagerGuardian g = (EntityVillagerGuardian) currentSpawns.get(i);
			}
			// TODO Auto-generated method stub
		      this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		       
			int amount = 1 + this.world.rand.nextInt(4);
			BlockPos spawn = new BlockPos(this.posX, this.posY, this.posZ);
			for(int i =0; i < amount; i++){
				EntityVillagerGuardian entityvillager = new EntityVillagerGuardian(this.world);
				entityvillager.onInitialSpawn(this.world.getDifficultyForLocation(getPosition()), null);
	            entityvillager.setLocationAndAngles(spawn.getX() + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5), 0.0F, 0.0F);
	            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, 
	            		(double)(spawn.getX() + rand.nextInt(5)), 
	            		(double)(spawn.getY()  + rand.nextInt(5)),
	            		(double)(spawn.getZ()  + rand.nextInt(5)),
	            		(double)5, 
	            		(double)5,
	            		(double)5
	            		);
			       
	            //entityvillager.setSpawnPoint(spawn.getX()  + rand.nextInt(5),spawn.getY()  + rand.nextInt(5),spawn.getZ()  + rand.nextInt(5));
	          //entityvillager.setProfession(null); 
	          entityvillager.setMaster(this);
	          
	          //entityvillager.finalizeMobSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
	          this.world.spawnEntity(entityvillager);
			}
			
			coolDown = 6;
		}
	}

}
