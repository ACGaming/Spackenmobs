package mod.acgaming.spackenmobs.events;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class TauntDrachenlordEvent
{
	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event, EntityPlayer player, World world, int x, int y, int z)
	{
		final int aggroRange = 64;
		if (Keyboard.isKeyDown(Keyboard.KEY_J))
		{

		}
	}
}