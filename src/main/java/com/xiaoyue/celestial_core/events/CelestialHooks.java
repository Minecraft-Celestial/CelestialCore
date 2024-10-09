package com.xiaoyue.celestial_core.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class CelestialHooks extends Event {

	public static <T extends CelestialHooks> T fire(T event) {
		MinecraftForge.EVENT_BUS.post(event);
		return event;
	}

	public static <T extends CelestialHooks> void post(T event) {
		MinecraftForge.EVENT_BUS.post(event);
	}

}
