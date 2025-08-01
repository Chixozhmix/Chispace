package net.chixozhmix.space.item;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChiSpace.MOD_ID);

    //Orbs
    public static final RegistryObject<CreativeModeTab> ORBS_TAB = CREATIVE_MOD_TABS.register("orbs_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLESH_ORB.get()))
                    .title(Component.translatable("creativetab.chispace.orbs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.FLESH_ORB.get());
                    })
                    .build());

    //Blocks
    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MOD_TABS.register("blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SOUL_GEM_ORE.get()))
                    .title(Component.translatable("creativetab.chispace.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SOUL_GEM_ORE.get());

                        output.accept(ModBlocks.DESECRATION_ALTAR.get());
                        output.accept(ModBlocks.FLESH_ALTAR.get());

                        output.accept(ModBlocks.FLESH_VINE_HEAD.get());
                        output.accept(ModBlocks.FLESH_VINE.get());

                        output.accept(ModBlocks.GUTS.get());
                        output.accept(ModBlocks.GUTS_HEAD.get());
                        output.accept(ModBlocks.TENDONS.get());
                    })
                    .build());

    //Items
    public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_MOD_TABS.register("items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLACK_SOUL_GEM.get()))
                    .title(Component.translatable("creativetab.chispace.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SOUL_GEM.get());
                        output.accept(ModItems.BLACK_SOUL_GEM.get());
                        output.accept(ModItems.ETHERIUM_PICKAXE.get());
                        output.accept(ModItems.FLESH_STAFF.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);

    }
}
