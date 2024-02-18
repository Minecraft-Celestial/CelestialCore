package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.COAttributes;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BladeModifier extends MobEffect {
    public BladeModifier() {
        super(MobEffectCategory.BENEFICIAL, 0xff9900);
        this.addAttributeModifier(COAttributes.CRIT_RATE.get(),
                MathHelper.getUUIDFromString("celestial_core:blade").toString(),
                0.25f, AttributeModifier.Operation.ADDITION);
    }

}
