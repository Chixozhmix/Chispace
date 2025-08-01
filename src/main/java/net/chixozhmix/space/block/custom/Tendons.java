package net.chixozhmix.space.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class Tendons extends Block {
    public Tendons() {
        super(BlockBehaviour.Properties.copy(Blocks.GRASS)
                .mapColor(MapColor.COLOR_RED)
                .noCollission()
                .instabreak()
                .sound(SoundType.WOOL)
                .offsetType(BlockBehaviour.OffsetType.XZ));
    }
}
