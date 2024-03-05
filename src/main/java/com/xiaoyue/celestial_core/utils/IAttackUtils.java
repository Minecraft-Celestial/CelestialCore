package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.entity.player.Player;

public class IAttackUtils {

	public static float getAttackCharged(Player player) {
		return player.getCurrentItemAttackStrengthDelay();
	}

	public static boolean isFullCharged(Player player) {
		return getAttackCharged(player) > 0.9f;
	}


}
