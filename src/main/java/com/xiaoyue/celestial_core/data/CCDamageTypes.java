package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;
import static net.minecraft.tags.DamageTypeTags.*;

public class CCDamageTypes extends DamageTypeAndTagsGen {

	public static final ResourceKey<DamageType> MAGIC = createDamage("magic");
	public static final ResourceKey<DamageType> ABYSSAL_MAGIC = createDamage("abyssal_magic");

	public CCDamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> pvd, ExistingFileHelper helper) {
		super(output, pvd, helper, MODID);
		new DamageTypeHolder(MAGIC, new DamageType("magic", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_ARMOR, BYPASSES_COOLDOWN, AVOIDS_GUARDIAN_THORNS, L2DamageTypes.MAGIC);
		new DamageTypeHolder(ABYSSAL_MAGIC, new DamageType("magic", DamageScaling.NEVER, 0.1f))
				.add(BYPASSES_ARMOR, BYPASSES_COOLDOWN, AVOIDS_GUARDIAN_THORNS, L2DamageTypes.MAGIC,
						BYPASSES_EFFECTS, BYPASSES_ENCHANTMENTS, BYPASSES_RESISTANCE);
	}

	public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> key) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
	}

	private static ResourceKey<DamageType> createDamage(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, CelestialCore.loc(id));
	}

}
