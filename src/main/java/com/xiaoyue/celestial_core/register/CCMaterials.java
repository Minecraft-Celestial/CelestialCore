package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.items.VirtualGoldArmor;
import com.xiaoyue.celestial_core.content.series.*;
import dev.xkmc.l2damagetracker.contents.materials.api.*;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.GenItemVanillaType;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.ToolStats;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;

import java.util.function.Supplier;

public enum CCMaterials implements IMatVanillaType {
    OCEAN("ocean", 3, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(1200, 8, 6, 1, 12),
            new ArmorStats(30, new int[]{3, 6, 8, 3}, 2, 0, 12),
            GenItemVanillaType.TOOL_GEN, GenItemVanillaType.ARMOR_GEN,
            new OceanTool().setStick(e -> CCItems.GUARDIAN_SPIKE.get(), false), new OceanArmor()),
    SAKURA("sakura", 2, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(260, 8, 5, 1, 15),
            new ArmorStats(15, new int[]{2, 5, 6, 2}, 0, 0, 15),
            GenItemVanillaType.TOOL_GEN, GenItemVanillaType.ARMOR_GEN,
            new SakuraTool().setStick(e -> Items.CHERRY_LOG, false), new SakuraArmor()),
    VIRTUAL_GOLD("virtual_gold", 3, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(1200, 12, 7, 1, 30),
            new ArmorStats(22, new int[]{3, 6, 8, 3}, 2, 0, 30),
            GenItemVanillaType.TOOL_GEN, VirtualGoldArmor.ARMOR_GEN,
            new VirtualGoldTool().setStick(e -> CCItems.FIRE_ESSENCE.get(), false), new com.xiaoyue.celestial_core.content.series.VirtualGoldArmor()),
    ;

    final String id;
    final Tier tier;
    final ArmorMaterial mat;
    final ToolConfig tool_config;
    final ArmorConfig armor_config;
    final IToolStats tool_stats;
    final ExtraToolConfig tool_extra;
    final ExtraArmorConfig armor_extra;

    CCMaterials(String name, int level, SoundEvent equip_sound, IToolStats tool, ArmorStats armor, ToolConfig tool_config, ArmorConfig armor_config, ExtraToolConfig tool_extra, ExtraArmorConfig armor_extra) {
        Supplier<Ingredient> ing = () -> Ingredient.of(CCItems.INGOTS[ordinal()]);
        this.id = name;
        this.tier = new ForgeTier(level, tool.durability(), tool.speed(), 0, tool.enchant(), tool_extra.getTier(level), ing);
        this.mat = new ArmorMat(armorPrefix(), armor.durability(), armor.protection(), armor.enchant(), equip_sound, armor.tough(), armor.kb(), ing);
        this.tool_config = tool_config;
        this.armor_config = armor_config;
        this.tool_stats = tool;
        this.tool_extra = tool_extra;
        this.armor_extra = armor_extra;
    }

    @Override
    public Item getIngot() {
        return CCItems.INGOTS[ordinal()].asItem();
    }

    @Override
    public Item getNugget() {
        return CCItems.NUGGETS[ordinal()].asItem();
    }

    @Override
    public Block getBlock() {
        return null;
    }

    @Override
    public String armorPrefix() {
        return CelestialCore.MODID + ":" + id;
    }

    public String getID() {
        return id;
    }

    @Override
    public ArmorConfig getArmorConfig() {
        return armor_config;
    }

    @Override
    public ToolConfig getToolConfig() {
        return tool_config;
    }

    @Override
    public IToolStats getToolStats() {
        return tool_stats;
    }

    @Override
    public Tier getTier() {
        return tier;
    }

    @Override
    public ExtraArmorConfig getExtraArmorConfig() {
        return armor_extra;
    }

    @Override
    public ArmorMaterial getArmorMaterial() {
        return mat;
    }

    @Override
    public ExtraToolConfig getExtraToolConfig() {
        return tool_extra;
    }

    @Override
    public ItemEntry<Item>[][] getGenerated() {
        return CCItems.GEN_ITEM;
    }

}
