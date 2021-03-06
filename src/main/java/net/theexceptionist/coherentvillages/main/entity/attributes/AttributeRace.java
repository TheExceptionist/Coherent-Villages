package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.render.RenderHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.spells.Spell;
import net.theexceptionist.coherentvillages.main.items.ItemWeaponThrowable;
import net.theexceptionist.coherentvillages.main.items.ModItems;

public class AttributeRace {	
	//Warrior Race - High Health, Lots of Damage, Nords
	public static final int RACE_TYPE_NORD = 0;
	//Knight Race - High Armor, has horses, Latins
	public static final int RACE_TYPE_LATIN = 1;
	//Archer Race - Good Detection and Speed, Slavs
	public static final int RACE_TYPE_SLAV = 2; 
	//Mage Race - Celts
	public static final int RACE_TYPE_GERMAN = 3;
	//Alchemy Race - Arabs
	public static final int RACE_TYPE_ARAB = 4;
	//All-around
	public static final int RACE_TYPE_GREEK = 5;
	public static final int RACE_TYPE_BRITON = 6;
	public static final int RACE_TYPE_FRANK = 7;
	public static final int RACE_TYPE_MONGOL = 8;
	public static final int RACE_TYPE_VAMPIRE = 9;
	
    public static final IAttribute MAGIC_DAMAGE = new RangedAttribute((IAttribute)null, "generic.magicDamage", 2.0D, 0.0D, 2048.0D);
    public static final IAttribute HORSE_HEALTH = new RangedAttribute((IAttribute)null, "generic.horseHealth", 2.0D, 0.0D, 2048.0D);

	public static AttributeRace nords;// = new AttributeRace("Nord", RACE_TYPE_BARBARIAN, 20, 5, 2, 0);
	public static AttributeRace latins;// = new AttributeRace("Latin", RACE_TYPE_EMPIRE, 15, 3, 1, 4);
	public static AttributeRace slavs;// = new AttributeRace("Slav", RACE_TYPE_HIGH_BARBARIAN, DEFAULT_UPGRADE_REQ, 4, 3, 16);
	public static AttributeRace germans;// = new AttributeRace("Celt", RACE_TYPE_FEUDAL, 5, 5, 2, 8);
	public static AttributeRace arabs;// = new AttributeRace("Arab", RACE_TYPE_SAND_EMPIRE, DEFAULT_UPGRADE_REQ, 5, 2, 4);
	public static AttributeRace greeks;
	public static AttributeRace britons;
	public static AttributeRace franks;
	public static AttributeRace mongols;
	public static AttributeRace vampires;
	
	protected Biome homeBiome = null;
	
	public static ArrayList<AttributeRace> races;// = new ArrayList<AttributeRace>();
	
	protected String mSkins, fSkins;
	
	protected String name;
	protected int type;
	protected int healthBonus;
	protected int attackBonus;
	protected int speedBonus;
	protected int detectBonus;
	protected int knockbackBonus;
	protected int magicBonus;
	protected int horseHealthBonus;

	
	protected HashMap<Integer,Item> armor;
	protected HashMap<Integer,Item> shield;
	protected HashMap<Integer,Item> meleeWeapons;
	protected HashMap<Integer,Item> rangedWeapons;
	protected HashMap<Integer,Spell> spells;
	protected HashMap<Integer,PotionType> potions;
	protected HashMap<Integer,PotionType> arrowPotions;
	protected HashMap<Integer,ItemWeaponThrowable> thrown; 
	protected HashMap<Integer,Item> horseArmors;
	
	protected HashMap<Integer, AttributeVocation> soldiers;
	protected HashMap<Integer, AttributeVocation> archers;
	protected HashMap<Integer, AttributeVocation> mages;
	protected HashMap<Integer, AttributeVocation> alchemists;
	protected HashMap<Integer, AttributeVocation> villagers;
	protected HashMap<Integer, AttributeVocation> bandits;
	protected HashMap<Integer, AttributeVocation> mercenaries;
	protected HashMap<Integer, AttributeVocation> rulers;
	
	protected HashMap<Integer, Enchantment> swordEnchantments;
	protected HashMap<Integer, Enchantment> bowEnchantments;
	protected HashMap<Integer, Enchantment> headEnchantments;
	protected HashMap<Integer, Enchantment> chestEnchantments;
	protected HashMap<Integer, Enchantment> legsEnchantments;
	protected HashMap<Integer, Enchantment> bootsEnchantments;

	
	public static HashMap<Integer, AttributeVocation> merchants;
	
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
	
	public static final boolean ALWAYS_HORSE = true;
	public static final boolean ALWAYS_BLOCK = true;
	public static final boolean USES_SHIELD = true;
	public static final boolean CAN_RIDE = true;
	public static final boolean IS_HEALER = true;
	
	public static final int DEFAULT_UPGRADE_REQ = 10;
	
	public static final int SHIELD_CHANCE_0 = 0;
	public static final int SHIELD_CHANCE_1 = 10;
	public static final int SHIELD_CHANCE_2 = 20;
	public static final int SHIELD_CHANCE_3 = 30;
	public static final int SHIELD_CHANCE_4 = 40;
	public static final int SHIELD_CHANCE_5 = 50;
	public static final int SHIELD_CHANCE_6 = 60;
	public static final int SHIELD_CHANCE_7 = 70;
	public static final int SHIELD_CHANCE_8 = 80;
	public static final int SHIELD_CHANCE_9 = 90;
	public static final int SHIELD_CHANCE_10 = 100;
	
	public static final int POTION_CHANCE_0 = 0;
	public static final int POTION_CHANCE_1 = 10;
	public static final int POTION_CHANCE_2 = 20;
	public static final int POTION_CHANCE_3 = 30;
	public static final int POTION_CHANCE_4 = 40;
	public static final int POTION_CHANCE_5 = 50;
	public static final int POTION_CHANCE_6 = 60;
	public static final int POTION_CHANCE_7 = 70;
	public static final int POTION_CHANCE_8 = 80;
	public static final int POTION_CHANCE_9 = 90;
	public static final int POTION_CHANCE_10 = 100;
	
	public static final int HORSE_ARMOR_CHANCE_0 = 0;
	public static final int HORSE_ARMOR_CHANCE_1 = 10;
	public static final int HORSE_ARMOR_CHANCE_2 = 20;
	public static final int HORSE_ARMOR_CHANCE_3 = 30;
	public static final int HORSE_ARMOR_CHANCE_4 = 40;
	public static final int HORSE_ARMOR_CHANCE_5 = 50;
	public static final int HORSE_ARMOR_CHANCE_6 = 60;
	public static final int HORSE_ARMOR_CHANCE_7 = 70;
	public static final int HORSE_ARMOR_CHANCE_8 = 80;
	public static final int HORSE_ARMOR_CHANCE_9 = 90;
	public static final int HORSE_ARMOR_CHANCE_10 = 100;
	
	public static final int ENCHANT_CHANCE_0 = 0;
	public static final int ENCHANT_CHANCE_1 = 10;
	public static final int ENCHANT_CHANCE_2 = 20;
	public static final int ENCHANT_CHANCE_3 = 30;
	public static final int ENCHANT_CHANCE_4 = 40;
	public static final int ENCHANT_CHANCE_5 = 50;
	public static final int ENCHANT_CHANCE_6 = 60;
	public static final int ENCHANT_CHANCE_7 = 70;
	public static final int ENCHANT_CHANCE_8 = 80;
	public static final int ENCHANT_CHANCE_9 = 90;
	public static final int ENCHANT_CHANCE_10 = 100;
	
	public static final int ARMOR_1_HORSE = 0;
	public static final int ARMOR_2_HORSE = 1;
	public static final int ARMOR_3_HORSE = 2;
	
	public static final int THROWN_1 = 0;
	
	public static final int RANK_1 = 1;
	public static final int RANK_2 = 2;
	public static final int RANK_3 = 3;
	public static final int RANK_4 = 4;
	public static final int RANK_5 = 5;
	public static final int RANK_6 = 6;
	public static final int RANK_7 = 7;
	private static final boolean USES_POISONED_ARROWS = true;


	
	
