package net.finalpeak.modmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.finalpeak.modmod.ModMod;
import net.finalpeak.modmod.block.custom.MysticMushroomBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block GNOMITE_BLOCK = registerBlock("gnomite_block", 
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(6f).requiresTool()));

    public static final Block GNOMITE_ORE = registerBlock("gnomite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE).strength(3f).requiresTool()));

    public static final Block MYSTIC_MUSHROOM_BLOCK = registerBlock("mystic_mushroom_block",
            new MysticMushroomBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM).nonOpaque()));


    public static final Block PANDO_LOG = registerBlock("pando_log",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block PANDO_WOOD = registerBlock("pando_wood",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block STRIPPED_PANDO_LOG = registerBlock("stripped_pando_log",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block STRIPPED_PANDO_WOOD = registerBlock("stripped_pando_wood",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block PANDO_PLANKS = registerBlock("pando_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block PANDO_LEAVES = registerBlock("pando_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(4f).nonOpaque()));



    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return  Registry.register(Registries.BLOCK, new Identifier(ModMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(ModMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        ModMod.LOGGER.info("Registering ModBlocks for " + ModMod.MOD_ID);
    }

}
