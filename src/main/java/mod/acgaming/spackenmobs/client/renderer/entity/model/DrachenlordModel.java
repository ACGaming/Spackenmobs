package mod.acgaming.spackenmobs.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.entity.DrachenlordEntity;

@OnlyIn(Dist.CLIENT)
public class DrachenlordModel<D extends MonsterEntity> extends AbstractZombieModel<DrachenlordEntity>
{
    public DrachenlordModel()
    {
        this(0.0F, false);
    }

    public DrachenlordModel(float modelSize, boolean p_i51066_2_)
    {
        super(modelSize, 0.0F, 64, p_i51066_2_ ? 32 : 64);
    }

    public boolean isAggressive(DrachenlordEntity entityIn)
    {
        return false;
    }
}