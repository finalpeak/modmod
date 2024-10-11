package net.finalpeak.modmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.finalpeak.modmod.client.overlay.ShardOverlay;
import net.finalpeak.modmod.client.overlay.StaffOverlay;
import net.finalpeak.modmod.client.overlay.TomeOverlay;
import net.finalpeak.modmod.events.EventHandlers;
import net.finalpeak.modmod.item.custom.AzureShardItem;
import net.finalpeak.modmod.item.custom.EarthenStaffItem;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.finalpeak.modmod.screen.ImbuingScreen;
import net.finalpeak.modmod.screen.ModScreenHandlers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.finalpeak.modmod.block.ModBlocks;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ModModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Set the render layer for your block to cutout or translucent
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTIC_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PANDO_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PANDO_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_PERIDOT_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_PERIDOT_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_PERIDOT_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PERIDOT_CLUSTER, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.IMBUING_SCREEN_HANDLER, ImbuingScreen::new);

        EventHandlers.registerEvents();

        // Register overlays
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null) {
                ItemStack heldItem = client.player.getStackInHand(client.player.getActiveHand());
                if (heldItem.getItem() instanceof GnomicTomeItem) {
                    new TomeOverlay().render(matrices);
                }
                if (heldItem.getItem() instanceof EarthenStaffItem) {
                    new StaffOverlay().render(matrices);
                }
                if (heldItem.getItem() instanceof AzureShardItem) {
                    new ShardOverlay().render(matrices);
                }
            }
        });
    }
}