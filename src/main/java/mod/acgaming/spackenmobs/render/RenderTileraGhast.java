package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityTileraGhast;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTileraGhast extends RenderLiving<EntityTileraGhast>
{
	public static final RenderTileraGhast.Factory FACTORY = new RenderTileraGhast.Factory();
	private static final ResourceLocation TILERA_GHAST_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/tilera_ghast.png");
	private static final ResourceLocation TILERA_GHAST_SHOOTING_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/tilera_ghast_shooting.png");

	public RenderTileraGhast(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelGhast(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityTileraGhast entity)
	{
		return entity.isAttacking() ? TILERA_GHAST_SHOOTING_TEXTURE : TILERA_GHAST_TEXTURE;
	}

	protected void preRenderCallback(EntityTileraGhast entitylivingbaseIn, float partialTickTime)
	{
		float f = 1.0F;
		float f1 = 4.5F;
		float f2 = 4.5F;
		GlStateManager.scale(4.5F, 4.5F, 4.5F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private static class Factory implements IRenderFactory<EntityTileraGhast>
	{
		@Override
		public Render<? super EntityTileraGhast> createRenderFor(RenderManager manager)
		{
			return new RenderTileraGhast(manager);
		}
	}
}