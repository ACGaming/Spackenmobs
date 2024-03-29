package mod.acgaming.spackenmobs.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntityJens;

@SideOnly(Side.CLIENT)
public class RenderJens extends RenderBiped<EntityJens>
{
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation JENS_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/jens.png");

    public RenderJens(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityJens entity)
    {
        return JENS_TEXTURE;
    }

    private static class Factory implements IRenderFactory<EntityJens>
    {
        @Override
        public Render<? super EntityJens> createRenderFor(RenderManager manager)
        {
            return new RenderJens(manager);
        }
    }
}