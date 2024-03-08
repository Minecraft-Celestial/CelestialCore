package com.xiaoyue.celestial_core.content.loot;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

public record DoubleConfigValue(String path, List<String> line) {

	public static DoubleConfigValue of(String file, ForgeConfigSpec.ConfigValue<Double> config) {
		return new DoubleConfigValue(file, config.getPath());
	}

	public static DoubleConfigValue of(String data) {
		int last = data.lastIndexOf('/');
		var line = data.substring(last + 1).split("\\.");
		return new DoubleConfigValue(data.substring(0, last), List.of(line));
	}

	public double get() {
		ModConfig file = ConfigTracker.INSTANCE.fileMap().get(this.path);
		if (file != null && file.getConfigData().get(this.line()) instanceof Double val) {
			return val;
		}
		return 0;
	}

	public String toData() {
		StringBuilder lines = new StringBuilder();
		for (var e : line) {
			if (!lines.isEmpty()) {
				lines.append(".");
			}
			lines.append(e);
		}
		return path + "/" + lines;
	}

}
