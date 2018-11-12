package net.theexceptionist.coherentvillages.main.entity.spells;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellBanishTouch extends Spell {

	private int radius;

	public SpellBanishTouch(String name, int type, int radius) {
		super(name, type);
		this.radius = radius;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(EntityLivingBase caster) {
		// TODO Auto-generated method stub
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			List<Entity> entities = caster.world.getEntitiesWithinAABBExcludingEntity(caster, caster.getEntityBoundingBox().grow(radius).offset(-1, 0, -1));
				
			for(Entity entity : entities)
			{
				if(entity instanceof EntityLiving)
				{
					if(((EntityLiving) entity).isEntityUndead())
					{
						((EntityLiving) entity).spawnExplosionParticle();
						
						if(entity instanceof EntityHumanVillager && ((EntityHumanVillager)entity).isVampire())
						{
							entity.setFire(60);
							((EntityLiving) entity).knockBack(caster, 1.0f, (double)MathHelper.sin(caster.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(caster.rotationYaw * 0.017453292F)));
						}
						else entity.setDead();
					}
				}
			}
			
			//villager.spawnExplosionParticle();
			
		}
	}
}
