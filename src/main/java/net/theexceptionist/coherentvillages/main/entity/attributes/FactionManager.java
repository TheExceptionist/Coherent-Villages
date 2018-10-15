package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class FactionManager {
	public ArrayList<AttributeFaction> factions;
	public HashMap<Integer, EntityHumanVillager> rulers;
	
	public FactionManager()
	{
		factions = new ArrayList<AttributeFaction>();
		rulers = new HashMap<Integer, EntityHumanVillager> ();
	}
	
	public void addFaction(AttributeFaction faction)
	{
		factions.add(faction);
	}
	
	public void removeFaction(AttributeFaction faction)
	{
		factions.remove(faction);
	}
	
	public void addRuler(int ID, EntityHumanVillager ruler)
	{
		rulers.put(ID, ruler);
	}
	
	public void removeRuler(EntityHumanVillager ruler)
	{
		rulers.remove(ruler);
	}
	
	public AttributeFaction getNearestVillage(BlockPos doorBlock, int radius)
    {
        AttributeFaction village = null;
        double d0 = 3.4028234663852886E38D;
        ArrayList<AttributeFaction> factions2 = this.factions;

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

}