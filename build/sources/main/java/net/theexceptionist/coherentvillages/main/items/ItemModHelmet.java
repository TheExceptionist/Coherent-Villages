package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.main.Main;

public class ItemModHelmet extends ItemModArmor{


	public ItemModHelmet(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped defaultModel)
    {
		if (itemStack != null) {
			if (itemStack.getItem() instanceof ItemArmor) {

				EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
				ModelBiped armorModel = null;
				
				switch (type) {
					case HEAD:
						if(itemStack.getItem() == ModItems.hornHelmet)
						{
							armorModel = Main.proxy.getArmorID(0);  //modelNordHelmet;
						}
						else
						{
							armorModel = Main.proxy.getArmorID(1);
							//armorModel = modelRomanHelmet;
						}
						break;
				default:
					break;
				}

				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
						|| (armorSlot == EntityEquipmentSlot.CHEST);
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
						|| (armorSlot == EntityEquipmentSlot.FEET);
				armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
						|| (armorSlot == EntityEquipmentSlot.FEET);

				armorModel.isSneak = defaultModel.isSneak;
				armorModel.isRiding = defaultModel.isRiding;
				armorModel.isChild = defaultModel.isChild;
				armorModel.rightArmPose = defaultModel.rightArmPose;
				armorModel.leftArmPose = defaultModel.leftArmPose;

				return armorModel;
			}
		}
		return null;
    }
}
