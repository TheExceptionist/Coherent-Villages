package net.theexceptionist.coherentvillages.main.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;

public class EventMerchantSpawn extends Event{

	public EventMerchantSpawn(String name, boolean startInDay, int range, int count, int chance) {
		super(name, startInDay, range, count, chance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(World world, boolean day, BlockPos spawn, AttributeRace race, AttributeFaction faction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset(boolean day) {
		// TODO Auto-generated method stub
		
	}

}