	protected static Random rand;// = new Random(System.nanoTime());
	protected int ID;
	public static int END_ID = 0;
	
	public AttributeRace(final String name, final int type, final int health, final int attack, final int speed, final int detect, final int knockback, final int magicBonus, final int horseHealthBonus, final String mPath, final String fPath)
	{
		this.name = name;
		this.type = type;
		this.armor = new HashMap<Integer,Item>();
		this.shield = new HashMap<Integer, Item>();
		this.horseArmors = new HashMap<Integer, Item>();
		this.meleeWeapons = new HashMap<Integer,Item>();
		this.rangedWeapons = new HashMap<Integer,Item>();
		this.spells = new HashMap<Integer,Spell>();
		this.potions = new HashMap<Integer,PotionType>();
		this.arrowPotions = new HashMap<Integer,PotionType>();
		this.thrown = new HashMap<Integer, ItemWeaponThrowable>();
		this.mSkins = mPath;
		this.fSkins = fPath;
		
		this.healthBonus = health;
		this.attackBonus = attack;
		this.speedBonus = speed;
		this.detectBonus = detect;
		this.knockbackBonus = knockback;
		this.magicBonus = magicBonus;
		this.horseHealthBonus = horseHealthBonus;
		
		this.soldiers = new HashMap<Integer, AttributeVocation>();
		this.archers = new HashMap<Integer, AttributeVocation>();
		this.mages = new HashMap<Integer, AttributeVocation>();
		this.alchemists = new HashMap<Integer, AttributeVocation>();
		this.villagers = new HashMap<Integer, AttributeVocation>();
		this.bandits = new HashMap<Integer, AttributeVocation>();
		this.mercenaries = new HashMap<Integer, AttributeVocation>();
		this.rulers = new HashMap<Integer, AttributeVocation>();
		
		swordEnchantments = new HashMap<Integer, Enchantment>();
		bowEnchantments = new HashMap<Integer, Enchantment>();
		headEnchantments = new HashMap<Integer, Enchantment>();
		chestEnchantments = new HashMap<Integer, Enchantment>();
		legsEnchantments = new HashMap<Integer, Enchantment>();
		bootsEnchantments = new HashMap<Integer, Enchantment>();
		
		this.races.add(this);
		
		this.ID = END_ID++;
		
		this.initByRaceType();
	}
	
	public static void init()
	{
		rand = new Random(System.nanoTime());
		
		races = new ArrayList<AttributeRace>();
		merchants = new HashMap<Integer, AttributeVocation>();
		
		nords = new AttributeRace("Nord", RACE_TYPE_NORD, 20, 5, 2, 0, 2, 1, 10, RenderHumanVillager.NORD_SKIN_M,RenderHumanVillager.NORD_SKIN_F);
		latins = new AttributeRace("Latin", RACE_TYPE_LATIN, 15, 3, 1, 4, 2, 2, 25, RenderHumanVillager.LATIN_SKIN_M,RenderHumanVillager.LATIN_SKIN_F);
		slavs = new AttributeRace("Slavic", RACE_TYPE_SLAV, 10, 4, 3, 16, 1, 3, 15, RenderHumanVillager.SLAV_SKIN_M,RenderHumanVillager.SLAV_SKIN_F);
		germans = new AttributeRace("Gothic", RACE_TYPE_GERMAN, 5, 5, 2, 8, 5, 5, 30, RenderHumanVillager.GERMAN_SKIN_M,RenderHumanVillager.GERMAN_SKIN_F);
		arabs = new AttributeRace("Arab", RACE_TYPE_ARAB, 10, 5, 2, 4, 0, 2, 20, RenderHumanVillager.ARAB_SKIN_M,RenderHumanVillager.ARAB_SKIN_F);
		
		greeks = new AttributeRace("Greek", RACE_TYPE_GREEK, 10, 5, 2, 4, 0, 3, 20, RenderHumanVillager.GREEK_SKIN_M,RenderHumanVillager.GREEK_SKIN_F);
		britons = new AttributeRace("Briton", RACE_TYPE_BRITON, 10, 5, 2, 4, 0, 3, 15, RenderHumanVillager.BRITON_SKIN_M,RenderHumanVillager.BRITON_SKIN_F);
		franks = new AttributeRace("Frank", RACE_TYPE_FRANK, 10, 5, 2, 4, 0, 4, 50, RenderHumanVillager.FRANK_SKIN_M,RenderHumanVillager.FRANK_SKIN_F);
		
		mongols = new AttributeRace("Mongol", RACE_TYPE_MONGOL, 5, 2, 4, 16, 0, 1, 25, RenderHumanVillager.MONGOL_SKIN_M,RenderHumanVillager.MONGOL_SKIN_F);
		vampires = new AttributeRace("Vampire", RACE_TYPE_VAMPIRE, 5 * 6, 5 * 2, 5, 8 * 2, 0, 5 * 2, 30, RenderHumanVillager.VAMPIRE_SKIN_M,RenderHumanVillager.VAMPIRE_SKIN_F);
	}
	
	public Biome getHomeBiome() {
		return homeBiome;
	}

	public void setHomeBiome(Biome homeBiome) {
		this.homeBiome = homeBiome;
	}

	public static AttributeRace getRandomRace()
	{
		return races.get(rand.nextInt(races.size()));
	}
	
	public void initByRaceType()
	{
		switch(type)
		{
			case RACE_TYPE_NORD:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, ModItems.barbarianHelmet);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, ModItems.barbarianBoots);
				
				//this.armor.put(ARMOR_3_HEAD, ModItems.hornHelmet);
				this.armor.put(ARMOR_2_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, ModItems.barbarianChestplate);
				this.armor.put(ARMOR_3_LEGS, ModItems.barbarianLeggings);
				this.armor.put(ARMOR_3_FEET, ModItems.barbarianBoots);
				
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
				
