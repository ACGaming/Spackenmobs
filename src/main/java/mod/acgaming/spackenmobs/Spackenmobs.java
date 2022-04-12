package mod.acgaming.spackenmobs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.misc.ModEntities;

@Mod(modid = Spackenmobs.MODID, version = Spackenmobs.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class Spackenmobs
{
    public static final String MODID = "spackenmobs";
    public static final String VERSION = "1.12.2-1.9.0-CF";

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

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}