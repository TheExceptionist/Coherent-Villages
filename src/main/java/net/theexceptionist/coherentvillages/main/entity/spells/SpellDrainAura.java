package net.theexceptionist.coherentvillages.main.entity.spells;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.util.DamageSource;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellDrainAura extends Spell{
	private int radius;
	private int amount;

	public SpellDrainAura(String name, int type, int radius, int amount) {
		super(name, type);
		this.radius = radius;
		this.amount = amount;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			List<Entity> entities = caster.world.getEntitiesWithinAABBExcludingEntity(caster, caster.getEntityBoundingBox().grow(radius).offset(-1, 0, -1));
			
			for(Entity entity : entities)
			{
				if(entity instanceof EntityLiving)
				{
					if(!((EntityLiving) entity).isEntityUndead() && villager.inCombat())
					{
						if(entity instanceof EntityHumanVillager && !villager.isHostileFaction((EntityHumanVillager)entity)) break;
						if(entity instanceof AbstractHorse || entity instanceof EntityGolem) break;
						
						((EntityLiving) entity).spawnExplosionParticle();
		    			entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(caster, caster), amount);
		    			caster.heal(amount);
		    			villager.spawnExplosionParticle();
					}
				}
			}			
		}
	}

}
