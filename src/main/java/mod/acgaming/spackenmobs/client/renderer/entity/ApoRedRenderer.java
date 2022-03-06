package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.AbstractApoRedEntity;

@OnlyIn(Dist.CLIENT)
public class ApoRedRenderer extends BipedRenderer<AbstractApoRedEntity, SkeletonModel<AbstractApoRedEntity>>
{
    private static final ResourceLocation APORED_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/apored.png");

    public ApoRedRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new SkeletonModel<>(), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new SkeletonModel(0.5F, true), new SkeletonModel(1.0F, true)));
    }

    public ResourceLocation getTextureLocation(AbstractApoRedEntity entity)
    {
        return APORED_TEXTURES;
    }
}