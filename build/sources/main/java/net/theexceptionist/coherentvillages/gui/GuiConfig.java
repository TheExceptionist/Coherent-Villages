package net.theexceptionist.coherentvillages.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.biome.Biome;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public class GuiConfig extends GuiScreen {
	private ArrayList<Biome> spawnBiomes;
	private ArrayList<EntityLivingBase> hostileEntities;
	private ArrayList<AbstractVillagerSoldier> enabledSoldiers;
	
	public GuiConfig()
	{
		
	}
}
