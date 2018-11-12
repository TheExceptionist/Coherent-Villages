package net.theexceptionist.coherentvillages.main.entity;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.Village;

public interface IEntityVillager {
	public abstract Village getVillage();
	public abstract EntityVillager getLiving();
}
