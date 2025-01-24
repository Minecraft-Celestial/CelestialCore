package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Hidden extends CelestialEffect {
    public Hidden() {
        super(MobEffectCategory.BENEFICIAL, 0xff373737);
        String uuid = MathHelper.getUUIDFromString("celestial_core:hidden_effect").toString();
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, uuid, 0.05, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return false;
    }
}
