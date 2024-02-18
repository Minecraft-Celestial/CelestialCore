package com.xiaoyue.celestial_core.content.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

public class CDamageTypes {
    public static final ResourceKey<DamageType> IGNORE_ARMOR = createDamage("ignore_armor");
    public static final ResourceKey<DamageType> IGNORE_INV_TIME = createDamage("ignore_inv_time");
    public static final ResourceKey<DamageType> IGNORE_EFFECT = createDamage("ignore_effect");
    public static final ResourceKey<DamageType> IGNORE_ENCHANTMENT = createDamage("ignore_enchantment");
    public static final ResourceKey<DamageType> IGNORE_SHIELD = createDamage("ignore_shield");
    public static final ResourceKey<DamageType> VIOLENT_DAMAGE = createDamage("violent_damage");

    /*
    public CDamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> pvd, ExistingFileHelper helper) {
        super(output, pvd, helper, MODID);
        new DamageTypeHolder(IGNORE_ARMOR, new DamageType("ignore_armor", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_ARMOR);
        new DamageTypeHolder(IGNORE_INV_TIME, new DamageType("ignore_inv_time", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_COOLDOWN);
        new DamageTypeHolder(IGNORE_EFFECT, new DamageType("ignore_effect", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_EFFECTS);
        new DamageTypeHolder(IGNORE_ENCHANTMENT, new DamageType("ignore_enchantment", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_ENCHANTMENTS);
        new DamageTypeHolder(IGNORE_SHIELD, new DamageType("ignore_shield", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_SHIELD);
        new DamageTypeHolder(VIOLENT_DAMAGE, new DamageType("violent_damage", DamageScaling.NEVER, 0.1f))
                .add(DamageTypeTags.BYPASSES_ARMOR).add(DamageTypeTags.BYPASSES_COOLDOWN).add(DamageTypeTags.BYPASSES_ENCHANTMENTS);
    }
    */

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }

    private static ResourceKey<DamageType> createDamage(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, id));
    }

}
