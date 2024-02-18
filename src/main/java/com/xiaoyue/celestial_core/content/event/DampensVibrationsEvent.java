package com.xiaoyue.celestial_core.content.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class DampensVibrationsEvent extends LivingEvent {

    private final Level level;

    public DampensVibrationsEvent(LivingEntity entity, Level level) {
        super(entity);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

}
