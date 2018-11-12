package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.PotionTypes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Spell {
	public static final SpellFireball fireball = new SpellFireball("Fireball", Spell.SPELL_TYPE_ATTACK, 1, 0, 5, 10,  false, false);
	public static final SpellFireball fireball_volley = new SpellFireball("Fireball Volley", Spell.SPELL_TYPE_ATTACK, 5, 0, 1, 10, true, false);
	public static final SpellFireball fireball_barrage = new SpellFireball("Fireball Barrage", Spell.SPELL_TYPE_ATTACK, 10, 0, 3, 10, true, false);

	public static final SpellFireball greater_fireball = new SpellFireball("Greater Fireball", Spell.SPELL_TYPE_ATTACK, 1, 0, 2, 10, false, true);
	public static final SpellFireball greater_fireball_volley = new SpellFireball("Greater Fireball Volley", Spell.SPELL_TYPE_ATTACK, 3, 0, 1, 20, true, true);
	public static final SpellFireball firestorm = new SpellFireball("Firestorm", Spell.SPELL_TYPE_ATTACK, 3, 1, 1, 40, true, true);
	
	public static final SpellMeteor meteorball = new SpellMeteor("Meteor", Spell.SPELL_TYPE_ATTACK, 1, 0, 5, 10,  false, false);
	public static final SpellMeteor meteorball_volley = new SpellMeteor("Meteor Volley", Spell.SPELL_TYPE_ATTACK, 5, 0, 1, 10, true, false);
	public static final SpellMeteor greater_meteorball = new SpellMeteor("Greater Meteor", Spell.SPELL_TYPE_ATTACK, 1, 0, 2, 10, false, true);
	public static final SpellMeteor greater_meteorball_volley = new SpellMeteor("Greater Meteor Volley", Spell.SPELL_TYPE_ATTACK, 3, 0, 1, 20, true, true);
	public static final SpellMeteor meteorstorm = new SpellMeteor("Firestorm", Spell.SPELL_TYPE_ATTACK, 3, 1, 1, 40, true, true);

	public static final SpellArrow arrow_greater_volley = new SpellArrow("Greater Arrow Volley", Spell.SPELL_TYPE_ATTACK, 10, 0, 5, 10,  false, false, SpellArrow.ATTACK_TYPE_NORMAL);
	public static final SpellArrow arrow_volley = new SpellArrow("Arrow Volley", Spell.SPELL_TYPE_ATTACK, 5, 0, 1, 10, true, false, SpellArrow.ATTACK_TYPE_NORMAL);
	public static final SpellArrow arrow_meteorball = new SpellArrow("Greater Arrow", Spell.SPELL_TYPE_ATTACK, 2, 0, 2, 10, false, true, SpellArrow.ATTACK_TYPE_DROP);
	public static final SpellArrow arrow_meteorball_volley = new SpellArrow("Greater Arrow Volley", Spell.SPELL_TYPE_ATTACK, 5, 0, 1, 20, true, true, SpellArrow.ATTACK_TYPE_DROP);
	public static final SpellArrow arrowstorm = new SpellArrow("Arrowstorm", Spell.SPELL_TYPE_ATTACK, 10, 1, 1, 40, true, true, SpellArrow.ATTACK_TYPE_CIRCLE);

	public static final SpellPlatform small_platform = new SpellPlatform("Small Platform", Spell.SPELL_TYPE_TRANSMUTE, 1, 3, 1200, 0);
	public static final SpellPlatform medium_platform = new SpellPlatform("Medium Platform", Spell.SPELL_TYPE_TRANSMUTE, 3, 6, 1600, 0);
	public static final SpellPlatform large_platform = new SpellPlatform("Large Platform", Spell.SPELL_TYPE_TRANSMUTE, 5, 8, 2000, 0);

	public static final SpellPlatform small_ditch = new SpellPlatform("Small Ditch", Spell.SPELL_TYPE_TRANSMUTE, 1, -4, 1200, 1);
	public static final SpellPlatform medium_ditch = new SpellPlatform("Medium Ditch", Spell.SPELL_TYPE_TRANSMUTE, 3, -6, 1600, 1);
	public static final SpellPlatform large_ditch = new SpellPlatform("Large Ditch", Spell.SPELL_TYPE_TRANSMUTE, 5, -8, 2000, 1);

	public static final SpellSummonAncient summon_ancient_warror = new SpellSummonAncient("Summon Ancient Warrior", Spell.SPELL_TYPE_SUMMON, 40, 3, 1000);
	
	public static final SpellSummonSkeleton summon_skeleton = new SpellSummonSkeleton("Summon Skeleton", Spell.SPELL_TYPE_SUMMON, 30, 5, 500);
	public static final SpellSummonSkeleton summon_skeleton_horde = new SpellSummonSkeleton("Summon Skeleton Horde", Spell.SPELL_TYPE_SUMMON, 20, 10, 200);

	public static final SpellSummon summon_bats = new SpellSummon("Summon Bats", Spell.SPELL_TYPE_SUMMON, 20, 3, 200);
	public static final SpellSummon summon_bat = new SpellSummon("Summon Bat", Spell.SPELL_TYPE_SUMMON, 20, 1, 200);

	public static final SpellThunder thunderbolt = new SpellThunder("Thunderbolt", Spell.SPELL_TYPE_ATTACK, 10);
	
	public static final SpellThunderStorm storm = new SpellThunderStorm("Storm", Spell.SPELL_TYPE_WEATHER);
	
	public static final SpellTransmuteFoot ice_foot = new SpellTransmuteFoot("Ice Foot", Spell.SPELL_TYPE_TRANSMUTE, Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellTransmuteFoot ice_foot_greater = new SpellTransmuteFoot("Ice Foot Greater", Spell.SPELL_TYPE_TRANSMUTE, Blocks.WATER, Blocks.ICE, 3, false);
	public static final SpellTransmuteFoot ice_aura = new SpellTransmuteFoot("Ice Aura", Spell.SPELL_TYPE_TRANSMUTE, Blocks.WATER, Blocks.ICE, 1, true);
	public static final SpellTransmuteFoot ice_aura_greater = new SpellTransmuteFoot("Ice Aura Greater", Spell.SPELL_TYPE_TRANSMUTE, Blocks.WATER, Blocks.ICE, 3, true);

	
	public static final SpellTransmuteFoot snow_foot = new SpellTransmuteFoot("Snow Foot", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.SNOW_LAYER, 1, false, true);
	public static final SpellTransmuteFoot snow_foot_greater = new SpellTransmuteFoot("Snow Foot Greater", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.SNOW_LAYER, 3, false, true);
	public static final SpellTransmuteFoot snow_aura = new SpellTransmuteFoot("Snow Aura", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.SNOW_LAYER, 1, true, true);
	public static final SpellTransmuteFoot snow_aura_greater = new SpellTransmuteFoot("Snow Aura Greater", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.SNOW_LAYER, 3, true, true);
	public static final SpellTransmuteFoot flame_aura = new SpellTransmuteFoot("Flame Aura", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.FIRE, 1, true, true);
	public static final SpellTransmuteFoot flame_aura_greater = new SpellTransmuteFoot("Flame Aura Greater", Spell.SPELL_TYPE_TRANSMUTE, Blocks.GRASS, Blocks.FIRE, 3, true, true);

	public static final SpellDamageReduction wood_skin = new SpellDamageReduction("Wood Skin", Spell.SPELL_TYPE_TRANSMUTE, 25);//SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellDamageReduction stone_skin = new SpellDamageReduction("Stone Skin", Spell.SPELL_TYPE_TRANSMUTE, 50);//SpellHarmingSkin.SPIKES, 4, null);
	public static final SpellDamageReduction iron_skin = new SpellDamageReduction("Iron Skin", Spell.SPELL_TYPE_TRANSMUTE, 75);//-1, -1, PotionTypes.LONG_POISON);
	
	public static final SpellHarmingSkin flaming_skin = new SpellHarmingSkin("Flaming Skin", Spell.SPELL_TYPE_TRANSMUTE, SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellHarmingSkin spike_skin = new SpellHarmingSkin("Spike Skin", Spell.SPELL_TYPE_TRANSMUTE, SpellHarmingSkin.SPIKES, 4, null);
	public static final SpellHarmingSkin poison_skin = new SpellHarmingSkin("Poison Skin", Spell.SPELL_TYPE_TRANSMUTE, -1, -1, PotionTypes.LONG_POISON);

	public static final SpellExtendLife life = new SpellExtendLife("Life", Spell.SPELL_TYPE_TRANSMUTE, 4, 2400, 2400);//SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellExtendLife greater_life = new SpellExtendLife("Greater Life", Spell.SPELL_TYPE_TRANSMUTE, 8, 2400, 2400);//SpellHarmingSkin.SPIKES, 4, null);
	public static final SpellExtendLife superior_life = new SpellExtendLife("Superior Life", Spell.SPELL_TYPE_TRANSMUTE, 12, 2400, 2400);//-1, -1, PotionTypes.LONG_POISON);
	
	public static final SpellBanishTouch banish_aura = new SpellBanishTouch("Banish Slin", Spell.SPELL_TYPE_TRANSMUTE, 3);//, -1, -1, PotionTypes.LONG_POISON);
	public static final SpellTransformAura raise_zombies = new SpellTransformAura("Zombie Skin", Spell.SPELL_TYPE_TRANSMUTE, 3, 0.10f, SpellTransformAura.ZOMBIE);//, -1, -1, PotionTypes.LONG_POISON);

	public static final SpellDrainAura drain_life = new SpellDrainAura("Drain Aura", Spell.SPELL_TYPE_TRANSMUTE, 5, 1);
	
	public static final SpellIronFoot iron_foot = new SpellIronFoot("Iron Foot", Spell.SPELL_TYPE_TRANSMUTE);
	
	public static final SpellEncase web_encase = new SpellEncase("Web Encase", Spell.SPELL_TYPE_ATTACK, 10, 1000, 1, Blocks.WEB); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellEncase ice_tomb = new SpellEncase("Ice Tomb", Spell.SPELL_TYPE_ATTACK, 10, 1000, 2, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellEncase glass_tomb = new SpellEncase("Glass Tomb", Spell.SPELL_TYPE_ATTACK, 10, 1000, 2, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellEncase stone_tomb = new SpellEncase("Stone Tomb", Spell.SPELL_TYPE_ATTACK, 10, 1000, 2, Blocks.STONE);//SpellHarmingSkin.SPIKES, 4, null);
	public static final SpellEncase sand_tomb = new SpellEncase("Sand Tomb", Spell.SPELL_TYPE_ATTACK, 10, 1000, 2, Blocks.SAND);//PotionTypes.LONG_POISON);

	public static final SpellEncase lava_spirt = new SpellEncase("Lava Spirt", Spell.SPELL_TYPE_ATTACK, 10, 1000, 1, Blocks.LAVA);//PotionTypes.LONG_POISON);
	public static final SpellEncase water_spirt = new SpellEncase("Water Spirt", Spell.SPELL_TYPE_ATTACK, 10, 1000, 1, Blocks.WATER);//PotionTypes.LONG_POISON);

	public static final SpellInflict harm = new SpellInflict("Harm", Spell.SPELL_TYPE_ATTACK, 2, PotionTypes.HARMING);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellInflict poison = new SpellInflict("Poison", Spell.SPELL_TYPE_ATTACK, 1, PotionTypes.POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellInflict greater_harm = new SpellInflict("Greater Harm", Spell.SPELL_TYPE_ATTACK, 5, PotionTypes.STRONG_HARMING);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellInflict greater_poison = new SpellInflict("Greater Poison", Spell.SPELL_TYPE_ATTACK, 3, PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);

	public static final SpellAfflict regeneration = new SpellAfflict("Regeneration", Spell.SPELL_TYPE_TRANSMUTE, 10, PotionTypes.REGENERATION, 1);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellAfflict fire_resistance = new SpellAfflict("Fire Resistance", Spell.SPELL_TYPE_TRANSMUTE, 10, PotionTypes.FIRE_RESISTANCE, 2);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellAfflict strong_regeneration = new SpellAfflict("Strong Night Regeneration", Spell.SPELL_TYPE_TRANSMUTE, 10, PotionTypes.STRONG_REGENERATION, 0);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);

	public static final SpellArrowProof arrow_proof = new SpellArrowProof("Arrow Proof", Spell.SPELL_TYPE_TRANSMUTE);//, 10, PotionTypes.STRONG_REGENERATION);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellTeleport teleport = new SpellTeleport("Teleport", Spell.SPELL_TYPE_TRANSMUTE, 40);//, 10, PotionTypes.STRONG_REGENERATION);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);

	public static final SpellLaser curse = new SpellLaser("Curse", Spell.SPELL_TYPE_ATTACK, 10, 5, -1, 1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellLaser implode = new SpellLaser("Implode", Spell.SPELL_TYPE_ATTACK, 10, 5, 0, 1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellLaser curse_greater = new SpellLaser("Greater Curse", Spell.SPELL_TYPE_ATTACK, 10, 5, -1, 3);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellLaser implode_greater = new SpellLaser("Greater Implode", Spell.SPELL_TYPE_ATTACK, 10, 5, 0, 3);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	
	public static final SpellThrall thrall_lesser = new SpellThrall("Lesser Thrall", Spell.SPELL_TYPE_TRANSMUTE, 800, 2400);//, 0);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellThrall thrall = new SpellThrall("Lesser Thrall", Spell.SPELL_TYPE_TRANSMUTE, 1200, 2400);//, 0);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellThrall thrall_greater = new SpellThrall("Lesser Thrall", Spell.SPELL_TYPE_TRANSMUTE, 1600, 2400);//, 0);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellThrall servant = new SpellThrall("Lesser Thrall", Spell.SPELL_TYPE_TRANSMUTE, 2000, 2400);//, 0);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);

	public static final SpellDrop drop = new SpellDrop("Drop", Spell.SPELL_TYPE_ATTACK, 10, 10);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellDrop long_drop = new SpellDrop("Long Drop", Spell.SPELL_TYPE_ATTACK, 10, 20);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellDrop skydrop = new SpellDrop("Sky Drop", Spell.SPELL_TYPE_ATTACK, 10, 30);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);

	public static final SpellDrop bury = new SpellDrop("Bury", Spell.SPELL_TYPE_ATTACK, 10, -10);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellDrop greater_bury = new SpellDrop("Greater Bury", Spell.SPELL_TYPE_ATTACK, 10, -20);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final SpellDrop grave = new SpellDrop("Grave", Spell.SPELL_TYPE_ATTACK, 10, -30);//, -1);//PotionTypes.LONG_POISON);//1, Blocks.GLASS); //SpellHarmingSkin.FIRE, 6, null);//Blocks.WATER, Blocks.ICE, 1, false);
	public static final Spell.NullSpell NULL_SPELL = new Spell.NullSpell("NULL SPELL", -1);
	
	static class NullSpell extends Spell
	{
		public NullSpell(String name, int type) {
			super(name, type);
			
		}

		@Override
		public void execute(EntityLivingBase caster) {
			// TODO Auto-generated method stub
			
		}
		
	}
	//Active
	public static final int SPELL_TYPE_ATTACK = 0;
	public static final int SPELL_TYPE_SUMMON = 1;
	
	//Passive
	public static final int SPELL_TYPE_WEATHER = 2;
	public static final int SPELL_TYPE_TRANSMUTE = 3;
	
	protected String name;
	protected int type;
	protected boolean isActive = false;
	protected boolean shouldReset = false;
	
	public Spell(String name, int type)
	{
		this.name = name;
		this.type = type;

		if(type == SPELL_TYPE_ATTACK || type == SPELL_TYPE_SUMMON) isActive = true;
	}	
	
	
	
	public boolean isShouldReset() {
		return shouldReset;
	}



	public void setShouldReset(boolean shouldReset) {
		this.shouldReset = shouldReset;
	}



	public abstract void execute(EntityLivingBase caster);
	
	public void reset(EntityLivingBase caster)
	{
		
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	protected void spawnExplosionParticle(World world, EntityLivingBase caster, double x, double y, double z) 
	{
        if (world.isRemote)
        {
            for (int i = 0; i < 20; ++i)
            {
                double d0 = world.rand.nextGaussian() * 0.02D;
                double d1 = world.rand.nextGaussian() * 0.02D;
                double d2 = world.rand.nextGaussian() * 0.02D;
                double d3 = 10.0D;
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, x + (double)(world.rand.nextFloat() * caster.width * 2.0F) - (double)caster.width - d0 * 10.0D, y + (double)(world.rand.nextFloat() * caster.height) - d1 * 10.0D, z + (double)(world.rand.nextFloat() * caster.width * 2.0F) - (double)caster.width - d2 * 10.0D, d0, d1, d2);
            }
        }
        else
        {
            world.setEntityState(caster, (byte)20);
        }
    }
	
	protected void spawnEndParticle(World world, EntityLivingBase caster, double x, double y, double z) 
	{
        if (world.isRemote)
        {
            for (int i = 0; i < 20; ++i)
            {
                double d0 = world.rand.nextGaussian() * 0.02D;
                double d1 = world.rand.nextGaussian() * 0.02D;
                double d2 = world.rand.nextGaussian() * 0.02D;
                double d3 = 10.0D;
                world.spawnParticle(EnumParticleTypes.PORTAL, x + (double)(world.rand.nextFloat() * caster.width * 2.0F) - (double)caster.width - d0 * 10.0D, y + (double)(world.rand.nextFloat() * caster.height) - d1 * 10.0D, z + (double)(world.rand.nextFloat() * caster.width * 2.0F) - (double)caster.width - d2 * 10.0D, d0, d1, d2);
            }
        }
        else
        {
            world.setEntityState(caster, (byte)20);
        }
    }
}
