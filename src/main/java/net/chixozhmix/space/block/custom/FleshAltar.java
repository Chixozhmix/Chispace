package net.chixozhmix.space.block.custom;

import com.github.elenterius.biomancy.init.ModItems;
import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FleshAltar extends Block {
    public FleshAltar(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);

        if(itemInHand.is(ModItems.LIVING_FLESH.get()) && itemInHand.getCount() >= 6)
        {
            if(!pLevel.isClientSide) {
                FleshMimicEntity fleshMimic = new FleshMimicEntity(ModEntities.FLESH_MIMIC_ENTITY.get(), pLevel);

                fleshMimic.setPos(pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5);
                pLevel.addFreshEntity(fleshMimic);

                if(!pPlayer.isCreative())
                    itemInHand.shrink(6);
            }

            return InteractionResult.SUCCESS;
        }

        else {
            pPlayer.sendSystemMessage(Component.translatable(
                    "message.chispace.flesh_altar_error"
            ));
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
