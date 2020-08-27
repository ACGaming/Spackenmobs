package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityWolfMZTE;
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
public class RenderWolfMZTE extends RenderWolf
{
	public static class Factory implements IRenderFactory<EntityWolfMZTE>
	{
		@Override
		public Render<? super EntityWolfMZTE> createRenderFor(RenderManager manager)
		{
			return new RenderWolfMZTE(manager);
		}
	}

	private static final ResourceLocation WOLFMZTE_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/wolfmzte.png");
	private static final ResourceLocation TAMED_WOLFMZTE_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/wolfmzte_tame.png");
	private static final ResourceLocation ANRGY_WOLFMZTE_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/wolfmzte_angry.png");

	public static final Factory FACTORY = new Factory();

	public RenderWolfMZTE(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new LayerWolfCollar(this));
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
			return TAMED_WOLFMZTE_TEXTURE;
		}
		else
		{
			return entity.isAngry() ? ANRGY_WOLFMZTE_TEXTURE : WOLFMZTE_TEXTURE;
		}
	}

	@Override
	protected float handleRotationFloat(EntityWolf livingBase, float partialTicks)
	{
		return livingBase.getTailRotation();
	}
}