package com.xiaoyue.celestial_core.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class VibrationEvent extends CelestialHooks {

	private final LivingEntity entity;
	private final Level level;

	public VibrationEvent(LivingEntity entity, Level level) {
		this.entity = entity;
		this.level = level;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public Level getLevel() {
		return level;
	}

}
