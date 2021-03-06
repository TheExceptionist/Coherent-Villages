package net.theexceptionist.coherentvillages.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theexceptionist.coherentvillages.main.Resources;

@Mod.EventBusSubscriber(modid=Resources.MODID)
public class BlockRegister {
	public static BlockFireWood firewood;
	
	//To set creative
	//tutorialBlock = new BlockBasic("tutorial_block", Material.ROCK).setHardness(1.5f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightLevel(1.0f);
	public static void init()
	{
		firewood = new BlockFireWood("firewood", Material.WOOD);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(firewood);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		  event.getRegistry().registerAll(new ItemBlock(firewood).setRegistryName(firewood.getRegistryName()));
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(Item.getItemFromBlock(firewood));
	}
	
	public static void registerRender(Item item) {
		  ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}
