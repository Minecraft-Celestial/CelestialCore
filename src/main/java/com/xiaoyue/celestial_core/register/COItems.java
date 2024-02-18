package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CTooltipItem;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class COItems {

	// material
	public static final ItemEntry<CTooltipItem> EARTH_CORE = material("earth_core",
			p -> new CTooltipItem(new Item.Properties().rarity(IRarityUtils.DARK_GREEN), false));
	public static final ItemEntry<CTooltipItem> VOID_ESSENCE = material("void_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> DEATH_ESSENCE = material("death_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> FIRE_ESSENCE = material("fire_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
	public static final ItemEntry<CTooltipItem> OCEAN_ESSENCE = material("ocean_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> LIGHT_FRAGMENT = material("light_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
	public static final ItemEntry<CTooltipItem> MIDNIGHT_FRAGMENT = material("midnight_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> TREASURE_FRAGMENT = material("treasure_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> PURE_NETHER_STAR = material("pure_nether_star",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
	public static final ItemEntry<CTooltipItem> SHULKER_SCRAP = material("shulker_scrap",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> SOARING_WINGS = material("soaring_wings",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> HEART_FRAGMENT = material("heart_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> THE_END_DUST = material("the_end_dust",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> WARDEN_SCLERITE = material("warden_sclerite",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> NEBULA_CUBE = material("nebula_cube",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
	public static final ItemEntry<CTooltipItem> ICE_FRAGMENT = material("ice_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
	public static final ItemEntry<CTooltipItem> EQUIPMENT_FRAGMENT = material("equipment_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
	public static final ItemEntry<CTooltipItem> CURSE_ESSENCE = material("curse_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));

	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory) {
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}

}
