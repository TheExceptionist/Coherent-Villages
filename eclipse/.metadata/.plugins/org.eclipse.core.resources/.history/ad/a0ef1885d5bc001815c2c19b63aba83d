package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.theexceptionist.coherentvillages.entity.EntityVillagerSoldier;

public class VillageComponentWall extends StructureVillagePieces.Village
{
	static int numBlocks = 0;
	static int numBlocksY = 0; 
	static boolean up = false;
	private int villagersSpawned = 0;
    public VillageComponentWall()
    {
    }

    public VillageComponentWall(StructureVillagePieces.Start start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing)
    {
        super(start, p_i45568_2_);
        this.setCoordBaseMode(facing);
        this.boundingBox = p_i45568_4_;
    }

    public static StructureBoundingBox findPieceBox(StructureVillagePieces.Start start, List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing, int p5)
    {
    	numBlocks = 2  + rand.nextInt(10);
    	numBlocksY = 2  + rand.nextInt(2);
    	up = (rand.nextInt(2) == 0);
    	if(up){
    		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, numBlocks, 4, 1, facing);
    		return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
    	}else{
    		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 1, 4, numBlocks, facing);
    		return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
    	}
        
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        if (this.averageGroundLvl < 0)
        {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
        }

       // IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
        IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
        
        if(up){
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, numBlocks, numBlocksY, 0, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, numBlocks, numBlocksY, 0, iblockstate, iblockstate, false);
	        this.placeTorch(worldIn, EnumFacing.UP, numBlocks, numBlocksY + 1, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 0, numBlocksY + 1, 0, structureBoundingBoxIn);
	        for (int j = 0; j < numBlocks + 1; ++j)
            {
                /*for (int i = 0; i < numBlocksY; ++i)
                {*/
                    //this.clearCurrentPositionBlocksUpwards(worldIn, j, -2, 0, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j, -2, 0, structureBoundingBoxIn);
                //}
            }
	        //this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
        }else{
        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, 0, numBlocksY, numBlocks, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, 0, numBlocksY, numBlocks, iblockstate, iblockstate, false);
	        this.placeTorch(worldIn, EnumFacing.UP, 0, numBlocksY + 1, numBlocks, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 0, numBlocksY + 1, 0, structureBoundingBoxIn);
	        for (int j = 0; j < numBlocks + 1; ++j)
            {
                /*for (int i = 0; i > -10; ++i)
                {*/
                    //this.clearCurrentPositionBlocksUpwards(worldIn, 0, -2, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, 0, -2, j, structureBoundingBoxIn);
                //}
            }
        }
        
        this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1 + randomIn.nextInt(2));
        return true;
    }
    protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count)
    {
        if (this.villagersSpawned  < count)
        {
            for (int i = villagersSpawned; i < count; ++i)
            {
                int j = this.getXWithOffset(x + i, z);
                int k = this.getYWithOffset(y);
                int l = this.getZWithOffset(x + i, z);

                if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                {
                    break;
                }

                ++this.villagersSpawned;

                if(worldIn.rand.nextInt(100) <= 50){
                	EntityVillagerSoldier entityvillager = new EntityVillagerSoldier(worldIn);
                	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    //entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
                else
                {
                	EntityVillager entityvillager = new EntityVillager(worldIn);
                    entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setProfession(5);
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
                    /*else if(worldIn.rand.nextInt(100) <= 50){
                }
                	EntityVillagerArcher entityvillager = new EntityVillagerArcher(worldIn);
                	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }else{
                	EntityVillagerKnight entityvillager = new EntityVillagerKnight(worldIn);
                	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }*/
                
            }
        }
    }
    

}
