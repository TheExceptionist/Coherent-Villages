package net.theexceptionist.coherentvillages.worldgen.villages;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import net.theexceptionist.coherentvillages.events.PlayerConnectionEvent;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.block.BlockRegister;
import net.theexceptionist.coherentvillages.main.entity.EntityBjornserker;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.EntityWarg;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;
import net.theexceptionist.coherentvillages.worldgen.helper.ModTemplate;
import net.theexceptionist.coherentvillages.worldgen.villages.arrays.NordBuildingsArray;
import net.theexceptionist.coherentvillages.worldgen.villages.chartypes.CharBlockTypes;

public class NordStructurePieces
{
    public static void registerVillagePieces()
    {
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.ArcherHut.class, "ViBH");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Field1.class, "ViDF");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Field2.class, "ViF");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Brazier.class, "ViL");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.GuardHouse.class, "ViGH");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.LargerHut.class, "ViSH");//**
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.NordHut.class, "ViSmH");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.JarlHall.class, "ViST");//**
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.DockHouse.class, "ViS");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Start.class, "ViStart");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Path.class, "ViSR");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.LongHouse.class, "ViLH");//
        MapGenStructureIO.registerStructureComponent(NordStructurePieces.Well.class, "ViW");//
    }

    public static List<NordStructurePieces.PieceWeight> getStructureVillageWeightedPieceList(Random random, int size)
    {
        List<NordStructurePieces.PieceWeight> list = Lists.<NordStructurePieces.PieceWeight>newArrayList();
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.LargerHut.class, 4, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.JarlHall.class, 20, MathHelper.getInt(random, 0 + size, 1 + size)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.ArcherHut.class, 20, MathHelper.getInt(random, 0 + size, 2 + size)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.NordHut.class, 3, MathHelper.getInt(random, 2 + size, 5 + size * 3)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.LongHouse.class, 15, MathHelper.getInt(random, 0 + size, 2 + size)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.Field1.class, 3, MathHelper.getInt(random, 1 + size, 4 + size)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.Field2.class, 3, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.DockHouse.class, 15, MathHelper.getInt(random, 0, 1 + size)));
        list.add(new NordStructurePieces.PieceWeight(NordStructurePieces.GuardHouse.class, 8, MathHelper.getInt(random, 0 + size, 3 + size * 2)));
        Iterator<NordStructurePieces.PieceWeight> iterator = list.iterator();

        while (iterator.hasNext())
        {
            if ((iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }

        return list;
    }

    private static int updatePieceWeight(List<NordStructurePieces.PieceWeight> p_75079_0_)
    {
        boolean flag = false;
        int i = 0;

        for (NordStructurePieces.PieceWeight NordStructurePieces$pieceweight : p_75079_0_)
        {
            if (NordStructurePieces$pieceweight.villagePiecesLimit > 0 && NordStructurePieces$pieceweight.villagePiecesSpawned < NordStructurePieces$pieceweight.villagePiecesLimit)
            {
                flag = true;
            }

            i += NordStructurePieces$pieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }

    private static NordStructurePieces.Village findAndCreateComponentFactory(NordStructurePieces.Start start, NordStructurePieces.PieceWeight weight, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        Class <? extends NordStructurePieces.Village > oclass = weight.villagePieceClass;
        NordStructurePieces.Village NordStructurePieces$village = null;

        if (oclass == NordStructurePieces.LargerHut.class)
        {
            NordStructurePieces$village = NordStructurePieces.LargerHut.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.JarlHall.class)
        {
            NordStructurePieces$village = NordStructurePieces.JarlHall.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.ArcherHut.class)
        {
            NordStructurePieces$village = NordStructurePieces.ArcherHut.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.NordHut.class)
        {
            NordStructurePieces$village = NordStructurePieces.NordHut.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.GuardHouse.class)
        {
            NordStructurePieces$village = NordStructurePieces.GuardHouse.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.Field1.class)
        {
            NordStructurePieces$village = NordStructurePieces.Field1.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.Field2.class)
        {
            NordStructurePieces$village = NordStructurePieces.Field2.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.DockHouse.class)
        {
            NordStructurePieces$village = NordStructurePieces.DockHouse.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == NordStructurePieces.LongHouse.class)
        {
            NordStructurePieces$village = NordStructurePieces.LongHouse.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }

        return NordStructurePieces$village;
    }

    private static NordStructurePieces.Village generateComponent(NordStructurePieces.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        int i = updatePieceWeight(start.structureVillageWeightedPieceList);

        if (i <= 0)
        {
            return null;
        }
        else
        {
            int j = 0;

            while (j < 5)
            {
                ++j;
                int k = rand.nextInt(i);

                for (NordStructurePieces.PieceWeight NordStructurePieces$pieceweight : start.structureVillageWeightedPieceList)
                {
                    k -= NordStructurePieces$pieceweight.villagePieceWeight;

                    if (k < 0)
                    {
                        if (!NordStructurePieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType) || NordStructurePieces$pieceweight == start.lastPlaced && start.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        NordStructurePieces.Village NordStructurePieces$village = findAndCreateComponentFactory(start, NordStructurePieces$pieceweight, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);

                        if (NordStructurePieces$village != null)
                        {
                            ++NordStructurePieces$pieceweight.villagePiecesSpawned;
                            start.lastPlaced = NordStructurePieces$pieceweight;

                            if (!NordStructurePieces$pieceweight.canSpawnMoreVillagePieces())
                            {
                                start.structureVillageWeightedPieceList.remove(NordStructurePieces$pieceweight);
                            }

                            return NordStructurePieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = NordStructurePieces.Brazier.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);

            if (structureboundingbox != null)
            {
                return new NordStructurePieces.Brazier(start, componentType, rand, structureboundingbox, facing);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent generateAndAddComponent(NordStructurePieces.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        if (componentType > 50)
        {
            return null;
        }
        else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 && Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = generateComponent(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType + 1);

            if (structurecomponent != null)
            {
                structureComponents.add(structurecomponent);
                start.pendingHouses.add(structurecomponent);
                return structurecomponent;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent generateAndAddRoadPiece(NordStructurePieces.Start start, List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + start.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = NordStructurePieces.Path.findPieceBox(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                StructureComponent structurecomponent = new NordStructurePieces.Path(start, p_176069_7_, rand, structureboundingbox, facing);
                p_176069_1_.add(structurecomponent);
                start.pendingRoads.add(structurecomponent);
                return structurecomponent;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public static class JarlHall extends NordStructurePieces.Village
        {
            public JarlHall()
            {
            }

            public JarlHall(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45564_4_;
            }

            public static NordStructurePieces.JarlHall createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, NordBuildingsArray.JARLHOUSE_LENGTH, NordBuildingsArray.JARLHOUSE_HEIGHT, NordBuildingsArray.JARLHOUSE_WIDTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new NordStructurePieces.JarlHall(start, p_175854_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.JARLHOUSE_HEIGHT - 1, 0);
                }

                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.jarlhouse_1, NordBuildingsArray.JARLHOUSE_LENGTH, NordBuildingsArray.JARLHOUSE_HEIGHT, NordBuildingsArray.JARLHOUSE_WIDTH);

                this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1, AttributeVocation.CLASS_MERCENARY, 0, true);
                this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 3, 1, AttributeVocation.CLASS_ALCHEMIST, 0, true);
                return true;
            }

            protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
            {
                return 2;
            }
        }

    public static class Field1 extends NordStructurePieces.Village
        {
            /** First crop type for this field. */
            private Block cropTypeA;
            /** Second crop type for this field. */
            private Block cropTypeB;
            /** Third crop type for this field. */
            private Block cropTypeC;
            /** Fourth crop type for this field. */
            private Block cropTypeD;

            public Field1()
            {
            }

            public Field1(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45570_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45570_4_;
                this.cropTypeA = this.getRandomCropType(rand);
                this.cropTypeB = this.getRandomCropType(rand);
                this.cropTypeC = this.getRandomCropType(rand);
                this.cropTypeD = this.getRandomCropType(rand);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
                tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
                tagCompound.setInteger("CC", Block.REGISTRY.getIDForObject(this.cropTypeC));
                tagCompound.setInteger("CD", Block.REGISTRY.getIDForObject(this.cropTypeD));
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
                this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
                this.cropTypeC = Block.getBlockById(tagCompound.getInteger("CC"));
                this.cropTypeD = Block.getBlockById(tagCompound.getInteger("CD"));

                if (!(this.cropTypeA instanceof BlockCrops))
                {
                    this.cropTypeA = Blocks.WHEAT;
                }

                if (!(this.cropTypeB instanceof BlockCrops))
                {
                    this.cropTypeB = Blocks.CARROTS;
                }

                if (!(this.cropTypeC instanceof BlockCrops))
                {
                    this.cropTypeC = Blocks.POTATOES;
                }

                if (!(this.cropTypeD instanceof BlockCrops))
                {
                    this.cropTypeD = Blocks.BEETROOTS;
                }
            }

            private Block getRandomCropType(Random rand)
            {
                switch (rand.nextInt(10))
                {
                    case 0:
                    	//return Blocks.WHEAT;
                    case 1:
                    case 2:
                    case 3:
                    	return Blocks.PUMPKIN_STEM;
                    default:
                    	return Blocks.MELON_STEM;
                }
            }

            public static NordStructurePieces.Field1 createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175851_1_, Random rand, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing facing, int p_175851_7_)
            {
            	if(start.isBanditInfested) return null;
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ? new NordStructurePieces.Field1(start, p_175851_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
                }

                IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 12, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 1, 8, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 1, 11, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 0, 12, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 11, 0, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 11, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 1, 9, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

                for (int i = 1; i <= 7; ++i)
                {
                	//System.out.println("Crop: "+this.cropTypeA.getUnlocalizedName()+" Type: "+(this.cropTypeA instanceof BlockStem));
                    int j = 7;// : ((BlockCrops)this.cropTypeA).getMaxAge();
                    int k = j / 3;
                    this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 1, 1, i, structureBoundingBoxIn);
                 //   this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 2, 1, i, structureBoundingBoxIn);
                    int l = 7;//this.cropTypeA instanceof BlockStem ? 7 : ((BlockCrops)this.cropTypeB).getMaxAge();
                    int i1 = l / 3;
                   // this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, i, structureBoundingBoxIn);
                    int j1 = 7;//this.cropTypeA instanceof BlockStem ? 7 : ((BlockCrops)this.cropTypeC).getMaxAge();
                    int k1 = j1 / 3;
                    this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getInt(randomIn, k1, j1)), 7, 1, i, structureBoundingBoxIn);
                    //this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getInt(randomIn, k1, j1)), 8, 1, i, structureBoundingBoxIn);
                    int l1 = 7;//this.cropTypeA instanceof BlockStem ? 7 : ((BlockCrops)this.cropTypeD).getMaxAge();
                    int i2 = l1 / 3;
                    //this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getInt(randomIn, i2, l1)), 10, 1, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getInt(randomIn, i2, l1)), 11, 1, i, structureBoundingBoxIn);
                }

                for (int j2 = 0; j2 < 9; ++j2)
                {
                    for (int k2 = 0; k2 < 13; ++k2)
                    {
                        this.clearCurrentPositionBlocksUpwards(worldIn, k2, 4, j2, structureBoundingBoxIn);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), k2, -1, j2, structureBoundingBoxIn);
                    }
                }

                return true;
            }
        }

    public static class Field2 extends NordStructurePieces.Village
        {
            /** First crop type for this field. */
            private Block cropTypeA;
            /** Second crop type for this field. */
            private Block cropTypeB;

            public Field2()
            {
            }

            public Field2(NordStructurePieces.Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
            {
                super(start, p_i45569_2_);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45569_4_;
                this.cropTypeA = this.getRandomCropType(rand);
                this.cropTypeB = this.getRandomCropType(rand);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
                tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
                this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
            }

            private Block getRandomCropType(Random rand)
            {
                return Blocks.WHEAT;
            }


            public static NordStructurePieces.Field2 createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
            {
            	if(start.isBanditInfested) return null;
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new NordStructurePieces.Field2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
                }

                IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, iblockstate, iblockstate, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

                for (int i = 1; i <= 7; ++i)
                {
                    int j = ((BlockCrops)this.cropTypeA).getMaxAge();
                    int k = j / 3;
                    this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 1, 1, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 2, 1, i, structureBoundingBoxIn);
                    int l = ((BlockCrops)this.cropTypeB).getMaxAge();
                    int i1 = l / 3;
                    this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, i, structureBoundingBoxIn);
                }

                for (int j1 = 0; j1 < 9; ++j1)
                {
                    for (int k1 = 0; k1 < 7; ++k1)
                    {
                        this.clearCurrentPositionBlocksUpwards(worldIn, k1, 4, j1, structureBoundingBoxIn);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), k1, -1, j1, structureBoundingBoxIn);
                    }
                }

                return true;
            }
        }

    public static class GuardHouse extends NordStructurePieces.Village
        {
            public GuardHouse()
            {
            }

            public GuardHouse(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45567_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45567_4_;
            }

            public static NordStructurePieces.GuardHouse createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, NordBuildingsArray.GUARDHUT_WIDTH, NordBuildingsArray.GUARDHUT_HEIGHT, NordBuildingsArray.GUARDHUT_LENGTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ? new NordStructurePieces.GuardHouse(start, p_175857_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.GUARDHUT_HEIGHT, 0);
                }
                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.guard_hut_0, NordBuildingsArray.GUARDHUT_LENGTH, NordBuildingsArray.GUARDHUT_HEIGHT, NordBuildingsArray.GUARDHUT_WIDTH);

                this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 2, AttributeVocation.CLASS_SOLDIER, 0, true);
                return true;
            }

            protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
            {
                return villagersSpawnedIn == 0 ? 4 : super.chooseProfession(villagersSpawnedIn, currentVillagerProfession);
            }
        }

    public static class ArcherHut extends NordStructurePieces.Village
        {
            public ArcherHut()
            {
            }

            public ArcherHut(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45571_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45571_4_;
            }

            public static NordStructurePieces.ArcherHut createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, NordBuildingsArray.ARCHERHUT_WIDTH, NordBuildingsArray.ARCHERHUT_HEIGHT, NordBuildingsArray.ARCHERHUT_LENGTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new NordStructurePieces.ArcherHut(start, p_175850_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.ARCHERHUT_HEIGHT - 1, 0);
                }

                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.archer_hut_1, NordBuildingsArray.ARCHERHUT_LENGTH, NordBuildingsArray.ARCHERHUT_HEIGHT, NordBuildingsArray.ARCHERHUT_WIDTH);
                this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1, AttributeVocation.CLASS_ARCHER, 0, true);
                return true;
            }

            protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
            {
                return 1;
            }
        }

    public static class DockHouse extends NordStructurePieces.Village
        {
            private boolean hasMadeChest;

            public DockHouse()
            {
            }

            public DockHouse(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45563_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45563_4_;
            }

            public static NordStructurePieces.DockHouse createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, NordBuildingsArray.DOCKYARD_WIDTH, NordBuildingsArray.DOCKYARD_HEIGHT, NordBuildingsArray.DOCKYARD_LENGTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new NordStructurePieces.DockHouse(start, p_175855_7_, rand, structureboundingbox, facing) : null;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Chest", this.hasMadeChest);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.hasMadeChest = tagCompound.getBoolean("Chest");
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            private static final ResourceLocation Inn = new ResourceLocation(Resources.MODID+":inn");
            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.averageGroundLvl < 0)
                {
                    this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                    if (this.averageGroundLvl < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.DOCKYARD_HEIGHT - 1, 0);
                }
                //BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0));
                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.dockyard_0, NordBuildingsArray.DOCKYARD_LENGTH, NordBuildingsArray.DOCKYARD_HEIGHT, NordBuildingsArray.DOCKYARD_WIDTH);

                //ModTemplate template = WorldGenVillage.templateHandler.getTemplate(worldIn.getMinecraftServer(), Inn);//worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), Inn);
                
                //template.addBlocksToWorld(worldIn, pos, WorldGenVillage.settings);
                this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 1, 1, AttributeVocation.CLASS_VILLAGER, 1, false);
                return true;
            }

            protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
            {
                return 3;
            }
        }

    public static class LongHouse extends NordStructurePieces.Village
        {
            public LongHouse()
            {
            }

            public LongHouse(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45561_4_;
            }

            public static NordStructurePieces.LongHouse createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, NordBuildingsArray.LONGHOUSE_WIDTH, NordBuildingsArray.LONGHOUSE_HEIGHT, NordBuildingsArray.LONGHOUSE_LENGTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new NordStructurePieces.LongHouse(start, p_175849_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.LONGHOUSE_HEIGHT - 1, -1);
                }

                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.longhouse_1, NordBuildingsArray.LONGHOUSE_LENGTH, NordBuildingsArray.LONGHOUSE_HEIGHT, NordBuildingsArray.LONGHOUSE_WIDTH);
                //this.placeTorch(worldIn, EnumFacing.EAST, 2, 3, 0, structureBoundingBoxIn);
                //this.placeTorch(worldIn, EnumFacing.WEST, 3, 3, 0, structureBoundingBoxIn);
                //this.placeTorch(worldIn, EnumFacing.NORTH, 5, 3, 0, structureBoundingBoxIn);
                
                //this.placeTorch(worldIn, EnumFacing.SOUTH, 6, 3, -1, structureBoundingBoxIn);
                


                this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2, AttributeVocation.CLASS_VILLAGER, randomIn.nextInt(100) < 50 ? 1 : 2, false);
                return true;
            }

	
        }

    public static class LargerHut extends NordStructurePieces.Village
        {
            private boolean isRoofAccessible;

            public LargerHut()
            {
            }

            public LargerHut(NordStructurePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
            {
                super(start, p_i45566_2_);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45566_4_;
                this.isRoofAccessible = rand.nextBoolean();
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Terrace", this.isRoofAccessible);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.isRoofAccessible = tagCompound.getBoolean("Terrace");
            }

            public static NordStructurePieces.LargerHut createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, NordBuildingsArray.LONGHOUSE_WIDTH_2, NordBuildingsArray.LONGHOUSE_HEIGHT_2, NordBuildingsArray.LONGHOUSE_LENGTH_2, facing);
                return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new NordStructurePieces.LargerHut(start, p_175858_7_, rand, structureboundingbox, facing);
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.LONGHOUSE_HEIGHT_2 - 1, 0);
                }

               this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.longhouse_2, NordBuildingsArray.LONGHOUSE_LENGTH_2, NordBuildingsArray.LONGHOUSE_HEIGHT_2, NordBuildingsArray.LONGHOUSE_WIDTH_2);

                this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1, AttributeVocation.CLASS_VILLAGER, 0, false);
                return true;
            }
        }

    public static class Path extends NordStructurePieces.Road
        {
            private int length;

            public Path()
            {
            }

            public Path(NordStructurePieces.Start start, int p_i45562_2_, Random rand, StructureBoundingBox p_i45562_4_, EnumFacing facing)
            {
                super(start, p_i45562_2_);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45562_4_;
                this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("Length", this.length);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.length = tagCompound.getInteger("Length");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                boolean flag = false;

                for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5))
                {
                    StructureComponent structurecomponent = this.getNextComponentNN((NordStructurePieces.Start)componentIn, listIn, rand, 0, i);

                    if (structurecomponent != null)
                    {
                        i += Math.max(structurecomponent.getBoundingBox().getXSize(), structurecomponent.getBoundingBox().getZSize());
                        flag = true;
                    }
                }

                for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
                {
                    StructureComponent structurecomponent1 = this.getNextComponentPP((NordStructurePieces.Start)componentIn, listIn, rand, 0, j);

                    if (structurecomponent1 != null)
                    {
                        j += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                        flag = true;
                    }
                }

                EnumFacing enumfacing = this.getCoordBaseMode();

                if (flag && rand.nextInt(3) > 0 && enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                        default:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                            break;
                        case SOUTH:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                            break;
                        case WEST:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                            break;
                        case EAST:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }

                if (flag && rand.nextInt(3) > 0 && enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                        default:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                            break;
                        case SOUTH:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                            break;
                        case WEST:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                            break;
                        case EAST:
                            NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }
            }

            public static StructureBoundingBox findPieceBox(NordStructurePieces.Start start, List<StructureComponent> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing)
            {
                for (int i = 7 * MathHelper.getInt(rand, 3, 5); i >= 7; i -= 7)
                {
                    StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);

                    if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null)
                    {
                        return structureboundingbox;
                    }
                }

                return null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.GRASS_PATH.getDefaultState());
                IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
                IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.GRAVEL.getDefaultState());
                IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());

                for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
                {
                    for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                    {
                        BlockPos blockpos = new BlockPos(i, 64, j);

                        if (structureBoundingBoxIn.isVecInside(blockpos))
                        {
                            blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();

                            if (blockpos.getY() < worldIn.getSeaLevel())
                            {
                                blockpos = new BlockPos(blockpos.getX(), worldIn.getSeaLevel() - 1, blockpos.getZ());
                            }

                            while (blockpos.getY() >= worldIn.getSeaLevel() - 1)
                            {
                                IBlockState iblockstate4 = worldIn.getBlockState(blockpos);

                                if (iblockstate4.getBlock() == Blocks.GRASS && worldIn.isAirBlock(blockpos.up()))
                                {
                                    worldIn.setBlockState(blockpos, iblockstate, 2);
                                    break;
                                }

                                if (iblockstate4.getMaterial().isLiquid())
                                {
                                    worldIn.setBlockState(blockpos, iblockstate1, 2);
                                    break;
                                }

                                if (iblockstate4.getBlock() == Blocks.SAND || iblockstate4.getBlock() == Blocks.SANDSTONE || iblockstate4.getBlock() == Blocks.RED_SANDSTONE)
                                {
                                    worldIn.setBlockState(blockpos, iblockstate2, 2);
                                    worldIn.setBlockState(blockpos.down(), iblockstate3, 2);
                                    break;
                                } 

                                blockpos = blockpos.down();
                            }
                        }
                    }
                }

                return true;
            }
        }

    public static class PieceWeight
        {
            public Class <? extends NordStructurePieces.Village > villagePieceClass;
            public final int villagePieceWeight;
            public int villagePiecesSpawned;
            public int villagePiecesLimit;

            public PieceWeight(Class <? extends NordStructurePieces.Village > p_i2098_1_, int p_i2098_2_, int p_i2098_3_)
            {
                this.villagePieceClass = p_i2098_1_;
                this.villagePieceWeight = p_i2098_2_;
                this.villagePiecesLimit = p_i2098_3_;
            }

            public boolean canSpawnMoreVillagePiecesOfType(int componentType)
            {
                return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
            }

            public boolean canSpawnMoreVillagePieces()
            {
                return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
            }
        }

    public abstract static class Road extends NordStructurePieces.Village
        {
            public Road()
            {
            }

            protected Road(NordStructurePieces.Start start, int type)
            {
                super(start, type);
            }
        }

    public static class Start extends NordStructurePieces.Well
        {
            public BiomeProvider biomeProvider;
            /** World terrain type, 0 for normal, 1 for flap map */
            public int terrainType;
            public NordStructurePieces.PieceWeight lastPlaced;
            /**
             * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they
             * are removed from this list
             */
            public List<NordStructurePieces.PieceWeight> structureVillageWeightedPieceList;
            public List<StructureComponent> pendingHouses = Lists.<StructureComponent>newArrayList();
            public List<StructureComponent> pendingRoads = Lists.<StructureComponent>newArrayList();
            public Biome biome;

            public Start()
            {
            }

            public Start(BiomeProvider biomeProviderIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_, List<NordStructurePieces.PieceWeight> p_i2104_6_, int p_i2104_7_)
            {
                super((NordStructurePieces.Start)null, 0, rand, p_i2104_4_, p_i2104_5_);
                this.biomeProvider = biomeProviderIn;
                this.structureVillageWeightedPieceList = p_i2104_6_;
                this.terrainType = p_i2104_7_;
                Biome biome = biomeProviderIn.getBiome(new BlockPos(p_i2104_4_, 0, p_i2104_5_), Biomes.DEFAULT);
                this.biome = biome;
                this.startPiece = this;

                if (biome instanceof BiomeDesert)
                {
                    this.structureType = 1;
                }
                else if (biome instanceof BiomeSavanna)
                {
                    this.structureType = 2;
                }
                else if (biome instanceof BiomeTaiga)
                {
                    this.structureType = 3;
                }

                this.setStructureType(this.structureType);
                this.isZombieInfested = rand.nextInt(100) <= Main.nord_zombie_infest_rate;
                if(!this.isZombieInfested) 
                {
                	this.isBanditInfested = rand.nextInt(100) <= Main.nord_bandit_infest_rate;
                }
                else 
                {
                	this.isBanditInfested = false;
                }
            }
        }

    public static class Brazier extends NordStructurePieces.Village
        {
            public Brazier()
            {
            }

            public Brazier(NordStructurePieces.Start start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing)
            {
                super(start, p_i45568_2_);
                this.setCoordBaseMode(facing);
                this.boundingBox = p_i45568_4_;
            }

            public static StructureBoundingBox findPieceBox(NordStructurePieces.Start start, List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing)
            {
            	if(start.isBanditInfested || start.isZombieInfested) return null;
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 3, facing);
                return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 2 - 1, 0);
                }

                IBlockState iblockstate = Blocks.COBBLESTONE.getDefaultState();
                Block noSpreadFire = Blocks.FIRE.setTickRandomly(false);
                //System.out.println("Can tick: "+noSpreadFire.getTickRandomly());
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 2, 3, iblockstate, iblockstate, false);
                this.setBlockState(worldIn, BlockRegister.firewood.getDefaultState(), 2, 1, 2, structureBoundingBoxIn);
               // this.setBlockState(worldIn, noSpreadFire.getDefaultState(), 2, 2, 2, structureBoundingBoxIn);
                this.placeTorch(worldIn, EnumFacing.UP, 2, 2, 2, structureBoundingBoxIn);
                if(worldIn.rand.nextInt(100) <= 50) this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 3, 3, 3, Blocks.COBBLESTONE_WALL.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 3, 2, structureBoundingBoxIn);
                //this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
                /*this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate, 1, 1, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate, 1, 2, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WOOL.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, structureBoundingBoxIn);
                this.placeTorch(worldIn, EnumFacing.EAST, 2, 3, 0, structureBoundingBoxIn);
                this.placeTorch(worldIn, EnumFacing.NORTH, 1, 3, 1, structureBoundingBoxIn);
                this.placeTorch(worldIn, EnumFacing.WEST, 0, 3, 0, structureBoundingBoxIn);
                this.placeTorch(worldIn, EnumFacing.SOUTH, 1, 3, -1, structureBoundingBoxIn);*/
                return true;
            }
        }

    public abstract static class Village extends StructureComponent
        {
            protected int averageGroundLvl = -1;
            /** The number of villagers that have been spawned in this component. */
            private int villagersSpawned;
            protected int structureType;
            protected boolean isZombieInfested;
            protected boolean isBanditInfested;
            EntityHumanVillager ruler;
            protected NordStructurePieces.Start startPiece;

            public Village()
            {
            }

            protected Village(NordStructurePieces.Start start, int type)
            {
                super(type);

                if (start != null)
                {
                    this.structureType = start.structureType;
                    this.isZombieInfested = start.isZombieInfested;
                    this.isBanditInfested = start.isBanditInfested;
                    this.ruler = null;
                    startPiece = start;
                }
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                tagCompound.setInteger("HPos", this.averageGroundLvl);
                tagCompound.setInteger("VCount", this.villagersSpawned);
                tagCompound.setByte("Type", (byte)this.structureType);
                tagCompound.setBoolean("Zombie", this.isZombieInfested);
                tagCompound.setBoolean("Bandit", this.isBanditInfested);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                this.averageGroundLvl = tagCompound.getInteger("HPos");
                this.villagersSpawned = tagCompound.getInteger("VCount");
                this.structureType = tagCompound.getByte("Type");

                if (tagCompound.getBoolean("Desert"))
                {
                    this.structureType = 1;
                }

                this.isZombieInfested = tagCompound.getBoolean("Zombie");
                this.isBanditInfested = tagCompound.getBoolean("Bandit");
            }

            /**
             * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
             */
            @Nullable
            protected StructureComponent getNextComponentNN(NordStructurePieces.Start start, List<StructureComponent> structureComponents, Random rand, int p_74891_4_, int p_74891_5_)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                        default:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                        case SOUTH:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                        case WEST:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        case EAST:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }
                else
                {
                    return null;
                }
            }

            /**
             * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
             */
            @Nullable
            protected StructureComponent getNextComponentPP(NordStructurePieces.Start start, List<StructureComponent> structureComponents, Random rand, int p_74894_4_, int p_74894_5_)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                        default:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                        case SOUTH:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                        case WEST:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        case EAST:
                            return NordStructurePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }
                else
                {
                    return null;
                }
            }

            /**
             * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
             * all the levels in the BB's horizontal rectangle).
             */
            protected int getAverageGroundLevel(World worldIn, StructureBoundingBox structurebb)
            {
                int i = 0;
                int j = 0;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
                {
                    for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                    {
                        blockpos$mutableblockpos.setPos(l, 64, k);

                        if (structurebb.isVecInside(blockpos$mutableblockpos))
                        {
                            i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel() - 1);
                            ++j;
                        }
                    }
                }

                if (j == 0)
                {
                    return -1;
                }
                else
                {
                    return i / j;
                }
            }

            protected static boolean canVillageGoDeeper(StructureBoundingBox structurebb)
            {
                return structurebb != null && structurebb.minY > 10;
            }

            /**
             * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y
             * offset, z offset, number of villagers
             * @param b 
             */
            protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count, int vocation, int rank, boolean upgrade)
            {
                if (this.villagersSpawned < count)
                {
                    for (int i = this.villagersSpawned; i < count; ++i)
                    {
                        int j = this.getXWithOffset(x, z + i);
                        //j -= 0.5D;
                        int k = this.getYWithOffset(y);
                        int l = this.getZWithOffset(x, z + i);
                        
                        
                        //l -= 0.5D;

                        if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                        {
                            break;
                        }

                        ++this.villagersSpawned;

                        
                        
                        if (this.isZombieInfested)
                        {
                            EntityZombieVillager entityzombievillager = new EntityZombieVillager(worldIn);
                            entityzombievillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                            entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData)null);
                            entityzombievillager.enablePersistence();
                            worldIn.spawnEntity(entityzombievillager);
                        }
                        else if(this.isBanditInfested)
                        {
                        	EntityHumanVillager entityHumanVillager = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, 
                        			AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomBandit(worldIn), EntityHumanVillager.getRandomGender(worldIn), vocation == AttributeVocation.CLASS_RULER ? true : false);                            
                        	entityHumanVillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                        	
                        	if(worldIn.rand.nextInt(100) <= Main.villager_bjorn_rate) entityHumanVillager.setShifter(true, new EntityBjornserker(worldIn), true, -1);
                        	
                        	if(entityHumanVillager.isRuler())
                        	{
                        		entityHumanVillager.getFaction().setBandit(true);
                        		this.ruler = entityHumanVillager;
                        		//System.out.println("Ruler: - "+this.ruler.getTitle());
                        	}
                        	
                            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
                            //entityHumanVillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
                            worldIn.spawnEntity(entityHumanVillager);
                            
                            if(upgrade)
                            {
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance) entityHumanVillager.upgrade();
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance/5) entityHumanVillager.upgrade();
                            }
                            
                            if(worldIn.rand.nextInt(100) < 30) 
                            {
                            	EntityWarg entityWarg = new EntityWarg(worldIn);       
	                        	entityWarg.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l - 7.5D, 0.0F, 0.0F);
	                            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
	                            //entityWarg.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityWarg)), (IEntityLivingData)null, false);
	                            worldIn.spawnEntity(entityWarg); 
                            }
                            if(worldIn.rand.nextInt(100) <= Main.villager_bjorn_rate * 3) 
                            {
                            	EntityHumanVillager entityBjorn = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomBandit(worldIn), EntityHumanVillager.GENDER_MALE, true, new EntityBjornserker(worldIn));                            
                            	entityBjorn.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                                //entityBjorn.setProfession(this.chooseForgeProfession(i, entityBjorn.getProfessionForge()));
                                //entityBjorn.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityBjorn)), (IEntityLivingData)null, false);
                                worldIn.spawnEntity(entityBjorn);
                            }
                        }
                        else if(vocation == AttributeVocation.CLASS_RULER)
                        {
                        	if(this.ruler == null)
                        	{
                            	EntityHumanVillager entityRuler = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomRuler(worldIn), EntityHumanVillager.getRandomGender(worldIn), true);                            
                        		this.ruler = entityRuler;
                        	}
                        	
                        	if(worldIn.rand.nextInt(100) <= Main.villager_bjorn_rate) ruler.setShifter(true, new EntityBjornserker(worldIn), true, -1);
                        	/*AttributeFaction faction = new AttributeFaction(worldIn, new BlockPos(x, 70, z), AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID), NameGenerator.generateRandomName(worldIn.rand, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID)));
                            faction.setBandit(isBanditInfested);
                            faction.setName();
                            faction.addRuler(ruler);
                            WorldGenVillage.nordManager.addFaction(faction);
                            WorldGenVillage.nordManager.addRuler(faction.getID(), ruler);
                            System.out.println(ruler.getName()+" - "+faction.getTitleName());*/
                        	//EntityHumanVillager entityHumanVillager = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomRuler(worldIn), EntityHumanVillager.getRandomGender(worldIn));                            
                        	this.ruler.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
                            //entityHumanVillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
                            worldIn.spawnEntity(this.ruler);
                        }
                        else if(vocation == AttributeVocation.CLASS_MERCENARY)
                        {
                        	EntityHumanVillager entityHumanVillager = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomMercenary(worldIn), EntityHumanVillager.getRandomGender(worldIn), false);                            
                        	entityHumanVillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    
                        	if(worldIn.rand.nextInt(100) <= Main.villager_bjorn_rate) entityHumanVillager.setShifter(true, new EntityBjornserker(worldIn), true, -1);
                            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
                           // entityHumanVillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
                            worldIn.spawnEntity(entityHumanVillager);
                            
                            if(upgrade)
                            {
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance) entityHumanVillager.upgrade();
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance/5) entityHumanVillager.upgrade();
                            }
                        }
                        else
                        {
                        	if(this.ruler == null)
                        	{
                            	EntityHumanVillager entityRuler = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, AttributeRace.getFromIDRace(WorldGenVillage.NORD_ID).getRandomRuler(worldIn), EntityHumanVillager.getRandomGender(worldIn), true);                            
                        		this.ruler = entityRuler;
                        	}
                        	
                        	EntityHumanVillager entityHumanVillager = new EntityHumanVillager(worldIn, WorldGenVillage.NORD_ID, vocation, rank, EntityHumanVillager.getRandomGender(worldIn));                            
                        	entityHumanVillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                        	entityHumanVillager.setRuler(ruler);
                        	
                        	if(worldIn.rand.nextInt(100) <= Main.villager_bjorn_rate) entityHumanVillager.setShifter(true, new EntityBjornserker(worldIn), true, -1);
                            //entityHumanVillager.setProfession(this.chooseForgeProfession(i, entityHumanVillager.getProfessionForge()));
                           // entityHumanVillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityHumanVillager)), (IEntityLivingData)null, false);
                            worldIn.spawnEntity(entityHumanVillager);
                            
                            if(upgrade)
                            {
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance) entityHumanVillager.upgrade();
                            	if(worldIn.rand.nextInt(100) < Main.upgrade_chance/5) entityHumanVillager.upgrade();
                            }
                        }
                    }
                }
            }

            @Deprecated // Use Forge version below.
            protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
            {
                return currentVillagerProfession;
            }
            protected net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession chooseForgeProfession(int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof)
            {
                return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(chooseProfession(count, net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof)));
            }

            protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn)
            {
                net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, blockstateIn);
                net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
                if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getReplacement();
                /*if (this.structureType == 1)
                {
                    if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
                    {
                        return Blocks.SANDSTONE.getDefaultState();
                    }

                    if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
                    {
                        return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
                    }

                    if (blockstateIn.getBlock() == Blocks.PLANKS)
                    {
                        return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
                    }

                    if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
                    {
                        return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
                    }

                    if (blockstateIn.getBlock() == Blocks.STONE_STAIRS)
                    {
                        return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
                    }

                    if (blockstateIn.getBlock() == Blocks.GRAVEL)
                    {
                        return Blocks.SANDSTONE.getDefaultState();
                    }
                }
                else if (this.structureType == 3)
                {
                    if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
                    {
                        return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
                    }

                    if (blockstateIn.getBlock() == Blocks.PLANKS)
                    {
                        return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
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
                else if (this.structureType == 2)
                {
                    if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
                    {
                        return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
                    }

                    if (blockstateIn.getBlock() == Blocks.PLANKS)
                    {
                        return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
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
                        return Blocks.ACACIA_FENCE.getDefaultState();
                    }
                }*/
                if(Blocks.LOG == blockstateIn.getBlock()  || blockstateIn.getBlock()  == Blocks.LOG2){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS))
					
				}
				
				if(Blocks.COBBLESTONE == blockstateIn.getBlock() ){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS))
					
				}
				
				if(Blocks.PLANKS == blockstateIn.getBlock() ){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}
				
				if(Blocks.OAK_FENCE == blockstateIn.getBlock() ){
					return Blocks.OAK_FENCE.getDefaultState();
					
				}
				
				if(Blocks.OAK_STAIRS == blockstateIn.getBlock() ){
					return Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
					
				}
				
				/*if(Blocks.STONE_STAIRS == event.getOriginal()){
					//return Blocks.BIRCH_STAIRS.getDefaultState()
				}*/
				
				if(Blocks.GRASS_PATH == blockstateIn.getBlock() ){
					return Blocks.GRASS_PATH.getDefaultState();//.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH)
					
				}
				
				if(Blocks.GRAVEL == blockstateIn.getBlock() ){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}
				
				if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock() ){
					return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);//.withProperty(BlockLog.LOG_AXIS, event.getOriginal().getValue(BlockLog.LOG_AXIS))
					
				}
				
				if(Blocks.STONEBRICK == blockstateIn.getBlock() ){
					return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
					
				}

                return blockstateIn;
            }

            protected BlockDoor biomeDoor()
            {
                /*switch (this.structureType)
                {
                    case 2:
                        return Blocks.ACACIA_DOOR;
                    case 3:
                        return Blocks.SPRUCE_DOOR;
                    default:
                        return Blocks.OAK_DOOR;
                }*/
            	return Blocks.OAK_DOOR;
            }

            protected void createVillageDoor(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_, Mirror mirror)
            {
                if (!this.isZombieInfested)
                {
                	//this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH);
                	//this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withMirror(Mirror.LEFT_RIGHT);
                    this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, (BlockDoor) this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withMirror(mirror).getBlock());
                }
                else if(this.isBanditInfested && p_189927_1_.rand.nextInt(100) > 25)
                {
                    this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, (BlockDoor) this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withMirror(mirror).getBlock());
                }
            }

            protected void placeTorch(World p_189926_1_, EnumFacing p_189926_2_, int p_189926_3_, int p_189926_4_, int p_189926_5_, StructureBoundingBox p_189926_6_)
            {
            	//System.out.println(p_189926_2_.getName()+" Placing: "+p_189926_3_+" "+p_189926_4_+" "+p_189926_5_);
                if (!this.isZombieInfested || !this.isBanditInfested)
                {
                    this.setBlockState(p_189926_1_, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, p_189926_2_), p_189926_3_, p_189926_4_, p_189926_5_, p_189926_6_);
                }
            }

            /**
             * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or
             * liquid
             */
            protected void replaceAirAndLiquidDownwards(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
            {
                IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
                super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
            }

            protected void setStructureType(int p_189924_1_)
            {
                this.structureType = p_189924_1_;
            }
            
            protected void placeStructureBase(World worldIn, StructureBoundingBox structureBoundingBoxIn, Random randomIn, char[][] plan, int length, int height, int width) {
				IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
                IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, EnumAxis.X));
                IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
                IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
                IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
                IBlockState iblockstate7 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
                IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
                IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.GLASS.getDefaultState());
                IBlockState iblockstate8 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
                IBlockState iblockstate9 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                
                for(int y = 0; y < height; y++)
                {
                	for(int z = 0; z < length; z++)
                	{
	                	for(int x = 0; x < width; x++)
	                	{
	                		switch(plan[y][x + (z * width)])
	                		{
		                		case CharBlockTypes.FENCE:
		                		{
		                			this.setBlockState(worldIn, iblockstate8, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.COBBLESTONE:
		                		{
		                			this.setBlockState(worldIn, iblockstate, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.LOG_UP:
		                		{
		                			this.setBlockState(worldIn, iblockstate2, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.AIR:
		                		{
		                			this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.CRAFTING_TABLE:
		                		{
		                			this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.DOOR_NORTH:
		                		{
		                			this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, x, y, z, EnumFacing.NORTH, Mirror.NONE);
		                            if (this.getBlockStateFromPos(worldIn, x, y - 1, z - 1, structureBoundingBoxIn).getMaterial() == Material.AIR)
		                            {
		                            	this.setBlockState(worldIn, iblockstate7, x, y - 1, z - 1, structureBoundingBoxIn);
		                            }
		                            if (this.getBlockStateFromPos(worldIn, x, y - 2, z - 1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                                    {
                                        this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), x, y - 2, z - 1, structureBoundingBoxIn);
                                    }   
		                		}
		                		break;
		                		case CharBlockTypes.DOOR_SOUTH:
		                		{
		                		    this.setBlockState(worldIn, this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH), x, y, z, structureBoundingBoxIn);
		                		    this.setBlockState(worldIn, this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withMirror(Mirror.LEFT_RIGHT), x, y + 1, z, structureBoundingBoxIn);
		                		    if (this.getBlockStateFromPos(worldIn, x, y - 1, z - 1, structureBoundingBoxIn).getMaterial() == Material.AIR)
			                        {
			                          	this.setBlockState(worldIn, iblockstate7, x, y - 1, z - 1, structureBoundingBoxIn);
			                        }
			                        if (this.getBlockStateFromPos(worldIn, x, y - 2, z - 1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
	                                {
	                                   this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), x, y - 2, z - 1, structureBoundingBoxIn);
	                                }   
		                			//this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH);
		                        	//this.biomeDoor().getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withMirror(Mirror.LEFT_RIGHT);
		                            
		                	    }
		                		break;
		                		case CharBlockTypes.NULL:
		                		{
		                			continue;
		                		}
		                		case CharBlockTypes.LOG_SIDE:
		                		{
		                			this.setBlockState(worldIn, iblockstate2, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.GLASS:
		                		{
		                			this.setBlockState(worldIn, iblockstate6, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.OAK_WOOD:
		                		{
		                			this.setBlockState(worldIn, iblockstate5, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.STAIRS_LEFT:
		                		{
		                			this.setBlockState(worldIn, iblockstate3, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.STAIRS_RIGHT:
		                		{
		                			this.setBlockState(worldIn, iblockstate4, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.STAIRS_NORTH:
		                		{
		                			this.setBlockState(worldIn, iblockstate7, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.STAIRS_SOUTH:
		                		{
		                			this.setBlockState(worldIn, iblockstate9, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.TORCH_NORTH:
		                		{
		                			this.placeTorch(worldIn, EnumFacing.NORTH, x, y, z, structureBoundingBoxIn);
		                		}
		                		break;
		                		case CharBlockTypes.CHEST:
		                		{
		                		      this.generateChest(worldIn, structureBoundingBoxIn, randomIn, x, y, z, LootTableList.CHESTS_VILLAGE_BLACKSMITH);
		                	    }
		                		break;
		                		case CharBlockTypes.TORCH_SOUTH:
		                		{
		                			//this.setBlockState(worldIn, iblockstate8, x, y, z, structureBoundingBoxIn);
		                			this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z, structureBoundingBoxIn);
		                			/*if(z == 0)
		                			{
		                				this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, 0, structureBoundingBoxIn);
		                	//			System.out.println("Z Coord 1: "+z+" X Coord 1: "+x+" Y Coord 1: "+y);
		                			}
		                			if(z == 9)
		                			{
		                				this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, 9, structureBoundingBoxIn);
		                //				System.out.println("Z Coord 2: "+z+" X Coord 2: "+x+" Y Coord 2: "+y);
		                			}*/
		       
		              //  			System.out.println("Z Coord: "+z+" X Coord : "+x+" Y Coord : "+y);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z-2, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z-1, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z+1, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, z+2, structureBoundingBoxIn);
		                			
		                			
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, 0, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.SOUTH, x, y, 9, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.EAST, x, y, z + 1, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.WEST, x + 1, y, z + 1, structureBoundingBoxIn);
		                			//this.placeTorch(worldIn, EnumFacing.NORTH, x + 2, y, z + 1, structureBoundingBoxIn);
		                		}
		                		break;
	                		}
	                	}
                	}
                }
                
                for (int i = 0; i <= length - 1; ++i)
                {
                    for (int j = 0; j <= width - 1; ++j)
                    {
                        //if (j == 0 || j == 5 || i == 0 || i == 5)
                        //{
                        //this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, height + 1, i, structureBoundingBoxIn);
                        this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j, -1, i, structureBoundingBoxIn);
                  
                        //}
                    }
                }

			}
        }

    public static class Well extends NordStructurePieces.Village
        {
    	 public Well()
         {
         }

         public Well(NordStructurePieces.Start start, int type, Random rand, int x, int z)
         {
             super(start, type);
             this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

             if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
             {
                 this.boundingBox = new StructureBoundingBox(x, 64, z, x + 5 - 1, 78, z + 5 - 1);
             }
             else
             {
                 this.boundingBox = new StructureBoundingBox(x, 64, z, x + 5 - 1, 78, z + 5 - 1);
             }
         }

         /**
          * Initiates construction of the Structure Component picked, at the current Location of StructGen
          */
         public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
         {
             NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
             NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
             NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
             NordStructurePieces.generateAndAddRoadPiece((NordStructurePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
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
             this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 3, 12, 3, iblockstate, Blocks.FLOWING_WATER.getDefaultState(), false);
             this.setBlockState(worldIn, Blocks.LADDER.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
             /*this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
             this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
             this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);*/
             this.setBlockState(worldIn, iblockstate1, 1, 13, 1, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 1, 14, 1, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 3, 13, 1, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 3, 14, 1, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 1, 13, 3, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 1, 14, 3, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 3, 13, 3, structureBoundingBoxIn);
             this.setBlockState(worldIn, iblockstate1, 3, 14, 3, structureBoundingBoxIn);
             this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 3, 15, 3, iblockstate, iblockstate, false);

             for (int i = 0; i <= 4; ++i)
             {
                 for (int j = 0; j <= 4; ++j)
                 {
                     if (j == 0 || j == 4 || i == 0 || i == 4)
                     {
                         this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
                         this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                     }
                 }
             }
             
             /*int radius = 25 * 4;
             BlockPos center = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0));
             BlockPos northStart = center.add(0, 0, radius);
             BlockPos southStart = center.add(0, 0, -radius);
             BlockPos westStart = center.add(radius, 0, 0);
             BlockPos eastStart = center.add(-radius, 0, 0);
             
             for(int xx = -radius; xx < radius; xx++)
             {
             	BlockPos posE = new BlockPos(eastStart.add(xx, 0, 0).getX(), eastStart.getY(), eastStart.getZ());
             	BlockPos posW = new BlockPos(westStart.add(xx, 0, 0).getX(), westStart.getY(), westStart.getZ());
             	
             	for(int y = -10; y < 30; y++)
             	{
                 	if(posE.getX() >= -2 && 2 <= posE.getX() && y > 0 && y < 2)
                 	{
                 		continue;
                 	}
                 	
                 	if(posW.getX() >= -2 && 2 <= posE.getX() && y > 0 && y < 2)
                 	{
                 		continue;
                 	} 
                 	
                 	worldIn.setBlockState(posE.add(0, y, 0), iblockstate);
                 	worldIn.setBlockState(posW.add(0, y, 0), iblockstate);
             	}
             }
             
             for(int zz = -radius; zz < radius; zz++)
             {
             	BlockPos posE = new BlockPos(northStart.getX(), northStart.getY(), northStart.add(0, 0, zz).getZ());
             	BlockPos posW = new BlockPos(southStart.getX(), southStart.getY(), southStart.add(0, 0, zz).getZ());
             	
             	for(int y = -10; y < 10; y++)
             	{
                 	worldIn.setBlockState(posE.add(0, y, 0), iblockstate);
                 	worldIn.setBlockState(posW.add(0, y, 0), iblockstate);
             	}
             }*/
             this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 13, 2, 1, AttributeVocation.CLASS_RULER, 0, false);
             return true;
         }
        }

    public static class NordHut extends NordStructurePieces.Village
        {
            private boolean isTallHouse;
            private int tablePosition;

            public NordHut()
            {
            }

            public NordHut(NordStructurePieces.Start start, int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing)
            {
                super(start, type);
                this.setCoordBaseMode(facing);
                this.boundingBox = structurebb;
                this.isTallHouse = rand.nextBoolean();
                this.tablePosition = rand.nextInt(3);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("T", this.tablePosition);
                tagCompound.setBoolean("C", this.isTallHouse);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
            {
                super.readStructureFromNBT(tagCompound, p_143011_2_);
                this.tablePosition = tagCompound.getInteger("T");
                this.isTallHouse = tagCompound.getBoolean("C");
            }

            public static NordStructurePieces.NordHut createPiece(NordStructurePieces.Start start, List<StructureComponent> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, NordBuildingsArray.HUT_WIDTH, NordBuildingsArray.HUT_HEIGHT, NordBuildingsArray.HUT_LENGTH, facing);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new NordStructurePieces.NordHut(start, p_175853_7_, rand, structureboundingbox, facing) : null;
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

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + NordBuildingsArray.HUT_HEIGHT, 0);
                }

                this.placeStructureBase(worldIn, structureBoundingBoxIn, randomIn, NordBuildingsArray.hut_1, NordBuildingsArray.HUT_LENGTH, NordBuildingsArray.HUT_HEIGHT, NordBuildingsArray.HUT_WIDTH);

                this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1, AttributeVocation.CLASS_VILLAGER, 0, false);
                return true;
            }
        }
}