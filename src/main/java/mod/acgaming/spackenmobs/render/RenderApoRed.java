package mod.acgaming.spackenmobs.render;
import mod.acgaming.spackenmobs.entities.EntityApoRed;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderApoRed extends RenderSkeleton
{
    private static final ResourceLocation APORED_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/apored.png");
    public static final Factory FACTORY = new Factory();

    public RenderApoRed(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelSkeleton(0.5F, true);
                this.modelArmor = new ModelSkeleton(1.0F, true);
            }
        });
    }
    
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
    
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity)
    {
        return APORED_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityApoRed>
    {
    	@Override
    	public Render<? super EntityApoRed> createRenderFor(RenderManager manager)
    	{
    		return new RenderApoRed(manager);
    	}
    }
}