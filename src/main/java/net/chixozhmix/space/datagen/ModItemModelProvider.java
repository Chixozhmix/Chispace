package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ChiSpace.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.FLESH_ORB);
        simpleItem(ModItems.MORDAN_SIGIL);

        simpleItem(ModItems.SOUL_GEM);
        simpleItem(ModItems.BLACK_SOUL_GEM);

        simpleItem(ModItems.ETHERIUM_INGOT);

        handheldItem(ModItems.ETHERIUM_PICKAXE);

        saplingItem(ModBlocks.COLD_OAK_SAPLING);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ChiSpace.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem (RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ChiSpace.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ChiSpace.MOD_ID, "block/" + item.getId().getPath()));
    }
}
