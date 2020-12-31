package mod.acgaming.spackenmobs.client.renderer.entity;

import mod.acgaming.spackenmobs.entity.DrachenlordEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrachenlordRenderer extends MobRenderer<DrachenlordEntity, BipedModel<DrachenlordEntity>>
{
	private static final ResourceLocation DRACHENLORD_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/drachenlord.png");

	public DrachenlordRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new BipedModel<>(0.0F), 0.5F);
	}

	public ResourceLocation getEntityTexture(DrachenlordEntity entity)
	{
		return DRACHENLORD_TEXTURES;
	}
}