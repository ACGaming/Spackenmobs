package mod.acgaming.spackenmobs.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.acgaming.spackenmobs.client.renderer.entity.model.MZTEWolfModel;
import mod.acgaming.spackenmobs.entity.MZTEWolfEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MZTEWolfCollarLayer extends LayerRenderer<MZTEWolfEntity, MZTEWolfModel<MZTEWolfEntity>>
{
	private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

	public MZTEWolfCollarLayer(IEntityRenderer<MZTEWolfEntity, MZTEWolfModel<MZTEWolfEntity>> rendererIn)
	{
		super(rendererIn);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, MZTEWolfEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible())
		{
			float[] afloat = entitylivingbaseIn.getCollarColor().getColorComponentValues();
			renderCutoutModel(this.getEntityModel(), WOLF_COLLAR, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, afloat[0], afloat[1], afloat[2]);
		}
	}
}