package mod.acgaming.spackenmobs.entity.ai.goal;

import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.ai.goal.Goal;

import mod.acgaming.spackenmobs.entity.JensEntity;
import mod.acgaming.spackenmobs.util.ConfigurationHandler;

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

    public boolean canUse()
    {
        for (int x = -this.searchRadius; x <= this.searchRadius; x++)
        {
            for (int y = -2; y <= 2; y++)
            {
                for (int z = -this.searchRadius; z <= this.searchRadius; z++)
                {
                    if (this.jens.level.getBlockState(this.jens.blockPosition().offset(x, y, z)).getBlock() == Blocks.JUKEBOX && this.jens.level.getBlockState(this.jens.blockPosition().offset(x, y, z)).getValue(JukeboxBlock.HAS_RECORD))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean canContinueToUse()
    {
        return canUse();
    }

    public void start()
    {
        this.danceStage = 1;
    }

    public void stop()
    {
        this.lastDanceMoveTime = 0;
        this.danceStage = 0;
    }

    public void tick()
    {
        if (this.lastDanceMoveTime <= 0)
        {
            switch (this.danceStage)
            {
                case 1:
                    this.danceStage = this.jens.level.random.nextBoolean() ? 1 : 2;
                    this.jens.setDeltaMovement(0, 0.5, 0);
                    break;
                case 2:
                    this.jens.setShiftKeyDown(true);
                    this.jens.setDeltaMovement(0, -3, 0);
                    this.danceStage = 3;
                    break;
                case 3:
                    this.danceStage = this.jens.level.random.nextBoolean() ? 1 : 2;
                    this.jens.setShiftKeyDown(false);
                    break;
            }
            this.lastDanceMoveTime = this.jens.level.random.nextInt(20) + 10;
        }
        this.lastDanceMoveTime--;
    }
}