package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.Spackenmobs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = Spackenmobs.MODID)
public class RegHandlerModels
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		registerModel(ModItems.RAM, 0);
		registerModel(ModItems.RAM_ON_A_STICK, 0);
		registerModel(ModItems.SURSTROEMMING, 0);
	}

	private static void registerModel(Item item, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}