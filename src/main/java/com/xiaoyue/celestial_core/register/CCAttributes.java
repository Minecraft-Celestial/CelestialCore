package com.xiaoyue.celestial_core.register;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

public class CCAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    public static final RegistryObject<Attribute> REPLY_POWER = ATTRIBUTES.register("reply_effect",
            () -> new RangedAttribute("attribute.celestial_core.reply_power", 0, 0, 64).setSyncable(true));

    public static final RegistryObject<Attribute> ARROW_SPEED = ATTRIBUTES.register("arrow_speed",
            () -> new RangedAttribute("attribute.celestial_core.arrow_speed", 0, 0, 1024).setSyncable(true));

    public static final RegistryObject<Attribute> ARROW_KNOCK = ATTRIBUTES.register("arrow_knock",
            () -> new RangedAttribute("attribute.celestial_core.arrow_knock", 0, 0, 64).setSyncable(true));

}