package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BladeModifier extends MobEffect {
    public BladeModifier() {
        super(MobEffectCategory.BENEFICIAL, 0xff9900);
        this.addAttributeModifier(CAttributes.CRIT_RATE.get(), "1-2-3-4-5", 0.25f, AttributeModifier.Operation.ADDITION);
    }

}
