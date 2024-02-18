package com.xiaoyue.celestial_core.content.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SoulShatter extends MobEffect {
    public SoulShatter() {
        super(MobEffectCategory.HARMFUL, 0xff9900);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "1-2-3-4-5", -10, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "1-2-3-4-5", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
