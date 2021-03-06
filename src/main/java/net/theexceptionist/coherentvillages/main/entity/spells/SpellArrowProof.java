package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellArrowProof extends Spell {

	public SpellArrowProof(String name, int type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			if(villager.inCombat())
			{
				((EntityHumanVillager) caster).setArrowProof(true);
				//((EntityHumanVillager) caster).spawnCloudParticle();
			}
			else
			{
				((EntityHumanVillager) caster).setArrowProof(false);
			}
		}
	}

}
