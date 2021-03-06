package net.theexceptionist.coherentvillages.main.entity.attributes;

import java.util.ArrayList;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.events.Event;

public class AttributeFaction {
	//Warrior Race - High Health, Lots of Damage, Nords
		public static final int FACTION_TYPE_NORD = 0;
		//Knight Race - High Armor, has horses, Latins
		public static final int FACTION_TYPE_LATIN = 1;
		//Archer Race - Good Detection and Speed, Slavs
		public static final int FACTION_TYPE_SLAV = 2; 
		//Mage Race - Celts
		public static final int FACTION_TYPE_GERMAN = 3;
		//Alchemy Race - Arabs
		public static final int FACTION_TYPE_ARAB = 4;
		//All-around
		public static final int FACTION_TYPE_GREEK = 5;
		public static final int FACTION_TYPE_BRITON = 6;
		public static final int FACTION_TYPE_FRANK = 7;
		public static final int FACTION_TYPE_MONGOL = 8;
		public static final int FACTION_TYPE_VAMPIRE = 9;
		
		public static final int FACTION_TYPE_NORD_BANDIT = 10;
		//Knight Race - High Armor, has horses, Latins
		public static final int FACTION_TYPE_LATIN_BANDIT = 11;
		//Archer Race - Good Detection and Speed, Slavs
		public static final int FACTION_TYPE_SLAV_BANDIT = 12; 
		//Mage Race - Celts
		public static final int FACTION_TYPE_GERMAN_BANDIT = 13;
		//Alchemy Race - Arabs
		public static final int FACTION_TYPE_ARAB_BANDIT = 14;
		//All-around
		public static final int FACTION_TYPE_GREEK_BANDIT = 15;
		public static final int FACTION_TYPE_BRITON_BANDIT = 16;
		public static final int FACTION_TYPE_FRANK_BANDIT = 17;
		
