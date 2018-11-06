package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellAfflict extends Spell{

	private PotionType inflict;
	private int coolDown, setCoolDown;
	
	public SpellAfflict(String name, int type, int coolDown, PotionType inflict) {
		super(name, type);
		this.coolDown = coolDown;
		this.setCoolDown = coolDown;
		this.inflict = inflict;

	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(coolDown <= 0 && caster instanceof EntityLiving)
		{
			if(caster instanceof EntityHumanVillager)
			{
				EntityHumanVillager villager = (EntityHumanVillager) caster;
				
				if(villager.inCombat())
				{
					for (PotionEffect potioneffect : inflict.getEffects())
			        {
						villager.addPotionEffect(new PotionEffect(potioneffect));
						//villager.spawnEndParticle();
			        }
					
					coolDown = this.setCoolDown;

				}
				
			}
		}
		else
		{
			coolDown--;
		}
	}
}
