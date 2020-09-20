package mod.acgaming.spackenmobs.misc;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ModKeybinds
{
	public static KeyBinding taunt;
	public static void init()
	{
		taunt = new KeyBinding("key.taunt", Keyboard.KEY_J, "key.categories.spackenmobs");
		ClientRegistry.registerKeyBinding(taunt);
	}
}