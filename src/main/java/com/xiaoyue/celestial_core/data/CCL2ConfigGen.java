package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCMaterials;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2damagetracker.init.data.ArmorEffectConfig;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2tabs.init.L2Tabs;
import dev.xkmc.l2tabs.init.data.AttributeDisplayConfig;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffects;

public class CCL2ConfigGen extends ConfigDataProvider {

    public CCL2ConfigGen(DataGenerator generator) {
        super(generator, "Celestial Core L2 Config Gen");
    }

    @Override
    public void add(Collector collector) {
        collector.add(L2Tabs.ATTRIBUTE_ENTRY, CelestialCore.loc(CelestialCore.MODID),
                new AttributeDisplayConfig()
                        .add(CCAttributes.REPLY_POWER.get(), true, 22000, 0)
                        .add(CCAttributes.ARROW_SPEED.get(), true, 22300, 0)
                        .add(CCAttributes.ARROW_KNOCK.get(), false, 22500, 0)
                        .add(CCAttributes.ARMOR_PENETRATION.get(), true, 22700, 0)
                        .add(CCAttributes.TOUGHNESS_PENETRATION.get(), true, 22900, 0)
        );
        collector.add(L2DamageTracker.ARMOR, CelestialCore.loc(CelestialCore.MODID),
                new ArmorEffectConfig()
                        .add(CCMaterials.OCEAN.armorPrefix(), MobEffects.DIG_SLOWDOWN)
                        .add(CCMaterials.SAKURA.armorPrefix(), MobEffects.POISON)
                        .add(CCMaterials.VIRTUAL_GOLD.armorPrefix(), MobEffects.WITHER)
        );
    }

}
