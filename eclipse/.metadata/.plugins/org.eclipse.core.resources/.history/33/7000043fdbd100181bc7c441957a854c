package net.theexceptionist.coherentvillages.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.theexceptionist.coherentvillages.entity.EntityVillagerArrow;
import net.theexceptionist.coherentvillages.entity.archer.RenderVillagerArrow;
import net.theexceptionist.coherentvillages.events.EventModTick;
import net.theexceptionist.coherentvillages.events.EventOverrideVillages;
import net.theexceptionist.coherentvillages.events.PlayerConnectionEvent;
import net.theexceptionist.coherentvillages.main.entity.EntityBjornserker;
import net.theexceptionist.coherentvillages.main.entity.EntityHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.EntityWarg;
import net.theexceptionist.coherentvillages.main.entity.RenderBjornserker;
import net.theexceptionist.coherentvillages.main.entity.RenderHumanVillager;
import net.theexceptionist.coherentvillages.main.entity.RenderWarg;

public class ClientProxy extends CommonProxy {
	public void registerRenderInformation(){
		
	}
	
	public void registerRenderers(){
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		//Humans
		renderManager.entityRenderMap.put(EntityHumanVillager.class, new RenderHumanVillager(renderManager));
		renderManager.entityRenderMap.put(EntityWarg.class, new RenderWarg(renderManager));
		renderManager.entityRenderMap.put(EntityBjornserker.class, new RenderBjornserker(renderManager));
		
		/*
		//Soldiers
        renderManager.entityRenderMap.put(EntityVillagerGuard.class, new RenderVillagerGuard(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerPeasant.class, new RenderVillagerPeasant(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerSergeant.class, new RenderVillagerSergeant(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerWarrior.class, new RenderVillagerWarrior(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerManAtArms.class, new RenderVillagerManAtArms(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMilitia.class, new RenderVillagerMilitia(renderManager));
        
        //Archers
        renderManager.entityRenderMap.put(EntityVillagerArcher.class, new RenderVillagerArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHunter.class, new RenderVillagerHunter(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMageArcher.class, new RenderVillagerMageArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMarksman.class, new RenderVillagerMarksman(renderManager));
        
        //Mages
        renderManager.entityRenderMap.put(EntityVillagerMage.class, new RenderVillagerMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerGrandMage.class, new RenderVillagerGrandMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerConjurer.class, new RenderVillagerConjurer(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerNecromancer.class, new RenderVillagerNecromancer(renderManager));
        
        //Alchemists
        renderManager.entityRenderMap.put(EntityVillagerAlchemist.class, new RenderVillagerAlchemist(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerUndeadHunter.class, new RenderVillagerUndeadHunter(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHealer.class, new RenderVillagerHealer(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerPotionMaster.class, new RenderVillagerPotionMaster(renderManager));
        
        //Knights
        renderManager.entityRenderMap.put(EntityVillagerKnight.class, new RenderVillagerKnight(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerCavalier.class, new RenderVillagerCavalier(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMageKnight.class, new RenderVillagerMageKnight(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerPaladin.class, new RenderVillagerPaladin(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHorseArcher.class, new RenderVillagerHorseArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerApothecary.class, new RenderVillagerApothecary(renderManager));
        

        renderManager.entityRenderMap.put(EntityVillagerMerchant.class, new RenderVillagerMerchant(renderManager));
        
        renderManager.entityRenderMap.put(EntityVillagerGuardian.class, new RenderVillagerGuardian(renderManager));
        renderManager.entityRenderMap.put(EntitySkeletonMinion.class, new RenderSkeleton(renderManager));
        renderManager.entityRenderMap.put(EntityMerchantGuard.class, new RenderMerchantGuard(renderManager));
        
        renderManager.entityRenderMap.put(EntityVillagerHorse.class, new RenderVillagerHorse(renderManager));
        
        //Bandits
        renderManager.entityRenderMap.put(EntityVillagerBandit.class, new RenderVillagerBandit(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerBanditArcher.class, new RenderVillagerBanditArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerBanditMage.class, new RenderVillagerBanditMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerBanditHorseman.class, new RenderVillagerBanditHorseman(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerBanditAlchemist.class, new RenderVillagerBanditAlchemist(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerDarkKnight.class, new RenderVillagerDarkKnight(renderManager));
        
        //renderManager.entityRenderMap.put(EntityVillagerEvilMage.class, new RenderVillagerEvilMage(renderManager));*/
        
        renderManager.entityRenderMap.put(EntityVillagerArrow.class, new RenderVillagerArrow(renderManager));
}
	
	public void initEvents(){
		MinecraftForge.EVENT_BUS.register(new EventModTick());	
		MinecraftForge.EVENT_BUS.register(new PlayerConnectionEvent());	
		MinecraftForge.TERRAIN_GEN_BUS.register(new EventOverrideVillages());
	
	}
}
