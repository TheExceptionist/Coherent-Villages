package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.theexceptionist.coherentvillages.main.Main;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellLaser extends Spell {
	private int coolDown, setCoolDown;
	private int damage;
	private int explosion;
	
	public SpellLaser(String name, int type, int coolDown, int damage, int explosion) {
		super(name, type);
		this.coolDown = coolDown;
		this.setCoolDown = coolDown;
		this.damage = damage;
		this.explosion = explosion;
	}
	
	@Override
	public void execute(EntityLivingBase caster) {
		if(coolDown <= 0 && caster instanceof EntityLiving)
		{
			EntityLivingBase target = ((EntityLiving)caster).getAttackTarget();
			
           // entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.guardian), (float)this.guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
            if(explosion == 0 && Main.allDestructive)
            {
            	int explosion = 0;
            	
            	if(caster instanceof EntityHumanVillager) explosion = ((EntityHumanVillager)caster).getVocation().getRank();
            	
                caster.world.newExplosion((Entity)null, target.posX, target.posY, target.posZ, (float)explosion, true, true);
            }
            else if(explosion != -1 && Main.allDestructive)
            {
                caster.world.newExplosion((Entity)null, target.posX, target.posY, target.posZ, (float)explosion, true, true);
            }
            else
            {
    			target.attackEntityFrom(DamageSource.causeIndirectMagicDamage(caster, caster), damage);
    			target.attackEntityFrom(DamageSource.MAGIC, damage);
                target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 300, 1));
            }
			
			coolDown = this.setCoolDown;
			
			if(target instanceof EntityHumanVillager) ((EntityHumanVillager)target).spawnEndParticle();

		}
		else
		{
			coolDown--;
		}
	}

}