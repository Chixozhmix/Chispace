package net.chixozhmix.space.block.custom;

import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;

public class Guts extends GrowingPlantBlock {
    public Guts(Properties pProperties) {
        super(pProperties, Direction.DOWN,
                Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
                false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.GUTS_HEAD.get();
    }

    @Override
    protected Block getBodyBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.GUTS.get();
    }
}
