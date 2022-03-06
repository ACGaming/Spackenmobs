package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.JensEntity;

@OnlyIn(Dist.CLIENT)
public class JensRenderer extends MobRenderer<JensEntity, BipedModel<JensEntity>>
{
    private static final ResourceLocation JENS_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/jens.png");

    public JensRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new BipedModel<>(0.0F), 0.25F);
    }

    public ResourceLocation getTextureLocation(JensEntity entity)
    {
        return JENS_TEXTURES;
    }
}