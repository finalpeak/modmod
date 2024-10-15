package net.finalpeak.gnomesandtomes.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.finalpeak.gnomesandtomes.item.custom.AzureShardItem;
import net.finalpeak.gnomesandtomes.item.custom.EarthenStaffItem;
import net.finalpeak.gnomesandtomes.item.custom.GnomicTomeItem;
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

    public static final Item PERIDOT_SHARD =  registerItem("peridot_shard",
            new Item(new FabricItemSettings()));
            
    public static final Item EARTHEN_STAFF = registerItem("earthen_staff",
            new EarthenStaffItem(new FabricItemSettings().maxCount(1)));
    public static final Item GNOMIC_TOME = registerItem("gnomic_tome",
            new GnomicTomeItem(new FabricItemSettings().maxCount(1)));
    public static final Item AZURE_SHARD = registerItem("azure_shard",
            new AzureShardItem(new FabricItemSettings().maxCount(1)));

    public static final Item MYSTIC_MUSHROOM_ITEM = registerItem("mystic_mushroom_item",
            new BlockItem(ModBlocks.MYSTIC_MUSHROOM, new FabricItemSettings().food(ModFoodComponents.MYSTIC_MUSHROOM)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(GnomesAndTomes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GnomesAndTomes.LOGGER.info(("Registering Mod Items for " + GnomesAndTomes.MOD_ID));
    }

}
