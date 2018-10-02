package net.theexceptionist.coherentvillages.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.Resources;

public class ModMapVillageGen extends MapGenVillage
{
    /** A list of all the biomes villages can spawn in. */
    public static ArrayList<Biome> VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    /** None */
    private int size;
    private int distance;
    private final int minTownSeparation;
 
    public ModMapVillageGen()
    {
        this.distance = Main.max_distance;
        this.minTownSeparation = Main.min_distance;
        this.size = Main.village_size;
    }

    public ModMapVillageGen(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (((String)entry.getKey()).equals("size"))
            {
                this.size = MathHelper.getInt(entry.getValue(), this.size, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }

    public String getStructureName()
    {
        return "Village";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l)
        {
            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 10387312, false, 100, findUnexplored);
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
    	//System.out.println("Starting");
        return new ModMapVillageGen.Start(this.world, this.rand, chunkX, chunkZ, this.size);
    }

    public static class Start extends StructureStart
        {
            /** well ... thats what it does */
            private boolean hasMoreThanTwoComponents;

            public Start()
            {
            }

            public Start(World worldIn, Random rand, int x, int z, int size)
            {
                super(x, z);
               // System.out.println("Generating: "+x+" "+z);
                List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
                StructureVillagePieces.Start structurevillagepieces$start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
                this.components.add(structurevillagepieces$start);
                structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
                List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
                List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;
       
                //list2.add(new VillageComponentVillageWall(structurevillagepieces$start, rand, structurevillagepieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
                
                while (!list1.isEmpty() || !list2.isEmpty())
                {
                    if (list1.isEmpty())
                    {
                        int i = rand.nextInt(list2.size());
                        StructureComponent structurecomponent = list2.remove(i);
                        structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                    }
                    else
                    {
                        int j = rand.nextInt(list1.size());
                        StructureComponent structurecomponent2 = list1.remove(j);
                        structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                    }
                }

                this.updateBoundingBox();
                int k = 0;

                for (StructureComponent structurecomponent1 : this.components)
                {
                    if (!(structurecomponent1 instanceof StructureVillagePieces.Road))
                    {
                        ++k;
                    }
                }

                this.hasMoreThanTwoComponents = k > 2;
                
                
                //Village village = worldIn.getVillageCollection().getNearestVillage(new BlockPos(x, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY(), z), 30);
                //int y = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();

               /* BlockPos center = new BlockPos(x, 80, z);
                int radius = numHouses * 10;
                System.out.println("Radius: "+radius);
                BlockPos nWall = new BlockPos(x, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY(), z + radius);
                BlockPos sWall = new BlockPos(x, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY(), z - radius);
                BlockPos eWall = new BlockPos(x + radius, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY(), z);
                BlockPos wWall = new BlockPos(x - radius, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY(), z);
                
                for(int i = x - radius; i < x + radius; i++)
                {
                	worldIn.setBlockState(new BlockPos(i, worldIn.getTopSolidOrLiquidBlock(new BlockPos(i, 80, z + radius)).getY(), z+ radius), Blocks.STONEBRICK.getDefaultState()); 
                	worldIn.setBlockState(new BlockPos(i, worldIn.getTopSolidOrLiquidBlock(new BlockPos(i, 80, z - radius)).getY(), z - radius), Blocks.STONEBRICK.getDefaultState()); 
                }
                
                for(int i = z - radius; i < z + radius; i++)
                {
                	worldIn.setBlockState(new BlockPos(x + radius, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x + radius, 80, i)).getY(), i), Blocks.STONEBRICK.getDefaultState()); 
                	worldIn.setBlockState(new BlockPos(x - radius, worldIn.getTopSolidOrLiquidBlock(new BlockPos(x - radius, 80, i)).getY(), i), Blocks.STONEBRICK.getDefaultState()); 
                }*/
            }

            /**
             * currently only defined for Villages, returns true if Village has more than 2 non-road components
             */
            public boolean isSizeableStructure()
            {
                return this.hasMoreThanTwoComponents;
            }

            public void writeToNBT(NBTTagCompound tagCompound)
            {
                super.writeToNBT(tagCompound);
                tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
            }

            public void readFromNBT(NBTTagCompound tagCompound)
            {
                super.readFromNBT(tagCompound);
                this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
            }
        }
}