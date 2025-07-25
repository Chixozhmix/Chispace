package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.worldgen.ModBiomeModifirs;
import net.chixozhmix.space.worldgen.ModConfiguredFeature;
import net.chixozhmix.space.worldgen.ModPlacedFeature;
import net.chixozhmix.space.worldgen.biome.ModBiomes;
import net.chixozhmix.space.worldgen.dimension.KunabulaDimension;
import net.chixozhmix.space.worldgen.noise.KunabulaNoiseSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // Объединяем оба DimensionType в одном методе
            .add(Registries.DIMENSION_TYPE, context -> {
                KunabulaDimension.bootstrapType(context);
            })
            .add(Registries.NOISE_SETTINGS, KunabulaNoiseSettings::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeature::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifirs::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, context -> {
                KunabulaDimension.bootstrapStem(context);
            });

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ChiSpace.MOD_ID));
    }
}
