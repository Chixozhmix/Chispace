package net.chixozhmix.space.worldgen.noise;

import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.elenterius.biomancy.init.ModBlocks;
import net.chixozhmix.space.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class KunabulaNoiseSettings {
    public static NoiseGeneratorSettings createNetherLikeSettings(HolderGetter<DensityFunction> densityFunctions,
                                                                  HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        NoiseSettings noiseSettings = NoiseSettings.create(
                0,    // minY (как в Незере)
                256,  // height (меньше, чем в overworld)
                1,    // noiseSizeX
                2     // noiseSizeY
        );

        return new NoiseGeneratorSettings(
                noiseSettings,
                ModBlocks.FLESH.get().defaultBlockState(), // defaultBlock
                ACBlockRegistry.ACID.get().defaultBlockState(), // defaultFluid
                createNetherLikeNoiseRouter(densityFunctions, noiseParameters),
                ModSurfaceRules.makeRulesNether(),
                List.of(),
                31,   // seaLevel (уровень жидкости)
                false, // disableMobGeneration
                false, // aquifersEnabled
                false, // oreGenEnabled
                false  // useLegacyRandomSource
        );
    }

    private static NoiseRouter createNetherLikeNoiseRouter(HolderGetter<DensityFunction> densityFunctions,
                                                           HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        // Получаем стандартные параметры шумов
        Holder<NormalNoise.NoiseParameters> temperatureNoise = noiseParameters.getOrThrow(Noises.TEMPERATURE);
        Holder<NormalNoise.NoiseParameters> vegetationNoise = noiseParameters.getOrThrow(Noises.VEGETATION);
        Holder<NormalNoise.NoiseParameters> continentsNoise = noiseParameters.getOrThrow(Noises.CONTINENTALNESS);
        Holder<NormalNoise.NoiseParameters> erosionNoise = noiseParameters.getOrThrow(Noises.EROSION);

        // Настройки идентичные Незеру
        DensityFunction temperature = DensityFunctions.noise(temperatureNoise, 1.25D);
        DensityFunction vegetation = DensityFunctions.noise(vegetationNoise, 1.25D);
        DensityFunction continents = DensityFunctions.noise(continentsNoise, 1.25D);
        DensityFunction erosion = DensityFunctions.noise(erosionNoise, 1.25D);

        // Основная density функция (близкая к ванильному Незеру)
        DensityFunction baseNoise = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.NETHER_STATE_SELECTOR));
        DensityFunction netherWaves = DensityFunctions.add(
                DensityFunctions.constant(-0.703125),
                DensityFunctions.mul(
                        DensityFunctions.constant(4.0),
                        DensityFunctions.cache2d(
                                DensityFunctions.rangeChoice(
                                        baseNoise,
                                        -0.3, 0.5,
                                        DensityFunctions.constant(1.0),
                                        DensityFunctions.constant(0.0)
                                )
                        )
                ));

        // Добавляем вертикальную вариацию
        DensityFunction verticalShape = DensityFunctions.add(
                DensityFunctions.yClampedGradient(0, 128, 1.0, 0.0),
                DensityFunctions.mul(
                        DensityFunctions.constant(0.5),
                        DensityFunctions.noise(noiseParameters.getOrThrow(Noises.RIDGE), 1.0)
                )
        );

        DensityFunction finalDensity = DensityFunctions.add(
                DensityFunctions.mul(netherWaves, verticalShape),
                DensityFunctions.constant(-0.1) // Небольшая корректировка
        );

        return new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                temperature,
                vegetation,
                continents,
                erosion,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                netherWaves,
                finalDensity,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero()
        );
    }

    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {
        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noiseParameters = context.lookup(Registries.NOISE);

        context.register(NoiseRegistry.KUNABULA_NOISE_SETTINGS,
                createNetherLikeSettings(densityFunctions, noiseParameters));
    }
}
