package net.chixozhmix.space.block.custom;

import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FleshVinesHeadBlock extends GrowingPlantHeadBlock {
    public FleshVinesHeadBlock(Properties pProperties) {
        super(pProperties,         // свойства блока (например, звук, прочность)
                Direction.DOWN,      // направление роста (вниз, как у лиан)
                Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),               // форма блока (VoxelShape)
                false,               // случайное смещение текстуры (true/false)
                0.1);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState blockState) {
        return blockState.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.FLESH_VINE.get();
    }
}
