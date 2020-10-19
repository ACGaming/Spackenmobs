package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.entities.*;
import mod.acgaming.spackenmobs.render.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModEntities
{
	public static void initModels()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityApoRed.class, RenderApoRed.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBakaMitaiCreeper.class, RenderBakaMitaiCreeper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDrachenlord.class, RenderDrachenlord.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFriedrichLiechtenstein.class, RenderFriedrichLiechtenstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHolzstammhuhn.class, RenderHolzstammhuhn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityIslamist.class, RenderIslamist.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityITbyHF.class, RenderITbyHF.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityJens.class, RenderJens.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLatinTeacher.class, RenderLatinTeacher.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMZTEWolf.class, RenderMZTEWolf.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMarcellDAvis.class, RenderMarcellDAvis.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMrBean.class, RenderMrBean.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySchalker.class, RenderSchalker.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySmavaCreeper.class, RenderSmavaCreeper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTileraGhast.class, RenderTileraGhast.FACTORY);
	}
}