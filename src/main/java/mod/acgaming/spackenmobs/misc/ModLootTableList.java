package mod.acgaming.spackenmobs.misc;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

public class ModLootTableList
{
    private static final Set<ResourceLocation> LOOT_TABLES = Sets.newHashSet();
    public static final ResourceLocation EMPTY = register("empty");
    public static final ResourceLocation ENTITIES_JENS = register("entities/jens");
    public static final ResourceLocation ENTITIES_FRIEDRICH = register("entities/friedrich");
    public static final ResourceLocation ENTITIES_MARCELL_DAVIS = register("entities/marcell_davis");
    private static final Set<ResourceLocation> READ_ONLY_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

    public static Set<ResourceLocation> getAll()
    {
        return READ_ONLY_LOOT_TABLES;
    }

    public static ResourceLocation register(ResourceLocation id)
    {
        if (LOOT_TABLES.add(id))
        {
            return id;
        }
        else
        {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

    public static boolean test()
    {
        LootTableManager loottablemanager = new LootTableManager(null);

        for (ResourceLocation resourcelocation : READ_ONLY_LOOT_TABLES)
        {
            if (loottablemanager.getLootTableFromLocation(resourcelocation) == LootTable.EMPTY_LOOT_TABLE)
            {
                return false;
            }
        }

        return true;
    }

    private static ResourceLocation register(String id)
    {
        return register(new ResourceLocation("spackenmobs", id));
    }
}