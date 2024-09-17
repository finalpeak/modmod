package net.finalpeak.modmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.finalpeak.modmod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GNOMITE_BLOCK)
                .add(ModBlocks.GNOMITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GNOMITE_ORE)
                .add(ModBlocks.GNOMITE_BLOCK);

//        getOrCreateTagBuilder(BlockTags.FENCES)
//                .add(ModBlocks.RUBY_FENCE);
//        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
//                .add(ModBlocks.RUBY_FENCE_GATE);
//        getOrCreateTagBuilder(BlockTags.WALLS)
//                .add(ModBlocks.RUBY_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PANDO_LOG)
                .add(ModBlocks.PANDO_WOOD)
                .add(ModBlocks.STRIPPED_PANDO_LOG)
                .add(ModBlocks.STRIPPED_PANDO_WOOD);
    }
}