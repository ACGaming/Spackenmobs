package mod.acgaming.spackenmobs.render;
import mod.acgaming.spackenmobs.entities.EntitySchalker;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelShulker;
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

@SideOnly(Side.CLIENT)
public class RenderSchalker extends RenderLiving<EntitySchalker>
{
    public static final ResourceLocation[] SCHALKER_TEXTURE = new ResourceLocation[] {new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png"), new ResourceLocation("spackenmobs:textures/entities/schalker.png")};
    public static final Factory FACTORY = new Factory();

    public RenderSchalker(RenderManager p_i47194_1_)
    {
        super(p_i47194_1_, new ModelShulker(), 0.0F);
        this.addLayer(new RenderSchalker.HeadLayer());
    }

    public ModelShulker getMainModel()
    {
        return (ModelShulker)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntitySchalker entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        int i = entity.getClientTeleportInterp();

        if (i > 0 && entity.isAttachedToBlock())
        {
            BlockPos blockpos = entity.getAttachmentPos();
            BlockPos blockpos1 = entity.getOldAttachPos();
            double d0 = (double)((float)i - partialTicks) / 6.0D;
            d0 = d0 * d0;
            double d1 = (double)(blockpos.getX() - blockpos1.getX()) * d0;
            double d2 = (double)(blockpos.getY() - blockpos1.getY()) * d0;
            double d3 = (double)(blockpos.getZ() - blockpos1.getZ()) * d0;
            super.doRender(entity, x - d1, y - d2, z - d3, entityYaw, partialTicks);
        }
        else
        {
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

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
                Vec3d vec3d = new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
                Vec3d vec3d1 = new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());

                if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z)))
                {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySchalker entity)
    {
        return SCHALKER_TEXTURE[entity.getColor().getMetadata()];
    }

    protected void applyRotations(EntitySchalker entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
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

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntitySchalker entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.999F;
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
    }

    @SideOnly(Side.CLIENT)
    class HeadLayer implements LayerRenderer<EntitySchalker>
    {
        private HeadLayer()
        {
        }

        public void doRenderLayer(EntitySchalker entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
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
            RenderSchalker.this.bindTexture(RenderSchalker.SCHALKER_TEXTURE[entitylivingbaseIn.getColor().getMetadata()]);
            modelrenderer.render(scale);
            GlStateManager.popMatrix();
        }

        public boolean shouldCombineTextures()
        {
            return false;
        }
    }
    
    public static class Factory implements IRenderFactory<EntitySchalker>
    {
    	@Override
    	public Render<? super EntitySchalker> createRenderFor(RenderManager manager)
    	{
    		return new RenderSchalker(manager);
    	}
    }
}