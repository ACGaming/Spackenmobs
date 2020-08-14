package mod.acgaming.spackenmobs.misc;
import mod.acgaming.spackenmobs.entities.EntityApoRed;
import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import mod.acgaming.spackenmobs.entities.EntityHolzstammhuhn;
import mod.acgaming.spackenmobs.entities.EntityIslamist;
import mod.acgaming.spackenmobs.entities.EntityJens;
import mod.acgaming.spackenmobs.entities.EntityMarcellDAvis;
import mod.acgaming.spackenmobs.entities.EntityMrBean;
import mod.acgaming.spackenmobs.entities.EntitySchalker;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import mod.acgaming.spackenmobs.entities.EntityWolfMZTE;
import mod.acgaming.spackenmobs.render.RenderApoRed;
import mod.acgaming.spackenmobs.render.RenderDrachenlord;
import mod.acgaming.spackenmobs.render.RenderHolzstammhuhn;
import mod.acgaming.spackenmobs.render.RenderIslamist;
import mod.acgaming.spackenmobs.render.RenderJens;
import mod.acgaming.spackenmobs.render.RenderMarcellDAvis;
import mod.acgaming.spackenmobs.render.RenderMrBean;
import mod.acgaming.spackenmobs.render.RenderSchalker;
import mod.acgaming.spackenmobs.render.RenderSmavaCreeper;
import mod.acgaming.spackenmobs.render.RenderWolfMZTE;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModEntities
{
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityApoRed.class, RenderApoRed.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDrachenlord.class, RenderDrachenlord.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityIslamist.class, RenderIslamist.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityJens.class, RenderJens.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMarcellDAvis.class, RenderMarcellDAvis.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMrBean.class, RenderMrBean.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySchalker.class, RenderSchalker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmavaCreeper.class, RenderSmavaCreeper.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityWolfMZTE.class, RenderWolfMZTE.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHolzstammhuhn.class, RenderHolzstammhuhn.FACTORY);
    }
}