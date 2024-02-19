package com.xiaoyue.celestial_core.utils;

import com.xiaoyue.celestial_core.register.CCAttributes;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.world.entity.player.Player;

public class AttributeUtils {

    public static double getNewCR(Player player) {
        return L2DamageTracker.CRIT_RATE.get().getWrappedValue(player);
    }

    public static double getNewCD(Player player) {
        return L2DamageTracker.CRIT_DMG.get().getWrappedValue(player);
    }

    public static double getAD(Player player) {
        return L2DamageTracker.BOW_STRENGTH.get().getWrappedValue(player);
    }

    public static double getAS(Player player) {
        return player.getAttributeValue(CCAttributes.ARROW_SPEED.get());
    }

    public static double getAK(Player player) {
        return player.getAttributeValue(CCAttributes.ARROW_KNOCK.get());
    }

    public static double getRP(Player player) {
        return player.getAttributeValue(CCAttributes.REPLY_POWER.get());
    }

    public static double getDR(Player player) {
        return player.getAttributeValue(CCAttributes.DAMAGE_REDUCTION.get());
    }

    public static double getNewDR(Player player) {
        return getDR(player) - 1;
    }
}
