package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellIronFoot extends Spell{

	public SpellIronFoot(String name, int type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityHumanVillager)
		{
			EntityHumanVillager villager = (EntityHumanVillager) caster;
			
			if(!villager.hasIronFoot())
			{
				villager.setIronFeet(true);
			}
		}
	}

}
