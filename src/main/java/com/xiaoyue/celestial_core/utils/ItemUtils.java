package com.xiaoyue.celestial_core.utils;

import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUtils {

	public static void repairStack(ItemStack stack) {
		repairStack(stack, 1);
	}

	public static void repairStack(ItemStack stack, int value) {
		stack.setDamageValue(stack.getDamageValue() - value);
	}

	public static AttributeModifier.Operation getOpFromId(int id) {
		return AttributeModifier.Operation.fromValue(id);
	}

	public static AttributeModifier addMod(String name, double amount, AttributeModifier.Operation operation) {
		return new AttributeModifier(MathHelper.getUUIDFromString(name), name, amount, operation);
	}

	public static AttributeModifier addMod(String name, double amount, int operation) {
		return new AttributeModifier(MathHelper.getUUIDFromString(name), name, amount, getOpFromId(operation));
	}

	public static Component addTranslatable(String info, @Nullable ChatFormatting color) {
		MutableComponent tip = Component.translatable(info);
		if (color != null) {
			return tip.withStyle(color);
		}
		return tip;
	}

	public static Component addTranslatable(String info, @Nullable ChatFormatting color, Object... objects) {
		MutableComponent tip = Component.translatable(info, objects);
		if (color != null) {
			return tip.withStyle(color);
		}
		return tip;
	}

	public static void addTranslatable(List<Component> list, String info, @Nullable ChatFormatting color) {
		list.add(addTranslatable(info, color));
	}

	public static void addTranslatable(List<Component> list, String info, @Nullable ChatFormatting color, Object... objects) {
		list.add(addTranslatable(info, color, objects));
	}

	public static class ItemTag {

		public static boolean tagCheck(ItemStack stack) {
			return !stack.isEmpty() && stack.getTag() != null;
		}

		public static void removeTag(ItemStack stack, String tag) {
			if (tagCheck(stack)) stack.getTag().remove(tag);
		}

		public static boolean getBoolean(ItemStack stack, String tag) {
			return tagCheck(stack) && stack.getTag().getBoolean(tag);
		}

		public static boolean isBoolean(ItemStack stack, String tag, boolean value) {
			return tagCheck(stack) && (stack.getTag().getBoolean(tag) == value);
		}

		public static void setBoolean(ItemStack stack, String tag, boolean value) {
			stack.getOrCreateTag().putBoolean(tag, value);
		}

		public static int getInt(ItemStack stack, String tag) {
			return tagCheck(stack) ? stack.getTag().getInt(tag) : 0;
		}

		public static int getInt(ItemStack stack, String tag, int def) {
			return tagCheck(stack) ? stack.getTag().getInt(tag) : def;
		}

		public static void setInt(ItemStack stack, String tag, int value) {
			stack.getOrCreateTag().putInt(tag, value);
		}

		public static float getFloat(ItemStack stack, String tag) {
			return tagCheck(stack) ? stack.getTag().getFloat(tag) : 0f;
		}

		public static float getFloat(ItemStack stack, String tag, float def) {
			return tagCheck(stack) ? stack.getTag().getFloat(tag) : def;
		}

		public static void setFloat(ItemStack stack, String tag, float value) {
			stack.getOrCreateTag().putFloat(tag, value);
		}

		public static double getDouble(ItemStack stack, String tag) {
			return tagCheck(stack) ? stack.getTag().getDouble(tag) : 0;
		}

		public static double getDouble(ItemStack stack, String tag, double def) {
			return tagCheck(stack) ? stack.getTag().getDouble(tag) : def;
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

		public static ListTag getListTag(ItemStack stack, String tag, int type) {
			return stack.getOrCreateTag().getList(tag, type);
		}
	}
}
