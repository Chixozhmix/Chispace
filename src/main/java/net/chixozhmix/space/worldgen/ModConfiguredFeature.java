package net.chixozhmix.space.worldgen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.worldgen.feature.CustomVinesFeature;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeature {
    //Ore Demension Key
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_GEM_ORE_KEY = registerKey("soul_gem_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLESH_VINES_KEY = registerKey("flesh_vines_key");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ChiSpace.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //Ore
        List<OreConfiguration.TargetBlockState> coldPlantsSoulGemOre = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.SOUL_GEM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.SOUL_GEM_ORE.get().defaultBlockState()));

        register(context, SOUL_GEM_ORE_KEY, Feature.ORE, new OreConfiguration(coldPlantsSoulGemOre, 9));

        //Vines
        // Используйте зарегистрированный Feature
        register(context, FLESH_VINES_KEY,
                ChiSpace.CUSTOM_VINES.get(), // Используем RegistryObject
                NoneFeatureConfiguration.INSTANCE
        );

//        context.register(FLESH_VINES_KEY,
//                new ConfiguredFeature<>(
//                        new CustomVinesFeature(12), // Максимальная длина 12 блоков
//                        NoneFeatureConfiguration.INSTANCE
//                )
//        );
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstapContext<ConfiguredFeature<?,?>> context, ResourceKey<ConfiguredFeature<?,?>> key,
                  F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
