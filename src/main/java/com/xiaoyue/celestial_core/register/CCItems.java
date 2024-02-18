package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CTooltipItem;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CCItems {

	// material
	public static final ItemEntry<CTooltipItem> EARTH_CORE = material("earth_core",
			p -> new CTooltipItem(new Item.Properties().rarity(IRarityUtils.DARK_GREEN), false), "Craftable");
	public static final ItemEntry<CTooltipItem> VOID_ESSENCE = material("void_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Can be found in End City chests");
	public static final ItemEntry<CTooltipItem> DEATH_ESSENCE = material("death_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Dropped when mobs with at least 100 health is killed by withering effect");
	public static final ItemEntry<CTooltipItem> FIRE_ESSENCE = material("fire_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "Rarely dropped from blaze");
	public static final ItemEntry<CTooltipItem> OCEAN_ESSENCE = material("ocean_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Rarely dropped from guardian");
	public static final ItemEntry<CTooltipItem> LIGHT_FRAGMENT = material("light_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "After defeating Wither, rarely dropped from Husks");
	public static final ItemEntry<CTooltipItem> MIDNIGHT_FRAGMENT = material("midnight_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "After defeating Wither, rarely dropped from Stray");
	public static final ItemEntry<CTooltipItem> TREASURE_FRAGMENT = material("treasure_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Can be found in Mineshaft chests");
	public static final ItemEntry<CTooltipItem> PURE_NETHER_STAR = material("pure_nether_star",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "Dropped with wither is killed by player with more than 12 beneficial effects");
	public static final ItemEntry<CTooltipItem> SHULKER_SCRAP = material("shulker_scrap",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Dropped when Shulker is killed by explosion");
	public static final ItemEntry<CTooltipItem> SOARING_WINGS = material("soaring_wings",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Killed whenPhantom is killed above y=621");
	public static final ItemEntry<CTooltipItem> HEART_FRAGMENT = material("heart_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Dropped when Pillager is killed by charged Creeper");
	public static final ItemEntry<CTooltipItem> THE_END_DUST = material("the_end_dust",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Rarely dropped when player equipped with Demon Curse kills mobs");
	public static final ItemEntry<CTooltipItem> WARDEN_SCLERITE = material("warden_sclerite",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Rarely dropped from Warden");
	public static final ItemEntry<CTooltipItem> NEBULA_CUBE = material("nebula_cube",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "WIP");
	public static final ItemEntry<CTooltipItem> ICE_FRAGMENT = material("ice_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "WIP");
	public static final ItemEntry<CTooltipItem> EQUIPMENT_FRAGMENT = material("equipment_fragment",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "WIP");
	public static final ItemEntry<CTooltipItem> CURSE_ESSENCE = material("curse_essence",
			p -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "WIP");

	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory, String desc) {
		CelestialCore.REGISTRATE.addRawLang("tooltip.item." + CelestialCore.MODID + "." + id, desc);
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}

}
