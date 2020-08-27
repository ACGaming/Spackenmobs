package mod.acgaming.spackenmobs.misc;

import mod.acgaming.spackenmobs.Spackenmobs;
import mod.acgaming.spackenmobs.items.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Spackenmobs.MODID)
public class ModItems
{
	public static final Item RAM = new ItemBase("ram", Spackenmobs.SPACKENMOBS_TAB);
	public static final Item RAM_ON_A_STICK = new ItemBase("ram_on_a_stick", Spackenmobs.SPACKENMOBS_TAB);
	public static final Item SURSTROEMMING = new ItemBase("surstroemming", Spackenmobs.SPACKENMOBS_TAB);

	@SubscribeEvent
	public void SurstroemmingEvent(PlayerTickEvent event)
	{
		if (event.player instanceof EntityPlayer)
		{
			EntityPlayer player = event.player;
			if (player.inventory.hasItemStack(new ItemStack(ModItems.SURSTROEMMING)))
			{
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
			}
		}
	}
}