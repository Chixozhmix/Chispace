package net.chixozhmix.space.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.worldgen.biome.ModBiomes;
import net.chixozhmix.space.worldgen.noise.NoiseRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class KunabulaDimension {

    public static final ResourceKey<LevelStem> KAUPENDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(ChiSpace.MOD_ID, "kunambula"));
    public static final ResourceKey<Level> KAUPENDIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(ChiSpace.MOD_ID, "kunambula"));
    public static final ResourceKey<DimensionType> KAUPEN_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(ChiSpace.MOD_ID, "kunambula_plants_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(KAUPEN_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // fixedTime - нет фиксированного времени как в Незере
                false, // hasSkylight - нет естественного освещения
                true, // hasCeiling - есть потолок как в Незере
                true, // ultraWarm - ультра-теплый климат
                false, // natural - не естественное измерение
                8.0, // coordinateScale - увеличенный масштаб координат как в Незере
                true, // bedWorks - кровати взрываются
                true, // respawnAnchorWorks - можно использовать якорь возрождения
                0, // minY
                256, // height
                128, // logicalHeight - ограниченная высота как в Незере
                BlockTags.INFINIBURN_NETHER, // infiniburn - бесконечное горение как в Незере
                BuiltinDimensionTypes.NETHER_EFFECTS, // effectsLocation - эффекты Незера
                0.0f, // ambientLight - низкое окружающее освещение
                new DimensionType.MonsterSettings(true, true, ConstantInt.of(7), 0))); // monsterSettings - спавн мобов в темноте
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);


//         Создаем мульти-биомный источник как в Незере
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(1.0F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.FLESH_GORGE)),
                                Pair.of(
                                        Climate.parameters(1.0F, 0.3F, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.GASTRIC_GROTTO))

                        ))),
                noiseGenSettings.getOrThrow(NoiseRegistry.KUNABULA_NOISE_SETTINGS));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(KunabulaDimension.KAUPEN_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(KAUPENDIM_KEY, stem);
    }
}
