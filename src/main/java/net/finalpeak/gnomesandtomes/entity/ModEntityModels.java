package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.finalpeak.gnomesandtomes.entity.client.*;

public class ModEntityModels {
    public static void registerModels() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOULDER, BoulderModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BOULDER, BoulderRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PILLAR, PillarModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PILLAR, PillarRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GNOME, GnomeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GNOME, GnomeRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
    }
}

