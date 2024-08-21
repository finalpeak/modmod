package net.finalpeak.modmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.finalpeak.modmod.block.ModBlocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.PANDO_LOG).log(ModBlocks.PANDO_LOG).wood(ModBlocks.PANDO_LOG);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PANDO_LOG).log(ModBlocks.STRIPPED_PANDO_LOG).wood(ModBlocks.STRIPPED_PANDO_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PANDO_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PANDO_LEAVES);
        blockStateModelGenerator.registerLog(ModBlocks.PANDO_LOG).log(ModBlocks.PANDO_LOG).wood(ModBlocks.PANDO_LOG);
        blockStateModelGenerator.registerLog(ModBlocks.PANDO_LOG).log(ModBlocks.PANDO_LOG).wood(ModBlocks.PANDO_LOG);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
