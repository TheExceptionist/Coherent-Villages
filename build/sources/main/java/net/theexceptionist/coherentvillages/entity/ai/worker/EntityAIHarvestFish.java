package net.theexceptionist.coherentvillages.entity.ai.worker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EntityAIHarvestFish  extends EntityAIMoveToBlock
{
    /** Villager that is harvesting */
    private final EntityHumanVillager villager;
    private boolean hasFarmItem;
    private boolean wantsToReapStuff;
    /** 0 => harvest, 1 => replant, -1 => none */
    private int currentTask;

    public EntityAIHarvestFish(EntityHumanVillager villagerIn, double speedIn)
    {
        super(villagerIn, speedIn, 16);
        this.villager = villagerIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.runDelay <= 0)
        {
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.villager.world, this.villager))
            {
                return false;
            }

            this.currentTask = -1;
            this.hasFarmItem = this.villager.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAxe;
            this.wantsToReapStuff = this.villager.world.isDaytime();
        }

        return super.shouldExecute();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return this.currentTask >= 0 && super.shouldContinueExecuting();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        super.updateTask();
        this.villager.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.villager.getVerticalFaceSpeed());

        if (this.getIsAboveDestination())
        {
            World world = this.villager.world;
            BlockPos blockpos = this.destinationBlock.up();
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();

            if (this.currentTask == 0)
            {
                villager.swingArm(EnumHand.MAIN_HAND);
                InventoryBasic inventorybasic = this.villager.getVillagerInventory();

                for (int i = 0; i < inventorybasic.getSizeInventory(); ++i)
                {
                	ItemStack itemstack = inventorybasic.getStackInSlot(i);
                	
                	if(!itemstack.isEmpty())
                	{
                		if(itemstack.getItem() == Items.FISH)
                		{
                			itemstack.setCount(itemstack.getCount() + 1);
                		}
                	}
                	else
                	{
                		inventorybasic.setInventorySlotContents(i, new ItemStack(Items.FISH));
                	}
                }
            }
            /*else if (this.currentTask == 1 && iblockstate.getMaterial() == Material.AIR)
            {
                InventoryBasic inventorybasic = this.villager.getVillagerInventory();

                for (int i = 0; i < inventorybasic.getSizeInventory(); ++i)
                {
                    ItemStack itemstack = inventorybasic.getStackInSlot(i);
                    boolean flag = false;

                    if (!itemstack.isEmpty())
                    {
                        if (itemstack.getItem() == Items.WHEAT_SEEDS)
                        {
                            world.setBlockState(blockpos, Blocks.WHEAT.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemstack.getItem() == Items.POTATO)
                        {
                            world.setBlockState(blockpos, Blocks.POTATOES.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemstack.getItem() == Items.CARROT)
                        {
                            world.setBlockState(blockpos, Blocks.CARROTS.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemstack.getItem() == Items.BEETROOT_SEEDS)
                        {
                            world.setBlockState(blockpos, Blocks.BEETROOTS.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemstack.getItem() instanceof net.minecraftforge.common.IPlantable) {
                            if(((net.minecraftforge.common.IPlantable)itemstack.getItem()).getPlantType(world,blockpos) == net.minecraftforge.common.EnumPlantType.Crop) {
                                world.setBlockState(blockpos, ((net.minecraftforge.common.IPlantable)itemstack.getItem()).getPlant(world,blockpos),3);
                                flag = true;
                            }
                        }
                    }

                    if (flag)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            inventorybasic.setInventorySlotContents(i, ItemStack.EMPTY);
                        }

                        break;
                    }
                }
            }*/

            this.currentTask = -1;
            this.runDelay = 10;
        }
    }

    /**
     * Return true to set given position as destination
     */
    protected boolean shouldMoveTo(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (block == Blocks.WATER)
        {
            pos = pos.up();
            IBlockState iblockstate = worldIn.getBlockState(pos);
            block = iblockstate.getBlock();
            //boolean isTree = false;
            
            /*for(int i = 0; i < 10; i++)
            {
            	pos = pos.up();
                iblockstate = worldIn.getBlockState(pos);
                block = iblockstate.getBlock();
                
            	if(block == Blocks.LEAVES || block == Blocks.LEAVES2)
            	{
            		isTree = true;
            	}
            }
            
            isTree && */

            if (this.wantsToReapStuff && (this.currentTask == 0 || this.currentTask < 0))
            {
                this.currentTask = 0;
                return true;
            }

            if (iblockstate.getMaterial() == Material.AIR && this.hasFarmItem && (this.currentTask == 1 || this.currentTask < 0))
            {
                this.currentTask = 1;
                return true;
            }
        }

        return false;
    }
}