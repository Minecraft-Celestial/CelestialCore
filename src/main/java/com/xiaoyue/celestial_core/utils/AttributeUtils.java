package com.xiaoyue.celestial_core.utils;

import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.entity.player.Player;

public class AttributeUtils {

    public static double getCR(Player player) {
        return player.getAttributeValue(CAttributes.CRIT_RATE.get());
    }

    public static double getNewCR(Player player) {
        return getCR(player) - 0.95;
    }

    public static double getCD(Player player) {
        return player.getAttributeValue(CAttributes.CRIT_DAMAGE.get());
    }

    public static double getNewCD(Player player) {
        return getCD(player) + 0.5;
    }

    public static double getAD(Player player) {
        return player.getAttributeValue(CAttributes.ARROW_DAMAGE.get());
    }

    public static double getAS(Player player) {
        return player.getAttributeValue(CAttributes.ARROW_SPEED.get());
    }

    public static double getAK(Player player) {
        return player.getAttributeValue(CAttributes.ARROW_KNOCK.get());
    }

    public static double getRP(Player player) {
        return player.getAttributeValue(CAttributes.REPLY_POWER.get());
    }

    public static double getDR(Player player) {
        return player.getAttributeValue(CAttributes.DAMAGE_REDUCTION.get());
    }

    public static double getNewDR(Player player) {
        return getDR(player) - 1;
    }
}
