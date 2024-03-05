package com.xiaoyue.celestial_core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class CCUtils {

	public static int getLight(Level level, BlockPos pos) {
		if (level instanceof ServerLevel sl) {
			return sl.getMaxLocalRawBrightness(pos);
		}
		return level.getMaxLocalRawBrightness(pos, getSkyDarken(level));
	}

	public static int getSkyDarken(Level level) {
		double d0 = 1 - (level.getRainLevel(1) * 5) / 16;
		double d1 = 1 - (level.getThunderLevel(1) * 5) / 16;
		double d2 = 0.5 + 2 * Mth.clamp(Mth.cos(level.getTimeOfDay(1) * ((float) Math.PI * 2)), -0.25, 0.25);
		return (int) ((1 - d2 * d0 * d1) * 11);
	}

}
