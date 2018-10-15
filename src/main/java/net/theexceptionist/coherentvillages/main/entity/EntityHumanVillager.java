package net.theexceptionist.coherentvillages.main.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerInteract;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.Village;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithBow;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackWithMagic;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIFollowEntity;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIHealAllies;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIHideFromHarm;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIRest;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIShareTarget;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIShareTargetPlayer;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIStayInBorders;
import net.theexceptionist.coherentvillages.entity.followers.EntitySkeletonMinion;
import net.theexceptionist.coherentvillages.entity.followers.IEntityFollower;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.NameGenerator;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;

public class EntityHumanVillager extends EntityVillager implements IEntityFollower, IEntityVillager, IRangedAttackMob{
	protected String firstName, lastName;
	protected int gender = -1;
	protected AttributeRace race;
	protected AttributeFaction faction;
	protected AttributeVocation vocation;
	protected Village village;
	
	private EntityAIAttackMelee melee;
	private EntityAIAttackRanged ranged;
	private EntityAIAttackWithBow rangedTrained;
	
	private int homeCheckTimer = 0;
	private EntityAIHealAllies heal;
	private EntityAIAttackRanged potions;
	private EntityAIAttackWithMagic spells;
	private int swingProgressTicks;
	private boolean isSwinging = false;
	private int kills = 0;
	private EntityAIHarvestFarmland farm;
	private int supply = 5;
	private int vassalsKilled = 0;
	private ArrayList<EntityHumanVillager> vassals;
	//private EntityHumanVillager liege = null;
	private boolean spawnAttempt = false;
	
	public final static int GENDER_MALE = 0;
	public final static int GENDER_FEMALE = 1;
	private boolean ruler = false;
	
	protected boolean shifter = false;
	protected EntityLiving creatureShiftTo = null;
	private EntityLivingBase master = null;
	private boolean dropLoot = true;
	
	public static int END_ID = 0;
	
