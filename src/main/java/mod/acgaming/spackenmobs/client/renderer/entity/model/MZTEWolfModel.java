package mod.acgaming.spackenmobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.MZTEWolfEntity;

@OnlyIn(Dist.CLIENT)
public class MZTEWolfModel<T extends MZTEWolfEntity> extends TintedAgeableModel<T>
{
    private final ModelRenderer head;
    private final ModelRenderer headChild;
    private final ModelRenderer body;
    private final ModelRenderer legBackRight;
    private final ModelRenderer legBackLeft;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer tail;
    private final ModelRenderer tailChild;
    private final ModelRenderer mane;

    public MZTEWolfModel()
    {
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(-1.0F, 13.5F, -7.0F);
        this.headChild = new ModelRenderer(this, 0, 0);
        this.headChild.addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, 0.0F);
        this.head.addChild(this.headChild);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F);
        this.body.setPos(0.0F, 14.0F, 2.0F);
        this.mane = new ModelRenderer(this, 21, 0);
        this.mane.addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, 0.0F);
        this.mane.setPos(-1.0F, 14.0F, 2.0F);
        this.legBackRight = new ModelRenderer(this, 0, 18);
        this.legBackRight.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 18);
        this.legBackLeft.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 18);
        this.legFrontRight.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 18);
        this.legFrontLeft.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.setPos(-1.0F, 12.0F, 8.0F);
        this.tailChild = new ModelRenderer(this, 9, 18);
        this.tailChild.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.tail.addChild(this.tailChild);
        this.headChild.texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.headChild.texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.headChild.texOffs(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, 0.0F);
    }

    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.tail.xRot = ageInTicks;
    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
    {
        if (entityIn.isAngry())
        {
            this.tail.yRot = 0.0F;
        }
        else
        {
            this.tail.yRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        if (entityIn.isInSittingPose())
        {
            this.mane.setPos(-1.0F, 16.0F, -3.0F);
            this.mane.xRot = 1.2566371F;
            this.mane.yRot = 0.0F;
            this.body.setPos(0.0F, 18.0F, 0.0F);
            this.body.xRot = ((float) Math.PI / 4F);
            this.tail.setPos(-1.0F, 21.0F, 6.0F);
            this.legBackRight.setPos(-2.5F, 22.7F, 2.0F);
            this.legBackRight.xRot = ((float) Math.PI * 1.5F);
            this.legBackLeft.setPos(0.5F, 22.7F, 2.0F);
            this.legBackLeft.xRot = ((float) Math.PI * 1.5F);
            this.legFrontRight.xRot = 5.811947F;
            this.legFrontRight.setPos(-2.49F, 17.0F, -4.0F);
            this.legFrontLeft.xRot = 5.811947F;
            this.legFrontLeft.setPos(0.51F, 17.0F, -4.0F);
        }
        else
        {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float) Math.PI / 2F);
            this.mane.setPos(-1.0F, 14.0F, -3.0F);
            this.mane.xRot = this.body.xRot;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
            this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
            this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
            this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);
            this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        this.headChild.zRot = entityIn.getInterestedAngle(partialTick) + entityIn.getShakeAngle(partialTick, 0.0F);
        this.mane.zRot = entityIn.getShakeAngle(partialTick, -0.08F);
        this.body.zRot = entityIn.getShakeAngle(partialTick, -0.16F);
        this.tailChild.zRot = entityIn.getShakeAngle(partialTick, -0.2F);
    }

    protected Iterable<ModelRenderer> headParts()
    {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelRenderer> bodyParts()
    {
        return ImmutableList.of(this.body, this.legBackRight, this.legBackLeft, this.legFrontRight, this.legFrontLeft, this.tail, this.mane);
    }
}