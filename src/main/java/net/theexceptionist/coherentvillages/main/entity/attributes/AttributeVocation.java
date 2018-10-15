package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;

public class AttributeVocation {
	public final static int CLASS_VILLAGER = 0;
	
	public final static int SUBCLASS_FARMER = 0;
	public final static int SUBCLASS_CRAFTER = 1;
	public final static int SUBCLASS_WORKER = 2;
	public final static int SUBCLASS_MERCHANT = 3;
	public final static int SUBCLASS_TRAINER = 4;
	
	public final static int CLASS_SOLDIER = 1;
	public final static int CLASS_ARCHER = 2;
	public final static int CLASS_MAGE = 3;
	public final static int CLASS_ALCHEMIST = 4;
	
	public final static int CLASS_RULER = 5;
	public final static int CLASS_BANDIT = 6;
	public final static int CLASS_MERCENARY = 7;
	
	protected final int type;
	protected final int rank;
	protected final int upgradeReq;
	
	//can not ride by default
	protected boolean canRide = false;
	
	protected final String name;
	
	protected AttributeVocation upgradeLeft = null;
	protected AttributeVocation upgradeRight = null;
	
	protected final AttributeRace originRace;
	
	protected Item helmet = null;
	protected Item chestplate = null;
	protected Item leggings = null;
	protected Item boots = null;
	
	protected Item shield = null;
	protected Item meleeWeapon = null;
	protected Item rangedWeapon = null;

	protected boolean usesShield = false;
	protected final int ID;
	protected int subType;
	private int shieldChance = 0;

	private double damageOffset;

	private double detectOffest;

	private int KnockbackOffset;

	private double speedOffset;

	private double healthOffset;
	
	public static int END_ID = 0;
	
	private int genderOnly = -1;

