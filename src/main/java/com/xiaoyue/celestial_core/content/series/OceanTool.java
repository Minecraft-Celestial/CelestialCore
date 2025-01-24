package com.xiaoyue.celestial_core.content.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class OceanTool extends ExtraToolConfig {

    private final String IN_WATER = "CelestialCore_oceanTool_inWater";

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (selected) {
            stack.getOrCreateTag().putBoolean(IN_WATER, entity.isInWater());
        }
        super.inventoryTick(stack, level, entity, slot, selected);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state, float old) {
        if (stack.getOrCreateTag().getBoolean(IN_WATER)) {
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
