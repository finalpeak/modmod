package net.finalpeak.modmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.finalpeak.modmod.ModMod;
import net.finalpeak.modmod.block.ModBlocks;
import net.finalpeak.modmod.item.custom.AzureShardItem;
import net.finalpeak.modmod.item.custom.EarthenStaffItem;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item GNOMITE = registerItem("gnomite", 
            new Item(new FabricItemSettings()));

    public static final Item REFINED_GNOMITE =  registerItem("refined_gnomite", 
            new Item(new FabricItemSettings()));
            
    public static final Item GNOMIC_STAFF = registerItem("earthen_staff",
            new EarthenStaffItem(new FabricItemSettings().maxDamage(16)));

    public static final Item GNOMIC_TOME = registerItem("gnomic_tome",
            new GnomicTomeItem(new FabricItemSettings().maxDamage(16)));

    public static final Item AZURE_SHARD = registerItem("azure_shard",
            new AzureShardItem(new FabricItemSettings().maxDamage(16)));

    public static final Item MYSTIC_MUSHROOM_ITEM = registerItem("mystic_mushroom_item",
            new BlockItem(ModBlocks.MYSTIC_MUSHROOM_BLOCK, new FabricItemSettings().food(ModFoodComponents.MYSTIC_MUSHROOM)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(ModMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ModMod.LOGGER.info(("Registering Mod Items for " + ModMod.MOD_ID));
    }

}
