package net.chixozhmix.space.worldgen;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeature {
    public static final ResourceKey<PlacedFeature> SOUL_GEM_ORE_PLACED_KEY = resourceKey("soul_gem_ore_placed");
    public static final ResourceKey<PlacedFeature> FLESH_VINES_PLACED_KEY = resourceKey("flesh_vines_placed");
    public static final ResourceKey<PlacedFeature> GUTS_VINES_PLACED_KEY = resourceKey("guts_vines_placed");
    public static final ResourceKey<PlacedFeature> TENDONS_PLACED_KEY = resourceKey("tendons_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {

        var configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //Ores
        register(context, SOUL_GEM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeature.SOUL_GEM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        //Vines
        register(context, FLESH_VINES_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeature.FLESH_VINES_KEY),
                List.of(
                        CountPlacement.of(25), // Количество попыток на чанк
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(32), // Минимальная высота
                                VerticalAnchor.absolute(120) // Максимальная высота
                        ),
                        SurfaceRelativeThresholdFilter.of(
                                Heightmap.Types.OCEAN_FLOOR_WG,
                                Integer.MIN_VALUE,
                                -5 // Максимальная разница высот для генерации
                        ),
                        RandomOffsetPlacement.vertical(ConstantInt.of(-1)), // Смещение вниз
                        BiomeFilter.biome()
                )
        );

        register(context, GUTS_VINES_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeature.GUTS_VINES_KEY),
                List.of(
                        CountPlacement.of(25), // Количество попыток на чанк
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(32), // Минимальная высота
                                VerticalAnchor.absolute(256) // Максимальная высота
                        ),
                        SurfaceRelativeThresholdFilter.of(
                                Heightmap.Types.OCEAN_FLOOR_WG,
                                Integer.MIN_VALUE,
                                -5 // Максимальная разница высот для генерации
                        ),
                        RandomOffsetPlacement.vertical(ConstantInt.of(-1)), // Смещение вниз
                        BiomeFilter.biome()
                )
        );

        register(context, TENDONS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeature.TENDONS_KEY),
                List.of(
                        CountPlacement.of(25), // Количество попыток на чанк
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(32), // Минимальная высота
                                VerticalAnchor.absolute(256) // Максимальная высота
                        ),
                        SurfaceRelativeThresholdFilter.of(
                                Heightmap.Types.OCEAN_FLOOR_WG,
                                Integer.MIN_VALUE,
                                -5 // Максимальная разница высот для генерации
                        ),
                        RandomOffsetPlacement.vertical(ConstantInt.of(1)), // Смещение вверх
                        BiomeFilter.biome()
                )
        );

//        register(context, TENDONS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeature.TENDONS_KEY),
//                List.of(
//                        RarityFilter.onAverageOnceEvery(1),
//                        InSquarePlacement.spread(),
//                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
//                        BiomeFilter.biome()
//                ));

    }

    private static ResourceKey<PlacedFeature> resourceKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ChiSpace.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?,?>> configuredFeatureHolder, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeatureHolder, List.copyOf(modifiers)));
    }
}
