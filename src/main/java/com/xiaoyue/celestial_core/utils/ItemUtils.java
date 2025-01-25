package com.xiaoyue.celestial_core.utils;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ItemUtils {

    public static final int MAX_USE_TIME = 72000;

    public static int getEnchSize(ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack).size();
    }

    public static int getCurseEnchSize(ItemStack stack) {
        int totalCurses = 0;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        for (Enchantment enchantment : enchantments.keySet()) {
            if (!enchantment.isCurse() || enchantments.get(enchantment) <= 0) continue;
            totalCurses += 1;
        }
        return totalCurses;
    }

    public static int getAllSlotCurseEnchSize(Player player) {
        int total = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            int curseEnch = ItemUtils.getCurseEnchSize(player.getItemBySlot(slot));
            total += curseEnch;
        }
        return total;
    }

    public static int getTotalEnchLevelOfArmors(Player player) {
        int level = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.isEnchanted()) continue;
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                level += entry.getValue();
            }
        }
        return level;
    }

    public static boolean hasInHand(Player player, ItemStack stack) {
        for (InteractionHand hand : InteractionHand.values()) {
            return player.getItemInHand(hand).equals(stack);
        }
        return false;
    }

    public static String getPathAfterSlash(String path) {
        int index = path.lastIndexOf("/");
        if (index != -1 && index < path.length() - 1) {
            return path.substring(index + 1);
        }
        return path;
    }

    public static void defaultAnvilOutput(AnvilUpdateEvent event, ItemStack output) {
        ItemUtils.defaultAnvilOutput(event, output, 10);
    }

    public static void defaultAnvilOutput(AnvilUpdateEvent event, ItemStack output, int expCost) {
        event.setMaterialCost(1);
        event.setCost(expCost);
        event.setOutput(output);
    }

    public static int calculateRef(RandomSource random, int origin, double chance) {
        return calculateRef(origin, random.nextDouble() < chance, 1);
    }

    public static int calculateRef(int origin, boolean canReduce, int toAdd) {
        int newInt = 0;
        for (int i = 0; i < origin; i++) {
            if (canReduce) newInt += toAdd;
        }
        return newInt;
    }

    public static void toShrink(ItemStack stack, Player player) {
        if (!player.getAbilities().instabuild || !player.isCreative()) {
            stack.shrink(1);
        }
    }

    @Nullable
    public static ResourceLocation getKey(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    public static List<ItemStack> getDefaultLootStacks(ResourceLocation lootTable, ServerPlayer player) {
        LootParams lootParams = (new LootParams.Builder(player.serverLevel()))
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.ORIGIN, player.position())
                .withLuck(player.getLuck())
                .create(LootContextParamSets.GIFT);
        return ItemUtils.getLootStacks(lootTable, player, lootParams);
    }

    public static List<ItemStack> getLootStacks(ResourceLocation lootTable, ServerPlayer player, LootParams lootParams) {
        MinecraftServer server = player.level().getServer();
        if (server == null || lootTable.equals(BuiltInLootTables.EMPTY)) {
            return List.of();
        }
        return server.getLootData().getLootTable(lootTable).getRandomItems(lootParams);
    }

    public static void repairStack(ItemStack stack) {
        repairStack(stack, 1);
    }

    public static void repairStack(ItemStack stack, int value) {
        if (stack.isDamaged()) {
            stack.setDamageValue(stack.getDamageValue() - value);
        }
    }

    public static int getDurability(ItemStack stack) {
        return stack.getMaxDamage() - stack.getDamageValue();
    }

    public static void directDamage(ItemStack stack, int value) {
        int toDamage = stack.getDamageValue() + value;
        if (stack.getMaxDamage() > toDamage) {
            stack.setDamageValue(toDamage);
        } else {
            stack.shrink(1);
        }
    }

    public static AttributeModifier.Operation getOperation(int id) {
        return AttributeModifier.Operation.fromValue(id);
    }

    public static AttributeModifier addMod(String name, double amount, AttributeModifier.Operation operation) {
        return new AttributeModifier(MathHelper.getUUIDFromString(name), name, amount, operation);
    }

    public static AttributeModifier addMod(String name, double amount, int operation) {
        return new AttributeModifier(MathHelper.getUUIDFromString(name), name, amount, ItemUtils.getOperation(operation));
    }

    public static MutableComponent getUSNameInfo(String info) {
        return Component.literal(RegistrateLangProvider.toEnglishName(info));
    }

    @Deprecated
    public static Component addTranslatable(String info, @Nullable ChatFormatting color) {
        MutableComponent tip = Component.translatable(info);
        if (color != null) return tip.withStyle(color);
        return tip;
    }

    @Deprecated
    public static Component addTranslatable(String info, @Nullable ChatFormatting color, Object... objects) {
        MutableComponent tip = Component.translatable(info, objects);
        if (color != null) return tip.withStyle(color);
        return tip;
    }

    @Deprecated(forRemoval = true)
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
