package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.entity.spells.Spell;

public class ItemTome extends Item {
	private Spell spell;
	public ItemTome(String name, Spell spell)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.villagesTab);
		this.spell = spell;
	}
	
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		spell.execute(player);
        return EnumActionResult.PASS;
    }

	
}
