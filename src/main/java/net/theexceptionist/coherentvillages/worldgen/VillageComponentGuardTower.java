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
import net.theexceptionist.coherentvillages.entity.archer.AbstractVillagerArcher;
import net.theexceptionist.coherentvillages.entity.archer.EntityVillagerArcher;
import net.theexceptionist.coherentvillages.entity.archer.EntityVillagerMageArcher;
import net.theexceptionist.coherentvillages.entity.archer.EntityVillagerMarksman;

public class VillageComponentGuardTower extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;

        public VillageComponentGuardTower()
        {
        }

        public VillageComponentGuardTower(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        public static VillageComponentGuardTower createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 7, 7, 7, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentGuardTower(start, p_175858_7_, rand, structureboundingbox, facing);
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

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 5, 0);
            }
            
            int type = randomIn.nextInt(2);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 7, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            
            //6 ,9 ,6
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 5, 7, 0,iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 6, 5, 7, 6,iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 7, 6,iblockstate6, iblockstate6, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 0, 6, 7, 6,iblockstate6, iblockstate6, false);
            
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 0, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 0, 5,iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 6, 3, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 5, 3, 5,iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 6, 3, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 5, 3, 5,iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 0, 6, 7, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 1, 5, 7, 5,iblockstate1, iblockstate1, false);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn,randomIn, 3, 1, 0, EnumFacing.NORTH);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 1, -1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 2, -1, structureBoundingBoxIn);
            
            this.placeTorch(worldIn, EnumFacing.SOUTH, 3, 2, 1, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 1, 2, 1, structureBoundingBoxIn);
	        
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 9, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 9, 6, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 6, 9, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 6, 9, 6, structureBoundingBoxIn);
            
	        this.placeTorch(worldIn, EnumFacing.UP, 3, 1, 1, structureBoundingBoxIn);
	        
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 7, 0, iblockstate, iblockstate, false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 7, 0, iblockstate, iblockstate, false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 6, 0, 7, 6, iblockstate, iblockstate, false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 6, 6, 7, 6, iblockstate, iblockstate, false);
	        
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 0, 6, 7, 6, iblockstate, iblockstate, false);
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 7, 1, 5, 7, 5, iblockstate6, iblockstate6, false);
	        
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 5, 3, 7, 5, Blocks.LADDER.getDefaultState(), Blocks.LADDER.getDefaultState(), false);
	        this.setBlockState(worldIn, Blocks.TRAPDOOR.getDefaultState(), 3, 8, 5, structureBoundingBoxIn);
            
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	      //  this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 1, 2, 4, 1, iblockstate6, iblockstate6, false);
	       // this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 1, 2, 4, 1, iblockstate6, iblockstate6, false);
	        //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 1, 2, 4, 1, iblockstate6, iblockstate6, false);
	       
	        
	        
	        if(worldIn.rand.nextInt(100) <= 50){
	        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 2, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 2, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 5, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 5, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 4, 5, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 0, 5, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 5, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 4, 5, 6, structureBoundingBoxIn);
	        }else{
	        	this.setBlockState(worldIn, iblockstate4, 2, 2, 0, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 4, 2, 0, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 0, 2, 2, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 0, 2, 3, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 0, 2, 4, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 6, 2, 2, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 6, 2, 3, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 6, 2, 4, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 2, 2, 6, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 3, 2, 6, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 4, 2, 6, structureBoundingBoxIn);
	            
	            
	            this.setBlockState(worldIn, iblockstate4, 2, 5, 0, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 3, 5, 0, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 4, 5, 0, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 0, 5, 2, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 0, 5, 3, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 0, 5, 4, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 6, 5, 2, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 6, 5, 3, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 6, 5, 4, structureBoundingBoxIn);
	            
	            this.setBlockState(worldIn, iblockstate4, 2, 5, 6, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 3, 5, 6, structureBoundingBoxIn);
	            this.setBlockState(worldIn, iblockstate4, 4, 5, 6, structureBoundingBoxIn);
	        }
	        
	        //if(type == 0){
	        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 8, 0, 6, 8, 6, iblockstate4, iblockstate4, false);
	        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 8, 1, 5, 8, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
	        /*}else{
	        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 8, 0, 6, 8, 6, iblockstate4, iblockstate4, false);
	        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 8, 1, 5, 8, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
	        	this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 8, 6, 6, 8, 12, iblockstate, iblockstate, false);
		        
	        }*/
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 9, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 6, 9, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 9, 6, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 6, 9, 6, structureBoundingBoxIn);
      

            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate2, 3, 0, -1, structureBoundingBoxIn);

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

          //  this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);

            for (int j = 0; j < 8; ++j)
            {
                for (int i = 0; i < 8; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 10, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.PLANKS.getDefaultState(), i, -1, j, structureBoundingBoxIn);
                }
            }
            
            if(!worldIn.isRemote){
            	this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 10, 2, 2 + randomIn.nextInt(4));
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

                   
                    AbstractVillagerArcher entityvillager = new EntityVillagerArcher(worldIn);

                   if(worldIn.rand.nextInt(100) <= 50) entityvillager = new EntityVillagerMarksman(worldIn);
                   else if(worldIn.rand.nextInt(100) <= 5) entityvillager = new EntityVillagerMageArcher(worldIn);
                   
                   entityvillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(j, k, l)), null);
                	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k - 8D, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k - 8, (double)l + 0.5D);
                    //entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                    
                }
            }
        }
    
}
