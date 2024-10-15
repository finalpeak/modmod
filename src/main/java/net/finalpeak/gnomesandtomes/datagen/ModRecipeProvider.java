package net.finalpeak.gnomesandtomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.finalpeak.gnomesandtomes.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> GNOMITE_SMELTABLES = List.of(ModBlocks.GNOMITE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, GNOMITE_SMELTABLES, RecipeCategory.MISC, ModItems.GNOMITE,
                0.7f, 200, "gnomite");
        offerBlasting(exporter, GNOMITE_SMELTABLES, RecipeCategory.MISC, ModItems.GNOMITE,
                0.7f, 100, "gnomite");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.REFINED_GNOMITE, RecipeCategory.DECORATIONS,
                ModBlocks.BLOCK_OF_GNOMITE);

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PERIDOT_SHARD, ModBlocks.BLOCK_OF_PERIDOT);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.EARTHEN_STAFF, 1)
                .pattern("  G")
                .pattern(" S ")
                .pattern("S  ")
                .input('G', ModBlocks.BLOCK_OF_GNOMITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.REFINED_GNOMITE), conditionsFromItem(ModItems.REFINED_GNOMITE))
                .criterion(hasItem(ModBlocks.BLOCK_OF_GNOMITE), conditionsFromItem(ModBlocks.BLOCK_OF_GNOMITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EARTHEN_STAFF)));

        createStairsRecipe(ModBlocks.PANDO_STAIRS, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_STAIRS)));

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PANDO_SLAB, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PANDO_BUTTON, 1)
                .pattern("#")
                .input('#', ModBlocks.PANDO_PLANKS)
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_BUTTON)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PANDO_PRESSURE_PLATE, 1)
                .pattern("##")
                .input('#', ModBlocks.PANDO_PLANKS)
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_PRESSURE_PLATE)));

        createFenceRecipe(ModBlocks.PANDO_FENCE, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_FENCE)));

        createFenceGateRecipe(ModBlocks.PANDO_FENCE_GATE, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_FENCE_GATE)));

        createDoorRecipe(ModBlocks.PANDO_DOOR, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_DOOR)));

        createTrapdoorRecipe(ModBlocks.PANDO_TRAPDOOR, Ingredient.ofItems(ModBlocks.PANDO_PLANKS))
                .criterion(hasItem(ModBlocks.PANDO_PLANKS), conditionsFromItem(ModBlocks.PANDO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PANDO_TRAPDOOR)));

    }
}
