package net.theexceptionist.coherentvillages.main;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class EntityVillagerEntry extends EntityEntry{

	public EntityVillagerEntry(Class<? extends Entity> cls, String name) {
		super(cls, name);
	}



}
