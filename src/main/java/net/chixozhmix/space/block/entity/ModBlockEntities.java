package net.chixozhmix.space.block.entity;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ChiSpace.MOD_ID);

    public static final RegistryObject<BlockEntityType<DesecrationAltarBlockEntity>> DESECRATION_ALTAR =
            BLOCK_ENTITIES.register("desecration_altar", () ->
                    BlockEntityType.Builder.of(DesecrationAltarBlockEntity::new,
                            ModBlocks.DESECRATION_ALTAR.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
