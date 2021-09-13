package mod.acgaming.spackenmobs.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;

// Thanks to Vazkii!
public class BiomeHelper
{

    public static Biome[] getBiomesWithMonster(Class<? extends Entity> clazz)
    {
        List<Biome> biomes = new ArrayList<>();
        for (Biome b : Biome.REGISTRY)
        {
            List<SpawnListEntry> spawnList = b.getSpawnableList(EnumCreatureType.MONSTER);
            for (SpawnListEntry e : spawnList)
                if (e.entityClass == clazz)
                {
                    biomes.add(b);
                    break;
                }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static Biome[] getBiomesWithCreature(Class<? extends Entity> clazz)
    {
        List<Biome> biomes = new ArrayList<>();
        for (Biome b : Biome.REGISTRY)
        {
            List<SpawnListEntry> spawnList = b.getSpawnableList(EnumCreatureType.CREATURE);
            for (SpawnListEntry e : spawnList)
                if (e.entityClass == clazz)
                {
                    biomes.add(b);
                    break;
                }
        }
        return biomes.toArray(new Biome[0]);
    }
}