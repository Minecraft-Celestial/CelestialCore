package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;

public class CCItems {

	// material
	public static final ItemEntry<Item> EARTH_CORE;
	public static final ItemEntry<CCTooltipItem> TREASURE_FRAGMENT;
	public static final ItemEntry<CCTooltipItem> VOID_ESSENCE;
	public static final ItemEntry<CCTooltipItem> FIRE_ESSENCE;
	public static final ItemEntry<CCTooltipItem> OCEAN_ESSENCE;
	public static final ItemEntry<CCTooltipItem> WARDEN_SCLERITE;
	public static final ItemEntry<CCTooltipItem> LIGHT_FRAGMENT;
	public static final ItemEntry<CCTooltipItem> MIDNIGHT_FRAGMENT;
	public static final ItemEntry<CCTooltipItem> DEATH_ESSENCE;
	public static final ItemEntry<CCTooltipItem> PURE_NETHER_STAR;
	public static final ItemEntry<CCTooltipItem> SHULKER_SCRAP;
	public static final ItemEntry<CCTooltipItem> SOARING_WINGS;
	public static final ItemEntry<CCTooltipItem> HEART_FRAGMENT;
	public static final ItemEntry<CCTooltipItem> SAKURA_FRAGMENT;
	public static final ItemEntry<CCTooltipItem> SAKURA_STEEL;
	public static final ItemEntry<Item> OCEAN_INGOT;
	public static final ItemEntry<Item> GUARDIAN_OCEAN_INGOT;
	public static final ItemEntry<Item> OCEAN_NUGGET;
	public static final ItemEntry<CCTooltipItem> GUARDIAN_SPIKE;
	public static final ItemEntry<Item> VIRTUAL_GOLD_INGOT;
	public static final ItemEntry<Item> VIRTUAL_GOLD_NUGGET;

	static {
		CelestialCore.REGISTRATE.defaultCreativeTab(CelestialCore.TAB.getKey());
		EARTH_CORE = material("earth_core",
				p -> new Item(new Item.Properties().rarity(IRarityUtils.DARK_GREEN)));
		TREASURE_FRAGMENT = material("treasure_fragment",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, CCLangData.TREASURE_FRAGMENT::get));
		VOID_ESSENCE = material("void_essence",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, CCLangData.VOID_ESSENCE::get));
		FIRE_ESSENCE = material("fire_essence",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
						() -> CCLangData.simpleDrop(EntityType.BLAZE, CCModConfig.COMMON.fireEssenceChance.get())));
		OCEAN_ESSENCE = material("ocean_essence",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false,
						() -> CCLangData.simpleDrop(EntityType.GUARDIAN, CCModConfig.COMMON.oceanEssenceChance.get())));
		WARDEN_SCLERITE = material("warden_sclerite",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
						() -> CCLangData.simpleDrop(EntityType.WARDEN, CCModConfig.COMMON.wardenScleriteChance.get())));
		LIGHT_FRAGMENT = material("light_fragment",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
						() -> CCLangData.witherDrop(EntityType.HUSK, CCModConfig.COMMON.lightFragmentChance.get())));
		MIDNIGHT_FRAGMENT = material("midnight_fragment",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
						() -> CCLangData.witherDrop(EntityType.STRAY, CCModConfig.COMMON.midnightFragmentChance.get())));
		DEATH_ESSENCE = material("death_essence",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
						() -> CCLangData.deathEssence(CCModConfig.COMMON.deathEssenceChance.get(),
								CCModConfig.COMMON.deathEssenceMinHealth.get())));
		PURE_NETHER_STAR = material("pure_nether_star",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
						() -> CCLangData.PURE_NETHER_STAR.get(CCLangData.entity(EntityType.WITHER),
								CCLangData.num(CCModConfig.COMMON.pureNetherStarEffectCount.get()))));
		SHULKER_SCRAP = material("shulker_scrap",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
						() -> CCLangData.SHULKER_SCRAP.get(CCLangData.chance(CCModConfig.COMMON.shulkerScrapChance.get()),
								CCLangData.entity(EntityType.SHULKER))));
		SOARING_WINGS = material("soaring_wings",
				p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.GOLD), false,
						() -> CCLangData.SOARING_WINGS.get(CCLangData.entity(EntityType.PHANTOM), CCLangData.num(620))));
		HEART_FRAGMENT = material("heart_fragment",
				p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, () ->
						CCLangData.HEART_FRAGMENT.get(CCLangData.entity(EntityType.PILLAGER))));
		SAKURA_FRAGMENT = material("sakura_fragment",
				p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.PINK), true,
						() -> CCLangData.SAKURA_FRAGMENT.get(CCLangData.chance(CCModConfig.COMMON.sakuraFragmentChance.get()))));
		SAKURA_STEEL = material("sakura_steel",
				p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.PINK), false, CCLangData.SAKURA_STEEL::get));
		OCEAN_INGOT = material("ocean_ingot",
				p -> new Item(new Item.Properties().rarity(IRarityUtils.BLUE)));
		GUARDIAN_OCEAN_INGOT = material("guardian_ocean_ingot",
				p -> new Item(new Item.Properties().rarity(IRarityUtils.BLUE)));
		OCEAN_NUGGET = material("ocean_nugget",
				p -> new Item(new Item.Properties().rarity(IRarityUtils.BLUE)));
		GUARDIAN_SPIKE = material("guardian_spike",
				p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.BLUE), false,
						() -> CCLangData.simpleDrop(EntityType.GUARDIAN, CCModConfig.COMMON.guardianSpikeChance.get())));
		VIRTUAL_GOLD_INGOT = material("virtual_gold_ingot",
				p -> new Item(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE)));
		VIRTUAL_GOLD_NUGGET = material("virtual_gold_nugget",
				p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE), false,
						() -> CCLangData.VIRTUAL_GOLD_NUGGET.get(CCLangData.chance(CCModConfig.COMMON.virtualGoldNuggetChance.get()))));
		CelestialCore.REGISTRATE.defaultCreativeTab(CelestialCore.TOOL_TAB.getKey());

	}

	public static final ItemLike[] INGOTS = {CCItems.GUARDIAN_OCEAN_INGOT, CCItems.SAKURA_STEEL, CCItems.VIRTUAL_GOLD_INGOT};
	public static final ItemLike[] NUGGETS = {CCItems.OCEAN_NUGGET, CCItems.SAKURA_FRAGMENT, CCItems.VIRTUAL_GOLD_NUGGET};
	// tool
	public static final ItemEntry<Item>[][] GEN_ITEM = CelestialCore.MATS.genItem(CCMaterials.values());


	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory) {
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static <T extends Item> ItemEntry<T> material(String path, String id, NonNullFunction<Item.Properties, T> factory) {
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/" + path + "/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}
}