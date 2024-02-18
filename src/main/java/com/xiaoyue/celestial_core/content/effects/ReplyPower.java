package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ReplyPower extends MobEffect {
    public ReplyPower() {
        super(MobEffectCategory.BENEFICIAL, 0xd94a95);
        this.addAttributeModifier(CAttributes.REPLY_POWER.get(), "1-2-3-4-5", 0.25f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
