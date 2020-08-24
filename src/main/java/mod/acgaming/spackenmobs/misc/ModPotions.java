package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.items.ItemPotion;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Spackenmobs.MODID)
public class ModPotions
{
	public static final Potion SURSTROEMMING_DRINK = new ItemPotion("surstroemming_drink", true, 42, 0, 0);
}