package com.xiaoyue.celestial_core.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class INBTUtils {

    public static boolean tagCheck(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.getTag() != null;
    }

    public static void removeTag(ItemStack stack, String tag) {
        if (tagCheck(stack, tag)) {
            stack.getTag().remove(tag);
        }
    }

    public static boolean getBoolean(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getBoolean(tag);
    }

    public static boolean hasBooleanTag(ItemStack stack, String tag, boolean value) {
        return stack.getOrCreateTag().getBoolean(tag) == value;
    }

    public static void setBoolean(ItemStack stack, String tag, boolean value) {
        stack.getOrCreateTag().putBoolean(tag, value);
    }

    public static int getInt(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getInt(tag);
    }

    public static void setInt(ItemStack stack, String tag, int value) {
        stack.getOrCreateTag().putInt(tag, value);
    }

    public static long getLong(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getLong(tag);
    }

    public static void setLong(ItemStack stack, String tag, long value) {
        stack.getOrCreateTag().putLong(tag, value);
    }

    public static float getFloat(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getFloat(tag);
    }

    public static void setFloat(ItemStack stack, String tag, float value) {
        stack.getOrCreateTag().putFloat(tag, value);
    }

    public static double getDouble(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getDouble(tag);
    }

    public static void setDouble(ItemStack stack, String tag, double value) {
        stack.getOrCreateTag().putDouble(tag, value);
    }

    public static String getString(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getString(tag);
    }

    public static boolean hasStringTag(ItemStack stack, String tag, String value) {
        return stack.getOrCreateTag().getString(tag).equals(value);
    }

    public static void setString(ItemStack stack, String tag, String value) {
        stack.getOrCreateTag().putString(tag, value);
    }

    public static CompoundTag getCompound(ItemStack stack, String tag) {
        return stack.getOrCreateTag().getCompound(tag);
    }

    public static void setCompound(ItemStack stack, String tag, CompoundTag value) {
        stack.getOrCreateTag().put(tag, value);
    }

}
