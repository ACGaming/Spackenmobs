package mod.acgaming.spackenmobs.init;

import mod.acgaming.spackenmobs.Reference;
import mod.acgaming.spackenmobs.entity.*;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;
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

import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class SpackenmobsEntities
{
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event)
	{
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if (biome != null)
		{
			MobSpawnInfo info = biome.getMobSpawnInfo();
			List<MobSpawnInfo.Spawners> monster_spawns = event.getSpawns().getSpawner(EntityClassification.MONSTER);
			List<MobSpawnInfo.Spawners> creature_spawns = event.getSpawns().getSpawner(EntityClassification.CREATURE);

			for (Spawners entry : info.getSpawners(EntityClassification.MONSTER))
			{
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.apored_weight.get(), ConfigurationHandler.SPAWN.apored_min.get(), ConfigurationHandler.SPAWN.apored_max.get(), EntityType.SKELETON, SpackenmobsRegistry.APORED.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.bakamitai_creeper_weight.get(), ConfigurationHandler.SPAWN.bakamitai_creeper_min.get(), ConfigurationHandler.SPAWN.bakamitai_creeper_max.get(), EntityType.CREEPER, SpackenmobsRegistry.BAKAMITAI_CREEPER.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.drachenlord_weight.get(), ConfigurationHandler.SPAWN.drachenlord_min.get(), ConfigurationHandler.SPAWN.drachenlord_max.get(), EntityType.PIGLIN, SpackenmobsRegistry.DRACHENLORD.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.islamist_weight.get(), ConfigurationHandler.SPAWN.islamist_min.get(), ConfigurationHandler.SPAWN.islamist_max.get(), EntityType.CREEPER, SpackenmobsRegistry.ISLAMIST.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.marcell_davis_weight.get(), ConfigurationHandler.SPAWN.marcell_davis_min.get(), ConfigurationHandler.SPAWN.marcell_davis_max.get(), EntityType.ZOMBIE, SpackenmobsRegistry.MARCELLDAVIS.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.mr_bean_weight.get(), ConfigurationHandler.SPAWN.mr_bean_min.get(), ConfigurationHandler.SPAWN.mr_bean_max.get(), EntityType.ZOMBIE, SpackenmobsRegistry.MRBEAN.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.schalker_weight.get(), ConfigurationHandler.SPAWN.schalker_min.get(), ConfigurationHandler.SPAWN.schalker_max.get(), EntityType.SHULKER, SpackenmobsRegistry.SCHALKER.get());
				registerSpawn(monster_spawns, entry, ConfigurationHandler.SPAWN.smava_creeper_weight.get(), ConfigurationHandler.SPAWN.smava_creeper_min.get(), ConfigurationHandler.SPAWN.smava_creeper_max.get(), EntityType.CREEPER, SpackenmobsRegistry.SMAVA_CREEPER.get());
			}

			for (Spawners entry : info.getSpawners(EntityClassification.CREATURE))
			{
				registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.holzstammhuhn_weight.get(), ConfigurationHandler.SPAWN.holzstammhuhn_min.get(), ConfigurationHandler.SPAWN.holzstammhuhn_max.get(), EntityType.CHICKEN, SpackenmobsRegistry.HOLZSTAMMHUHN.get());
				registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.jens_weight.get(), ConfigurationHandler.SPAWN.jens_min.get(), ConfigurationHandler.SPAWN.jens_max.get(), EntityType.PIG, SpackenmobsRegistry.JENS.get());
				registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.friedrich_weight.get(), ConfigurationHandler.SPAWN.friedrich_min.get(), ConfigurationHandler.SPAWN.friedrich_max.get(), EntityType.COW, SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get());
				registerSpawn(creature_spawns, entry, ConfigurationHandler.SPAWN.mztewolf_weight.get(), ConfigurationHandler.SPAWN.mztewolf_min.get(), ConfigurationHandler.SPAWN.mztewolf_max.get(), EntityType.WOLF, SpackenmobsRegistry.MZTEWOLF.get());
			}
		}
	}

	public static void initializeEntities()
	{
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.APORED.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.BAKAMITAI_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.DRACHENLORD.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.ISLAMIST.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.HOLZSTAMMHUHN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.JENS.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MARCELLDAVIS.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MRBEAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.MZTEWOLF.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.SCHALKER.get(), PlacementType.NO_RESTRICTIONS, Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
		EntitySpawnPlacementRegistry.register(SpackenmobsRegistry.SMAVA_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);

		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.APORED.get(), AbstractApoRedEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.BAKAMITAI_CREEPER.get(), BakaMitaiCreeperEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.DRACHENLORD.get(), DrachenlordEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN.get(), FriedrichLiechtensteinEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.ISLAMIST.get(), IslamistEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.HOLZSTAMMHUHN.get(), HolzstammhuhnEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.JENS.get(), JensEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MARCELLDAVIS.get(), MarcellDAvisEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MRBEAN.get(), MrBeanEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.MZTEWOLF.get(), MZTEWolfEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.SCHALKER.get(), SchalkerEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(SpackenmobsRegistry.SMAVA_CREEPER.get(), SmavaCreeperEntity.registerAttributes().create());
	}

	public static void registerSpawn(List<Spawners> spawns, Spawners entry, Integer weight, Integer min, Integer max, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity)
	{
		if (entry.type == oldEntity)
		{
			spawns.add(new MobSpawnInfo.Spawners(newEntity, weight, min, max));
		}
	}
}