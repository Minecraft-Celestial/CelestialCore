package com.xiaoyue.celestial_core.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class DamageItemEvent extends Event {

	private final LivingEntity entity;
	private final ItemStack stack;
	private int amount;

	public DamageItemEvent(LivingEntity entity, ItemStack stack, int amount) {
		this.entity = entity;
		this.stack = stack;
		this.amount = amount;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public ItemStack getStack() {
		return stack;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
