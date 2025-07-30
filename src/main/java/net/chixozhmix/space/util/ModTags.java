package net.chixozhmix.space.util;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        //ToolTags
        public static final TagKey<Block> NEEDS_ETHERIUM_TOOL = tag("needs_etherium_tool");

        //FeatureTags
        public static final TagKey<Block> HANGING_VINES_GROWABLE = tag("hanging_vines_growable");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ChiSpace.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ChiSpace.MOD_ID, name));
        }
    }
}
