package mod.acgaming.spackenmobs.init;

import java.util.List;

import net.minecraft.entity.*;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import mod.acgaming.spackenmobs.Reference;
import mod.acgaming.spackenmobs.entity.*;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class SpackenmobsEntities
{
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event)
    {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome != null)
        {
            MobSpawnInfo info = biome.getMobSettings();
            List<MobSpawnInfo.Spawners> monster_spawns = event.getSpawns().getSpawner(EntityClassification.MONSTER);
            List<MobSpawnInfo.Spawners> creature_spawns = event.getSpawns().getSpawner(EntityClassification.CREATURE);

            for (Spawners entry : info.getMobs(EntityClassification.MONSTER))
            {
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.apored_weight.get(), ConfigurationHandler.SPAWN.apored_min.get(), ConfigurationHandler.SPAWN.apored_max.get(), EntityType.SKELETON, SpackenmobsRegistry.APORED.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.bakamitai_creeper_weight.get(), ConfigurationHandler.SPAWN.bakamitai_creeper_min.get(), ConfigurationHandler.SPAWN.bakamitai_creeper_max.get(), EntityType.CREEPER, SpackenmobsRegistry.BAKAMITAI_CREEPER.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.drachenlord_weight.get(), ConfigurationHandler.SPAWN.drachenlord_min.get(), ConfigurationHandler.SPAWN.drachenlord_max.get(), EntityType.PIGLIN, SpackenmobsRegistry.DRACHENLORD.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.marcell_davis_weight.get(), ConfigurationHandler.SPAWN.marcell_davis_min.get(), ConfigurationHandler.SPAWN.marcell_davis_max.get(), EntityType.ZOMBIE, SpackenmobsRegistry.MARCELLDAVIS.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.mr_bean_weight.get(), ConfigurationHandler.SPAWN.mr_bean_min.get(), ConfigurationHandler.SPAWN.mr_bean_max.get(), EntityType.ZOMBIE, SpackenmobsRegistry.MRBEAN.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.schalker_weight.get(), ConfigurationHandler.SPAWN.schalker_min.get(), ConfigurationHandler.SPAWN.schalker_max.get(), EntityType.SHULKER, SpackenmobsRegistry.SCHALKER.get());
                registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.smava_creeper_weight.get(), ConfigurationHandler.SPAWN.smava_creeper_min.get(), ConfigurationHandler.SPAWN.smava_creeper_max.get(), EntityType.CREEPER, SpackenmobsRegistry.SMAVA_CREEPER.get());
            }

            for (Spawners entry : info.getMobs(EntityClassification.CREATURE))
            {
                registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.holzstammhuhn_weight.get(), ConfigurationHandler.SPAWN.holzstammhuhn_min.get(), ConfigurationHandler.SPAWN.holzstammhuhn_max.get(), EntityType.CHICKEN, SpackenmobsRegistry.HOLZSTAMMHUHN.get());
                registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.gisela_weight.get(), ConfigurationHandler.SPAWN.gisela_min.get(), ConfigurationHandler.SPAWN.gisela_max.get(), EntityType.SHEEP, SpackenmobsRegistry.GISELA.get());
                registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.jens_weight.get(), ConfigurationHandler.SPAWN.jens_min.get(), ConfigurationHandler.SPAWN.jens_max.get(), EntityType.PIG, SpackenmobsRegistry.JENS.get());
                registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.friedrich_weight.get(), ConfigurationHandler.SPAWN.friedrich_min.get(), ConfigurationHandler.SPAWN.friedrich_max.get(), EntityType.COW, SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get());
                registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.mztewolf_weight.get(), ConfigurationHandler.SPAWN.mztewolf_min.get(), ConfigurationHandler.SPAWN.mztewolf_max.get(), EntityType.WOLF, SpackenmobsRegistry.MZTEWOLF.get());
                //registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.dagibee_weight.get(), ConfigurationHandler.SPAWN.dagibee_min.get(), ConfigurationHandler.SPAWN.dagibee_max.get(), EntityType.BEE, SpackenmobsRegistry.DAGIBEE.get());
            }
        }
    }

    public static void initializeEntities()
    {
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.APORED.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.BAKAMITAI_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        //EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.DAGIBEE.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.DRACHENLORD.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.GISELA.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.HOLZSTAMMHUHN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.JENS.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MARCELLDAVIS.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MRBEAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MZTEWOLF.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.SCHALKER.get(), PlacementType.NO_RESTRICTIONS, Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.SMAVA_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.APORED.get(), AbstractApoRedEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.BAKAMITAI_CREEPER.get(), BakaMitaiCreeperEntity.registerAttributes().build());
        //GlobalEntityTypeAttributes.put(SpackenmobsRegistry.DAGIBEE.get(), DagiBeeEntity.createLivingAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.DRACHENLORD.get(), DrachenlordEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get(), FriedrichLiechtensteinEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.GISELA.get(), GiselaEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.HOLZSTAMMHUHN.get(), HolzstammhuhnEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.JENS.get(), JensEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MARCELLDAVIS.get(), MarcellDAvisEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MRBEAN.get(), MrBeanEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MZTEWOLF.get(), MZTEWolfEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.SCHALKER.get(), SchalkerEntity.registerAttributes().build());
        GlobalEntityTypeAttributes.put(SpackenmobsRegistry.SMAVA_CREEPER.get(), SmavaCreeperEntity.registerAttributes().build());
    }

    public static void registerSpawn(List<Spawners> spawns, Spawners entry, Integer weight, Integer min, Integer max, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity)
    {
        if (entry.type == oldEntity)
        {
            spawns.add(new MobSpawnInfo.Spawners(newEntity, weight, min, max));
        }
    }
}