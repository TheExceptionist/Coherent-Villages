package net.theexceptionist.coherentvillages.entity.knight;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithMagic;
import net.theexceptionist.coherentvillages.main.Main;

public class EntityVillagerMageKnight extends AbstractEntityKnight implements IRangedAttackMob {

	protected int coolDown = 0;
	protected int burstCount = 3;
	public EntityVillagerMageKnight(World worldIn) {
		super(worldIn);
		this.className = "Mage Knight";
		this.canSpawn = Main.villager_spawn.get(Main.Soldier.Mage_Knight.ordinal()).spawn;

		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	 protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	    {
	        super.setEquipmentBasedOnDifficulty(difficulty);
	        
	        //Main.logger.info("Gave Equipment");//, message, p0, p1, p2, p3, p4, p5, p6, p7);

			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
	    }
	
	protected void initEntityAI()
   {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackWithMagic(this, 1.0D, 20, 15.0F));
		
		//super.initEntityAI();
       //this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.6D, true));
   }
	
	 public void onLivingUpdate()
	    {
		 super.onLivingUpdate();
	        if(this.horse != null)
	        {
	        	//this.getNavigator();
	        	//getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	        	//this.horse.get.setSprinting(true);
	        	//0.8 trot
	        	//1.6 sprint
	        	//2.4 Running
	        	//this.getMoveHelper().setMoveTo(this.posX + 100, this.world.getTopSolidOrLiquidBlock(new BlockPos(this.posX + 100, 80, this.posZ)).getY(), this.posZ, 2.4D);
	        	//this.setMoveForward(1);
	        	//this.horse.setMoveForward(5);
		        //this.horse.travel(this.posX + 1, this.posY, forward);
		        /*this.horse.turn(yaw, pitch);
		        this.horse.setVelocity(x, y, z);
		        this.horse.move(type, x, y, z);
		        this.horse.setMoveForward(amount);
		        this.horse.setMoveVertical(amount)
		        this.horse.setMoveStrafe(amount);*/
	        }
	    }
	 
	
	
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        
	        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
	        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
	        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.8D);
	        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(20.0D);
	        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
	    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if(coolDown > 0)
		{
			coolDown--;
		}
		else
		{
		
		 double d1 = 4.0D;
         Vec3d vec3d = this.getLook(1.0F);
         EntityLivingBase entitylivingbase = this.getAttackTarget();
         
         double d2 = entitylivingbase.posX - (this.posX + vec3d.x * 4.0D);
         double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.posY + (double)(this.height / 2.0F));
         double d4 = entitylivingbase.posZ - (this.posZ + vec3d.z * 4.0D);
         
         this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
	       
         EntityFireball entitylargefireball = new EntitySmallFireball(world, this, d2, d3, d4);
         if(world.rand.nextInt() < 25) 
        	 {
        	 entitylargefireball = new EntityLargeFireball(world, this, d2, d3, d4);
        	 ((EntityLargeFireball)entitylargefireball).explosionPower = 0;
        	 }
         
         entitylargefireball.posX = this.posX + vec3d.x * 4.0D;
         entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
         entitylargefireball.posZ = this.posZ + vec3d.z * 4.0D;
         world.spawnEntity(entitylargefireball);
         
         burstCount--;
		}
		
		if(burstCount <= 0)
		{
			coolDown = 3;
			burstCount = 3;
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

}
