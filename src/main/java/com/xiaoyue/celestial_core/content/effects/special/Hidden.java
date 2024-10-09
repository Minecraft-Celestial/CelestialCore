package com.xiaoyue.celestial_core.content.effects.special;

import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Hidden extends MobEffect {
	public Hidden() {
		super(MobEffectCategory.BENEFICIAL, 0xff9900);
		String uuid = MathHelper.getUUIDFromString("celestial_core:hidden_effect").toString();
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, uuid, 0.05, AttributeModifier.Operation.MULTIPLY_BASE);
	}
}
