package com.xiaoyue.celestial_core.utils;

import net.minecraftforge.fml.ModList;

public class ModListUtils {

    public static boolean isLoader(String name) {
        return ModList.get().isLoaded(name);
    }
}
