package net.finalpeak.gnomesandtomes.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<ImbuingTableBlockEntity> IMBUING_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GnomesAndTomes.MOD_ID, "imbuing_table_be"),
                    FabricBlockEntityTypeBuilder.create(ImbuingTableBlockEntity::new,
                            ModBlocks.IMBUING_TABLE).build());

    public static void registerBlockEntities() {
        GnomesAndTomes.LOGGER.info("Registering Block Entities for " + GnomesAndTomes.MOD_ID);
    }
}
