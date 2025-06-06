package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ReinforcedBlade extends CelestialEffect {

    public ReinforcedBlade() {
        super(MobEffectCategory.BENEFICIAL, 0xffc6712b);
        String uuid = MathHelper.getUUIDFromString("celestial_core:reinforced_blade").toString();
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, uuid, 0.1, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(L2DamageTracker.CRIT_RATE.get(), uuid, 0.25f, AttributeModifier.Operation.ADDITION);
    }
}
