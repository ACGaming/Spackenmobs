package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityMZTEWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMZTEWolf extends RenderWolf
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation MZTEWOLF_TEXTURE = new ResourceLocation(
			"spackenmobs:textures/entities/mztewolf.png");
	private static final ResourceLocation TAMED_MZTEWOLF_TEXTURE = new ResourceLocation(
			"spackenmobs:textures/entities/mztewolf_tame.png");
	private static final ResourceLocation ANRGY_MZTEWOLF_TEXTURE = new ResourceLocation(
			"spackenmobs:textures/entities/mztewolf_angry.png");

	public RenderMZTEWolf(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new LayerWolfCollar(this));
	}

	@Override
	protected float handleRotationFloat(EntityWolf livingBase, float partialTicks)
	{
		return livingBase.getTailRotation();
	}

	@Override
	public void doRender(EntityWolf entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if (entity.isWolfWet())
		{
			float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
			GlStateManager.color(f, f, f);
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWolf entity)
	{
		if (entity.isTamed())
		{
			return TAMED_MZTEWOLF_TEXTURE;
		} else
		{
			return entity.isAngry() ? ANRGY_MZTEWOLF_TEXTURE : MZTEWOLF_TEXTURE;
		}
	}

	public static class Factory implements IRenderFactory<EntityMZTEWolf>
	{
		@Override
		public Render<? super EntityMZTEWolf> createRenderFor(RenderManager manager)
		{
			return new RenderMZTEWolf(manager);
		}
	}
}