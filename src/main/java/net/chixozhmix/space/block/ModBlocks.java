package net.chixozhmix.space.block;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.custom.*;
import net.chixozhmix.space.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ChiSpace.MOD_ID);

    //Blocks
    public static final RegistryObject<Block> SOUL_GEM_ORE = registerBlock("soul_gem_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> DESECRATION_ALTAR = registerBlock("desecration_altar",
            () -> new DesecrationAltarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noCollission()));

    public static final RegistryObject<Block> FLESH_ALTAR = registerBlock("flesh_altar",
            () -> new FleshAltar(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().strength(-1F, 360000F)));

    public static final RegistryObject<Block> FLESH_VINE_HEAD = registerBlock("flesh_vine_head",
            () -> new FleshVinesHeadBlock(BlockBehaviour.Properties.copy(Blocks.KELP)));

    public static final RegistryObject<Block> FLESH_VINE = registerBlock("flesh_vine",
            () -> new FleshVines(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT)));

    public static final RegistryObject<Block> GUTS_HEAD = registerBlock("guts_head",
            () -> new GutsHeadBlock(BlockBehaviour.Properties.copy(Blocks.KELP)));

    public static final RegistryObject<Block> GUTS = registerBlock("guts",
            () -> new Guts(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
