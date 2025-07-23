package net.chixozhmix.space.datagen;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLESH_ORB.get())
                .pattern("NBN")
                .pattern("GFG")
                .pattern("NBN")
                .define('N', Items.NETHERITE_SCRAP)
                .define('B', ItemRegistry.BLOOD_VIAL.get())
                .define('F', com.github.elenterius.biomancy.init.ModItems.LIVING_FLESH.get())
                .define('G', ModItems.BLACK_SOUL_GEM.get())
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ETHERIUM_PICKAXE.get())
                .pattern("EGE")
                .pattern(" S ")
                .pattern(" S ")
                .define('E', ModItems.ETHERIUM_INGOT.get())
                .define('G', ModItems.SOUL_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_etherium_ingot", has(ModItems.ETHERIUM_INGOT.get()))
                .save(consumer);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ETHERIUM_INGOT.get(), 1)
                .requires(ModItems.BLACK_SOUL_GEM.get())
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.BLACK_SOUL_GEM.get()), has(ModItems.BLACK_SOUL_GEM.get()))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme,
                pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience,
                pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe>
            pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime,
                                     String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                    ChiSpace.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
