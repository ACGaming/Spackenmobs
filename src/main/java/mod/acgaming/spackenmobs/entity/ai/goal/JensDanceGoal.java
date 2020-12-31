package mod.acgaming.spackenmobs.entity.ai.goal;

import mod.acgaming.spackenmobs.entity.JensEntity;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.ai.goal.Goal;

// Thanks to Akrivus!
public class JensDanceGoal extends Goal
{
	private final JensEntity jens;
	private final int searchRadius;
	private int lastDanceMoveTime = 0;
	private int danceStage = 0;

	public JensDanceGoal(JensEntity jens)
	{
		this.jens = jens;
		this.searchRadius = ConfigurationHandler.GENERAL.jens_search_distance.get();
	}

	public void resetTask()
	{
		this.lastDanceMoveTime = 0;
		this.danceStage = 0;
	}

	public boolean shouldContinueExecuting()
	{
		return shouldExecute();
	}

	public boolean shouldExecute()
	{
		for (int x = -this.searchRadius; x <= this.searchRadius; x++)
		{
			for (int y = -2; y <= 2; y++)
			{
				for (int z = -this.searchRadius; z <= this.searchRadius; z++)
				{
					if (this.jens.world.getBlockState(this.jens.getPosition().add(x, y, z)).getBlock() == Blocks.JUKEBOX && this.jens.world.getBlockState(this.jens.getPosition().add(x, y, z)).get(JukeboxBlock.HAS_RECORD))
						return true;
				}
			}
		}
		return false;
	}

	public void startExecuting()
	{
		this.danceStage = 1;
	}

	public void tick()
	{
		if (this.lastDanceMoveTime <= 0)
		{
			switch (this.danceStage)
			{
				case 1:
					this.danceStage = this.jens.world.rand.nextBoolean() ? 1 : 2;
					this.jens.setMotion(0, 0.5, 0);
					break;
				case 2:
					this.jens.setSneaking(true);
					this.jens.setMotion(0, -3, 0);
					this.danceStage = 3;
					break;
				case 3:
					this.danceStage = this.jens.world.rand.nextBoolean() ? 1 : 2;
					this.jens.setSneaking(false);
					break;
			}
			this.lastDanceMoveTime = this.jens.world.rand.nextInt(20) + 10;
		}
		this.lastDanceMoveTime--;
	}
}