package net.chixozhmix.space.datagen.loot;

import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.SOUL_GEM_ORE.get(),
                block -> createSoulBlockDrop(ModBlocks.SOUL_GEM_ORE.get(), ModItems.SOUL_GEM.get()));

        this.dropSelf(ModBlocks.DESECRATION_ALTAR.get());
        this.dropSelf(ModBlocks.FLESH_ALTAR.get());

        this.dropSelf(ModBlocks.FLESH_VINE.get());
        this.dropSelf(ModBlocks.FLESH_VINE_HEAD.get());
        this.dropSelf(ModBlocks.GUTS.get());
        this.dropSelf(ModBlocks.GUTS_HEAD.get());
    }

    protected LootTable.Builder createSoulBlockDrop(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
