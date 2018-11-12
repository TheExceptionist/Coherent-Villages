package net.theexceptionist.coherentvillages.entity.ai;

import net.minecraft.util.math.BlockPos;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public interface IVillagerGuard {
	EntityHumanVillager getSoldier();
	BlockPos getPost();
	boolean getArrived();
}
