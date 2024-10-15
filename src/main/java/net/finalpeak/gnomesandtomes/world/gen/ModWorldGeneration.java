package net.finalpeak.gnomesandtomes.world.gen;

import net.finalpeak.gnomesandtomes.world.ModFeatures;

public class ModWorldGeneration {
    public static void generateModWorldGen(){
        ModOreGeneration.generateOres();
        ModFeatures.placeFeaturesInBiomes();
    }
}
