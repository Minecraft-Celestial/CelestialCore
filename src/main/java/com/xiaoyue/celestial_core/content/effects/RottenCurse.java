package com.xiaoyue.celestial_core.content.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RottenCurse extends MobEffect {
    public RottenCurse() {
        super(MobEffectCategory.HARMFUL, 0xff9900);
    }

    @Override
    public void applyEffectTick(LivingEntity target, int amplifier) {
        if (target.getLastDamageSource() != null) {
            target.hurt(target.getLastDamageSource(), amplifier + target.getMaxHealth() * 0.02f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int Duration, int amplifier) {
        return Duration > 20;
    };
}