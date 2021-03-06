package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionType;
import net.theexceptionist.coherentvillages.main.entity.spells.Spell;
import net.theexceptionist.coherentvillages.main.items.ItemWeaponThrowable;

public class AttributeVocation {
	public final static int CLASS_VILLAGER = 0;
	
	public final static int SUBCLASS_FARMER = 0;
	public final static int SUBCLASS_CRAFTER = 1;
	public final static int SUBCLASS_WORKER = 2;
	public final static int SUBCLASS_MERCHANT = 3;
	public final static int SUBCLASS_TRAINER = 4;
	public final static int SUBCLASS_HUNTER = 5;
	
	public final static int CLASS_SOLDIER = 1;
	public final static int CLASS_ARCHER = 2;
	public final static int CLASS_MAGE = 3;
	public final static int CLASS_ALCHEMIST = 4;
	
	public final static int CLASS_RULER = 5;
	public final static int CLASS_BANDIT = 6;
	public final static int CLASS_MERCENARY = 7;
	public final static int CLASS_OVERRIDE_NO_ARMOR = 8;
	
	public final static int CLASS_HYBRID_MAGE_SOLDER = 9;
	public final static int CLASS_HYBRID_MAGE_ARCHER = 10;
	public final static int CLASS_HYBRID_ARCHER_SOLDIER = 11;
	
	public final static int NUM_CLASSES = 12;
	
	protected final int type;
	protected final int rank;
	protected final int upgradeReq;
	
	//can not ride by default
	protected boolean canRide = false;
	
	protected final String name;
	protected boolean alwaysBlock = false;
	
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
	protected Item horseArmor = null;
	
	protected ItemWeaponThrowable thrown = null;
	
	protected Enchantment[] swordEnchantments = new Enchantment[2];
	public static final int SWORD_ENCHANT_1 = 0;
	public static final int SWORD_ENCHANT_2 = 1;
	
	protected Enchantment[] bowEnchantments = new Enchantment[2];
	public static final int BOW_ENCHANT_1 = 0;
	public static final int BOW_ENCHANT_2 = 1;
	
	protected Enchantment[] headEnchantments = new Enchantment[2];
	public static final int HEAD_ENCHANT_1 = 0;
	public static final int HEAD_ENCHANT_2 = 1;
	
	protected Enchantment[] chestEnchantments = new Enchantment[2];
	public static final int CHEST_ENCHANT_1 = 0;
	public static final int CHEST_ENCHANT_2 = 1;
	
	protected Enchantment[] legsEnchantments = new Enchantment[2];
	public static final int LEGS_ENCHANT_1 = 0;
	public static final int LEGS_ENCHANT_2 = 1;
	
	protected Enchantment[] bootsEnchantments = new Enchantment[2];
	public static final int BOOTS_ENCHANT_1 = 0;
	public static final int BOOTS_ENCHANT_2 = 1;
	
	protected PotionType[] arrowPotions = new PotionType[2];
	public static final int PA_SLOT_PASSIVE_1 = 0;
	public static final int PA_SLOT_PASSIVE_2 = 1;

	//4 potion slots max
	protected PotionType[] potions = new PotionType[ALL_POTION_SLOTS];
	public static final int POTION_SLOT_PASSIVE_1 = 0;
	public static final int POTION_SLOT_PASSIVE_2 = 1;
	public static final int POTION_SLOT_PASSIVE_3 = 2;
	public static final int POTION_SLOT_PASSIVE_4 = 3;
	public static final int POTION_SLOT_ACTIVE_1 = 4;
	public static final int POTION_SLOT_ACTIVE_2 = 5;
	public static final int POTION_SLOT_ACTIVE_3 = 6;
	public static final int POTION_SLOT_ACTIVE_4 = 7;
	
	public static final int POTION_SLOT_PASSIVE_5 = 8;
	public static final int POTION_SLOT_PASSIVE_6 = 9;
	public static final int POTION_SLOT_PASSIVE_7 = 10;
	public static final int POTION_SLOT_PASSIVE_8 = 11;
	public static final int POTION_SLOT_ACTIVE_5 = 12;
	public static final int POTION_SLOT_ACTIVE_6 = 13;
	public static final int POTION_SLOT_ACTIVE_7 = 14;
	public static final int POTION_SLOT_ACTIVE_8 = 15;
	public static final int ALL_POTION_SLOTS = POTION_SLOT_ACTIVE_8 + 1;

	
	protected Spell[] spells = new Spell[ALL_SPELL_SLOTS];
	public static final int SPELL_SLOT_PASSIVE_1 = 0;
	public static final int SPELL_SLOT_PASSIVE_2 = 1;
	public static final int SPELL_SLOT_ACTIVE_1 = 2;
	public static final int SPELL_SLOT_ACTIVE_2 = 3;
	
