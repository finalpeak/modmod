package net.finalpeak.gnomesandtomes.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.entity.custom.BoulderEntity;
import net.finalpeak.gnomesandtomes.entity.custom.GnomeEntity;
import net.finalpeak.gnomesandtomes.entity.custom.PillarEntity;
import net.finalpeak.gnomesandtomes.entity.custom.PorcupineEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BoulderEntity> BOULDER = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "boulder"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<BoulderEntity>) BoulderEntity::new) // Ensure correct factory
                    .dimensions(EntityDimensions.fixed(3f, 2f)).build());

    public static final EntityType<PillarEntity> PILLAR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "pillar"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<PillarEntity>) PillarEntity::new) // Ensure correct factory
                    .dimensions(EntityDimensions.fixed(1.5f, 4.9f)).build());

    public static final EntityType<GnomeEntity> GNOME = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "gnome"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GnomeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1f))
                    .build()
    );

    public static final EntityType<PorcupineEntity> PORCUPINE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "porcupine"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorcupineEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void registerModEntities() {
        GnomesAndTomes.LOGGER.info("Registering Entities for " + GnomesAndTomes.MOD_ID);
    }
}
