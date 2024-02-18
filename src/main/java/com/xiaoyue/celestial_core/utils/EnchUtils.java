package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class EnchUtils {

    public static int getCurseEnch(ItemStack stack) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        int totalCurses = 0;
        for (Enchantment enchantment: enchantments.keySet()) {
            if (enchantment.isCurse() && enchantments.get(enchantment) > 0) {
                totalCurses += 1;
            }
        }
        return totalCurses;
    }

    public static int getTotalCurseEnch(Player player) {
        int m = getCurseEnch(player.getMainHandItem());
        int o = getCurseEnch(player.getOffhandItem());
        int h = getCurseEnch(player.getItemBySlot(EquipmentSlot.HEAD));
        int c = getCurseEnch(player.getItemBySlot(EquipmentSlot.CHEST));
        int l = getCurseEnch(player.getItemBySlot(EquipmentSlot.LEGS));
        int f = getCurseEnch(player.getItemBySlot(EquipmentSlot.FEET));
        return m + o + h + c + l + f;
    }

    public static int getArmorTotalEnchLevel(Player player) {
        int level = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.isEnchanted()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
                for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                    level += entry.getValue();
                }
            }
        }
        return level;
    }

    public static void removeCurseEnch(Player player) {
        ItemStack offhandItem = player.getOffhandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(offhandItem);
        enchantments.keySet().removeIf(Enchantment::isCurse);
        EnchantmentHelper.setEnchantments(enchantments, offhandItem);
    }
}
