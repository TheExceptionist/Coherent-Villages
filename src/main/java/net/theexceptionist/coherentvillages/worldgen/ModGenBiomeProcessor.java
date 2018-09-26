package net.theexceptionist.coherentvillages.worldgen;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;

public class ModGenBiomeProcessor implements ITemplateProcessor {
	private Biome biomeIn;
	
	public ModGenBiomeProcessor(final Biome biomeIn)
	{
		this.biomeIn = biomeIn;
	}
	
	@Override
	public BlockInfo processBlock(World worldIn, BlockPos pos, BlockInfo blockInfoIn) {
		// TODO Auto-generated method stub
		IBlockState initState = blockInfoIn.blockState;
		IBlockState state = getBiomeSpecificBlockState(initState, this.biomeIn);
		BlockInfo info = new BlockInfo(pos, state, blockInfoIn.tileentityData);
		
		return info;
	}
	
    public static IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn, Biome biome)
    {
			if (biome == Biomes.TAIGA || biome == Biomes.MUTATED_TAIGA || biome == Biomes.TAIGA_HILLS)
	        {
	            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
	            {
	            	return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
	            	
	            }
	
	            if (blockstateIn.getBlock() == Blocks.PLANKS)
	            {
	            	return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
	            	
	            }
	            
	            if(Blocks.GRASS_PATH== blockstateIn.getBlock()){
					return Blocks.COBBLESTONE.getDefaultState();//.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
	
	            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
	            {
	            	return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
	            	
	            }
	
	            if (blockstateIn.getBlock() == Blocks.OAK_FENCE)
	            {
	            	return Blocks.SPRUCE_FENCE.getDefaultState();
	            	
	            }
	        }
			if(biome == Biomes.REDWOOD_TAIGA_HILLS || biome == Biomes.REDWOOD_TAIGA || biome == Biomes.FROZEN_OCEAN ||biome == Biomes.FROZEN_OCEAN || biome == Biomes.ICE_MOUNTAINS || biome == Biomes.MUTATED_ICE_FLATS || biome == Biomes.ICE_PLAINS || biome == Biomes.MUTATED_TAIGA_COLD || biome == Biomes.COLD_TAIGA || biome == Biomes.COLD_BEACH || biome == Biomes.COLD_TAIGA_HILLS){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH);
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
					    
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.SPRUCE_FENCE.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				/*if(Blocks.STONE_STAIRS == blockstateIn){
					//return Blocks.SPRUCE_FENCE.getDefaultState();
				}*/
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.STONEBRICK.getDefaultState();//.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				if(Blocks.GRAVEL == blockstateIn.getBlock()){
					return Blocks.STONEBRICK.getDefaultState();
					
				}
				
				/*if(Blocks.DOUBLE_STONE_SLAB.getDefaultState() == blockstateIn){
					//return Blocks.SPRUCE_FENCE.getDefaultState();
				}*/
				
				/*if(Blocks.STONEBRICK.getDefaultState() == blockstateIn){
					//return Blocks.SPRUCE_FENCE.getDefaultState();
				}*/
				//
			}
			if(biome == Biomes.BIRCH_FOREST ||biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.FOREST || biome == Biomes.FOREST_HILLS || biome == Biomes.MUTATED_BIRCH_FOREST || biome == Biomes.MUTATED_BIRCH_FOREST || biome == Biomes.MUTATED_FOREST){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE== blockstateIn.getBlock()){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.BIRCH_FENCE.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.STONE_STAIRS == blockstateIn.getBlock()){
					return Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH);//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				if(Blocks.GRAVEL == blockstateIn.getBlock()){
					return Blocks.GRASS_PATH.getDefaultState();
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.STONEBRICK == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH);
					
				}
			}
			if(biome == Biomes.MUSHROOM_ISLAND || biome == Biomes.MUSHROOM_ISLAND_SHORE || biome == Biomes.DEEP_OCEAN ||biome == Biomes.OCEAN || biome == Biomes.RIVER || biome == Biomes.SWAMPLAND){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.OAK_FENCE.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				/*if(Blocks.STONE_STAIRS == blockstateIn){
					//return Blocks.BIRCH_STAIRS.getDefaultState();
				}*/
				
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				
				if(Blocks.GRAVEL == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.STONEBRICK == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}
			}
			if(biome == Biomes.EXTREME_HILLS || biome == Biomes.EXTREME_HILLS_EDGE || biome == Biomes.EXTREME_HILLS_WITH_TREES ||biome == Biomes.STONE_BEACH || biome == Biomes.MUTATED_EXTREME_HILLS || biome == Biomes.MUTATED_EXTREME_HILLS_WITH_TREES){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.STONE.getDefaultState();//.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.STONE.getDefaultState();
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.COBBLESTONE_WALL.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.STONE_STAIRS == blockstateIn.getBlock()){
					return Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.GRAVEL.getDefaultState();//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.BRICK_BLOCK.getDefaultState();
					
				}
				
				/*if(Blocks.STONEBRICK.getDefaultState() == blockstateIn){
					//return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
		          }*/
			}
			if(biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_EDGE || biome == Biomes.JUNGLE_HILLS ||biome == Biomes.MUTATED_JUNGLE || biome == Biomes.MUTATED_JUNGLE_EDGE){
				if(Blocks.LOG.getDefaultState() == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.MOSSY_COBBLESTONE.getDefaultState();
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.JUNGLE_FENCE.getDefaultState();//.withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));;
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.JUNGLE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				/*if(Blocks.STONE_STAIRS == blockstateIn){
					//return Blocks.BRICK_STAIRS.getDefaultState();
				}*/
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.MOSSY_COBBLESTONE.getDefaultState();//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				if(Blocks.GRAVEL == blockstateIn.getBlock()){
					return Blocks.MOSSY_COBBLESTONE.getDefaultState();//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
					
				}
				
				if(Blocks.STONEBRICK == blockstateIn.getBlock()){
					return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY);
					
				}
			}
			if(biome == Biomes.MESA || biome == Biomes.MESA_CLEAR_ROCK || biome == Biomes.MESA_ROCK ||biome == Biomes.MUTATED_MESA || biome == Biomes.MESA_CLEAR_ROCK || biome == Biomes.MESA_ROCK){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.HARDENED_CLAY.getDefaultState();
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.HARDENED_CLAY.getStateFromMeta(1);//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.);
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.HARDENED_CLAY.getStateFromMeta(9);//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.COBBLESTONE_WALL.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock()){
					return Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.STONE_STAIRS == blockstateIn.getBlock()){
					return Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				/*if(Blocks.GRAVEL.getDefaultState() == blockstateIn){
					//return Blocks.CLAY.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
		        }*/
				if(Blocks.GRASS_PATH == blockstateIn.getBlock()){
					return Blocks.BRICK_BLOCK.getDefaultState();
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.HARDENED_CLAY.getStateFromMeta(8);//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.);
					
				}
				
				if(Blocks.STONEBRICK == blockstateIn.getBlock()){
					return Blocks.HARDENED_CLAY.getStateFromMeta(12);//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.);
					
				}
			}
			
			if(biome == Biomes.MUTATED_ROOFED_FOREST || biome == Biomes.ROOFED_FOREST){
				if(Blocks.LOG == blockstateIn.getBlock() || blockstateIn.getBlock() == Blocks.LOG2){
					return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockNewLog.LOG_AXIS, blockstateIn.getValue(BlockNewLog.LOG_AXIS));//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock()){
					return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);//.withProperty(BlockNewLog.LOG_AXIS, blockstateIn.getValue(BlockNewLog.LOG_AXIS));//
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock()){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));//
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock()){
					return Blocks.DARK_OAK_FENCE.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS== blockstateIn.getBlock()){
					return Blocks.DARK_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.STONE_STAIRS == blockstateIn.getBlock()){
					return Blocks.DARK_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				if(Blocks.GRAVEL == blockstateIn.getBlock()){
					return Blocks.GRASS_PATH.getDefaultState();
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock()){
					return Blocks.DOUBLE_WOODEN_SLAB.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK);//.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
				}
				
				/*if(Blocks.STONEBRICK.getDefaultState() == blockstateIn){
					//return Blocks.HARDENED_CLAY.getStateFromMeta(12);//.withProperty(BlockHardenedClay.VARIANT, BlockHardenedClay.);
					 }*/
			}
			
			if(biome == Biomes.MUTATED_SAVANNA || biome == Biomes.MUTATED_SAVANNA_ROCK || biome == Biomes.SAVANNA || biome == Biomes.SAVANNA_PLATEAU){
				if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
	            {
					return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
					
	            }
	
	            if (blockstateIn.getBlock() == Blocks.PLANKS)
	            {
	            	return  Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
	            	
	            }
	
	            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
	            {
	            	return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
	            	
	            }
	
	            if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
	            {
	            	return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
	            	
	            }
	
	            if (blockstateIn.getBlock() == Blocks.OAK_FENCE)
	            {
	            	return  Blocks.ACACIA_FENCE.getDefaultState();
	            	
	            }
			}
			
	if(biome == Biomes.BEACH || biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT || biome == Biomes.MUTATED_DESERT){
		  if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
	      {
			  return  Blocks.SANDSTONE.getDefaultState();
			  
	      }
	
	      if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
	      {
	    	  return  Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
	    	  
	      }
	
	      if (blockstateIn.getBlock() == Blocks.PLANKS || blockstateIn.getBlock() == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH).getBlock())
	      {
	    	  return   Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
	    	  
	      }
	
	      if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
	      {
	    	  return  Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
	    	  
	      }
	
	      if (blockstateIn.getBlock() == Blocks.STONE_STAIRS || blockstateIn.getBlock() == Blocks.STONE_BRICK_STAIRS)
	      {
	    	  return  Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
	    	  
	      }
	
	      if (blockstateIn.getBlock() == Blocks.GRAVEL)
	      {
	    	  return  Blocks.SANDSTONE.getDefaultState();
	      }
	      
	      if (blockstateIn.getBlock() == Blocks.DIRT || blockstateIn.getBlock() == Blocks.GRASS)
	      {
	    	  return  Blocks.SAND.getDefaultState();
	    	  
	      }
	 	}

        return blockstateIn;
    }

}
