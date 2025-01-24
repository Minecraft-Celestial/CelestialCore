package com.xiaoyue.celestial_core.content.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SakuraArmor extends ExtraArmorConfig {

    private int time() {
        return CCModConfig.COMMON.sakuraToolRecoveryTime.get();
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.tickCount % (time() * 20) == 0) {
            ItemUtils.repairStack(stack);
        }
        super.onArmorTick(stack, world, player);
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.SAKURA_SERIES.get(CCLangData.num(time())).withStyle(ChatFormatting.GRAY));
        super.addTooltip(stack, list);
    }
}
