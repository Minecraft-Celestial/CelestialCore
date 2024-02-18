package com.xiaoyue.celestial_core.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToolTipUtils {

    public static float getNoZeroValue(float value) {
        return Math.max(value, 0);
    }

    public static double getNoZeroValue(double value) {
        return Math.max(value, 0);
    }

    public static UUID getFUuid() {
        return UUID.fromString("1-2-3-4-5");
    }

    public static String NullTooltip = "tooltip.celestial_core.null";

    public static void addSTooltip(List<Component> list, String s, ChatFormatting chatFormatting, String v) {
        list.add(Component.translatable(s).withStyle(chatFormatting).append(v));
    }

    public static void addSTooltip(List<Component> list, String s, ChatFormatting chatFormatting, float v) {
        list.add(Component.translatable(s).withStyle(chatFormatting).append(v + ""));
    }

    public static void addValueTooltip(List<Component> list, String s, ChatFormatting chatFormatting, float value) {
        list.add(Component.translatable(s).withStyle(chatFormatting).append(value + ""));
    }

    public static void addLocalTooltip(List<Component> list, String s, ChatFormatting chatFormatting) {
        list.add(Component.translatable(s).withStyle(chatFormatting));
    }

    public static Component addLocalTooltip(String s, Object... values) {
        ArrayList<Component> list = new ArrayList<>();
        addLocalTooltip(list, s, null, values);
        return list.get(0);
    }

    public static Component addLocalTooltip(String s) {
        return Component.translatable(s);
    }

    public static void addLocalTooltip(List<Component> list, String s) {
        list.add(Component.translatable(s));
    }

    public static void addLocalTooltip(List<Component> list, String s, @Nullable ChatFormatting chatFormatting, Object... values) {
        Component[] stringValues = new Component[values.length];
        int counter = 0;
        for (Object value : values) {
            MutableComponent comp;
            if (value instanceof MutableComponent) {
                comp = (MutableComponent)value;
            } else {
                comp = Component.literal(value.toString());
            }
            if (chatFormatting != null) {
                comp.withStyle(chatFormatting);
            }
            stringValues[counter] = comp;
            counter++;
        }
        list.add(Component.translatable(s, (Object[])stringValues));
    }
}