	private static final DataParameter<Integer> PROFESSION = EntityDataManager.<Integer>createKey(EntityHumanVillager.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> RACE = EntityDataManager.<Integer>createKey(EntityHumanVillager.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityHumanVillager.class, DataSerializers.VARINT);
	private static final DataParameter<String> FIRST_NAME = EntityDataManager.<String>createKey(EntityHumanVillager.class, DataSerializers.STRING);
	private static final DataParameter<String> LAST_NAME = EntityDataManager.<String>createKey(EntityHumanVillager.class, DataSerializers.STRING);

	public EntityHumanVillager(World worldIn) {
		super(worldIn);
		//System.out.println("Reinit"); name = new ();
		//worldIn.getVillageCollection().getNearestVillage(doorBlock, radius);
	}
	
	public EntityHumanVillager(World worldIn, final int ID)
	{
		super(worldIn);
		
		this.race = AttributeRace.getFromIDRace((ID/2) % AttributeRace.races.size());
		this.gender = (ID/2) % (GENDER_FEMALE + 1);
		
		this.initEntityClass();
		this.initEntityCharacteristics();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
	}
	
	public EntityHumanVillager(World worldIn, final int ID, final int vocation, final int rank, final int gender)
	{
		super(worldIn);
		
		this.race = AttributeRace.getFromIDRace(ID);
		this.gender = gender;
		this.initVocation(vocation, rank);
		this.initEntityCharacteristics();
		
		//this.setSkin();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
		
		//if(this.liege != null) System.out.println(this.getTitle()+" Ruler: "+this.liege.getTitle());
		//else System.out.println("None");
		//System.out.println(this.getTitle()+" Gender: "+gender);
	}
	
	public EntityHumanVillager(World worldIn, final int ID, final AttributeVocation vocation, final int gender, boolean shifter, EntityLiving creature)
	{
		super(worldIn);
		
		this.race = AttributeRace.getFromIDRace(ID);
		this.gender = gender;
		this.vocation = vocation;
		this.shifter = shifter;
		this.creatureShiftTo = creature;
		
		this.initEntityCharacteristics();
		
		//this.setSkin();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
		
		//if(this.liege != null) System.out.println(this.getTitle()+" Ruler: "+this.liege.getTitle());
		//else System.out.println("None");
		//System.out.println(this.getTitle()+" Gender: "+gender);
	}
	
	public EntityHumanVillager(World worldIn, final int ID, final AttributeVocation vocation, final int gender, final String firstName, final String lastName)
	{
		super(worldIn);
		
		this.race = AttributeRace.getFromIDRace(ID);
		this.gender = gender;
		this.vocation = vocation;
		//this.initEntityCharacteristics();
		this.firstName = firstName;
		this.lastName = lastName;
		
		//this.setSkin();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
		//System.out.println(this.getTitle()+" Gender: "+gender);
	}

	public EntityHumanVillager(World worldIn, final int ID, final AttributeVocation vocation, final int gender, final boolean ruler)
	{
		super(worldIn);
		this.race = AttributeRace.getFromIDRace(ID);
		this.gender = gender;
		this.vocation = vocation;
		this.ruler = ruler;
		
		//this.setSkin();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		this.initEntityCharacteristics();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
		//System.out.println(this.getTitle()+" Gender: "+gender);
	}
	
	public void reInit(final int ID, final AttributeVocation vocation, final int gender, final String firstName, final String lastName)
	{
		this.race = AttributeRace.getFromIDRace(ID);
		this.gender = gender;
		this.vocation = vocation;
		//this.initEntityCharacteristics();
		this.firstName = firstName;
		this.lastName = lastName;
		
		//this.setSkin();
		this.initEntityAI();
		this.applyEquipment();
		this.applyAttributes();
		
		this.initEntityName();
		this.initValues();
		this.setCombatTask();
		//System.out.println(this.getTitle()+" FirstName: "+this.firstName);
	}
	
	public void transform()
	{
		EntityLiving mob = getShifter();
		
		if(mob != null)
		{
			EntityLiving entityShifter = mob;                           
        	entityShifter.setLocationAndAngles((double)this.posX + 0.5D, (double)this.posY, (double)this.posZ + 0.5D, 0.0F, 0.0F);
            //entityShifter.setProfession(this.chooseForgeProfession(i, entityShifter.getProfessionForge()));
            //entityShifter.finalizeMobSpawn(world.getDifficultyForLocation(new BlockPos(entityShifter)), (IEntityLivingData)null, false);
            world.spawnEntity(entityShifter);
            this.setDead();
		}
	}
	
	public EntityLiving getShifter() {
		if(isShifter())return this.creatureShiftTo;
		else return null;
	}
	
	public boolean isShifter() {
		return shifter;
	}

	public void setShifter(boolean shifter, EntityLiving creature) {
		this.shifter = shifter;
		this.creatureShiftTo = creature;
	}

	@Override
	public void setDead()
	{
		//if(liege != null && this.vocation.getType() == AttributeVocation.CLASS_SOLDIER)
		//{
		//	liege.manKilled();
		//}
		this.spawnExplosionParticle();
		super.setDead();
	}
	
	public void manKilled() {
		//this.vassalsKilled += 1;
		//if(this.liege.)System.out.println(liege.getName()+" | Killed: "+this.vassalsKilled);
	}
	
	public static int getRandomGender(World worldIn)
	{
		return worldIn.rand.nextInt(100) < 45 ? EntityHumanVillager.GENDER_MALE : EntityHumanVillager.GENDER_FEMALE;
	}
	
	private void initValues() {
		if(!this.world.isRemote)
		{
			if(getRaceFromManager() == -1)
			{
				setRace(this.race);
			}
			if(getGender() == -1)
			{
				setGender(gender);
			}
			if(getProfessionID() == -1)
			{
				setProfessionID(this.vocation);
			}
			if(getLastNameFromManager() == "")
			{
				setLastNameFromManager(this.lastName);
			}
			if(getNameFromManager() == "")
			{
				setNameFromManager(this.firstName);
			}
		}
	}
	
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		if(this.vocation != null && this.vocation.getType() == AttributeVocation.CLASS_MERCENARY )
		{	
			ItemStack item = player.getHeldItem(hand);
			
			if(item.getItem() == Items.EMERALD && this.master == null)
			{
				Style style2 = new Style();
				style2.setColor(TextFormatting.GREEN);
				
				ITextComponent itextcomponent1 = new TextComponentString(
						player.getDisplayNameString()+", sure I "+this.firstName+" will follow you.");
				itextcomponent1.setStyle(style2);
				player.sendMessage(itextcomponent1);
				//player.setHeldItem(hand, null);
				this.setMaster(player);
			}
			else
			{
				return false;
			}
			
			return false;
		}
		else if(this.vocation != null && this.vocation.getType() == AttributeVocation.CLASS_BANDIT)
		{
			return false;
		}
		else
		{
			return super.processInteract(player, hand);
		}
	}

