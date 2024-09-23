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
        BlockStateModelGenerator.BlockTexturePool pandoPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PANDO_PLANKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_GNOMITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GNOMITE_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_PERIDOT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BUDDING_PERIDOT);

        blockStateModelGenerator.registerAmethyst(ModBlocks.SMALL_PERIDOT_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.MEDIUM_PERIDOT_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.LARGE_PERIDOT_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.PERIDOT_CLUSTER);

        blockStateModelGenerator.registerLog(ModBlocks.PANDO_LOG).log(ModBlocks.PANDO_LOG).wood(ModBlocks.PANDO_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PANDO_LOG).log(ModBlocks.STRIPPED_PANDO_LOG).wood(ModBlocks.STRIPPED_PANDO_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PANDO_LEAVES);

        blockStateModelGenerator.registerTintableCross(ModBlocks.PANDO_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        pandoPool.stairs(ModBlocks.PANDO_STAIRS);
        pandoPool.slab(ModBlocks.PANDO_SLAB);
        pandoPool.button(ModBlocks.PANDO_BUTTON);
        pandoPool.pressurePlate(ModBlocks.PANDO_PRESSURE_PLATE);
        pandoPool.fence(ModBlocks.PANDO_FENCE);
        pandoPool.fenceGate(ModBlocks.PANDO_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.PANDO_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PANDO_TRAPDOOR);

        blockStateModelGenerator.registerTintableCross(ModBlocks.MYSTIC_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GNOMITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.REFINED_GNOMITE, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.SMALL_PERIDOT_BUD, Models.GENERATED);
        itemModelGenerator.register(ModItems.PERIDOT_SHARD, Models.GENERATED);

        itemModelGenerator.register(ModItems.EARTHEN_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GNOMIC_TOME, Models.GENERATED);
        itemModelGenerator.register(ModItems.AZURE_SHARD, Models.GENERATED);
    }
}