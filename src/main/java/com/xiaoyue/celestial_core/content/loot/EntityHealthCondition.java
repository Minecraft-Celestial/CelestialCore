package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class EntityHealthCondition implements LootItemCondition {

	@SerialClass.SerialField
	public String min;

	@Deprecated
	public EntityHealthCondition() {

	}

	public EntityHealthCondition(IntConfigValue min) {
		this.min = min.toData();
	}

	@Override
	public LootItemConditionType getType() {
		return CCLootModifier.ENTITY_HEALTH.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		if (!ctx.hasParam(LootContextParams.THIS_ENTITY)) return false;
		var self = ctx.getParam(LootContextParams.THIS_ENTITY);
		if (!(self instanceof LivingEntity le)) return false;
		float hp = le.getMaxHealth();
		return IntConfigValue.of(min).get() <= hp;
	}

}
