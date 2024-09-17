package net.finalpeak.modmod.world;

import net.finalpeak.modmod.ModMod;
import net.finalpeak.modmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> GNOMITE_ORE_KEY = registerKey("gnomite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PANDO_KEY = registerKey("pando");

    public static final RegistryKey<ConfiguredFeature<?, ?>> MYSTIC_MUSHROOM_KEY = registerKey("mystic_mushroom");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldGnomiteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.GNOMITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.GNOMITE_ORE.getDefaultState()));

        register(context, GNOMITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldGnomiteOres, 12));

        register(context, PANDO_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PANDO_LOG),
                new StraightTrunkPlacer(5, 4, 3),

                BlockStateProvider.of(ModBlocks.PANDO_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(5), ConstantIntProvider.create(2), 2),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, MYSTIC_MUSHROOM_KEY, Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfig(
                BlockStateProvider.of(Blocks.PURPLE_CONCRETE),
                BlockStateProvider.of(Blocks.LAPIS_BLOCK),
                3));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ModMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
