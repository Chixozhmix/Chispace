package net.chixozhmix.space.entity;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.chixozhmix.space.entity.custom.NecoEntity;
import net.chixozhmix.space.entity.custom.SummonedFleshBlob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChiSpace.MOD_ID);

    public static final RegistryObject<EntityType<FleshMimicEntity>> FLESH_MIMIC_ENTITY =
            ENTITIES.register("flesh_mimic", () -> EntityType.Builder.of(FleshMimicEntity::new, MobCategory.MONSTER)
                    .sized(1.6f, 2.97f)
                    .build("flesh_mimic"));

    public static final RegistryObject<EntityType<SummonedFleshBlob>> SUMMONED_FLESH_BLOB =
            ENTITIES.register("summoned_flesh_blob", () -> EntityType.Builder.of(SummonedFleshBlob::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build("summoned_flesh_blob"));

    public static final RegistryObject<EntityType<NecoEntity>> NECO_ENTITY =
            ENTITIES.register("neco_entity", () -> EntityType.Builder.of(NecoEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build("neco_entity"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
