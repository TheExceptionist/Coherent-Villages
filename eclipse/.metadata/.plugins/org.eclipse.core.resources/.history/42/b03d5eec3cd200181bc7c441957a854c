package net.theexceptionist.coherentvillages.worldgen.villages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.NameGenerator;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.FactionManager;

public class WorldGenVillage extends MapGenVillage
{
    /** A list of all the biomes villages can spawn in. */
    public static ArrayList<Biome> NORD_VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    public static ArrayList<Biome> SLAV_VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    public static ArrayList<Biome> LATIN_VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    public static ArrayList<Biome> GERMAN_VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    public static ArrayList<Biome> ARAB_VILLAGE_SPAWN_BIOMES = new ArrayList<Biome>();
    
    public static final int NORD_ID = 0;
    public static final int LATIN_ID = 1;
    public static final int GERMAN_ID = 2;
    public static final int SLAV_ID = 3;
    public static final int ARAB_ID = 4;
    
    public static FactionManager nordManager;
    public static FactionManager latinManager;
    public static FactionManager slavManager;
    public static FactionManager germanManager;
    public static FactionManager arabManager;
    
    /** None */
    private int size;
    private int distance;
    private final int minTownSeparation;
	private int type;
 
    public WorldGenVillage()
    {
        this.distance = Main.max_distance;
        this.minTownSeparation = Main.min_distance;
        this.size = Main.village_size;
        
        this.nordManager = new FactionManager();
        this.slavManager = new FactionManager();
        this.latinManager = new FactionManager();
        this.germanManager = new FactionManager();
        this.arabManager = new FactionManager();
    }

    public WorldGenVillage(Map<String, String> map)
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
        
