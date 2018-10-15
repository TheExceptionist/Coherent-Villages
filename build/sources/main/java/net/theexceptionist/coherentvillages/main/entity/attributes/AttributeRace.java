package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.RenderHumanVillager;

public class AttributeRace {	
	//Warrior Race - High Health, Lots of Damage, Nords
	public static final int RACE_TYPE_BARBARIAN = 0;
	//Knight Race - High Armor, has horses, Latins
	public static final int RACE_TYPE_EMPIRE = 1;
	//Archer Race - Good Detection and Speed, Slavs
	public static final int RACE_TYPE_HIGH_BARBARIAN = 2; 
	//Mage Race - Celts
	public static final int RACE_TYPE_FEUDAL = 3;
	//Alchemy Race - Arabs
	public static final int RACE_TYPE_SAND_EMPIRE = 4;
	
	public static AttributeRace nords;// = new AttributeRace("Nord", RACE_TYPE_BARBARIAN, 20, 5, 2, 0);
	public static AttributeRace latins;// = new AttributeRace("Latin", RACE_TYPE_EMPIRE, 15, 3, 1, 4);
	public static AttributeRace slavs;// = new AttributeRace("Slav", RACE_TYPE_HIGH_BARBARIAN, 10, 4, 3, 16);
	public static AttributeRace germans;// = new AttributeRace("Celt", RACE_TYPE_FEUDAL, 5, 5, 2, 8);
	public static AttributeRace arabs;// = new AttributeRace("Arab", RACE_TYPE_SAND_EMPIRE, 10, 5, 2, 4);
	
	public static ArrayList<AttributeRace> races;// = new ArrayList<AttributeRace>();
	
	protected String mSkins, fSkins;
	
	protected String name;
	protected int type;
	protected int healthBonus;
	protected int attackBonus;
	protected int speedBonus;
	protected int detectBonus;
	protected int knockbackBonus;
	
	protected HashMap<Integer,Item> armor;
	protected HashMap<Integer,Item> shield;
	protected HashMap<Integer,Item> meleeWeapons;
	protected HashMap<Integer,Item> rangedWeapons;
	protected HashMap<Integer,Item> spells;
	protected HashMap<Integer,PotionType> potions;
	
	protected HashMap<Integer, AttributeVocation> soldiers;
	protected HashMap<Integer, AttributeVocation> archers;
	protected HashMap<Integer, AttributeVocation> mages;
	protected HashMap<Integer, AttributeVocation> alchemists;
	protected HashMap<Integer, AttributeVocation> villagers;
	protected HashMap<Integer, AttributeVocation> bandits;
	protected HashMap<Integer, AttributeVocation> mercenaries;
	protected HashMap<Integer, AttributeVocation> rulers;
	
	private AttributeVocation recruitSoldier = null;
	private AttributeVocation recruitArcher = null;
	private AttributeVocation recruitAlchemist = null;
	private AttributeVocation recruitMage = null;
	
	public static final int ARMOR_1_HEAD = 0;
	public static final int ARMOR_1_CHEST = 1;
	public static final int ARMOR_1_LEGS = 2;
	public static final int ARMOR_1_FEET = 3;
	
	public static final int ARMOR_2_HEAD = 4;
	public static final int ARMOR_2_CHEST = 5;
	public static final int ARMOR_2_LEGS = 6;
	public static final int ARMOR_2_FEET = 7;
	
	public static final int ARMOR_3_HEAD = 8;
	public static final int ARMOR_3_CHEST = 9;
	public static final int ARMOR_3_LEGS = 10;
	public static final int ARMOR_3_FEET = 11;
	
	public static final int ARMOR_4_HEAD = 12;
	public static final int ARMOR_4_CHEST = 13;
	public static final int ARMOR_4_LEGS = 14;
	public static final int ARMOR_4_FEET = 15;
	
	public static final int ARMOR_5_HEAD = 16;
	public static final int ARMOR_5_CHEST = 17;
	public static final int ARMOR_5_LEGS = 18;
	public static final int ARMOR_5_FEET = 19;
	
