package net.finalpeak.gnomesandtomes.entity.custom;

import net.finalpeak.gnomesandtomes.entity.ModEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BoulderEntity extends Entity implements Ownable {
    public final AnimationState earthquakeAnimationState = new AnimationState();
    private boolean playedAnimation = false;

    public BoulderEntity(World world) {
        super(ModEntities.BOULDER, world);
    }

    public BoulderEntity(EntityType<? extends BoulderEntity> type, World world) {
        super(type, world);
    }

    private void setupAnimationStates(){
        System.out.println("Animation starting at age: " + this.age);
        this.earthquakeAnimationState.start(this.age);
    }

    @Override
    public void tick() {
        super.tick();
        setupAnimationStates();  // Run on both client and server
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Nullable
    @Override
    public Entity getOwner() {
        return null;
    }
}
