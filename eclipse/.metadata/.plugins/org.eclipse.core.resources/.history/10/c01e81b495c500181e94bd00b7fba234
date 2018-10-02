package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillageComponentWell extends StructureVillagePieces.Village
{
	public VillageComponentWell()
    {
    }

    public VillageComponentWell(StructureVillagePieces.Start start, int type, Random rand, int x, int z)
    {
        super(start, type);
        this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

        if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
        {
            this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
        }
        else
        {
            this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
        }
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
    {
        /*StructureVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
        StructureVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
        StructureVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
        StructureVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
    	*/
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        if (this.averageGroundLvl < 0)
        {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
        }

        IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
        IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, iblockstate, Blocks.FLOWING_WATER.getDefaultState(), false);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 1, 13, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 1, 14, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 4, 13, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 4, 14, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 1, 13, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 1, 14, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 4, 13, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate1, 4, 14, 4, structureBoundingBoxIn);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, iblockstate, iblockstate, false);

        for (int i = 0; i <= 5; ++i)
        {
            for (int j = 0; j <= 5; ++j)
            {
                if (j == 0 || j == 5 || i == 0 || i == 5)
                {
                    this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                }
            }
        }

        return true;
    }
}
