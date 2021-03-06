package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class FactionManager {
	private static ArrayList<AttributeFaction> factions;
	
	public static void addFaction(AttributeFaction faction)
	{
		//System.out.println("Faction: "+faction.getTitleName()+" ID: "+faction.getID()+" Ruler: "+faction.getRuler().getTitle());
		factions.add(faction);
	}
	
	public static void removeFaction(AttributeFaction faction)
	{
		factions.remove(faction);
	}

	public static AttributeFaction getNearestVillage(BlockPos doorBlock, int radius)
    {
        AttributeFaction village = null;
        double d0 = 3.4028234663852886E38D;
        ArrayList<AttributeFaction> factions2 = factions;

        for (AttributeFaction village1 : factions2)
        {
            double d1 = village1.getCenter().distanceSq(doorBlock);

            //System.out.println("distance: "+d1+" max distance: "+d0);
            
            if (d1 < d0)
            {
                float f = (float)(radius + village1.getVillageRadius());

               // System.out.println("radius: "+f+" radius true: "+(f*f));
                
                if (d1 <= (double)(f * f))
                {
                    village = village1;
                    d0 = d1;
                }
            }
        }

        return village;
    }

	public static void init() {
		// TODO Auto-generated method stub
		factions = new ArrayList<AttributeFaction>();
	}

}