      //  System.out.println("Chunk X:"+chunkX+"Chunk Z: "+chunkZ+" Distance: "+this.distance);

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        
     //   System.out.println("X1: "+k+" Z1: "+l);
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        
      //System.out.println("X2: "+k+" Z2: "+l);
        
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);
        
       // System.out.println("X3: "+k+" Z3: "+l);

        if (i == k && j == l)
        {
            boolean flag_n = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, NORD_VILLAGE_SPAWN_BIOMES);
            boolean flag_l = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, LATIN_VILLAGE_SPAWN_BIOMES);
            boolean flag_g = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, GERMAN_VILLAGE_SPAWN_BIOMES);
            boolean flag_s = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, SLAV_VILLAGE_SPAWN_BIOMES);
            boolean flag_a = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, ARAB_VILLAGE_SPAWN_BIOMES);

            //System.out.println("Nord: "+flag_n+" Latin: "+flag_l+" German: "+flag_g+" Slav: "+flag_s+" Arab: "+flag_a);
            
            if (flag_n)
            {
            	this.type = NORD_ID; 
                return true;
            }
            if (flag_l)
            {
            	this.type = LATIN_ID; 
                return true;
            }
            if (flag_g)
            {
            	this.type = GERMAN_ID; 
                return true;
            }
            if (flag_s)
            {
            	this.type = SLAV_ID; 
                return true;
            }
            if (flag_a)
            {
            	this.type = ARAB_ID; 
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
    	//System.out.println("Starting: "+chunkX * 16+" | "+chunkZ * 16);
    	
    	
    	switch(this.type)
    	{
	    	case NORD_ID:
	    	{
	    		//this.nordManager.addFaction(faction);
	    		return new WorldGenVillage.NordStart(this.world, this.rand, chunkX, chunkZ, this.size);
	    	}
	    	case LATIN_ID:
	    	{
	    		//this.nordManager.addFaction(faction);
	    		return new WorldGenVillage.LatinStart(this.world, this.rand, chunkX, chunkZ, this.size);
	    	}
	    	case GERMAN_ID:
	    	{
	    		//this.nordManager.addFaction(faction);
	    		return new WorldGenVillage.GermanStart(this.world, this.rand, chunkX, chunkZ, this.size);
	    	}
	    	case SLAV_ID:
	    	{
	    		//this.nordManager.addFaction(faction);
	    		return new WorldGenVillage.SlavStart(this.world, this.rand, chunkX, chunkZ, this.size);
	    	}
	    	case ARAB_ID:
	    	{
	    		//this.nordManager.addFaction(faction);
	    		return new WorldGenVillage.ArabStart(this.world, this.rand, chunkX, chunkZ, this.size);
	    	}
	    	default:
	    		return new WorldGenVillage.Start(this.world, this.rand, chunkX, chunkZ, this.size);
    	}
    }

    public static class NordStart extends StructureStart
        {
            /** well ... thats what it does */
            private boolean hasMoreThanTwoComponents;

            public NordStart()
            {
            }

            public NordStart(World worldIn, Random rand, int x, int z, int size)
            {
                super(x, z);
                //System.out.println("Generating: "+x+" "+z);
                List<NordStructurePieces.PieceWeight> list = NordStructurePieces.getStructureVillageWeightedPieceList(rand, size);
                NordStructurePieces.Start NordStructurePieces$start = new NordStructurePieces.Start(worldIn, x, z, worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
                this.components.add(NordStructurePieces$start);
                NordStructurePieces$start.buildComponent(NordStructurePieces$start, this.components, rand);
                List<StructureComponent> list1 = NordStructurePieces$start.pendingRoads;
                List<StructureComponent> list2 = NordStructurePieces$start.pendingHouses;
                //list2.add(new VillageComponentVillageWall(NordStructurePieces$start, rand, NordStructurePieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
                
                while (!list1.isEmpty() || !list2.isEmpty())
                {
                    if (list1.isEmpty())
                    {
                        int i = rand.nextInt(list2.size());
                        StructureComponent structurecomponent = list2.remove(i);
                        structurecomponent.buildComponent(NordStructurePieces$start, this.components, rand);
                    }
                    else
                    {
                        int j = rand.nextInt(list1.size());
                        StructureComponent structurecomponent2 = list1.remove(j);
                        structurecomponent2.buildComponent(NordStructurePieces$start, this.components, rand);
                    }
                }

                this.updateBoundingBox();
                int k = 0;

                for (StructureComponent structurecomponent1 : this.components)
                {
                    if (!(structurecomponent1 instanceof NordStructurePieces.Road))
                    {
                        ++k;
                    }
                }

                this.hasMoreThanTwoComponents = k > 2;
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
    public static class GermanStart extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public GermanStart()
        {
        }

        public GermanStart(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            //System.out.println("Generating: "+x+" "+z);
            List<GermanStructurePieces.PieceWeight> list = GermanStructurePieces.getStructureVillageWeightedPieceList(rand, size);
            GermanStructurePieces.Start GermanStructurePieces$start = new GermanStructurePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(GermanStructurePieces$start);
            GermanStructurePieces$start.buildComponent(GermanStructurePieces$start, this.components, rand);
            List<StructureComponent> list1 = GermanStructurePieces$start.pendingRoads;
            List<StructureComponent> list2 = GermanStructurePieces$start.pendingHouses;
            //list2.add(new VillageComponentVillageWall(NordStructurePieces$start, rand, NordStructurePieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
            
            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(GermanStructurePieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(GermanStructurePieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof GermanStructurePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
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
    public static class SlavStart extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public SlavStart()
        {
        }

        public SlavStart(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            //System.out.println("Generating: "+x+" "+z);
            List<SlavStructurePieces.PieceWeight> list = SlavStructurePieces.getStructureVillageWeightedPieceList(rand, size);
            SlavStructurePieces.Start SlavStructurePieces$start = new SlavStructurePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(SlavStructurePieces$start);
            SlavStructurePieces$start.buildComponent(SlavStructurePieces$start, this.components, rand);
            List<StructureComponent> list1 = SlavStructurePieces$start.pendingRoads;
            List<StructureComponent> list2 = SlavStructurePieces$start.pendingHouses;
            //list2.add(new VillageComponentVillageWall(NordStructurePieces$start, rand, NordStructurePieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
            
            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(SlavStructurePieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(SlavStructurePieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof SlavStructurePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
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
    public static class LatinStart extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public LatinStart()
        {
        }

        public LatinStart(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            //System.out.println("Generating: "+x+" "+z);
            List<LatinStructurePieces.PieceWeight> list = LatinStructurePieces.getStructureVillageWeightedPieceList(rand, size);
            LatinStructurePieces.Start LatinStructurePieces$start = new LatinStructurePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(LatinStructurePieces$start);
            LatinStructurePieces$start.buildComponent(LatinStructurePieces$start, this.components, rand);
            List<StructureComponent> list1 = LatinStructurePieces$start.pendingRoads;
            List<StructureComponent> list2 = LatinStructurePieces$start.pendingHouses;
            //list2.add(new VillageComponentVillageWall(NordStructurePieces$start, rand, NordStructurePieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
            
            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(LatinStructurePieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(LatinStructurePieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof LatinStructurePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
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
    public static class ArabStart extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public ArabStart()
        {
        }

        public ArabStart(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            //System.out.println("Generating: "+x+" "+z);
            List<ArabStructurePieces.PieceWeight> list = ArabStructurePieces.getStructureVillageWeightedPieceList(rand, size);
            ArabStructurePieces.Start ArabStructurePieces$start = new ArabStructurePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(ArabStructurePieces$start);
            ArabStructurePieces$start.buildComponent(ArabStructurePieces$start, this.components, rand);
            List<StructureComponent> list1 = ArabStructurePieces$start.pendingRoads;
            List<StructureComponent> list2 = ArabStructurePieces$start.pendingHouses;
            //list2.add(new VillageComponentVillageWall(ArabStructurePieces$start, rand, ArabStructurePieces$start.getComponentType(), EnumFacing.DOWN, new BlockPos(x, 80, z), list.size() * 10, worldIn));
            
            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(ArabStructurePieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(ArabStructurePieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof ArabStructurePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
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