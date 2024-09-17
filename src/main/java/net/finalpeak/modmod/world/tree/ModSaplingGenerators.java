package net.finalpeak.modmod.world.tree;

import net.finalpeak.modmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator PANDO =
            new SaplingGenerator("pando", 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(ModConfiguredFeatures.PANDO_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
}