package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityIslamist;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIslamist extends RenderCreeper
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation ISLAMIST_TEXTURE = new ResourceLocation(
			"spackenmobs:textures/entities/islamist.png");

	public RenderIslamist(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new LayerCreeperCharge(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity)
	{
		return ISLAMIST_TEXTURE;
	}

	public static class Factory implements IRenderFactory<EntityIslamist>
	{
		@Override
		public Render<? super EntityIslamist> createRenderFor(RenderManager manager)
		{
			return new RenderIslamist(manager);
		}
	}
}