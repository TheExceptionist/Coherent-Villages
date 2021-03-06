package net.theexceptionist.coherentvillages.main.entity.spells;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.projectile.EntityModFireballSmall;
import net.theexceptionist.coherentvillages.main.entity.projectile.EntityModLargeFireball;

public class SpellArrow extends Spell{
	private int count, power;
	private boolean isVolley;
	private boolean largeFireball;
	private int burst, burstSet;
	private int coolDown, coolDownSet;
	private int attackType;
	
	public static final int ATTACK_TYPE_NORMAL = 0;
	public static final int ATTACK_TYPE_DROP = 1;
	public static final int ATTACK_TYPE_CIRCLE = 2;
	
	public SpellArrow(String name, int type, int count, int power, int burst, int coolDown, boolean isVolley, boolean isGreater, int attackType) {
		super(name, type);
		this.count = count;
		this.isVolley = isVolley;
		this.largeFireball = isGreater;
		this.power = power;
		this.burstSet = burst;
		this.burst = this.burstSet;
		this.coolDownSet = coolDown;
		this.attackType = attackType;
	}

	@Override
	public void execute(EntityLivingBase caster) {
		if(this.coolDown <= 0 && caster instanceof EntityLiving)
		{
	        Vec3d vec3d = caster.getLook(1.0F);
	        EntityLivingBase entitylivingbase = ((EntityLiving)caster).getAttackTarget();
	        World world = caster.world;
	        
	        world.playSound((EntityPlayer)null, caster.posX, caster.posY, caster.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 2.0F, 0.5F + world.rand.nextFloat() * 0.2F);
		    
	        int numFireballs = count;
	        if(isVolley) numFireballs = world.rand.nextInt(count) + 1;
	        
	        double x = caster.posX + vec3d.x * 4.0D;
	        double y = caster.posY + (double)(caster.height / 2.0F) + 0.5D;
	        double z = caster.posZ + vec3d.z * 4.0D;
	        
	        int degree = 0;
	
	        for(int i = 0; i < numFireballs; i++)
	        {
	        	EntityArrow entityarrow = null;
	        	
	        	
	        	switch(attackType)
	        	{
		        	case ATTACK_TYPE_NORMAL:
		        	{
		        		entityarrow = this.getArrow(world, caster, x, y, z, 1.0f);
		        	}
		        	break;
		        	case ATTACK_TYPE_DROP:
		        	{
		        		y = entitylivingbase.posY + 10;
		        		entityarrow = this.getArrow(world, caster, x, y, z, 1.0f);
		        	}
		        	break;
		        	case ATTACK_TYPE_CIRCLE:
		        	{
		        		float radius = caster.getDistance(entitylivingbase);
		        		radius -= radius * 0.2;
		        		double rad = Math.toDegrees(degree);
		        		degree += 5;
		        		
		        		x += radius * Math.cos(rad);
		        		z += radius * Math.sin(rad);
		        		
		        		entityarrow = this.getArrow(world, caster, x, y, z, 1.0f);
		        	}
		        	break;
		        	default:
		        		entityarrow = this.getArrow(world, caster, x, y, z, 1.0f);
	        	}
	        	
	    		entityarrow.setDamage((float)caster.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() / (2 * (caster.getRNG().nextInt(2) - 1)));
	    		//entityarrow.setIsCritical(true);

	           double d0 = entitylivingbase.posX - x;
	           double d1 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 3.0F) - entityarrow.posY;
	           double d2 = entitylivingbase.posZ - z;
	           double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	           entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - caster.world.getDifficulty().getDifficultyId() * 4));
	          
	        	this.spawnExplosionParticle(world, caster, x, y, z);
	           caster.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (caster.getRNG().nextFloat() * 0.4F + 0.8F));
	           caster.world.spawnEntity(entityarrow);
		        this.burst--;
	        }
	        
	        if(burst <= 0)
	        {
	        	this.coolDown = this.coolDownSet;
	        	this.burst = this.burstSet;
	        }
		}
		else
		{
			this.coolDown--;
		}
	}
	protected EntityArrow getArrow(World world, EntityLivingBase caster, double x, double y, double z, float p_190726_1_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, x, y, z);
        entitytippedarrow.setEnchantmentEffectsFromEntity(caster, p_190726_1_);
        return entitytippedarrow;
	}

}
