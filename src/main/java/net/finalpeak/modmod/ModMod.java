package net.finalpeak.modmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.modmod.block.ModBlocks;
import net.finalpeak.modmod.client.TomeOverlay;
import net.finalpeak.modmod.events.EventHandlers;
import net.finalpeak.modmod.item.ModItems;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMod implements ModInitializer {

	// Mod ID
	public static final String MOD_ID = "modmod";

	// Logger for the mod
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register mod items
		ModItems.registerModItems();

		// Register mod blocks
		ModBlocks.registerModBlocks();

		// Register event handlers
		EventHandlers.registerEvents();

		// Register the HUD render callback
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				ItemStack heldItem = client.player.getStackInHand(client.player.getActiveHand());
				if (heldItem.getItem() instanceof GnomicTomeItem) {
					MatrixStack matrices = new MatrixStack();
					TomeOverlay.render(matrices);
				}
			}
		});

		// Log mod initialization
		LOGGER.info("ModMod initialized successfully!");
	}
}
