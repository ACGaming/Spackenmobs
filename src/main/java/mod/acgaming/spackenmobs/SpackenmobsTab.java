package mod.acgaming.spackenmobs;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mod.acgaming.spackenmobs.misc.ModItems;

public class SpackenmobsTab extends CreativeTabs
{
    public static ArrayList<ItemStack> eggs = new ArrayList<>();

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

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        super.displayAllRelevantItems(list);
        list.addAll(eggs);
    }
}