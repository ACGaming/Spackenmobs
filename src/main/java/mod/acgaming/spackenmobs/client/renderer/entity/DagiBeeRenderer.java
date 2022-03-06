package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BeeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.DagiBeeEntity;

@OnlyIn(Dist.CLIENT)
public class DagiBeeRenderer extends MobRenderer<DagiBeeEntity, BeeModel<DagiBeeEntity>>
{
    private static final ResourceLocation ANGRY_BEE_TEXTURE = new ResourceLocation("textures/entity/bee/bee_angry.png");
    private static final ResourceLocation ANGRY_NECTAR_BEE_TEXTURE = new ResourceLocation("textures/entity/bee/bee_angry_nectar.png");
    private static final ResourceLocation BEE_TEXTURE = new ResourceLocation("textures/entity/bee/bee.png");
    private static final ResourceLocation NECTAR_BEE_TEXTURE = new ResourceLocation("textures/entity/bee/bee_nectar.png");

    public DagiBeeRenderer(EntityRendererManager p_i226033_1_)
    {
        super(p_i226033_1_, new BeeModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(DagiBeeEntity entity)
    {
        if (entity.isAngry())
        {
            return entity.hasNectar() ? ANGRY_NECTAR_BEE_TEXTURE : ANGRY_BEE_TEXTURE;
        }
        else
        {
            return entity.hasNectar() ? NECTAR_BEE_TEXTURE : BEE_TEXTURE;
        }
    }
}