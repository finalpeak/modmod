package net.finalpeak.gnomesandtomes.damage;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> GNOMIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(GnomesAndTomes.MOD_ID, "gnomic"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
