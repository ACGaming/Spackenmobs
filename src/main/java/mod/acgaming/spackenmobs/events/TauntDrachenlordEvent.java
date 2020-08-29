package mod.acgaming.spackenmobs.events;

import org.lwjgl.input.Keyboard;

import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class TauntDrachenlordEvent
{
	public static AxisAlignedBB getBoundingBox(double x, double y, double z, int hRadius, int vRadius)
	{
		return new AxisAlignedBB(x - hRadius, y - vRadius, z - hRadius, x + hRadius, y + vRadius, z + hRadius);
	}

	public static void makeAngry(EntityPlayer player, EntityDrachenlord drache)
	{
		drache.attackEntityFrom(DamageSource.causePlayerDamage(player), 0);
	}

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event, EntityDrachenlord drache, EntityPlayer player)
	{
		final int aggroRange = 64;
		if (Keyboard.isKeyDown(Keyboard.KEY_J))
		{

		}
	}
}