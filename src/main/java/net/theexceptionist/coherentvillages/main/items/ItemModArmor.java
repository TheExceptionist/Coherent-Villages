package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.Resources;

public class ItemModArmor extends ItemArmor{		
	public ItemModArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.villagesTab);
	}

}
