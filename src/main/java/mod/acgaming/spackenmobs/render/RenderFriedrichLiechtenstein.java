package mod.acgaming.spackenmobs.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntityFriedrichLiechtenstein;

@SideOnly(Side.CLIENT)
public class RenderFriedrichLiechtenstein extends RenderBiped<EntityFriedrichLiechtenstein>
{
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation FRIEDRICH_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/friedrich.png");

    public RenderFriedrichLiechtenstein(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.25F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFriedrichLiechtenstein entity)
    {
        return FRIEDRICH_TEXTURE;
    }

    private static class Factory implements IRenderFactory<EntityFriedrichLiechtenstein>
    {
        @Override
        public Render<? super EntityFriedrichLiechtenstein> createRenderFor(RenderManager manager)
        {
            return new RenderFriedrichLiechtenstein(manager);
        }
    }
}