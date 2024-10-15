package net.finalpeak.gnomesandtomes.world;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import java.util.List;

import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> GNOMITE_ORE_PLACED_KEY = registerKey("gnomite_ore_placed");
    public static final RegistryKey<PlacedFeature> TEST_GEODE_PLACED_KEY = registerKey("test_geode_placed"); // Use a unique key here

    public static void bootstrap(Registerable<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Register Gnomite Ore Placed Feature
        register(context, GNOMITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GNOMITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));



        // Register Geode Placed Feature
        RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TEST_GEODE_KEY);
        // Create the PlacedFeature with the RegistryEntry
        PlacedFeature testGeodePlacedFeature = new PlacedFeature(
                configuredFeature,
                List.of(
                        RarityFilterPlacementModifier.of(25),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(20)) // Geode Y range
                )
        );
        // Register the Test Geode Placed Feature
        context.register(
                ModPlacedFeatures.TEST_GEODE_PLACED_KEY,
                testGeodePlacedFeature
        );

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GnomesAndTomes.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
