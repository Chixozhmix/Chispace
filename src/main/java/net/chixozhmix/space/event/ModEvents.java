package net.chixozhmix.space.event;

import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.elenterius.biomancy.init.ModEntityTypes;
import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.chixozhmix.space.entity.custom.NecoEntity;
import net.chixozhmix.space.entity.custom.SummonedFleshBlob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChiSpace.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FLESH_MIMIC_ENTITY.get(), FleshMimicEntity.createAttributes());
        event.put(ModEntities.SUMMONED_FLESH_BLOB.get(), SummonedFleshBlob.createAttributes());
        event.put(ModEntities.NECO_ENTITY.get(), NecoEntity.createAttributes());
    }

    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
        event.register(
                ModEntityTypes.FLESH_COW.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> {
                    // Проверяем блок под позицией спавна
                    BlockState blockBelow = world.getBlockState(pos.below());
                    return !(blockBelow.is(ACBlockRegistry.ACID.get()) || blockBelow.is(Blocks.LAVA_CAULDRON));
                },
                SpawnPlacementRegisterEvent.Operation.AND
        );

        event.register(
                ModEntityTypes.FLESH_CHICKEN.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> {
                    // Проверяем блок под позицией спавна
                    BlockState blockBelow = world.getBlockState(pos.below());
                    return !(blockBelow.is(ACBlockRegistry.ACID.get()) || blockBelow.is(Blocks.LAVA_CAULDRON));
                },
                SpawnPlacementRegisterEvent.Operation.AND
        );

        event.register(
                ModEntityTypes.FLESH_PIG.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> {
                    // Проверяем блок под позицией спавна
                    BlockState blockBelow = world.getBlockState(pos.below());
                    return !(blockBelow.is(ACBlockRegistry.ACID.get()) || blockBelow.is(Blocks.LAVA_CAULDRON));
                },
                SpawnPlacementRegisterEvent.Operation.AND
        );
    }
}
