package net.finalpeak.modmod.world.gen;

import net.finalpeak.modmod.world.ModFeatures;

public class ModWorldGeneration {
    public static void generateModWorldGen(){
        ModOreGeneration.generateOres();
        ModFeatures.placeFeaturesInBiomes();
    }
}
