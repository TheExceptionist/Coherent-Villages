package net.theexceptionist.coherentvillages.main.entity.spells;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.DamageSource;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellHarmingSkin extends Spell {

	private int damageType = -1;

	private PotionType potionType;

	private int severity = 2;
	
	static final int FIRE = 0;
	static final int SPIKES = 1;

	public SpellHarmingSkin(String name, int type, int damageType, int severity, PotionType potionType) {
		super(name, type);
		this.damageType = damageType;
		this.potionType = potionType;
		this.severity = severity;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(EntityLivingBase caster) {
		// TODO Auto-generated method stub
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			if(villager.inCombat())
			{
				List<Entity> entities = caster.world.getEntitiesWithinAABBExcludingEntity(caster, caster.getEntityBoundingBox());
				
				for(Entity entity : entities)
				{
					if(entity == villager) continue;
					if(entity instanceof AbstractHorse) continue;
					if(entity instanceof EntityHumanVillager)
					{
						if(!villager.isHostileFaction((EntityHumanVillager)entity)) continue;
					}
					
					if(entity instanceof EntityLivingBase)
					{
						EntityLivingBase living = (EntityLivingBase) entity;
						
						if(damageType > -1)
						{
							switch(damageType)
							{
								case FIRE:
								{
									living.setFire(severity);
								}
								break;
								case SPIKES:
								{
					                living.attackEntityFrom(DamageSource.causeThornsDamage(caster), severity);
								}
								break;
							}
						}
						else if(potionType != null)
						{
							for (PotionEffect potioneffect : potionType.getEffects())
					        {
								living.addPotionEffect(new PotionEffect(potioneffect));
					        }
						}
					}
				}
			}
			
			
			//villager.spawnFlameParticle();
		}
	}

}
