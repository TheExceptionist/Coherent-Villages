package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntitySkeletonSummon;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;

public class SpellSummonSkeleton extends Spell {
	private int coolDown, coolDownSet, count, lifespan;
	
	public SpellSummonSkeleton(String name, int type, int coolDown, int count, int lifespan) {
		super(name, type);
		// TODO Auto-generated constructor stub
		this.coolDownSet = coolDown;
		this.count = count;
		this.lifespan = lifespan;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(caster instanceof EntityVillager)
		{
			World world = ((EntityVillager)caster).getWorld();
			
			if(coolDown > 0)
			{
				coolDown--;
			}
			if(coolDown <= 0){
				BlockPos spawn = new BlockPos(caster.posX, caster.posY, caster.posZ);
				
				for(int i = 0; i < count; i++)
				{
					EntitySkeletonSummon entityvillager = new EntitySkeletonSummon(world);//, AttributeRace.RACE_TYPE_LATIN, toSpawn, 0, false);                            
					entityvillager.setLocationAndAngles((double)caster.posX + 0.5D, (double)caster.posY, (double)caster.posZ + 0.5D, 0.0F, 0.0F);
					entityvillager.setMaster(caster);
					world.spawnEntity(entityvillager);
					entityvillager.setLifespan(lifespan);
					entityvillager.spawnExplosionParticle();
				}
				
				coolDown = coolDownSet;
			}
		}
		else if(caster instanceof EntityPlayer)
		{
			World world = ((EntityPlayer)caster).world;
			
			if(world.isRemote) return;
			
			for(int i = 0; i < count; i++)
			{
				EntitySkeletonSummon entityvillager = new EntitySkeletonSummon(world);//, AttributeRace.RACE_TYPE_LATIN, toSpawn, 0, false);                            
				entityvillager.setLocationAndAngles((double)caster.posX + 0.5D, (double)caster.posY, (double)caster.posZ + 0.5D, 0.0F, 0.0F);
				entityvillager.setMaster(caster);
				world.spawnEntity(entityvillager);
				entityvillager.setLifespan(lifespan);
				entityvillager.spawnExplosionParticle();
			}
		}
	}

}
