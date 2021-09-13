package mod.acgaming.spackenmobs.items;

import net.minecraft.item.Item;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.misc.ModItems;

public class ModItemBase extends Item
{
    public ModItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Spackenmobs.SPACKENMOBS_TAB);

        ModItems.ITEMS.add(this);
    }
}