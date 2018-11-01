package net.theexceptionist.coherentvillages.main.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.events.EventModTick;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeFaction;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;

public abstract class Event {
	public static final EventBanditRaid small_raid = new EventBanditRaid("Bandit Raid Small", false, 25, 5, Main.SMALL_RAID_RATE);
	public static final EventBanditRaid medium_raid = new EventBanditRaid("Bandit Raid Medium", false, 30, 10, Main.MEDIUM_RAID_RATE);
	public static final EventBanditRaid large_raid = new EventBanditRaid("Bandit Raid Large", false, 35, 1, Main.LARGE_RAID_RATE);
	
	public static final EventImmigration small_immgrate = new EventImmigration("Immigration Small", true, 25, 5, Main.SMALL_IMMIGRATE_RATE, 20);
	public static final EventImmigration medium_immgrate = new EventImmigration("Immigration Medium", true, 30, 10, Main.MEDIUM_IMMIGRATE_RATE, 30);
	public static final EventImmigration large_immgrate = new EventImmigration("Immigration Large", true, 35, 1, Main.LARGE_IMMIGRATE_RATE, 40);
	
	public static final EventSkirmish small_skirmish = new EventSkirmish("Skirmish Small", true, 100, 5, Main.SMALL_SKIRMISH_RATE);
	public static final EventSkirmish medium_skirmish = new EventSkirmish("Skirmish Medium", true, 125, 10, Main.MEDIUM_SKIRMISH_RATE);
	public static final EventSkirmish large_skirmish = new EventSkirmish("Skirmish Large", true, 150, 1, Main.LARGE_SKIRMISH_RATE);
	
	
	protected String name;
	protected AttributeRace race;
	protected Style style;
	protected String eventMessage;
	protected boolean startInDay;
	protected int range, count, chance;
	
	public Event(String name, boolean startInDay, int range, int count, int chance)
	{
		this.name = name;
		this.startInDay = startInDay;
		this.range = range;
		this.count = count;
		this.chance = chance;
	}
	
	public abstract boolean execute(World world, boolean day, BlockPos spawn, AttributeRace race, AttributeFaction faction);
	public abstract void reset(boolean day);
	
	public void setEventMessage(AttributeFaction faction)
	{
		this.eventMessage = this.eventMessage.replaceAll("&f", faction.getTitleName());
		TextComponentString message = new TextComponentString(eventMessage);
		message.setStyle(style);
		if(Main.sendMessage) EventModTick.addMessage(message);
	}
}
