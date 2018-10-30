package net.theexceptionist.coherentvillages.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDrachen extends EntityMob implements IMob, EntityFlying, IEntityMultiPart, IRangedAttackMob
{
    /** An array containing all body parts of this dragon */
    public MultiPartEntityPart[] dragonPartArray;
    /** The head bounding box of a dragon */
    public MultiPartEntityPart dragonPartHead = new MultiPartEntityPart(this, "head", 6.0F, 6.0F);
    public MultiPartEntityPart dragonPartNeck = new MultiPartEntityPart(this, "neck", 6.0F, 6.0F);
    /** The body bounding box of a dragon */
    public MultiPartEntityPart dragonPartBody = new MultiPartEntityPart(this, "body", 8.0F, 8.0F);
    public MultiPartEntityPart dragonPartTail1 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart dragonPartTail2 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart dragonPartTail3 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart dragonPartWing1 = new MultiPartEntityPart(this, "wing", 4.0F, 4.0F);
    public MultiPartEntityPart dragonPartWing2 = new MultiPartEntityPart(this, "wing", 4.0F, 4.0F);
	public int prevAnimTime;
	public int animTime;
    
	public EntityDrachen(World worldIn) {
		super(worldIn);
		this.width = 7;
		this.height = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		// TODO Auto-generated method stub
		return false;
	}

	public double[] getMovementOffsets(int i, float partialTicks) {
		// TODO Auto-generated method stub
		return null;
	}

	public float getHeadPartYOffset(int i, double[] adouble, double[] adouble1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
