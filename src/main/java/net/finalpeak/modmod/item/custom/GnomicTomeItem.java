package net.finalpeak.modmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GnomicTomeItem extends Item {
    public GnomicTomeItem(Settings settings) {
        super(settings);
    }

    private List<String> inputs = new ArrayList<>();
    public void addInput(String input) {
        inputs.add(input);
    }
    public List<String> getInputs() {
        return inputs;
    }
    public void sendInputs(PlayerEntity user){
        String msg = inputs.get(0);
        for (int i = 1; i < inputs.size(); i++) {
            msg += " - " + inputs.get(i);
        }
        user.sendMessage(new LiteralText(msg), true);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.shift"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell1"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell2"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell3"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell4"));
        } else {
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip"));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (inputs.size() >= 3) {
                inputs.clear();
            }
            inputs.add("R");
            sendInputs(user);

            //Spells
            if (inputs.size() == 3) {
                spells(world, user);
            }
        }

        return super.use(world, user, hand);
    }

    public void spells(World world, PlayerEntity player){
        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "R", "R")))) {
            if(spell1(world, player)){
                removeMagic(1);
            }
        }
    }

    public boolean spell1(World world, PlayerEntity player) {
        BlockHitResult hitResult = (BlockHitResult) player.raycast(30.0D, 0.0F, false);
        BlockPos blockPos = hitResult.getBlockPos();

        BlockState blockState = world.getBlockState(blockPos);
        if (!blockState.isOf(Blocks.AIR)) {
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                world.spawnEntity(lightning);
                return true;
            }
        }
        return false;
    }

    public void removeMagic(int value){
        //IMPLEMENT MAGIC STORAGE/USAGE
    }
}
