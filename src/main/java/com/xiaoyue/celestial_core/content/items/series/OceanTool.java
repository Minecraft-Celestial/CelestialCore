package com.xiaoyue.celestial_core.content.items.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class OceanTool extends ExtraToolConfig {

	private final String KEY = "cc_in_water";

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		if (selected) {
			ItemUtils.ItemTag.setBoolean(stack, KEY, entity.isInWater());
		}
		super.inventoryTick(stack, level, entity, slot, selected);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state, float old) {
		if (ItemUtils.ItemTag.getBoolean(stack, KEY)) {
			return old * 5f;
		}
		return super.getDestroySpeed(stack, state, old);
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		list.add(CCLangData.OCEAN_TOOL.get().withStyle(ChatFormatting.GRAY));
		super.addTooltip(stack, list);
	}
}
