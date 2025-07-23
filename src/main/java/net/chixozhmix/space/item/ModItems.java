package net.chixozhmix.space.item;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChiSpace.MOD_ID);


    //Orbs
    public static final RegistryObject<Item> FLESH_ORB = ITEMS.register("flesh_orb",
            () -> new FleshOrbItems(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> MORDAN_SIGIL = ITEMS.register("mordan_sigil",
            () -> new MordanSigil(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)));

    //Gem and Ore
    public static final RegistryObject<Item> SOUL_GEM = ITEMS.register("soul_gem",
            () -> new SoulGem(new Item.Properties()
                    .stacksTo(64)));
    public static final RegistryObject<Item> BLACK_SOUL_GEM = ITEMS.register("black_soul_gem",
            () -> new BlackSoulGem(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ETHERIUM_INGOT = ITEMS.register("etherium",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.EPIC)));

    //Staff
    public static final RegistryObject<Item> FLESH_STAFF = ITEMS.register("flesh_staff",
            () -> new FleshStaff(new Item.Properties()
                    .stacksTo(1)));

    //Tools
    public static final RegistryObject<Item> ETHERIUM_PICKAXE = ITEMS.register("etherium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ETHERIUM, 1, 1f, new Item.Properties()
                    .rarity(Rarity.EPIC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
