package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ArrowDamage extends MobEffect {
    public ArrowDamage() {
        super(MobEffectCategory.BENEFICIAL, 0xff9900);
        this.addAttributeModifier(CAttributes.ARROW_DAMAGE.get(), "1-2-3-4-5", 0.2f, AttributeModifier.Operation.ADDITION);
    }
}
