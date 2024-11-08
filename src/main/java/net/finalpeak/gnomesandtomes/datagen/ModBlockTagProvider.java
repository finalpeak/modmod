package net.finalpeak.gnomesandtomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<net.minecraft.block.Block> REPLACEABLE_BY_SPELL =
            TagKey.of(RegistryKey.ofRegistry(BlockTags.REPLACEABLE.id()), new Identifier(GnomesAndTomes.MOD_ID, "replaceable_by_spell"));

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(REPLACEABLE_BY_SPELL)
                .add(Blocks.AIR)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS);


        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BLOCK_OF_GNOMITE)
                .add(ModBlocks.GNOMITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GNOMITE_ORE)
                .add(ModBlocks.BLOCK_OF_GNOMITE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.PANDO_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.PANDO_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PANDO_LOG)
                .add(ModBlocks.PANDO_WOOD)
                .add(ModBlocks.STRIPPED_PANDO_LOG)
                .add(ModBlocks.STRIPPED_PANDO_WOOD);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.PANDO_SAPLING);
    }
}