	private int classOver = -1;
	//protected Spell[] spells
	//protected PotionType[] potions
	public static ArrayList<AttributeVocation> jobs = new ArrayList<AttributeVocation>();
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, final AttributeRace race)
	{
		this.name = name;
		this.type = type;
		this.rank = rank + 1;
		this.upgradeReq = upgradeReq;
		this.originRace = race;
		this.ID = END_ID++;
		this.setEquipment(null);
		
		this.jobs.add(this);
	}
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, final AttributeRace race, final boolean canRide, final boolean usesShield, final int shieldChance, Random rand)
	{
		this(name, type, rank, upgradeReq, race);
		this.canRide = canRide;
		this.usesShield = usesShield;
		this.shieldChance = shieldChance;
		this.setEquipment(rand);
	}
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, final AttributeRace race, final Item heldItem, final int subType)
	{
		this(name, type, rank, upgradeReq, race);
		this.meleeWeapon = heldItem;
		this.subType = subType;
	}
	
	public AttributeVocation(String string, int classBandit, int i, int j, AttributeRace attributeRace, boolean b,
			boolean c, int k, Random rand, int classOver) {
		this(string, classBandit, i, j, attributeRace, b, c, k, rand);
		this.classOver  = classOver;
		this.setEquipment(rand);
		//System.out.println(classOver);
	}

	public void setUpgradeTree(AttributeVocation left, AttributeVocation right)
	{
		this.upgradeLeft = left;
		this.upgradeRight = right;
	}

	public static AttributeVocation getVocationFromID(int ID)
	{
		return jobs.get(ID);
	}
	
	public int getSubType() {
		return subType;
	}
	
	public void setEquipment(Random rand)
	{
		int armorID = (rank - 1) * 4;
		
		int head = armorID;
		int chest = armorID + 1;
		int legs = armorID + 2;
		int feet = armorID + 3;
		
		int typeUsed = this.classOver == -1 ? this.type : this.classOver; 
		
		if(this.classOver != -1)
		{
			helmet = null;//this.originRace.armor.get(head);
			chestplate = null;//this.originRace.armor.get(chest);
			leggings = null;//this.originRace.armor.get(legs);
			boots = null;//this.originRace.armor.get(feet);
			
			meleeWeapon = null;//this.originRace.meleeWeapons.get(rank - 1);
			rangedWeapon = null;//this.originRace.meleeWeapons.get(rank - 1);
			shield = null;//this.originRace.shield
		}
		
		switch(typeUsed)
		{
			case CLASS_SOLDIER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				//Rank
				//System.out.println(this.getName()+" Uses Shield: "+usesShield);
				if(usesShield && rand != null && rand.nextInt(100) < shieldChance) 
				{
					shield = this.originRace.shield.get(0);
					//System.out.println("Shield: "+shield);
				}
			}
			break;
			case CLASS_ARCHER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//rangedWeapon = this.originRace.rangedWeapons.get(rank);
				rangedWeapon = this.originRace.rangedWeapons.get(0);
			}
			break;
			case CLASS_MAGE:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//spells = this.originRace.spells.get(rank);
			}
			break;
			case CLASS_ALCHEMIST:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//potions = this.originRace.potions.get(rank);
			}
			break;
			case CLASS_BANDIT:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				//Rank
				if(usesShield) shield = this.originRace.shield.get(0);
			}
			break;
			case CLASS_RULER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				//Rank
				if(usesShield) shield = this.originRace.shield.get(0);
			}
			break;
		}
		
		/*this.originRace.meleeWeapons
		this.originRace.shield
		this.originRace.rangedWeapons
		this.originRace.potions
		this.originRace.spells*/
	}
	
	
	
	
	public int getID() {
		return ID;
	}

	public boolean isCanRide() {
		return canRide;
	}

	public int getUpgradeReq() {
		return upgradeReq;
	}

	public int getType() {
		return type;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle()
	{
		return originRace.getName() + name;
	}
	
	public AttributeVocation getUpgradeRight() {
		return upgradeRight;
	}

	public void setUpgradeRight(AttributeVocation upgradeRight) {
		this.upgradeRight = upgradeRight;
	}

	public AttributeRace getOriginRace() {
		return originRace;
	}

	public AttributeVocation getUpgradeLeft() {
		return upgradeLeft;
	}

	public void setUpgradLeft(AttributeVocation upgradLeft) {
		this.upgradeLeft = upgradLeft;
	}

	public Item getHelmet() {
		return helmet;
	}
	
	public Item getChestplate() {
		return chestplate;
	}

	public Item getLeggings() {
		return leggings;
	}

	public Item getBoots() {
		return boots;
	}

	public Item getShield() {
		return shield;
	}

	public Item getWeapon() {
		if(meleeWeapon != null) return meleeWeapon;
		else if(rangedWeapon != null) return rangedWeapon;
		return null;
	}
	
	public Item getRangedWeapon() {
		return rangedWeapon;
	}

	public double getDamageOffest() {
		// TODO Auto-generated method stub
		return damageOffset;
	}

	public double getDetectOffest() {
		// TODO Auto-generated method stub
		return detectOffest;
	}

	public int getKnockBackOffest() {
		// TODO Auto-generated method stub
		return KnockbackOffset;
	}

	public double getSpeedOffest() {
		// TODO Auto-generated method stub
		return speedOffset;
	}

	public double getHealthOffest() {
		// TODO Auto-generated method stub
		return healthOffset;
	}
	
	public void setDamageOffest(int amount) {
		// TODO Auto-generated method stub
		damageOffset += amount;
	}

	public void setDetectOffest(int amount) {
		// TODO Auto-generated method stub
		detectOffest += amount;
	}

	public void setKnockBackOffest(int amount) {
		// TODO Auto-generated method stub
		KnockbackOffset += amount;
	}

	public void setSpeedOffest(int amount) {
		// TODO Auto-generated method stub
		speedOffset += amount;
	}

	public void setHealthOffest(int amount) {
		// TODO Auto-generated method stub
		healthOffset += amount;
	}

	public int getGenderOnly() {
		return genderOnly;
	}
}