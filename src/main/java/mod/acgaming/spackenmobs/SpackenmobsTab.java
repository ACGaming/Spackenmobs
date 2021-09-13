package mod.acgaming.spackenmobs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.misc.ModItems;

public class SpackenmobsTab extends CreativeTabs
{
    public SpackenmobsTab()
    {
        super(Spackenmobs.MODID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.RAM);
    }
}