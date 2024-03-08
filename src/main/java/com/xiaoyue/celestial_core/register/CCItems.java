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

public class CCItems {

	// material
	public static final ItemEntry<Item> EARTH_CORE = material("earth_core",
			p -> new Item(new Item.Properties().rarity(IRarityUtils.DARK_GREEN)));
	public static final ItemEntry<CCTooltipItem> TREASURE_FRAGMENT = material("treasure_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, CCLangData.TREASURE_FRAGMENT::get));
	public static final ItemEntry<CCTooltipItem> VOID_ESSENCE = material("void_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, CCLangData.VOID_ESSENCE::get));
	public static final ItemEntry<CCTooltipItem> FIRE_ESSENCE = material("fire_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
					() -> CCLangData.simpleDrop(EntityType.BLAZE, CCModConfig.COMMON.fireEssenceChance.get())));
	public static final ItemEntry<CCTooltipItem> OCEAN_ESSENCE = material("ocean_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false,
					() -> CCLangData.simpleDrop(EntityType.GUARDIAN, CCModConfig.COMMON.oceanEssenceChance.get())));
	public static final ItemEntry<CCTooltipItem> WARDEN_SCLERITE = material("warden_sclerite",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
					() -> CCLangData.simpleDrop(EntityType.WARDEN, CCModConfig.COMMON.wardenScleriteChance.get())));
	public static final ItemEntry<CCTooltipItem> LIGHT_FRAGMENT = material("light_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
					() -> CCLangData.witherDrop(EntityType.HUSK, CCModConfig.COMMON.lightFragmentChance.get())));
	public static final ItemEntry<CCTooltipItem> MIDNIGHT_FRAGMENT = material("midnight_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
					() -> CCLangData.witherDrop(EntityType.STRAY, CCModConfig.COMMON.midnightFragmentChance.get())));
	public static final ItemEntry<CCTooltipItem> DEATH_ESSENCE = material("death_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
					() -> CCLangData.deathEssence(
							CCModConfig.COMMON.deathEssenceChance.get(),
							CCModConfig.COMMON.deathEssenceMinHealth.get()
					)));
	public static final ItemEntry<CCTooltipItem> PURE_NETHER_STAR = material("pure_nether_star",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false,
					() -> CCLangData.PURE_NETHER_STAR.get(CCLangData.num(CCModConfig.COMMON.pureNetherStarEffectCount.get()))));
	public static final ItemEntry<CCTooltipItem> SHULKER_SCRAP = material("shulker_scrap",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
					() -> CCLangData.SHULKER_SCRAP.get(CCLangData.entity(EntityType.SHULKER),
							CCLangData.chance(CCModConfig.COMMON.shulkerScrapChance.get()))));
	public static final ItemEntry<CCTooltipItem> SOARING_WINGS = material("soaring_wings",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false,
					() -> CCLangData.SOARING_WINGS.get(CCLangData.entity(EntityType.PHANTOM), CCLangData.num(620))));
	public static final ItemEntry<CCTooltipItem> HEART_FRAGMENT = material("heart_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false, () ->
					CCLangData.HEART_FRAGMENT.get(CCLangData.entity(EntityType.PILLAGER))));

	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory) {
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}

}
