package mod.acgaming.spackenmobs.client.renderer.entity;

import java.util.Objects;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.acgaming.spackenmobs.client.renderer.entity.model.SchalkerModel;
import mod.acgaming.spackenmobs.entity.SchalkerEntity;

@OnlyIn(Dist.CLIENT)
public class SchalkerRenderer extends MobRenderer<SchalkerEntity, SchalkerModel<SchalkerEntity>>
{
    private static final ResourceLocation SCHALKER_TEXTURES = new ResourceLocation("spackenmobs:textures/entities/schalker.png");

    public SchalkerRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new SchalkerModel<>(), 0.25F);
    }

    public Vector3d getRenderOffset(SchalkerEntity entityIn, float partialTicks)
    {
        int i = entityIn.getClientTeleportInterp();
        if (i > 0 && entityIn.isAttachedToBlock())
        {
            BlockPos blockpos = entityIn.getAttachmentPos();
            BlockPos blockpos1 = entityIn.getOldAttachPos();
            double d0 = (double) ((float) i - partialTicks) / 6.0D;
            d0 = d0 * d0;
            double d1 = (double) (blockpos.getX() - blockpos1.getX()) * d0;
            double d2 = (double) (blockpos.getY() - blockpos1.getY()) * d0;
            double d3 = (double) (blockpos.getZ() - blockpos1.getZ()) * d0;
            return new Vector3d(-d1, -d2, -d3);
        }
        else
        {
            return super.getRenderOffset(entityIn, partialTicks);
        }
    }

    public ResourceLocation getTextureLocation(SchalkerEntity entity)
    {
        return SCHALKER_TEXTURES;
    }

    public boolean shouldRender(SchalkerEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ)
    {
        if (super.shouldRender(livingEntityIn, camera, camX, camY, camZ))
        {
            return true;
        }
        else
        {
            if (livingEntityIn.getClientTeleportInterp() > 0 && livingEntityIn.isAttachedToBlock())
            {
                Vector3d vector3d = Vector3d.atLowerCornerOf(Objects.requireNonNull(livingEntityIn.getAttachmentPos()));
                Vector3d vector3d1 = Vector3d.atLowerCornerOf(livingEntityIn.getOldAttachPos());
                return camera.isVisible(new AxisAlignedBB(vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y, vector3d.z));
            }

            return false;
        }
    }

    protected void setupRotations(SchalkerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw + 180.0F, partialTicks);
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        matrixStackIn.mulPose(entityLiving.getAttachmentFacing().getOpposite().getRotation());
        matrixStackIn.translate(0.0D, -0.5D, 0.0D);
    }
}