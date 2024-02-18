package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class IPotionUtils {

    public static ItemStack setPotionStack(ItemStack stack, Potion potion ) {
        return PotionUtils.setPotion(stack, potion);
    }

    public static ItemStack setPotionStack(Item item, Potion potion ) {
        return PotionUtils.setPotion(item.getDefaultInstance(), potion);
    }

    public static ItemStack setPotionStack(Potion potion ) {
        return PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potion);
    }
}
