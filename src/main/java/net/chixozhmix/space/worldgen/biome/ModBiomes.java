package net.chixozhmix.space.worldgen.biome;

import com.github.elenterius.biomancy.init.ModEntityTypes;
import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.worldgen.ModPlacedFeature;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;


public class ModBiomes {
    public static final ResourceKey<Biome> COLD_PLAINTS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ChiSpace.MOD_ID, "cold_plaints"));

    public static final ResourceKey<Biome> FLESH_GORGE = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ChiSpace.MOD_ID, "flesh_gorge"));
    public static final ResourceKey<Biome> GASTRIC_GROTTO = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ChiSpace.MOD_ID, "gastric_grotto"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(COLD_PLAINTS, coldPlantsBiome(context));
        context.register(FLESH_GORGE, fleshGorge(context));
        context.register(GASTRIC_GROTTO, gastricGrotto(context));
    }

    //Overworld
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    //Nether
    public static void globalNetherGeneration(BiomeGenerationSettings.Builder builder) {
        // Добавляем особенности генерации для Нижнего мира
        BiomeDefaultFeatures.addNetherDefaultOres(builder);
    }

    //Cold Plants Biome
    private static Biome coldPlantsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.MORDAN_SERVANT_ENTITY.get(), 2, 3, 5));

        //BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.COLD_OAK_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x7fa4c7)
                        .waterFogColor(0x7fa4c7)
                        .skyColor(0x9bc5ca)
                        .grassColorOverride(0x7badc6)
                        .foliageColorOverride(0x8cbfd9)
                        .fogColor(0x92bdd3)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(Sound.BAR_BRAWL.getHolder().get()))
                        .build())
                .build();
    }

    private static Biome fleshGorge (BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // Добавляем мобов
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_COW.get(), 20, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_CHICKEN.get(), 20, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_PIG.get(), 30, 1, 2));

        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_BLOB.get(), 30, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Добавляем особенности генерации для Нижнего мира
        globalNetherGeneration(biomeBuilder);

        // Можно добавить кастомные особенности
        //biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeature.INFERNAL_CRYSTALS_KEY);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.SMOLDERING_BUSHES_KEY);


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

        // Добавляем мобов
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_COW.get(), 20, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_CHICKEN.get(), 20, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLESH_PIG.get(), 30, 1, 2));

        spawnBuilder.creatureGenerationProbability(1.0f);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Добавляем особенности генерации для Нижнего мира
        globalNetherGeneration(biomeBuilder);

        // Можно добавить кастомные особенности
        //biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeature.INFERNAL_CRYSTALS_KEY);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeature.SMOLDERING_BUSHES_KEY);


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
