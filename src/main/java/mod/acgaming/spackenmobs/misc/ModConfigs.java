package mod.acgaming.spackenmobs.misc;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = "spackenmobs")
@LangKey("spackenmobs.config.title")
public class ModConfigs
{
	@EventBusSubscriber(modid = "spackenmobs")
	private static class EventHandler
	{
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals("spackenmobs"))
			{
				ConfigManager.sync("spackenmobs", Config.Type.INSTANCE);
			}
		}
	}

	@Name("Allow ApoRed to spawn?")
	public static boolean ApoRed_spawn = true;
	@Name("Allow Baka Mitai Creeper to spawn?")
	public static boolean BakaMitaiCreeper_spawn = true;
	@Name("Allow Drachenlord to spawn?")
	public static boolean Drachenlord_spawn = true;
	@Name("Allow Holzstammhuhn to spawn?")
	public static boolean Holzstammhuhn_spawn = true;
	@Name("Allow Islamist to spawn?")
	public static boolean Islamist_spawn = true;
	@Name("Allow Jens to spawn?")
	public static boolean Jens_spawn = true;
	@Name("Allow Marcell D'Avis to spawn?")
	public static boolean MarcellDAvis_spawn = true;
	@Name("Allow Mr. Bean to spawn?")
	public static boolean MrBean_spawn = true;
	@Name("Allow Schalker to spawn?")
	public static boolean Schalker_spawn = true;
	@Name("Allow Smava Creeper to spawn?")
	public static boolean SmavaCreeper_spawn = true;
	@Name("Allow WolfMZTE to spawn?")
	public static boolean WolfMZTE_spawn = true;
	@Name("ApoRed spawn probability:")
	public static int ApoRed_weight = 50;
	@Name("ApoRed min group size:")
	public static int ApoRed_min = 1;
	@Name("ApoRed max group size:")
	public static int ApoRed_max = 4;
	@Name("Baka Mitai Creeper spawn probability:")
	public static int BakaMitaiCreeper_weight = 10;
	@Name("Baka Mitai Creeper min group size:")
	public static int BakaMitaiCreeper_min = 1;
	@Name("Baka Mitai Creeper max group size:")
	public static int BakaMitaiCreeper_max = 2;
	@Name("Drachenlord spawn probability:")
	public static int Drachenlord_weight = 50;
	@Name("Drachenlord min group size:")
	public static int Drachenlord_min = 1;
	@Name("Drachenlord max group size:")
	public static int Drachenlord_max = 4;
	@Name("Holzstammhuhn spawn probability:")
	public static int Holzstammhuhn_weight = 50;
	@Name("Holzstammhuhn min group size:")
	public static int Holzstammhuhn_min = 1;
	@Name("Holzstammhuhn max group size:")
	public static int Holzstammhuhn_max = 4;
	@Name("Islamist spawn probability:")
	public static int Islamist_weight = 50;
	@Name("Islamist min group size:")
	public static int Islamist_min = 1;
	@Name("Islamist max group size:")
	public static int Islamist_max = 4;
	@Name("Jens spawn probability:")
	public static int Jens_weight = 25;
	@Name("Jens min group size:")
	public static int Jens_min = 1;
	@Name("Jens max group size:")
	public static int Jens_max = 4;
	@Name("Marcell D'Avis spawn probability:")
	public static int MarcellDAvis_weight = 50;
	@Name("Marcell D'Avis min group size:")
	public static int MarcellDAvis_min = 1;
	@Name("Marcell D'Avis max group size:")
	public static int MarcellDAvis_max = 4;
	@Name("Mr. Bean spawn probability:")
	public static int MrBean_weight = 50;
	@Name("Mr. Bean min group size:")
	public static int MrBean_min = 1;
	@Name("Mr. Bean max group size:")
	public static int MrBean_max = 4;
	@Name("Schalker spawn probability:")
	public static int Schalker_weight = 50;
	@Name("Schalker min group size:")
	public static int Schalker_min = 1;
	@Name("Schalker max group size:")
	public static int Schalker_max = 4;
	@Name("Smava Creeper spawn probability:")
	public static int SmavaCreeper_weight = 25;
	@Name("Smava Creeper min group size:")
	public static int SmavaCreeper_min = 1;
	@Name("Smava Creeper max group size:")
	public static int SmavaCreeper_max = 4;
	@Name("WolfMZTE spawn probability:")
	public static int WolfMZTE_weight = 50;
	@Name("WolfMZTE min group size:")
	public static int WolfMZTE_min = 1;
	@Name("WolfMZTE max group size:")
	public static int WolfMZTE_max = 4;
	@Name("Time in seconds Jens needs to digest:")
	public static int Jens_digest_time = 120;
	@Name("Maximum distance in blocks Jens can search for fish:")
	public static double Jens_search_distance = 10.0;
}