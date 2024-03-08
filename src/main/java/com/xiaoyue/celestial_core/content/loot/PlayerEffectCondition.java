package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class PlayerEffectCondition implements LootItemCondition {

	@SerialClass.SerialField
	public MobEffectCategory category;

	@SerialClass.SerialField
	public String count;

	@Deprecated
	public PlayerEffectCondition() {

	}

	public PlayerEffectCondition(MobEffectCategory category, IntConfigValue count) {
		this.category = category;
		this.count = count.toData();
	}

	@Override
	public LootItemConditionType getType() {
		return CCLootModifier.PLAYER_EFFECT.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		if (!ctx.hasParam(LootContextParams.LAST_DAMAGE_PLAYER)) return false;
		var player = ctx.getParam(LootContextParams.LAST_DAMAGE_PLAYER);
		return EntityUtils.getEffectCount(player, category) >= IntConfigValue.of(count).get();
	}

}
