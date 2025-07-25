package net.chixozhmix.space.screen;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ChiSpace.MOD_ID);

    public static final RegistryObject<MenuType<DesecrationAltarMenu>> DESECRATION_ALTAR_MENU =
            registryMenuType("desecration_altar_menu", DesecrationAltarMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registryMenuType(String name, IContainerFactory<T>
                                                                                                 factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
