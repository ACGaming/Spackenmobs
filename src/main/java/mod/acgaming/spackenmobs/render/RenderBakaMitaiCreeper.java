package mod.acgaming.spackenmobs.render;

import mod.acgaming.spackenmobs.entities.EntityBakaMitaiCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBakaMitaiCreeper extends RenderCreeper {
    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation CREEPER_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper.png");

    public RenderBakaMitaiCreeper(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.addLayer(new LayerCreeperCharge(this));
    }

    protected int getColorMultiplier(EntityBakaMitaiCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime) {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

        if((int)(f * 10.0F) % 2 == 0) {
            return 0;
        }else {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCreeper entity) {
        return CREEPER_TEXTURE;
    }

    protected void preRenderCallback(EntityBakaMitaiCreeper entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }

    public static class Factory implements IRenderFactory<EntityBakaMitaiCreeper> {
        @Override
        public Render<? super EntityBakaMitaiCreeper> createRenderFor(RenderManager manager) {
            return new RenderBakaMitaiCreeper(manager);
        }
    }
}