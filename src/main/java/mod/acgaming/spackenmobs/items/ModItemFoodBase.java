package mod.acgaming.spackenmobs.items;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.misc.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}