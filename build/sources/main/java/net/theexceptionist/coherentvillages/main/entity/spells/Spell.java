package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public abstract class Spell {
	public static final SpellFireball fireball = new SpellFireball("Fireball", Spell.SPELL_TYPE_ATTACK, 1, 0, 5, 10,  false, false);
	public static final SpellFireball fireball_volley = new SpellFireball("Fireball Volley", Spell.SPELL_TYPE_ATTACK, 5, 0, 1, 10, true, false);
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

	
	public static final SpellSummonAncient summon_ancient_warror = new SpellSummonAncient("Summon Ancient Warrior", Spell.SPELL_TYPE_SUMMON, 40, 3, 1000);
	
	public static final SpellSummonSkeleton summon_skeleton = new SpellSummonSkeleton("Summon Skeleton", Spell.SPELL_TYPE_SUMMON, 30, 5, 500);
	public static final SpellSummonSkeleton summon_skeleton_horde = new SpellSummonSkeleton("Summon Skeleton Horde", Spell.SPELL_TYPE_SUMMON, 20, 10, 200);

	public static final SpellSummon summon_bats = new SpellSummon("Summon Bats", Spell.SPELL_TYPE_SUMMON, 20, 3, 200);
	public static final SpellSummon summon_bat = new SpellSummon("Summon Bat", Spell.SPELL_TYPE_SUMMON, 20, 1, 200);

	public static final SpellThunder thunderbolt = new SpellThunder("Thunderbolt", Spell.SPELL_TYPE_ATTACK, 10);
	
	public static final SpellThunderStorm storm = new SpellThunderStorm("Storm", Spell.SPELL_TYPE_WEATHER);
	//Active
	public static final int SPELL_TYPE_ATTACK = 0;
	public static final int SPELL_TYPE_SUMMON = 1;
	
	//Passive
	public static final int SPELL_TYPE_WEATHER = 2;
	public static final int SPELL_TYPE_TRANSMUTE = 3;
	
	protected String name;
	protected int type;
	protected boolean isActive = false;
	
	public Spell(String name, int type)
	{
		this.name = name;
		this.type = type;

		if(type == SPELL_TYPE_ATTACK || type == SPELL_TYPE_SUMMON) isActive = true;
	}	
	
	public abstract void execute(EntityLivingBase caster);

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
}
