package net.chixozhmix.space.worldgen.noise;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class NoiseRegistry {
    public static final ResourceKey<NoiseGeneratorSettings> KUNABULA_NOISE_SETTINGS =
            ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(ChiSpace.MOD_ID, "kunabula"));
}
