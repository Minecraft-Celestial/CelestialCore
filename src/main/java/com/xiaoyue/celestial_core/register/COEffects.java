package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.builders.NoConfigBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class COEffects {

	public static final List<RegistryEntry<? extends Potion>> POTION_LIST = new ArrayList<>();
	private static final List<Runnable> TEMP = new ArrayList<>();

	public static final RegistryEntry<Violent> VIOLENT = genEffect("violent", Violent::new, "");
	public static final RegistryEntry<Hidden> HIDDEN = genEffect("hidden", Hidden::new, "");
	public static final RegistryEntry<RottenCurse> ROTTEN_CURSE = genEffect("rotten_curse", RottenCurse::new, "");
	public static final RegistryEntry<BladeModifier> BLADE_MODIFIER = genEffect("blade_modifier", BladeModifier::new, "");
	public static final RegistryEntry<SoulShatter> SOUL_SHATTER = genEffect("soul_shatter", SoulShatter::new, "");
	public static final RegistryEntry<CritRate> CRIT_RATE = genEffect("crit_rate", CritRate::new, "");
	public static final RegistryEntry<CritDamage> CRIT_DAMAGE = genEffect("crit_damage", CritDamage::new, "");
	public static final RegistryEntry<ReplyPower> REPLY_POWER = genEffect("reply_power", ReplyPower::new, "");
	public static final RegistryEntry<ArrowDamage> ARROW_DAMAGE = genEffect("arrow_damage", ArrowDamage::new, "");

	static {
		regPotion2("crit_rate", CRIT_RATE::get, () -> Items.BLAZE_ROD, 6000, 9600);
		regPotion2("crit_damage", CRIT_DAMAGE::get, () -> Items.AMETHYST_SHARD, 6000, 9600);
		regPotion2("reply_power", CRIT_DAMAGE::get, () -> Items.HONEY_BOTTLE, 6000, 9600);
	}

	private static <T extends MobEffect> RegistryEntry<T> genEffect(String name, NonNullSupplier<T> sup, String desc) {
		return CelestialCore.REGISTRATE.effect(name, sup, desc).lang(MobEffect::getDescriptionId).register();
	}

	private static <T extends Potion> RegistryEntry<T> genPotion(String name, NonNullSupplier<T> sup) {
		var reg = CelestialCore.REGISTRATE;
		var ans = reg.entry(name, (cb) -> new NoConfigBuilder<>(
						reg, reg, name, cb, ForgeRegistries.Keys.POTIONS, sup))
				.register();
		POTION_LIST.add(ans);
		return ans;
	}

	public static void registerBrewingRecipe() {
		TEMP.forEach(Runnable::run);
	}

	private static void regPotion2(String id, Supplier<MobEffect> sup, Supplier<Item> item, int dur, int durLong) {
		var potion = genPotion(id + "_base", () -> new Potion(new MobEffectInstance(sup.get(), dur)));
		var longPotion = genPotion(id + "_log", () -> new Potion(new MobEffectInstance(sup.get(), durLong)));
		TEMP.add(() -> {
			PotionBrewing.addMix(Potions.AWKWARD, item.get(), potion.get());
			PotionBrewing.addMix(potion.get(), Items.REDSTONE, longPotion.get());
		});
	}

	private static void addMix(Potion in, Item item, Potion out) {

	}

	public static void register() {

	}

}