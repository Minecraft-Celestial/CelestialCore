package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

public class CCAttributes {

	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

	public static final RegistryEntry<Attribute> REPLY_POWER = reg("regen_rate", 1, 64, "Regeneration Rate");
	public static final RegistryEntry<Attribute> ARROW_SPEED = reg("arrow_speed", 1, 1024, "Arrow Speed");
	public static final RegistryEntry<Attribute> ARROW_KNOCK = reg("arrow_knock", 0, 64, "Arrow Knock Back");

	private static RegistryEntry<Attribute> reg(String id, double def, double max, String name) {
		CelestialCore.REGISTRATE.addRawLang("attribute." + MODID + "." + id, name);
		return CelestialCore.REGISTRATE.simple(id, ForgeRegistries.ATTRIBUTES.getRegistryKey(), () ->
				new RangedAttribute("attribute.name." + id, def, 0.0, max).setSyncable(true));
	}

}