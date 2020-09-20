package mod.acgaming.spackenmobs;

import mod.acgaming.spackenmobs.events.TauntDrachenlordEvent;
import mod.acgaming.spackenmobs.misc.ModEntities;
import mod.acgaming.spackenmobs.misc.ModKeybinds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "spackenmobs", version = "RC4", acceptedMinecraftVersions = "[1.12.2]")
public class Spackenmobs
{
	public static final String MODID = "spackenmobs";
	public static final String VERSION = "RC4";

	public static final CreativeTabs SPACKENMOBS_TAB = new SpackenmobsTab();

	@Instance
	public static Spackenmobs instance;

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void preInitClient(FMLPreInitializationEvent event)
	{
		ModEntities.initModels();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new TauntDrachenlordEvent());
		ModKeybinds.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}