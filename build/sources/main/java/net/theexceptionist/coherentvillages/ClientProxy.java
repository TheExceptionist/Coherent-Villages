package net.theexceptionist.coherentvillages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.theexceptionist.coherentvillages.entity.EntityMerchantGuard;
import net.theexceptionist.coherentvillages.entity.EntityVillagerAlchemist;
import net.theexceptionist.coherentvillages.entity.EntityVillagerArcher;
import net.theexceptionist.coherentvillages.entity.EntityVillagerArrow;
import net.theexceptionist.coherentvillages.entity.EntityVillagerGuardian;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMage;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMerchant;
import net.theexceptionist.coherentvillages.entity.EntityVillagerSoldier;
import net.theexceptionist.coherentvillages.entity.RenderMerchantGuard;
import net.theexceptionist.coherentvillages.entity.RenderVillagerAlchemist;
import net.theexceptionist.coherentvillages.entity.RenderVillagerArcher;
import net.theexceptionist.coherentvillages.entity.RenderVillagerArrow;
import net.theexceptionist.coherentvillages.entity.RenderVillagerGuardian;
import net.theexceptionist.coherentvillages.entity.RenderVillagerMage;
import net.theexceptionist.coherentvillages.entity.RenderVillagerMerchant;
import net.theexceptionist.coherentvillages.entity.RenderVillagerSoldier;
import net.theexceptionist.coherentvillages.events.EventOverrideVillages;

public class ClientProxy extends CommonProxy {
	public void registerRenderInformation(){
		
	}
	
	public void registerRenderers(){
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        
        renderManager.entityRenderMap.put(EntityVillagerSoldier.class, new RenderVillagerSoldier(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerArcher.class, new RenderVillagerArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMage.class, new RenderVillagerMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerGuardian.class, new RenderVillagerGuardian(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerAlchemist.class, new RenderVillagerAlchemist(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMerchant.class, new RenderVillagerMerchant(renderManager));
        renderManager.entityRenderMap.put(EntityMerchantGuard.class, new RenderMerchantGuard(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerArrow.class, new RenderVillagerArrow(renderManager));
}
	
	public void initEvents(){
		//MinecraftForge.EVENT_BUS.register(new EventHandler());	
		MinecraftForge.TERRAIN_GEN_BUS.register(new EventOverrideVillages());	
	}
}
