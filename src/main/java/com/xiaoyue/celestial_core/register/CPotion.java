package com.xiaoyue.celestial_core.register;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

public class CPotion {

    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(ForgeRegistries.POTIONS,MODID);

    public static final RegistryObject<Potion> CRIT_RATE_BASE = POTION.register("crit_rate_base",
            () -> new Potion(new MobEffectInstance(CEffects.CRIT_RATE.get(), 6000)));

    public static final RegistryObject<Potion> CRIT_RATE_LOG = POTION.register("crit_rate_log",
            () -> new Potion(new MobEffectInstance(CEffects.CRIT_RATE.get(), 9600)));

    public static final RegistryObject<Potion> CRIT_DAMAGE_BASE = POTION.register("crit_damage_base",
            () -> new Potion(new MobEffectInstance(CEffects.CRIT_DAMAGE.get(), 6000)));

    public static final RegistryObject<Potion> CRIT_DAMAGE_LOG = POTION.register("crit_damage_log",
            () -> new Potion(new MobEffectInstance(CEffects.CRIT_DAMAGE.get(), 9600)));

    public static final RegistryObject<Potion> REPLY_POWER_BASE = POTION.register("reply_power_base",
            () -> new Potion(new MobEffectInstance(CEffects.REPLY_POWER.get(), 6000)));

    public static final RegistryObject<Potion> REPLY_POWER_LOG = POTION.register("reply_power_log",
            () -> new Potion(new MobEffectInstance(CEffects.REPLY_POWER.get(), 9600)));

}
