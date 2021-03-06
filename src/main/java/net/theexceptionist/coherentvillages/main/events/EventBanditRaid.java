package net.theexceptionist.coherentvillages.main.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.events.EventModTick;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;

public class EventBanditRaid extends Event {

	public EventBanditRaid(String name, boolean startInDay, int range, int count, int change) {
		super(name, startInDay, range, count, change);
		this.style = new Style();
		this.style.setColor(TextFormatting.GREEN);
		this.eventMessage = "Bandits arrive at &f....";
	}

	@Override
	public boolean execute(World world, boolean day, BlockPos spawn, AttributeRace race, AttributeFaction faction) {
		boolean executed = true;
		int roll = world.rand.nextInt(100);
		if(roll > this.chance) return (executed = false);
		
		int x = spawn.getX() + (world.rand.nextInt(100) < 50 ? range : -range);
		int z = spawn.getZ() + (world.rand.nextInt(100) < 50 ? range : -range);
		int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();
		int count = world.rand.nextInt(this.count) + 1;
		int raceID = race == AttributeRace.germans ? AttributeRace.RACE_TYPE_MONGOL : race.getID();
		
		if(day == startInDay)
		{
			
			
			for(int i = 0; i < count; i++)
			{
				EntityHumanVillager bandit = new EntityHumanVillager(world, raceID, AttributeRace.getFromIDRace(raceID).getRandomBandit(world), EntityHumanVillager.getRandomGender(world), false);                            
            	bandit.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
            	world.spawnEntity(bandit);
            	//System.out.println("Spawning - X: "+x+" Y: "+y+" Z: "+z);
			}
			
			this.setEventMessage(faction);
		}
		else
		{
			executed = false;
		}
		
		return executed;
	}

	@Override
	public void reset(boolean day) {
		// TODO Auto-generated method stub
		
	}
	

}
