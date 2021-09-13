package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.MrBeanEntity;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMrBeanRenderer<T extends MrBeanEntity, M extends ZombieModel<T>> extends BipedRenderer<T, M>
{
    private static final ResourceLocation MRBEAN_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mr_bean.png");

    protected AbstractMrBeanRenderer(EntityRendererManager p_i50974_1_, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_)
    {
        super(p_i50974_1_, p_i50974_2_, 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
    }

    public ResourceLocation getEntityTexture(MrBeanEntity entity)
    {
        return MRBEAN_TEXTURES;
    }

    protected boolean func_230495_a_(T p_230495_1_)
    {
        return p_230495_1_.isDrowning();
    }
}