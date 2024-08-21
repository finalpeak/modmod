package net.finalpeak.modmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.finalpeak.modmod.ModMod;
import net.finalpeak.modmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GNOME_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ModMod.MOD_ID, "gnome_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gnome_group"))
                    .icon(() -> new ItemStack(ModItems.REFINED_GNOMITE)).entries((displayContext, entries) -> {

                        entries.add(ModItems.GNOMITE);
                        entries.add(ModItems.REFINED_GNOMITE);
                        entries.add(ModBlocks.GNOMITE_BLOCK);
                        entries.add(ModBlocks.GNOMITE_ORE);

                        entries.add(ModItems.GNOMIC_STAFF);
                        entries.add(ModItems.GNOMIC_TOME);
                        entries.add(ModItems.AZURE_SHARD);

                        entries.add(ModItems.MYSTIC_MUSHROOM_ITEM);

                        entries.add(ModBlocks.PANDO_LOG);
                        entries.add(ModBlocks.PANDO_WOOD);
                        entries.add(ModBlocks.STRIPPED_PANDO_LOG);
                        entries.add(ModBlocks.STRIPPED_PANDO_WOOD);
                        entries.add(ModBlocks.PANDO_PLANKS);
                        entries.add(ModBlocks.PANDO_LEAVES);

                    }).build());


    public static void registerItemGroups() {
        ModMod.LOGGER.info("Registering Item Groups for " + ModMod.MOD_ID);
    }
}