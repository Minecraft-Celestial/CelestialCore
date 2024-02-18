package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.content.generic.CTooltipItem;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

public class COItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // material
    public static final RegistryObject<Item> EARTH_CORE = ITEM.register("earth_core",
            () -> new CTooltipItem(new Item.Properties().rarity(IRarityUtils.DARK_GREEN), false));
    public static final RegistryObject<Item> VOID_ESSENCE = ITEM.register("void_essence",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> DEATH_ESSENCE = ITEM.register("death_essence",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> FIRE_ESSENCE = ITEM.register("fire_essence",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> OCEAN_ESSENCE = ITEM.register("ocean_essence",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> LIGHT_FRAGMENT = ITEM.register("light_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> MIDNIGHT_FRAGMENT = ITEM.register("midnight_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> TREASURE_FRAGMENT = ITEM.register("treasure_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> PURE_NETHER_STAR = ITEM.register("pure_nether_star",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> SHULKER_SCRAP = ITEM.register("shulker_scrap",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> SOARING_WINGS = ITEM.register("soaring_wings",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> HEART_FRAGMENT = ITEM.register("heart_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> THE_END_DUST = ITEM.register("the_end_dust",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> WARDEN_SCLERITE = ITEM.register("warden_sclerite",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> NEBULA_CUBE = ITEM.register("nebula_cube",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));
    public static final RegistryObject<Item> ICE_FRAGMENT = ITEM.register("ice_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.RARE), false));
    public static final RegistryObject<Item> EQUIPMENT_FRAGMENT = ITEM.register("equipment_fragment",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> CURSE_ESSENCE = ITEM.register("curse_essence",
            () -> new CTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false));

}
