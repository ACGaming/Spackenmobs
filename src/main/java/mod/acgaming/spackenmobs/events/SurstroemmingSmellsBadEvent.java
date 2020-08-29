package mod.acgaming.spackenmobs.events;

import mod.acgaming.spackenmobs.misc.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SurstroemmingSmellsBadEvent
{
	@SubscribeEvent
	public void onEvent(PlayerTickEvent event)
	{
		if (event.player != null)
		{
			EntityPlayer player = event.player;
			if (player.inventory.hasItemStack(new ItemStack(ModItems.SURSTROEMMING)))
			{
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
			}
		}
	}
}