package net.finalpeak.modmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.finalpeak.modmod.ModMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item RUBY_CRYSTAL = registerItem("ruby_crystal",
            new Item(new FabricItemSettings().group(ModItemGroup.RUBY)));

    public static final Item REFINED_RUBY_CRYSTAL = registerItem("refined_ruby_crystal",
            new Item(new FabricItemSettings().group(ModItemGroup.RUBY)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(ModMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ModMod.LOGGER.info(("Registering Mod Items for " + ModMod.MOD_ID));
    }

}
