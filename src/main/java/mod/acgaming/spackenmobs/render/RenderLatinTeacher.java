package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityLatinTeacher;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLatinTeacher extends RenderLiving<EntityLatinTeacher>
{
	public static final RenderLatinTeacher.Factory FACTORY = new RenderLatinTeacher.Factory();
	private static final ResourceLocation LATINTEACHER_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/latin_teacher.png");

	public RenderLatinTeacher(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelWitch(0.0F), 0.5F);
		this.addLayer(new LayerHeldItemLatinTeacher(this));
	}

	public ModelWitch getMainModel()
	{
		return (ModelWitch) super.getMainModel();
	}

	public void doRender(EntityLatinTeacher entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		((ModelWitch) this.mainModel).holdingItem = !entity.getHeldItemMainhand().isEmpty();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	protected ResourceLocation getEntityTexture(EntityLatinTeacher entity)
	{
		return LATINTEACHER_TEXTURE;
	}

	public void transformHeldFull3DItemLayer()
	{
		GlStateManager.translate(0.0F, 0.1875F, 0.0F);
	}

	protected void preRenderCallback(EntityLatinTeacher entitylivingbaseIn, float partialTickTime)
	{
		float f = 0.9375F;
		GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
	}

	private static class Factory implements IRenderFactory<EntityLatinTeacher>
	{
		@Override
		public Render<? super EntityLatinTeacher> createRenderFor(RenderManager manager)
		{
			return new RenderLatinTeacher(manager);
		}
	}
}