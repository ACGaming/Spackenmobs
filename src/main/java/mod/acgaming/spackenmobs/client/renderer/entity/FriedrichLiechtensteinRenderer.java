package mod.acgaming.spackenmobs.client.renderer.entity;

import mod.acgaming.spackenmobs.entity.FriedrichLiechtensteinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriedrichLiechtensteinRenderer extends MobRenderer<FriedrichLiechtensteinEntity, BipedModel<FriedrichLiechtensteinEntity>>
{
	private static final ResourceLocation FRIEDRICH_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/friedrich.png");

	public FriedrichLiechtensteinRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new BipedModel<>(0.0F), 0.25F);
		this.addLayer(new HeldItemLayer<>(this));
	}

	public ResourceLocation getEntityTexture(FriedrichLiechtensteinEntity entity)
	{
		return FRIEDRICH_TEXTURES;
	}
}