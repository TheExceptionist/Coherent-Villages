package net.theexceptionist.coherentvillages.main.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.theexceptionist.coherentvillages.main.Resources;

public class RenderWarg extends RenderLiving<EntityWolf>
{
	private static final ResourceLocation ANRGY_WOLF_TEXTURES = new ResourceLocation(Resources.MODID, "textures/entity/wolf/wolf_angry.png");

	public RenderWarg(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelWolf(), 0.5F);
	}
	
	protected float handleRotationFloat(EntityWolf livingBase, float partialTicks)
    {
        return livingBase.getTailRotation();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityWolf entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	//this.renderOutlines = true;
        if (entity.isWolfWet())
        {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color(f, f, f);
        }
        
       // GlStateManager.scale(1.2F, 1.3F, 1.0F);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    protected void preRenderCallback(EntityWolf entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityWolf entity)
    {
        return ANRGY_WOLF_TEXTURES;
    }

}
