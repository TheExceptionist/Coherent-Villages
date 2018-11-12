package net.theexceptionist.coherentvillages.main.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelDrachen - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelDrachen extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer Neck_b;
    public ModelRenderer Leg_right_back_a;
    public ModelRenderer Leg_left_front_a;
    public ModelRenderer Wing_bone_right_a;
    public ModelRenderer Leg_right_front_a;
    public ModelRenderer tail;
    public ModelRenderer Leg_left_back_a;
    public ModelRenderer Wing_bone_left_a;
    public ModelRenderer Leg_right_back_b;
    public ModelRenderer Leg_left_front_b;
    public ModelRenderer Wing_right_a;
    public ModelRenderer Wing_bone_right_b;
    public ModelRenderer Wing_left_b;
    public ModelRenderer Leg_right_front_b;
    public ModelRenderer Leg_left_back_b;
    public ModelRenderer Wing_left_a;
    public ModelRenderer Wing_bone_left_b;
    public ModelRenderer Wing_left_b_1;
    public ModelRenderer Neck_a;
    public ModelRenderer Head;
    public ModelRenderer Mouth_bottom;
    public ModelRenderer Mouth_top;
    public ModelRenderer Horn_a;
    public ModelRenderer Horn_b;

    public ModelDrachen() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.Leg_right_back_b = new ModelRenderer(this, 180, 128);
        this.Leg_right_back_b.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Leg_right_back_b.addBox(-6.0F, 0.0F, -10.0F, 12, 4, 16, 0.0F);
        this.setRotateAngle(Leg_right_back_b, -0.36425021489121656F, 0.0F, 0.0F);
        this.Wing_left_b = new ModelRenderer(this, 16, 184);
        this.Wing_left_b.mirror = true;
        this.Wing_left_b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing_left_b.addBox(0.0F, 0.0F, 0.0F, 30, 1, 32, 0.0F);
        this.Leg_left_back_b = new ModelRenderer(this, 180, 128);
        this.Leg_left_back_b.mirror = true;
        this.Leg_left_back_b.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Leg_left_back_b.addBox(-6.0F, 0.0F, -10.0F, 12, 4, 16, 0.0F);
        this.setRotateAngle(Leg_left_back_b, -0.36425021489121656F, 0.0F, 0.0F);
        this.Neck_a = new ModelRenderer(this, 0, 220);
        this.Neck_a.setRotationPoint(0.5F, 1.0F, -30.0F);
        this.Neck_a.addBox(-6.0F, -6.0F, -12.0F, 11, 10, 24, 0.0F);
        this.Leg_left_back_a = new ModelRenderer(this, 190, 64);
        this.Leg_left_back_a.mirror = true;
        this.Leg_left_back_a.setRotationPoint(12.0F, 8.0F, 12.0F);
        this.Leg_left_back_a.addBox(-6.0F, -4.0F, -6.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Leg_left_back_a, 0.27314402793711257F, -0.31869712141416456F, 0.0F);
        this.Wing_bone_left_a = new ModelRenderer(this, 128, 0);
        this.Wing_bone_left_a.mirror = true;
        this.Wing_bone_left_a.setRotationPoint(12.0F, -12.0F, -10.0F);
        this.Wing_bone_left_a.addBox(0.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(Wing_bone_left_a, -0.091106186954104F, -0.40980330836826856F, -0.5009094953223726F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-12.0F, -12.0F, -32.0F, 24, 24, 64, 0.0F);
        this.Leg_right_back_a = new ModelRenderer(this, 190, 64);
        this.Leg_right_back_a.setRotationPoint(-12.0F, 8.0F, 12.0F);
        this.Leg_right_back_a.addBox(-6.0F, -4.0F, -6.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Leg_right_back_a, 0.36425021489121656F, 0.5918411493512771F, 0.0F);
        this.Wing_left_a = new ModelRenderer(this, 32, 148);
        this.Wing_left_a.mirror = true;
        this.Wing_left_a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing_left_a.addBox(0.0F, 0.0F, 0.0F, 32, 1, 32, 0.0F);
        this.Horn_b = new ModelRenderer(this, 0, 45);
        this.Horn_b.setRotationPoint(-4.0F, -8.0F, 0.0F);
        this.Horn_b.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 12, 0.0F);
        this.setRotateAngle(Horn_b, 0.7853981633974483F, -0.2617993877991494F, 0.0F);
        this.Neck_b = new ModelRenderer(this, 0, 96);
        this.Neck_b.setRotationPoint(0.0F, 0.0F, -25.5F);
        this.Neck_b.addBox(-6.0F, -6.0F, -24.0F, 12, 12, 24, 0.0F);
        this.Leg_left_front_b = new ModelRenderer(this, 128, 128);
        this.Leg_left_front_b.mirror = true;
        this.Leg_left_front_b.setRotationPoint(4.0F, 10.0F, -6.0F);
        this.Leg_left_front_b.addBox(-4.0F, 0.0F, -6.0F, 8, 4, 12, 0.0F);
        this.setRotateAngle(Leg_left_front_b, -0.36425021489121656F, 0.0F, 0.0F);
        this.Wing_left_b_1 = new ModelRenderer(this, 16, 184);
        this.Wing_left_b_1.mirror = true;
        this.Wing_left_b_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing_left_b_1.addBox(0.0F, 0.0F, 0.0F, 30, 1, 32, 0.0F);
        this.Leg_left_front_a = new ModelRenderer(this, 190, 32);
        this.Leg_left_front_a.mirror = true;
        this.Leg_left_front_a.setRotationPoint(12.0F, 4.0F, -18.0F);
        this.Leg_left_front_a.addBox(0.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.setRotateAngle(Leg_left_front_a, 0.40980330836826856F, -0.40980330836826856F, 0.0F);
        this.Wing_right_a = new ModelRenderer(this, 32, 148);
        this.Wing_right_a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing_right_a.addBox(-32.0F, 0.0F, 0.0F, 32, 1, 32, 0.0F);
        this.Horn_a = new ModelRenderer(this, 0, 45);
        this.Horn_a.setRotationPoint(4.0F, -8.0F, 0.0F);
        this.Horn_a.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 12, 0.0F);
        this.setRotateAngle(Horn_a, 0.7853981633974483F, 0.2617993877991494F, 0.0F);
        this.Leg_right_front_b = new ModelRenderer(this, 128, 128);
        this.Leg_right_front_b.setRotationPoint(-3.0F, 10.0F, -4.0F);
        this.Leg_right_front_b.addBox(-5.0F, 0.0F, -8.0F, 8, 4, 12, 0.0F);
        this.setRotateAngle(Leg_right_front_b, -0.36425021489121656F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.8F, -12.0F);
        this.Head.addBox(-8.0F, -10.0F, -12.0F, 16, 13, 15, 0.0F);
        this.setRotateAngle(Head, 0.39269908169872414F, 0.0F, 0.0F);
        this.Mouth_top = new ModelRenderer(this, 0, 148);
        this.Mouth_top.setRotationPoint(0.5F, -3.5F, -12.0F);
        this.Mouth_top.addBox(-5.0F, 0.0F, -10.0F, 8, 4, 10, 0.0F);
        this.Wing_bone_left_b = new ModelRenderer(this, 128, 0);
        this.Wing_bone_left_b.mirror = true;
        this.Wing_bone_left_b.setRotationPoint(32.0F, 0.0F, 0.0F);
        this.Wing_bone_left_b.addBox(-3.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(Wing_bone_left_b, 0.0F, 0.0F, 1.6845917940249266F);
        this.Wing_bone_right_a = new ModelRenderer(this, 128, 0);
        this.Wing_bone_right_a.setRotationPoint(-12.0F, -12.0F, -10.0F);
        this.Wing_bone_right_a.addBox(-30.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(Wing_bone_right_a, -0.136659280431156F, 0.31869712141416456F, 0.40980330836826856F);
        this.Leg_right_front_a = new ModelRenderer(this, 190, 32);
        this.Leg_right_front_a.setRotationPoint(-12.0F, 4.0F, -18.0F);
        this.Leg_right_front_a.addBox(-8.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.setRotateAngle(Leg_right_front_a, 0.5009094953223726F, 0.27314402793711257F, 0.0F);
        this.tail = new ModelRenderer(this, 128, 190);
        this.tail.setRotationPoint(0.0F, 0.0F, 32.0F);
        this.tail.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 48, 0.0F);
        this.Wing_bone_right_b = new ModelRenderer(this, 128, 0);
        this.Wing_bone_right_b.setRotationPoint(-32.0F, 0.0F, 0.0F);
        this.Wing_bone_right_b.addBox(-3.0F, -3.0F, -3.0F, 32, 6, 6, 0.0F);
        this.setRotateAngle(Wing_bone_right_b, 0.0F, 0.0F, 1.6845917940249266F);
        this.Mouth_bottom = new ModelRenderer(this, 0, 164);
        this.Mouth_bottom.setRotationPoint(1.0F, -0.85F, -9.0F);
        this.Mouth_bottom.addBox(-5.0F, 0.0F, -12.0F, 7, 4, 10, 0.0F);
        this.Leg_right_back_a.addChild(this.Leg_right_back_b);
        this.Wing_bone_right_b.addChild(this.Wing_left_b);
        this.Leg_left_back_a.addChild(this.Leg_left_back_b);
        this.Neck_b.addChild(this.Neck_a);
        this.body.addChild(this.Leg_left_back_a);
        this.body.addChild(this.Wing_bone_left_a);
        this.body.addChild(this.Leg_right_back_a);
        this.Wing_bone_left_a.addChild(this.Wing_left_a);
        this.Head.addChild(this.Horn_b);
        this.Leg_left_front_a.addChild(this.Leg_left_front_b);
        this.Wing_bone_left_b.addChild(this.Wing_left_b_1);
        this.body.addChild(this.Leg_left_front_a);
        this.Wing_bone_right_a.addChild(this.Wing_right_a);
        this.Head.addChild(this.Horn_a);
        this.Leg_right_front_a.addChild(this.Leg_right_front_b);
        this.Neck_a.addChild(this.Head);
        this.Head.addChild(this.Mouth_top);
        this.Wing_bone_left_a.addChild(this.Wing_bone_left_b);
        this.body.addChild(this.Wing_bone_right_a);
        this.body.addChild(this.Leg_right_front_a);
        this.body.addChild(this.tail);
        this.Wing_bone_right_a.addChild(this.Wing_bone_right_b);
        this.Head.addChild(this.Mouth_bottom);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
        this.Neck_b.render(f5);
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
