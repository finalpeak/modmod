package net.finalpeak.gnomesandtomes.entity.custom;

import net.finalpeak.gnomesandtomes.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PillarEntity extends Entity implements Ownable {

    public PillarEntity(World world) {
        super(ModEntities.PILLAR, world);
    }

    public PillarEntity(EntityType<? extends PillarEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean isPushable() { return false; }

    @Override
    public boolean isCollidable() { return true; }

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