	public void onLivingUpdate()
    {
		super.onLivingUpdate();
		
		//this.vassalsKilled++;
		//System.out.print("Ruler"+vassalsKilled);
		
		if(this.vocation != null && this.kills > this.vocation.getUpgradeReq() && !this.ruler)
		{
			this.upgrade();
		}
		else if(this.ruler && this.kills > 5 && this.world.isDaytime())
		{
			this.spawnRecruits(world.rand.nextInt(this.kills + 1));
			this.kills = 0;
		}
		
		if(this.shifter && this.getHealth() < this.getMaxHealth()/2)
		{
			this.transform();
		}
		
		/*if(this.vassalsKilled > 5 && this.world.isDaytime() && !this.spawnAttempt)
		{
			this.spawnRecruits(world.rand.nextInt(this.vassalsKilled + 1));
			this.vassalsKilled = 0;
			this.spawnAttempt = true;
		}
		else if(this.world.isDaytime() && this.spawnAttempt)
		{
			this.spawnAttempt = false;
		}*/
    }
	
	public void upgrade() {
		if(this.world.isRemote) return;
		
		AttributeVocation upgrade = null;
		if(this.vocation.getUpgradeLeft() != null && this.vocation.getUpgradeRight() != null) upgrade = world.rand.nextInt(50) < 100 ? this.vocation.getUpgradeLeft() : this.vocation.getUpgradeRight();
		else if(this.vocation.getUpgradeLeft() != null) upgrade = this.vocation.getUpgradeLeft();
		else if(this.vocation.getUpgradeRight() != null) upgrade = this.vocation.getUpgradeRight();

		if(upgrade != null)
		{
			//System.out.println(this.vocation.getName());
			EntityHumanVillager entityHumanVillager = new EntityHumanVillager(world, race.getID(), upgrade, gender, firstName, lastName);                            
        	entityHumanVillager.setLocationAndAngles((double)this.posX + 0.5D, (double)this.posY, (double)this.posZ + 0.5D, 0.0F, 0.0F);
            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
            //entityHumanVillager.finalizeMobSpawn(world.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
            world.spawnEntity(entityHumanVillager);
            this.setDead();
		}
	}
	
	protected void spawnRecruits(int num)
	{
		for(int i = 0; i < num; i++)
		{
			EntityHumanVillager entityHumanVillager = new EntityHumanVillager(world, race.getID(), race.getRecruitVocation(AttributeVocation.CLASS_SOLDIER), gender, firstName, lastName);                            
	    	entityHumanVillager.setLocationAndAngles((double)this.posX + 0.5D, (double)this.posY, (double)this.posZ + 0.5D, 0.0F, 0.0F);
	        //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
	        //entityHumanVillager.finalizeMobSpawn(world.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
	        world.spawnEntity(entityHumanVillager);
		}
	}

	protected void entityInit()
    {
        super.entityInit();
		dataManager.register(RACE, -1);
		dataManager.register(GENDER, -1);
		dataManager.register(PROFESSION, -1);
		dataManager.register(FIRST_NAME, "");
		dataManager.register(LAST_NAME, "");
    }
	
	public void setLastNameFromManager(String name)
	{
		dataManager.set(LAST_NAME, name);
	}

	public String getLastNameFromManager()
	{
		return dataManager.get(LAST_NAME).toString();
	}
	
	public void setNameFromManager(String name)
	{
		dataManager.set(FIRST_NAME, name);
	}

	public String getNameFromManager()
	{
		return dataManager.get(FIRST_NAME).toString();
	}
	
	public void setRace(AttributeRace num)
	{
		dataManager.set(RACE, num.getID());
	}

	public void setGender(int num)
	{
		dataManager.set(GENDER, num);
	}
	
	public void setProfessionID(AttributeVocation num)
	{
		dataManager.set(PROFESSION, num.getID());
	}

	public int getRaceFromManager()
	{
		return dataManager.get(RACE).intValue();
	}

	public int getGender()
	{
		return dataManager.get(GENDER).intValue();
	}
	
	public int getProfessionID()
	{
		return dataManager.get(PROFESSION).intValue();
	}
	
