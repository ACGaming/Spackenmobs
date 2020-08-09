package mod.acgaming.spackenmobs.proxy;
import mod.acgaming.spackenmobs.ModEntities;
import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.items.ItemRAM;
import mod.acgaming.spackenmobs.items.ItemRAMOnAStick;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class CommonProxy
{	
	public void preInit(FMLPreInitializationEvent e)
	{
		ModEntities.init();
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
	{
		/*
		 * CREEPERS
		 */
		// Smava Creeper
		Spackenmobs.ENTITY_SMAVACREEPER_FUSE.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.fuse"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SMAVACREEPER_FUSE);
		Spackenmobs.ENTITY_SMAVACREEPER_BLOW.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.blow"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SMAVACREEPER_BLOW);
		Spackenmobs.ENTITY_SMAVACREEPER_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SMAVACREEPER_HURT);
		Spackenmobs.ENTITY_SMAVACREEPER_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SMAVACREEPER_AMBIENT);
		
		// Islamist
		Spackenmobs.ENTITY_ISLAMIST_FUSE.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.fuse"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_ISLAMIST_FUSE);
		Spackenmobs.ENTITY_ISLAMIST_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_ISLAMIST_HURT);
		
		/*
		 * ZOMBIES
		 */
		// Marcell D'Avis
		Spackenmobs.ENTITY_MARCELLDAVIS_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MARCELLDAVIS_AMBIENT);
		Spackenmobs.ENTITY_MARCELLDAVIS_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MARCELLDAVIS_HURT);
		Spackenmobs.ENTITY_MARCELLDAVIS_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MARCELLDAVIS_DEATH);
		
		// Mr. Bean
		Spackenmobs.ENTITY_MRBEAN_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MRBEAN_AMBIENT);
		Spackenmobs.ENTITY_MRBEAN_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MRBEAN_HURT);
		Spackenmobs.ENTITY_MRBEAN_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_MRBEAN_DEATH);
		
		/*
		 * SKELETONS
		 */
		// ApoRed
		Spackenmobs.ENTITY_APORED_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_APORED_AMBIENT);
		Spackenmobs.ENTITY_APORED_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_APORED_HURT);
		Spackenmobs.ENTITY_APORED_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_APORED_DEATH);
		
		/*
		 * ZOMBIE PIGMEN
		 */
		// Drachenlord
		Spackenmobs.ENTITY_DRACHENLORD_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_DRACHENLORD_AMBIENT);
		Spackenmobs.ENTITY_DRACHENLORD_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_DRACHENLORD_HURT);
		Spackenmobs.ENTITY_DRACHENLORD_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_DRACHENLORD_DEATH);
		Spackenmobs.ENTITY_DRACHENLORD_ANGRY.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.angry"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_DRACHENLORD_ANGRY);
		
		/*
		 * SHULKER
		 */
		Spackenmobs.ENTITY_SCHALKER_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SCHALKER_AMBIENT);
		Spackenmobs.ENTITY_SCHALKER_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SCHALKER_HURT);
		Spackenmobs.ENTITY_SCHALKER_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SCHALKER_DEATH);
		Spackenmobs.ENTITY_SCHALKER_OPEN.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.open"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SCHALKER_OPEN);
		Spackenmobs.ENTITY_SCHALKER_SHOOT.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.shoot"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_SCHALKER_SHOOT);
		
		/*
		 * BIPEDS
		 */
		Spackenmobs.ENTITY_JENS_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.ambient"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_JENS_AMBIENT);
		Spackenmobs.ENTITY_JENS_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.hurt"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_JENS_HURT);
		Spackenmobs.ENTITY_JENS_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.death"));
		event.getRegistry().register((SoundEvent)Spackenmobs.ENTITY_JENS_DEATH);
	}
	
	public void init(FMLInitializationEvent e) {}
	
	public void postInit(FMLPostInitializationEvent e) {}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemRAM());
		event.getRegistry().register(new ItemRAMOnAStick());
	}
	
	public class Events {}
}