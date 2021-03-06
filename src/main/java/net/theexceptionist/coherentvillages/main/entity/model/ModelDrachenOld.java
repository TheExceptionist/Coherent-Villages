package net.theexceptionist.coherentvillages.main.entity.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.theexceptionist.coherentvillages.main.entity.EntityDrachen;

public class ModelDrachenOld extends ModelBase {	
    public ModelRenderer body;
    public ModelRenderer neck;
    public ModelRenderer boneR1;
    public ModelRenderer tail;
    public ModelRenderer thighR1;
    public ModelRenderer thighR2;
    public ModelRenderer boneL1;
    public ModelRenderer thighL1;
    public ModelRenderer thighL2;
    public ModelRenderer head;
    public ModelRenderer lipTop;
    public ModelRenderer lipBottom;
    public ModelRenderer boneR2;
    public ModelRenderer wingR1;
    public ModelRenderer wingR2;
    public ModelRenderer footR1;
    public ModelRenderer footR2;
    public ModelRenderer boneL2;
    public ModelRenderer wingL1;
    public ModelRenderer wingL2;
    public ModelRenderer footL1;
    public ModelRenderer footL2;

    public ModelDrachenOld() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.footL2 = new ModelRenderer(this, 128, 128);
        this.footL2.mirror = true;
        this.footL2.setRotationPoint(4.0F, 10.0F, -6.0F);
        this.footL2.addBox(-4.0F, 0.0F, -6.0F, 8, 4, 12, 0.0F);
        this.setRotateAngle(footL2, -0.36425021489121656F, 0.0F, 0.0F);
        this.wingL1 = new ModelRenderer(this, 32, 148);
        this.wingL1.mirror = true;
        this.wingL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingL1.addBox(0.0F, 0.0F, 0.0F, 32, 1, 32, 0.0F);
        this.thighR1 = new ModelRenderer(this, 190, 64);
        this.thighR1.setRotationPoint(-12.0F, 8.0F, 12.0F);
        this.thighR1.addBox(-6.0F, -4.0F, -6.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(thighR1, 0.36425021489121656F, 0.5918411493512771F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-12.0F, -12.0F, -32.0F, 24, 24, 64, 0.0F);
        this.thighL2 = new ModelRenderer(this, 190, 32);
        this.thighL2.mirror = true;
        this.thighL2.setRotationPoint(12.0F, 4.0F, -18.0F);
        this.thighL2.addBox(0.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.setRotateAngle(thighL2, 0.40980330836826856F, -0.40980330836826856F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 96);
        this.neck.setRotationPoint(0.0F, 0.0F, -32.0F);
        this.neck.addBox(-6.0F, -6.0F, -12.0F, 12, 12, 24, 0.0F);
        this.lipBottom = new ModelRenderer(this, 0, 148);
        this.lipBottom.setRotationPoint(0.0F, 2.0F, -8.0F);
        this.lipBottom.addBox(-5.0F, 0.0F, -12.0F, 10, 4, 8, 0.0F);
        this.lipTop = new ModelRenderer(this, 0, 148);
        this.lipTop.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.lipTop.addBox(-5.0F, 0.0F, -8.0F, 10, 4, 8, 0.0F);
        this.wingR1 = new ModelRenderer(this, 32, 148);
        this.wingR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingR1.addBox(-32.0F, 0.0F, 0.0F, 32, 1, 32, 0.0F);
        this.footR2 = new ModelRenderer(this, 128, 128);
        this.footR2.setRotationPoint(-3.0F, 10.0F, -4.0F);
        this.footR2.addBox(-5.0F, 0.0F, -8.0F, 8, 4, 12, 0.0F);
        this.setRotateAngle(footR2, -0.36425021489121656F, 0.0F, 0.0F);
        this.wingR2 = new ModelRenderer(this, 16, 190);
        this.wingR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingR2.addBox(0.0F, 0.0F, 0.0F, 30, 1, 32, 0.0F);
        this.boneR1 = new ModelRenderer(this, 128, 0);
        this.boneR1.setRotationPoint(-12.0F, -12.0F, -10.0F);
        this.boneR1.addBox(-30.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(boneR1, -0.136659280431156F, 0.31869712141416456F, 0.40980330836826856F);
        this.thighR2 = new ModelRenderer(this, 190, 32);
        this.thighR2.setRotationPoint(-12.0F, 4.0F, -18.0F);
        this.thighR2.addBox(-8.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.setRotateAngle(thighR2, 0.5009094953223726F, 0.27314402793711257F, 0.0F);
        this.wingL2 = new ModelRenderer(this, 16, 190);
        this.wingL2.mirror = true;
        this.wingL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingL2.addBox(0.0F, 0.0F, 0.0F, 30, 1, 32, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.head.addBox(-8.0F, -10.0F, -12.0F, 16, 16, 12, 0.0F);
        this.footL1 = new ModelRenderer(this, 180, 128);
        this.footL1.mirror = true;
        this.footL1.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.footL1.addBox(-6.0F, 0.0F, -10.0F, 12, 4, 16, 0.0F);
        this.setRotateAngle(footL1, -0.36425021489121656F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 128, 190);
        this.tail.setRotationPoint(0.0F, 0.0F, 32.0F);
        this.tail.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 48, 0.0F);
        this.footR1 = new ModelRenderer(this, 180, 128);
        this.footR1.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.footR1.addBox(-6.0F, 0.0F, -10.0F, 12, 4, 16, 0.0F);
        this.setRotateAngle(footR1, -0.36425021489121656F, 0.0F, 0.0F);
        this.thighL1 = new ModelRenderer(this, 190, 64);
        this.thighL1.mirror = true;
        this.thighL1.setRotationPoint(12.0F, 8.0F, 12.0F);
        this.thighL1.addBox(-6.0F, -4.0F, -6.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(thighL1, 0.27314402793711257F, -0.31869712141416456F, 0.0F);
        this.boneL1 = new ModelRenderer(this, 128, 0);
        this.boneL1.mirror = true;
        this.boneL1.setRotationPoint(12.0F, -12.0F, -10.0F);
        this.boneL1.addBox(0.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(boneL1, -0.091106186954104F, -0.40980330836826856F, -0.5009094953223726F);
        this.boneR2 = new ModelRenderer(this, 128, 0);
        this.boneR2.setRotationPoint(-32.0F, 0.0F, 0.0F);
        this.boneR2.addBox(-3.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(boneR2, 0.0F, 0.0F, 1.6845917940249266F);
        this.boneL2 = new ModelRenderer(this, 128, 0);
        this.boneL2.mirror = true;
        this.boneL2.setRotationPoint(32.0F, 0.0F, 0.0F);
        this.boneL2.addBox(-3.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(boneL2, 0.0F, 0.0F, 1.6845917940249266F);
        this.thighL2.addChild(this.footL2);
        this.boneL1.addChild(this.wingL1);
        this.body.addChild(this.thighR1);
        this.body.addChild(this.thighL2);
        this.body.addChild(this.neck);
        this.head.addChild(this.lipBottom);
        this.head.addChild(this.lipTop);
        this.boneR1.addChild(this.wingR1);
        this.thighR2.addChild(this.footR2);
        this.boneR2.addChild(this.wingR2);
        this.body.addChild(this.boneR1);
        this.body.addChild(this.thighR2);
        this.boneL2.addChild(this.wingL2);
        this.neck.addChild(this.head);
        this.thighL1.addChild(this.footL1);
        this.body.addChild(this.tail);
        this.thighR1.addChild(this.footR1);
        this.body.addChild(this.thighL1);
        this.body.addChild(this.boneL1);
        this.boneR1.addChild(this.boneR2);
        this.boneL1.addChild(this.boneL2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
    
    public void renderDrachen(EntityDrachen parEntity, float parTime, float parSwingSuppress, 
            float par4, float parHeadAngleY, float parHeadAngleX, float par7)
      {
    	setRotationAngles(parTime, parSwingSuppress, par4, parHeadAngleY, parHeadAngleX, 
                par7, parEntity);
      }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity parEntity)
    {
    	this.head.rotateAngleX = headPitch * 0.017453292F;
    	this.head.rotateAngleY = netHeadYaw * 0.017453292F;
       
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
