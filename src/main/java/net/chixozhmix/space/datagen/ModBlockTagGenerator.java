package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ChiSpace.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SOUL_GEM_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.COLD_GRASS_BLOCK.get())
                .add(ModBlocks.COLD_DIRT.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.COLD_OAK_LOG.get())
                .add(ModBlocks.STRIPPED_COLD_OAK_LOG.get())
                .add(ModBlocks.DESECRATION_ALTAR.get());


        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.COLD_OAK_LOG.get())
                .add(ModBlocks.STRIPPED_COLD_OAK_LOG.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL);


        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DESECRATION_ALTAR.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SOUL_GEM_ORE.get());


        this.tag(ModTags.Blocks.NEEDS_ETHERIUM_TOOL);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.COLD_OAK_LOG.get())
                .add(ModBlocks.STRIPPED_COLD_OAK_LOG.get());

    }
}
