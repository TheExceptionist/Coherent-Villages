package net.theexceptionist.coherentvillages.entity.soldier;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.theexceptionist.coherentvillages.main.Resources;

public class RenderVillagerPeasant extends RenderVillagerSoldier{
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTURE = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier_a.png");
	/*
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREA = new ResourceLocation(Reference.MODID,"textures/entity/villager/soldier_a.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREB = new ResourceLocation(Reference.MODID,"textures/entity/villager/soldier_b.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREC = new ResourceLocation(Reference.MODID,"textures/entity/villager/soldier_c.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTURED = new ResourceLocation(Reference.MODID,"textures/entity/villager/soldier_d.png");
	private static final ResourceLocation VILLAGER_KNIGHT_TEXTURE = new ResourceLocation(Reference.MODID,"textures/entity/villager/captain_a.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREX = new ResourceLocation(Reference.MODID,"textures/entity/villager/captain_a.png");
	*/

    public RenderVillagerPeasant(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVillager entity)
    {
		return VILLAGER_SOLDIER_TEXTURE;
    }
}
