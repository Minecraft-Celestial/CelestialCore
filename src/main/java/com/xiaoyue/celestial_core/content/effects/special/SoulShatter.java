package com.xiaoyue.celestial_core.content.effects.special;

import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SoulShatter extends MobEffect {
	public SoulShatter() {
		super(MobEffectCategory.HARMFUL, 0xff9900);
		String uuid = MathHelper.getUUIDFromString("celestial_core:soul_shatter").toString();
		this.addAttributeModifier(Attributes.MAX_HEALTH, uuid, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, uuid, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}
}
