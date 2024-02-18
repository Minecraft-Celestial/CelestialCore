package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CritRate extends MobEffect {
    public CritRate() {
        super(MobEffectCategory.BENEFICIAL, 0xd9d44a);
        this.addAttributeModifier(CAttributes.CRIT_RATE.get(), "1-2-3-4-5", 0.1f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
