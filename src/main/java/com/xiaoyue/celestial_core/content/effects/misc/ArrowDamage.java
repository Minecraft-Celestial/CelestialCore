package com.xiaoyue.celestial_core.content.effects.misc;

import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ArrowDamage extends MobEffect {
	public ArrowDamage() {
		super(MobEffectCategory.BENEFICIAL, 0xff9900);
		String uuid = MathHelper.getUUIDFromString("celestial_core:arrow_damage_effect").toString();
		this.addAttributeModifier(L2DamageTracker.BOW_STRENGTH.get(), uuid, 0.2f, AttributeModifier.Operation.ADDITION);
	}
}
