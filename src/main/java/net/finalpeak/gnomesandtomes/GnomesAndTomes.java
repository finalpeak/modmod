package net.finalpeak.gnomesandtomes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.finalpeak.gnomesandtomes.block.entity.ModBlockEntities;
import net.finalpeak.gnomesandtomes.item.ModItemGroups;
import net.finalpeak.gnomesandtomes.item.ModItems;
import net.finalpeak.gnomesandtomes.screen.ModScreenHandlers;
import net.finalpeak.gnomesandtomes.util.ModLootTableModifiers;
import net.finalpeak.gnomesandtomes.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GnomesAndTomes implements ModInitializer {

	// Mod ID
	public static final String MOD_ID = "gnomesandtomes";

	// Logger for the mod
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModLootTableModifiers.modifyLootTables();

		ModWorldGeneration.generateModWorldGen();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		// Register strippable blocks
		StrippableBlockRegistry.register(ModBlocks.PANDO_LOG, ModBlocks.STRIPPED_PANDO_LOG);
		StrippableBlockRegistry.register(ModBlocks.PANDO_WOOD, ModBlocks.STRIPPED_PANDO_WOOD);

		// Register flammable blocks
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PANDO_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PANDO_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PANDO_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PANDO_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PANDO_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PANDO_LEAVES, 30, 60);

		FuelRegistry.INSTANCE.add(ModBlocks.PANDO_LOG, 300);
		FuelRegistry.INSTANCE.add(ModBlocks.PANDO_WOOD, 300);
		FuelRegistry.INSTANCE.add(ModBlocks.STRIPPED_PANDO_LOG, 300);
		FuelRegistry.INSTANCE.add(ModBlocks.STRIPPED_PANDO_WOOD, 300);
		FuelRegistry.INSTANCE.add(ModBlocks.PANDO_PLANKS, 300);
		FuelRegistry.INSTANCE.add(ModBlocks.PANDO_SAPLING, 100);

	}
}
