package mod.acgaming.spackenmobs.entities;

import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;

// Thanks to Akrivus!
public class EntityAIDance extends EntityAIBase {
    private final EntityJens jens;

    private final int searchRadius;

    private int lastDanceMoveTime = 0;

    private int danceStage = 0;

    public EntityAIDance(EntityJens jens, int searchRadius) {
        this.jens = jens;
        this.searchRadius = searchRadius;
    }

    @Override
    public void resetTask() {
        this.lastDanceMoveTime = 0;
        this.danceStage = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return shouldExecute();
    }

    @Override
    public boolean shouldExecute() {
        for(int x = -this.searchRadius; x <= this.searchRadius; x++) {
            for(int y = -2; y <= 2; y++) {
                for(int z = -this.searchRadius; z <= this.searchRadius; z++) {
                    if(this.jens.world.getBlockState(this.jens.getPosition().add(x, y, z)).getBlock() == Blocks.JUKEBOX
                        && this.jens.world.getBlockState(this.jens.getPosition().add(x, y, z)).getValue(BlockJukebox.HAS_RECORD))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void startExecuting() {
        this.danceStage = 1;
    }

    @Override
    public void updateTask() {
        if(this.lastDanceMoveTime <= 0) {
            switch(this.danceStage) {
                case 1:
                    this.danceStage = this.jens.world.rand.nextBoolean() ? 1 : 2;
                    this.jens.motionY = 0.42D;
                    break;
                case 2:
                    this.jens.setSneaking(true);
                    this.jens.motionY = -3.0D;
                    this.danceStage = 3;
                    break;
                case 3:
                    this.danceStage = this.jens.world.rand.nextBoolean() ? 1 : 2;
                    this.jens.setSneaking(false);
                    break;
            }
            this.lastDanceMoveTime = this.jens.world.rand.nextInt(10) + 5;
        }
        this.lastDanceMoveTime--;
    }
}
