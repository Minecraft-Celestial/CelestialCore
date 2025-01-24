package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RottenCurse extends CelestialEffect {
    public RottenCurse() {
        super(MobEffectCategory.HARMFUL, 0xff47841d);
    }

    @Override
    public void applyEffectTick(LivingEntity target, int amplifier) {
        if (target.getLastDamageSource() != null) {
            target.hurt(target.getLastDamageSource(), amplifier + target.getMaxHealth() * 0.02f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int tick, int amplifier) {
        return tick % 20 == 0;
    }
}