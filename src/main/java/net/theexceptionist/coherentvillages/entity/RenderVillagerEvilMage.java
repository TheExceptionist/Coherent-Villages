package net.theexceptionist.coherentvillages.entity;

	import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.entity.soldier.ModelVillagerSoldier;
import net.theexceptionist.coherentvillages.main.Resources;

	@SideOnly(Side.CLIENT)
	public class RenderVillagerEvilMage  extends  RenderLiving<EntityVillager>
	{
		private static final ResourceLocation VILLAGER_SOLDIER_TEXTURE = new ResourceLocation(Resources.MODID,"textures/entity/villager/mage_e.png");
		
		public RenderVillagerEvilMage(RenderManager renderManagerIn)
	    {
	        super(renderManagerIn, new ModelVillagerSoldier(0.0F), 0.5F);
	        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
	        //this.addLayer(new LayerHeldItem(this));
	        /*this.addLayer(new LayerHeldItem(this));
	        {
	            protected void initArmor()
	            {
	                this.modelLeggings = new ModelSkeleton(0.5F, true);
	                this.modelArmor = new ModelSkeleton(1.0F, true);
	            }
	        });*/
	    }

	    public void transformHeldFull3DItemLayer()
	    {
	        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
	    }

	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    /*private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
	    private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
	    private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
	    private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
	    private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
	    private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");
*/
		
		
	    
	   // }

	    public ModelVillagerSoldier getMainModel()
	    {
	        return (ModelVillagerSoldier)super.getMainModel();
	    }

	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    protected ResourceLocation getEntityTexture(EntityVillager entity)
	    {
	        return VILLAGER_SOLDIER_TEXTURE;
	    }
	    /**
	     * Allows the render to do state modifications necessary before the model is rendered.
	     */
	    protected void preRenderCallback(EntityVillager entitylivingbaseIn, float partialTickTime)
	    {
	        float f = 0.9375F;

	        if (entitylivingbaseIn.getGrowingAge() < 0)
	        {
	            f = (float)((double)f * 0.5D);
	            this.shadowSize = 0.25F;
	        }
	        else
	        {
	            this.shadowSize = 0.5F;
	        }

	        GlStateManager.scale(f, f, f);
	    }
	}

