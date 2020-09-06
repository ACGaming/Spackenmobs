package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.items.ModItemBase;
import mod.acgaming.spackenmobs.items.ModItemFoodDrink;
import mod.acgaming.spackenmobs.items.ModItemFoodEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<>();

	public static final Item RAM = new ModItemBase("ram");
	public static final Item RAM_ON_A_STICK = new ModItemBase("ram_on_a_stick");
	public static final Item SURSTROEMMING = new ModItemBase("surstroemming");
	public static final Item AHOJ_BRAUSE = new ModItemFoodEffect("ahoj_brause", 2, 0.15F, false, new PotionEffect(MobEffects.SPEED, 200, 5, false, true));
	public static final Item AHOJ_BRAUSE_DRINK = new ModItemFoodDrink("ahoj_brause_drink", 4, 0.3F, false, new PotionEffect(MobEffects.SPEED, 400, 10, false, true));
}