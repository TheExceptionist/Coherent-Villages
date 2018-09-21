package net.theexceptionist.coherentvillages.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.main.Resources;

@SideOnly(Side.CLIENT)
public class RenderVillagerCreeperHunter extends RenderLiving<EntityVillager>
{
    /*private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
    private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
    private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
    private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
    private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
    private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");
*/
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTURE = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREA = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier_a.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREB = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier_b.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREC = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier_c.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTURED = new ResourceLocation(Resources.MODID,"textures/entity/villager/soldier_d.png");
	private static final ResourceLocation VILLAGER_KNIGHT_TEXTURE = new ResourceLocation(Resources.MODID,"textures/entity/villager/captain_a.png");
	private static final ResourceLocation VILLAGER_SOLDIER_TEXTUREX = new ResourceLocation(Resources.MODID,"textures/entity/villager/captain_a.png");
	
	//private int type = 0;
	
    public RenderVillagerCreeperHunter(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelVillagerSoldier(0.0F), 0.5F);
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerHeldItem(this));
    }

    public ModelVillagerSoldier getMainModel()
    {
        return (ModelVillagerSoldier)super.getMainModel();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVillager entity)
    {
    	String name = "";
    	EntityVillagerCreeperHunter soldier = null;
    	if(entity instanceof EntityVillagerCreeperHunter){
    		soldier = ((EntityVillagerCreeperHunter)entity);
    		
    		if(!soldier.isHasEquip()){
    			soldier.spawnEquipment();
    		}
    		//soldier.initSpecialAI();//.setArmor();
    		//soldier.applyEntityAttributesAgain();
    		//System.out.println(entity+" 2 "+name+" : "+type);
    	}

	    return VILLAGER_SOLDIER_TEXTUREX;// = new ResourceLocation(Resources.MODID,"textures/entity/villager/captain_a.png");
    	
    	//String n =
        
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
