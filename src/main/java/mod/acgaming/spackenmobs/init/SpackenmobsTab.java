package mod.acgaming.spackenmobs.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import mod.acgaming.spackenmobs.Reference;

public class SpackenmobsTab
{
    public static final ItemGroup SPACKENMOBS_TAB = new ItemGroup(Reference.MOD_ID)
    {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon()
        {
            return new ItemStack(SpackenmobsRegistry.SURSTROEMMING.get());
        }
    };
}