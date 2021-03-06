package net.theexceptionist.coherentvillages.main.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;

public class EventSkirmish extends Event {

	public EventSkirmish(String name, boolean startInDay, int range, int count, int chance) {
		super(name, startInDay, range, count, chance);
		this.style = new Style();
		this.style.setColor(TextFormatting.GREEN);
		this.eventMessage = "You hear fighting off in the distance near &f....";
	}

	@Override
	public boolean execute(World world, boolean day, BlockPos spawn, AttributeRace race, AttributeFaction faction) {
		if(race == null || faction == null) return false;
		
		boolean success = true;
		int roll = world.rand.nextInt(100);
		if(roll > this.chance) return (success = false);
		
		int x = spawn.getX() + range * (world.rand.nextInt(100) < 50 ? -1 : 1);
		int z = spawn.getX() + range * (world.rand.nextInt(100) < 50 ? -1 : 1);
		int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();
		int raceID = race.getID();
		
		int z2 = z + 10;
		int y2 = world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z2)).getY();
		
		
		for(int i = 0; i < count; i++)
		{
			AttributeVocation soldierClass = race.getRandomSoldier(world);
			AttributeVocation banditClass = race.getRandomBandit(world);
			
			if(soldierClass == null || banditClass == null) continue;
			
			EntityHumanVillager soldier = new EntityHumanVillager(world, raceID, soldierClass, EntityHumanVillager.getRandomGender(world), false);                            
        	soldier.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
        	world.spawnEntity(soldier);
        	
        	EntityHumanVillager bandit = new EntityHumanVillager(world, raceID, banditClass, EntityHumanVillager.getRandomGender(world), false);                            
        	bandit.setLocationAndAngles((double)x + 0.5D, (double)y2, (double)z2 + 0.5D, 0.0F, 0.0F);
        	world.spawnEntity(bandit);
		}
		this.setEventMessage(faction);
		
		//System.out.println("^&%$#@#$%^&$%#$@#!#$%$#@Q Starting at: "+x+" "+y+" "+z);
		
		return success;
	}

	@Override
	public void reset(boolean day) {
		// TODO Auto-generated method stub
		
	}

}
