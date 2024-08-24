package net.finalpeak.modmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.finalpeak.modmod.datagen.*;

public class ModModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((output, completableFuture) -> new ModItemTagProvider(output, completableFuture, null));
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        //pack.addProvider(ModRecipeProvider::new);
        //pack.addProvider(ModPoiTagProvider::new);
    }
}