	protected void applyAttributes()
    {
		if(this.world.isRemote) return;
		int healthBonus = race.getHealthBonus();
		int attackBonus = race.getAttackBonus();
		int detectBonus = race.getDetectBonus();
		int speedBonus = race.getSpeedBonus();
		int knockbackBonus = race.getKnockbackBonus();
		
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D + (healthBonus * vocation.getRank()) + this.vocation.getHealthOffest());
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D + ((speedBonus * vocation.getRank())/20) + this.vocation.getSpeedOffest());
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue((knockbackBonus * vocation.getRank() + this.vocation.getKnockBackOffest())/10);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(32.0D);
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D + (detectBonus * vocation.getRank()) + this.vocation.getDetectOffest());
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D + (attackBonus * vocation.getRank()) + this.vocation.getDamageOffest());
        
        this.setHealth((float) getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue());
    }
	
	public boolean attackEntityAsMob(Entity entityIn)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        
        if (flag)
        {
            if (i > 0 && entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
                this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, this.getSoundCategory(), 1.0F, 1.0F);
                ((WorldServer)this.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, entityIn.posX, entityIn.posY + (double)(entityIn.height * 0.5F), entityIn.posZ, (int)f, 0.1D, 0.0D, 0.1D, 0.2D);
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
	
	protected void updateAITasks()
    {
	
	 super.updateAITasks();
	 
	 if (--this.homeCheckTimer  <= 0)
        {
            this.homeCheckTimer = 70 + this.rand.nextInt(50);
            this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this), 32);

            if (this.village == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos blockpos = this.village.getCenter();
                this.setHomePosAndDistance(blockpos, (int)((float)this.village.getVillageRadius() * 0.6F));
            }
        }
	
	 if((this.getAttackTarget() != null || this.getAttackingEntity() != null) && !this.isSprinting())
	 {
		 this.setSprinting(true);
		 //System.out.println("Sprinting! To: "+(this.getAttackTarget() != null || this.getAttackingEntity() != null));
	 }
	 else if (this.isSprinting())
	 {
		 this.setSprinting(false);
		 //System.out.println("Not Sprinting");
	 }
    }
	
	public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.melee);
            this.tasks.removeTask(this.ranged);
            
            if(this.vocation.getType() != AttributeVocation.CLASS_VILLAGER)
            {
                for(Object task : this.tasks.taskEntries.toArray())
        		{
        			 EntityAIBase ai = ((EntityAITaskEntry) task).action;
        			 if(ai instanceof EntityAIHarvestFarmland)
        				 this.tasks.removeTask(ai);	
        			 
        		}
            }
            	
            ItemStack itemstack = this.getHeldItemMainhand();
            
            //System.out.println("Executing");
            //if(itemstack != null)System.out.println("Working! "+itemstack.getDisplayName());

            if (itemstack.getItem() == Items.BOW)
            {
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD)
                {
                    i = 40;
                }

                //this.ranged.setAttackCooldown(i);
                if(this.vocation.getType() != AttributeVocation.CLASS_ARCHER) this.tasks.addTask(1, this.ranged);
                else this.tasks.addTask(1, this.ranged);
            }
            else
            {
                this.tasks.addTask(1, this.melee);
                //System.out.println("Melee!");
            }
        }
    }
 

	
	@Override
	protected void initEntityAI()
	{
		if(vocation == null || this.world.isRemote) return;
		//this.getVillage();
		melee = new EntityAIAttackMelee(this, 1.0D, true);
		ranged = new EntityAIAttackRanged(this, 1.0D, 60,  (float)getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue() + 16.0F);
		rangedTrained = new EntityAIAttackWithBow(this, 1.0D, 60, 10.0F);
		//heal = new EntityAIHealAllies(this, 1.0D, 60, 10.0F, EntityVillager.class);
        potions = new EntityAIAttackRanged(this, 1.0D, 60, 10.0F);
        spells =  new  EntityAIAttackWithMagic(this, 1.0D, 20, 15.0F);
        farm = new EntityAIHarvestFarmland(this, 0.6D);
        

		
		switch(vocation.getType())
		{
			case AttributeVocation.CLASS_SOLDIER:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		       // this.tasks.addTask(1, melee);
		        this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_ARCHER:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		        this.tasks.addTask(3, new EntityAIRest(this, true));
		     //   this.tasks.addTask(1, ranged);
		        this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		      //  this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_MAGE:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		    //    this.tasks.addTask(1, spells);
		        this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_ALCHEMIST:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		        //this.tasks.addTask(1, potions);
		       // this.tasks.addTask(2, heal);
		        this.tasks.addTask(3, new EntityAIStayInBorders(this, 1.0D));
		        this.tasks.addTask(4, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(5, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        this.tasks.addTask(8, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(9, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(12, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_VILLAGER:
			{
				int subType = vocation.getSubType();
				
				this.tasks.addTask(0, new EntityAISwimming(this));
		     //   this.tasks.addTask(1, melee);
		        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
		        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEvoker.class, 12.0F, 0.8D, 0.8D));
		        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVindicator.class, 8.0F, 0.8D, 0.8D));
		        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVex.class, 8.0F, 0.6D, 0.6D));
		        this.tasks.addTask(1, new EntityAITradePlayer(this));
		        this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
		        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		       // this.tasks.addTask(6, new EntityAIVillagerMate(this));
		        this.tasks.addTask(7, new EntityAIFollowGolem(this));
		        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		        this.tasks.addTask(9, new EntityAIVillagerInteract(this));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
		        
		        switch(subType)
		        {
			        case AttributeVocation.SUBCLASS_FARMER:
			        {
			        	this.tasks.addTask(6, farm);
			        }
			        break;
			        case AttributeVocation.SUBCLASS_CRAFTER:
			        {
			        	
			        }
			        break;
			        case AttributeVocation.SUBCLASS_MERCHANT:
			        {
			        	
			        }
			        break;
			        case AttributeVocation.SUBCLASS_TRAINER:
			        {
			        	
			        }
			        break;
			        case AttributeVocation.SUBCLASS_WORKER:
			        {
			        	
			        }
			        break;
		        }
			}
			break;
			case AttributeVocation.CLASS_BANDIT:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		       // this.tasks.addTask(1, melee);
		        this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
				//this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
				this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGolem.class, true));
		        
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
		            	
		        		return p_apply_1_ != null && (IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper)) && !(p_apply_1_ instanceof EntityWarg);
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_RULER:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		       // this.tasks.addTask(1, melee);
		        this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        //his.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
			case AttributeVocation.CLASS_MERCENARY:
			{
				this.tasks.addTask(0, new EntityAISwimming(this));
		       // this.tasks.addTask(1, melee);
		        //this.tasks.addTask(2, new EntityAIStayInBorders(this, 1.0D));
		        //this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
		        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
		        this.tasks.addTask(6, new EntityAIRest(this, true));
		        //this.tasks.addTask(7, new EntityAIHideFromHarm(this));
		        this.tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
		        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		        this.tasks.addTask(11, new EntityAILookIdle(this));
		        
		        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, true, true, new Predicate<EntityLiving>()
		        {
		            public boolean apply(@Nullable EntityLiving p_apply_1_)
		            {
		        		return p_apply_1_ != null && ((IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityTameable) && !(p_apply_1_ instanceof EntitySkeletonMinion)) || (p_apply_1_ instanceof EntityWarg));
		            }
		        }));
		        this.targetTasks.addTask(1, new EntityAIAttackBackExclude(this, true, new Class[0])); 
			}
			break;
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		//if(this.world.isRemote) return;
		//System.out.println("Name: "+this.getTitle()+"Vocation: "+compound.getInteger("Profession")+" | Name: "+AttributeVocation.getVocationFromID(compound.getInteger("Profession")).getName());
		AttributeVocation vocation = AttributeVocation.getVocationFromID(compound.getInteger("Profession"));
        AttributeRace race = AttributeRace.getFromIDRace(compound.getInteger("Race"));
        int gender = compound.getInteger("Gender");
        String name1 = compound.getString("First Name");
        String name2 = compound.getString("Last Name");
        
        this.reInit(race.getID(), vocation, gender, name1, name2);
        
        this.kills = compound.getInteger("Kills");
        int vassals = compound.getInteger("Vassals Killed");
        if(vassals >= this.vassalsKilled) this.vassalsKilled = vassals;

        /*NBTTagList nbttaglist = compound.getTagList("Equipment", 10);
        
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            ItemStack itemstack = new ItemStack(nbttaglist.getCompoundTagAt(i));

            if(itemstack.getItem() instanceof ItemSword || itemstack.getItem() instanceof ItemAxe || itemstack.getItem() instanceof ItemSpade|| itemstack.getItem() instanceof ItemHoe)
            {
            	 this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
            else if(itemstack.getItem().isValidArmor(itemstack, EntityEquipmentSlot.HEAD, this))
            {
    			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
            else if(itemstack.getItem().isValidArmor(itemstack, EntityEquipmentSlot.CHEST, this))
            {
    			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
            else if(itemstack.getItem().isValidArmor(itemstack, EntityEquipmentSlot.LEGS, this))
            {
    			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
            else if(itemstack.getItem().isValidArmor(itemstack, EntityEquipmentSlot.FEET, this))
            {
    			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
        }*/

        //this.isDirty = true;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		if(this.world.isRemote) return;
		//System.out.println("Name: "+this.getTitle()+"Vocation: "+AttributeVocation.getVocationFromID(this.getProfessionID()).getID()+" | Name: "+AttributeVocation.getVocationFromID(this.getProfessionID()).getName());
        compound.setInteger("Profession", this.getProfessionID());
        compound.setInteger("Race", this.getRaceFromManager());
        compound.setInteger("Gender", this.getGender());
        compound.setInteger("Kills", this.kills);
        compound.setInteger("Vassals Killed", this.vassalsKilled);
        compound.setString("First Name", this.getNameFromManager());
        compound.setString("Last Name", this.getLastNameFromManager());
        //this.setCustomNameTag(this.getTitle());
        /*compound.setInteger("Career", this.careerId);
        compound.setInteger("CareerLevel", this.careerLevel);
        compound.setBoolean("Willing", this.isWillingToMate);*/

        /*if (this.buyingList != null)
        {
            compound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }*/

        NBTTagList nbttaglist = new NBTTagList();
        
        nbttaglist.appendTag(this.getHeldItemMainhand().writeToNBT(new NBTTagCompound()));
        Iterator<ItemStack> equip = this.getEquipmentAndArmor().iterator();

       while(equip.hasNext())
       {
    	   ItemStack item = equip.next();
    	   nbttaglist.appendTag(item.writeToNBT(new NBTTagCompound()));
    	//   System.out.println(this.getName()+" equip: - "+item.getDisplayName());
       }

        compound.setTag("Equipment", nbttaglist);

        //compound.setTag("Inventory", nbttaglist);
	}
	
	public void initVocation(int type, int id)
	{
		switch(type)
		{
			case AttributeVocation.CLASS_SOLDIER:
			{
				this.vocation = race.getVocation(type, id);
			}
			break;
			case AttributeVocation.CLASS_ARCHER:
			{
				this.vocation = race.getVocation(type, id);
			}
			break;
			case AttributeVocation.CLASS_MAGE:
			{
				this.vocation = race.getVocation(type, id);
			}
			break;
			case AttributeVocation.CLASS_ALCHEMIST:
			{
				this.vocation = race.getVocation(type, id);
			}
			break;
			case AttributeVocation.CLASS_VILLAGER:
			{
				this.vocation = race.getVocation(type, id);
			}
			break;
		}
		
		//System.out.println(this.toString()+" "+this.vocation.getName());
	}
	
	protected void initEntityCharacteristics() {
		if(!world.isRemote)
		{
			this.generateName(rand);
		}
	}
	
	protected void initEntityName()
	{
		if(this.isShifter())
		{ 
			this.setCustomNameTag("Nervous "+this.getTitle());
			//System.out.println(this.getTitle());
		}
		else
		{
			this.setCustomNameTag(this.getTitle());
		}
		
		this.setAlwaysRenderNameTag(Main.useNametags);
	}
	
	
	protected void applyNewAI()
	{
		
	}
	
	protected void applyEquipment()
	{
		if(vocation == null || this.world.isRemote) return;
		
		//System.out.println(this.toString()+" "+this.vocation.getName()+" "+vocation.getChestplate());
		//System.out.println(this.toString()+" "+this.vocation.getName()+" "+this.race.getName());
		
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(vocation.getWeapon()));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(vocation.getShield()));
		
		//System.out.println(vocation.getName()+" Has Shield "+vocation.getShield());
		
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(vocation.getHelmet()));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(vocation.getChestplate()));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(vocation.getLeggings()));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(vocation.getBoots()));
		
		//System.out.println(this.getHeldItem(EnumHand.MAIN_HAND).getDisplayName()+" "+vocation.getWeapon().getUnlocalizedName());
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }
	
	private void initEntityClass() 
	{
		//if(this.world.isRemote) return;
		this.vocation = race.getRecruitVocation(AttributeVocation.CLASS_SOLDIER);		

		//this.setSkin();
		//System.out.println("Working");
	}
	
	/*private void setSkin()
	{
		if(this.gender == GENDER_MALE) skin = race.getRandomSkinM(this.vocation);
		else skin = race.getRandomSkinF(this.vocation);
	}*/
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if (this.world != null && !this.world.isRemote)
        {
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack.getItem() == Items.BOW)
            {
                this.attackWithBow(target, distanceFactor);
            }
        }
		
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaster(EntityLivingBase player) {
		// TODO Auto-generated method stub
		this.master = player;
		this.tasks.addTask(3, new EntityAIFollowEntity(this, player, true));	
		this.targetTasks.addTask(0, new EntityAIShareTargetPlayer(this, player, false));
		//System.out.println("Setting master!");
	}

	@Override
	public boolean isShouldFollow() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    protected void generateName(Random rand)
    {
    	this.firstName = NameGenerator.generateRandomName(rand, race, gender);
    	this.lastName = NameGenerator.generateRandomName(rand, race);
    }
    
    protected String getTitle()
    {
    	return this.firstName+" - "+this.race.getName()+" "+this.vocation.getName();
    }
    
    public String getFullName()
    {
    	return this.firstName+" "+this.lastName;
    }
    
    public AttributeVocation getVocation()
    {
    	return this.vocation;
    }
    
    public AttributeRace getRace()
    {
    	return this.race;
    }
	
	public EntityVillager getLiving()
	{
		return (EntityVillager)this;
	}
	
	public Village getVillage() {
		// TODO Auto-generated method stub
		return this.village;
	}
	
	protected void damageEntity(DamageSource damageSrc, float damageAmount)
    {
		DamageSource newSrc = handleInWall(damageSrc);
		float damage = damageAmount;
		if(newSrc == null) return;
		//Placeholder until I figure the shield thing out
		if(newSrc.getImmediateSource() instanceof EntityArrow) damage /= 2; 
		super.damageEntity(newSrc, damage);
    }
	
	public DamageSource handleInWall(DamageSource source)
	{
		if(source.damageType.contains("inWall"))
		{
			this.posX += 1;
			this.posY += 1;
			this.posZ += 1;
			return null;
		}
		else
		{
			return source;
		}
	}
	
	protected void attackWithBow(EntityLivingBase target,
			float distanceFactor)
	{
		EntityArrow entityarrow = this.getArrow(distanceFactor);
		 entityarrow.setDamage((float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		
       double d0 = target.posX - this.posX;
       double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
       double d2 = target.posZ - this.posZ;
       double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
       entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
      
       this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
       this.world.spawnEntity(entityarrow);
	}
	
	protected EntityArrow getArrow(float p_190726_1_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
	}

	public ResourceLocation getVillagerSkin() {
		return this.getSkin(this.getProfessionID(), this.getRaceFromManager(), this.getGender());
	}

	private ResourceLocation getSkin(int profession2, int race2, int gender2) {
		AttributeRace tempRace = AttributeRace.getFromIDRace(race2);
		ResourceLocation skin = null;
		
		if(gender2 == this.GENDER_MALE) skin = tempRace.getRandomSkinM(profession2);
		else skin = tempRace.getRandomSkinF(profession2);	
		
		//System.out.println("Vocation: "+AttributeVocation.getVocationFromID(profession2).getName()+" Race: "+tempRace.getName()+" Gender: "+gender2);
		
		return skin;
	}

	public void addKills(int i) {
		// TODO Auto-generated method stub
		this.kills  += i;
	}

	public int getSupply() {
		// TODO Auto-generated method stub
		return supply;
	}

	public void reSupply(int i) {
		// TODO Auto-generated method stub
		supply += i;
	}

	public int getKills() {
		// TODO Auto-generated method stub
		return kills;
	}

	public int getVassalsKilled() {
		return vassalsKilled;
	}

	public void setVassalsKilled(int vassalsKilled) {
		this.vassalsKilled = vassalsKilled;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	
	 protected SoundEvent getAmbientSound()
	    {
	        return null;
	    }

	    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	    {
	        return SoundEvents.ENTITY_PLAYER_HURT;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_PLAYER_DEATH;
	    }
	
	protected boolean canDropLoot()
    {
        return dropLoot ;
    }

}