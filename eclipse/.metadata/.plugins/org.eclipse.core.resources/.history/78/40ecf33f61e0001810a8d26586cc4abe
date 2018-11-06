package net.theexceptionist.coherentvillages.events;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.CheckResult;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.worldgen.helper.ModTemplateHandler;

public class PlayerConnectionEvent{
		
	@SubscribeEvent
	public void sendPlayerMessage(PlayerEvent.PlayerLoggedInEvent event)
	{
		World world = event.player.world;
		CheckResult result = ForgeVersion.getResult(Loader.instance().getIndexedModList().get(Resources.MODID));
		Style style = new Style();
		style.setColor(TextFormatting.DARK_RED);
		/*ITextComponent itextcomponent1 = new TextComponentString(
				"Welcome to Cohorent Villages\n Your current version is: "+Resources.VERSION+"\n This version is: "+result.status.name()+"!\n"
		+"Goto this url to get the latest version: "+result.url
				);*/
		
		ITextComponent itextcomponent1 = new TextComponentString(
				"Welcome to "+Resources.NAME+"\n By: TheExceptionist (Or majesticmerlin)\n Your current version is: "+Resources.VERSION
				+"\n Thank you for downloading this mod! Check for updates on Curseforge and post issues on Github! \nDon't forget to change the config file to suit your needs!\n Special Thanks to: eeeeetdyeeeee, Olkusz_Ssak12 and various other people For the Textures");
		
			
		itextcomponent1.setStyle(style);
		EntityPlayer player = event.player;
		
		player.sendMessage(itextcomponent1);
	}
}
