package net.finalpeak.gnomesandtomes.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer BOULDER =
            new EntityModelLayer(new Identifier(GnomesAndTomes.MOD_ID, "boulder"), "main");

    public static void registerLayers(EntityModelLayerRegistry registry) {
        registry.registerModelLayer(BOULDER, BoulderModel::getTexturedModelData);
    }
}
