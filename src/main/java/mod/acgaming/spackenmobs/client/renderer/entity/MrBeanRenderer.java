package mod.acgaming.spackenmobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.MrBeanEntity;

@OnlyIn(Dist.CLIENT)
public class MrBeanRenderer extends AbstractMrBeanRenderer<MrBeanEntity, ZombieModel<MrBeanEntity>>
{
    public MrBeanRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
    }
}