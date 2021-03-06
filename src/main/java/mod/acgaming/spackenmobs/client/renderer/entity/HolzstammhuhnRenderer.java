package mod.acgaming.spackenmobs.client.renderer.entity;

import mod.acgaming.spackenmobs.entity.HolzstammhuhnEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HolzstammhuhnRenderer extends MobRenderer<HolzstammhuhnEntity, ChickenModel<HolzstammhuhnEntity>>
{
	private static final ResourceLocation HOLZSTAMMHUHN_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/holzstammhuhn.png");

	public HolzstammhuhnRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ChickenModel<>(), 0.3F);
	}

	public ResourceLocation getEntityTexture(HolzstammhuhnEntity entity)
	{
		return HOLZSTAMMHUHN_TEXTURES;
	}

	protected float handleRotationFloat(HolzstammhuhnEntity livingBase, float partialTicks)
	{
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}