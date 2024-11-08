package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.finalpeak.gnomesandtomes.entity.client.BoulderModel;
import net.finalpeak.gnomesandtomes.entity.client.BoulderRenderer;
import net.finalpeak.gnomesandtomes.entity.client.ModModelLayers;

public class ModEntityModels {
    public static void registerModels() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOULDER, BoulderModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BOULDER, BoulderRenderer::new);

    }
}

