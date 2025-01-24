package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.CelestialCore;

public class CelestialFlags {

    public static final String NETHER_STAGE = CelestialFlags.getFlag("nether_stage");
    public static final String ARROW_SPEED = CelestialFlags.getFlag("arrow_speed");
    public static final String ARROW_KNOCK = CelestialFlags.getFlag("arrow_knock");
    public static final String BLACK_FLAME = CelestialFlags.getFlag("black_flame");

    public static String getFlag(String path) {
        return CelestialCore.loc(path).toString();
    }
}
