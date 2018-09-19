package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMage;

public class VillageComponentWizardTower extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;

        public VillageComponentWizardTower()
        {
        }

        public VillageComponentWizardTower(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        public static VillageComponentWizardTower createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 4, 7, 4, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentWizardTower(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
        	//System.out.println("Generating"+this.getXWithOffset(0, 0)+" "+this.getZWithOffset(0, 0));
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7, 0);
            }
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 7, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            
            IBlockState iblockstate7 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate8 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
            IBlockState iblockstate9 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
            IBlockState iblockstate10 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            //IBlockState iblockstate10 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing));
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, 4, 4, 4, iblockstate6,iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 3, 4, 3, Blocks.AIR.getDefaultState(),Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 3, 4, 0,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 4, 4,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 4, 3,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 4, 3,iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, -1, 5, 0, 5, 5, 0,iblockstate7, iblockstate7, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 1, 4, 6, 1,iblockstate7, iblockstate7, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 2, 3, 7, 2,iblockstate7, iblockstate7, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, -1, 5, 4, 5, 5, 4,iblockstate8, iblockstate8, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 3, 4, 6, 3,iblockstate8, iblockstate8, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 2, 3, 7, 2,iblockstate6, iblockstate6, false);
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 3, 5, 3, iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 6, 2, 2, 6, 2, iblockstate6, iblockstate6, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 3, 3, 4, 3, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 1, 5, 5, 3, iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 2, 4, 6, 2, iblockstate6, iblockstate6, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, -1, 5, 1, -1, 5, 3, iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 2, 0, 6, 2, iblockstate6, iblockstate6, false);
            
            
            this.setBlockState(worldIn, Blocks.ENCHANTING_TABLE.getDefaultState(), 2, 0, 3, structureBoundingBoxIn);
           // this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 0, 0, EnumFacing.NORTH);
            /*this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 0, 6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 0, 5,iblockstate1, iblockstate1, false);
            
            this.setBlockState(worldIn, iblockstate5, 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 5, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 3, 0, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, iblockstate6, 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 2, 4, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 4, 0, structureBoundingBoxIn);
            
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn,randomIn, 3, 1, 0, EnumFacing.NORTH);
            this.placeTorch(worldIn, EnumFacing.NORTH, 3, 3, 2, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 3, 3, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 5, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 0, structureBoundingBoxIn);
            
            if(randomIn.nextInt(2) == 0){
            	this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), 2, 1, 2, structureBoundingBoxIn);
            }
            
            if(randomIn.nextInt(2) == 0){
            	this.setBlockState(worldIn, iblockstate, 4, 1, 2, structureBoundingBoxIn);
            	this.setBlockState(worldIn, Blocks.BREWING_STAND.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            	this.setBlockState(worldIn, Blocks.CAULDRON.getDefaultState(), 5, 1, 2, structureBoundingBoxIn);
            }
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 1, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 6, structureBoundingBoxIn);
            
            
            //
            
            
            this.setBlockState(worldIn, iblockstate5, 6, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 6, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 0, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 5, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 5, 6, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 1, 6, 3, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 6, iblockstate, iblockstate, false);
            
            this.setBlockState(worldIn, iblockstate5, 3, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 0, structureBoundingBoxIn);
           
         /* //Stair Stab Piece
            //x, y , z
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 0, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 4, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 3, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 2, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 0, 13, structureBoundingBoxIn);
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 11, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 6, structureBoundingBoxIn);
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 4, 12, Blocks.STONEBRICK.getDefaultState(), Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 1, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 1, 12, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 12, 1, 5, 12, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 1, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 12, 12, 5, 12, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 3, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 3, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 5, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 5, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 6, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 6, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 7, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 7, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 8, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 8, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 9, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 9, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 12, structureBoundingBoxIn);
            
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 12, structureBoundingBoxIn);
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 12, structureBoundingBoxIn);
            //Stair Stab Piece
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 13, 0, 0, structureBoundingBoxIn);
           
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 0, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 1, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 2, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 3, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 3, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 4, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
           
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 4, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 3, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 2, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 0, 13, structureBoundingBoxIn);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 12, 3, 12, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            
           /* this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 5, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate2, 2, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 2, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 3, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 3, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 4, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 4, 4, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 4, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 4, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 4, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 4, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 5, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 6, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 7, 6, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 4, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 4, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 4, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 8, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 9, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 9, 4, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 9, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 10, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 10, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 11, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 11, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 0, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 3, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 3, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 3, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 12, 5, 7, structureBoundingBoxIn);
            */
            /*this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 2, 2, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 2, 1, 3, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 4, 2, 1, 5, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
           
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 10, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 2, 2, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 2, 2, 3, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 4, 2, 2, 5, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 10, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 3, 2, 2, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 2, 2, 4, 7, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 6, 2, 2, 6, 7, iblockstate4, iblockstate4, false);
          
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 2, 3, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 3, 3, 2, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 8, 3, 1, 9, iblockstate4, iblockstate4, false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 3, 1, 2, structureBoundingBoxIn)
            ;
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 3, 2, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 3, 2, 9, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 3, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 2, 3, 7, structureBoundingBoxIn);*/
            /*
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 7, 3, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 4, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 5, 3, 3, 5, 6, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
            this.setBlockState(worldIn, iblockstate4, 3, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 3, 6, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 2, 4, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 2, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 4, 3, 2, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 2, 4, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 4, 4, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 3, 4, 5, 6, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
            this.setBlockState(worldIn, iblockstate4, 4, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 4, 6, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 2, 5, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 1, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 5, 1, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 2, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 5, 2, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 3, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 5, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 5, 3, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 5, 3, 10, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 3, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 4, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 4, 11, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 5, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 5, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 5, 5, 6, structureBoundingBoxIn);
           
            
            this.setBlockState(worldIn, iblockstate4, 5, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 5, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 2, 6, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 6, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 6, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 6, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 6, 3, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 6, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 6, 4, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 4, 8, 6, 4, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 3, 6, 5, 6, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 6, 5, 11, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 6, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 6, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 2, 7, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 7, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 7, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 7, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 7, 3, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 7, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 7, 4, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 8, 7, 4, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 5, 3, 7, 5, 6, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 7, 5, 11, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 7, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 7, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 2, 8, 0, 9, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 0, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState(), 8, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 5, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 1, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 2, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 2, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 3, 7, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 3, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 8, 3, 10, structureBoundingBoxIn);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 5, 1, 5, EnumFacing.NORTH);
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 5, 1, 6, EnumFacing.NORTH);

            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 5, 1, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 3, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 4, 7, structureBoundingBoxIn);            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 8, 4, 11, structureBoundingBoxIn);
 
            
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 8, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 8, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 8, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 8, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 8, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 8, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 2, 9, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState(), 9, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 2, 2, structureBoundingBoxIn);            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 2, 7, structureBoundingBoxIn);
 
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 9, 3, 2, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 7, 9, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 4, 2, structureBoundingBoxIn);            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 9, 4, 7, structureBoundingBoxIn);
 
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 9, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 9, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 9, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 9, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 9, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 9, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 2, 10, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 10, 1, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 10, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 10, 1, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 10, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 10, 1, 10, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 10, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 10, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 10, 2, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 10, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONE.getDefaultState(), 10, 2, 10, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 10, 3, 2, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, 7, 10, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 10, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 10, 4, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 10, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 10, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 0, 2, 11, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 1, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 2, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 2, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 3, 2, 11, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 4, 2, 11, 4, 7, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, iblockstate4, 11, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 7, structureBoundingBoxIn);
            */
            /*
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, iblockstate3, iblockstate3, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 3, 4, 3, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, iblockstate, 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 1, 0, structureBoundingBoxIn);

            */if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
                }
            }

          //  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            /*if (this.isRoofAccessible)
            {
                this.setBlockState(worldIn, iblockstate4, 0, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 3, structureBoundingBoxIn);
            }

            if (this.isRoofAccessible)
            {
                IBlockState iblockstate5 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                this.setBlockState(worldIn, iblockstate5, 3, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 3, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 4, 3, structureBoundingBoxIn);
            }*/

            this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);

            for (int j = 0; j < 5; ++j)
            {
                for (int i = 0; i < 5; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 10, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.PLANKS.getDefaultState(), i, -1, j, structureBoundingBoxIn);
                }
            }
            if(!worldIn.isRemote){
            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1 + randomIn.nextInt(2));
            }
            return true;
        }
        
        protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count)
        {
            if (this.villagersSpawned < count)
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
                    
                    if(worldIn.rand.nextInt(100) <= 20){
                    	EntityVillagerMage entityvillager = new EntityVillagerMage(worldIn);
                    	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                        entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                       // entityvillager.setProfession(null);
                        
                        entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                        worldIn.spawnEntity(entityvillager);
                    }
                    
                }
            }
        }
    
}
