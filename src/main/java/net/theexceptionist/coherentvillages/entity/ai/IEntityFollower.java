package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.entity.EntityLiving;

public interface IEntityFollower {

	public boolean isShouldFollow();
	public EntityLiving getLiving();

}
