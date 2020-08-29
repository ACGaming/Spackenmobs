package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntitySchalker;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSchalker extends ModelBase
{
	public final ModelRenderer base;
	public final ModelRenderer lid;
	public ModelRenderer head;

	public ModelSchalker()
	{
		this.textureHeight = 64;
		this.textureWidth = 64;
		this.lid = new ModelRenderer(this);
		this.base = new ModelRenderer(this);
		this.head = new ModelRenderer(this);
		this.lid.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
		this.lid.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.base.setTextureOffset(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
		this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.head.setTextureOffset(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
		this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
	                              float headPitch, float scaleFactor, Entity entityIn)
	{
		EntitySchalker EntitySchalker = (EntitySchalker) entityIn;
		float f = ageInTicks - EntitySchalker.ticksExisted;
		float f1 = (0.5F + EntitySchalker.getClientPeekAmount(f)) * (float) Math.PI;
		float f2 = -1.0F + MathHelper.sin(f1);
		float f3 = 0.0F;

		if (f1 > (float) Math.PI)
		{
			f3 = MathHelper.sin(ageInTicks * 0.1F) * 0.7F;
		}

		this.lid.setRotationPoint(0.0F, 16.0F + MathHelper.sin(f1) * 8.0F + f3, 0.0F);

		if (EntitySchalker.getClientPeekAmount(f) > 0.3F)
		{
			this.lid.rotateAngleY = f2 * f2 * f2 * f2 * (float) Math.PI * 0.125F;
		} else
		{
			this.lid.rotateAngleY = 0.0F;
		}

		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
	                   float headPitch, float scale)
	{
		this.base.render(scale);
		this.lid.render(scale);
	}
}