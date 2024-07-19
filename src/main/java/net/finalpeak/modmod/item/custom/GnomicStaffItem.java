package net.finalpeak.modmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class GnomicStaffItem extends Item {
    public GnomicStaffItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        outputMsg(user, entity);

        stack.damage(1, user, (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnEntity(stack, user, entity, hand);
    }

    private void outputMsg(PlayerEntity player, LivingEntity entity){
        String entityName = entity.getName().getString();
        player.sendMessage(new LiteralText("AHHHHHHHHH a " + entityName + "!"), true);
    }
}
