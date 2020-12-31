package mod.acgaming.spackenmobs.client;

import mod.acgaming.spackenmobs.client.renderer.entity.*;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import mod.acgaming.spackenmobs.item.CustomSpawnEggItem;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientHandler
{
	public static void doClientStuff(final FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.APORED.get(), ApoRedRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.BAKAMITAI_CREEPER.get(), BakaMitaiCreeperRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.DRACHENLORD.get(), DrachenlordRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get(), FriedrichLiechtensteinRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.HOLZSTAMMHUHN.get(), HolzstammhuhnRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.ISLAMIST.get(), IslamistRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.JENS.get(), JensRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.MARCELLDAVIS.get(), MarcellDAvisRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.MRBEAN.get(), MrBeanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.MZTEWOLF.get(), MZTEWolfRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.SCHALKER.get(), SchalkerRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(SpackenmobsRegistry.SMAVA_CREEPER.get(), SmavaCreeperRenderer::new);
	}

	public static void registerItemColors(final ColorHandlerEvent.Item event)
	{
		ItemColors colors = event.getItemColors();

		for (CustomSpawnEggItem item : CustomSpawnEggItem.getEggs())
		{
			colors.register((p_198141_1_, p_198141_2_) -> item.getColor(p_198141_2_), item);
		}
	}
}