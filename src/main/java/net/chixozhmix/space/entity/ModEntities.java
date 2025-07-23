package net.chixozhmix.space.entity;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.chixozhmix.space.entity.custom.MordanServantEntity;
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


    public static final RegistryObject<EntityType<MordanServantEntity>> MORDAN_SERVANT_ENTITY =
            ENTITIES.register("mordan_servant", () -> EntityType.Builder.of(MordanServantEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.97f)
                    .build("mordan_servant"));

    public static final RegistryObject<EntityType<FleshMimicEntity>> FLESH_MIMIC_ENTITY =
            ENTITIES.register("flesh_mimic", () -> EntityType.Builder.of(FleshMimicEntity::new, MobCategory.MONSTER)
                    .sized(1.6f, 2.97f)
                    .build("flesh_mimic"));

    public static final RegistryObject<EntityType<SummonedFleshBlob>> SUMMONED_FLESH_BLOB =
            ENTITIES.register("summoned_flesh_blob", () -> EntityType.Builder.of(SummonedFleshBlob::new, MobCategory.MONSTER)
                    .sized(1.6f, 2.97f)
                    .build("summoned_flesh_blob"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
