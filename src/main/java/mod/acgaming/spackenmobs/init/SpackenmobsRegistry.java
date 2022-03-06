package mod.acgaming.spackenmobs.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Food;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import mod.acgaming.spackenmobs.Reference;
import mod.acgaming.spackenmobs.entity.*;
import mod.acgaming.spackenmobs.item.CustomSpawnEggItem;

public class SpackenmobsRegistry
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    // --- ENTITIES ---
    // ApoRed
    public static final RegistryObject<EntityType<ApoRedEntity>> APORED = ENTITIES.register("apored",
        () -> register("apored", EntityType.Builder.of(ApoRedEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.99F).clientTrackingRange(10)));
    public static final RegistryObject<Item> APORED_SPAWN_EGG = ITEMS.register("apored_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.APORED::get, 2039583, 16711680, itemBuilder()));
    // Baka Mitai Creeper
    public static final RegistryObject<EntityType<BakaMitaiCreeperEntity>> BAKAMITAI_CREEPER = ENTITIES.register("bakamitai_creeper",
        () -> register("bakamitai_creeper", EntityType.Builder.of(BakaMitaiCreeperEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(10)));
    public static final RegistryObject<Item> BAKAMITAI_CREEPER_SPAWN_EGG = ITEMS.register("bakamitai_creeper_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.BAKAMITAI_CREEPER::get, 826890, 0, itemBuilder()));
    // DagiBee
    // public static final RegistryObject<EntityType<DagiBeeEntity>> DAGIBEE = ENTITIES.register("dagibee",
    //     () -> register("dagibee", EntityType.Builder.of(DagiBeeEntity::new, EntityClassification.CREATURE)
    //         .sized(0.6F, 0.6F).clientTrackingRange(10)));
    // Drachenlord
    public static final RegistryObject<EntityType<DrachenlordEntity>> DRACHENLORD = ENTITIES.register("drachenlord",
        () -> register("drachenlord", EntityType.Builder.of(DrachenlordEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.95F).clientTrackingRange(10)));
    public static final RegistryObject<Item> DRACHENLORD_SPAWN_EGG = ITEMS.register("drachenlord_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.DRACHENLORD::get, 15256745, 8738878, itemBuilder()));
    // Gisela
    public static final RegistryObject<EntityType<GiselaEntity>> GISELA = ENTITIES.register("gisela",
        () -> register("gisela", EntityType.Builder.of(GiselaEntity::new, EntityClassification.CREATURE)
            .sized(0.6F, 1.8F).clientTrackingRange(10)));
    public static final RegistryObject<Item> GISELA_SPAWN_EGG = ITEMS.register("gisela_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.GISELA::get, 39835, 16448250, itemBuilder()));
    // Holzstammhuhn
    public static final RegistryObject<EntityType<HolzstammhuhnEntity>> HOLZSTAMMHUHN = ENTITIES.register("holzstammhuhn",
        () -> register("holzstammhuhn", EntityType.Builder.of(HolzstammhuhnEntity::new, EntityClassification.CREATURE)
            .sized(0.4F, 0.7F).clientTrackingRange(10)));
    public static final RegistryObject<Item> HOLZSTAMMHUHN_SPAWN_EGG = ITEMS.register("holzstammhuhn_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.HOLZSTAMMHUHN::get, 12096347, 5295899, itemBuilder()));
    // Islamist
    public static final RegistryObject<EntityType<IslamistEntity>> ISLAMIST = ENTITIES.register("islamist",
        () -> register("islamist", EntityType.Builder.of(IslamistEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(10)));
    public static final RegistryObject<Item> ISLAMIST_SPAWN_EGG = ITEMS.register("islamist_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.ISLAMIST::get, 15263976, 15211548, itemBuilder()));
    // Jens
    public static final RegistryObject<EntityType<JensEntity>> JENS = ENTITIES.register("jens",
        () -> register("jens", EntityType.Builder.of(JensEntity::new, EntityClassification.CREATURE)
            .sized(0.6F, 1.8F).clientTrackingRange(10)));
    public static final RegistryObject<Item> JENS_SPAWN_EGG = ITEMS.register("jens_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.JENS::get, 6704526, 6767911, itemBuilder()));
    // Marcell D'Avis
    public static final RegistryObject<EntityType<MarcellDAvisEntity>> MARCELLDAVIS = ENTITIES.register("marcell_davis",
        () -> register("marcell_davis", EntityType.Builder.of(MarcellDAvisEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.95F).clientTrackingRange(10)));
    public static final RegistryObject<Item> MARCELLDAVIS_SPAWN_EGG = ITEMS.register("marcell_davis_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.MARCELLDAVIS::get, 15759, 16777215, itemBuilder()));
    // Mr. Bean
    public static final RegistryObject<EntityType<MrBeanEntity>> MRBEAN = ENTITIES.register("mr_bean",
        () -> register("mr_bean", EntityType.Builder.of(MrBeanEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.95F).clientTrackingRange(10)));
    public static final RegistryObject<Item> MRBEAN_SPAWN_EGG = ITEMS.register("mr_bean_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.MRBEAN::get, 4802350, 3220238, itemBuilder()));
    // MZTEWolf
    public static final RegistryObject<EntityType<MZTEWolfEntity>> MZTEWOLF = ENTITIES.register("mztewolf",
        () -> register("mztewolf", EntityType.Builder.of(MZTEWolfEntity::new, EntityClassification.CREATURE)
            .sized(0.6F, 0.85F).clientTrackingRange(10)));
    public static final RegistryObject<Item> MZTEWOLF_SPAWN_EGG = ITEMS.register("mztewolf_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.MZTEWOLF::get, 16711680, 0, itemBuilder()));
    // Schalker
    public static final RegistryObject<EntityType<SchalkerEntity>> SCHALKER = ENTITIES.register("schalker",
        () -> register("schalker", EntityType.Builder.of(SchalkerEntity::new, EntityClassification.MONSTER)
            .sized(1.0F, 1.0F).clientTrackingRange(10)));
    public static final RegistryObject<Item> SCHALKER_SPAWN_EGG = ITEMS.register("schalker_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.SCHALKER::get, 24745, 16777215, itemBuilder()));
    // Smava Creeper
    public static final RegistryObject<EntityType<SmavaCreeperEntity>> SMAVA_CREEPER = ENTITIES.register("smava_creeper",
        () -> register("smava_creeper", EntityType.Builder.of(SmavaCreeperEntity::new, EntityClassification.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(10)));
    public static final RegistryObject<Item> SMAVA_CREEPER_SPAWN_EGG = ITEMS.register("smava_creeper_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.SMAVA_CREEPER::get, 7649828, 11053224, itemBuilder()));
    // --- ITEMS ---
    public static final RegistryObject<Item> AHOJ_BRAUSE = ITEMS.register("ahoj_brause", () -> new Item(new Item.Properties().tab(SpackenmobsTab.SPACKENMOBS_TAB).food(new Food.Builder().effect(new EffectInstance(Effects.MOVEMENT_SPEED, 200, 4), 1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> AHOJ_BRAUSE_DRINK = ITEMS.register("ahoj_brause_drink", () -> new HoneyBottleItem(new Item.Properties().tab(SpackenmobsTab.SPACKENMOBS_TAB).food(new Food.Builder().effect(new EffectInstance(Effects.MOVEMENT_SPEED, 400, 9), 1.0F).alwaysEat().build())));
    // Friedrich Liechtenstein
    public static final RegistryObject<EntityType<FriedrichLiechtensteinEntity>> FRIEDRICH_LIECHTENSTEIN = ENTITIES.register("friedrich",
        () -> register("friedrich", EntityType.Builder.of(FriedrichLiechtensteinEntity::new, EntityClassification.CREATURE)
            .sized(0.6F, 1.8F).clientTrackingRange(10)));
    public static final RegistryObject<Item> FRIEDRICH_LIECHTENSTEIN_SPAWN_EGG = ITEMS.register("friedrich_spawn_egg", () -> new CustomSpawnEggItem(SpackenmobsRegistry.FRIEDRICH_LIECHTENSTEIN::get, 16447728, 15878595, itemBuilder()));
    public static final RegistryObject<Item> RAM = ITEMS.register("ram", () -> new Item(new Item.Properties().tab(SpackenmobsTab.SPACKENMOBS_TAB)));
    public static final RegistryObject<Item> RAM_ON_A_STICK = ITEMS.register("ram_on_a_stick", () -> new Item(new Item.Properties().stacksTo(1).tab(SpackenmobsTab.SPACKENMOBS_TAB)));
    public static final RegistryObject<Item> SURSTROEMMING = ITEMS.register("surstroemming", () -> new Item(new Item.Properties().tab(SpackenmobsTab.SPACKENMOBS_TAB).food(new Food.Builder().nutrition(6).saturationMod(3).effect(new EffectInstance(Effects.CONFUSION, 200, 0), 1.0F).build())));
    // --- SOUND EVENTS ---
    // Baka Mitai Creeper
    public static final RegistryObject<SoundEvent> ENTITY_BAKAMITAI_CREEPER_FUSE = SOUND_EVENTS.register("entities.bakamitai_creeper.fuse", () -> createSound("entities.bakamitai_creeper.fuse"));
    public static final RegistryObject<SoundEvent> ENTITY_BAKAMITAI_CREEPER_BLOW = SOUND_EVENTS.register("entities.bakamitai_creeper.blow", () -> createSound("entities.bakamitai_creeper.blow"));
    // ApoRed
    public static final RegistryObject<SoundEvent> ENTITY_APORED_AMBIENT = SOUND_EVENTS.register("entities.apored.ambient", () -> createSound("entities.apored.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_APORED_HURT = SOUND_EVENTS.register("entities.apored.hurt", () -> createSound("entities.apored.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_APORED_DEATH = SOUND_EVENTS.register("entities.apored.death", () -> createSound("entities.apored.death"));
    // Drachenlord
    public static final RegistryObject<SoundEvent> ENTITY_DRACHENLORD_AMBIENT = SOUND_EVENTS.register("entities.drachenlord.ambient", () -> createSound("entities.drachenlord.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_DRACHENLORD_HURT = SOUND_EVENTS.register("entities.drachenlord.hurt", () -> createSound("entities.drachenlord.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_DRACHENLORD_DEATH = SOUND_EVENTS.register("entities.drachenlord.death", () -> createSound("entities.drachenlord.death"));
    public static final RegistryObject<SoundEvent> ENTITY_DRACHENLORD_ANGRY = SOUND_EVENTS.register("entities.drachenlord.angry", () -> createSound("entities.drachenlord.angry"));
    // Friedrich Liechtenstein
    public static final RegistryObject<SoundEvent> ENTITY_FRIEDRICH_AMBIENT = SOUND_EVENTS.register("entities.friedrich.ambient", () -> createSound("entities.friedrich.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_FRIEDRICH_HURT = SOUND_EVENTS.register("entities.friedrich.hurt", () -> createSound("entities.friedrich.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_FRIEDRICH_DEATH = SOUND_EVENTS.register("entities.friedrich.death", () -> createSound("entities.friedrich.death"));
    // Gisela
    public static final RegistryObject<SoundEvent> ENTITY_GISELA_AMBIENT = SOUND_EVENTS.register("entities.gisela.ambient", () -> createSound("entities.gisela.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_GISELA_HURT = SOUND_EVENTS.register("entities.gisela.hurt", () -> createSound("entities.gisela.hurt"));
    //public static final RegistryObject<SoundEvent> ENTITY_GISELA_DEATH = SOUND_EVENTS.register("entities.gisela.death", () -> createSound("entities.gisela.death"));
    // Islamist
    public static final RegistryObject<SoundEvent> ENTITY_ISLAMIST_FUSE = SOUND_EVENTS.register("entities.islamist.fuse", () -> createSound("entities.islamist.fuse"));
    public static final RegistryObject<SoundEvent> ENTITY_ISLAMIST_BLOW = SOUND_EVENTS.register("entities.islamist.blow", () -> createSound("entities.islamist.blow"));
    public static final RegistryObject<SoundEvent> ENTITY_ISLAMIST_HURT = SOUND_EVENTS.register("entities.islamist.hurt", () -> createSound("entities.islamist.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_ISLAMIST_AMBIENT = SOUND_EVENTS.register("entities.islamist.ambient", () -> createSound("entities.islamist.ambient"));
    // Jens
    public static final RegistryObject<SoundEvent> ENTITY_JENS_AMBIENT = SOUND_EVENTS.register("entities.jens.ambient", () -> createSound("entities.jens.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_JENS_HURT = SOUND_EVENTS.register("entities.jens.hurt", () -> createSound("entities.jens.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_JENS_DEATH = SOUND_EVENTS.register("entities.jens.death", () -> createSound("entities.jens.death"));
    public static final RegistryObject<SoundEvent> ENTITY_JENS_EAT = SOUND_EVENTS.register("entities.jens.eat", () -> createSound("entities.jens.eat"));
    public static final RegistryObject<SoundEvent> ENTITY_JENS_POOP = SOUND_EVENTS.register("entities.jens.poop", () -> createSound("entities.jens.poop"));
    // Marcell D'Avis
    public static final RegistryObject<SoundEvent> ENTITY_MARCELLDAVIS_AMBIENT = SOUND_EVENTS.register("entities.marcell_davis.ambient", () -> createSound("entities.marcell_davis.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_MARCELLDAVIS_HURT = SOUND_EVENTS.register("entities.marcell_davis.hurt", () -> createSound("entities.marcell_davis.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_MARCELLDAVIS_DEATH = SOUND_EVENTS.register("entities.marcell_davis.death", () -> createSound("entities.marcell_davis.death"));
    // Mr. Bean
    public static final RegistryObject<SoundEvent> ENTITY_MRBEAN_AMBIENT = SOUND_EVENTS.register("entities.mr_bean.ambient", () -> createSound("entities.mr_bean.ambient"));
    public static final RegistryObject<SoundEvent> ENTITY_MRBEAN_HURT = SOUND_EVENTS.register("entities.mr_bean.hurt", () -> createSound("entities.mr_bean.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_MRBEAN_DEATH = SOUND_EVENTS.register("entities.mr_bean.death", () -> createSound("entities.mr_bean.death"));
    // MZTEWolf
    public static final RegistryObject<SoundEvent> ENTITY_MZTEWOLF_AMBIENT = SOUND_EVENTS.register("entities.mztewolf.ambient", () -> createSound("entities.mztewolf.ambient"));
    // Schalker
    public static final RegistryObject<SoundEvent> ENTITY_SCHALKER_SHOOT = SOUND_EVENTS.register("entities.schalker.shoot", () -> createSound("entities.schalker.shoot"));
    public static final RegistryObject<SoundEvent> ENTITY_SCHALKER_OPEN = SOUND_EVENTS.register("entities.schalker.open", () -> createSound("entities.schalker.open"));
    public static final RegistryObject<SoundEvent> ENTITY_SCHALKER_DEATH = SOUND_EVENTS.register("entities.schalker.death", () -> createSound("entities.schalker.death"));
    public static final RegistryObject<SoundEvent> ENTITY_SCHALKER_AMBIENT = SOUND_EVENTS.register("entities.schalker.ambient", () -> createSound("entities.schalker.ambient"));
    // Smava Creeper
    public static final RegistryObject<SoundEvent> ENTITY_SMAVA_CREEPER_FUSE = SOUND_EVENTS.register("entities.smava_creeper.fuse", () -> createSound("entities.smava_creeper.fuse"));
    public static final RegistryObject<SoundEvent> ENTITY_SMAVA_CREEPER_BLOW = SOUND_EVENTS.register("entities.smava_creeper.blow", () -> createSound("entities.smava_creeper.blow"));
    public static final RegistryObject<SoundEvent> ENTITY_SMAVA_CREEPER_HURT = SOUND_EVENTS.register("entities.smava_creeper.hurt", () -> createSound("entities.smava_creeper.hurt"));
    public static final RegistryObject<SoundEvent> ENTITY_SMAVA_CREEPER_AMBIENT = SOUND_EVENTS.register("entities.smava_creeper.ambient", () -> createSound("entities.smava_creeper.ambient"));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates)
    {
        return builder.setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
    {
        return register(id, builder, true);
    }

    private static SoundEvent createSound(String name)
    {
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, name);
        return new SoundEvent(resourceLocation);
    }

    private static Item.Properties itemBuilder()
    {
        return new Item.Properties().tab(SpackenmobsTab.SPACKENMOBS_TAB);
    }
}