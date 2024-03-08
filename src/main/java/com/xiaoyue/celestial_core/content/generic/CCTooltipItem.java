package com.xiaoyue.celestial_core.content.generic;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CCTooltipItem extends Item {

	public boolean canHurt;
	public Supplier<MutableComponent> sup;

	public CCTooltipItem(Properties pProperties, boolean canHurt, Supplier<MutableComponent> sup) {
		super(pProperties);
		this.canHurt = canHurt;
		this.sup = sup;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		list.add(sup.get().withStyle(ChatFormatting.GRAY));
	}

	@Override
	public boolean canBeHurtBy(DamageSource pDamageSource) {
		return canHurt;
	}
}
