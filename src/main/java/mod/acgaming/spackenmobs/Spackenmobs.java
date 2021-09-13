package mod.acgaming.spackenmobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import mod.acgaming.spackenmobs.client.ClientHandler;
import mod.acgaming.spackenmobs.init.SpackenmobsEntities;
import mod.acgaming.spackenmobs.init.SpackenmobsRegistry;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;

@Mod(Reference.MOD_ID)
public class Spackenmobs
{
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public Spackenmobs()
    {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
        eventBus.register(ConfigurationHandler.class);

        eventBus.addListener(this::setup);

        SpackenmobsRegistry.ITEMS.register(eventBus);
        SpackenmobsRegistry.ENTITIES.register(eventBus);
        SpackenmobsRegistry.SOUND_EVENTS.register(eventBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () ->
        {
            eventBus.addListener(ClientHandler::doClientStuff);
            eventBus.addListener(ClientHandler::registerItemColors);
        });
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        SpackenmobsEntities.initializeEntities();
    }
}