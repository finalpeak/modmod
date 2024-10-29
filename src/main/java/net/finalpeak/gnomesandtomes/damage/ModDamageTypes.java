package net.finalpeak.gnomesandtomes.damage;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> EARTH = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "earth"));
    public static final RegistryKey<DamageType> SKY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "sky"));
    public static final RegistryKey<DamageType> PLANT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier(GnomesAndTomes.MOD_ID, "plant"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
