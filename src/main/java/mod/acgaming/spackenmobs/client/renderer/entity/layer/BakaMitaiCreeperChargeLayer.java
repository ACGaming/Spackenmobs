package mod.acgaming.spackenmobs.client.renderer.entity.layer;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.BakaMitaiCreeperEntity;

@OnlyIn(Dist.CLIENT)
public class BakaMitaiCreeperChargeLayer extends EnergyLayer<BakaMitaiCreeperEntity, CreeperModel<BakaMitaiCreeperEntity>>
{
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final CreeperModel<BakaMitaiCreeperEntity> creeperModel = new CreeperModel<>(2.0F);

    public BakaMitaiCreeperChargeLayer(IEntityRenderer<BakaMitaiCreeperEntity, CreeperModel<BakaMitaiCreeperEntity>> p_i50947_1_)
    {
        super(p_i50947_1_);
    }

    protected float xOffset(float p_225634_1_)
    {
        return p_225634_1_ * 0.01F;
    }

    protected ResourceLocation getTextureLocation()
    {
        return LIGHTNING_TEXTURE;
    }

    protected EntityModel<BakaMitaiCreeperEntity> model()
    {
        return this.creeperModel;
    }
}