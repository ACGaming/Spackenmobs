package mod.acgaming.spackenmobs.render;
import mod.acgaming.spackenmobs.entities.EntityIslamist;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import mod.acgaming.spackenmobs.render.RenderSmavaCreeper.Factory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderIslamist extends RenderCreeper
{
    private static final ResourceLocation ISLAMIST_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/islamist.png");
    public static final Factory FACTORY = new Factory();

    public RenderIslamist(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.addLayer(new LayerCreeperCharge(this));
    }
    
    protected ResourceLocation getEntityTexture(EntityCreeper entity)
    {
        return ISLAMIST_TEXTURES;
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