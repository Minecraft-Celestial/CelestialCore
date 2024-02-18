package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class CCGLMProvider extends GlobalLootModifierProvider {

	public CCGLMProvider(PackOutput output) {
		super(output, CelestialCore.MODID);
	}

	@Override
	protected void start() {
		add("chests/abandoned_mineshaft", new AddLootTableModifier(CCLootTableGen.MINESHAFT,
				new LootTableIdCondition.Builder(BuiltInLootTables.ABANDONED_MINESHAFT).build()));
		add("chests/end_city_treasure", new AddLootTableModifier(CCLootTableGen.END_CITY,
				new LootTableIdCondition.Builder(BuiltInLootTables.END_CITY_TREASURE).build()));
	}

}
