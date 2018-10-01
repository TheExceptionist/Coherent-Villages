package net.theexceptionist.coherentvillages.entity.followers;

import net.minecraft.entity.EntityLiving;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public interface IEntityFollower {
	public void setMaster(AbstractVillagerSoldier villager);
	public boolean isShouldFollow();
	public EntityLiving getLiving();
}
