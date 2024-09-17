package net.finalpeak.modmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.finalpeak.modmod.block.ModBlocks;
import net.finalpeak.modmod.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool gnomitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GNOMITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GNOMITE_ORE);

        blockStateModelGenerator.registerLog(ModBlocks.PANDO_LOG).log(ModBlocks.PANDO_LOG).wood(ModBlocks.PANDO_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PANDO_LOG).log(ModBlocks.STRIPPED_PANDO_LOG).wood(ModBlocks.STRIPPED_PANDO_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PANDO_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PANDO_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GNOMITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.REFINED_GNOMITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.EARTHEN_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GNOMIC_TOME, Models.GENERATED);
        itemModelGenerator.register(ModItems.AZURE_SHARD, Models.GENERATED);
    }
}