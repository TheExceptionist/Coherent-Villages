package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.spells.Spell;

@Mod.EventBusSubscriber(modid = Resources.MODID)
public class ModItems {
	public static ArmorMaterial NORD_MAIL = EnumHelper.addArmorMaterial("nord_mail", Resources.MODID+":nord_mail", 1500, new int[]{3 , 4, 7, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 8F);
	public static ArmorMaterial ROMAN_ARMOR = EnumHelper.addArmorMaterial("roman_armor", Resources.MODID+":roman_armor_2", 2000, new int[]{4, 4, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 12F);
	public static ArmorMaterial ROMAN_ARMOR_ELITE = EnumHelper.addArmorMaterial("roman_armor_elite", Resources.MODID+":roman_armor", 2500, new int[]{5, 6, 7, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 15F);
	public static ArmorMaterial GOTHIC_ARMOR = EnumHelper.addArmorMaterial("gothic_armor", Resources.MODID+":gothic_armor", 2250, new int[]{5, 8, 5, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 20F);
	
	public static ArmorMaterial NORD_HELMET = EnumHelper.addArmorMaterial("horn_helmet", Resources.MODID+":horn_helmet", 1700, new int[]{6, 4, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 10F);
	public static ArmorMaterial ROMAN_ARMOR_HELMET = EnumHelper.addArmorMaterial("roman_helmet", Resources.MODID+":roman_helmet", 2000, new int[]{4, 4, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 12F);
	public static ArmorMaterial ROMAN_ARMOR_ELITE_HELMET = EnumHelper.addArmorMaterial("roman_helmet_elite", Resources.MODID+":roman_helmet_elite", 2000, new int[]{4, 4, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 12F);
	
	public static ToolMaterial ROMAN_STEEL = EnumHelper.addToolMaterial("roman_steel", 2, 2000, 6.5f, 5.0f, 7);//.addToolMaterial("nord_mail", Resources.MODID+":nord_mail", 1500, new int[]{3 , 4, 7, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8F);
	
	public static ToolMaterial SLAV_STEEL = EnumHelper.addToolMaterial("slav_steel", 1, 1500, 3.5f, 10.0f, 2);//.addToolMaterial("nord_mail", Resources.MODID+":nord_mail", 1500, new int[]{3 , 4, 7, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8F);
	
	
	public static ItemWeaponThrowable throwingAxe;
	
	public static ItemModArmor barbarianHelmet;// = new ItemNordArmor("barbarian_helmet", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianChestplate;// = new ItemNordArmor("barbarian_chestplate", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianLeggings;// = new ItemNordArmor("barbarian_leggings", ItemNordArmor.NORD_MAIL, 1, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianBoots;
	
	public static ItemModArmor romanHelmet;// = new ItemNordArmor("barbarian_helmet", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanChestplate;// = new ItemNordArmor("barbarian_chestplate", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanLeggings;// = new ItemNordArmor("barbarian_leggings", ItemNordArmor.NORD_MAIL, 1, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanBoots;
	
	public static ItemModArmor romanHelmetElite;// = new ItemNordArmor("barbarian_helmet", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanChestplateElite;// = new ItemNordArmor("barbarian_chestplate", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanLeggingsElite;// = new ItemNordArmor("barbarian_leggings", ItemNordArmor.NORD_MAIL, 1, EntityEquipmentSlot.HEAD);
	public static ItemModArmor romanBootsElite;
	
	public static ItemModArmor gothicHelmet;// = new ItemNordArmor("barbarian_helmet", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor gothicChestplate;// = new ItemNordArmor("barbarian_chestplate", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor gothicLeggings;// = new ItemNordArmor("barbarian_leggings", ItemNordArmor.NORD_MAIL, 1, EntityEquipmentSlot.HEAD);
	public static ItemModArmor gothicBoots;
	
	public static ItemModHelmet hornHelmet;
	
	public static ItemModShield[] nordShield = new ItemModShield[6];
	public static ItemModShield romanShield;
	
	public static ItemModSword romanSword;
	public static ItemSpear spear;
	public static ItemSpear bardiche;
	
	public static ItemTome tomeSummonAncient;
	public static ItemTome tomeFireball;
	
	public static void init()
	{
		throwingAxe = new ItemWeaponThrowable("throwing_axe", 16, Items.IRON_AXE);//.setMaxStackSize(maxStackSize)
		
		barbarianHelmet = new ItemModArmor("barbarian_helmet", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
		barbarianChestplate = new ItemModArmor("barbarian_chestplate", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.CHEST);
		barbarianLeggings = new ItemModArmor("barbarian_leggings", ModItems.NORD_MAIL, 1, EntityEquipmentSlot.LEGS);
		barbarianBoots = new ItemModArmor("barbarian_boots", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.FEET);
		
		for(int i = 0; i < nordShield.length; i++)
		{
			nordShield[i] = new ItemModShield("nord_shield_"+i, i);
			//System.out.println("Create: "+nordShield[i]);
		}
		
		romanShield = new ItemModShield("roman_shield");
		
		romanSword = new ItemModSword("roman_sword", ModItems.ROMAN_STEEL);
		spear = new ItemSpear("spear", ModItems.ROMAN_STEEL);
		bardiche = new ItemSpear("bardiche", ModItems.SLAV_STEEL);
		
		hornHelmet = new ItemModHelmet("horn_helmet", ModItems.NORD_HELMET, 0, EntityEquipmentSlot.HEAD);
		
		romanHelmet = new ItemModArmor("roman_helmet", ModItems.ROMAN_ARMOR, 0, EntityEquipmentSlot.HEAD);
		romanChestplate = new ItemModArmor("roman_chestplate", ModItems.ROMAN_ARMOR, 0, EntityEquipmentSlot.CHEST);
		romanLeggings = new ItemModArmor("roman_leggings", ModItems.ROMAN_ARMOR, 0, EntityEquipmentSlot.LEGS);
		romanBoots = new ItemModArmor("roman_boots", ModItems.ROMAN_ARMOR, 0, EntityEquipmentSlot.FEET);
		
		romanHelmetElite = new ItemModArmor("roman_helmet_elite", ModItems.ROMAN_ARMOR_ELITE, 0, EntityEquipmentSlot.HEAD);
		romanChestplateElite = new ItemModArmor("roman_chestplate_elite", ModItems.ROMAN_ARMOR_ELITE, 0, EntityEquipmentSlot.CHEST);
		romanLeggingsElite = new ItemModArmor("roman_leggings_elite", ModItems.ROMAN_ARMOR_ELITE, 0, EntityEquipmentSlot.LEGS);
		romanBootsElite = new ItemModArmor("roman_boots_elite", ModItems.ROMAN_ARMOR_ELITE, 0, EntityEquipmentSlot.FEET);
		
		gothicHelmet = new ItemModArmor("gothic_helmet", ModItems.GOTHIC_ARMOR, 0, EntityEquipmentSlot.HEAD);
		gothicChestplate = new ItemModArmor("gothic_chestplate", ModItems.GOTHIC_ARMOR, 0, EntityEquipmentSlot.CHEST);
		gothicLeggings = new ItemModArmor("gothic_leggings", ModItems.GOTHIC_ARMOR, 0, EntityEquipmentSlot.LEGS);
		gothicBoots = new ItemModArmor("gothic_boots", ModItems.GOTHIC_ARMOR, 0, EntityEquipmentSlot.FEET);
		
		tomeSummonAncient = new ItemTome("summon_tome_0", Spell.summon_ancient_warror);
		tomeFireball = new ItemTome("attack_tome_0", Spell.fireball);
	}
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) 
	{
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		//registry.register(throwingAxe);
		
		registry.registerAll(barbarianHelmet);
		registry.registerAll(barbarianChestplate);
		registry.registerAll(barbarianLeggings);
		registry.registerAll(barbarianBoots);
		
		for(int i = 0; i < nordShield.length; i++)
		{
			registry.registerAll(nordShield[i]);
		}
		
		registry.registerAll(nordShield[0]);
		registry.registerAll(romanShield);
		
		registry.registerAll(romanSword);
		registry.registerAll(spear);
		registry.registerAll(bardiche);
		
		//registry.registerAll(hornHelmet);
		registry.registerAll(romanHelmet);
		registry.registerAll(romanChestplate);
		registry.registerAll(romanLeggings);
		registry.registerAll(romanBoots);
		
		registry.registerAll(romanHelmetElite);
		registry.registerAll(romanChestplateElite);
		registry.registerAll(romanLeggingsElite);
		registry.registerAll(romanBootsElite);
		
		registry.registerAll(gothicHelmet);
		registry.registerAll(gothicChestplate);
		registry.registerAll(gothicLeggings);
		registry.registerAll(gothicBoots);

		registry.registerAll(tomeSummonAncient);
		registry.registerAll(tomeFireball);
		
		registry.registerAll(throwingAxe);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(barbarianHelmet);
		registerRender(barbarianChestplate);
		registerRender(barbarianLeggings);
		registerRender(barbarianBoots);
		
		for(int i = 0; i < nordShield.length; i++)
		{
			//System.out.println("Render: "+nordShield[i]);

			registerRender(nordShield[i]);
		}
		
		registerRender(romanShield);
		
		registerRender(romanSword);
		registerRender(spear);
		registerRender(bardiche);
		
		//registerRender(hornHelmet);
		registerRender(romanHelmet);
		registerRender(romanChestplate);
		registerRender(romanLeggings);
		registerRender(romanBoots);
		
		registerRender(romanHelmetElite);
		registerRender(romanChestplateElite);
		registerRender(romanLeggingsElite);
		registerRender(romanBootsElite);
		
		registerRender(gothicHelmet);
		registerRender(gothicChestplate);
		registerRender(gothicLeggings);
		registerRender(gothicBoots);
		
		registerRender(tomeSummonAncient);
		registerRender(tomeFireball);
		
		registerRender(throwingAxe);
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}
