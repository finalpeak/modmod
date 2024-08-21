package net.finalpeak.modmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.finalpeak.modmod.block.ModBlocks;

@Environment(EnvType.CLIENT)
public class ModModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Set the render layer for your block to cutout or translucent
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MYSTIC_MUSHROOM_BLOCK, RenderLayer.getCutout());
    }
}