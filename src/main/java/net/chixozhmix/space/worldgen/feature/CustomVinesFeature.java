package net.chixozhmix.space.worldgen.feature;

import com.mojang.serialization.Codec;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CustomVinesFeature extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] DIRECTIONS = {Direction.DOWN};
    private final int maxLength; // Максимальная длина лианы

    public CustomVinesFeature(int maxLength) {
        super(NoneFeatureConfiguration.CODEC);
        this.maxLength = maxLength;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (!world.isEmptyBlock(origin)) {
            return false;
        }

        // Ищем ближайший твердый блок сверху
        BlockPos.MutableBlockPos mutablePos = origin.mutable();
        for (int i = 0; i < 10; i++) {
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

        for (int i = 0; i < length; i++) {
            if (world.isEmptyBlock(mutablePos)) {
                world.setBlock(mutablePos, ModBlocks.FLESH_VINE.get().defaultBlockState(), 2);
                mutablePos.move(Direction.DOWN);
            } else {
                break;
            }
        }
        return true;
    }
}
