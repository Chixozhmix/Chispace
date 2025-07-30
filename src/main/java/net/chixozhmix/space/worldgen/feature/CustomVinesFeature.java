package net.chixozhmix.space.worldgen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CustomVinesFeature extends Feature<NoneFeatureConfiguration> {
    private final int maxLength;
    private final Block vineBlock;
    private final Block vineHeadBlock;
    private final int searchRange; // Диапазон поиска опоры сверху

    public CustomVinesFeature(int maxLength, Block vineBlock, Block vineHeadBlock, int searchRange) {
        super(NoneFeatureConfiguration.CODEC);
        this.maxLength = maxLength;
        this.vineBlock = vineBlock;
        this.vineHeadBlock = vineHeadBlock;
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
            mutablePos.move(Direction.UP);
            if (world.getBlockState(mutablePos).isSolid()) {
                return placeVineColumn(world, random, mutablePos.below(), maxLength);
            }
        }
        return false;
    }

    private boolean placeVineColumn(WorldGenLevel world, RandomSource random, BlockPos topPos, int maxLength) {
        int length = random.nextIntBetweenInclusive(1, maxLength);
        BlockPos.MutableBlockPos mutablePos = topPos.mutable();

        // Ставим голову лианы
        world.setBlock(mutablePos, vineHeadBlock.defaultBlockState(), 2);
        mutablePos.move(Direction.DOWN);

        // Ставим тело лианы
        for (int i = 1; i < length; i++) {
            if (world.isEmptyBlock(mutablePos)) {
                world.setBlock(mutablePos, vineBlock.defaultBlockState(), 2);
                mutablePos.move(Direction.DOWN);
            } else {
                break;
            }
        }
        return true;
    }
}
