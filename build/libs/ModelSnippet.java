public class AwesomeSauceModel extends ModelBiped
{
 private ModelRenderer Spaulder;
 private ModelRenderer Headpart1;
 private ModelRenderer Headpart2;
 private ModelRenderer KneePad;

 public AwesomeSauceModel(float scale)
 {
 super(scale, 0, 64, 64);
 
 textureWidth = 64;
 textureHeight = 64;

 Spaulder = new ModelRenderer(this, 0, 55);
 Spaulder.addBox(-1F, -3F, -2.5F, 5, 4, 5);
 Spaulder.setRotationPoint(5F, 2F, 0F);
 Spaulder.setTextureSize(64, 64);
 Spaulder.mirror = true;
 setRotation(Spaulder, 0F, 0F, 0.3F);
 Headpart1 = new ModelRenderer(this, 0, 41);
 Headpart1.addBox(-6F, -6F, -1F, 2, 4, 4);
 Headpart1.setRotationPoint(0F, 0F, 0F);
 Headpart1.setTextureSize(64, 64);
 Headpart1.mirror = true;
 setRotation(Headpart1, 0F, 0F, 0F);
 Headpart2 = new ModelRenderer(this, 0, 36);
 Headpart2.addBox(-6F, -10F, 2F, 1, 4, 1);
 Headpart2.setRotationPoint(0F, 0F, 0F);
 Headpart2.setTextureSize(64, 64);
 Headpart2.mirror = true;
 setRotation(Headpart2, 0F, 0F, 0F);
 KneePad = new ModelRenderer(this, 0, 50);
 KneePad.addBox(-2F, 4F, -3F, 4, 2, 1);
 KneePad.setRotationPoint(2F, 12F, 0F);
 KneePad.setTextureSize(64, 64);
 KneePad.mirror = true;
 setRotation(KneePad, 0F, 0F, 0F);

 bipedLeftArm.addChild(Spaulder);
 bipedLeftLeg.addChild(KneePad);
 bipedHead.addChild(Headpart1);
 bipedHead.addChild(Headpart2);
 }

 public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
 {
 super.render(entity, f, f1, f2, f3, f4, f5);
 setRotationAngles(f, f1, f2, f3, f4, f5, entity);
 Spaulder.render(f5);
 Headpart1.render(f5);
 Headpart2.render(f5);
 KneePad.render(f5);
 }

 private void setRotation(ModelRenderer model, float x, float y, float z)
 {
 model.rotateAngleX = x;
 model.rotateAngleY = y;
 model.rotateAngleZ = z;
 }
}