package com.xiaoyue.celestial_core.content.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LHurtAfterMagicEvent extends LivingEvent {

    private final DamageSource source;
    private final float afterMagicAmount;
    private float amount;

    public LHurtAfterMagicEvent(LivingEntity entity, DamageSource source, float afterMagicAmount, float amount) {
        super(entity);
        this.source = source;
        this.afterMagicAmount = afterMagicAmount;
        this.amount = amount;
    }

    public DamageSource getSource() {
        return source;
    }

    public float getAfterMagicAmount() {
        return afterMagicAmount;
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
