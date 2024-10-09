package com.xiaoyue.celestial_core.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class IRarityUtils {

	public static Rarity RED = Rarity.create("red", ChatFormatting.RED);
	public static Rarity GREEN = Rarity.create("green", ChatFormatting.GREEN);
	public static Rarity GOLD = Rarity.create("gold", ChatFormatting.GOLD);
	public static Rarity YELLOW = Rarity.create("yellow", ChatFormatting.YELLOW);
	public static Rarity BLUE = Rarity.create("blue", ChatFormatting.BLUE);
	public static Rarity PINK = Rarity.create("pink", ChatFormatting.LIGHT_PURPLE);
	public static Rarity DARK_GREEN = Rarity.create("dark_green", ChatFormatting.DARK_GREEN);
	public static Rarity DARK_PURPLE = Rarity.create("dark_purple", ChatFormatting.DARK_PURPLE);
	public static Rarity DARK_AQUA = Rarity.create("dark_aqua", ChatFormatting.DARK_AQUA);

	public static Rarity create(String name, ChatFormatting chatFormatting) {
		return Rarity.create(name, chatFormatting);
	}
}
