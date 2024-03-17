package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2damagetracker.contents.attributes.WrappedAttribute;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;

@SuppressWarnings("unchecked")
public class CCAttributes {

	public static final RegistryEntry<WrappedAttribute> REPLY_POWER = L2DamageTracker.regWrapped(CelestialCore.REGISTRATE,
			"regen_rate", 1, 0, 64, "Regeneration Rate", L2DamageTracker.PERCENTAGE);
	public static final RegistryEntry<WrappedAttribute> ARROW_SPEED = L2DamageTracker.regWrapped(CelestialCore.REGISTRATE,
			"arrow_speed", 1, 0, 1024, "Arrow Speed", L2DamageTracker.PERCENTAGE);
	public static final RegistryEntry<WrappedAttribute> ARROW_KNOCK = L2DamageTracker.regWrapped(CelestialCore.REGISTRATE,
			"arrow_knock", 0, 0, 64, "Arrow Knock Back");


	public static void register() {

	}

}