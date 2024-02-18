package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;

public class LevelUtils {

    public static boolean isServerLevel(Level level) {
        return !level.isClientSide();
    }

    public static boolean sourceIsFire(DamageSource source) {
       return source.is(DamageTypes.ON_FIRE) ||
                source.is(DamageTypes.IN_FIRE) ||
                source.is(DamageTypes.LAVA);
    }

    public static boolean sourceIsPhysics(DamageSource source) {
        return source.is(DamageTypes.ARROW) ||
                source.is(DamageTypes.GENERIC);
    }
}
