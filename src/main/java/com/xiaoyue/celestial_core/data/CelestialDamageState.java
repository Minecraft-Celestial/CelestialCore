package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2damagetracker.contents.damage.DamageState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.Locale;
import java.util.function.Consumer;

public enum CelestialDamageState implements DamageState {
	BYPASS_COOLDOWN(DamageTypeTags.BYPASSES_COOLDOWN),
	BYPASS_EFFECTS(DamageTypeTags.BYPASSES_EFFECTS),
	BYPASSES_INV(DamageTypeTags.BYPASSES_INVULNERABILITY),
	BYPASSES_ENCHANTS(DamageTypeTags.BYPASSES_ENCHANTMENTS),
	BYPASSES_SHIELD(DamageTypeTags.BYPASSES_SHIELD),
	EXPLOSION(DamageTypeTags.IS_EXPLOSION),
	NO_ANGER(DamageTypeTags.NO_ANGER),
	;

	private final TagKey<DamageType>[] keys;

	@SafeVarargs
	CelestialDamageState(TagKey<DamageType>... keys) {
		this.keys = keys;
	}

	@Override
	public void gatherTags(Consumer<TagKey<DamageType>> consumer) {
		for (TagKey<DamageType> key : keys) {
			consumer.accept(key);
		}
	}

	@Override
	public void removeTags(Consumer<TagKey<DamageType>> consumer) {

	}

	@Override
	public ResourceLocation getId() {
		return CelestialCore.loc(name().toLowerCase(Locale.ROOT));
	}

	@Override
	public boolean overrides(DamageState state) {
		return false;
	}
}
