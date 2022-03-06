package mod.acgaming.spackenmobs.client.renderer.entity.layer;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.SmavaCreeperEntity;

@OnlyIn(Dist.CLIENT)
public class SmavaCreeperChargeLayer extends EnergyLayer<SmavaCreeperEntity, CreeperModel<SmavaCreeperEntity>>
{
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final CreeperModel<SmavaCreeperEntity> creeperModel = new CreeperModel<>(2.0F);

    public SmavaCreeperChargeLayer(IEntityRenderer<SmavaCreeperEntity, CreeperModel<SmavaCreeperEntity>> p_i50947_1_)
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

    protected EntityModel<SmavaCreeperEntity> model()
    {
        return this.creeperModel;
    }
}