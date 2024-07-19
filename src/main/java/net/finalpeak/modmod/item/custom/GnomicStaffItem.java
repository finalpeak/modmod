package net.finalpeak.modmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GnomicStaffItem extends Item {
    public GnomicStaffItem(Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.modmod.gnomic_staff.tooltip"));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        outputMsg(user, entity);

        applyMomentum(user, entity, 10, 1);

        stack.damage(1, user, (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnEntity(stack, user, entity, hand);
    }

    private void outputMsg(PlayerEntity player, LivingEntity entity){
        String entityName = entity.getName().getString();
        player.sendMessage(new LiteralText("AHHHHHHHHH a " + entityName + "!"), true);
    }

    public void applyMomentum(PlayerEntity player, LivingEntity entity, double speed, double verticalSpeed) {
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d horizontalVelocity = direction.multiply(speed);
        Vec3d finalVelocity = new Vec3d(horizontalVelocity.x, verticalSpeed, horizontalVelocity.z);
        entity.setVelocity(finalVelocity);
        entity.velocityModified = true;
    }
}
