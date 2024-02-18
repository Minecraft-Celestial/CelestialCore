package com.xiaoyue.celestial_core.content.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Hidden extends MobEffect {
    public Hidden() {
        super(MobEffectCategory.BENEFICIAL, 0xff9900);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "1-2-3-4-5", 0.05, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
