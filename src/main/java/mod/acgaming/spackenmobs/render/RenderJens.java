package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityJens;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJens extends RenderBiped<EntityJens> {
    private static final ResourceLocation JENS_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/jens.png");
    public static final Factory FACTORY = new Factory();

    public RenderJens(RenderManager renderManagerIn) {
	super(renderManagerIn, new ModelBiped(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityJens entity) {
	return JENS_TEXTURE;
    }

    private static class Factory implements IRenderFactory<EntityJens> {
	@Override
	public Render<? super EntityJens> createRenderFor(RenderManager manager) {
	    return new RenderJens(manager);
	}
    }
}