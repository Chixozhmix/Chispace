package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
