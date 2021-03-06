package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.Main;

public class ItemWeaponThrowable extends Item 
{
	private Item weapon;
	
	public ItemWeaponThrowable(String name, int stackSize, Item weapon)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(Main.villagesTab);
		this.weapon = weapon;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityWeaponThrowable entityweapon = new EntityWeaponThrowable(worldIn, playerIn, weapon, 5);
            entityweapon.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityweapon);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

	public Item getWeapon() {
		// TODO Auto-generated method stub
		return weapon;
	}
}