	public static final int WEAPON_1 = 0;
	public static final int WEAPON_2 = 1;
	public static final int WEAPON_3 = 2;
	public static final int WEAPON_4 = 3;
	public static final int WEAPON_5 = 4;
	
	protected static Random rand;// = new Random(System.nanoTime());
	protected int ID;
	public static int END_ID = 0;
	
	public AttributeRace(final String name, final int type, final int health, final int attack, final int speed, final int detect, final int knockback, final String mPath, final String fPath)
	{
		this.name = name;
		this.type = type;
		this.armor = new HashMap<Integer,Item>();
		this.shield = new HashMap<Integer, Item>();
		this.meleeWeapons = new HashMap<Integer,Item>();
		this.rangedWeapons = new HashMap<Integer,Item>();
		this.spells = new HashMap<Integer,Item>();
		this.potions = new HashMap<Integer,PotionType>();
		this.mSkins = mPath;
		this.fSkins = fPath;
		
		this.healthBonus = health;
		this.attackBonus = attack;
		this.speedBonus = speed;
		this.detectBonus = detect;
		this.knockbackBonus = knockback;
		
		this.soldiers = new HashMap<Integer, AttributeVocation>();
		this.archers = new HashMap<Integer, AttributeVocation>();
		this.mages = new HashMap<Integer, AttributeVocation>();
		this.alchemists = new HashMap<Integer, AttributeVocation>();
		this.villagers = new HashMap<Integer, AttributeVocation>();
		this.bandits = new HashMap<Integer, AttributeVocation>();
		this.mercenaries = new HashMap<Integer, AttributeVocation>();
		this.rulers = new HashMap<Integer, AttributeVocation>();
		
		this.races.add(this);
		
		this.ID = END_ID++;
		
		this.initByRaceType();
	}
	
	public static void init()
	{
		rand = new Random(System.nanoTime());
		
		races = new ArrayList<AttributeRace>();
		
		nords = new AttributeRace("Nord", RACE_TYPE_BARBARIAN, 20, 5, 2, 0, 2, RenderHumanVillager.NORD_SKIN_M,RenderHumanVillager.NORD_SKIN_F);
		latins = new AttributeRace("Latin", RACE_TYPE_EMPIRE, 15, 3, 1, 4, 2, RenderHumanVillager.LATIN_SKIN_M,RenderHumanVillager.LATIN_SKIN_F);
		slavs = new AttributeRace("Slav", RACE_TYPE_HIGH_BARBARIAN, 10, 4, 3, 16, 1, RenderHumanVillager.SLAV_SKIN_M,RenderHumanVillager.SLAV_SKIN_F);
		germans = new AttributeRace("German", RACE_TYPE_FEUDAL, 5, 5, 2, 8, 5, RenderHumanVillager.GERMAN_SKIN_M,RenderHumanVillager.GERMAN_SKIN_F);
		arabs = new AttributeRace("Arab", RACE_TYPE_SAND_EMPIRE, 10, 5, 2, 4, 0, RenderHumanVillager.ARAB_SKIN_M,RenderHumanVillager.ARAB_SKIN_F);
	}
	
	public static AttributeRace getRandomRace()
	{
		return races.get(rand.nextInt(races.size()));
	}
	
