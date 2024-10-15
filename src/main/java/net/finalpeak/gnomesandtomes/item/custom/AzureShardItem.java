package net.finalpeak.gnomesandtomes.item.custom;

import net.finalpeak.gnomesandtomes.item.custom.util.Spells;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AzureShardItem extends MagicTool {

    public AzureShardItem(Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip.shift"));
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip.spell1"));
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip.spell2"));
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip.spell3"));
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip.spell4"));
        } else {
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip1"));
            tooltip.add(Text.translatable("item.modmod.azure_shard.tooltip2"));
        }
    }

    public void spells(World world, PlayerEntity player) {
        spelling = true;
        Timer timer = new Timer();
        int delay = 0;

        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "R", "R")))) {
            if (Spells.lightning(world, player)) {
                removeMagic(1);
            }
            delay = 500;
        }

        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "L", "R")))) {
            if (Spells.spell2(world, player)) {
                removeMagic(1);
            }
        }

        if (inputs.equals(new ArrayList<>(Arrays.asList("L", "L", "L")))) {
            if (Spells.spell3(world, player)) {
                removeMagic(1);
            }
        }

        if (inputs.equals(new ArrayList<>(Arrays.asList("L", "R", "L")))) {
            if (Spells.spell4(world, player)) {
                removeMagic(1);
            }
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                inputs.clear();
                spelling = false;
            }
        }, delay);
    }
}
