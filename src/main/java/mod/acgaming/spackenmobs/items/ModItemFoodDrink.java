package mod.acgaming.spackenmobs.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ModItemFoodDrink extends ModItemFoodBase
{
	PotionEffect effect;

	public ModItemFoodDrink(String name, int amount, float saturation, boolean isAnimalFood, PotionEffect effect)
	{
		super(name, amount, saturation, isAnimalFood);
		setAlwaysEdible();

		this.effect = effect;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if (!worldIn.isRemote)
		{
			player.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
			player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;
	}
}