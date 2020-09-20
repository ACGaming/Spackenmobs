package mod.acgaming.spackenmobs.events;

import mod.acgaming.spackenmobs.entities.EntityDrachenlord;
import mod.acgaming.spackenmobs.misc.ModKeybinds;
import mod.acgaming.spackenmobs.misc.ModSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class TauntDrachenlordEvent
{
	private void PigmanAggro(World worldIn, BlockPos pos, EntityPlayer player)
	{
		float radius = 64;

		BlockPos corner1 = new BlockPos(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius);
		BlockPos corner2 = new BlockPos(pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius);
		AxisAlignedBB AABB_radius = new AxisAlignedBB(corner1, corner2);
		List<EntityDrachenlord> drachenlords = worldIn.getEntitiesWithinAABB(EntityDrachenlord.class, AABB_radius);

		for (EntityDrachenlord drachenlord : drachenlords)
		{
				DamageSource damageSource = new EntityDamageSourceIndirect("generic", player, player);
				drachenlord.becomeAngryAt(player);
		}
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (ModKeybinds.taunt.isPressed())
		{
			
		}
	}
}