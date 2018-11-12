package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class SpellTeleport extends Spell {

	private int radius;

	public SpellTeleport(String name, int type, int i) {
		super(name, type);
		this.radius = i;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster.hurtTime > 0
				&& !caster.getLastDamageSource().isFireDamage())
		{
			double x = caster.posX + caster.getRNG().nextInt(radius) - radius/2;
			double z = caster.posZ + caster.getRNG().nextInt(radius) - radius/2;
			double y = caster.world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();
			BlockPos pos = new BlockPos(x, y, z);
			
			if(caster.world.isAirBlock(pos) && caster.world.isAirBlock(pos.up()))
			{
				if(caster instanceof EntityHumanVillager) ((EntityHumanVillager)caster).spawnExplosionParticle();
				
				caster.setPosition(x, y, z);
				
				caster.hurtTime = 0;
			}
		}
	}
	
}
