package net.theexceptionist.coherentvillages.events;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;

public class EventModTick {
	public static boolean raidInProgress = false;
	boolean raidHappened = false;
	boolean driveAttempted = false;
	int daysPassed = 0;
	private static ArrayList<TextComponentString> messageReader = new ArrayList<TextComponentString>(); 
	//Probably will only work on singleplayer
	
	public static void addMessage(TextComponentString msg)
	{
		messageReader.add(msg);
	}
	
	@SubscribeEvent
	public void checkRaid(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = event.player.world;

		
		Style style = new Style();
		style.setColor(TextFormatting.DARK_RED);
		
		Style style2 = new Style();
		style2.setColor(TextFormatting.LIGHT_PURPLE);
		
		
		if(messageReader.size() > 0)
		{
			TextComponentString str = messageReader.get(0);
			
			player.sendMessage(str);
			
			messageReader.remove(str);
		}
		//manager.tick();
		
		/*if(!world.isRemote)
		{
			if(!spawned && world.isDaytime())
			{
				if(world.rand.nextInt(100) <= Main.BANDIT_SKIRMISH) return;
				int count = world.rand.nextInt(4) + 2;
				double x = player.posX + range;
				double z = player.posZ + range;
				
				//Vec3d vec = this.polarRotate(x, z, world.rand.nextInt(360));
				
				double y = player.world.getTopSolidOrLiquidBlock(new BlockPos(x, 80, z)).getY();
				BlockPos spawn = new BlockPos(x, y, z);
				World worldIn = player.world;
				
				for(int i = 0; i < count; i++)
				{
					EntityHumanVillager soldier = new EntityHumanVillager(worldIn, raceID, AttributeRace.getFromIDRace(raceID).getRandomSoldier(worldIn), EntityHumanVillager.getRandomGender(worldIn), false);                            
	            	soldier.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
	            	worldIn.spawnEntity(soldier);
	            	
					EntityHumanVillager bandit = new EntityHumanVillager(worldIn, raceID, AttributeRace.getFromIDRace(raceID).getRandomBandit(worldIn), EntityHumanVillager.getRandomGender(worldIn), false);                            
	            	bandit.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
	            	worldIn.spawnEntity(bandit);
				}
				
				Style style3 = new Style();
				style3.setColor(TextFormatting.GREEN);
				TextComponentString eventMessage = new TextComponentString("You hear a battle going on somewhere nearby....");
				eventMessage.setStyle(style3);
				
				player.sendMessage(eventMessage);
				
				spawned = true;
			}
			else if(spawned && !world.isDaytime())
			{
				spawned = false;
			}
			/*AttributeFaction faction = WorldGenVillage.nordManager.getNearestVillage(player.getPosition(), 50);
			//System.out.println("Working: "+faction);
			if(faction != null && !faction.messageSent())
			{
				ITextComponent itextcomponent1 = new TextComponentString(
						player.getDisplayNameString()+", welcome To: "+faction.getTitleName());
				itextcomponent1.setStyle(style2);
				player.sendMessage(itextcomponent1);
				faction.setMessageSent(false);
			}
		}*/
		
		/*if(world.villageCollection != null){
			Village village = world.villageCollection.getNearestVillage(player.getPosition(), 30);
			
			if(!world.isRemote && village != null){
				BlockPos center = village.getCenter();
				if(!world.isDaytime() && !raidInProgress && Main.bandit_spawn > 0){
					int radius = village.getVillageRadius();
					int offX = center.getX() + (world.rand.nextBoolean() == true ? 0 : radius * ((world.rand.nextBoolean() == true) ? -1 : 1));
					int offZ = center.getZ() + (world.rand.nextBoolean() == true ? 0 : radius * ((world.rand.nextBoolean() == true) ? -1 : 1));
					int y = world.getTopSolidOrLiquidBlock(new BlockPos(offX, 60, offZ)).getY();
					BlockPos spawn = new BlockPos(offX, y, offZ);
					VillageDoorInfo door = village.getNearestDoor(spawn);
					BlockPos spawnpoint = door.getDoorBlockPos().add(20, 0, -1);
					
					//System.out.println(radius+" - "+offX+" | "+offZ+" Spawn: "+spawnpoint.getX()+" "+spawnpoint.getY()+" "+spawnpoint.getZ());
					if(world.rand.nextInt(100) < Main.raid_rate)
					{
						int numBandits = world.rand.nextInt(5 * ((int)Math.floor(world.getDifficultyForLocation(spawnpoint).getClampedAdditionalDifficulty() + 1))) + 5;
						AbstractVillagerBandit bandit = null;
						
						ITextComponent itextcomponent1 = new TextComponentString(
								"A village raid has begun "+(int)Math.sqrt(player.getPosition().distanceSq(spawnpoint))+" blocks away!");
						itextcomponent1.setStyle(style);
						player.sendMessage(itextcomponent1);
						
						while(numBandits > 0)
						{
							bandit = new EntityVillagerBandit(world);
							
							if(world.rand.nextInt(100) < 50) bandit = new EntityVillagerBanditArcher(world);
							else if(world.rand.nextInt(100) < 50) bandit = new EntityVillagerBanditHorseman(world);
							else if(world.rand.nextInt(100) < 25) bandit = new EntityVillagerBanditAlchemist(world);
							else if(world.rand.nextInt(100) < 10) bandit = new EntityVillagerBanditMage(world);
							
							if(numBandits == 1) bandit = new EntityVillagerDarkKnight(world);
							if(!bandit.isCanSpawn()) continue;
							
							bandit.onInitialSpawn(world.getDifficultyForLocation(spawnpoint), null);
							bandit.setAlwaysRenderNameTag(true);
		                	bandit.setLocationAndAngles(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), 0.0F, 0.0F);
		                    bandit.setSpawnPoint(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ());
		                    
		                    bandit.finalizeMobSpawn(world.getDifficultyForLocation(new BlockPos(bandit)), (IEntityLivingData)null, false);
		                    world.spawnEntity(bandit);
		                    
							spawnpoint = spawnpoint.add(1, 0, 0);
							spawnpoint = new BlockPos(spawnpoint.getX(), world.getTopSolidOrLiquidBlock(spawnpoint).getY(), spawnpoint.getZ());
							
							numBandits--;
							raidHappened = true;
						}
						
						
					}
					raidInProgress = true;
				}
				else if(world.isDaytime() && raidInProgress)
				{
					raidInProgress = false;
				}
				
				if(world.getTotalWorldTime() % 14500 == 0)
				{
					daysPassed++;
				}
				
				//recruiting drive
				System.out.println(daysPassed+" "+raidHappened);
				if(world.isDaytime() && !driveAttempted)
				{
					driveAttempted = true;
					
					int num = village.getNumVillagers() / 4;
					BlockPos spawnpoint = village.getNearestDoor(center).getDoorBlockPos();
					AbstractVillagerSoldier soldier = null;
					
					if(world.rand.nextInt(100) > Main.recruit_rate)
					{
						if(world.rand.nextInt(100) < 75) return;
						if(village.getNumVillagers() + num > village.getNumVillageDoors() * 2) return;
					}
					
					ITextComponent itextcomponent1 = new TextComponentString(
							"A village recruiting drive has begun "+(int)Math.sqrt(player.getPosition().distanceSq(center))+" blocks away!");
					itextcomponent1.setStyle(style2);
					player.sendMessage(itextcomponent1);
					
					while(num > 0)
					{
						soldier = new EntityVillagerMilitia(world);
						
						if(world.rand.nextInt(100) < 50) soldier = new EntityVillagerArcher(world);
						else if(world.rand.nextInt(100) < 50) soldier = new EntityVillagerCavalier(world);
						else if(world.rand.nextInt(100) < 25) soldier = new EntityVillagerAlchemist(world);
						else if(world.rand.nextInt(100) < 10) soldier = new EntityVillagerMage(world);
						
						soldier.setFreshRecruit(true);
						if(!soldier.isCanSpawn()) continue;
						
						//world.spaw
						soldier.onInitialSpawn(world.getDifficultyForLocation(spawnpoint), null);
	                	soldier.setLocationAndAngles(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), 0.0F, 0.0F);
	                    soldier.setSpawnPoint(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ());
	                    
	                    soldier.finalizeMobSpawn(world.getDifficultyForLocation(new BlockPos(soldier)), (IEntityLivingData)null, false);
	                    world.spawnEntity(soldier);
	                    
						spawnpoint = spawnpoint.add(1, 0, 0);
						spawnpoint = new BlockPos(spawnpoint.getX(), world.getTopSolidOrLiquidBlock(spawnpoint).getY(), spawnpoint.getZ());
						
						num--;
					}
					
					
					daysPassed = 0;
					raidHappened = false;
				}
				else if(!world.isDaytime() && raidHappened && driveAttempted)
				{
					driveAttempted = false;
				}
			}
		}*/
	}
	
	@SubscribeEvent
	public void checkKills(LivingDeathEvent event)
	{
		//System.out.println("Working");
		EntityLivingBase killed = event.getEntityLiving();
		EntityLivingBase killer = killed.getAttackingEntity();
		
		if(killer instanceof EntityPlayer && killed instanceof EntityHumanVillager)
		{
			EntityPlayer player = (EntityPlayer) killer;
			EntityHumanVillager soldier = (EntityHumanVillager) killed;
			Style style = new Style();
			style.setColor(TextFormatting.WHITE);
			
			ITextComponent itextcomponent1 = new TextComponentString(
					player.getDisplayNameString()+" has killed "+soldier.getFullName());
			itextcomponent1.setStyle(style);
			player.sendMessage(itextcomponent1);
		}
		
		if(killer instanceof EntityHumanVillager)
		{
		//	System.out.println(killed.getName()+" "+killer.getName());
			
			EntityHumanVillager soldier = (EntityHumanVillager) killer;
			
			soldier.addKills(1);
		}
	}
	
}
