package net.chixozhmix.space.item;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier ETHERIUM = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2200, 10f, 4f, 25,
                    ModTags.Blocks.NEEDS_ETHERIUM_TOOL, () -> Ingredient.of(ModItems.ETHERIUM_INGOT.get())),
            new ResourceLocation(ChiSpace.MOD_ID, "etherium"), List.of(Tiers.NETHERITE), List.of());
}
