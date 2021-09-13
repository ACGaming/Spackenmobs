package mod.acgaming.spackenmobs.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntityGisela;

@SideOnly(Side.CLIENT)
public class RenderGisela extends RenderBiped<EntityGisela>
{
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation GISELA_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/gisela.png");

    public RenderGisela(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGisela entity)
    {
        return GISELA_TEXTURE;
    }

    private static class Factory implements IRenderFactory<EntityGisela>
    {
        @Override
        public Render<? super EntityGisela> createRenderFor(RenderManager manager)
        {
            return new RenderGisela(manager);
        }
    }
}