	public void initByRaceType()
	{
		switch(type)
		{
			case RACE_TYPE_BARBARIAN:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_5_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_5_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_5_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_5_FEET, Items.DIAMOND_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_AXE);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_AXE);
				this.meleeWeapons.put(WEAPON_3, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_4, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_5, Items.DIAMOND_AXE);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Footman", AttributeVocation.CLASS_SOLDIER, 0, 10, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Axeman", AttributeVocation.CLASS_SOLDIER, 1, 10, this);
				AttributeVocation soldierTier3 = new AttributeVocation("Warrior", AttributeVocation.CLASS_SOLDIER, 2, 10, this, false, true, 50, rand);
				AttributeVocation soldierTier4 = new AttributeVocation("Huskarl", AttributeVocation.CLASS_SOLDIER, 3, 10, this, false, true, 50, rand);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, 0, 10, this);
				
				soldierTier1.setUpgradeTree(soldierTier2, soldierTier3);
				soldierTier2.setUpgradeTree(soldierTier4, null);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				this.soldiers.put(4, soldierTier4);
				
				this.archers.put(1, archerTier1);
				
				AttributeVocation villagerFarmer = new AttributeVocation("Farmer", AttributeVocation.CLASS_VILLAGER, 0, 10, this, Items.STONE_HOE, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerFisherman = new AttributeVocation("Fisherman", AttributeVocation.CLASS_VILLAGER, 1, 10, this, Items.FISHING_ROD, AttributeVocation.SUBCLASS_WORKER);
				AttributeVocation villagerLumberman = new AttributeVocation("Lumberman", AttributeVocation.CLASS_VILLAGER, 2, 10, this, Items.STONE_AXE, AttributeVocation.SUBCLASS_WORKER);
				
				this.villagers.put(villagerFarmer.getRank(), villagerFarmer);
				this.villagers.put(villagerFisherman.getRank(), villagerFisherman);
				this.villagers.put(villagerLumberman.getRank(), villagerLumberman);
				
				AttributeVocation banditTier1 = new AttributeVocation("Raider", AttributeVocation.CLASS_BANDIT, 0, 10, this, false, false, 0, rand);
				AttributeVocation banditTier2 = new AttributeVocation("Berserker", AttributeVocation.CLASS_BANDIT, 1, 10, this, false, false, 0, rand);
				AttributeVocation banditTier3 = new AttributeVocation("Viking",  AttributeVocation.CLASS_BANDIT, 2, 10, this, false, true, 50, rand);
				
				banditTier1.setUpgradeTree(banditTier2, banditTier3);
				banditTier2.setDamageOffest(20);
				
				AttributeVocation banditTier4 = new AttributeVocation("Barbarian", AttributeVocation.CLASS_BANDIT, 1, 10, this, false, false, 0, rand, AttributeVocation.CLASS_ARCHER);

				this.bandits.put(banditTier1.getRank(), banditTier1);
				this.bandits.put(banditTier2.getRank(), banditTier2);
				this.bandits.put(banditTier3.getRank(), banditTier3);
				this.bandits.put(banditTier4.getRank() + 2, banditTier4);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Mercenary", AttributeVocation.CLASS_MERCENARY, 2, 10, this, false, true, 50, rand, AttributeVocation.CLASS_SOLDIER);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Jarl", AttributeVocation.CLASS_RULER, 4, 10, this, false, true, 75, rand);
				this.rulers.put(1, soldierTier5);
			}
			break;
			case RACE_TYPE_EMPIRE:
			{
				this.armor.put(ARMOR_1_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.DIAMOND_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Legionnaire", AttributeVocation.CLASS_SOLDIER, 0, 10, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Legate", AttributeVocation.CLASS_SOLDIER, 1, 10, this, true, true, 100, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Champion", AttributeVocation.CLASS_SOLDIER, 2, 10, this, true, true, 100, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, 0, 10, this);
				AttributeVocation archerTier2 = new AttributeVocation("Master Archer", AttributeVocation.CLASS_ARCHER, 1, 10, this, true, true, 50, rand);
				
				archerTier1.setUpgradeTree(archerTier2, null);
				
				AttributeVocation mageTier1 = new AttributeVocation("Battlemage", AttributeVocation.CLASS_MAGE, 0, 10, this, true, false, 20, rand);
				
				AttributeVocation alchemistTier1 = new AttributeVocation("Healer", AttributeVocation.CLASS_ALCHEMIST, 0, 10, this, true, false, 0, rand);
				AttributeVocation alchemistTier2 = new AttributeVocation("Alchemist", AttributeVocation.CLASS_ALCHEMIST, 0, 10, this, true, true, 20, rand);

				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitAlchemist = alchemistTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				
				this.mages.put(1, mageTier1);
			
				this.alchemists.put(1, alchemistTier1);
				this.alchemists.put(1 + 1, alchemistTier2);
				
				AttributeVocation villagerArtisan = new AttributeVocation("Artisan", AttributeVocation.CLASS_VILLAGER, 0, 10, this, null, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerMerchant = new AttributeVocation("Merchant", AttributeVocation.CLASS_VILLAGER, 1, 10, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerBlacksmith = new AttributeVocation("Blacksmith", AttributeVocation.CLASS_VILLAGER, 2, 10, this, null, AttributeVocation.SUBCLASS_WORKER);
				
				this.villagers.put(villagerArtisan.getRank(), villagerArtisan);
				this.villagers.put(villagerMerchant.getRank(), villagerMerchant);
				this.villagers.put(villagerBlacksmith.getRank(), villagerBlacksmith);
			}
			break;
			case RACE_TYPE_HIGH_BARBARIAN:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_AXE);
				this.meleeWeapons.put(WEAPON_2, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.IRON_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Tribesman", AttributeVocation.CLASS_SOLDIER, 0, 10, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Warrior", AttributeVocation.CLASS_SOLDIER, 1, 10, this, true, false, 0, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Bogatyr", AttributeVocation.CLASS_SOLDIER, 2, 10, this, true, false, 0, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Bowman", AttributeVocation.CLASS_ARCHER, 0, 10, this);
				AttributeVocation archerTier2 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, 1, 10, this);
				AttributeVocation archerTier3 = new AttributeVocation("Mage Archer", AttributeVocation.CLASS_ARCHER, 2, 10, this, true, false, 0, rand);
				AttributeVocation archerTier4 = new AttributeVocation("Marksman", AttributeVocation.CLASS_ARCHER, 2, 10, this, true, false, 0, rand);
				
				archerTier1.setUpgradeTree(archerTier2, archerTier3);
				archerTier2.setUpgradeTree(archerTier4, null);
				
				AttributeVocation mageTier1 = new AttributeVocation("Bogatyr Wizard", AttributeVocation.CLASS_MAGE, 0, 10, this, true, false, 0, rand);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				
				this.mages.put(3, mageTier1);
				
				AttributeVocation villagerHunter = new AttributeVocation("Hunter", AttributeVocation.CLASS_VILLAGER, 0, 10, this, Items.BOW, AttributeVocation.SUBCLASS_WORKER);
				AttributeVocation villagerBard = new AttributeVocation("Bard", AttributeVocation.CLASS_VILLAGER, 1, 10, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerFletcher = new AttributeVocation("Fletcher", AttributeVocation.CLASS_VILLAGER, 2, 10, this, null, AttributeVocation.SUBCLASS_WORKER);
				
				this.villagers.put(villagerHunter.getRank(), villagerHunter);
				this.villagers.put(villagerBard.getRank(), villagerBard);
				this.villagers.put(villagerFletcher.getRank(), villagerFletcher);
			}
			break;
			case RACE_TYPE_FEUDAL:
			{
				this.armor.put(ARMOR_1_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Militia", AttributeVocation.CLASS_SOLDIER, 0, 10, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Man-At-Arms", AttributeVocation.CLASS_SOLDIER, 1, 10, this, true, true, 70, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Knight", AttributeVocation.CLASS_SOLDIER, 2, 10, this, true, true, 100, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, 0, 10, this);
				
				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, 0, 10, this);
				AttributeVocation mageTier2 = new AttributeVocation("Mage Knight", AttributeVocation.CLASS_MAGE, 1, 10, this, true, true, 50, rand);
				AttributeVocation mageTier3 = new AttributeVocation("Grand Wizard", AttributeVocation.CLASS_MAGE, 1, 10, this);
			
				mageTier1.setUpgradeTree(mageTier2, mageTier3);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				this.archers.put(1, archerTier1);

				this.mages.put(1, mageTier1);
				this.mages.put(1 + 1, mageTier2);
				this.mages.put(1 + 2, mageTier3);
				
				AttributeVocation villagerSerf = new AttributeVocation("Serf", AttributeVocation.CLASS_VILLAGER, 0, 10, this, Items.WOODEN_HOE, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerPriest = new AttributeVocation("Priest", AttributeVocation.CLASS_VILLAGER, 1, 10, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerScribe = new AttributeVocation("Scribe", AttributeVocation.CLASS_VILLAGER, 2, 10, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				
				this.villagers.put(villagerSerf.getRank(), villagerSerf);
				this.villagers.put(villagerPriest.getRank(), villagerPriest);
				this.villagers.put(villagerScribe.getRank(), villagerScribe);
			}
			break;
			case RACE_TYPE_SAND_EMPIRE:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.DIAMOND_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Peasant", AttributeVocation.CLASS_SOLDIER, 0, 10, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Footman", AttributeVocation.CLASS_SOLDIER, 1, 10, this, true, false, 60, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Mamluke", AttributeVocation.CLASS_SOLDIER, 2, 10, this, true, true, 80, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, 0, 10, this, true, false, 10, rand);
				AttributeVocation archerTier2 = new AttributeVocation("Sharpshooter", AttributeVocation.CLASS_ARCHER, 1, 10, this, true, false, 30, rand);
				
				archerTier1.setUpgradeTree(archerTier2, null);
				
				AttributeVocation mageTier1 = new AttributeVocation("Conjurer", AttributeVocation.CLASS_MAGE, 0, 10, this);
				
				AttributeVocation alchemistTier1 = new AttributeVocation("Undead Hunter", AttributeVocation.CLASS_ALCHEMIST, 0, 10, this);
				AttributeVocation alchemistTier2 = new AttributeVocation("Potion Master", AttributeVocation.CLASS_ALCHEMIST, 0, 10, this, true, false, 20, rand);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitAlchemist = alchemistTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(1 + 1, soldierTier2);
				this.soldiers.put(1 + 2, soldierTier3);
				
				this.archers.put(1, archerTier1);
				this.archers.put(1 + 1, archerTier2);
				
				this.mages.put(1, mageTier1);
			
				this.alchemists.put(1, alchemistTier1);
				this.alchemists.put(1 + 1, alchemistTier2);
				
				AttributeVocation villagerNomad = new AttributeVocation("Nomad", AttributeVocation.CLASS_VILLAGER, 0, 10, this, null, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerScholar = new AttributeVocation("Scholar", AttributeVocation.CLASS_VILLAGER, 1, 10, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerQuartermaster = new AttributeVocation("Quartermaster", AttributeVocation.CLASS_VILLAGER, 2, 10, this, Items.IRON_SWORD, AttributeVocation.SUBCLASS_TRAINER);
				
				this.villagers.put(villagerNomad.getRank(), villagerNomad);
				this.villagers.put(villagerScholar.getRank(), villagerScholar);
				this.villagers.put(villagerQuartermaster.getRank(), villagerQuartermaster);
			}
			break;
		}
	}
	
	public int getRulerTypes()
	{
		return rulers.size();
	}
	
	public int getBanditTypes()
	{
		return bandits.size();
	}
	
	public int getSoldierTypes()
	{
		return soldiers.size();
	}
	
	public int getArcherTypes()
	{
		return archers.size();
	}
	
	public int getMageTypes()
	{
		return mages.size();
	}
	
	public int getAlchemistTypes()
	{
		return alchemists.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public HashMap<Integer, Item> getArmor() {
		return armor;
	}

	public void setArmor(HashMap<Integer, Item> armor) {
		this.armor = armor;
	}

	public HashMap<Integer, Item> getMeleeWeapons() {
		return meleeWeapons;
	}

	public void setMeleeWeapons(HashMap<Integer, Item> meleeWeapons) {
		this.meleeWeapons = meleeWeapons;
	}

	public HashMap<Integer, Item> getRangedWeapons() {
		return rangedWeapons;
	}

	public void setRangedWeapons(HashMap<Integer, Item> rangedWeapons) {
		this.rangedWeapons = rangedWeapons;
	}

	public HashMap<Integer, Item> getSpells() {
		return spells;
	}

	public void setSpells(HashMap<Integer, Item> spells) {
		this.spells = spells;
	}

	public HashMap<Integer, PotionType> getPotions() {
		return potions;
	}

	public void setPotions(HashMap<Integer, PotionType> potions) {
		this.potions = potions;
	}

	public int getHealthBonus() {
		return healthBonus;
	}

	public void setHealthBonus(int healthBonus) {
		this.healthBonus = healthBonus;
	}

	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getSpeedBonus() {
		return speedBonus;
	}

	public void setSpeedBonus(int speedBonus) {
		this.speedBonus = speedBonus;
	}

	public int getDetectBonus() {
		return detectBonus;
	}

	public void setDetectBonus(int detectBonus) {
		this.detectBonus = detectBonus;
	}

	public HashMap<Integer, Item> getShield() {
		return shield;
	}

	public HashMap<Integer, AttributeVocation> getSoldiers() {
		return soldiers;
	}

	public HashMap<Integer, AttributeVocation> getArchers() {
		return archers;
	}
	
	public HashMap<Integer, AttributeVocation> getMages() {
		return mages;
	}

	public HashMap<Integer, AttributeVocation> getAlchemists() {
		return alchemists;
	}

	public AttributeVocation getVocation(int type, int id) {
		/*int armorID = rank * 4;
		int weaponID = rank;
		int shieldID = 0;*/
		
		//System.out.println("Working");
		
		switch(type)
		{
			case AttributeVocation.CLASS_SOLDIER:
			{
				if(id > soldiers.size())  return null;
				return soldiers.get(id + 1);
			}
			case AttributeVocation.CLASS_ARCHER:
			{
				if(id > archers.size())  return null;
				return archers.get(id + 1);	
			}
			case AttributeVocation.CLASS_MAGE:
			{
				if(id > mages.size())  return null;
				return mages.get(id + 1);
			}
			case AttributeVocation.CLASS_ALCHEMIST:
			{
				if(id > alchemists.size())  return null;
				return alchemists.get(id + 1);
			}
			case AttributeVocation.CLASS_VILLAGER:
			{
				if(id > villagers.size())  return null;
				return villagers.get(id + 1);
			}
		}
		
		return null;
	}

	public AttributeVocation getRecruitVocation(int type) {
		switch(type)
		{
			case AttributeVocation.CLASS_SOLDIER:
			{
				return recruitSoldier;
			}
			case AttributeVocation.CLASS_ARCHER:
			{
				return recruitArcher;
			}
			case AttributeVocation.CLASS_MAGE:
			{
				return recruitMage;
			}
			case AttributeVocation.CLASS_ALCHEMIST:
			{
				return recruitAlchemist;
			}
		}
		
		return null;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

	public static AttributeRace getFromIDRace(int id) {
		for(AttributeRace race : races)
		{
			if(race.ID == id)
			{
				return race;
			}
		}
		return null;
	}
	
	
	public ResourceLocation getRandomSkinM(int vocation) {
		int i = 0;
		
		AttributeVocation job = AttributeVocation.getVocationFromID(vocation);
		return new ResourceLocation(Resources.MODID, mSkins+job.getName().toLowerCase()+"_"+i+".png");
	}
	
	public ResourceLocation getRandomSkinF(int vocation) {
		int i = 0;
		
		AttributeVocation job = AttributeVocation.getVocationFromID(vocation);
		return new ResourceLocation(Resources.MODID, fSkins+job.getName().toLowerCase()+"_"+i+".png");

	}

	public int getKnockbackBonus() {
		// TODO Auto-generated method stub
		return knockbackBonus;
	}
	
	public AttributeVocation getRandomMercenary(World world) {
		if(this.mercenaries.isEmpty()) return null;
		return this.mercenaries.get(world.rand.nextInt(mercenaries.size()) + 1);
	}

	public AttributeVocation getRandomBandit(World world) {
		if(this.bandits.isEmpty()) return null;
		return this.bandits.get(world.rand.nextInt(bandits.size()) + 1);
	}
	
	public AttributeVocation getRandomRuler(World world) {
		if(this.rulers.isEmpty()) return null;
		return this.rulers.get(world.rand.nextInt(rulers.size()) + 1);
	}

	public AttributeVocation getBandit(int i) {
		// TODO Auto-generated method stub
		int last = i;
		if(last >= this.bandits.size()) last = this.bandits.size();
		return this.bandits.get(last);
	}
}