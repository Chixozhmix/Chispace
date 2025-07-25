package net.chixozhmix.space.loot;

import com.mojang.serialization.Codec;
import net.chixozhmix.space.ChiSpace;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIRER_SERIALIZES =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ChiSpace.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIRER_SERIALIZES.register("add_item", AddItemModifiers.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIRER_SERIALIZES.register(eventBus);
    }
}
