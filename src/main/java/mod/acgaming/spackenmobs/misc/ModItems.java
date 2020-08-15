package mod.acgaming.spackenmobs.misc;
import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Spackenmobs.MODID)
public class ModItems
{
    public static final Item RAM = new ItemBase("ram", Spackenmobs.SPACKENMOBS_TAB);
    public static final Item RAM_ON_A_STICK = new ItemBase("ram_on_a_stick", Spackenmobs.SPACKENMOBS_TAB);
    public static final Item SURSTROEMMING = new ItemBase("surstroemming", Spackenmobs.SPACKENMOBS_TAB);
}