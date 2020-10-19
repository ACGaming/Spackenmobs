package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityJensWither;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJensWither extends RenderLiving<EntityJensWither>
{
	private static final ResourceLocation INVULNERABLE_JENS_WITHER_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/jens_wither_inv.png");
	private static final ResourceLocation JENS_WITHER_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/jens_wither.png");

	public RenderJensWither(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelWither(0.0F), 1.0F);
		this.addLayer(new LayerJensWitherAura(this));
	}

	protected ResourceLocation getEntityTexture(EntityJensWither entity)
	{
		int i = entity.getInvulTime();
		return i > 0 && (i > 80 || i / 5 % 2 != 1) ? INVULNERABLE_JENS_WITHER_TEXTURE : JENS_WITHER_TEXTURE;
	}

	protected void preRenderCallback(EntityJensWither entitylivingbaseIn, float partialTickTime)
	{
		float f = 2.0F;
		int i = entitylivingbaseIn.getInvulTime();

		if (i > 0)
		{
			f -= ((float) i - partialTickTime) / 220.0F * 0.5F;
		}

		GlStateManager.scale(f, f, f);
	}
}