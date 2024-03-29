package mod.acgaming.spackenmobs.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntityMrBean;

@SideOnly(Side.CLIENT)
public class RenderMrBean extends RenderZombie
{
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation MRBEAN_TEXTURE = new ResourceLocation(
        "spackenmobs:textures/entities/mr_bean.png");

    public RenderMrBean(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return MRBEAN_TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityMrBean>
    {
        @Override
        public Render<? super EntityMrBean> createRenderFor(RenderManager manager)
        {
            return new RenderMrBean(manager);
        }
    }
}