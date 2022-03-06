package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.acgaming.spackenmobs.client.renderer.entity.layer.SmavaCreeperChargeLayer;
import mod.acgaming.spackenmobs.entity.SmavaCreeperEntity;

@OnlyIn(Dist.CLIENT)
public class SmavaCreeperRenderer extends MobRenderer<SmavaCreeperEntity, CreeperModel<SmavaCreeperEntity>> implements IEntityRenderer<SmavaCreeperEntity, CreeperModel<SmavaCreeperEntity>>
{
    private static final ResourceLocation SMAVA_CREEPER_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/smava_creeper.png");

    public SmavaCreeperRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new CreeperModel<>(), 0.5F);
        this.addLayer(new SmavaCreeperChargeLayer(this));
    }

    public ResourceLocation getTextureLocation(SmavaCreeperEntity entity)
    {
        return SMAVA_CREEPER_TEXTURES;
    }

    protected float getWhiteOverlayProgress(SmavaCreeperEntity livingEntityIn, float partialTicks)
    {
        float f = livingEntityIn.getCreeperFlashIntensity(partialTicks);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
    }

    protected void scale(SmavaCreeperEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        matrixStackIn.scale(f2, f3, f2);
    }
}