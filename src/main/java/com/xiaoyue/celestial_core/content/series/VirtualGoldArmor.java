package com.xiaoyue.celestial_core.content.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.register.CCMaterials;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class VirtualGoldArmor extends ExtraArmorConfig {

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (EntityUtils.getSeriesArmorAmount(player, CCMaterials.VIRTUAL_GOLD) == 4) {
            EntityUtils.addEct(player, MobEffects.FIRE_RESISTANCE, 40);
        }
        super.onArmorTick(stack, world, player);
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.VIRTUAL_GOLD_GENERAL.get().withStyle(ChatFormatting.GRAY));
        list.add(CCLangData.FULL_SET.get(CCLangData.eff(MobEffects.FIRE_RESISTANCE)).withStyle(ChatFormatting.GRAY));
        super.addTooltip(stack, list);
    }

}
