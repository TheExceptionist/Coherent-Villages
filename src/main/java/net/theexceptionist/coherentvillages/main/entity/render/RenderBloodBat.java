package net.theexceptionist.coherentvillages.main.entity.render;

import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.EntityBloodBat;
import net.theexceptionist.coherentvillages.main.entity.model.ModelBloodBat;

public class RenderBloodBat extends RenderLiving<EntityBloodBat>
{
    private static final ResourceLocation BAT_TEXTURES = new ResourceLocation(Resources.MODID, "textures/entity/bat.png");

    public RenderBloodBat(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBloodBat(), 0.25F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBloodBat entity)
    {
        return BAT_TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityBloodBat entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    protected void applyRotations(EntityBloodBat entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        if (entityLiving.getIsBatHanging())
        {
            GlStateManager.translate(0.0F, -0.1F, 0.0F);
        }
        else
        {
            GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
        }

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}