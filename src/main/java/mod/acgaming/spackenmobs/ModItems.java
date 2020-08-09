package mod.acgaming.spackenmobs;
import mod.acgaming.spackenmobs.items.ItemRAM;
import mod.acgaming.spackenmobs.items.ItemRAMOnAStick;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
    @GameRegistry.ObjectHolder("spackenmobs:ram")
    public static ItemRAM ram;
    
    @GameRegistry.ObjectHolder("spackenmobs:ram_on_a_stick")
    public static ItemRAMOnAStick ram_on_a_stick;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        ram.initModel();
        ram_on_a_stick.initModel();
    }
}