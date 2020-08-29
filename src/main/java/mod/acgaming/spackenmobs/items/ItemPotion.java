package mod.acgaming.spackenmobs.items;

import net.minecraft.potion.Potion;

public class ItemPotion extends Potion
{
	public ItemPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY)
	{
		super(isBadPotion, color);
		setPotionName("effect." + name);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(name);
	}
}