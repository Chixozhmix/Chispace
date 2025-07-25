package net.chixozhmix.space.worldgen.biome.surface;

import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    public static SurfaceRules.RuleSource makeRulesNether() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(com.github.elenterius.biomancy.init.ModBlocks.FLESH.get().defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.state(com.github.elenterius.biomancy.init.ModBlocks.FIBROUS_FLESH.get().defaultBlockState()))
        );
    }
}
