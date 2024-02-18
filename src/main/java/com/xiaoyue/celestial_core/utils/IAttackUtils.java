package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class IAttackUtils {

    public static float getAttackCharged(Player player) {
        return player.getCurrentItemAttackStrengthDelay();
    }

    public static boolean isFullCharged(Player player) {
        return getAttackCharged(player) > 0.9f;
    }

    public static boolean isCritical(Player attacker, LivingEntity target) {
        return isFullCharged(attacker) && attacker.fallDistance > 0.0F && !attacker.onGround() && !attacker.onClimbable()
                && !attacker.isInWater() && !attacker.hasEffect(MobEffects.BLINDNESS)
                && !attacker.isPassenger() && target != null && !attacker.isSprinting();
    }

    public static float getPlayerAttackAttr(Player player) {
        return (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public static boolean attackEntitySecondary(DamageSource source, float damage, LivingEntity target) {
        int lastInvulnerableTime = target.invulnerableTime;
        target.invulnerableTime = 0;
        boolean hurt = target.hurt(source, damage);
        target.invulnerableTime = lastInvulnerableTime;
        return hurt;
    }

    public static boolean isNotHaveInvTime(LivingEntity entity) {
        if (entity.invulnerableTime != 0) {
            entity.invulnerableTime = 0;
            return true;
        }
        return false;
    }
}
