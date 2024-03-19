package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.loot.*;
import com.xiaoyue.celestial_core.events.CCGeneralEventHandler;
import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class CCGLMProvider extends GlobalLootModifierProvider {

	public CCGLMProvider(PackOutput output) {
		super(output, CelestialCore.MODID);
	}

	@Override
	protected void start() {
		add("chests/abandoned_mineshaft", new AddLootTableModifier(CCLootTableGen.MINESHAFT,
				new LootTableIdCondition.Builder(BuiltInLootTables.ABANDONED_MINESHAFT).build()));
		add("chests/end_city_treasure", new AddLootTableModifier(CCLootTableGen.END_CITY,
				new LootTableIdCondition.Builder(BuiltInLootTables.END_CITY_TREASURE).build()));

		add("drops/fire_essence", new AddItemModifier(CCItems.FIRE_ESSENCE.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.fireEssenceChance),
				entityType(EntityType.BLAZE), LootTableTemplate.byPlayer().build()));
		add("drops/ocean_essence", new AddItemModifier(CCItems.OCEAN_ESSENCE.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.oceanEssenceChance),
				entityType(EntityType.GUARDIAN), LootTableTemplate.byPlayer().build()));
		add("drops/death_essence", new AddItemModifier(CCItems.DEATH_ESSENCE.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.deathEssenceChance),
				damage(CCDamageTypes.WITHER),
				new EntityHealthCondition(IntConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.deathEssenceMinHealth))));
		add("drops/warden_sclerite", new AddItemModifier(CCItems.WARDEN_SCLERITE.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.wardenScleriteChance),
				entityType(EntityType.WARDEN), LootTableTemplate.byPlayer().build()));
		add("drops/shulker_scrap", new AddItemModifier(CCItems.SHULKER_SCRAP.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.shulkerScrapChance),
				entityType(EntityType.SHULKER), damage(DamageTypeTags.IS_EXPLOSION)));
		add("drops/light_fragment", new AddItemModifier(CCItems.LIGHT_FRAGMENT.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.lightFragmentChance),
				entityType(EntityType.HUSK), new PlayerFlagCondition(CCGeneralEventHandler.NETHER_STAGE)));
		add("drops/midnight_fragment", new AddItemModifier(CCItems.MIDNIGHT_FRAGMENT.get(),
				DoubleConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.midnightFragmentChance),
				entityType(EntityType.STRAY), new PlayerFlagCondition(CCGeneralEventHandler.NETHER_STAGE)));
		add("drops/soaring_wings", new AddItemModifier(CCItems.SOARING_WINGS.get(), null,
				entityType(EntityType.PHANTOM), LootTableTemplate.byPlayer().build(),
				entity(EntityPredicate.Builder.entity().located(LocationPredicate.atYLocation(MinMaxBounds.Doubles.atLeast(619))))));
		add("drops/pure_nether_star", new AddItemModifier(CCItems.PURE_NETHER_STAR.get(), null,
				entityType(EntityType.WITHER), new PlayerEffectCondition(MobEffectCategory.BENEFICIAL,
				IntConfigValue.of(CCModConfig.COMMON_PATH, CCModConfig.COMMON.pureNetherStarEffectCount))));
		add("drops/heart_fragment", new AddItemModifier(CCItems.HEART_FRAGMENT.get(), null,
				entityType(EntityType.PILLAGER), new ChargedCreeperKillCondition()));
	}

	public static LootItemCondition entity(EntityPredicate.Builder builder) {
		return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, builder).build();
	}

	public static LootItemCondition entityType(EntityType<?> type) {
		return entity(EntityPredicate.Builder.entity().of(type));
	}

	public static LootItemCondition entityType(TagKey<EntityType<?>> tag) {
		return entity(EntityPredicate.Builder.entity().of(tag));
	}

	public static LootItemCondition damage(TagKey<DamageType> tag) {
		return DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType()
				.tag(TagPredicate.is(tag))).build();
	}

}
