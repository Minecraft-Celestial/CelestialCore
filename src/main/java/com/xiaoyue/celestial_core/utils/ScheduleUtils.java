package com.xiaoyue.celestial_core.utils;

import com.xiaoyue.celestial_core.CelestialCore;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ScheduleUtils {

    private static final Map<ResourceLocation, ScheduleUtils> MAP = new HashMap<>();

    public int runTick;
    public int runCount;
    public int needRunTick;
    public int needRunCount;
    public final Runnable action;
    public boolean isEnd;

    public ScheduleUtils(int singleRunTick, Runnable action) {
        this.runTick = 0;
        this.runCount = 0;
        this.needRunTick = singleRunTick;
        this.needRunCount = 1;
        this.action = action;
    }

    public ScheduleUtils(int needRunTick, int needRunCount, Runnable action) {
        this.runTick = 0;
        this.runCount = 0;
        this.needRunTick = needRunTick;
        this.needRunCount = needRunCount;
        this.action = action;
    }

    @Nullable
    public static ScheduleUtils getUtils(ResourceLocation id) {
        if (!MAP.isEmpty()) return MAP.get(id);
        return null;
    }

    public static void serverTick() {
        if (MAP.isEmpty()) return;
        MAP.forEach((key, utils) -> {
            if (utils.runTick >= utils.needRunTick) {
                utils.action.run();
                utils.runCount++;
                if (utils.runCount >= utils.needRunCount) {
                    utils.isEnd = true;
                    CelestialCore.LOGGER.info("{}: schedule end", key.toString());
                } else {
                    utils.runTick = 0;
                    utils.isEnd = false;
                    CelestialCore.LOGGER.info("{}: {} time schedule completion", key.toString(), utils.runCount);
                }
            } else {
                utils.runTick++;
                utils.isEnd = false;
            }
        });
        MAP.entrySet().removeIf(entry -> entry.getValue().isEnd);
    }

    public static void schedule(ResourceLocation id, int needRunTick, Runnable action) {
        ScheduleUtils utils = new ScheduleUtils(needRunTick, action);
        MAP.put(id, utils);
    }

    public static void schedule(ResourceLocation id, int needRunTick, int needRunCount, Runnable action) {
        ScheduleUtils utils = new ScheduleUtils(needRunTick, needRunCount, action);
        MAP.put(id, utils);
    }
}
