package net.chixozhmix.space.worldgen.biome.surface;

import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(ModBlocks.COLD_DIRT.get());
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ModBlocks.COLD_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    private static final SurfaceRules.RuleSource FLESH_BLOCK = makeStateRule(com.github.elenterius.biomancy.init.ModBlocks.FLESH.get());
    private static final SurfaceRules.RuleSource FIBROUS_FLESH_BLOCK = makeStateRule(com.github.elenterius.biomancy.init.ModBlocks.FIBROUS_FLESH.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Поверхностные правила для биома с плотью
        SurfaceRules.RuleSource coldBiomeSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.COLD_PLAINTS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT) // Под плотью может быть земля
                        )
                )
        );

        // Стандартные поверхностные правила
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK),
                DIRT
        );

        return SurfaceRules.sequence(
                // Специальные биомы (плоть)
                coldBiomeSurface,

                // Стандартные правила
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface),

                // Правило по умолчанию (камень под всем)
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
        );
    }

    public static SurfaceRules.RuleSource makeRulesNether() {
        return SurfaceRules.sequence(

//                // Правила для биомов Незера
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.FLESH_GORGE), FLESH_BLOCK),
//                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FLESH_BLOCK),
//
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GASTRIC_GROTTO), FIBROUS_FLESH_BLOCK),
//                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FIBROUS_FLESH_BLOCK),
//                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FIBROUS_FLESH_BLOCK),
//
//                // Оригинальные правила для бедрока
//                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor",
//                        VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
//                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof",
//                        VerticalAnchor.belowTop(5), VerticalAnchor.top())), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState()))

                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(com.github.elenterius.biomancy.init.ModBlocks.FLESH.get().defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.state(com.github.elenterius.biomancy.init.ModBlocks.FIBROUS_FLESH.get().defaultBlockState()))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
