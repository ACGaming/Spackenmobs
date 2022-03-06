package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.acgaming.spackenmobs.client.renderer.entity.layer.MZTEWolfCollarLayer;
import mod.acgaming.spackenmobs.client.renderer.entity.model.MZTEWolfModel;
import mod.acgaming.spackenmobs.entity.MZTEWolfEntity;

@OnlyIn(Dist.CLIENT)
public class MZTEWolfRenderer extends MobRenderer<MZTEWolfEntity, MZTEWolfModel<MZTEWolfEntity>>
{
    private static final ResourceLocation MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf.png");
    private static final ResourceLocation TAMED_MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf_tame.png");
    private static final ResourceLocation ANGRY_MZTEWOLF_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/mztewolf_angry.png");

    public MZTEWolfRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new MZTEWolfModel<>(), 0.5F);
        this.addLayer(new MZTEWolfCollarLayer(this));
    }

    public void render(MZTEWolfEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        if (entityIn.isWolfWet())
        {
            float f = entityIn.getShadingWhileWet(partialTicks);
            this.model.setColor(f, f, f);
        }

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entityIn.isWolfWet())
        {
            this.model.setColor(1.0F, 1.0F, 1.0F);
        }
    }

    public ResourceLocation getTextureLocation(MZTEWolfEntity entity)
    {
        if (entity.isTame())
        {
            return TAMED_MZTEWOLF_TEXTURES;
        }
        else
        {
            return entity.isAngry() ? ANGRY_MZTEWOLF_TEXTURES : MZTEWOLF_TEXTURES;
        }
    }

    protected float getBob(MZTEWolfEntity livingBase, float partialTicks)
    {
        return livingBase.getTailRotation();
    }
}