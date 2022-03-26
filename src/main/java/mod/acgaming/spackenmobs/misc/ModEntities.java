package mod.acgaming.spackenmobs.misc;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

import mod.acgaming.spackenmobs.entities.*;
import mod.acgaming.spackenmobs.render.*;

public class ModEntities
{
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityApoRed.class, RenderApoRed.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBakaMitaiCreeper.class, RenderBakaMitaiCreeper.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDrachenlord.class, RenderDrachenlord.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriedrichLiechtenstein.class, RenderFriedrichLiechtenstein.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGisela.class, RenderGisela.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHolzstammhuhn.class, RenderHolzstammhuhn.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityJens.class, RenderJens.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMZTEWolf.class, RenderMZTEWolf.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMarcellDAvis.class, RenderMarcellDAvis.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMrBean.class, RenderMrBean.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySchalker.class, RenderSchalker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmavaCreeper.class, RenderSmavaCreeper.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTileraGhast.class, RenderTileraGhast.FACTORY);
    }
}