package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.entity.custom.BoulderEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModEntities {
    public static final EntityType<BoulderEntity> BOULDER = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "boulder"), // Use the correct identifier
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<BoulderEntity>) BoulderEntity::new) // Ensure correct factory
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());
}
