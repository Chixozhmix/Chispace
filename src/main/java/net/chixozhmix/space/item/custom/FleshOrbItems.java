package net.chixozhmix.space.item.custom;

import net.chixozhmix.space.util.TeleportItemUtil;
import net.chixozhmix.space.worldgen.dimension.KunabulaDimension;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FleshOrbItems extends TeleportItemUtil {
    public FleshOrbItems(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ServerLevel getTargetLevel(ServerLevel currentLevel) {
        if(currentLevel.dimension() == Level.OVERWORLD)
            return currentLevel.getServer().getLevel(KunabulaDimension.KAUPENDIM_LEVEL_KEY);

         else if(currentLevel.dimension() == KunabulaDimension.KAUPENDIM_LEVEL_KEY)
             return currentLevel.getServer().getLevel(Level.OVERWORLD);

         return null;
    }

    @Override
    protected Component getErrorMessage() {
        return Component.translatable("message.chispace.flesh_orb_error");
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
       pTooltipComponents.add(Component.translatable("tooltip.flesh_orb.tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
