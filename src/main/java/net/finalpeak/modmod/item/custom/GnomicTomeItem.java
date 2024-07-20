package net.finalpeak.modmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GnomicTomeItem extends Item {
    public GnomicTomeItem(Settings settings){
        super(settings);
    }

    private List<String> inputs = new ArrayList<>();
    private boolean hasPerformedAction = false; // Flag to track action performance

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.modmod.gnomic_staff.tooltip"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (!hasPerformedAction) {
                user.sendMessage(new LiteralText("right click"), true);
                hasPerformedAction = true; // Set flag to true to indicate the action has been performed

                // Add to inputs and clear if necessary
                if (inputs.size() >= 3) {
                    inputs.clear();
                }
                inputs.add("r");
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (!world.isClient && user instanceof PlayerEntity) {
            hasPerformedAction = false; // Reset flag when the item use stops
        }
    }

    private void sendMessage(String message, PlayerEntity user){
        user.sendMessage(new LiteralText(message), true);
    }
}
