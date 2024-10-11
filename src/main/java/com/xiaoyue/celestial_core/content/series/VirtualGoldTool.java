package com.xiaoyue.celestial_core.content.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.utils.EnchUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class VirtualGoldTool extends ExtraToolConfig {

	private double add() {
		return CCModConfig.COMMON.virtualGoldToolPerAdd.get();
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state, float old) {
		int totalEnch = EnchUtils.getTotalEnch(stack);
		return (float) (old * (1 + add() * totalEnch));
	}

	@Override
	public void onDamage(AttackCache cache, ItemStack stack) {
		int totalEnch = EnchUtils.getTotalEnch(stack);
		cache.addHurtModifier(DamageModifier.multBase((float) (add() * totalEnch)));
		super.onDamage(cache, stack);
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		list.add(CCLangData.VIRTUAL_GOLD_GENERAL.get().withStyle(ChatFormatting.GRAY));
		list.add(CCLangData.VIRTUAL_GOLD_TOOL.get(CCLangData.chance(add())).withStyle(ChatFormatting.GRAY));
	}

}
