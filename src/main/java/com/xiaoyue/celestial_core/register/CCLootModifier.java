package com.xiaoyue.celestial_core.register;

import com.mojang.serialization.Codec;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.loot.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class CCLootModifier {

	public static final RegistryEntry<Codec<AddItemModifier>> ADD_ITEM;
	public static final RegistryEntry<Codec<AddLootTableModifier>> ADD_LOOT_TABLE;

	public static final RegistryEntry<LootItemConditionType> PLAYER_FLAG, ENTITY_HEALTH, PLAYER_EFFECT, CHARGED_CREEPER;

	static {
		ADD_ITEM = reg("add_item", () -> AddItemModifier.CODEC);
		ADD_LOOT_TABLE = reg("add_loot_table", () -> AddLootTableModifier.CODEC);

		PLAYER_FLAG = reg("player_flag", PlayerFlagCondition.class);
		ENTITY_HEALTH = reg("entity_health", EntityHealthCondition.class);
		PLAYER_EFFECT = reg("player_effect", PlayerEffectCondition.class);
		CHARGED_CREEPER = reg("charged_creeper_kill", ChargedCreeperKillCondition.class);

	}

	private static <T extends IGlobalLootModifier> RegistryEntry<Codec<T>> reg(String str, NonNullSupplier<Codec<T>> codec) {
		return CelestialCore.REGISTRATE.simple(str, ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, codec);
	}

	private static <T extends LootItemCondition> RegistryEntry<LootItemConditionType> reg(String str, Class<T> codec) {
		return CelestialCore.REGISTRATE.simple(str, Registries.LOOT_CONDITION_TYPE, () ->
				new LootItemConditionType(new CCConditionSerializer<>(codec)));
	}

	public static void register() {

	}

}
