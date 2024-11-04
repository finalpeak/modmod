package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.finalpeak.gnomesandtomes.entity.client.BoulderModel;
import net.finalpeak.gnomesandtomes.entity.client.ModModelLayers;

public class ModEntityModels {
    public static void registerModels() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOULDER, BoulderModel::getTexturedModelData);
    }
}

