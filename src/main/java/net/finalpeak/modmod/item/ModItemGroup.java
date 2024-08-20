package net.kaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.finalpeak.modmod.ModMod;
import net.finalpeak.modmod.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ModMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.REFINED_GNOMITE)).entries((displayContext, entries) -> {



                    }).build());


    public static void registerItemGroups() {
        ModMod.LOGGER.info("Registering Item Groups for " + ModMod.MOD_ID);
    }
}