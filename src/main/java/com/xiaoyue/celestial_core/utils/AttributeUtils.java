package com.xiaoyue.celestial_core.utils;

import com.xiaoyue.celestial_core.register.CCAttributes;
import net.minecraft.world.entity.player.Player;

public class AttributeUtils {

    public static double getCR(Player player) {
        return player.getAttributeValue(CCAttributes.CRIT_RATE.get());
    }

    public static double getNewCR(Player player) {
        return getCR(player) - 0.95;
    }

    public static double getCD(Player player) {
        return player.getAttributeValue(CCAttributes.CRIT_DAMAGE.get());
    }

    public static double getNewCD(Player player) {
        return getCD(player) + 0.5;
    }

    public static double getAD(Player player) {
        return player.getAttributeValue(CCAttributes.ARROW_DAMAGE.get());
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
