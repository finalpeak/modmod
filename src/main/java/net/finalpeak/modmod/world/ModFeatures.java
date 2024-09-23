package net.finalpeak.modmod.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.finalpeak.modmod.ModMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.BiConsumer;

public class ModFeatures {

    public static void placeFeaturesInBiomes() {

        BiomeModification modification = BiomeModifications.create(
                new Identifier(ModMod.MOD_ID, "geode_additions")
        );

        modification
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_EMERALD_GEODE),
                        modifier(
                                ModPlacedFeatures.EMERALD_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEmeraldGeodes()
                        )
                );
    }
}