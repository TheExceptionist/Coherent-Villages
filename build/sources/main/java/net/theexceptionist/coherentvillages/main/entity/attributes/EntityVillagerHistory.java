package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;

public class EntityVillagerHistory {
	public static ArrayList<HistoryEntry> promotions = new ArrayList<HistoryEntry>();
	public static ArrayList<HistoryEntry> kills = new ArrayList<HistoryEntry>();
	public static ArrayList<HistoryEntry> attackers = new ArrayList<HistoryEntry>();
	public static ArrayList<HistoryEntry> attackings = new ArrayList<HistoryEntry>();

	private static final int PROMOTION_ID = 0;
	private static final int KILLS_ID = 1;
	private static final int ATTACKERS_ID = 2;
	private static final int ATTACKINGS_ID = 3;

	
	public class HistoryEntry
	{
		protected int type;
		protected String name;
		
		public HistoryEntry(String name, int type)
		{
			this.name = name;
			this.type = type;
			
			switch(type)
			{
				case PROMOTION_ID:
				{
					promotions.add(this);
				}
				break;
				case KILLS_ID:
				{
					kills.add(this);
				}
				break;
				case ATTACKERS_ID:
				{
					attackers.add(this);
				}
				break;
				case ATTACKINGS_ID:
				{
					attackings.add(this);
				}
				break;
			}
		}
	}
}