	public static final int SPELL_SLOT_PASSIVE_3 = 4;
	public static final int SPELL_SLOT_PASSIVE_4 = 5;
	public static final int SPELL_SLOT_ACTIVE_3 = 6;
	public static final int SPELL_SLOT_ACTIVE_4 = 7;
	public static final int ALL_SPELL_SLOTS = SPELL_SLOT_ACTIVE_4 + 1;
	
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

	private int potionChance = 25;

	private boolean canHeal = false;

	private int horseArmorChance = 0;

	private Random rand = null;

	private boolean alwaysHorse = false;

	private int horseChance = 5;

	private float scale = 1;

	private boolean breakBlocks = false;

	private Block targetBlock = null;

	private boolean usesMagicSlot = false;
	private boolean usesPotionSlots = false;
	private boolean usesLingering = false;
	private boolean usesPoisonedArrows = false;
	private int poisonedArrowsChance = 0;

	private int armorOffset = 0;
	private int enchantmentChance = 2;
	//protected Spell[] spells
	//protected PotionType[] potions
	public static ArrayList<AttributeVocation> jobs = new ArrayList<AttributeVocation>();
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, final AttributeRace race)
	{
		this.name = name;
		this.type = type;
		this.rank = rank;
		this.upgradeReq = upgradeReq;
		this.originRace = race;
		this.ID = END_ID++;
		this.rand = new Random();
		this.setEquipment();
		
		this.jobs.add(this);
	}
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, 
			final AttributeRace race, final boolean canRide, final boolean usesShield, final int shieldChance, 
			final int potionChance, final int armorChance, final int enchantChance, final boolean alwaysBlock, Random rand)
	{
		this(name, type, rank, upgradeReq, race);
		this.canRide = canRide;
		this.enchantmentChance = enchantChance;
		//System.out.println(name+" : "+canRide);
		this.usesShield = usesShield;
		this.shieldChance = shieldChance;
		this.potionChance = potionChance;
		this.horseArmorChance  = armorChance;
		this.alwaysBlock = alwaysBlock;
		this.rand = rand;
		this.setEquipment();
	}
	
	public AttributeVocation(final String name, final int type, final int rank, final int upgradeReq, 
			final AttributeRace race, final Item heldItem, final int subType)
	{
		this(name, type, rank, upgradeReq, race);
		this.meleeWeapon = heldItem;
		this.subType = subType;
		this.potions[POTION_SLOT_PASSIVE_1] = race.potions.get(POTION_SLOT_PASSIVE_1);
	}
	
	public AttributeVocation(String name, int type, int rank, int upgradeReq, AttributeRace attributeRace, 
			final boolean canRide, final boolean usesShield, final int shieldChance, 
			final int potionChance, final int armorChance, final int enchantChance, final boolean alwaysBlock, Random rand, int classOver) {
		this(name, type, rank, upgradeReq, attributeRace, canRide, usesShield, shieldChance, potionChance, armorChance, enchantChance, alwaysBlock, rand);
		this.classOver  = classOver;
		this.rand = rand;
		this.setEquipment();
		//System.out.println(classOver);
	}

	public AttributeVocation(String name, int type, int rank, int upgradeReq, AttributeRace attributeRace, 
			final boolean canRide, final boolean usesShield, final int shieldChance, 
			final int potionChance, final int armorChance, final int enchantChance, final boolean alwaysBlock, Random rand, boolean healer) {
		this(name, type, rank, upgradeReq, attributeRace, canRide, usesShield, shieldChance, potionChance, armorChance, enchantChance, alwaysBlock, rand);
		this.canHeal = healer;
		this.rand = rand;
		this.setEquipment();
	}

	public AttributeVocation(String name, int type, int rank, int upgradeReq, AttributeRace attributeRace, 
			final boolean canRide, final boolean usesShield, final int shieldChance, 
			final int potionChance, final int armorChance, final int enchantChance, final boolean alwaysBlock, Random rand
			, boolean healer, boolean alwaysHorse) {
		this(name, type, rank, upgradeReq, attributeRace, canRide, usesShield, shieldChance, potionChance, armorChance, enchantChance, alwaysBlock, rand);
		this.alwaysHorse = alwaysHorse;
		if(alwaysHorse) horseChance = 100;
	}

	public AttributeVocation(String string, int classVillager, int i, int j, AttributeRace attributeRace, Item stoneAxe,
			int subclassWorker, Block log) {
		this(string, classVillager, i, j, attributeRace, stoneAxe,
				subclassWorker);
		this.targetBlock  = log;
	}

	public AttributeVocation(String string, int classType, int rank2, int defaultReq,
			AttributeRace race, boolean usesPoisonedArrows) {
		this(string, classType, rank2, defaultReq, race);
		this.usesPoisonedArrows = usesPoisonedArrows;
		this.setEquipment();
	}

	public AttributeVocation(String name, int type, int rank, int upgradeReq,
			AttributeRace attributeRace, boolean canRide, boolean usesShield, int shieldChance, int potionChance,
			int armorChance, int enchantChance, boolean alwaysBlock, boolean usesPoisonedArrows, Random rand) {
		this(name, type, rank, upgradeReq, attributeRace, canRide, usesShield, shieldChance, potionChance, armorChance, enchantChance, alwaysBlock, rand);
		this.usesPoisonedArrows = usesPoisonedArrows;
		this.setEquipment();
	}

	public int getEnchantmentChance() {
		return enchantmentChance;
	}

	public void setEnchantmentChance(int enchantmentChance) {
		this.enchantmentChance = enchantmentChance;
	}

	public Block getTargetBlock() {
		return targetBlock;
	}

	public void setTargetBlock(Block targetBlock) {
		this.targetBlock = targetBlock;
	}

	public boolean isAlwaysHorse() {
		return alwaysHorse;
	}

	public void setAlwaysHorse(boolean alwaysHorse) {
		this.alwaysHorse = alwaysHorse;
		if(alwaysHorse) horseChance = 100;
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
	
	public boolean isAlwaysBlocking()
	{
		return alwaysBlock;
	}

	public PotionType getArrowPotions(int i) {
		if(arrowPotions.length <= i) return null;
		return arrowPotions[i];
	}

	public void setArrowPotions(PotionType[] arrowPotions) {
		this.arrowPotions = arrowPotions;
	}

	public boolean isUsesPoisonedArrows() {
		return usesPoisonedArrows;
	}

	public void setUsesPoisonedArrows(boolean usesPoisonedArrows) {
		this.usesPoisonedArrows = usesPoisonedArrows;
		this.setEquipment();
	}

	public int getPoisonedArrowsChance() {
		return poisonedArrowsChance;
	}

	public void setPoisonedArrowsChance(int poisonedArrowsChance) {
		this.poisonedArrowsChance = poisonedArrowsChance;
	}

	public Enchantment getSwordEnchantments(int i) {
		return swordEnchantments[i];
	}

	public void setSwordEnchantments(Enchantment[] swordEnchantments) {
		this.swordEnchantments = swordEnchantments;
	}

	public Enchantment getBowEnchantments(int i) {
		return bowEnchantments[i];
	}

	public void setBowEnchantments(Enchantment[] bowEnchantments) {
		this.bowEnchantments = bowEnchantments;
	}

	public Enchantment getHeadEnchantments(int i) {
		return headEnchantments[i];
	}

	public void setHeadEnchantments(Enchantment[] headEnchantments) {
		this.headEnchantments = headEnchantments;
	}

	public Enchantment getChestEnchantments(int i) {
		return chestEnchantments[i];
	}

	public void setChestEnchantments(Enchantment[] chestEnchantments) {
		this.chestEnchantments = chestEnchantments;
	}

	public Enchantment getLegsEnchantments(int i) {
		return legsEnchantments[i];
	}

	public void setLegsEnchantments(Enchantment[] legsEnchantments) {
		this.legsEnchantments = legsEnchantments;
	}

	public Enchantment getBootsEnchantments(int i) {
		return bootsEnchantments[i];
	}

	public void setBootsEnchantments(Enchantment[] bootsEnchantments) {
		this.bootsEnchantments = bootsEnchantments;
	}

	public void setEquipment()
	{
		int armorID = ((rank - 1) + armorOffset) * 4;
		
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
			
			meleeWeapon = null;//this.originRace.meleeWeapons.get(rank);
			rangedWeapon = null;//this.originRace.meleeWeapons.get(rank);
			shield = null;//this.originRace.shield
			horseArmor = null;
			thrown = null;
		}
		
		for(int i  = 0; i < swordEnchantments.length; i++)
		{
			swordEnchantments[i] = this.originRace.swordEnchantments.get(i);
		}
		
		for(int i  = 0; i < bowEnchantments.length; i++)
		{
			bowEnchantments[i] = this.originRace.bowEnchantments.get(i);
		}
		
		for(int i  = 0; i < headEnchantments.length; i++)
		{
			headEnchantments[i] = this.originRace.headEnchantments.get(i);
		}
		
		for(int i  = 0; i < chestEnchantments.length; i++)
		{
			chestEnchantments[i] = this.originRace.chestEnchantments.get(i);
		}
		
		
		for(int i  = 0; i < legsEnchantments.length; i++)
		{
			legsEnchantments[i] = this.originRace.legsEnchantments.get(i);
		}
		
		for(int i  = 0; i < bootsEnchantments.length; i++)
		{
			bootsEnchantments[i] = this.originRace.bootsEnchantments.get(i);
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
				if(usesShield && rand.nextInt(100) < shieldChance) 
				{
					shield = this.originRace.shield.get(rand.nextInt(this.originRace.shield.size()));
					//System.out.println("Shield: "+shield);
				}
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				
				//if(rand != null && rand.nextInt(100) < potionChance) potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);
				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				thrown = this.originRace.thrown.get(AttributeRace.THROWN_1);
				//potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);
				//}
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
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				
				if(this.usesPoisonedArrows)
				{
					for(int i = 0; i < this.arrowPotions.length; i++)
					{
						this.arrowPotions[i] = this.originRace.arrowPotions.get(i);
					}
				}
				//if(rand != null && rand.nextInt(100) < potionChance) potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);

				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);

				//}
			}
			break;
			case CLASS_MAGE:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//spells = this.originRace.spells.get(rank);
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				//if(rand != null && rand.nextInt(100) < potionChance )
				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//}
				for(int i = 0; i < spells.length; i++)
				{
					if(spells[i] != null) continue;
					//System.out.println("Spell "+i+": "+this.originRace.spells.get(i + ((rank) * 4)));
					spells[i] = this.originRace.spells.get(i + ((rank - 1) * ALL_SPELL_SLOTS));
				}
			}
			break;
			case CLASS_ALCHEMIST:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				
				for(int i = 0; i < potions.length; i++)
				{
					//System.out.println(this.originRace.potions.get(i + ((rank) * ALL_POTION_SLOTS))+"\nRanks:"+(rank));
					potions[i] = this.originRace.potions.get(i + ((rank - 1) * ALL_POTION_SLOTS));
				}
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
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				
				if(rand != null && rand.nextInt(100) < potionChance )
				{
				//	if(rand != null && rand.nextInt(100) < potionChance) potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);
					potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				}
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
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				//if(rand != null && rand.nextInt(100) < potionChance )
				//{
					potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
					//potions[POTION_SLOT_ACTIVE_1]  = this.originRace.potions.get(AttributeRace.POTION_3);

				//}
			}
			break;
			case CLASS_HYBRID_MAGE_SOLDER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//spells = this.originRace.spells.get(rank);
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				//if(rand != null && rand.nextInt(100) < potionChance )
				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//}
				for(int i = 0; i < spells.length; i++)
				{
					if(spells[i] != null) continue;
					//System.out.println("Spell: "+this.originRace.spells.get(i + ((rank) * 4)));
					spells[i] = this.originRace.spells.get(i + ((rank - 1) * ALL_SPELL_SLOTS));
				}
			}
			break;
			case CLASS_HYBRID_MAGE_ARCHER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//spells = this.originRace.spells.get(rank);
				rangedWeapon = this.originRace.rangedWeapons.get(0);
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				//if(rand != null && rand.nextInt(100) < potionChance )
				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//}
				for(int i = 0; i < spells.length; i++)
				{
					if(spells[i] != null) continue;
					//System.out.println("Spell: "+this.originRace.spells.get(i + ((rank) * 4)));
					spells[i] = this.originRace.spells.get(i + ((rank - 1) * ALL_SPELL_SLOTS));
				}
				
				if(this.usesPoisonedArrows)
				{
					for(int i = 0; i < this.arrowPotions.length; i++)
					{
						this.arrowPotions[i] = this.originRace.arrowPotions.get(i);
					}
				}
			}
			break;
			case CLASS_HYBRID_ARCHER_SOLDIER:
			{
				helmet = this.originRace.armor.get(head);
				chestplate = this.originRace.armor.get(chest);
				leggings = this.originRace.armor.get(legs);
				boots = this.originRace.armor.get(feet);
				//spells = this.originRace.spells.get(rank);
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				rangedWeapon = this.originRace.rangedWeapons.get(0);
				
				
				if(canRide && rand.nextInt(100) < horseArmorChance)
				{
					horseArmor = this.originRace.horseArmors.get(rank - 1);
				}
				//if(rand != null && rand.nextInt(100) < potionChance )
				//{
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//}
				if(this.usesPoisonedArrows)
				{
					for(int i = 0; i < this.arrowPotions.length; i++)
					{
						this.arrowPotions[i] = this.originRace.arrowPotions.get(i);
					}
				}
			}
			break;
			case CLASS_OVERRIDE_NO_ARMOR:
			{
				meleeWeapon = this.originRace.meleeWeapons.get(rank - 1);
				potions[0] = this.originRace.potions.get(POTION_SLOT_PASSIVE_1);
				//thrown = this.originRace.thrown.get(AttributeRace.THROWN_1);
			}
			break;
		}
		
		/*this.originRace.meleeWeapons
		this.originRace.shield
		this.originRace.rangedWeapons
		this.originRace.potions
		this.originRace.spells*/
	}
	
	public Item getHorseArmor() {
		return horseArmor;
	}

	public void setHorseArmor(Item horseArmor) {
		this.horseArmor = horseArmor;
	}

	public PotionType getPotions(final int num) {
		return potions[num];
	}

	public void setPotions(int i, PotionType potions) {
		this.potions[i] = potions;
	}
	
	public Spell getSpell(final int num) {
		return this.spells[num];
	}

	public void setSpell(int i, Spell spell) {
		this.spells[i] = spell;
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

	public boolean isHealer() {
		return canHeal ;
	}

	public ItemWeaponThrowable getThrown() {
		// TODO Auto-generated method stub
		return thrown;
	}

	public int getHorseChance() {
		// TODO Auto-generated method stub
		return horseChance ;
	}

	public void overrideSpells(int spell3, Spell summonSkeleton) {
		spells[spell3] = summonSkeleton;
	}

	public Item getMeleeWeapon() {
		// TODO Auto-generated method stub
		return this.meleeWeapon;
	}

	public float getScale() {
		// TODO Auto-generated method stub
		return scale ;
	}
	
	public void setScale(float f) {
		// TODO Auto-generated method stub
		scale = f;
	}

	public boolean getBreakBlocks() {
		// TODO Auto-generated method stub
		return breakBlocks;
	}
	
	public void setBreakBlocks(boolean b) {
		// TODO Auto-generated method stub
		breakBlocks = b;
	}

	public void setHeal(boolean b) {
		this.canHeal = b;
	}

	public void setCanRide(boolean b) {
		this.canRide = b;
	}

	public boolean usesAdditionalMagicSlots() {
		// TODO Auto-generated method stub
		return this.usesMagicSlot;
	}

	public boolean usesAdditionalPotionSlots() {
		// TODO Auto-generated method stub
		return this.usesPotionSlots;
	}

	public void setUsesMagicSlot(boolean usesMagicSlot) {
		this.usesMagicSlot = usesMagicSlot;
	}

	public void setUsesPotionSlots(boolean usesPotionSlots) {
		this.usesPotionSlots = usesPotionSlots;
	}

	public boolean isUsesLingering() {
		return usesLingering;
	}

	public void setUsesLingering(boolean usesLingering) {
		this.usesLingering = usesLingering;
	}

	public void armorChoiceOffset(int i) {
		this.armorOffset  = i;
	}
	
	
}
