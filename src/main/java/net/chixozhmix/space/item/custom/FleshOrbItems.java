package net.chixozhmix.space.item.custom;

import net.chixozhmix.space.worldgen.dimension.KunabulaDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

public class FleshOrbItems extends Item {
    public FleshOrbItems(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            teleportPlayer(player);
            // Добавляем кулдаун для предотвращения спама
            player.getCooldowns().addCooldown(this, 20); // 1 секунда
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private void teleportPlayer(Player player) {
        if (player.level().isClientSide || player.getServer() == null) return;

        ServerLevel targetLevel = null;

        if (player.level().dimension() == Level.OVERWORLD) {
            targetLevel = player.getServer().getLevel(KunabulaDimension.KAUPENDIM_LEVEL_KEY);
        } else if (player.level().dimension() == KunabulaDimension.KAUPENDIM_LEVEL_KEY) {
            targetLevel = player.getServer().getLevel(Level.OVERWORLD);
        }

        if (targetLevel != null) {
            // Находим безопасное место для спавна
            BlockPos safePos = findSafeSpawnLocation(targetLevel, player);

            // Телепортируем с кастомным телепортером
            player.changeDimension(targetLevel, (ITeleporter) new SafeTeleporter(safePos));
        } else {
            player.sendSystemMessage(Component.translatable(
                    "message.chispace.flesh_orb_error"
            ));
        }
    }

    private BlockPos findSafeSpawnLocation(ServerLevel level, Player player) {
        // Используем текущую позицию игрока как основу для поиска
        BlockPos startPos = new BlockPos((int)player.getX(), (int)player.getY(), (int)player.getZ());

        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight();

        // Проверяем сначала текущую позицию и близлежащие
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                // Ищем сверху вниз
                for (int y = startPos.getY() + 10; y >= minY; y--) {
                    BlockPos checkPos = new BlockPos(startPos.getX() + x, y, startPos.getZ() + z);
                    if (isSafeSpawnPosition(level, checkPos)) {
                        return checkPos.above();
                    }
                }
                // Если сверху не нашли, ищем снизу вверх
                for (int y = minY; y < maxY; y++) {
                    BlockPos checkPos = new BlockPos(startPos.getX() + x, y, startPos.getZ() + z);
                    if (isSafeSpawnPosition(level, checkPos)) {
                        return checkPos.above();
                    }
                }
            }
        }

        // Если ничего не нашли - возвращаем мировую точку спавна
        return level.getSharedSpawnPos().atY(Math.max(level.getSharedSpawnPos().getY(), minY + 10));
    }

    private boolean isSafeSpawnPosition(ServerLevel level, BlockPos pos) {
        // Проверяем, что блок под ногами твердый и не опасный
        if (!level.getBlockState(pos).isSolid() ||
                level.getBlockState(pos).is(Blocks.BEDROCK) ||
                level.getBlockState(pos).is(Blocks.LAVA)) {
            return false;
        }

        // Проверяем, что место для спавна свободно (2 блока воздуха над поверхностью)
        return level.getBlockState(pos.above()).isAir() &&
                level.getBlockState(pos.above(2)).isAir() &&
                !level.getBlockState(pos.above()).is(Blocks.BEDROCK) &&
                !level.getBlockState(pos.above(2)).is(Blocks.BEDROCK);
    }

    private static class SafeTeleporter implements ITeleporter {
        private final BlockPos targetPos;

        public SafeTeleporter(BlockPos targetPos) {
            this.targetPos = targetPos;
        }

        @Override
        public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
            Entity newEntity = repositionEntity.apply(false);
            if (newEntity instanceof ServerPlayer player) {
                // Убеждаемся, что чанки загружены
                destWorld.getChunkSource().addRegionTicket(
                        TicketType.POST_TELEPORT,
                        new ChunkPos(targetPos),
                        1,
                        player.getId()
                );

                // Проверяем окончательную позицию
                BlockPos finalPos = ensureSafePosition(destWorld, targetPos);

                // Телепортация
                player.teleportTo(
                        destWorld,
                        finalPos.getX() + 0.5,
                        finalPos.getY(),
                        finalPos.getZ() + 0.5,
                        yaw,
                        entity.getXRot()
                );

                // Дополнительная проверка от удушья
                preventSuffocation(destWorld, player);
            }
            return newEntity;
        }

        private BlockPos ensureSafePosition(ServerLevel level, BlockPos pos) {
            // Если позиция под бедроком - поднимаем на 10 блоков выше минимальной высоты
            if (pos.getY() <= level.getMinBuildHeight() + 5) {
                return pos.atY(level.getMinBuildHeight() + 10);
            }

            // Проверяем блоки над позицией
            for (int i = 0; i < 10; i++) {
                BlockPos checkPos = pos.above(i);
                if (level.getBlockState(checkPos).isAir() &&
                        level.getBlockState(checkPos.above()).isAir()) {
                    return checkPos;
                }
            }

            return pos;
        }

        private void preventSuffocation(ServerLevel level, ServerPlayer player) {
            // Поднимаем игрока, пока он не окажется в воздухе
            while (!level.noCollision(player) && player.getY() < level.getMaxBuildHeight()) {
                player.setPos(player.getX(), player.getY() + 1.0, player.getZ());
            }

            // Если достигли максимальной высоты - телепортируем на спавн
            if (player.getY() >= level.getMaxBuildHeight()) {
                BlockPos spawnPos = level.getSharedSpawnPos();
                player.teleportTo(
                        spawnPos.getX() + 0.5,
                        spawnPos.getY(),
                        spawnPos.getZ() + 0.5
                );
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
       pTooltipComponents.add(Component.translatable("tooltip.flesh_orb.tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
