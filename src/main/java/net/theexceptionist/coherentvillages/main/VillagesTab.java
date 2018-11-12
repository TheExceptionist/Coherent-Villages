package net.theexceptionist.coherentvillages.main;

import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.main.items.ModItems;

public class VillagesTab extends CreativeTabs {

	public VillagesTab(int index, String label) {
		super(index, label);
	}

	public String getTranslatedTabLabel(){
		return Resources.NAME;
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.nordShield[0], 1, 0);
	}

}
