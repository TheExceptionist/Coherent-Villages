package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.main.Main;

public class SpellPlatform extends Spell{

	private int radius;
	private int height;
	private int spellType;
	private int duration;
	private int setDuration;
	private BlockPos castSpot;
	private IBlockState state;
	private int yStart;
	private int yEnd;
	private EntityLivingBase target;

	public SpellPlatform(String name, int type, int radius, int height, int duration, int spellType) {
		super(name, type);
		this.radius = radius;
		this.height = height;
		this.duration = duration;
		this.setDuration = duration;
		this.spellType = spellType;
		
		this.yStart = height < 0 ? height : 0;
		this.yEnd = height >= 0 ? height : 0;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		// TODO Auto-generated method stub
		
		if(caster instanceof EntityLiving)
		{			
			if(caster.hurtTime > 0 && !caster.getLastDamageSource().isExplosion()
					&& !caster.getLastDamageSource().isFireDamage()
					&& !caster.getLastDamageSource().isMagicDamage())
			{
				if(castSpot != null) this.reset(target);
				target = spellType == 1 ? ((EntityLiving)caster).getAttackingEntity() : caster;
				//if(yStart < 0) System.out.println("Target: "+target);
				
				if(target != null && Main.allDestructive)
				{					
					BlockPos floor = target.getPosition().down();
					BlockPos endLoc = target.getPosition();
					castSpot = target.getPosition();
					
					for(int i = -radius; i < radius; i++)
				     {
				    	 for(int n = yStart; n < yEnd; n++)
				    	 {
				    		 for(int j = -radius; j < radius; j++)
				    		 {
				    	 
						    	BlockPos blockpos = target.getPosition().add(i, n, j);
					            IBlockState iblockstate = target.world.getBlockState(floor);
					            Block block = iblockstate.getBlock();
					            state = iblockstate;
					
					            if (yStart < 0 && !block.isAir(iblockstate, caster.world, blockpos) &&  net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(caster, blockpos, iblockstate))
					            {
					                caster.world.destroyBlock(blockpos, true);
					            }
					            else if(!block.isAir(iblockstate, caster.world, blockpos))
					            {
					            	caster.world.setBlockState(blockpos, iblockstate);
					            }
					            
					            if(i == 0 && j == 0)
					            {
					            	endLoc = blockpos.up();
					            }
				    		 }
				    	 }
				     }
					
					((EntityLiving) caster).spawnExplosionParticle();
		            target.motionX = 0;
		            target.motionY = 0;
		            target.motionZ = 0;
		            target.setPosition(endLoc.getX(), endLoc.getY(), endLoc.getZ());
					caster.hurtTime = 0;
					duration = 0;
				}
			}
		}
		
	}
	
	public void reset(EntityLivingBase caster)
	{
		if(duration < setDuration)
		{
			duration++;
		}
		else if(castSpot != null)
		{
			BlockPos endLoc = null;
			
			for(int i = -radius; i < radius; i++)
		     {
		    	 for(int n = yStart; n < yEnd; n++)
		    	 {
		    		 for(int j = -radius; j < radius; j++)
		    		 {
		    	 
				    	BlockPos blockpos = castSpot.add(i, n, j);
			            IBlockState iblockstate = state;
			            Block block = iblockstate.getBlock();
			
			            if (!block.isAir(iblockstate, caster.world, blockpos) &&  net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(caster, blockpos, iblockstate))
			            {
			                caster.world.destroyBlock(blockpos, true);
			            }
			            else if(yStart < 0 && !block.isAir(iblockstate, caster.world, blockpos))
			            {
			            	caster.world.setBlockState(blockpos, iblockstate);
			            }
			            
			            if(i == 0 && j == 0)
			            {
			            	endLoc = blockpos.up();
			            }
		    		 }
		    	 }
		     }
			
			if(target != null) target.setPosition(endLoc.getX(), endLoc.getY(), endLoc.getZ());
			castSpot = null;
		}
	}

}
