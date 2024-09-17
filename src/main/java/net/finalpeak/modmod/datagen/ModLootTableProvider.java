package net.finalpeak.modmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.finalpeak.modmod.block.ModBlocks;
import net.finalpeak.modmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.GNOMITE_BLOCK);
        addDrop(ModBlocks.GNOMITE_ORE, copperLikeOreDrops(ModBlocks.GNOMITE_ORE, ModItems.GNOMITE));

        addDrop(ModBlocks.MYSTIC_MUSHROOM);

        addDrop(ModBlocks.PANDO_LOG);
        addDrop(ModBlocks.PANDO_WOOD);
        addDrop(ModBlocks.STRIPPED_PANDO_LOG);
        addDrop(ModBlocks.STRIPPED_PANDO_WOOD);
        addDrop(ModBlocks.PANDO_PLANKS);
        addDrop(ModBlocks.PANDO_LEAVES, leavesDrops(ModBlocks.PANDO_LEAVES, ModBlocks.MYSTIC_MUSHROOM, 0.5f)); // TODO

        addDrop(ModBlocks.PANDO_SAPLING);

        addDrop(ModBlocks.PANDO_STAIRS);
        addDrop(ModBlocks.PANDO_BUTTON);
        addDrop(ModBlocks.PANDO_PRESSURE_PLATE);
        addDrop(ModBlocks.PANDO_FENCE);
        addDrop(ModBlocks.PANDO_FENCE_GATE);
        addDrop(ModBlocks.PANDO_TRAPDOOR);
        addDrop(ModBlocks.PANDO_DOOR, doorDrops(ModBlocks.PANDO_DOOR));
        addDrop(ModBlocks.PANDO_SLAB, slabDrops(ModBlocks.PANDO_SLAB));
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(2.0f, 5.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}