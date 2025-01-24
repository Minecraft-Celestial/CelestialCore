package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

@Deprecated
public class EnchUtils {

    public static int getEnchSize(ItemStack stack) {
        return ItemUtils.getEnchSize(stack);
    }

    public static int getCurseEnch(ItemStack stack) {
        return ItemUtils.getCurseEnchSize(stack);
    }

    public static int getTotalCurseEnch(Player player) {
        return ItemUtils.getAllSlotCurseEnchSize(player);
    }

    public static int getArmorTotalEnchLevel(Player player) {
        return ItemUtils.getTotalEnchLevelOfArmors(player);
    }

    public static void removeCurseEnch(ItemStack curseStack) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(curseStack);
        enchantments.keySet().removeIf(Enchantment::isCurse);
        EnchantmentHelper.setEnchantments(enchantments, curseStack);
    }
}
