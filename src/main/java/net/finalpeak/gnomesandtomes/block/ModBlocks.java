package net.finalpeak.gnomesandtomes.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.block.custom.BuddingPeridotBlock;
import net.finalpeak.gnomesandtomes.block.custom.ImbuingTableBlock;
import net.finalpeak.gnomesandtomes.block.custom.PeridotClusterBlock;
import net.finalpeak.gnomesandtomes.world.ModConfiguredFeatures;
import net.finalpeak.gnomesandtomes.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block IMBUING_TABLE = registerBlock("imbuing_table",
            new ImbuingTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block BLOCK_OF_GNOMITE = registerBlock("gnomite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(6f).requiresTool()));
    public static final Block GNOMITE_ORE = registerBlock("gnomite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE).strength(3f).requiresTool()));

    public static final Block BLOCK_OF_PERIDOT = registerBlock("peridot_block",
            new AmethystBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block BUDDING_PERIDOT = registerBlock("budding_peridot",
            new BuddingPeridotBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST)));
    public static final Block SMALL_PERIDOT_BUD = registerBlock("small_peridot_bud",
            new PeridotClusterBlock(3.0f, 5.0f, FabricBlockSettings.copyOf(Blocks.SMALL_AMETHYST_BUD)));
    public static final Block MEDIUM_PERIDOT_BUD = registerBlock("medium_peridot_bud",
            new PeridotClusterBlock(4.0f, 4.0f, FabricBlockSettings.copyOf(Blocks.MEDIUM_AMETHYST_BUD)));
    public static final Block LARGE_PERIDOT_BUD = registerBlock("large_peridot_bud",
            new PeridotClusterBlock(5.0f, 3.0f, FabricBlockSettings.copyOf(Blocks.LARGE_AMETHYST_BUD)));
    public static final Block PERIDOT_CLUSTER = registerBlock("peridot_cluster",
            new PeridotClusterBlock(6.0f, 2.0f, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    // Logs and Wood need PillarBlock due to their axis property
    public static final Block PANDO_LOG = registerBlock("pando_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));
    public static final Block PANDO_WOOD = registerBlock("pando_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));
    public static final Block STRIPPED_PANDO_LOG = registerBlock("stripped_pando_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));
    public static final Block STRIPPED_PANDO_WOOD = registerBlock("stripped_pando_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));
    public static final Block PANDO_PLANKS = registerBlock("pando_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_LEAVES = registerBlock("pando_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.SPRUCE_LEAVES).strength(4f).nonOpaque()));

    public static final Block PANDO_SAPLING = registerBlock("pando_sapling",
            new SaplingBlock(ModSaplingGenerators.PANDO, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final Block PANDO_STAIRS = registerBlock("pando_stairs",
            new StairsBlock(ModBlocks.PANDO_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_SLAB = registerBlock("pando_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_BUTTON = registerBlock("pando_button",
            new ButtonBlock(BlockSetType.OAK, 15, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_PRESSURE_PLATE = registerBlock("pando_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_FENCE = registerBlock("pando_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_FENCE_GATE = registerBlock("pando_fence_gate",
            new FenceGateBlock(WoodType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_DOOR = registerBlock("pando_door",
            new DoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block PANDO_TRAPDOOR = registerBlock("pando_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));

    public static final Block MYSTIC_MUSHROOM = registerBlock("mystic_mushroom_block",
            new MushroomPlantBlock(ModConfiguredFeatures.MYSTIC_MUSHROOM_KEY, FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));


    private static Block registerBlock(String name, Block block) {
        // Register block and block item together
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(GnomesAndTomes.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(GnomesAndTomes.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        GnomesAndTomes.LOGGER.info("Registering ModBlocks for " + GnomesAndTomes.MOD_ID);
    }
}
