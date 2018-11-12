package net.theexceptionist.coherentvillages.main.items.generator;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.theexceptionist.coherentvillages.main.NameGenerator;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;

public class ItemGenerator {
	private static String[] possibleWeaponNames = 
		{
				"%n's Sword",
				"%n's Wrath",
				"Sword of %n",
				"",
				"%n's Sword"
		};
	
	
	public static ItemStack generateRandomWeapon(Random rand, Item swordtype, AttributeRace race)
	{	
		ItemStack itemsword = new ItemStack(swordtype);

		if(swordtype instanceof ItemSword)
		{
			String name = possibleWeaponNames[rand.nextInt(possibleWeaponNames.length)].replaceAll("%n", NameGenerator.generateRandomName(rand, race));
			System.out.println(name);
			itemsword.setStackDisplayName(name);
		}
		
		return itemsword;
	}
}
