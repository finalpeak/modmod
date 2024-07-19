package net.finalpeak.modmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.finalpeak.modmod.ModMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup RUBY = FabricItemGroupBuilder.build(new Identifier(ModMod.MOD_ID, "ruby"),
            () -> new ItemStack(ModItems.REFINED_RUBY_CRYSTAL));

    public static final ItemGroup GNOME = FabricItemGroupBuilder.build(new Identifier(ModMod.MOD_ID, "gnome"),
            () -> new ItemStack(ModItems.GNOMITE));
}
