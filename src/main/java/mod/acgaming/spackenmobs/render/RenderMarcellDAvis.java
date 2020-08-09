package mod.acgaming.spackenmobs.render;
import mod.acgaming.spackenmobs.entities.EntityMarcellDAvis;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import mod.acgaming.spackenmobs.render.RenderSmavaCreeper.Factory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMarcellDAvis extends RenderZombie
{
    private static final ResourceLocation MARCELLDAVIS_TEXTURE = new ResourceLocation("spackenmobs:textures/entities/marcell_davis.png");
    public static final Factory FACTORY = new Factory();

    public RenderMarcellDAvis(RenderManager renderManagerIn)
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
        return MARCELLDAVIS_TEXTURE;
    }
    
    public static class Factory implements IRenderFactory<EntityMarcellDAvis>
    {
    	@Override
    	public Render<? super EntityMarcellDAvis> createRenderFor(RenderManager manager)
    	{
    		return new RenderMarcellDAvis(manager);
    	}
    }
}