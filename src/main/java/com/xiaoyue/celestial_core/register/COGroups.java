package com.xiaoyue.celestial_core.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_core.celestial_core.MODID;
import static com.xiaoyue.celestial_core.register.COItems.*;

public class COGroups {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> CORE = CREATIVE_TAB.register("core", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemGroup.celestial_core.core"))
            .icon(() -> FIRE_ESSENCE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EARTH_CORE.get());
                output.accept(VOID_ESSENCE.get());
                output.accept(DEATH_ESSENCE.get());
                output.accept(FIRE_ESSENCE.get());
                output.accept(OCEAN_ESSENCE.get());
                output.accept(LIGHT_FRAGMENT.get());
                output.accept(MIDNIGHT_FRAGMENT.get());
                output.accept(TREASURE_FRAGMENT.get());
                output.accept(PURE_NETHER_STAR.get());
                output.accept(SHULKER_SCRAP.get());
                output.accept(SOARING_WINGS.get());
                output.accept(HEART_FRAGMENT.get());
                output.accept(THE_END_DUST.get());
                output.accept(WARDEN_SCLERITE.get());
                output.accept(NEBULA_CUBE.get());
                output.accept(ICE_FRAGMENT.get());
                output.accept(EQUIPMENT_FRAGMENT.get());
                output.accept(CURSE_ESSENCE.get());
            }).build());
}
