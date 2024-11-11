package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.finalpeak.gnomesandtomes.entity.client.BoulderModel;
import net.finalpeak.gnomesandtomes.entity.client.BoulderRenderer;
import net.finalpeak.gnomesandtomes.entity.client.ModModelLayers;
import net.finalpeak.gnomesandtomes.entity.client.PillarRenderer;

public class ModEntityModels {
    public static void registerModels() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOULDER, BoulderModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BOULDER, BoulderRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PILLAR, BoulderModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PILLAR, PillarRenderer::new);

    }
}

