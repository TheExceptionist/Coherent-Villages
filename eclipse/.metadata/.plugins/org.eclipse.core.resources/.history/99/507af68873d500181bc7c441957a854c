package net.theexceptionist.coherentvillages.main.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.coherentvillages.entity.ai.EntityAIHideFromHarm;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.attributes.AttributeVocation;

public class RenderBjornserker extends RenderLiving<EntityPolarBear>
{

	private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/bear/polarbear.png");

    public RenderBjornserker(RenderManager p_i47197_1_)
    {
        super(p_i47197_1_, new ModelPolarBear(), 0.7F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPolarBear entity)
    {
        return POLAR_BEAR_TEXTURE;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityPolarBear entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityPolarBear entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.5F, 1.4F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

}
