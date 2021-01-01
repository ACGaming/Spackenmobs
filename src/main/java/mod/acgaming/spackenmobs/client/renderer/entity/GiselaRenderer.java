package mod.acgaming.spackenmobs.client.renderer.entity;

import mod.acgaming.spackenmobs.entity.GiselaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiselaRenderer extends MobRenderer<GiselaEntity, BipedModel<GiselaEntity>>
{
	private static final ResourceLocation GISELA_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/gisela.png");

	public GiselaRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new BipedModel<>(0.0F), 0.25F);
	}

	public ResourceLocation getEntityTexture(GiselaEntity entity)
	{
		return GISELA_TEXTURES;
	}
}