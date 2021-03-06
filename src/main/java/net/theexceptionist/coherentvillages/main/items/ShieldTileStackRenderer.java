package net.theexceptionist.coherentvillages.main.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theexceptionist.coherentvillages.main.Resources;

@SideOnly(Side.CLIENT)
public class ShieldTileStackRenderer extends TileEntityItemStackRenderer 
{
   //public static ShieldTileStackRenderer instance;
   private final ModelModShield modelNordShield = new ModelModShield();
   private final ModelModShield modelRomanShield = new ModelModShield();
   public static final ResourceLocation ROMAN_SHIELD_BASE_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/roman_shield_base.png");
   public static final ResourceLocation[] NORD_SHIELD_TEXTURE = 
	   {
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_base.png"),   
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield.png"),
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_2.png"),
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_3.png"),
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_4.png"),
			   new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_5.png"),

	   };
//   public static final ResourceLocation NORD_SHIELD_BASE_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_base.png");
//   public static final ResourceLocation NORD_SHIELD_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield.png");
//   public static final ResourceLocation NORD_SHIELD_0_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_2.png");
//   public static final ResourceLocation NORD_SHIELD_1_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_3.png");
//   public static final ResourceLocation NORD_SHIELD_2_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_4.png");
//   public static final ResourceLocation NORD_SHIELD_3_TEXTURE = new ResourceLocation(Resources.MODID, "textures/entity/shield/nord_shield_5.png");
//   
    public void renderByItem(ItemStack itemStackIn)
    {
        this.renderByItem(itemStackIn, 1.0F);
    }

    public void renderByItem(ItemStack p_192838_1_, float partialTicks)
    {
        Item item = p_192838_1_.getItem();
        
        for(int i = 0; i < NORD_SHIELD_TEXTURE.length; i++)
        {
	        if(item == ModItems.nordShield[i])
	        {
	        	 Minecraft.getMinecraft().getTextureManager().bindTexture(NORD_SHIELD_TEXTURE[i]);
	
	            GlStateManager.pushMatrix();
	            GlStateManager.scale(1.0F, -1.0F, -1.0F);
	            this.modelNordShield.render();
	            GlStateManager.popMatrix();
	        }
        }
        
        if(item == ModItems.romanShield)
        {
           Minecraft.getMinecraft().getTextureManager().bindTexture(ROMAN_SHIELD_BASE_TEXTURE);

           GlStateManager.pushMatrix();
           GlStateManager.scale(1.0F, -1.0F, -1.0F);
           this.modelRomanShield.render();
           GlStateManager.popMatrix();
       }
        
        super.renderByItem(p_192838_1_, partialTicks);
    }

       /* if (item == Items.BANNER)
        {
            this.banner.setItemValues(p_192838_1_, false);
            TileEntityRendererDispatcher.instance.render(this.banner, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Items.BED)
        {
            this.bed.setItemValues(p_192838_1_);
            TileEntityRendererDispatcher.instance.render(this.bed, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (item == Items.SHIELD)
        {
            if (p_192838_1_.getSubCompound("BlockEntityTag") != null)
            {
                this.banner.setItemValues(p_192838_1_, true);
                Minecraft.getMinecraft().getTextureManager().bindTexture(BannerTextures.SHIELD_DESIGNS.getResourceLocation(this.banner.getPatternResourceLocation(), this.banner.getPatternList(), this.banner.getColorList()));
            }
            else
            {
                Minecraft.getMinecraft().getTextureManager().bindTexture(BannerTextures.SHIELD_BASE_TEXTURE);
            }

            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            this.modelShield.render();
            GlStateManager.popMatrix();
        }
        else if (item == Items.SKULL)
        {
            GameProfile gameprofile = null;

            if (p_192838_1_.hasTagCompound())
            {
                NBTTagCompound nbttagcompound = p_192838_1_.getTagCompound();

                if (nbttagcompound.hasKey("SkullOwner", 10))
                {
                    gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                }
                else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner")))
                {
                    GameProfile gameprofile1 = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                    gameprofile = TileEntitySkull.updateGameprofile(gameprofile1);
                    nbttagcompound.removeTag("SkullOwner");
                    nbttagcompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), gameprofile));
                }
            }

            if (TileEntitySkullRenderer.instance != null)
            {
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                TileEntitySkullRenderer.instance.renderSkull(0.0F, 0.0F, 0.0F, EnumFacing.UP, 180.0F, p_192838_1_.getMetadata(), gameprofile, -1, 0.0F);
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
            }
        }
        else if (item == Item.getItemFromBlock(Blocks.ENDER_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.enderChest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.TRAPPED_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.chestTrap, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (Block.getBlockFromItem(item) instanceof BlockShulkerBox)
        {
            TileEntityRendererDispatcher.instance.render(SHULKER_BOXES[BlockShulkerBox.getColorFromItem(item).getMetadata()], 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (Block.getBlockFromItem(item) != Blocks.CHEST) net.minecraftforge.client.ForgeHooksClient.renderTileItem(p_192838_1_.getItem(), p_192838_1_.getMetadata());
        else
        {
            TileEntityRendererDispatcher.instance.render(this.chestBasic, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
    }

    static
    {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
            SHULKER_BOXES[enumdyecolor.getMetadata()] = new TileEntityShulkerBox(enumdyecolor);
        }

        instance = new TileEntityItemStackRenderer();
    }*/
}
