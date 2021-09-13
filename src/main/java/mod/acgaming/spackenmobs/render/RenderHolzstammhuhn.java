package mod.acgaming.spackenmobs.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntityHolzstammhuhn;

@SideOnly(Side.CLIENT)
public class RenderHolzstammhuhn extends RenderChicken
{
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation HOLZSTAMMHUHN_TEXTURE = new ResourceLocation(
        "spackenmobs:textures/entities/holzstammhuhn.png");

    public RenderHolzstammhuhn(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityChicken entity)
    {
        return HOLZSTAMMHUHN_TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityHolzstammhuhn>
    {
        @Override
        public Render<? super EntityHolzstammhuhn> createRenderFor(RenderManager manager)
        {
            return new RenderHolzstammhuhn(manager);
        }
    }
}