package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer BOULDER =
            new EntityModelLayer(new Identifier(GnomesAndTomes.MOD_ID, "boulder"), "main");
}
