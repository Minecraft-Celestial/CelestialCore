package com.xiaoyue.celestial_core.content.effects.misc;

import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CritDamage extends MobEffect {
	public CritDamage() {
		super(MobEffectCategory.BENEFICIAL, 0xd9ac4a);
		String uuid = MathHelper.getUUIDFromString("celestial_core:crit_damage_effect").toString();
		this.addAttributeModifier(L2DamageTracker.CRIT_DMG.get(), uuid, 0.1f, AttributeModifier.Operation.ADDITION);
	}
}
