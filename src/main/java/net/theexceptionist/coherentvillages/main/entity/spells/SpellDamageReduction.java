package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellDamageReduction extends Spell{
	
	
	private int percent;

	public SpellDamageReduction(String name, int type, int percent) {
		super(name, type);
		this.percent = percent;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			if(villager.inCombat())
			{
				villager.setReductionAmount(percent);
				//this.spawnEndParticle(caster.world, caster, caster.posX, caster.posY, caster.posZ);
			}
			else
			{
				villager.setReductionAmount(0);
			}
			
			//villager.spawnEndParticle();
		}
	}

}
