package com.xiaoyue.celestial_core.content.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CCTooltipItem extends Item {

    public boolean canHurt;

    public CCTooltipItem(Properties pProperties, boolean hurt) {
        super(pProperties);
        canHurt = hurt;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip." + this.getDescriptionId()));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return canHurt;
    }
}
