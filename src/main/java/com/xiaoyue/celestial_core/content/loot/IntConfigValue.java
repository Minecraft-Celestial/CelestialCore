package com.xiaoyue.celestial_core.content.loot;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

public record IntConfigValue(String path, List<String> line) {

	public static IntConfigValue of(String file, ForgeConfigSpec.ConfigValue<Integer> config) {
		return new IntConfigValue(file, config.getPath());
	}

	public static IntConfigValue of(String data) {
		int last = data.lastIndexOf('/');
		var line = data.substring(last + 1).split("\\.");
		return new IntConfigValue(data.substring(0, last), List.of(line));
	}

	public int get() {
		ModConfig file = ConfigTracker.INSTANCE.fileMap().get(this.path);
		if (file != null && file.getConfigData().get(this.line()) instanceof Number val) {
			return val.intValue();
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
