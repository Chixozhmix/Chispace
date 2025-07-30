package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ChiSpace.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SOUL_GEM_ORE);

        simpleBlockWithItem(ModBlocks.DESECRATION_ALTAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/desecration_altar")));

        simpleBlockWithItem(ModBlocks.FLESH_ALTAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/flesh_altar")));

        createVineBlock(ModBlocks.FLESH_VINE_HEAD.get(), ModBlocks.FLESH_VINE.get());
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void createVineBlock(Block vineHead, Block vineBody) {
        // Модель для головы лианы
        ModelFile vineHeadModel = models().cross(
                ForgeRegistries.BLOCKS.getKey(vineHead).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(vineHead).getPath())
        ).renderType("cutout");  // renderType вызывается у ModelFile, а не у ResourceLocation

        // Модель для тела лианы
        ModelFile vineBodyModel = models().cross(
                ForgeRegistries.BLOCKS.getKey(vineBody).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(vineBody).getPath())
        ).renderType("cutout");

        // Blockstate для головы лианы
        getVariantBuilder(vineHead)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(vineHeadModel)
                        .build()
                );

        // Blockstate для тела лианы
        simpleBlock(vineBody, vineBodyModel);

        // Модели предметов
        simpleBlockItem(vineHead, vineHeadModel);
        simpleBlockItem(vineBody, vineBodyModel);
    }
}
