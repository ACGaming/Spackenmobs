package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityITbyHF;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderITbyHF extends RenderBiped<EntityITbyHF>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation ITBYHF_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/itbyhf.png");

	public RenderITbyHF(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelBiped(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityITbyHF entity)
	{
		return ITBYHF_TEXTURE;
	}

	private static class Factory implements IRenderFactory<EntityITbyHF>
	{
		@Override
		public Render<? super EntityITbyHF> createRenderFor(RenderManager manager)
		{
			return new RenderITbyHF(manager);
		}
	}
}