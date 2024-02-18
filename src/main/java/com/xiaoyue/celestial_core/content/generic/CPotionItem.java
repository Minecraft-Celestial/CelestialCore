package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.utils.IPotionUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;

public class CPotionItem extends PotionItem {

    public Potion potions;

    public CPotionItem(Properties pProperties, Potion potion) {
        super(pProperties);
        potions = potion;
    }

    @Override
    public ItemStack getDefaultInstance() {
        return IPotionUtils.setPotionStack(potions);
    }
}
