package net.chixozhmix.space.worldgen.biome.surface;

import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

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
