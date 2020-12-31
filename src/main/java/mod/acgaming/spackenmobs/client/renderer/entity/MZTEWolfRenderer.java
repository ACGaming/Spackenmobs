package mod.acgaming.spackenmobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.acgaming.spackenmobs.client.renderer.entity.layer.MZTEWolfCollarLayer;
import mod.acgaming.spackenmobs.client.renderer.entity.model.MZTEWolfModel;
import mod.acgaming.spackenmobs.entity.MZTEWolfEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MZTEWolfRenderer extends MobRenderer<MZTEWolfEntity, MZTEWolfModel<MZTEWolfEntity>>
{
	private static final ResourceLocation MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf.png");
	private static final ResourceLocation TAMED_MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf_tame.png");
	private static final ResourceLocation ANGRY_MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf_angry.png");

	public MZTEWolfRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new MZTEWolfModel<>(), 0.5F);
		this.addLayer(new MZTEWolfCollarLayer(this));
	}

	protected float handleRotationFloat(MZTEWolfEntity livingBase, float partialTicks)
	{
		return livingBase.getTailRotation();
	}

	public void render(MZTEWolfEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		if (entityIn.isWolfWet())
		{
			float f = entityIn.getShadingWhileWet(partialTicks);
			this.entityModel.setTint(f, f, f);
		}

		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		if (entityIn.isWolfWet())
		{
			this.entityModel.setTint(1.0F, 1.0F, 1.0F);
		}
	}

	public ResourceLocation getEntityTexture(MZTEWolfEntity entity)
	{
		if (entity.isTamed())
		{
			return TAMED_MZTEWOLF_TEXTURES;
		} else
		{
			return entity.func_233678_J__() ? ANGRY_MZTEWOLF_TEXTURES : MZTEWOLF_TEXTURES;
		}
	}
}