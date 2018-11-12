package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.EntityVillagerLighting;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellThunder extends Spell {
	private int coolDown, coolDownSet;
	
	public SpellThunder(String name, int type, int coolDown) {
		super(name, type);
		this.coolDownSet = coolDown;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityHumanVillager)
		{
			if(coolDown <= 0)
			{
				EntityLivingBase target = ((EntityLiving)caster).getAttackTarget();
				caster.world.addWeatherEffect(new EntityVillagerLighting(caster.world, (EntityHumanVillager)caster, target.posX, target.posY, target.posZ, true));
				this.coolDown = this.coolDownSet;
			}
			else
			{
				this.coolDown--;
			}
		}
		
		else if(caster instanceof EntityPlayer)
		{
			World world  = ((EntityPlayer)caster).world;
			
			/*EntityLivingBase target = ((EntityPlayer)caster).getAttackTarget();
			caster.world.addWeatherEffect(new EntityVillagerLighting(caster.world, target.posX, target.posY, target.posZ, true));
			this.coolDown = this.coolDownSet;*/
		}
	}

}
