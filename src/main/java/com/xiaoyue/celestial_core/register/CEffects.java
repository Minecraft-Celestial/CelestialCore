package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.content.effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

public class CEffects {

    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

    public static final RegistryObject<MobEffect> VIOLENT = EFFECT.register("violent", Violent::new);
    public static final RegistryObject<MobEffect> HIDDEN = EFFECT.register("hidden", Hidden::new);
    public static final RegistryObject<MobEffect> ROTTEN_CURSE = EFFECT.register("rotten_curse", RottenCurse::new);
    public static final RegistryObject<MobEffect> BLADE_MODIFIER = EFFECT.register("blade_modifier", BladeModifier::new);
    public static final RegistryObject<MobEffect> SOUL_SHATTER = EFFECT.register("soul_shatter", SoulShatter::new);
    public static final RegistryObject<MobEffect> CRIT_RATE = EFFECT.register("crit_rate", CritRate::new);
    public static final RegistryObject<MobEffect> CRIT_DAMAGE = EFFECT.register("crit_damage", CritDamage::new);
    public static final RegistryObject<MobEffect> REPLY_POWER = EFFECT.register("reply_power", ReplyPower::new);
    public static final RegistryObject<MobEffect> ARROW_DAMAGE = EFFECT.register("arrow_damage", ArrowDamage::new);

}