package mod.acgaming.spackenmobs;
import mod.acgaming.spackenmobs.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "spackenmobs", version = "1.0", acceptedMinecraftVersions = "[1.12.2]")
public class Spackenmobs
{
	public static final String MODID = "spackenmobs";
	public static final String VERSION = "1.0";
	
	public static final SoundEvent ENTITY_SMAVACREEPER_FUSE = new SoundEvent(new ResourceLocation("spackenmobs:entities.smava_creeper.fuse"));
	public static final SoundEvent ENTITY_SMAVACREEPER_BLOW = new SoundEvent(new ResourceLocation("spackenmobs:entities.smava_creeper.blow"));
	public static final SoundEvent ENTITY_SMAVACREEPER_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.smava_creeper.hurt"));
	public static final SoundEvent ENTITY_SMAVACREEPER_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.smava_creeper.ambient"));
	
	public static final SoundEvent ENTITY_MARCELLDAVIS_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.marcell_davis.ambient"));
	public static final SoundEvent ENTITY_MARCELLDAVIS_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.marcell_davis.hurt"));
	public static final SoundEvent ENTITY_MARCELLDAVIS_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.marcell_davis.death"));
	
	public static final SoundEvent ENTITY_ISLAMIST_FUSE = new SoundEvent(new ResourceLocation("spackenmobs:entities.islamist.fuse"));
	public static final SoundEvent ENTITY_ISLAMIST_BLOW = new SoundEvent(new ResourceLocation("spackenmobs:entities.islamist.blow"));
	public static final SoundEvent ENTITY_ISLAMIST_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.islamist.hurt"));
	public static final SoundEvent ENTITY_ISLAMIST_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.islamist.ambient"));
	
	public static final SoundEvent ENTITY_APORED_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.apored.ambient"));
	public static final SoundEvent ENTITY_APORED_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.apored.hurt"));
	public static final SoundEvent ENTITY_APORED_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.apored.death"));
	
	public static final SoundEvent ENTITY_MRBEAN_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.mr_bean.ambient"));
	public static final SoundEvent ENTITY_MRBEAN_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.mr_bean.hurt"));
	public static final SoundEvent ENTITY_MRBEAN_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.mr_bean.death"));
	
	public static final SoundEvent ENTITY_DRACHENLORD_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.drachenlord.ambient"));
	public static final SoundEvent ENTITY_DRACHENLORD_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.drachenlord.hurt"));
	public static final SoundEvent ENTITY_DRACHENLORD_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.drachenlord.death"));
	public static final SoundEvent ENTITY_DRACHENLORD_ANGRY = new SoundEvent(new ResourceLocation("spackenmobs:entities.drachenlord.angry"));
	
	public static final SoundEvent ENTITY_SCHALKER_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.schalker.ambient"));
	public static final SoundEvent ENTITY_SCHALKER_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.schalker.hurt"));
	public static final SoundEvent ENTITY_SCHALKER_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.schalker.death"));
	public static final SoundEvent ENTITY_SCHALKER_OPEN = new SoundEvent(new ResourceLocation("spackenmobs:entities.schalker.open"));
	public static final SoundEvent ENTITY_SCHALKER_SHOOT = new SoundEvent(new ResourceLocation("spackenmobs:entities.schalker.shoot"));
	
	public static final SoundEvent ENTITY_JENS_AMBIENT = new SoundEvent(new ResourceLocation("spackenmobs:entities.jens.ambient"));
	public static final SoundEvent ENTITY_JENS_HURT = new SoundEvent(new ResourceLocation("spackenmobs:entities.jens.hurt"));
	public static final SoundEvent ENTITY_JENS_DEATH = new SoundEvent(new ResourceLocation("spackenmobs:entities.jens.death"));

	@SidedProxy(clientSide = "mod.acgaming.spackenmobs.proxy.ClientProxy", serverSide = "mod.acgaming.spackenmobs.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance
	public static Spackenmobs instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit(e);
	}
}