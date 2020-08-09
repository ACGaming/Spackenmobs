package mod.acgaming.spackenmobs;
import java.util.ArrayList;
import java.util.List;

import mod.acgaming.spackenmobs.entities.EntityApoRed;
import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import mod.acgaming.spackenmobs.entities.EntityIslamist;
import mod.acgaming.spackenmobs.entities.EntityJens;
import mod.acgaming.spackenmobs.entities.EntityMarcellDAvis;
import mod.acgaming.spackenmobs.entities.EntityMrBean;
import mod.acgaming.spackenmobs.entities.EntitySchalker;
import mod.acgaming.spackenmobs.entities.EntitySmavaCreeper;
import mod.acgaming.spackenmobs.render.RenderApoRed;
import mod.acgaming.spackenmobs.render.RenderDrachenlord;
import mod.acgaming.spackenmobs.render.RenderIslamist;
import mod.acgaming.spackenmobs.render.RenderJens;
import mod.acgaming.spackenmobs.render.RenderMarcellDAvis;
import mod.acgaming.spackenmobs.render.RenderMrBean;
import mod.acgaming.spackenmobs.render.RenderSchalker;
import mod.acgaming.spackenmobs.render.RenderSmavaCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
    	int id = 1;
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:smava_creeper"), EntitySmavaCreeper.class, "smava_creeper", id++, Spackenmobs.instance, 64, 1, true, 7649828, 11053224);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:marcell_davis"), EntityMarcellDAvis.class, "marcell_davis", id++, Spackenmobs.instance, 64, 1, true, 15759, 16777215);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:islamist"), EntityIslamist.class, "islamist", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:apored"), EntityApoRed.class, "apored", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:mr_bean"), EntityMrBean.class, "mr_bean", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:drachenlord"), EntityDrachenlord.class, "drachenlord", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:schalker"), EntitySchalker.class, "schalker", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);
		EntityRegistry.registerModEntity(new ResourceLocation("spackenmobs:jens"), EntityJens.class, "jens", id++, Spackenmobs.instance, 64, 1, true, 15263976, 15211548);

		EntityRegistry.addSpawn(EntitySmavaCreeper.class, 25, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityCreeper.class));
		EntityRegistry.addSpawn(EntityIslamist.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityCreeper.class));
		EntityRegistry.addSpawn(EntityMarcellDAvis.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityZombie.class));
		EntityRegistry.addSpawn(EntityMrBean.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityZombie.class));
		EntityRegistry.addSpawn(EntityApoRed.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntitySkeleton.class));
		EntityRegistry.addSpawn(EntityDrachenlord.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityPigZombie.class));
		EntityRegistry.addSpawn(EntitySchalker.class, 50, 1, 4, EnumCreatureType.MONSTER, getBiomesWithMonster(EntityShulker.class));
		EntityRegistry.addSpawn(EntityJens.class, 50, 1, 4, EnumCreatureType.CREATURE, getBiomesWithCreature(EntityPig.class));

        //LootTableList.register(EntityJens.LOOT);
    }
    
	// Thanks to Vazkii!
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
	
	// Thanks to Vazkii!
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

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityApoRed.class, RenderApoRed.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDrachenlord.class, RenderDrachenlord.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityIslamist.class, RenderIslamist.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityJens.class, RenderJens.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMarcellDAvis.class, RenderMarcellDAvis.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMrBean.class, RenderMrBean.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySchalker.class, RenderSchalker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmavaCreeper.class, RenderSmavaCreeper.FACTORY);
    }
}