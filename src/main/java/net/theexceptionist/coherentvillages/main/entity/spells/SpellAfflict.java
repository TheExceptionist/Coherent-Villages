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
	private int inDay;
	
	public SpellAfflict(String name, int type, int coolDown, PotionType inflict, int inDay) {
		super(name, type);
		this.coolDown = coolDown;
		this.setCoolDown = coolDown;
		this.inflict = inflict;
		this.inDay = inDay;

	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(coolDown <= 0 && caster instanceof EntityLiving && ((caster.world.isDaytime() == (inDay == 0 ? false : true)) || inDay > 1))
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
