package net.theexceptionist.coherentvillages.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.main.entity.EntityBjornserker;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.EntityWraith;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeRace;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;
import net.theexceptionist.coherentvillages.main.items.generator.ItemGenerator;

public class CommandGenerateWeapon implements ICommand{
	private final List aliases;
	
	protected String entityName;
	protected Entity conjuredEntity;
	
	public CommandGenerateWeapon()
	{
		aliases = new ArrayList<>();
		aliases.add("generate");
		aliases.add("gen");
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "generator";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "generate <villager_culture> <item_type> <item_level>";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		World world = sender.getEntityWorld(); 
	    
        if (world.isRemote) 
        { 
            //System.out.println("Not processing on Client side"); 
        } 
        else 
        { 
        	ItemStack weapon = null;
        	Random rand = world.rand;
        	
        	if(args.length == 0) 
            { 
                sender.sendMessage(new TextComponentString("Invalid argument\nUsage: "+this.getUsage(sender)+"\nEx: /create nord warrior")); 
                return; 
            } 
            
            AttributeRace race = AttributeRace.getRaceFromString(args[0]);
            
            if(race == null)
            {
            	 sender.sendMessage(new TextComponentString("Invalid Race")); 
                 return;
            }

            if(args.length > 1 && args[1].equals("sword"))
            {
            	sender.getCommandSenderEntity().setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemGenerator.generateRandomWeapon(rand, Items.IRON_SWORD, race));
            }
       } 
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
}
