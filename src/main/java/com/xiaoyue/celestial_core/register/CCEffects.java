package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.builders.NoConfigBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.effects.*;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CCEffects {

    public static final List<RegistryEntry<? extends Potion>> POTION_LIST = new ArrayList<>();
    private static final List<Runnable> TEMP = new ArrayList<>();

    public static final RegistryEntry<AttributeEffect> CRIT_RATE = genEffect("crit_rate",
            () -> new AttributeEffect(0, 0xffd15720, L2DamageTracker.CRIT_RATE.get(),
                    CelestialCore.loc("crit_rate"), 0.1f, 0), "Increase crit rate");
    public static final RegistryEntry<AttributeEffect> CRIT_DAMAGE = genEffect("crit_damage",
            () -> new AttributeEffect(0, 0xffe1a76c, L2DamageTracker.CRIT_DMG.get(),
                    CelestialCore.loc("crit_damage"), 0.1f, 0), "Increase crit damage");
    public static final RegistryEntry<AttributeEffect> ARROW_DAMAGE = genEffect("arrow_damage",
            () -> new AttributeEffect(0, 0xffd18920, L2DamageTracker.BOW_STRENGTH.get(),
                    CelestialCore.loc("arrow_damage"), 0.2f, 0), "Increase arrow damage");
    public static final RegistryEntry<AttributeEffect> REPLY_POWER = genEffect("regen_rate",
            () -> new AttributeEffect(0, 0xffd1209d, CCAttributes.REPLY_POWER.get(),
                    CelestialCore.loc("regen_rate"), 0.2f, 0), "Increase regeneration rate");

    public static final RegistryEntry<CelestialEffect> VIOLENT = genEffect("violent",
            () -> new CelestialEffect(MobEffectCategory.NEUTRAL, 0xff841d1d), "Melee damage bypass armor");
    public static final RegistryEntry<CelestialEffect> SILENT = genEffect("silent",
            () -> new CelestialEffect(MobEffectCategory.NEUTRAL, 0xffffffff), "You won't make a vibration");
    public static final RegistryEntry<CelestialEffect> UNYIELDING = genEffect("unyielding",
            () -> new CelestialEffect(MobEffectCategory.BENEFICIAL, 0xfffff39b), "Stop you from dying");

    public static final RegistryEntry<Hidden> HIDDEN = genEffect("hidden", Hidden::new, "Cannot be seen as enemy. Removed on attack");
    public static final RegistryEntry<RottenCurse> ROTTEN_CURSE = genEffect("rotten_curse", RottenCurse::new, "Inflict damage after being attacked, prevents teleport");
    public static final RegistryEntry<ReinforcedBlade> BLADE_MODIFIER = genEffect("reinforced_blade", ReinforcedBlade::new, "Increase attack damage and crit rate");
    public static final RegistryEntry<SoulShatter> SOUL_SHATTER = genEffect("soul_shatter", SoulShatter::new, "Reduce max health and movement speed");
    public static final RegistryEntry<FearCurse> FEAR_CURSE = genEffect("fear_curse", FearCurse::new, "You will get hurt when you attack");

    static {
        initGradesPotion("crit_rate", CRIT_RATE::get, CCItems.OCEAN_ESSENCE::get, 6000, 9600);
        initGradesPotion("crit_damage", CRIT_DAMAGE::get, CCItems.FIRE_ESSENCE::get, 6000, 9600);
        initGradesPotion("regen_rate", REPLY_POWER::get, CCItems.HEART_FRAGMENT::get, 6000, 9600);
        initGradesPotion("arrow_damage", ARROW_DAMAGE::get, CCItems.LIGHT_FRAGMENT::get, 6000, 9600);
    }

    private static <T extends MobEffect> RegistryEntry<T> genEffect(String name, NonNullSupplier<T> sup, String desc) {
        return CelestialCore.REGISTRATE.effect(name, sup, desc).lang(MobEffect::getDescriptionId).register();
    }

    private static <T extends Potion> RegistryEntry<T> genPotion(String name, NonNullSupplier<T> sup) {
        var reg = CelestialCore.REGISTRATE;
        var ans = reg.entry(name, (cb) -> new NoConfigBuilder<>(reg, reg, name, cb, ForgeRegistries.Keys.POTIONS, sup)).register();
        POTION_LIST.add(ans);
        return ans;
    }

    public static void registerBrewingRecipe() {
        TEMP.forEach(Runnable::run);
    }

    private static void initGradesPotion(String id, Supplier<MobEffect> sup, Supplier<Item> item, int dur, int durLong) {
        var potion = genPotion(id, () -> new Potion(new MobEffectInstance(sup.get(), dur)));
        var longPotion = genPotion("long_" + id, () -> new Potion(new MobEffectInstance(sup.get(), durLong)));
        TEMP.add(() -> {
            PotionBrewing.addMix(Potions.AWKWARD, item.get(), potion.get());
            PotionBrewing.addMix(potion.get(), Items.REDSTONE, longPotion.get());
        });
    }

    public static void register() {

    }

}