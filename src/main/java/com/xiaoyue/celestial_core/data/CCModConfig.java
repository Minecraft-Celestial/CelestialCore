package com.xiaoyue.celestial_core.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class CCModConfig {

	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public final ForgeConfigSpec.DoubleValue fireEssenceChance;
		public final ForgeConfigSpec.DoubleValue oceanEssenceChance;
		public final ForgeConfigSpec.DoubleValue deathEssenceChance;
		public final ForgeConfigSpec.IntValue deathEssenceMinHealth;
		public final ForgeConfigSpec.DoubleValue wardenScleriteChance;
		public final ForgeConfigSpec.DoubleValue shulkerScrapChance;
		public final ForgeConfigSpec.DoubleValue lightFragmentChance;
		public final ForgeConfigSpec.DoubleValue midnightFragmentChance;
		public final ForgeConfigSpec.IntValue pureNetherStarEffectCount;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("materials");
			fireEssenceChance = builder.defineInRange("fireEssenceChance", 0.08, 0, 1);
			oceanEssenceChance = builder.defineInRange("oceanEssenceChance", 0.08, 0, 1);
			deathEssenceChance = builder.defineInRange("deathEssenceChance", 0.6, 0, 1);
			deathEssenceMinHealth = builder.defineInRange("deathEssenceMinHealth", 100, 0, 1000000);
			wardenScleriteChance = builder.defineInRange("wardenScleriteChance", 0.5, 0, 1);
			shulkerScrapChance = builder.defineInRange("shulkerScrapChance", 0.5, 0, 1);
			lightFragmentChance = builder.defineInRange("lightFragmentChance", 0.05, 0, 1);
			midnightFragmentChance = builder.defineInRange("midnightFragmentChance", 0.05, 0, 1);
			pureNetherStarEffectCount = builder.defineInRange("pureNetherStarEffectCount", 12, 0, 100);
			builder.pop();
		}

	}


	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	public static String COMMON_PATH;

	static {
		final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();

		final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
	}

	/**
	 * Registers any relevant listeners for config
	 */
	public static void init() {
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
		COMMON_PATH = register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static String register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = "celestial_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
		return path;
	}

}