				for(int i = 0; i < ModItems.nordShield.length; i++)
				{
					this.shield.put(WEAPON_1 + i, ModItems.nordShield[i]);
				}
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.thrown.put(THROWN_1, ModItems.throwingAxe);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2, PotionTypes.STRENGTH);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1, PotionTypes.SLOWNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2, PotionTypes.HARMING);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.INVISIBILITY);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.POISON);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.WEAKNESS);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.SHARPNESS);
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FEATHER_FALLING);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Footman", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Axeman", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier3 = new AttributeVocation("Warrior", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier4 = new AttributeVocation("Huskarl", AttributeVocation.CLASS_SOLDIER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_1, ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier5 = new AttributeVocation("Thane", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_1, ALWAYS_BLOCK, rand);
				
				soldierTier5.helmet = Items.DIAMOND_HELMET;
				soldierTier5.chestplate = Items.DIAMOND_CHESTPLATE;
				soldierTier5.leggings = Items.DIAMOND_LEGGINGS;
				soldierTier5.boots = Items.DIAMOND_BOOTS;
				soldierTier5.meleeWeapon = Items.DIAMOND_SWORD;

				
				soldierTier4.setScale(1.5f);

				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this);

				AttributeVocation alchemistTier1 = new AttributeVocation("Troubadour", AttributeVocation.CLASS_ALCHEMIST, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation alchemistTier2 = new AttributeVocation("Skald", AttributeVocation.CLASS_ALCHEMIST, RANK_2, DEFAULT_UPGRADE_REQ, this);

				alchemistTier1.setHeal(true);
				alchemistTier2.setHeal(true);
				
				alchemistTier1.setCanRide(true);
				alchemistTier2.setCanRide(true);

				
				alchemistTier1.setUpgradeRight(alchemistTier2);
				
				this.alchemists.put(RANK_1, alchemistTier1);
				this.alchemists.put(RANK_2, alchemistTier2);
				
				soldierTier1.setUpgradeTree(soldierTier2, soldierTier3);
				soldierTier2.setUpgradeTree(soldierTier4, soldierTier5);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				
				this.soldiers.put(RANK_1, soldierTier1);
				this.soldiers.put(RANK_2, soldierTier2);
				this.soldiers.put(RANK_3, soldierTier3);
				this.soldiers.put(RANK_4, soldierTier4);
				this.soldiers.put(RANK_5, soldierTier5);
				
				this.archers.put(RANK_1, archerTier1);
				
				AttributeVocation villagerFarmer = new AttributeVocation("Farmer", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, Items.STONE_HOE, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerFisherman = new AttributeVocation("Fisherman", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, Items.FISHING_ROD, AttributeVocation.SUBCLASS_WORKER, Blocks.WATER);
				AttributeVocation villagerLumberman = new AttributeVocation("Lumberman", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.STONE_AXE, AttributeVocation.SUBCLASS_WORKER, Blocks.LOG);
				
				this.villagers.put(villagerFarmer.getRank(), villagerFarmer);
				this.villagers.put(villagerFisherman.getRank(), villagerFisherman);
				this.villagers.put(villagerLumberman.getRank(), villagerLumberman);
				
				AttributeVocation banditTier1 = new AttributeVocation("Raider", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation banditTier2 = new AttributeVocation("Berserker", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_OVERRIDE_NO_ARMOR);
				AttributeVocation banditTier3 = new AttributeVocation("Viking",  AttributeVocation.CLASS_BANDIT, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_9, POTION_CHANCE_4,HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, ALWAYS_BLOCK, rand);
				
				banditTier1.setUpgradeTree(banditTier2, banditTier3);
				banditTier2.setDamageOffest(20);
				banditTier2.setScale(1.8f);
				banditTier2.setBreakBlocks(true);
				
				AttributeVocation banditTier4 = new AttributeVocation("Barbarian", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1,!ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);

				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Mercenary", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_SOLDIER);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier6 = new AttributeVocation("Jarl", AttributeVocation.CLASS_RULER, RANK_5, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_9, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_3, ALWAYS_BLOCK, rand);
				this.rulers.put(1, soldierTier6);
			}
			break;
			case RACE_TYPE_LATIN:
			{
				this.armor.put(ARMOR_1_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, ModItems.romanHelmet);
				this.armor.put(ARMOR_2_CHEST, ModItems.romanChestplate);
				this.armor.put(ARMOR_2_LEGS, ModItems.romanLeggings);
				this.armor.put(ARMOR_2_FEET, ModItems.romanBoots);
				
				this.armor.put(ARMOR_3_HEAD, ModItems.romanHelmetElite);
				this.armor.put(ARMOR_3_CHEST, ModItems.romanChestplateElite);
				this.armor.put(ARMOR_3_LEGS, ModItems.romanLeggingsElite);
				this.armor.put(ARMOR_3_FEET, ModItems.romanBootsElite);
				
				this.meleeWeapons.put(WEAPON_1, ModItems.romanSword);
				this.meleeWeapons.put(WEAPON_2, ModItems.spear);
				this.meleeWeapons.put(WEAPON_3, ModItems.romanSword);
				
				this.shield.put(WEAPON_1, ModItems.romanShield);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
		
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.GOLDEN_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.GOLDEN_HORSE_ARMOR);
				//Tier 1 potions
				//On self
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2, PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3, PotionTypes.STRONG_REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4, PotionTypes.STRONG_HEALING);
				
				//Additional Slots
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_5, PotionTypes.FIRE_RESISTANCE);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_6, PotionTypes.LONG_FIRE_RESISTANCE);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_7, PotionTypes.STRENGTH);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_8, PotionTypes.STRONG_STRENGTH);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1, PotionTypes.WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3, PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4, PotionTypes.LONG_WEAKNESS);
				
				//Additional Slots
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_5, PotionTypes.LONG_STRENGTH);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_6, PotionTypes.LONG_SLOWNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_7, PotionTypes.SLOWNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_8, PotionTypes.STRENGTH);
				
				/**Tier 2 potions **/	
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.FIRE_RESISTANCE);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.SWIFTNESS);
				
				//Additional Slots
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_5 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_6 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_FIRE_RESISTANCE);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_7 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_8 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRONG_SWIFTNESS);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.POISON);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRONG_HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRONG_POISON);
				
				//Additional Slots
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_5 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_6 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_7 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.SLOWNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_8 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_SLOWNESS);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.wood_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.storm);
				//Weather slot
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.summon_ancient_warror);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.fireball_volley);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.stone_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.banish_aura);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.fireball);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.long_drop);
				//this.spells.put(SPELL_1, Spell.fireball);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.SMITE);
				
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.POWER);

				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FEATHER_FALLING);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_2, Enchantments.RESPIRATION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_2, Enchantments.THORNS);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_2, Enchantments.FIRE_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_2, Enchantments.PROTECTION);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Legionnaire", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Principle", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_7, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_2, ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Centurion", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_5, ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Auxillary", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation archerTier2 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation archerTier3 = new AttributeVocation("Legate", AttributeVocation.CLASS_ARCHER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_7, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_3, ALWAYS_BLOCK, rand);
				
				archerTier1.setUpgradeTree(archerTier2, null);
				archerTier2.setUpgradeTree(archerTier3, null);
				
				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_6, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_8, ALWAYS_BLOCK, rand);
				AttributeVocation mageTier2 = new AttributeVocation("Battlemage", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_6, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_9, ALWAYS_BLOCK, rand);
				
				mageTier1.setUpgradeTree(mageTier1, null);
				
				AttributeVocation alchemistTier1 = new AttributeVocation("Healer", AttributeVocation.CLASS_ALCHEMIST, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_4, ALWAYS_BLOCK, rand, true);
				AttributeVocation alchemistTier2 = new AttributeVocation("Physician", AttributeVocation.CLASS_ALCHEMIST, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_5,HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_5, ALWAYS_BLOCK, rand, true);
				AttributeVocation alchemistTier3 = new AttributeVocation("Alchemist", AttributeVocation.CLASS_ALCHEMIST, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_6, ALWAYS_BLOCK, rand, false);
				AttributeVocation alchemistTier4 = new AttributeVocation("Augurer", AttributeVocation.CLASS_ALCHEMIST, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_7, ALWAYS_BLOCK, rand, false);

				alchemistTier1.setUpgradeTree(alchemistTier2, null);
				alchemistTier2.setUsesLingering(true);
				alchemistTier2.setUsesPotionSlots(true);

				alchemistTier3.setUpgradeRight(alchemistTier4);
				alchemistTier4.setUsesLingering(true);
				alchemistTier4.setUsesPotionSlots(true);
				alchemistTier4.armorChoiceOffset(1);

				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitAlchemist = alchemistTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(RANK_1, soldierTier1);
				this.soldiers.put(RANK_2, soldierTier2);
				this.soldiers.put(RANK_3, soldierTier3);
				
				this.archers.put(RANK_1, archerTier1);
				this.archers.put(RANK_2, archerTier2);
				this.archers.put(RANK_3, archerTier3);
				
				this.mages.put(RANK_1, mageTier1);
				this.mages.put(RANK_2, mageTier2);
			
				this.alchemists.put(RANK_1, alchemistTier1);
				this.alchemists.put(RANK_2, alchemistTier2);
				this.alchemists.put(RANK_3, alchemistTier3);
				this.alchemists.put(RANK_4, alchemistTier4);


				
				AttributeVocation villagerArtisan = new AttributeVocation("Artisan", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerMerchant = new AttributeVocation("Merchant", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerBlacksmith = new AttributeVocation("Blacksmith", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_WORKER);
				
				this.villagers.put(villagerArtisan.getRank(), villagerArtisan);
				this.villagers.put(villagerMerchant.getRank(), villagerMerchant);
				this.merchants.put(1, villagerMerchant);
				this.villagers.put(villagerBlacksmith.getRank(), villagerBlacksmith);
				
				AttributeVocation banditTier1 = new AttributeVocation("Bandit", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_SOLDIER);
				AttributeVocation banditTier2 = new AttributeVocation("Reaver", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_6, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, ALWAYS_BLOCK, rand);
				//Archers
				AttributeVocation banditTier3 = new AttributeVocation("Brigand", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_6, HORSE_ARMOR_CHANCE_4, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				AttributeVocation banditTier4 = new AttributeVocation("Rogue", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_2,  ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				//Mages
				AttributeVocation banditTier5 = new AttributeVocation("Necromancer", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_3, ENCHANT_CHANCE_2, ALWAYS_BLOCK, rand, AttributeVocation.CLASS_MAGE);
				banditTier5.overrideSpells(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.summon_skeleton);
				banditTier5.overrideSpells(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.raise_zombies);
				
				//Alchemists
				AttributeVocation banditTier6 = new AttributeVocation("Witchdoctor", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_4, ENCHANT_CHANCE_3, ALWAYS_BLOCK, rand);
				AttributeVocation banditTier7 = new AttributeVocation("Blackguard", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_5, ALWAYS_BLOCK, rand);

				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
				this.bandits.put(5, banditTier5);
				this.bandits.put(6, banditTier6);
				this.bandits.put(7, banditTier7);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Gladiator", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_7, HORSE_ARMOR_CHANCE_8, ENCHANT_CHANCE_4, ALWAYS_BLOCK, rand, AttributeVocation.CLASS_SOLDIER);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Emperor", AttributeVocation.CLASS_RULER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_10, ENCHANT_CHANCE_9, ALWAYS_BLOCK, rand);
				this.rulers.put(1, soldierTier5);
				
				AttributeVocation ancientWarrior = new AttributeVocation("Ancient Warrior", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_4, ENCHANT_CHANCE_4, ALWAYS_BLOCK, rand, false);
				Spell.summon_ancient_warror.setToSpawn(ancientWarrior);

			}
			break;
			case RACE_TYPE_SLAV:
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
				this.armor.put(ARMOR_3_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.IRON_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_AXE);
				this.meleeWeapons.put(WEAPON_2, ModItems.bardiche);
				this.meleeWeapons.put(WEAPON_3, ModItems.bardiche);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_AXE);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.IRON_HORSE_ARMOR);
				

				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.ice_foot_greater);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.snow_foot_greater);
				//this.spells.put(SPELL_4, Spell.thunderbolt);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.greater_bury);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.thunderbolt);
		
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.ice_foot);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.snow_foot);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.fireball_volley);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.bury);
				
				this.arrowPotions.put(AttributeVocation.PA_SLOT_PASSIVE_1, PotionTypes.POISON);
				this.arrowPotions.put(AttributeVocation.PA_SLOT_PASSIVE_2, PotionTypes.SLOWNESS);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.KNOCKBACK);
				
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.PUNCH);

				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.FIRE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.THORNS);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.FIRE_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FROST_WALKER);
	
				
				AttributeVocation soldierTier1 = new AttributeVocation("Tribesman", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Warrior", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_5, ENCHANT_CHANCE_1, HORSE_ARMOR_CHANCE_0, false,rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Bogatyr", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, ENCHANT_CHANCE_2, HORSE_ARMOR_CHANCE_0, !ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Bowman", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation archerTier2 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation archerTier3 = new AttributeVocation("Mage Archer", AttributeVocation.CLASS_HYBRID_MAGE_ARCHER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, USES_POISONED_ARROWS, rand);
				AttributeVocation archerTier4 = new AttributeVocation("Marksman", AttributeVocation.CLASS_ARCHER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, USES_POISONED_ARROWS, rand);
				AttributeVocation archerTier5 = new AttributeVocation("Ranger", AttributeVocation.CLASS_HYBRID_ARCHER_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, USES_POISONED_ARROWS);
				
				archerTier1.setUpgradeTree(archerTier2, archerTier3);
				archerTier2.setUpgradeTree(archerTier4, archerTier5);
				
				AttributeVocation mageTier1 = new AttributeVocation("Warlock", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_9, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_5, !ALWAYS_BLOCK, rand);
				
				recruitSoldier = soldierTier1;
				recruitArcher = archerTier1;
				recruitMage = mageTier1;
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				this.archers.put(3, archerTier3);
				this.archers.put(4, archerTier4);
				this.archers.put(5, archerTier5);

				this.mages.put(1, mageTier1);
				
				AttributeVocation banditTier1 = new AttributeVocation("Mavka", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_SOLDER);
				AttributeVocation banditTier2 = new AttributeVocation("Bandit Archer", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				AttributeVocation banditTier3 = new AttributeVocation("Retinue", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_ARCHER);
				AttributeVocation banditTier4 = new AttributeVocation("Highwayman", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_ARCHER_SOLDIER);

				banditTier2.setUsesPoisonedArrows(true);
				banditTier4.setUsesPoisonedArrows(true);
				
				banditTier1.overrideSpells(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.fire_resistance);
				banditTier1.overrideSpells(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.flame_aura);
				banditTier1.overrideSpells(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.fireball);
				banditTier1.overrideSpells(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.fireball_volley);

				
				banditTier1.setUpgradeTree(banditTier2, null);
				banditTier2.setUpgradeTree(banditTier3, banditTier4);
				
				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
				
				AttributeVocation villagerHunter = new AttributeVocation("Hunter", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, Items.BOW, AttributeVocation.SUBCLASS_HUNTER);
				AttributeVocation villagerBard = new AttributeVocation("Bard", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerFletcher = new AttributeVocation("Fletcher", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_WORKER);
				
				this.villagers.put(villagerHunter.getRank(), villagerHunter);
				this.villagers.put(villagerBard.getRank(), villagerBard);
				this.villagers.put(villagerFletcher.getRank(), villagerFletcher);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Mercenary Archer", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Boyar", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_9, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_5, ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_ARCHER_SOLDIER);
				this.rulers.put(1, soldierTier5);
			}
			break;
			case RACE_TYPE_GERMAN:
			{
				this.armor.put(ARMOR_1_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, ModItems.gothicHelmet);
				this.armor.put(ARMOR_2_CHEST, ModItems.gothicChestplate);
				this.armor.put(ARMOR_2_LEGS, ModItems.gothicLeggings);
				this.armor.put(ARMOR_2_FEET, ModItems.gothicBoots);
				
				this.armor.put(ARMOR_3_HEAD, ModItems.gothicHelmet);
				this.armor.put(ARMOR_3_CHEST, ModItems.gothicChestplate);
				this.armor.put(ARMOR_3_LEGS, ModItems.gothicLeggings);
				this.armor.put(ARMOR_3_FEET, ModItems.gothicBoots);
				
				this.armor.put(ARMOR_4_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.DIAMOND_BOOTS);
				
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.GOLDEN_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.GOLDEN_HORSE_ARMOR);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.wood_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.life);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.summon_ancient_warror);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.drop);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.stone_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.greater_life);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.meteorball);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.thunderbolt);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.iron_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.superior_life);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.greater_meteorball);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.meteorstorm);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_3 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.iron_foot);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_4 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.drain_life);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_3 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.grave);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_4 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.skydrop);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.FIRE_ASPECT);
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_2, Enchantments.BANE_OF_ARTHROPODS);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.POWER);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_2, Enchantments.FLAME);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FEATHER_FALLING);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_2, Enchantments.RESPIRATION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_2, Enchantments.THORNS);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_2, Enchantments.FIRE_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_2, Enchantments.PROTECTION);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Militia", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Man-At-Arms", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_7, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Knight", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_10, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_9, ENCHANT_CHANCE_4, ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				archerTier1.setEnchantmentChance(ENCHANT_CHANCE_4);

				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier2 = new AttributeVocation("Mage Knight", AttributeVocation.CLASS_HYBRID_MAGE_SOLDER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_9, HORSE_ARMOR_CHANCE_7, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, rand);
				AttributeVocation mageTier3 = new AttributeVocation("Magician", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);
				mageTier1.setEnchantmentChance(ENCHANT_CHANCE_5);
				mageTier1.setEnchantmentChance(ENCHANT_CHANCE_8);

				
				mageTier1.setUpgradeTree(mageTier2, mageTier3);
				mageTier3.setUsesMagicSlot(true);
				
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
				
				AttributeVocation villagerSerf = new AttributeVocation("Serf", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, Items.WOODEN_HOE, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerPriest = new AttributeVocation("Priest", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_MERCHANT);
				AttributeVocation villagerScribe = new AttributeVocation("Scribe", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				
				this.villagers.put(villagerSerf.getRank(), villagerSerf);
				this.villagers.put(villagerPriest.getRank(), villagerPriest);
				this.villagers.put(villagerScribe.getRank(), villagerScribe);

				AttributeVocation mercenaryTier3 = new AttributeVocation("SpellSword", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_SOLDER);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Count", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_9, HORSE_ARMOR_CHANCE_1, ENCHANT_CHANCE_10, ALWAYS_BLOCK, rand);
				this.rulers.put(1, soldierTier5);
			}
			break;
			case RACE_TYPE_ARAB:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.DIAMOND_BOOTS);
				
				this.armor.put(ARMOR_5_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_5_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_5_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_5_FEET, Items.DIAMOND_BOOTS);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.IRON_AXE);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				this.meleeWeapons.put(WEAPON_5, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				//Tier 1 potions
				//On self
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2, PotionTypes.INVISIBILITY);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3, PotionTypes.SWIFTNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4, PotionTypes.FIRE_RESISTANCE);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1, PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3, PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4, PotionTypes.STRENGTH);
				
				/**Tier 2 potions **/	
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_INVISIBILITY);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_FIRE_RESISTANCE);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.POISON);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.LONG_SLOWNESS);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.poison_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.small_platform);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.poison);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.harm);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.spike_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.fire_resistance);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.sand_tomb);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.glass_tomb);
		
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.flaming_skin);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.teleport);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.curse);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.implode);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.strong_regeneration);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.teleport);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_3 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_4 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.iron_skin);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.curse_greater);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.implode_greater);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_3 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.stone_tomb);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_4 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.fireball_barrage);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.FIRE_ASPECT);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.FLAME);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.THORNS);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FEATHER_FALLING);
				
				
				AttributeVocation soldierTier1 = new AttributeVocation("Footman", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Infantry", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_6, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_3, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Mamluke", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_2, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier2 = new AttributeVocation("Sharpshooter", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				
				archerTier1.setUpgradeTree(archerTier2, null);
				
				AttributeVocation mageTier1 = new AttributeVocation("Magi", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier2 = new AttributeVocation("Magus", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier3 = new AttributeVocation("Sorcerer", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);
				mageTier1.setEnchantmentChance(ENCHANT_CHANCE_6);
				mageTier1.setEnchantmentChance(ENCHANT_CHANCE_8);
				mageTier1.setEnchantmentChance(ENCHANT_CHANCE_10);

				AttributeVocation alchemistTier1 = new AttributeVocation("Undeadhunter", AttributeVocation.CLASS_ALCHEMIST, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation alchemistTier2 = new AttributeVocation("Diviner", AttributeVocation.CLASS_ALCHEMIST, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_4, !ALWAYS_BLOCK, rand);
				
				alchemistTier1.setEnchantmentChance(ENCHANT_CHANCE_2);
				alchemistTier2.setHeal(true);
				
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
				this.mages.put(2, mageTier2);
				this.mages.put(3, mageTier3);
			
				this.alchemists.put(1, alchemistTier1);
				this.alchemists.put(1 + 1, alchemistTier2);
				
				AttributeVocation villagerNomad = new AttributeVocation("Nomad", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerScholar = new AttributeVocation("Scholar", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerQuartermaster = new AttributeVocation("Quartermaster", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.IRON_SWORD, AttributeVocation.SUBCLASS_TRAINER);
				
				this.villagers.put(villagerNomad.getRank(), villagerNomad);
				this.villagers.put(villagerScholar.getRank(), villagerScholar);
				this.villagers.put(villagerQuartermaster.getRank(), villagerQuartermaster);
				
				AttributeVocation banditTier1 = new AttributeVocation("Desert Bandit", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation banditTier2 = new AttributeVocation("Moor", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_MAGE);
				AttributeVocation banditTier3 = new AttributeVocation("Desert Rider", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ALCHEMIST);
				AttributeVocation banditTier4 = new AttributeVocation("Hashasin", AttributeVocation.CLASS_BANDIT, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_ARCHER);	
				AttributeVocation banditTier5 = new AttributeVocation("Shair", AttributeVocation.CLASS_BANDIT, RANK_4, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_10, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_MAGE);
				
				banditTier1.setUpgradeTree(banditTier4, null);
				banditTier2.setUpgradeTree(banditTier3, null);
				
				banditTier2.setAlwaysHorse(true);
				banditTier3.setAlwaysHorse(true);
				
				banditTier4.overrideSpells(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.NULL_SPELL);
				banditTier4.overrideSpells(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.NULL_SPELL);
				
				banditTier5.setUsesMagicSlot(true);
				
				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
				this.bandits.put(5, banditTier5);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Janissary", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, rand);
				mercenaryTier3.setDamageOffest(10);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Sultan", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_9, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_10, ALWAYS_BLOCK, rand);
				this.rulers.put(1, soldierTier5);
			}break;
			case RACE_TYPE_MONGOL:
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
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.GOLDEN_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.GOLDEN_HORSE_ARMOR);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, ModItems.spear);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				
				/*this.spells.put(SPELL_1, Spell.arrow_proof);

				this.spells.put(SPELL_3, Spell.summon_ancient_warror);
				this.spells.put(SPELL_4, Spell.fireball_volley);*/
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.strong_regeneration);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.greater_harm);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.web_encase);
				
				/*this.spells.put(SPELL_9, Spell.summon_ancient_warror);
				this.spells.put(SPELL_11, Spell.greater_fireball);
				this.spells.put(SPELL_12, Spell.firestorm);*/
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.FIRE_ASPECT);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.POWER);
				
				AttributeVocation banditTier1 = new AttributeVocation("Horseman", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, !IS_HEALER, ALWAYS_HORSE);
				AttributeVocation banditTier2 = new AttributeVocation("Horse Archer", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ,this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation banditTier3 = new AttributeVocation("Lancer",  AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, ALWAYS_BLOCK, rand, !IS_HEALER, ALWAYS_HORSE);
				
				banditTier1.setUpgradeTree(banditTier2, banditTier3);
				
				AttributeVocation banditTier4 = new AttributeVocation("Shaman",  AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
	
				banditTier2.setAlwaysHorse(true);
				banditTier4.setAlwaysHorse(true);
				
				this.bandits.put(banditTier1.getRank(), banditTier1);
				this.bandits.put(banditTier2.getRank(), banditTier2);
				this.bandits.put(banditTier3.getRank(), banditTier3);
				this.bandits.put(4, banditTier4);
			}break;
			case RACE_TYPE_VAMPIRE:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, ModItems.gothicChestplate);
				this.armor.put(ARMOR_3_LEGS, ModItems.gothicLeggings);
				this.armor.put(ARMOR_3_FEET, ModItems.gothicBoots);
				
				/*this.armor.put(ARMOR_4_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.DIAMOND_BOOTS);*/
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.IRON_HORSE_ARMOR);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				//this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.FIRE_ASPECT);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.POWER);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FEATHER_FALLING);
				
				//this.potions.put(POTION_1, PotionTypes.HEALING);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1, Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2, Spell.small_ditch);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1, Spell.summon_bat);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2, Spell.arrow_volley);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.thrall);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.arrow_meteorball);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_1), Spell.thunderbolt);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.thrall_greater);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.arrow_greater_volley);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_2), Spell.arrowstorm);
				
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.arrow_proof);
				this.spells.put(AttributeVocation.SPELL_SLOT_PASSIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.servant);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_1 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.arrow_meteorball_volley);
				this.spells.put(AttributeVocation.SPELL_SLOT_ACTIVE_2 + (AttributeVocation.ALL_SPELL_SLOTS * RANK_3), Spell.summon_bats);
				
				AttributeVocation banditTier1 = new AttributeVocation("Peasant", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ,  this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_4, !ALWAYS_BLOCK, rand, !IS_HEALER, !ALWAYS_HORSE);
				AttributeVocation banditTier2 = new AttributeVocation("Brute", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_5, !ALWAYS_BLOCK, rand);
				AttributeVocation banditTier3 = new AttributeVocation("Liege", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_2, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_7, !ALWAYS_BLOCK, rand);
				AttributeVocation banditTier5 = new AttributeVocation("Count",  AttributeVocation.CLASS_HYBRID_MAGE_SOLDER, RANK_4, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_10, ENCHANT_CHANCE_5, !ALWAYS_BLOCK, rand);
				AttributeVocation banditTier4 = new AttributeVocation("Black Knight",  AttributeVocation.CLASS_HYBRID_MAGE_SOLDER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_10, ENCHANT_CHANCE_8, ALWAYS_BLOCK, rand, !IS_HEALER, !ALWAYS_HORSE);
				
				banditTier2.setScale(2.5f);
				banditTier4.setScale(1.7f);
				
				banditTier2.setBreakBlocks(true);
				banditTier4.setBreakBlocks(true);
				
				banditTier1.setUpgradeTree(banditTier2, banditTier3);
				banditTier2.setUpgradeTree(banditTier4, null);
				banditTier3.setUpgradeTree(banditTier5, null);
				
				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
				this.bandits.put(5, banditTier5);

			}break;
			case RACE_TYPE_GREEK:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.IRON_BOOTS);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.IRON_HORSE_ARMOR);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_2, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.DIAMOND_SWORD);
				//this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.BANE_OF_ARTHROPODS);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.FLAME);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FIRE_PROTECTION);
				
				//Tier 1 potions
				//On self
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2, PotionTypes.WATER_BREATHING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3, PotionTypes.LEAPING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4, PotionTypes.NIGHT_VISION);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1, PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2, PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3, PotionTypes.WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4, PotionTypes.POISON);
				
				/**Tier 2 potions **/	
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.SWIFTNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.STRENGTH);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_1), PotionTypes.SLOWNESS);
				
				/**Tier 3 potions **/	
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.INVISIBILITY);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.FIRE_RESISTANCE);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.POISON);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.LONG_WEAKNESS);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_2), PotionTypes.HEALING);
				
				/**Tier 4 potions **/	
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.LONG_REGENERATION);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.LONG_INVISIBILITY);
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.LONG_FIRE_RESISTANCE);

				//Attack
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_1 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.STRONG_POISON);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_2 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.STRONG_HARMING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_3 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.STRONG_HEALING);
				this.potions.put(AttributeVocation.POTION_SLOT_ACTIVE_4 + (AttributeVocation.ALL_POTION_SLOTS * RANK_3), PotionTypes.LONG_SLOWNESS);
				
				
				AttributeVocation soldierTier1 = new AttributeVocation("Peasant", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Psilo", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_6, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_3, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Hoplite", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				AttributeVocation archerTier1 = new AttributeVocation("Skirmisher", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_2, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier2 = new AttributeVocation("Peltast", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier3 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
		
				archerTier1.setUpgradeTree(archerTier2, null);
				archerTier2.setUpgradeTree(archerTier3, null);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				this.archers.put(3, archerTier3);

				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier2 = new AttributeVocation("Transmutator", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier3 = new AttributeVocation("Transumtatist", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);

				mageTier1.setUpgradeRight(mageTier2);
				mageTier2.setUpgradeRight(mageTier3);
				
				this.mages.put(1, mageTier1);
				this.mages.put(2, mageTier2);
				this.mages.put(3, mageTier3);

				AttributeVocation alchemistTier1 = new AttributeVocation("Herbalist", AttributeVocation.CLASS_ALCHEMIST, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation alchemistTier2 = new AttributeVocation("Shaman", AttributeVocation.CLASS_ALCHEMIST, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_4, !ALWAYS_BLOCK, rand);
				AttributeVocation alchemistTier3 = new AttributeVocation("Druid", AttributeVocation.CLASS_ALCHEMIST, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_2, POTION_CHANCE_10, HORSE_ARMOR_CHANCE_2, ENCHANT_CHANCE_4, !ALWAYS_BLOCK, rand);

				alchemistTier1.setUsesPotionSlots(true);
				
				alchemistTier1.setUpgradeRight(alchemistTier2);
				alchemistTier2.setUpgradeRight(alchemistTier3);
				
				this.alchemists.put(1, alchemistTier1);
				this.alchemists.put(2, alchemistTier2);
				this.alchemists.put(3, alchemistTier3);

				AttributeVocation villagerPeasant = new AttributeVocation("Peasant", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerLeatherworker = new AttributeVocation("Leatherworker", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerBanker = new AttributeVocation("Banker", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.IRON_SWORD, AttributeVocation.SUBCLASS_TRAINER);
				
				this.villagers.put(villagerPeasant.getRank(), villagerPeasant);
				this.villagers.put(villagerLeatherworker.getRank(), villagerLeatherworker);
				this.villagers.put(villagerBanker.getRank(), villagerBanker);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Monk", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, rand);
				
				this.mercenaries.put(1, mercenaryTier3);
				
				AttributeVocation soldierTier5 = new AttributeVocation("Emperor", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_9, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_10, ALWAYS_BLOCK, rand);
				
				this.rulers.put(1, soldierTier5);
				
				AttributeVocation banditTier1 = new AttributeVocation("Heretic", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ALCHEMIST);
				AttributeVocation banditTier2 = new AttributeVocation("Occultist", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ALCHEMIST);
				AttributeVocation banditTier3 = new AttributeVocation("Soothsayer", AttributeVocation.CLASS_BANDIT, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ALCHEMIST);
				AttributeVocation banditTier4 = new AttributeVocation("Pythia", AttributeVocation.CLASS_BANDIT, RANK_4, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ALCHEMIST);
				
				banditTier1.setUpgradeRight(banditTier2);
				banditTier2.setUpgradeRight(banditTier3);
				banditTier3.setUpgradeRight(banditTier4);
				
				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
			}
			break;
			case RACE_TYPE_FRANK:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.DIAMOND_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.DIAMOND_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.IRON_BOOTS);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.DIAMOND_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.DIAMOND_HORSE_ARMOR);
				
				this.meleeWeapons.put(WEAPON_1, ModItems.spear);
				this.meleeWeapons.put(WEAPON_2, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				//this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.SMITE);
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.PUNCH);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.PROTECTION);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Spearman", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Swordsman", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_6, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_3, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Knight", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier4 = new AttributeVocation("Paladin", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				soldierTier3.setUpgradeTree(soldierTier4, null);
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				this.soldiers.put(4, soldierTier4);
				
				AttributeVocation archerTier1 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_2, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier2 = new AttributeVocation("Sharpshooter", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				
				archerTier1.setUpgradeTree(archerTier2, null);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				
				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier2 = new AttributeVocation("Wizard", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier3 = new AttributeVocation("Theurgist", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier4 = new AttributeVocation("Illusionist", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);
				
				mageTier1.setUpgradeRight(mageTier2);
				mageTier2.setUpgradeTree(mageTier3, mageTier4);
				
				this.mages.put(1, mageTier1);
				this.mages.put(2, mageTier2);
				this.mages.put(3, mageTier3);
				this.mages.put(4, mageTier4);
				
				AttributeVocation villagerSerf = new AttributeVocation("Serf", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerCarpenter = new AttributeVocation("Carpenter", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerVassal = new AttributeVocation("Vassal", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.IRON_SWORD, AttributeVocation.SUBCLASS_TRAINER);
				
				this.villagers.put(villagerSerf.getRank(), villagerSerf);
				this.villagers.put(villagerCarpenter.getRank(), villagerCarpenter);
				this.villagers.put(villagerVassal.getRank(), villagerVassal);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Templar", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, rand);

				this.mercenaries.put(1, mercenaryTier3);

				AttributeVocation soldierTier5 = new AttributeVocation("Lord", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_9, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_10, ALWAYS_BLOCK, rand);
				
				this.rulers.put(1, soldierTier5);

				AttributeVocation banditTier1 = new AttributeVocation("Bandit", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_SOLDER);
				AttributeVocation banditTier2 = new AttributeVocation("Bandit Horseman", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				AttributeVocation banditTier3 = new AttributeVocation("Lone Knight", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_ARCHER);
				AttributeVocation banditTier4 = new AttributeVocation("Bandit Leader", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_ARCHER_SOLDIER);
				
				banditTier1.setUpgradeTree(banditTier2, null);
				banditTier2.setUpgradeTree(banditTier3, banditTier4);

				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
			}
			break;
			case RACE_TYPE_BRITON:
			{
				this.armor.put(ARMOR_1_HEAD, Items.LEATHER_HELMET);
				this.armor.put(ARMOR_1_CHEST, Items.LEATHER_CHESTPLATE);
				this.armor.put(ARMOR_1_LEGS, Items.LEATHER_LEGGINGS);
				this.armor.put(ARMOR_1_FEET, Items.LEATHER_BOOTS);
				
				this.armor.put(ARMOR_2_HEAD, Items.CHAINMAIL_HELMET);
				this.armor.put(ARMOR_2_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_2_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_2_FEET, Items.CHAINMAIL_BOOTS);
				
				this.armor.put(ARMOR_3_HEAD, Items.IRON_HELMET);
				this.armor.put(ARMOR_3_CHEST, Items.CHAINMAIL_CHESTPLATE);
				this.armor.put(ARMOR_3_LEGS, Items.CHAINMAIL_LEGGINGS);
				this.armor.put(ARMOR_3_FEET, Items.IRON_BOOTS);
				
				this.armor.put(ARMOR_4_HEAD, Items.DIAMOND_HELMET);
				this.armor.put(ARMOR_4_CHEST, Items.IRON_CHESTPLATE);
				this.armor.put(ARMOR_4_LEGS, Items.IRON_LEGGINGS);
				this.armor.put(ARMOR_4_FEET, Items.DIAMOND_BOOTS);
				
				this.horseArmors.put(ARMOR_1_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_2_HORSE, Items.IRON_HORSE_ARMOR);
				this.horseArmors.put(ARMOR_3_HORSE, Items.IRON_HORSE_ARMOR);
				
				this.meleeWeapons.put(WEAPON_1, Items.STONE_AXE);
				this.meleeWeapons.put(WEAPON_2, Items.STONE_SWORD);
				this.meleeWeapons.put(WEAPON_3, Items.IRON_SWORD);
				this.meleeWeapons.put(WEAPON_4, Items.DIAMOND_SWORD);
				
				this.shield.put(WEAPON_1, Items.SHIELD);
				
				this.rangedWeapons.put(WEAPON_1, Items.BOW);
				
				this.swordEnchantments.put(AttributeVocation.SWORD_ENCHANT_1, Enchantments.SHARPNESS);
				
				this.bowEnchantments.put(AttributeVocation.BOW_ENCHANT_1, Enchantments.POWER);
				
				this.headEnchantments.put(AttributeVocation.HEAD_ENCHANT_1, Enchantments.PROJECTILE_PROTECTION);
				this.chestEnchantments.put(AttributeVocation.CHEST_ENCHANT_1, Enchantments.BLAST_PROTECTION);
				this.legsEnchantments.put(AttributeVocation.LEGS_ENCHANT_1, Enchantments.FIRE_PROTECTION);
				this.bootsEnchantments.put(AttributeVocation.BOOTS_ENCHANT_1, Enchantments.FROST_WALKER);
				
				this.potions.put(AttributeVocation.POTION_SLOT_PASSIVE_1, PotionTypes.HEALING);
				
				AttributeVocation soldierTier1 = new AttributeVocation("Page", AttributeVocation.CLASS_SOLDIER, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation soldierTier2 = new AttributeVocation("Squire", AttributeVocation.CLASS_SOLDIER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_6, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_3, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation soldierTier3 = new AttributeVocation("Knight", AttributeVocation.CLASS_SOLDIER, RANK_3, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_8, POTION_CHANCE_5, HORSE_ARMOR_CHANCE_6, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand);

				soldierTier1.setUpgradeTree(soldierTier2, null);
				soldierTier2.setUpgradeTree(soldierTier3, null);
				
				this.soldiers.put(1, soldierTier1);
				this.soldiers.put(2, soldierTier2);
				this.soldiers.put(3, soldierTier3);
				
				AttributeVocation archerTier1 = new AttributeVocation("Skirmisher", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_2, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier2 = new AttributeVocation("Archer", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier3 = new AttributeVocation("Marksman", AttributeVocation.CLASS_ARCHER, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, POTION_CHANCE_1, HORSE_ARMOR_CHANCE_2, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);
				AttributeVocation archerTier4 = new AttributeVocation("Longbowman", AttributeVocation.CLASS_ARCHER, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_3, POTION_CHANCE_4, HORSE_ARMOR_CHANCE_5, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand);

				archerTier1.setUpgradeTree(archerTier2, null);
				archerTier2.setUpgradeTree(archerTier3, null);
				archerTier3.setUpgradeTree(archerTier4, null);
				
				this.archers.put(1, archerTier1);
				this.archers.put(2, archerTier2);
				this.archers.put(3, archerTier3);
				this.archers.put(4, archerTier4);
				
				AttributeVocation mageTier1 = new AttributeVocation("Mage", AttributeVocation.CLASS_MAGE, RANK_1, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier2 = new AttributeVocation("Thaumaturgist", AttributeVocation.CLASS_MAGE, RANK_2, DEFAULT_UPGRADE_REQ, this);
				AttributeVocation mageTier3 = new AttributeVocation("Thaumaturge", AttributeVocation.CLASS_MAGE, RANK_3, DEFAULT_UPGRADE_REQ, this);
				mageTier1.setUpgradeRight(mageTier2);
				mageTier2.setUpgradeRight(mageTier3);
				
				this.mages.put(1, mageTier1);
				this.mages.put(2, mageTier2);
				this.mages.put(3, mageTier3);
				
				AttributeVocation villagerSerf = new AttributeVocation("Serf", AttributeVocation.CLASS_VILLAGER, RANK_1, DEFAULT_UPGRADE_REQ, this, null, AttributeVocation.SUBCLASS_FARMER);
				AttributeVocation villagerBaker = new AttributeVocation("Baker", AttributeVocation.CLASS_VILLAGER, RANK_2, DEFAULT_UPGRADE_REQ, this, Items.BOOK, AttributeVocation.SUBCLASS_CRAFTER);
				AttributeVocation villagerVassal = new AttributeVocation("Vassal", AttributeVocation.CLASS_VILLAGER, RANK_3, DEFAULT_UPGRADE_REQ, this, Items.IRON_SWORD, AttributeVocation.SUBCLASS_TRAINER);
				
				this.villagers.put(villagerSerf.getRank(), villagerSerf);
				this.villagers.put(villagerBaker.getRank(), villagerBaker);
				this.villagers.put(villagerVassal.getRank(), villagerVassal);
				
				AttributeVocation mercenaryTier3 = new AttributeVocation("Mercenary Knight", AttributeVocation.CLASS_MERCENARY, RANK_3, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, SHIELD_CHANCE_5, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_6, !ALWAYS_BLOCK, rand);

				this.mercenaries.put(1, mercenaryTier3);

				AttributeVocation soldierTier5 = new AttributeVocation("King", AttributeVocation.CLASS_RULER, RANK_4, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, USES_SHIELD, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_9, DEFAULT_UPGRADE_REQ, ENCHANT_CHANCE_10, ALWAYS_BLOCK, rand);
				
				this.rulers.put(1, soldierTier5);

				AttributeVocation banditTier1 = new AttributeVocation("Thief", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, !CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_SOLDER);
				AttributeVocation banditTier2 = new AttributeVocation("Pillager", AttributeVocation.CLASS_BANDIT, RANK_1, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_8, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_1, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_ARCHER);
				AttributeVocation banditTier3 = new AttributeVocation("Marauder", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_3, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_2, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_MAGE_ARCHER);
				AttributeVocation banditTier4 = new AttributeVocation("Savage", AttributeVocation.CLASS_BANDIT, RANK_2, DEFAULT_UPGRADE_REQ, this, CAN_RIDE, !USES_SHIELD, SHIELD_CHANCE_0, POTION_CHANCE_0, HORSE_ARMOR_CHANCE_0, ENCHANT_CHANCE_3, !ALWAYS_BLOCK, rand, AttributeVocation.CLASS_HYBRID_ARCHER_SOLDIER);
				
				banditTier1.setUpgradeRight(banditTier2);
				banditTier2.setUpgradeTree(banditTier3, banditTier4);
				
				this.bandits.put(1, banditTier1);
				this.bandits.put(2, banditTier2);
				this.bandits.put(3, banditTier3);
				this.bandits.put(4, banditTier4);
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

	public HashMap<Integer, Spell> getSpells() {
		return spells;
	}

	public void setSpells(HashMap<Integer, Spell> spells) {
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
	
	public AttributeVocation getRandomRecruitVocation() {
		int type = rand.nextInt(AttributeVocation.CLASS_ALCHEMIST) + 1;
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
			if(race.type == id)
			{
				return race;
			}
		}
		return null;
	}
	
	
	public ResourceLocation getRandomSkinM(int vocation) {
		int i = 0;
		
		AttributeVocation job = AttributeVocation.getVocationFromID(vocation);
		return new ResourceLocation(Resources.MODID, mSkins+job.getName().toLowerCase().replace(" ", "_")+"_"+i+".png");
	}
	
	public ResourceLocation getRandomSkinF(int vocation) {
		int i = 0;
		
		AttributeVocation job = AttributeVocation.getVocationFromID(vocation);
		return new ResourceLocation(Resources.MODID, fSkins+job.getName().toLowerCase().replace(" ", "_")+"_"+i+".png");

	}

	public int getKnockbackBonus() {
		// TODO Auto-generated method stub
		return knockbackBonus;
	}
	
	public AttributeVocation getRandomMercenary(World world) {
		if(this.mercenaries.isEmpty()) return null;
		return this.mercenaries.get(world.rand.nextInt(mercenaries.size()) + 1);
	}
	
	public AttributeVocation getRandomMerchant(World world) {
		if(this.merchants.isEmpty()) return null;
		return this.merchants.get(world.rand.nextInt(merchants.size()) + 1);
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

	public static AttributeRace getRaceFromString(String string) {
		switch(string.toLowerCase())
		{
			case "nord":
			{
				return nords;
			}
			case "nords":
			{
				return nords;
			}
			case "latin":
			{
				return latins;
			}
			case "latins":
			{
				return latins;
			}
			case "roman":
			{
				return latins;
			}
			case "romans":
			{
				return latins;
			}
			case "german":
			{
				return germans;
			}
			case "germans":
			{
				return germans;
			}
			case "gothic":
			{
				return germans;
			}
			case "gothics":
			{
				return germans;
			}
			case "slav":
			{
				return slavs;
			}
			case "slavs":
			{
				return slavs;
			}
			case "slavic":
			{
				return slavs;
			}
			case "arab":
			{
				return arabs;
			}
			case "greek":
			{
				return greeks;
			}
			case "briton":
			{
				return britons;
			}
			case "frank":
			{
				return franks;
			}
			case "mongol":
			{
				return mongols;
			}
			case "mongols":
			{
				return mongols;
			}
			case "vampire":
			{
				return vampires;
			}
			default:
			{
				return null;
			}
		}
	}
	
	private HashMap<Integer, AttributeVocation> getVocationList(int type)
	{
		switch(type)
		{
			case AttributeVocation.CLASS_SOLDIER:
			{
				return soldiers;
			}
			case AttributeVocation.CLASS_ARCHER:
			{
				return archers;
			}
			case AttributeVocation.CLASS_MAGE:
			{
				return mages;
			}
			case AttributeVocation.CLASS_ALCHEMIST:
			{
				return alchemists;
			}
			case AttributeVocation.CLASS_MERCENARY:
			{
				return mercenaries;
			}
			case AttributeVocation.CLASS_BANDIT:
			{
				return bandits;
			}
			case AttributeVocation.CLASS_RULER:
			{
				return rulers;
			}
			case AttributeVocation.CLASS_VILLAGER:
			{
				return villagers;
			}
			default:
			{
				return null;
			}
		}
	}

	public AttributeVocation getVocationFromString(String string) {
		for(int i = 0; i < AttributeVocation.NUM_CLASSES; i++)
		{
			HashMap<Integer, AttributeVocation> list = this.getVocationList(i);
			if(list == null) continue;
			
			for(int n = 0; n < list.size(); n++)
			{
				AttributeVocation vocation = list.get(n + 1);
				
				//System.out.println("Searching: "+vocation.getName().toLowerCase().replace(" ", "_")+" "+string);
				
				if(vocation.getName().toLowerCase().replace(" ", "_").contains(string.toLowerCase()))
				{
					return vocation;
				}
			}
		}
		
		return null;
	}

	public String listClassNames() {
		String result = this.getName().toUpperCase()+" LIST: \n";
		
		for(int i = 0; i < AttributeVocation.NUM_CLASSES; i++)
		{
			HashMap<Integer, AttributeVocation> list = this.getVocationList(i);
			if(list == null) continue;
			
			for(int n = 0; n < list.size(); n++)
			{
				AttributeVocation vocation = list.get(n + 1);
				//System.out.println(vocation);
				result += vocation.getName().toLowerCase().replace(" ", "_")+"\n";
			}
		}
		
		return result;
	}

	public static String listRaceNames() {
		String result = "CULTURE LIST: \n";
		
		for(AttributeRace race : races)
		{
			result += race.getName().toLowerCase()+"\n";
		}
		
		return result;
	}

	public AttributeVocation getRandomSoldier(World worldIn) {
		if(this.archers.size() > 0 && this.rand.nextInt(100) <= 25) return archers.get(this.rand.nextInt(this.archers.size()) + 1);
		else if(this.mages.size() > 0 && this.rand.nextInt(100) <= 50) return mages.get(this.rand.nextInt(this.mages.size()) + 1);
		else if(this.alchemists.size() > 0 && this.rand.nextInt(100) <= 75) return alchemists.get(this.rand.nextInt(this.alchemists.size()) + 1);
		else if(this.soldiers.size() > 0) return soldiers.get(this.rand.nextInt(this.soldiers.size()) + 1);	
		else return null;
	}

	public AttributeVocation getRandomVillager(World world) {
		if(this.archers.size() > 0 && this.rand.nextInt(100) <= 1/6) return archers.get(this.rand.nextInt(this.archers.size()) + 1);
		else if(this.mages.size() > 0 && this.rand.nextInt(100) <= 2/6) return mages.get(this.rand.nextInt(this.mages.size()) + 1);
		else if(this.alchemists.size() > 0 && this.rand.nextInt(100) <= 3/6) return alchemists.get(this.rand.nextInt(this.alchemists.size()) + 1);
		else if(this.soldiers.size() > 0 && this.rand.nextInt(100) <= 4/6)  return soldiers.get(this.rand.nextInt(this.soldiers.size()) + 1);	//return alchemists.get(this.rand.nextInt(this.alchemists.size()) + 1);
		else if(this.villagers.size() > 0) return villagers.get(this.rand.nextInt(this.villagers.size()) + 1);	
		return null;
	}

	public int getMagicBonus() {
		return this.magicBonus;
	}

	public int getHorseHealthBonus() {
		return horseHealthBonus;
	}
}
