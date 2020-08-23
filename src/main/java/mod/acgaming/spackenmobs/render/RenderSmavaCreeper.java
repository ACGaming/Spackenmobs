package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
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
public class RenderSmavaCreeper extends RenderCreeper
{
	private static final ResourceLocation SMAVA_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/smava_creeper.png");
	public static final Factory FACTORY = new Factory();

	public RenderSmavaCreeper(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new LayerCreeperCharge(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity)
	{
		return SMAVA_TEXTURE;
	}

	public static class Factory implements IRenderFactory<EntitySmavaCreeper>
	{
		@Override
		public Render<? super EntitySmavaCreeper> createRenderFor(RenderManager manager)
		{
			return new RenderSmavaCreeper(manager);
		}
	}
}