package net.chixozhmix.space.worldgen.biome;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.worldgen.ModPlacedFeature;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;


public class ModBiomes {

    public static final ResourceKey<Biome> FLESH_GORGE = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ChiSpace.MOD_ID, "flesh_gorge"));
    public static final ResourceKey<Biome> GASTRIC_GROTTO = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ChiSpace.MOD_ID, "gastric_grotto"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(FLESH_GORGE, fleshGorge(context));
        context.register(GASTRIC_GROTTO, gastricGrotto(context));
    }

    //Nether
    public static void globalNetherGeneration(BiomeGenerationSettings.Builder builder) {
        // Добавляем особенности генерации для Нижнего мира
        BiomeDefaultFeatures.addNetherDefaultOres(builder);
    }

    private static Biome fleshGorge (BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.FLESH_VINES_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.TENDONS_PLACED_KEY);

        // Добавляем особенности генерации для Нижнего мира
        globalNetherGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false) // В Нижнем мире нет осадков
                .downfall(0.0f)
                .temperature(2.0f) // Высокая температура
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x905957) // Цвет воды (если есть)
                        .waterFogColor(0x905957)
                        .skyColor(0x804040) // Красноватое небо
                        .fogColor(0x330808) // Темно-красный туман
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.01f))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    private static Biome gastricGrotto (BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.creatureGenerationProbability(1.0f);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.GUTS_VINES_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.TENDONS_PLACED_KEY);

        // Добавляем особенности генерации для Нижнего мира
        globalNetherGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false) // В Нижнем мире нет осадков
                .downfall(0.0f)
                .temperature(2.0f) // Высокая температура
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x905957) // Цвет воды (если есть)
                        .waterFogColor(0x905957)
                        .skyColor(0x718702) // Красноватое небо
                        .fogColor(0x668702) // Темно-красный туман
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.01f))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}
