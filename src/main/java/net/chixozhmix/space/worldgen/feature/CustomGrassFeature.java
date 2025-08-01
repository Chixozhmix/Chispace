package net.chixozhmix.space.worldgen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CustomGrassFeature extends Feature<NoneFeatureConfiguration> {
    private final int maxLength;
    private final Block grassBlock;
    private final int searchRange; // Диапазон поиска опоры сверху

    public CustomGrassFeature(int maxLength, Block grassBlock, int searchRange) {
        super(NoneFeatureConfiguration.CODEC);
        this.maxLength = maxLength;
        this.grassBlock = grassBlock;
        this.searchRange = searchRange;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (!world.isEmptyBlock(origin)) {
            return false;
        }

        BlockPos.MutableBlockPos mutablePos = origin.mutable();
        for (int i = 0; i < searchRange; i++) {
            mutablePos.move(Direction.DOWN);
            if (world.getBlockState(mutablePos).isSolid()) {
                return placeGrassColumn(world, random, mutablePos.above(), maxLength);
            }
        }
        return false;
    }

    private boolean placeGrassColumn(WorldGenLevel world, RandomSource random, BlockPos topPos, int maxLength) {
        int length = random.nextIntBetweenInclusive(1, maxLength);
        BlockPos.MutableBlockPos mutablePos = topPos.mutable();

        // Ставим траву
        for (int i = 1; i < length; i++) {
            if (world.isEmptyBlock(mutablePos)) {
                world.setBlock(mutablePos, grassBlock.defaultBlockState(), 2);
                mutablePos.move(Direction.UP);
            } else {
                break;
            }
        }
        return true;
    }
}
