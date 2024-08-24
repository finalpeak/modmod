package net.finalpeak.modmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.finalpeak.modmod.block.ModBlocks;
import net.finalpeak.modmod.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.GNOMITE_BLOCK);
        addDrop(ModBlocks.GNOMITE_ORE, oreDrops(ModBlocks.GNOMITE_ORE, ModItems.GNOMITE));

        addDrop(ModBlocks.MYSTIC_MUSHROOM_BLOCK);

        addDrop(ModBlocks.PANDO_LOG);
        addDrop(ModBlocks.PANDO_WOOD);
        addDrop(ModBlocks.STRIPPED_PANDO_LOG);
        addDrop(ModBlocks.STRIPPED_PANDO_WOOD);
        addDrop(ModBlocks.PANDO_PLANKS);
        addDrop(ModBlocks.PANDO_LEAVES, leavesDrops(ModBlocks.PANDO_LEAVES, ModBlocks.MYSTIC_MUSHROOM_BLOCK, 0.5f));

    }
}
