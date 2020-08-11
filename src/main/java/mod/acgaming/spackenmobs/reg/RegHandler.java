package mod.acgaming.spackenmobs.reg;
import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.entities.BiomeHelper;
import mod.acgaming.spackenmobs.entities.EntityApoRed;
import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import mod.acgaming.spackenmobs.entities.EntityIslamist;
import mod.acgaming.spackenmobs.entities.EntityJens;
import mod.acgaming.spackenmobs.entities.EntityMarcellDAvis;
import mod.acgaming.spackenmobs.entities.EntityMrBean;
import mod.acgaming.spackenmobs.entities.EntitySchalker;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@EventBusSubscriber(modid = Spackenmobs.MODID)
public class RegHandler
{
	@SubscribeEvent
	public static void registerItems(Register<Item> event)
	{
		final Item[] items = {
			new Item().setRegistryName(Spackenmobs.MODID, "ram").setUnlocalizedName(Spackenmobs.MODID + "." + "ram").setCreativeTab(CreativeTabs.MISC),
			new Item().setRegistryName(Spackenmobs.MODID, "ram_on_a_stick").setUnlocalizedName(Spackenmobs.MODID + "." + "ram_on_a_stick").setCreativeTab(CreativeTabs.MISC)
		};
		event.getRegistry().registerAll(items);
	}
	
	@SubscribeEvent
	public static void registerEntities(Register<EntityEntry> event)
	{
    	int id = 1;
    	
    	// Smava Creeper
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:smava_creeper"), EntitySmavaCreeper.class, "smava_creeper", id++, Spackenmobs.instance, 64, 1, true, 7649828, 11053224);
		EntityRegistry.addSpawn(EntitySmavaCreeper.class, 25, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityCreeper.class));
		
		// Marcell D'Avis
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:marcell_davis"), EntityMarcellDAvis.class, "marcell_davis", id++, Spackenmobs.instance, 64, 1, true, 15759, 16777215);
		EntityRegistry.addSpawn(EntityMarcellDAvis.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityZombie.class));
		
		// Islamist		
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:islamist"), EntityIslamist.class, "islamist", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.addSpawn(EntityIslamist.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityCreeper.class));
		
		// ApoRed
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:apored"), EntityApoRed.class, "apored", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.addSpawn(EntityApoRed.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntitySkeleton.class));
		
		// Mr. Bean
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:mr_bean"), EntityMrBean.class, "mr_bean", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.addSpawn(EntityMrBean.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityZombie.class));
		
		// Drachenlord		
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:drachenlord"), EntityDrachenlord.class, "drachenlord", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);		
		EntityRegistry.addSpawn(EntityDrachenlord.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityPigZombie.class));
		
		// Schalker
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:schalker"), EntitySchalker.class, "schalker", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.addSpawn(EntitySchalker.class, 50, 1, 4, EnumCreatureType.MONSTER, BiomeHelper.getBiomesWithMonster(EntityShulker.class));
		
		// Jens
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:jens"), EntityJens.class, "jens", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.addSpawn(EntityJens.class, 50, 1, 4, EnumCreatureType.CREATURE, BiomeHelper.getBiomesWithCreature(EntityPig.class));
        
        //LootTableList.register(EntityJens.LOOT);
	}
	
	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> event)
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
}