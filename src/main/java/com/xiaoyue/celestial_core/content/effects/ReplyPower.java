package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.CCAttributes;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ReplyPower extends MobEffect {
    public ReplyPower() {
        super(MobEffectCategory.BENEFICIAL, 0xd94a95);
        this.addAttributeModifier(CCAttributes.REPLY_POWER.get(),
                MathHelper.getUUIDFromString("celestial_core:reply_power").toString(),
                0.25f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
