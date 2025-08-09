package net.chixozhmix.space.worldgen.biome.surface;

import com.github.elenterius.biomancy.init.ModBlocks;
import net.chixozhmix.space.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource FLESH = makeRules(ModBlocks.FLESH.get());
    private static final SurfaceRules.RuleSource FIBROUS_FLESH = makeRules(ModBlocks.FIBROUS_FLESH.get());

    public static SurfaceRules.RuleSource makeRulesNether() {
        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.FLESH_GORGE),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FLESH)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FIBROUS_FLESH),

                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GASTRIC_GROTTO),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FIBROUS_FLESH)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FIBROUS_FLESH),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, FIBROUS_FLESH))


        );
    }

    private static SurfaceRules.RuleSource makeRules(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
