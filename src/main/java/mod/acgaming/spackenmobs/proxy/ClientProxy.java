package mod.acgaming.spackenmobs.proxy;
import mod.acgaming.spackenmobs.ModEntities;
import mod.acgaming.spackenmobs.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		ModEntities.initModels();
	}
	
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		ModItems.initModels();
	}
}