package com.xiaoyue.celestial_core.content.effects.misc;

import com.xiaoyue.celestial_core.register.CCAttributes;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class RegenRate extends MobEffect {
	public RegenRate() {
		super(MobEffectCategory.BENEFICIAL, 0xd94a95);
		String uuid = MathHelper.getUUIDFromString("celestial_core:reply_power").toString();
		this.addAttributeModifier(CCAttributes.REPLY_POWER.get(), uuid, 0.25f, AttributeModifier.Operation.MULTIPLY_BASE);
	}
}
