package net;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelDragon - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelDragon extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer bodyChild;

    public ModelDragon() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.body = new ModelRenderer(this, 0, 128);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-24.0F, -16.0F, -32.0F, 48, 32, 64, 0.0F);
        this.bodyChild = new ModelRenderer(this, 0, 0);
        this.bodyChild.setRotationPoint(20.0F, 10.0F, -16.0F);
        this.bodyChild.addBox(0.0F, 0.0F, 0.0F, 8, 16, 8, 0.0F);
        this.setRotateAngle(bodyChild, 0.7285004297824331F, 0.0F, 0.0F);
        this.body.addChild(this.bodyChild);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