	private static int[] n_hostiles = 
		{
			-1,
			-1,
			-1,
			-1,
			-1,
			-1,
			-1,
			-1,
			FACTION_TYPE_MONGOL,
			FACTION_TYPE_VAMPIRE,
			FACTION_TYPE_NORD_BANDIT,
			FACTION_TYPE_LATIN_BANDIT,
			FACTION_TYPE_SLAV_BANDIT,
			FACTION_TYPE_GERMAN_BANDIT,
			FACTION_TYPE_ARAB_BANDIT,
			FACTION_TYPE_GREEK_BANDIT,
			FACTION_TYPE_BRITON_BANDIT,
			FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] l_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] s_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] g_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] a_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] o_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] b_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] f_hostiles = 
		{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] m_hostiles = 
		{
			FACTION_TYPE_NORD,
			FACTION_TYPE_LATIN,
			FACTION_TYPE_SLAV,
			FACTION_TYPE_GERMAN,
			FACTION_TYPE_ARAB,
			FACTION_TYPE_GREEK,
			FACTION_TYPE_BRITON,
			FACTION_TYPE_FRANK,
			-1,
			FACTION_TYPE_VAMPIRE,
			FACTION_TYPE_NORD_BANDIT,
			FACTION_TYPE_LATIN_BANDIT,
			FACTION_TYPE_SLAV_BANDIT,
			FACTION_TYPE_GERMAN_BANDIT,
			FACTION_TYPE_ARAB_BANDIT,
			FACTION_TYPE_GREEK_BANDIT,
			FACTION_TYPE_BRITON_BANDIT,
			FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] v_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				-1,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] n_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				-1,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] l_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				-1,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] s_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				-1,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	private static int[] g_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				-1,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] o_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				-1,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] b_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				-1,
				FACTION_TYPE_FRANK_BANDIT
		};
	
	private static int[] f_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				FACTION_TYPE_ARAB_BANDIT,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				-1
		};
	
	private static int[] a_b_hostiles = 
		{
				FACTION_TYPE_NORD,
				FACTION_TYPE_LATIN,
				FACTION_TYPE_SLAV,
				FACTION_TYPE_GERMAN,
				FACTION_TYPE_ARAB,
				FACTION_TYPE_GREEK,
				FACTION_TYPE_BRITON,
				FACTION_TYPE_FRANK,
				FACTION_TYPE_MONGOL,
				FACTION_TYPE_VAMPIRE,
				FACTION_TYPE_NORD_BANDIT,
				FACTION_TYPE_LATIN_BANDIT,
				FACTION_TYPE_SLAV_BANDIT,
				FACTION_TYPE_GERMAN_BANDIT,
				-1,
				FACTION_TYPE_GREEK_BANDIT,
				FACTION_TYPE_BRITON_BANDIT,
				FACTION_TYPE_FRANK_BANDIT
		};
		
	private static final String[] n_titles = 
		{
				"%n's Kingdom of the %rs",
				"%n's %r Village",
				"%n's %r Jarledom",
		};
	
	private static final String[] n_b_titles = 
		{
				"%n's Pillagers of the %rs",
				"%n's %r Viking Den",
				"The %n's %r Sea Raiders",
		};
	
	private static final String[] l_titles = 
		{
				"%n's %r Empire",
				"%n's %r Town",
				"%n's %r Village",
		};
	
	private static final String[] l_b_titles = 
		{
				"%n's Rebels of the %rs",
				"%n's %r Resistance",
				"The %n's %r Renegades",
		};
	
	private static final String[] g_titles = 
		{
				"%n's Kingdom of the %rs",
				"%n's %r Village",
				"%n's %r Fiefdom",
		};
	
	private static final String[] g_b_titles = 
		{
				"%n's Raiders of the %rs",
				"%n's %r Horde",
				"The %n's %r Khanate",
		};
	
	private static final String[] s_titles = 
		{
				"%n's Kingdom of the %rs",
				"%n's %r Village",
				"%n's %r States",
		};
	
	private static final String[] s_b_titles = 
		{
				"%n's Raiders of the %rs",
				"%n's %r Horde",
				"The %n's %r Khanate",
		};
	
	private static final String[] a_titles = 
		{
				"%n's Sultanate of the %rs",
				"%n's %r Village",
				"%n's %r Caliphate",
		};
	
	private static final String[] a_b_titles = 
		{
				"%n's Pillagers of the %rs",
				"%n's %r Viking Den",
				"The %n's %r Sea Raiders",
		};
	
	private int radius = 50;
	private BlockPos center = null;
	private World world;
	
	protected String name;
	protected String titleName;
	protected AttributeRace race;
	protected boolean isBandit = false;
	
	public static int END_ID = 0;
	protected final int ID;

	private boolean messageSend = false;
	private EntityHumanVillager ruler;

	private boolean wasUpdated = false;
	private ArrayList<Event> events;

	private int size;
	//private ArrayList<EntityHumanVillager> members;
	
	public AttributeFaction(World world, BlockPos center, AttributeRace race, String name, EntityHumanVillager entityHumanVillager, String factionName)
	{
		this.center = center;
		this.world = world;
		this.titleName = name;
		this.race = race;
		this.ID = END_ID++;
		this.ruler = entityHumanVillager;
		this.events = new ArrayList<Event>();
		this.size = 1;
		
		if(!world.isRemote)
		{
			if(factionName.contains("None"))
			{
				this.setName();
			}
			else
			{
				this.name = factionName;
			}
			
			FactionManager.addFaction(this);
		}
		
		this.getEvents();
	//this.members = new ArrayList<EntityHumanVillager>();
	}
	
	private void getEvents() {
		events.add(Event.small_raid);
		events.add(Event.medium_raid);
		events.add(Event.large_raid);
		
		events.add(Event.small_immgrate);
		events.add(Event.medium_immgrate);
		events.add(Event.large_immgrate);
		
		events.add(Event.small_skirmish);
		events.add(Event.medium_skirmish);
		events.add(Event.large_skirmish);
		
		switch(race.getID())
		{
			
		}
	}
	
	public static int[] getHostileID(AttributeRace race, boolean bandit)
	{
		switch(race.getType())
		{
			case AttributeRace.RACE_TYPE_NORD:
			{
				return bandit ? n_b_hostiles : n_hostiles;
				//if(!isBandit) this.name = n_titles[world.rand.nextInt(n_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				//else this.name = n_b_titles[world.rand.nextInt(n_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_SLAV:
			{
				return bandit ? s_b_hostiles : s_hostiles;
			}
			case AttributeRace.RACE_TYPE_LATIN:
			{
				return bandit ? l_b_hostiles : l_hostiles;
			}
			case AttributeRace.RACE_TYPE_GERMAN:
			{
				return bandit ? g_b_hostiles : g_hostiles;
			}
			case AttributeRace.RACE_TYPE_ARAB:
			{
				return bandit ? a_b_hostiles : a_hostiles;
			}
			case AttributeRace.RACE_TYPE_GREEK:
			{
				return bandit ? g_b_hostiles : g_hostiles;
//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_FRANK:
			{
				return bandit ? f_b_hostiles : f_hostiles;
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_BRITON:
			{
				return bandit ? b_b_hostiles : b_hostiles;
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_MONGOL:
			{
				return bandit ? m_hostiles : m_hostiles;
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_VAMPIRE:
			{
				return bandit ? v_hostiles : v_hostiles;
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			default:
				return null;
		}
	}
	
	
	public int getFactionID()
	{
		/*switch(race.getType())
		{
			case AttributeRace.RACE_TYPE_NORD:
			{
				
				//if(!isBandit) this.name = n_titles[world.rand.nextInt(n_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				//else this.name = n_b_titles[world.rand.nextInt(n_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_SLAV:
			{
				if(!isBandit) this.name = s_titles[world.rand.nextInt(s_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = s_b_titles[world.rand.nextInt(s_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_LATIN:
			{
				if(!isBandit) this.name = l_titles[world.rand.nextInt(l_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = l_b_titles[world.rand.nextInt(l_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_GERMAN:
			{
				if(!isBandit) this.name = g_titles[world.rand.nextInt(g_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = g_b_titles[world.rand.nextInt(g_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_ARAB:
			{
				if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_GREEK:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_FRANK:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_BRITON:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
		}*/
		return race.getType();
	}

	public EntityHumanVillager getRuler() {
		return ruler;
	}

	public int getID() {
		return ID;
	}

	public String getTitleName() {
		return this.name != null ? this.name : "{NO NAME}";
	}

	public void setName()
	{		
		switch(race.getType())
		{
			case AttributeRace.RACE_TYPE_NORD:
			{
				if(!isBandit) this.name = n_titles[world.rand.nextInt(n_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = n_b_titles[world.rand.nextInt(n_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_SLAV:
			{
				if(!isBandit) this.name = s_titles[world.rand.nextInt(s_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = s_b_titles[world.rand.nextInt(s_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_LATIN:
			{
				if(!isBandit) this.name = l_titles[world.rand.nextInt(l_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = l_b_titles[world.rand.nextInt(l_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_GERMAN:
			{
				if(!isBandit) this.name = g_titles[world.rand.nextInt(g_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = g_b_titles[world.rand.nextInt(g_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
			case AttributeRace.RACE_TYPE_ARAB:
			{
				if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
				else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_GREEK:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_FRANK:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			case AttributeRace.RACE_TYPE_BRITON:
			{
			//	if(!isBandit) this.name = a_titles[world.rand.nextInt(a_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			//	else this.name = a_b_titles[world.rand.nextInt(a_b_titles.length)].replace("%n", titleName).replace("%r", race.getName());
			}
			break;
		}
	}
	
	public Event getRandomEvent()
	{
		if(events.size() < 1) return null;
		return events.get(world.rand.nextInt(events.size()));
	}
	
	public void setBandit(boolean bandit)
	{
		isBandit = bandit;
		this.setName();
	}
	
	public boolean getBandit()
	{
		return isBandit;// = bandit;
	}
	
	public Vec3i getCenter() {
		// TODO Auto-generated method stub
		return center;
	}

	//return a constant from now
	public int getVillageRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

	public boolean messageSent() {
		// TODO Auto-generated method stub
		return messageSend ;
	}
	
	public void setMessageSent(boolean sent) {
		// TODO Auto-generated method stub
		messageSend = sent;
	}

	public void addRuler(EntityHumanVillager ruler) {
		this.ruler = ruler;
	}

	public void update(boolean day, BlockPos spawn) {
		//System.out.println("Spawn: "+spawn.getX()+" - "+spawn.getZ());
		getRandomEvent().execute(world, day, spawn, race, this);
	}

	public boolean doneUpdate() {
		// TODO Auto-generated method stub
		return wasUpdated;
	}

	public void setUpdate(boolean b) {
		// TODO Auto-generated method stub
		wasUpdated = b;
	}

	public void addVillager() {
		// TODO Auto-generated method stub
		size++;
	}

	public int getNumVillagers() {
		// TODO Auto-generated method stub
		return size;
	}

	/*public void addMember(EntityHumanVillager entityHumanVillager) {
		members.add(entityHumanVillager);
	}*/

}
