package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;
import static net.minecraft.tags.DamageTypeTags.*;

public class CCDamageTypes extends DamageTypeAndTagsGen {

	public static final ResourceKey<DamageType> IGNORE_ARMOR = createDamage("ignore_armor");
	public static final ResourceKey<DamageType> IGNORE_INV_TIME = createDamage("ignore_inv_time");
	public static final ResourceKey<DamageType> IGNORE_EFFECT = createDamage("ignore_effect");
	public static final ResourceKey<DamageType> IGNORE_ENCHANTMENT = createDamage("ignore_enchantment");
	public static final ResourceKey<DamageType> IGNORE_SHIELD = createDamage("ignore_shield");
	public static final ResourceKey<DamageType> VIOLENT_DAMAGE = createDamage("violent_damage");

	public CCDamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> pvd, ExistingFileHelper helper) {
		super(output, pvd, helper, MODID);
		//TODO clean up damage types
		new DamageTypeHolder(IGNORE_ARMOR, new DamageType("ignore_armor", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_ARMOR, BYPASSES_COOLDOWN);
		new DamageTypeHolder(IGNORE_INV_TIME, new DamageType("ignore_inv_time", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_COOLDOWN);
		new DamageTypeHolder(IGNORE_EFFECT, new DamageType("ignore_effect", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_EFFECTS, BYPASSES_COOLDOWN);
		new DamageTypeHolder(IGNORE_ENCHANTMENT, new DamageType("ignore_enchantment", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_ENCHANTMENTS, BYPASSES_COOLDOWN);
		new DamageTypeHolder(IGNORE_SHIELD, new DamageType("ignore_shield", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_SHIELD, BYPASSES_COOLDOWN);
		new DamageTypeHolder(VIOLENT_DAMAGE, new DamageType("violent_damage", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_ARMOR, BYPASSES_COOLDOWN, BYPASSES_INVULNERABILITY,
						BYPASSES_ENCHANTMENTS, BYPASSES_RESISTANCE, BYPASSES_EFFECTS,
						AVOIDS_GUARDIAN_THORNS);
	}

	public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> key) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
	}

	private static ResourceKey<DamageType> createDamage(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, CelestialCore.loc(id));
	}

}
