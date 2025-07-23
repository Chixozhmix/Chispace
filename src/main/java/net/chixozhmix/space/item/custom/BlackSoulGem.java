package net.chixozhmix.space.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BlackSoulGem extends Item {
    public BlackSoulGem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
