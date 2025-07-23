package net.chixozhmix.space.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModNetherRegion extends Region {
    public ModNetherRegion(ResourceLocation name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiome(mapper,
                Climate.Parameter.point(1.0f), // temperature
                Climate.Parameter.point(0.2f), // humidity
                Climate.Parameter.point(0.0f), // continentalness
                Climate.Parameter.point(0.2f), // erosion
                Climate.Parameter.point(0.0f), // depth
                Climate.Parameter.point(0.0f), // weirdness
                0, // offset
                ModBiomes.FLESH_GORGE);

        this.addBiome(mapper,
                Climate.Parameter.point(1.0f), // temperature
                Climate.Parameter.point(0.3f), // humidity
                Climate.Parameter.point(0.0f), // continentalness
                Climate.Parameter.point(0.3f), // erosion
                Climate.Parameter.point(0.0f), // depth
                Climate.Parameter.point(0.0f), // weirdness
                0, // offset
                ModBiomes.GASTRIC_GROTTO);
    }
}
