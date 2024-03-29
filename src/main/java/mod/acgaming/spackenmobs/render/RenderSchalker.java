package mod.acgaming.spackenmobs.render;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.entities.EntitySchalker;

@SideOnly(Side.CLIENT)
public class RenderSchalker extends RenderLiving<EntitySchalker>
{
    public static final ResourceLocation[] SCHALKER_TEXTURE = new ResourceLocation[] {
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png"),
        new ResourceLocation("spackenmobs:textures/entities/schalker.png")};
    public static final Factory FACTORY = new Factory();

    public RenderSchalker(RenderManager p_i47194_1_)
    {
        super(p_i47194_1_, new ModelSchalker(), 0.0F);
        this.addLayer(new RenderSchalker.HeadLayer());
    }

    @Override
    public ModelSchalker getMainModel()
    {
        return (ModelSchalker) super.getMainModel();
    }

    @Override
    protected void applyRotations(EntitySchalker entityLiving, float p_77043_2_, float rotationYaw,
                                  float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        switch (entityLiving.getAttachmentFacing())
        {
            case DOWN:
            default:
                break;
            case EAST:
                GlStateManager.translate(0.5F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case WEST:
                GlStateManager.translate(-0.5F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 0.5F, -0.5F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 0.5F, 0.5F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected void preRenderCallback(EntitySchalker entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.999F;
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
    }

    @Override
    public boolean shouldRender(EntitySchalker livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
        if (super.shouldRender(livingEntity, camera, camX, camY, camZ))
        {
            return true;
        }
        else
        {
            if (livingEntity.getClientTeleportInterp() > 0 && livingEntity.isAttachedToBlock())
            {
                BlockPos blockpos = livingEntity.getOldAttachPos();
                BlockPos blockpos1 = livingEntity.getAttachmentPos();
                Vec3d vec3d = new Vec3d(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
                Vec3d vec3d1 = new Vec3d(blockpos.getX(), blockpos.getY(), blockpos.getZ());

                return camera.isBoundingBoxInFrustum(
                    new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z));
            }

            return false;
        }
    }

    @Override
    public void doRender(EntitySchalker entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        int i = entity.getClientTeleportInterp();

        if (i > 0 && entity.isAttachedToBlock())
        {
            BlockPos blockpos = entity.getAttachmentPos();
            BlockPos blockpos1 = entity.getOldAttachPos();
            double d0 = (i - partialTicks) / 6.0D;
            d0 = d0 * d0;
            double d1 = (blockpos.getX() - blockpos1.getX()) * d0;
            double d2 = (blockpos.getY() - blockpos1.getY()) * d0;
            double d3 = (blockpos.getZ() - blockpos1.getZ()) * d0;
            super.doRender(entity, x - d1, y - d2, z - d3, entityYaw, partialTicks);
        }
        else
        {
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySchalker entity)
    {
        return SCHALKER_TEXTURE[entity.getColor().getMetadata()];
    }

    public static class Factory implements IRenderFactory<EntitySchalker>
    {
        @Override
        public Render<? super EntitySchalker> createRenderFor(RenderManager manager)
        {
            return new RenderSchalker(manager);
        }
    }

    @SideOnly(Side.CLIENT)
    class HeadLayer implements LayerRenderer<EntitySchalker>
    {
        private HeadLayer()
        {
        }

        @Override
        public void doRenderLayer(EntitySchalker entitylivingbaseIn, float limbSwing, float limbSwingAmount,
                                  float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
        {
            GlStateManager.pushMatrix();

            switch (entitylivingbaseIn.getAttachmentFacing())
            {
                case DOWN:
                default:
                    break;
                case EAST:
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(1.0F, -1.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case WEST:
                    GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(-1.0F, -1.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case NORTH:
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -1.0F, -1.0F);
                    break;
                case SOUTH:
                    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -1.0F, 1.0F);
                    break;
                case UP:
                    GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -2.0F, 0.0F);
            }

            ModelRenderer modelrenderer = RenderSchalker.this.getMainModel().head;
            modelrenderer.rotateAngleY = netHeadYaw * 0.017453292F;
            modelrenderer.rotateAngleX = headPitch * 0.017453292F;
            RenderSchalker.this
                .bindTexture(RenderSchalker.SCHALKER_TEXTURE[entitylivingbaseIn.getColor().getMetadata()]);
            modelrenderer.render(scale);
            GlStateManager.popMatrix();
        }

        @Override
        public boolean shouldCombineTextures()
        {
            return false;
        }
    }
}