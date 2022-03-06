package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.MarcellDAvisEntity;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMarcellDAvisRenderer<T extends MarcellDAvisEntity, M extends ZombieModel<T>> extends BipedRenderer<T, M>
{
    private static final ResourceLocation MARCELLDAVIS_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/marcell_davis.png");

    protected AbstractMarcellDAvisRenderer(EntityRendererManager p_i50974_1_, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_)
    {
        super(p_i50974_1_, p_i50974_2_, 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
    }

    public ResourceLocation getTextureLocation(MarcellDAvisEntity entity)
    {
        return MARCELLDAVIS_TEXTURES;
    }

    protected boolean isShaking(T p_230495_1_)
    {
        return p_230495_1_.isUnderWaterConverting();
    }
}