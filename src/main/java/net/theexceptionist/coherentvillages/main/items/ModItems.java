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
	public static ArmorMaterial NORD_HELMET = EnumHelper.addArmorMaterial("horn_helmet", Resources.MODID+":horn_helmet", 1500, new int[]{6, 4, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 10F);
	public static ArmorMaterial ROMAN_HELMET = EnumHelper.addArmorMaterial("roman_helmet", Resources.MODID+":roman_helmet", 2000, new int[]{10, 4, 3, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 12F);
	
	public static ToolMaterial ROMAN_STEEL = EnumHelper.addToolMaterial("roman_steel", 2, 2000, 6.5f, 5.0f, 7);//.addToolMaterial("nord_mail", Resources.MODID+":nord_mail", 1500, new int[]{3 , 4, 7, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8F);
	
	public static ItemWeaponThrowable throwingAxe;
	
	public static ItemModArmor barbarianHelmet;// = new ItemNordArmor("barbarian_helmet", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianChestplate;// = new ItemNordArmor("barbarian_chestplate", ItemNordArmor.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianLeggings;// = new ItemNordArmor("barbarian_leggings", ItemNordArmor.NORD_MAIL, 1, EntityEquipmentSlot.HEAD);
	public static ItemModArmor barbarianBoots;
	
	public static ItemModArmor hornHelmet;
	public static ItemModArmor romanHelmet;
	
	public static ItemModShield nordShield;
	
	public static ItemModSword romanSword;
	public static ItemSpear spear;
	
	public static ItemTome tomeSummonAncient;
	public static ItemTome tomeFireball;
	
	public static void init()
	{
		throwingAxe = new ItemWeaponThrowable("throwing_axe", 16, Items.IRON_AXE);//.setMaxStackSize(maxStackSize)
		
		barbarianHelmet = new ItemModArmor("barbarian_helmet", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.HEAD);
		barbarianChestplate = new ItemModArmor("barbarian_chestplate", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.CHEST);
		barbarianLeggings = new ItemModArmor("barbarian_leggings", ModItems.NORD_MAIL, 1, EntityEquipmentSlot.LEGS);
		barbarianBoots = new ItemModArmor("barbarian_boots", ModItems.NORD_MAIL, 0, EntityEquipmentSlot.FEET);
		
		nordShield = new ItemModShield("nord_shield");
		romanSword = new ItemModSword("roman_sword", ModItems.ROMAN_STEEL);
		spear = new ItemSpear("spear", ModItems.ROMAN_STEEL);
		
		hornHelmet = new ItemModArmor("horn_helmet", ModItems.NORD_HELMET, 0, EntityEquipmentSlot.HEAD);
		romanHelmet = new ItemModArmor("roman_helmet", ModItems.ROMAN_HELMET, 0, EntityEquipmentSlot.HEAD);
		
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
		
		registry.registerAll(nordShield);
		registry.registerAll(romanSword);
		registry.registerAll(spear);
		
		//registry.registerAll(hornHelmet);
		//registry.registerAll(romanHelmet);

		registry.registerAll(tomeSummonAncient);
		registry.registerAll(tomeFireball);
		
		registry.registerAll(throwingAxe);
		//GameRegistry.registerTileEntity(nordShieldModel, new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_base.png"));
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(barbarianHelmet);
		registerRender(barbarianChestplate);
		registerRender(barbarianLeggings);
		registerRender(barbarianBoots);
		
		registerRender(nordShield);
		registerRender(romanSword);
		registerRender(spear);
		
		//registerRender(hornHelmet);
		//registerRender(romanHelmet);
		
		registerRender(tomeSummonAncient);
		registerRender(tomeFireball);
		
		registerRender(throwingAxe);
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}
