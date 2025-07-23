package net.chixozhmix.space.worldgen.biome;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(ChiSpace.MOD_ID, "overworld"), 0));
        Regions.register(new ModNetherRegion(new ResourceLocation(ChiSpace.MOD_ID, "nether"), 0));
    }
}
