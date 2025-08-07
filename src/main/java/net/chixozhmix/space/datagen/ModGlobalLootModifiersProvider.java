package net.chixozhmix.space.datagen;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.item.ModItems;
import net.chixozhmix.space.loot.AddItemModifiers;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;



public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ChiSpace.MOD_ID);
    }

    @Override
    protected void start() {
        add("flesh_orb_from_living_flesh", new AddItemModifiers(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("biomancy:entities/flesh_blob")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()
        }, ModItems.FLESH_ORB.get()));

        add("flesh_orb_from_hungry_living_flesh", new AddItemModifiers(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("biomancy:entities/hungry_flesh_blob")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()
        }, ModItems.FLESH_ORB.get()));
    }
}
