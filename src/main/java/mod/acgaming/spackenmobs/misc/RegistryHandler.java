package mod.acgaming.spackenmobs.misc;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.oredict.OreDictionary;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.SpackenmobsTab;
import mod.acgaming.spackenmobs.entities.*;

@EventBusSubscriber(modid = Spackenmobs.MODID)
public class RegistryHandler
{
    public static int id;

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

        // REGISTRATION
        registerEntityWithSpawnEgg(EntityApoRed.class, "apored", 2039583, 16711680);
        registerEntityWithSpawnEgg(EntityBakaMitaiCreeper.class, "bakamitai_creeper", 826890, 0);
        registerEntityWithSpawnEgg(EntityDrachenlord.class, "drachenlord", 15256745, 8738878);
        registerEntityWithSpawnEgg(EntityFriedrichLiechtenstein.class, "friedrich", 16447728, 15878595);
        registerEntityWithSpawnEgg(EntityGisela.class, "gisela", 39835, 16448250);
        registerEntityWithSpawnEgg(EntityHolzstammhuhn.class, "holzstammhuhn", 12096347, 5295899);
        registerEntityWithSpawnEgg(EntityIslamist.class, "islamist", 15263976, 15211548);
        registerEntityWithSpawnEgg(EntityJens.class, "jens", 6704526, 6767911);
        registerEntityWithSpawnEgg(EntityMZTEWolf.class, "mztewolf", 16711680, 0);
        registerEntityWithSpawnEgg(EntityMarcellDAvis.class, "marcell_davis", 15759, 16777215);
        registerEntityWithSpawnEgg(EntityMrBean.class, "mr_bean", 4802350, 3220238);
        registerEntityWithSpawnEgg(EntitySchalker.class, "schalker", 24745, 16777215);
        registerEntityWithSpawnEgg(EntitySmavaCreeper.class, "smava_creeper", 7649828, 11053224);
        registerEntityWithSpawnEgg(EntityTileraGhast.class, "tilera_ghast", 255, 16711680);

        // SPAWNING
        if (ModConfigs.spawn_switches.ApoRed_spawn) EntityRegistry.addSpawn(EntityApoRed.class, ModConfigs.spawn_values.ApoRed_weight, ModConfigs.spawn_values.ApoRed_min, ModConfigs.spawn_values.ApoRed_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.BakaMitaiCreeper_spawn) EntityRegistry.addSpawn(EntityBakaMitaiCreeper.class, ModConfigs.spawn_values.BakaMitaiCreeper_weight, ModConfigs.spawn_values.BakaMitaiCreeper_min, ModConfigs.spawn_values.BakaMitaiCreeper_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Drachenlord_spawn) EntityRegistry.addSpawn(EntityDrachenlord.class, ModConfigs.spawn_values.Drachenlord_weight, ModConfigs.spawn_values.Drachenlord_min, ModConfigs.spawn_values.Drachenlord_max, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Friedrich_spawn) EntityRegistry.addSpawn(EntityFriedrichLiechtenstein.class, ModConfigs.spawn_values.Friedrich_weight, ModConfigs.spawn_values.Friedrich_min, ModConfigs.spawn_values.Friedrich_max, EnumCreatureType.CREATURE, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Gisela_spawn) EntityRegistry.addSpawn(EntityGisela.class, ModConfigs.spawn_values.Gisela_weight, ModConfigs.spawn_values.Gisela_min, ModConfigs.spawn_values.Gisela_max, EnumCreatureType.CREATURE, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Holzstammhuhn_spawn) EntityRegistry.addSpawn(EntityHolzstammhuhn.class, ModConfigs.spawn_values.Holzstammhuhn_weight, ModConfigs.spawn_values.Holzstammhuhn_min, ModConfigs.spawn_values.Holzstammhuhn_max, EnumCreatureType.CREATURE, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Islamist_spawn) EntityRegistry.addSpawn(EntityIslamist.class, ModConfigs.spawn_values.Islamist_weight, ModConfigs.spawn_values.Islamist_min, ModConfigs.spawn_values.Islamist_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Jens_spawn) EntityRegistry.addSpawn(EntityJens.class, ModConfigs.spawn_values.Jens_weight, ModConfigs.spawn_values.Jens_min, ModConfigs.spawn_values.Jens_max, EnumCreatureType.CREATURE, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.MZTEWolf_spawn) EntityRegistry.addSpawn(EntityMZTEWolf.class, ModConfigs.spawn_values.MZTEWolf_weight, ModConfigs.spawn_values.MZTEWolf_min, ModConfigs.spawn_values.MZTEWolf_max, EnumCreatureType.CREATURE, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.MarcellDAvis_spawn) EntityRegistry.addSpawn(EntityMarcellDAvis.class, ModConfigs.spawn_values.MarcellDAvis_weight, ModConfigs.spawn_values.MarcellDAvis_min, ModConfigs.spawn_values.MarcellDAvis_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.MrBean_spawn) EntityRegistry.addSpawn(EntityMrBean.class, ModConfigs.spawn_values.MrBean_weight, ModConfigs.spawn_values.MrBean_min, ModConfigs.spawn_values.MrBean_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.Schalker_spawn) EntityRegistry.addSpawn(EntitySchalker.class, ModConfigs.spawn_values.Schalker_weight, ModConfigs.spawn_values.Schalker_min, ModConfigs.spawn_values.Schalker_max, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(BiomeDictionary.Type.END).toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.SmavaCreeper_spawn) EntityRegistry.addSpawn(EntitySmavaCreeper.class, ModConfigs.spawn_values.SmavaCreeper_weight, ModConfigs.spawn_values.SmavaCreeper_min, ModConfigs.spawn_values.SmavaCreeper_max, EnumCreatureType.MONSTER, regularSpawning.toArray(new Biome[0]));
        if (ModConfigs.spawn_switches.tileraGhast_spawn) EntityRegistry.addSpawn(EntityTileraGhast.class, ModConfigs.spawn_values.tileraGhast_weight, ModConfigs.spawn_values.tileraGhast_min, ModConfigs.spawn_values.tileraGhast_max, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
    }

    public static void registerEntityWithSpawnEgg(Class clazz, String entityName, int primary, int secondary)
    {
        ResourceLocation registryName = new ResourceLocation(Spackenmobs.MODID, entityName);
        EntityRegistry.registerModEntity(registryName, clazz, entityName, id++, Spackenmobs.instance, 64, 1, true, primary, secondary);
        SpackenmobsTab.eggs.add(getSpawnEgg(entityName));
    }

    public static ItemStack getSpawnEgg(String entityName)
    {
        ItemStack stack = new ItemStack(Items.SPAWN_EGG);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("id", Spackenmobs.MODID + ":" + entityName);
        NBTTagCompound nbt2 = new NBTTagCompound();
        nbt2.setTag("EntityTag", nbt);
        stack.setTagCompound(nbt2);
        return stack;
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));

        OreDictionary.registerOre("dustRedstone", ModItems.MODEM);
        OreDictionary.registerOre("ingotIron", ModItems.RAM);
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
        ModSoundEvents.ENTITY_MZTEWOLF_HURT.setRegistryName(new ResourceLocation("spackenmobs:entities.mztewolf.hurt"));
        event.getRegistry().register(ModSoundEvents.ENTITY_MZTEWOLF_HURT);
        ModSoundEvents.ENTITY_MZTEWOLF_DEATH.setRegistryName(new ResourceLocation("spackenmobs:entities.mztewolf.death"));
        event.getRegistry().register(ModSoundEvents.ENTITY_MZTEWOLF_DEATH);
    }
}