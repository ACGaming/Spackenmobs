package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.entities.EntityApoRed;
import mod.acgaming.spackenmobs.entities.EntityBakaMitaiCreeper;
import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import mod.acgaming.spackenmobs.entities.EntityHolzstammhuhn;
import mod.acgaming.spackenmobs.entities.EntityIslamist;
import mod.acgaming.spackenmobs.entities.EntityJens;
import mod.acgaming.spackenmobs.entities.EntityMarcellDAvis;
import mod.acgaming.spackenmobs.entities.EntityMrBean;
import mod.acgaming.spackenmobs.entities.EntitySchalker;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import mod.acgaming.spackenmobs.entities.EntityWolfMZTE;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityWolf;
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
	public static void registerEntities(Register<EntityEntry> event)
	{
		int id = 1;

		// Smava Creeper
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:smava_creeper"), EntitySmavaCreeper.class, "smava_creeper", id++, Spackenmobs.instance, 64, 1, true, 7649828, 11053224);
		if (ModConfigs.SmavaCreeper_spawn == true)
		{
			EntityRegistry.addSpawn(EntitySmavaCreeper.class, ModConfigs.SmavaCreeper_weight, ModConfigs.SmavaCreeper_min, ModConfigs.SmavaCreeper_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityCreeper.class));
		}

		// Marcell D'Avis
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:marcell_davis"), EntityMarcellDAvis.class, "marcell_davis", id++, Spackenmobs.instance, 64, 1, true, 15759, 16777215);
		if (ModConfigs.MarcellDAvis_spawn == true)
		{
			EntityRegistry.addSpawn(EntityMarcellDAvis.class, ModConfigs.MarcellDAvis_weight, ModConfigs.MarcellDAvis_min, ModConfigs.MarcellDAvis_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityZombie.class));
		}

		// Islamist
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:islamist"), EntityIslamist.class, "islamist", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		if (ModConfigs.Islamist_spawn == true)
		{
			EntityRegistry.addSpawn(EntityIslamist.class, ModConfigs.Islamist_weight, ModConfigs.Islamist_min, ModConfigs.Islamist_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityCreeper.class));
		}

		// ApoRed
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:apored"), EntityApoRed.class, "apored", id++, Spackenmobs.instance, 64, 1, true, 2039583, 16711680);
		if (ModConfigs.ApoRed_spawn == true)
		{
			EntityRegistry.addSpawn(EntityApoRed.class, ModConfigs.ApoRed_weight, ModConfigs.ApoRed_min, ModConfigs.ApoRed_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntitySkeleton.class));
		}

		// Mr. Bean
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:mr_bean"), EntityMrBean.class, "mr_bean", id++, Spackenmobs.instance, 64, 1, true, 4802350, 3220238);
		if (ModConfigs.MrBean_spawn == true)
		{
			EntityRegistry.addSpawn(EntityMrBean.class, ModConfigs.MrBean_weight, ModConfigs.MrBean_min, ModConfigs.MrBean_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityZombie.class));
		}

		// Drachenlord
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:drachenlord"), EntityDrachenlord.class, "drachenlord", id++, Spackenmobs.instance, 64, 1, true, 15256745, 8738878);
		if (ModConfigs.Drachenlord_spawn == true)
		{
			EntityRegistry.addSpawn(EntityDrachenlord.class, ModConfigs.Drachenlord_weight, ModConfigs.Drachenlord_min, ModConfigs.Drachenlord_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityPigZombie.class));
		}

		// Schalker
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:schalker"), EntitySchalker.class, "schalker", id++, Spackenmobs.instance, 64, 1, true, 24745, 16777215);
		if (ModConfigs.Schalker_spawn == true)
		{
			EntityRegistry.addSpawn(EntitySchalker.class, ModConfigs.Schalker_weight, ModConfigs.Schalker_min, ModConfigs.Schalker_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityShulker.class));
		}

		// Jens
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:jens"), EntityJens.class, "jens", id++, Spackenmobs.instance, 64, 1, true, 6704526, 6767911);
		if (ModConfigs.Jens_spawn == true)
		{
			EntityRegistry.addSpawn(EntityJens.class, ModConfigs.Jens_weight, ModConfigs.Jens_min, ModConfigs.Jens_max, EnumCreatureType.CREATURE, BiomeHelper.getBiomesWithCreature(EntityPig.class));
		}

		// WolfMZTE
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:wolfmzte"), EntityWolfMZTE.class, "wolfmzte", id++, Spackenmobs.instance, 64, 1, true, 16711680, 0);
		if (ModConfigs.WolfMZTE_spawn == true)
		{
			EntityRegistry.addSpawn(EntityJens.class, ModConfigs.WolfMZTE_weight, ModConfigs.WolfMZTE_min, ModConfigs.WolfMZTE_max, EnumCreatureType.CREATURE,
					BiomeHelper.getBiomesWithCreature(EntityWolf.class));
		}

		// Holzstammhuhn
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:holzstammhuhn"), EntityHolzstammhuhn.class, "holzstammhuhn", id++, Spackenmobs.instance, 64, 1, true, 12096347, 5295899);
		if (ModConfigs.Holzstammhuhn_spawn == true)
		{
			EntityRegistry.addSpawn(EntityJens.class, ModConfigs.Holzstammhuhn_weight, ModConfigs.Holzstammhuhn_min, ModConfigs.Holzstammhuhn_max, EnumCreatureType.CREATURE,
					BiomeHelper.getBiomesWithCreature(EntityChicken.class));
		}

		// Baka Mitai Creeper
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:bakamitai_creeper"), EntityBakaMitaiCreeper.class, "bakamitai_creeper", id++, Spackenmobs.instance, 64, 1, true, 826890, 0);
		if (ModConfigs.BakaMitaiCreeper_spawn == true)
		{
			EntityRegistry.addSpawn(EntityBakaMitaiCreeper.class, ModConfigs.BakaMitaiCreeper_weight, ModConfigs.BakaMitaiCreeper_min, ModConfigs.BakaMitaiCreeper_max, EnumCreatureType.MONSTER,
					BiomeHelper.getBiomesWithMonster(EntityCreeper.class));
		}
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event)
	{
		final Item[] items =
		{
				new Item().setRegistryName(Spackenmobs.MODID, "ram").setUnlocalizedName(Spackenmobs.MODID + "." + "ram").setCreativeTab(Spackenmobs.SPACKENMOBS_TAB),
				new Item().setRegistryName(Spackenmobs.MODID, "ram_on_a_stick").setUnlocalizedName(Spackenmobs.MODID + "." + "ram_on_a_stick").setCreativeTab(Spackenmobs.SPACKENMOBS_TAB),
				new Item().setRegistryName(Spackenmobs.MODID, "surstroemming").setUnlocalizedName(Spackenmobs.MODID + "." + "surstroemming").setCreativeTab(Spackenmobs.SPACKENMOBS_TAB)
		};
		event.getRegistry().registerAll(items);
	}

	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> event)
	{
		// Smava Creeper
		ModSoundEvents.ENTITY_SMAVACREEPER_FUSE.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.fuse"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SMAVACREEPER_FUSE);
		ModSoundEvents.ENTITY_SMAVACREEPER_BLOW.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.blow"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SMAVACREEPER_BLOW);
		ModSoundEvents.ENTITY_SMAVACREEPER_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SMAVACREEPER_HURT);
		ModSoundEvents.ENTITY_SMAVACREEPER_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.smava_creeper.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SMAVACREEPER_AMBIENT);

		// Islamist
		ModSoundEvents.ENTITY_ISLAMIST_FUSE.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.fuse"));
		event.getRegistry().register(ModSoundEvents.ENTITY_ISLAMIST_FUSE);
		ModSoundEvents.ENTITY_ISLAMIST_BLOW.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.blow"));
		event.getRegistry().register(ModSoundEvents.ENTITY_ISLAMIST_BLOW);
		ModSoundEvents.ENTITY_ISLAMIST_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_ISLAMIST_HURT);
		ModSoundEvents.ENTITY_ISLAMIST_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.islamist.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_ISLAMIST_AMBIENT);

		// Marcell D'Avis
		ModSoundEvents.ENTITY_MARCELLDAVIS_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MARCELLDAVIS_AMBIENT);
		ModSoundEvents.ENTITY_MARCELLDAVIS_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MARCELLDAVIS_HURT);
		ModSoundEvents.ENTITY_MARCELLDAVIS_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.marcell_davis.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MARCELLDAVIS_DEATH);

		// Mr. Bean
		ModSoundEvents.ENTITY_MRBEAN_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MRBEAN_AMBIENT);
		ModSoundEvents.ENTITY_MRBEAN_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MRBEAN_HURT);
		ModSoundEvents.ENTITY_MRBEAN_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.mr_bean.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_MRBEAN_DEATH);

		// ApoRed
		ModSoundEvents.ENTITY_APORED_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_APORED_AMBIENT);
		ModSoundEvents.ENTITY_APORED_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_APORED_HURT);
		ModSoundEvents.ENTITY_APORED_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.apored.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_APORED_DEATH);

		// Drachenlord
		ModSoundEvents.ENTITY_DRACHENLORD_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_DRACHENLORD_AMBIENT);
		ModSoundEvents.ENTITY_DRACHENLORD_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_DRACHENLORD_HURT);
		ModSoundEvents.ENTITY_DRACHENLORD_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_DRACHENLORD_DEATH);
		ModSoundEvents.ENTITY_DRACHENLORD_ANGRY.setRegistryName(new ResourceLocation("spackenmobs:entities.drachenlord.angry"));
		event.getRegistry().register(ModSoundEvents.ENTITY_DRACHENLORD_ANGRY);

		// Schalker
		ModSoundEvents.ENTITY_SCHALKER_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SCHALKER_AMBIENT);
		ModSoundEvents.ENTITY_SCHALKER_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SCHALKER_DEATH);
		ModSoundEvents.ENTITY_SCHALKER_OPEN.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.open"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SCHALKER_OPEN);
		ModSoundEvents.ENTITY_SCHALKER_SHOOT.setRegistryName(new ResourceLocation("spackenmobs:entities.schalker.shoot"));
		event.getRegistry().register(ModSoundEvents.ENTITY_SCHALKER_SHOOT);

		// Jens
		ModSoundEvents.ENTITY_JENS_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.ambient"));
		event.getRegistry().register(ModSoundEvents.ENTITY_JENS_AMBIENT);
		ModSoundEvents.ENTITY_JENS_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.hurt"));
		event.getRegistry().register(ModSoundEvents.ENTITY_JENS_HURT);
		ModSoundEvents.ENTITY_JENS_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.death"));
		event.getRegistry().register(ModSoundEvents.ENTITY_JENS_DEATH);
		ModSoundEvents.ENTITY_JENS_EAT.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.eat"));
		event.getRegistry().register(ModSoundEvents.ENTITY_JENS_EAT);
		ModSoundEvents.ENTITY_JENS_POOP.setRegistryName(new ResourceLocation("spackenmobs:entities.jens.poop"));
		event.getRegistry().register(ModSoundEvents.ENTITY_JENS_POOP);

		// Baka Mitai Creeper
		ModSoundEvents.ENTITY_BAKAMITAICREEPER_FUSE.setRegistryName(new ResourceLocation("spackenmobs:entities.bakamitai_creeper.fuse"));
		event.getRegistry().register(ModSoundEvents.ENTITY_BAKAMITAICREEPER_FUSE);
		ModSoundEvents.ENTITY_BAKAMITAICREEPER_BLOW.setRegistryName(new ResourceLocation("spackenmobs:entities.bakamitai_creeper.blow"));
		event.getRegistry().register(ModSoundEvents.ENTITY_BAKAMITAICREEPER_BLOW);
	}
}