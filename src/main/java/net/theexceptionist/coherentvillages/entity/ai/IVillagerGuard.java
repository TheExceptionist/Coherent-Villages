package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public interface IVillagerGuard {
	AbstractVillagerSoldier getSoldier();
	BlockPos getPost();
	boolean getArrived();
}
