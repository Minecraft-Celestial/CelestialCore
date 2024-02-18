package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.content.event.LHurtAfterArmorEvent;
import com.xiaoyue.celestial_core.content.event.LHurtAfterMagicEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract void hurtArmor(DamageSource pDamageSource, float pDamageAmount);

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected float getDamageAfterArmorAbsorb(DamageSource pDamageSource, float pDamageAmount) {
        LivingEntity entity = (LivingEntity) (Object) this;
        float damageAfterArmor = CombatRules.getDamageAfterAbsorb(pDamageAmount, (float) entity.getArmorValue(), (float) entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        LHurtAfterArmorEvent event = new LHurtAfterArmorEvent(entity, pDamageSource, damageAfterArmor, pDamageAmount);
        MinecraftForge.EVENT_BUS.post(event);
        if (!pDamageSource.is(DamageTypeTags.BYPASSES_ARMOR)) {
            this.hurtArmor(pDamageSource, pDamageAmount);
            if (event.isCanceled()) {
                return event.getAmount();
            } else {
                pDamageAmount = damageAfterArmor;
            }
        }
        return pDamageAmount;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected float getDamageAfterMagicAbsorb(DamageSource pDamageSource, float pDamageAmount) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (pDamageSource.is(DamageTypeTags.BYPASSES_EFFECTS)) {
            return pDamageAmount;
        } else {
            int k;
            if (entity.hasEffect(MobEffects.DAMAGE_RESISTANCE) && !pDamageSource.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
                k = (entity.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 5;
                int j = 25 - k;
                float f = pDamageAmount * (float)j;
                float f1 = pDamageAmount;
                pDamageAmount = Math.max(f / 25.0F, 0.0F);
                float f2 = f1 - pDamageAmount;
                if (f2 > 0.0F && f2 < 3.4028235E37F) {
                    if (entity instanceof ServerPlayer) {
                        ((ServerPlayer)entity).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
                    } else if (pDamageSource.getEntity() instanceof ServerPlayer) {
                        ((ServerPlayer)pDamageSource.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
                    }
                }
            }
            if (pDamageAmount <= 0.0F) {
                return 0.0F;
            } else if (pDamageSource.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
                return pDamageAmount;
            } else {
                k = EnchantmentHelper.getDamageProtection(entity.getArmorSlots(), pDamageSource);
                if (k > 0) {
                    float damageAfterMagic = CombatRules.getDamageAfterMagicAbsorb(pDamageAmount, (float) k);
                    LHurtAfterMagicEvent event = new LHurtAfterMagicEvent(entity, pDamageSource, damageAfterMagic, pDamageAmount);
                    MinecraftForge.EVENT_BUS.post(event);
                    if (event.isCanceled()) {
                        return event.getAmount();
                    } else {
                        pDamageAmount = damageAfterMagic;
                    }
                }
                return pDamageAmount;
            }
        }
    }
}
