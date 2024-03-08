package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCAttributes;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2tabs.init.L2Tabs;
import dev.xkmc.l2tabs.init.data.AttributeDisplayConfig;
import net.minecraft.data.DataGenerator;

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
		);
	}

}
