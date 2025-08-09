package net.chixozhmix.space.item.custom;

import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.entity.custom.SummonedFleshBlob;
import net.chixozhmix.space.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class FleshStaff extends Item {

    public FleshStaff(Properties pProperties) {
        super(pProperties.defaultDurability(16).setNoRepair());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if(stack.getDamageValue() >= stack.getMaxDamage()) {
            return InteractionResultHolder.fail(stack);
        }

        if(!pLevel.isClientSide) {
            SummonedFleshBlob fleshBlob = new SummonedFleshBlob(ModEntities.SUMMONED_FLESH_BLOB.get(), pLevel);
            fleshBlob.moveTo(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), pPlayer.getYRot(), 0);
            pLevel.addFreshEntity(fleshBlob);
            pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, pPlayer.position());

            if(!pPlayer.isCreative()) {
                stack.hurt(1, pLevel.getRandom(), null);
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.getItem() == ModItems.SOUL_GEM.get() || pRepairCandidate.getItem() == ModItems.BLACK_SOUL_GEM.get();
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if(pStack.getDamageValue() > 0) {
            int repairAmount = Math.min(8, pStack.getDamageValue());

            pStack.setDamageValue(pStack.getDamageValue() - repairAmount);
        }

        super.onCraftedBy(pStack, pLevel, pPlayer);
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }
}
