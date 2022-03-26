package mod.acgaming.spackenmobs.misc;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.spackenmobs.Spackenmobs;

@Config(modid = Spackenmobs.MODID, name = "Spackenmobs")
@LangKey("spackenmobs.config.title")
public class ModConfigs
{
    @Config.Comment("Spawn Switches")
    @LangKey("spackenmobs.config.spawn_switches")
    public static SpawnSwitches spawn_switches = new SpawnSwitches();

    @Config.Comment("Spawn Values")
    @LangKey("spackenmobs.config.spawn_values")
    public static SpawnValues spawn_values = new SpawnValues();

    @Config.Comment("Misc")
    @LangKey("spackenmobs.config.misc")
    public static Misc misc = new Misc();

    public static class SpawnSwitches
    {
        @Name("Allow ApoRed to spawn?")
        public boolean ApoRed_spawn = true;
        @Name("Allow Baka Mitai Creeper to spawn?")
        public boolean BakaMitaiCreeper_spawn = true;
        @Name("Allow Drachenlord to spawn?")
        public boolean Drachenlord_spawn = true;
        @Name("Allow Friedrich Liechtenstein to spawn?")
        public boolean Friedrich_spawn = true;
        @Name("Allow Holzstammhuhn to spawn?")
        public boolean Holzstammhuhn_spawn = true;
        @Name("Allow Jens to spawn?")
        public boolean Jens_spawn = true;
        @Name("Allow Marcell D'Avis to spawn?")
        public boolean MarcellDAvis_spawn = true;
        @Name("Allow Mr. Bean to spawn?")
        public boolean MrBean_spawn = true;
        @Name("Allow Schalker to spawn?")
        public boolean Schalker_spawn = true;
        @Name("Allow Smava Creeper to spawn?")
        public boolean SmavaCreeper_spawn = true;
        @Name("Allow MZTEWolf to spawn?")
        public boolean MZTEWolf_spawn = true;
        @Name("Allow Gisela to spawn?")
        public boolean Gisela_spawn = true;
        @Name("Allow tilera Ghast to spawn?")
        public boolean tileraGhast_spawn = false;
    }

    public static class SpawnValues
    {
        @Name("ApoRed spawn weight:")
        public int ApoRed_weight = 15;
        @Name("ApoRed min group size:")
        public int ApoRed_min = 1;
        @Name("ApoRed max group size:")
        public int ApoRed_max = 1;
        @Name("Baka Mitai Creeper spawn weight:")
        public int BakaMitaiCreeper_weight = 10;
        @Name("Baka Mitai Creeper min group size:")
        public int BakaMitaiCreeper_min = 1;
        @Name("Baka Mitai Creeper max group size:")
        public int BakaMitaiCreeper_max = 1;
        @Name("Drachenlord spawn weight:")
        public int Drachenlord_weight = 10;
        @Name("Drachenlord min group size:")
        public int Drachenlord_min = 1;
        @Name("Drachenlord max group size:")
        public int Drachenlord_max = 1;
        @Name("Friedrich Liechtenstein spawn weight:")
        public int Friedrich_weight = 10;
        @Name("Friedrich Liechtenstein min group size:")
        public int Friedrich_min = 1;
        @Name("Friedrich Liechtenstein max group size:")
        public int Friedrich_max = 1;
        @Name("Holzstammhuhn spawn weight:")
        public int Holzstammhuhn_weight = 10;
        @Name("Holzstammhuhn min group size:")
        public int Holzstammhuhn_min = 1;
        @Name("Holzstammhuhn max group size:")
        public int Holzstammhuhn_max = 1;
        @Name("Jens spawn weight:")
        public int Jens_weight = 10;
        @Name("Jens min group size:")
        public int Jens_min = 1;
        @Name("Jens max group size:")
        public int Jens_max = 1;
        @Name("Marcell D'Avis spawn weight:")
        public int MarcellDAvis_weight = 15;
        @Name("Marcell D'Avis min group size:")
        public int MarcellDAvis_min = 1;
        @Name("Marcell D'Avis max group size:")
        public int MarcellDAvis_max = 1;
        @Name("Mr. Bean spawn weight:")
        public int MrBean_weight = 15;
        @Name("Mr. Bean min group size:")
        public int MrBean_min = 1;
        @Name("Mr. Bean max group size:")
        public int MrBean_max = 1;
        @Name("Schalker spawn weight:")
        public int Schalker_weight = 10;
        @Name("Schalker min group size:")
        public int Schalker_min = 1;
        @Name("Schalker max group size:")
        public int Schalker_max = 1;
        @Name("Smava Creeper spawn weight:")
        public int SmavaCreeper_weight = 15;
        @Name("Smava Creeper min group size:")
        public int SmavaCreeper_min = 1;
        @Name("Smava Creeper max group size:")
        public int SmavaCreeper_max = 1;
        @Name("MZTEWolf spawn weight:")
        public int MZTEWolf_weight = 10;
        @Name("MZTEWolf min group size:")
        public int MZTEWolf_min = 1;
        @Name("MZTEWolf max group size:")
        public int MZTEWolf_max = 1;
        @Name("Gisela spawn weight:")
        public int Gisela_weight = 10;
        @Name("Gisela min group size:")
        public int Gisela_min = 1;
        @Name("Gisela max group size:")
        public int Gisela_max = 1;
        @Name("tilera Ghast spawn weight:")
        public int tileraGhast_weight = 10;
        @Name("tilera Ghast min group size:")
        public int tileraGhast_min = 1;
        @Name("tilera Ghast max group size:")
        public int tileraGhast_max = 1;
    }

    public static class Misc
    {
        @Name("Time in seconds Jens needs to digest:")
        public int Jens_digest_time = 120;
        @Name("Maximum distance in blocks Jens can search:")
        public int Jens_search_distance = 16;
    }

    @Mod.EventBusSubscriber(modid = Spackenmobs.MODID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Spackenmobs.MODID))
            {
                ConfigManager.sync(Spackenmobs.MODID, Config.Type.INSTANCE);
            }
        }
    }
}