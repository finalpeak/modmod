package net.finalpeak.gnomesandtomes.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GNOME_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(GnomesAndTomes.MOD_ID, "gnome_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gnomesandtomes.gnome_group"))
                    .icon(() -> new ItemStack(ModItems.REFINED_GNOMITE)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.IMBUING_TABLE);

                        entries.add(ModItems.GNOMITE);
                        entries.add(ModItems.REFINED_GNOMITE);
                        entries.add(ModBlocks.BLOCK_OF_GNOMITE);
                        entries.add(ModBlocks.GNOMITE_ORE);

                        entries.add(ModItems.EARTHEN_STAFF);
                        entries.add(ModItems.GNOMIC_TOME);
                        entries.add(ModItems.AZURE_SHARD);

                        entries.add(ModItems.MYSTIC_MUSHROOM_ITEM);

                        entries.add(ModItems.GNOME_SUMMON_ITEM);

                        entries.add(ModBlocks.PANDO_LOG);
                        entries.add(ModBlocks.PANDO_WOOD);
                        entries.add(ModBlocks.STRIPPED_PANDO_LOG);
                        entries.add(ModBlocks.STRIPPED_PANDO_WOOD);
                        entries.add(ModBlocks.PANDO_PLANKS);
                        entries.add(ModBlocks.PANDO_LEAVES);

                        entries.add(ModBlocks.PANDO_STAIRS);
                        entries.add(ModBlocks.PANDO_SLAB);
                        entries.add(ModBlocks.PANDO_BUTTON);
                        entries.add(ModBlocks.PANDO_PRESSURE_PLATE);
                        entries.add(ModBlocks.PANDO_FENCE);
                        entries.add(ModBlocks.PANDO_FENCE_GATE);
                        entries.add(ModBlocks.PANDO_DOOR);
                        entries.add(ModBlocks.PANDO_TRAPDOOR);

                        entries.add(ModBlocks.BLOCK_OF_PERIDOT);
                        entries.add(ModBlocks.BUDDING_PERIDOT);
                        entries.add(ModBlocks.SMALL_PERIDOT_BUD);
                        entries.add(ModBlocks.MEDIUM_PERIDOT_BUD);
                        entries.add(ModBlocks.LARGE_PERIDOT_BUD);
                        entries.add(ModBlocks.PERIDOT_CLUSTER);

                    }).build());


    public static void registerItemGroups() {
        GnomesAndTomes.LOGGER.info("Registering Item Groups for " + GnomesAndTomes.MOD_ID);
    }
}