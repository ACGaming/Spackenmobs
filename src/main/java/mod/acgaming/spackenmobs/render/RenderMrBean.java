package mod.acgaming.spackenmobs.render;
import mod.acgaming.spackenmobs.entities.EntityMrBean;
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

@SideOnly(Side.CLIENT)
public class RenderMrBean extends RenderZombie
{
    private static final ResourceLocation MRBEAN_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mr_bean.png");
    public static final Factory FACTORY = new Factory();

    public RenderMrBean(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }
    
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return MRBEAN_TEXTURES;
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