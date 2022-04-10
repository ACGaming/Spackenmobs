package mod.acgaming.spackenmobs.misc;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.entities.*;

@EventBusSubscriber(modid = Spackenmobs.MODID)
public class RegistryHandler
{
    @SubscribeEvent
    public static void registerEntities(Register<EntityEntry> event)
    {
        List<Biome> regularSpawning = Lists.newArrayList();
        for (Biome b : Biome.REGISTRY)
        {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
            if (!types.contains(BiomeDictionary.Type.WATER) || !types.contains(BiomeDictionary.Type.NETHER) || !types.contains(BiomeDictionary.Type.END))
            {
                regularSpawning.add(b);
            }
        }

        int id = 1;

        // Smava Creeper
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:smava_creeper"), EntitySmavaCreeper.class, "smava_creeper", id++, Spackenmobs.instance, 64, 1, true, 7649828, 11053224);
        if (ModConfigs.spawn_switches.SmavaCreeper_spawn)
        {
            EntityRegistry.addSpawn(
                EntitySmavaCreeper.class,
                ModConfigs.spawn_values.SmavaCreeper_weight,
                ModConfigs.spawn_values.SmavaCreeper_min,
                ModConfigs.spawn_values.SmavaCreeper_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // Marcell D'Avis
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:marcell_davis"), EntityMarcellDAvis.class, "marcell_davis", id++, Spackenmobs.instance, 64, 1, true, 15759, 16777215);
        if (ModConfigs.spawn_switches.MarcellDAvis_spawn)
        {
            EntityRegistry.addSpawn(
                EntityMarcellDAvis.class,
                ModConfigs.spawn_values.MarcellDAvis_weight,
                ModConfigs.spawn_values.MarcellDAvis_min,
                ModConfigs.spawn_values.MarcellDAvis_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // Islamist
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:islamist"), EntityIslamist.class, "islamist", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
        if (ModConfigs.spawn_switches.Islamist_spawn)
        {
            EntityRegistry.addSpawn(
                EntityIslamist.class,
                ModConfigs.spawn_values.Islamist_weight,
                ModConfigs.spawn_values.Islamist_min,
                ModConfigs.spawn_values.Islamist_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // ApoRed
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:apored"), EntityApoRed.class, "apored", id++, Spackenmobs.instance, 64, 1, true, 2039583, 16711680);
        if (ModConfigs.spawn_switches.ApoRed_spawn)
        {
            EntityRegistry.addSpawn(
                EntityApoRed.class,
                ModConfigs.spawn_values.ApoRed_weight,
                ModConfigs.spawn_values.ApoRed_min,
                ModConfigs.spawn_values.ApoRed_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // Mr. Bean
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:mr_bean"), EntityMrBean.class, "mr_bean", id++, Spackenmobs.instance, 64, 1, true, 4802350, 3220238);
        if (ModConfigs.spawn_switches.MrBean_spawn)
        {
            EntityRegistry.addSpawn(
                EntityMrBean.class,
                ModConfigs.spawn_values.MrBean_weight,
                ModConfigs.spawn_values.MrBean_min,
                ModConfigs.spawn_values.MrBean_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // Drachenlord
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:drachenlord"), EntityDrachenlord.class, "drachenlord", id++, Spackenmobs.instance, 64, 1, true, 15256745, 8738878);
        if (ModConfigs.spawn_switches.Drachenlord_spawn)
        {
            EntityRegistry.addSpawn(
                EntityDrachenlord.class,
                ModConfigs.spawn_values.Drachenlord_weight,
                ModConfigs.spawn_values.Drachenlord_min,
                ModConfigs.spawn_values.Drachenlord_max,
                EnumCreatureType.MONSTER,
                BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
        }

        // Schalker
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:schalker"), EntitySchalker.class, "schalker", id++, Spackenmobs.instance, 64, 1, true, 24745, 16777215);
        if (ModConfigs.spawn_switches.Schalker_spawn)
        {
            EntityRegistry.addSpawn(
                EntitySchalker.class,
                ModConfigs.spawn_values.Schalker_weight,
                ModConfigs.spawn_values.Schalker_min,
                ModConfigs.spawn_values.Schalker_max,
                EnumCreatureType.MONSTER,
                BiomeDictionary.getBiomes(BiomeDictionary.Type.END).toArray(new Biome[0]));
        }

        // Jens
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:jens"), EntityJens.class, "jens", id++, Spackenmobs.instance, 64, 1, true, 6704526, 6767911);
        if (ModConfigs.spawn_switches.Jens_spawn)
        {
            EntityRegistry.addSpawn(
                EntityJens.class,
                ModConfigs.spawn_values.Jens_weight,
                ModConfigs.spawn_values.Jens_min,
                ModConfigs.spawn_values.Jens_max,
                EnumCreatureType.CREATURE,
                regularSpawning.toArray(new Biome[0]));
        }

        // MZTEWolf
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:mztewolf"), EntityMZTEWolf.class, "mztewolf", id++, Spackenmobs.instance, 64, 1, true, 16711680, 0);
        if (ModConfigs.spawn_switches.MZTEWolf_spawn)
        {
            EntityRegistry.addSpawn(
                EntityMZTEWolf.class,
                ModConfigs.spawn_values.MZTEWolf_weight,
                ModConfigs.spawn_values.MZTEWolf_min,
                ModConfigs.spawn_values.MZTEWolf_max,
                EnumCreatureType.CREATURE,
                regularSpawning.toArray(new Biome[0]));
        }

        // Holzstammhuhn
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:holzstammhuhn"), EntityHolzstammhuhn.class, "holzstammhuhn", id++, Spackenmobs.instance, 64, 1, true, 12096347, 5295899);
        if (ModConfigs.spawn_switches.Holzstammhuhn_spawn)
        {
            EntityRegistry.addSpawn(
                EntityHolzstammhuhn.class,
                ModConfigs.spawn_values.Holzstammhuhn_weight,
                ModConfigs.spawn_values.Holzstammhuhn_min,
                ModConfigs.spawn_values.Holzstammhuhn_max,
                EnumCreatureType.CREATURE,
                regularSpawning.toArray(new Biome[0]));
        }

        // Baka Mitai Creeper
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:bakamitai_creeper"), EntityBakaMitaiCreeper.class, "bakamitai_creeper", id++, Spackenmobs.instance, 64, 1, true, 826890, 0);
        if (ModConfigs.spawn_switches.BakaMitaiCreeper_spawn)
        {
            EntityRegistry.addSpawn(
                EntityBakaMitaiCreeper.class,
                ModConfigs.spawn_values.BakaMitaiCreeper_weight,
                ModConfigs.spawn_values.BakaMitaiCreeper_min,
                ModConfigs.spawn_values.BakaMitaiCreeper_max,
                EnumCreatureType.MONSTER,
                regularSpawning.toArray(new Biome[0]));
        }

        // Friedrich Liechtenstein
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:friedrich"), EntityFriedrichLiechtenstein.class, "friedrich", id++, Spackenmobs.instance, 64, 1, true, 16447728, 15878595);
        if (ModConfigs.spawn_switches.Friedrich_spawn)
        {
            EntityRegistry.addSpawn(
                EntityFriedrichLiechtenstein.class,
                ModConfigs.spawn_values.Friedrich_weight,
                ModConfigs.spawn_values.Friedrich_min,
                ModConfigs.spawn_values.Friedrich_max,
                EnumCreatureType.CREATURE,
                regularSpawning.toArray(new Biome[0]));
        }

        // Gisela
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:gisela"), EntityGisela.class, "gisela", id++, Spackenmobs.instance, 64, 1, true, 39835, 16448250);
        if (ModConfigs.spawn_switches.Gisela_spawn)
        {
            EntityRegistry.addSpawn(
                EntityGisela.class,
                ModConfigs.spawn_values.Gisela_weight,
                ModConfigs.spawn_values.Gisela_min,
                ModConfigs.spawn_values.Gisela_max,
                EnumCreatureType.CREATURE,
                regularSpawning.toArray(new Biome[0]));
        }

        // tilera Ghast
        EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:tilera_ghast"), EntityTileraGhast.class, "tilera_ghast", id++, Spackenmobs.instance, 64, 1, true, 16447728, 15878595);
        if (ModConfigs.spawn_switches.tileraGhast_spawn)
        {
            EntityRegistry.addSpawn(
                EntityTileraGhast.class,
                ModConfigs.spawn_values.tileraGhast_weight,
                ModConfigs.spawn_values.tileraGhast_min,
                ModConfigs.spawn_values.tileraGhast_max,
                EnumCreatureType.MONSTER,
                BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
        }
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
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

        // Friedrich Liechtenstein
        ModSoundEvents.ENTITY_FRIEDRICH_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.friedrich.ambient"));
        event.getRegistry().register(ModSoundEvents.ENTITY_FRIEDRICH_AMBIENT);
        ModSoundEvents.ENTITY_FRIEDRICH_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.friedrich.hurt"));
        event.getRegistry().register(ModSoundEvents.ENTITY_FRIEDRICH_HURT);
        ModSoundEvents.ENTITY_FRIEDRICH_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.friedrich.death"));
        event.getRegistry().register(ModSoundEvents.ENTITY_FRIEDRICH_DEATH);

        // Gisela
        ModSoundEvents.ENTITY_GISELA_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.gisela.ambient"));
        event.getRegistry().register(ModSoundEvents.ENTITY_GISELA_AMBIENT);
        ModSoundEvents.ENTITY_GISELA_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.gisela.hurt"));
        event.getRegistry().register(ModSoundEvents.ENTITY_GISELA_HURT);

	    //MZTEWolf
	    ModSoundEvents.ENTITY_MZTEWOLF_AMBIENT.setRegistryName(new ResourceLocation("spackenmobs:entities.mztewolf.ambient"));
	    event.getRegistry().register(ModSoundEvents.ENTITY_MZTEWOLF_AMBIENT);
    }
}