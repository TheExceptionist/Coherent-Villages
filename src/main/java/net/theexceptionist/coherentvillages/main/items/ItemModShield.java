package net.theexceptionist.coherentvillages.main.items;

import javax.annotation.Nullable;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.Resources;

public class ItemModShield extends ItemShield{
	protected int type;
	
	public ItemModShield(String name)
	{
		super();
		this.canRepair = true;
	    this.showDurabilityBar(new ItemStack(this));
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.villagesTab);
		type = 0;
	}

    public ItemModShield(String name, int i) {
    	this(name);
		type = i;
	}
    
    

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
