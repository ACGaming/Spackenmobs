package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.DrachenlordEntity;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractDrachenlordRenderer<T extends DrachenlordEntity, M extends ZombieModel<T>> extends BipedRenderer<T, M>
{
    private static final ResourceLocation DRACHENLORD_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/drachenlord.png");

    protected AbstractDrachenlordRenderer(EntityRendererManager p_i50974_1_, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_)
    {
        super(p_i50974_1_, p_i50974_2_, 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
    }

    public ResourceLocation getTextureLocation(DrachenlordEntity entity)
    {
        return DRACHENLORD_TEXTURES;
    }

    protected boolean isShaking(T p_230495_1_)
    {
        return p_230495_1_.isUnderWaterConverting();
    }
}