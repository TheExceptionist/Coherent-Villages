package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellInflict extends Spell{

	private PotionType inflict;
	private int coolDown, setCoolDown;
	
	public SpellInflict(String name, int type, int coolDown, PotionType inflict) {
		super(name, type);
		this.coolDown = coolDown;
		this.setCoolDown = coolDown;
		this.inflict = inflict;

	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(coolDown <= 0 && caster instanceof EntityLiving)
		{
			EntityLivingBase target = ((EntityLiving)caster).getAttackTarget();
			
            boolean targetUndead = target.isEntityUndead();
            boolean targetSpider = target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD;
            
			if(target != null)
			{
				for (PotionEffect potioneffect : inflict.getEffects())
		        {
					if(targetUndead && potioneffect.getPotion() == MobEffects.INSTANT_DAMAGE) continue;
					if(targetSpider && potioneffect.getPotion() == MobEffects.POISON) continue;
					
					target.addPotionEffect(new PotionEffect(potioneffect));
					
					if(target instanceof EntityLiving) ((EntityLiving)target).spawnExplosionParticle();


					double x = -caster.posX + target.posX;
					double z = -caster.posZ + target.posZ;
					
					if(x != 0) x /= x;
					if(z != 0) z /= z;
					
					target.addVelocity(x, 0, z);
					coolDown = this.setCoolDown;
		        }
			}
			
			//System.out.println("CoolDown: "+coolDown);
		}
		else
		{
			coolDown--;
		}
	}

}
