package com.xiaoyue.celestial_core.content.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LHurtAfterArmorEvent extends LivingEvent {

    private final DamageSource source;
    private final float afterArmorAmount;
    private float amount;

    public LHurtAfterArmorEvent(LivingEntity entity, DamageSource source, float afterArmorAmount, float amount) {
        super(entity);
        this.source = source;
        this.afterArmorAmount = afterArmorAmount;
        this.amount = amount;
    }

    public DamageSource getSource() {
        return source;
    }

    public float getAfterArmorAmount() {
        return afterArmorAmount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setOriginalDamage() {
        setAmount(getAmount());
    }
}
