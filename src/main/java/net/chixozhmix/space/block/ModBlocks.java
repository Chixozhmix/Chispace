package net.chixozhmix.space.block;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.custom.DesecrationAltarBlock;
import net.chixozhmix.space.block.custom.FleshAltar;
import net.chixozhmix.space.block.custom.ModFlammableRotatedPillarBlock;
import net.chixozhmix.space.item.ModItems;
import net.chixozhmix.space.worldgen.tree.ColdOakGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

    public static final RegistryObject<Block> COLD_GRASS_BLOCK = registerBlock("cold_grass_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> COLD_DIRT = registerBlock("cold_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> DESECRATION_ALTAR = registerBlock("desecration_altar",
            () -> new DesecrationAltarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noCollission()));

    public static final RegistryObject<Block> FLESH_ALTAR = registerBlock("flesh_altar",
            () -> new FleshAltar(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().strength(-1F, 360000F)));

    //BlockEntities
    public static final RegistryObject<Block> COLD_OAK_LOG = registerBlock("cold_oak_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3F)));
    public static final RegistryObject<Block> STRIPPED_COLD_OAK_LOG = registerBlock("stripped_cold_oak_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3F)));
    public static final RegistryObject<Block> COLD_OAK_LEAVES = registerBlock("cold_oak_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> COLD_OAK_SAPLING = registerBlock("cold_oak_sapling",
            () -> new SaplingBlock(new ColdOakGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


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
