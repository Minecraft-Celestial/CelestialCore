package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class FearCurse extends CelestialEffect {
    public FearCurse() {
        super(MobEffectCategory.HARMFUL, 0xff892215);
    }

    @Override
    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return false;
    }
}
