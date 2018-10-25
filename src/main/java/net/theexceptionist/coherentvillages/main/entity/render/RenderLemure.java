package net.theexceptionist.coherentvillages.main.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.theexceptionist.coherentvillages.main.Resources;
import net.theexceptionist.coherentvillages.main.entity.EntityLemure;
import net.theexceptionist.coherentvillages.main.entity.model.ModelLemure;

public class RenderLemure extends RenderLiving<EntityLemure> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/lemure.png");

	public RenderLemure(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelLemure(), 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLemure entity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}
	
}
