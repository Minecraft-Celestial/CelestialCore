package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CCItems {

	// material
	public static final ItemEntry<CCTooltipItem> EARTH_CORE = material("earth_core",
			p -> new CCTooltipItem(new Item.Properties().rarity(IRarityUtils.DARK_GREEN), false), "Craftable");
	public static final ItemEntry<CCTooltipItem> VOID_ESSENCE = material("void_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Can be found in End City chests");
	public static final ItemEntry<CCTooltipItem> DEATH_ESSENCE = material("death_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Dropped when mobs with at least 100 health is killed by withering effect");
	public static final ItemEntry<CCTooltipItem> FIRE_ESSENCE = material("fire_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "Rarely dropped from blaze");
	public static final ItemEntry<CCTooltipItem> OCEAN_ESSENCE = material("ocean_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Rarely dropped from guardian");
	public static final ItemEntry<CCTooltipItem> LIGHT_FRAGMENT = material("light_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "After defeating Wither, rarely dropped from Husks");
	public static final ItemEntry<CCTooltipItem> MIDNIGHT_FRAGMENT = material("midnight_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "After defeating Wither, rarely dropped from Stray");
	public static final ItemEntry<CCTooltipItem> TREASURE_FRAGMENT = material("treasure_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Can be found in Mineshaft chests");
	public static final ItemEntry<CCTooltipItem> PURE_NETHER_STAR = material("pure_nether_star",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "Dropped with wither is killed by player with more than 12 beneficial effects");
	public static final ItemEntry<CCTooltipItem> SHULKER_SCRAP = material("shulker_scrap",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Dropped when Shulker is killed by explosion");
	public static final ItemEntry<CCTooltipItem> SOARING_WINGS = material("soaring_wings",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Killed whenPhantom is killed above y=621");
	public static final ItemEntry<CCTooltipItem> HEART_FRAGMENT = material("heart_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "Dropped when Pillager is killed by charged Creeper");
	public static final ItemEntry<CCTooltipItem> THE_END_DUST = material("the_end_dust",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Rarely dropped when player equipped with Catastrophe Scroll kills mobs");
	public static final ItemEntry<CCTooltipItem> WARDEN_SCLERITE = material("warden_sclerite",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "Rarely dropped from Warden");
	public static final ItemEntry<CCTooltipItem> NEBULA_CUBE = material("nebula_cube",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "WIP");
	public static final ItemEntry<CCTooltipItem> ICE_FRAGMENT = material("ice_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.RARE), false), "WIP");
	public static final ItemEntry<CCTooltipItem> EQUIPMENT_FRAGMENT = material("equipment_fragment",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false), "WIP");
	public static final ItemEntry<CCTooltipItem> CURSE_ESSENCE = material("curse_essence",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false), "WIP");

	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory, String desc) {
		CelestialCore.REGISTRATE.addRawLang("tooltip.item." + CelestialCore.MODID + "." + id, desc);
		return CelestialCore.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}

}
