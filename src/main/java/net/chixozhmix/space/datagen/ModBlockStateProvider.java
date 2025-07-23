package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
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

        logBlock(((RotatedPillarBlock) ModBlocks.COLD_OAK_LOG.get()));
        itemModels().withExistingParent(
                ForgeRegistries.BLOCKS.getKey(ModBlocks.COLD_OAK_LOG.get()).getPath(),
                modLoc("block/cold_oak_log"));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_COLD_OAK_LOG.get()));
        itemModels().withExistingParent(
                ForgeRegistries.BLOCKS.getKey(ModBlocks.STRIPPED_COLD_OAK_LOG.get()).getPath(),
                modLoc("block/stripped_cold_oak_log"));

        leavesBlock(ModBlocks.COLD_OAK_LEAVES);

        saplingBlock(ModBlocks.COLD_OAK_SAPLING);

        createMultiTextureBlock(ModBlocks.COLD_GRASS_BLOCK.get(),
                "cold_grass_block_side",
                "cold_grass_block_buttom",
                "cold_grass_block_top");

        simpleBlock(ModBlocks.COLD_DIRT.get());
        itemModels().withExistingParent(
                ForgeRegistries.BLOCKS.getKey(ModBlocks.COLD_DIRT.get()).getPath(),
                modLoc("block/cold_dirt"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        new ResourceLocation("minecraft:block/leaves"), "all",
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(), models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void createMultiTextureBlock(Block block, String sideTex, String bottomTex, String topTex) {
        // Создаем модель куба с разными текстурами
        ModelFile model = models().cubeBottomTop(
                ForgeRegistries.BLOCKS.getKey(block).getPath(),
                modLoc("block/" + sideTex),
                modLoc("block/" + bottomTex),
                modLoc("block/" + topTex)
        );

        // Создаем простой blockstate (все стороны одинаковые)
        simpleBlock(block, model);

        // Создаем модель предмета
        simpleBlockItem(block, model);
    }
}
