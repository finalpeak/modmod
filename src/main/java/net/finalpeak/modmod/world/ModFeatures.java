package net.finalpeak.modmod.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.finalpeak.modmod.ModMod;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class ModFeatures {

    public static void placeFeaturesInBiomes() {

        BiomeModification modification = BiomeModifications.create(
                new Identifier(ModMod.MOD_ID, "geode_additions")
        );

        modification.add(
                ModificationPhase.ADDITIONS,
                BiomeSelectors.foundInOverworld(), //.tag(ModBiomeTags.HAS_EMERALD_GEODE)
                context -> context.getGenerationSettings().addFeature(
                        GenerationStep.Feature.UNDERGROUND_DECORATION,
                        ModPlacedFeatures.TEST_GEODE_PLACED_KEY
                        //MoreGeodes.CONFIG.generateEmeraldGeodes()
                )
        );
    }
}