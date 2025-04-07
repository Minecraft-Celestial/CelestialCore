package com.xiaoyue.celestial_core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;
import java.util.UUID;

public class CCUtils {

    public static final UUID BYPASS_ARMOR_UUID = UUID.fromString("CB3F55D3-2213-B01A-A497-9C23A33DB5CF");
    public static final UUID BYPASS_TOUGHNESS_UUID = UUID.fromString("CB3F55D3-2214-B024-A497-9C23A33DB5CF");

    public static Vec3 getCenterOf(Vec3i pos) {
        if (pos.equals(Vec3i.ZERO)) {
            return new Vec3(.5, .5, .5);
        }
        return Vec3.atLowerCornerOf(pos).add(.5f, .5f, .5f);
    }

    public static float getMoonFactor(Level level) {
        int moonPhase = ((int) (level.getDayTime() / 24000 % 8));
        float moonPhaseFactor;
        if (moonPhase == 0) {
            moonPhaseFactor = 1.0f;
        } else if (moonPhase == 1 || moonPhase == 7) {
            moonPhaseFactor = 0.75f;
        } else if (moonPhase == 2 || moonPhase == 6) {
            moonPhaseFactor = 0.5f;
        } else if (moonPhase == 3 || moonPhase == 5) {
            moonPhaseFactor = 0.25f;
        } else {
            moonPhaseFactor = 0.0F;
        }
        return moonPhaseFactor;
    }

    public static boolean isFullMoon(Level level) {
        return getMoonFactor(level) == 1f;
    }

    public static void addFlySpeed(LivingEntity entity, float speed) {
        Vec3 look = entity.getLookAngle();
        Vec3 motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.add(
                look.x * 0.1D + (look.x * speed - motion.x) * 0.5D,
                look.y * 0.1D + (look.y * speed - motion.y) * 0.5D,
                look.z * 0.1D + (look.z * speed - motion.z) * 0.5D
        ));
    }

    public static int getLight(Level level, BlockPos pos) {
        if (level instanceof ServerLevel sl) {
            return sl.getMaxLocalRawBrightness(pos);
        }
        return level.getMaxLocalRawBrightness(pos, getSkyDarken(level));
    }

    public static int getSkyDarken(Level level) {
        double d0 = 1 - (level.getRainLevel(1) * 5) / 16;
        double d1 = 1 - (level.getThunderLevel(1) * 5) / 16;
        double d2 = 0.5 + 2 * Mth.clamp(Mth.cos(level.getTimeOfDay(1) * ((float) Math.PI * 2)), -0.25, 0.25);
        return (int) ((1 - d2 * d0 * d1) * 11);
    }

    public static void attractItem(LivingEntity entity, AABB range, float speed) {
        attractEntity(entity, ItemEntity.class, range, speed);
    }

    public static <E extends Entity> void attractEntity(LivingEntity entity, Class<E> targets, AABB range, float speed) {
        List<E> list = entity.level().getEntitiesOfClass(targets, range);
        for (E target : list) {
            if (target.isRemoved()) {
                continue;
            }
            Vec3 sub = entity.position().subtract(target.getX(), target.getY(), target.getZ()).normalize().scale(speed);
            if (target.isNoGravity()) {
                sub = sub.add(0, 0.04f, 0);
            }
            target.setDeltaMovement(sub);
        }
    }

    public static <E extends Event> E fireEvent(E event) {
        MinecraftForge.EVENT_BUS.post(event);
        return event;
    }
}
