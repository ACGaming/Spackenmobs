package mod.acgaming.spackenmobs.items;

import net.minecraft.item.ItemFood;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.misc.ModItems;

public class ModItemFoodBase extends ItemFood
{
    public ModItemFoodBase(String name, int amount, float saturation, boolean isAnimalFood)
    {
        super(amount, saturation, isAnimalFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Spackenmobs.SPACKENMOBS_TAB);

        ModItems.ITEMS.add(this);
    }
}