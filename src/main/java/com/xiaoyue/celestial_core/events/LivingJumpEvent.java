package com.xiaoyue.celestial_core.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LivingJumpEvent extends CelestialHooks {

	private final LivingEntity entity;
	private float jumpPower;

	public LivingJumpEvent(LivingEntity entity, float jumpPower) {
		this.entity = entity;
		this.jumpPower = jumpPower;
	}

	public LivingEvent.LivingJumpEvent getJumpEvent() {
		return new LivingEvent.LivingJumpEvent(entity);
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public float getJumpPower() {
		return jumpPower;
	}

	public void setJumpPower(float jumpPower) {
		this.jumpPower = jumpPower;
	}
}