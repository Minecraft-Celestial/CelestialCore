package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.network.EntityIntDataSyncPacket;
import dev.xkmc.l2library.capability.entity.GeneralCapabilityHolder;
import dev.xkmc.l2library.capability.entity.GeneralCapabilityTemplate;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.HashMap;
import java.util.Map;

@SerialClass
public class EntityIntData extends GeneralCapabilityTemplate<LivingEntity, EntityIntData> {

    public static void syncData(LivingEntity entity, String flag, int data) {
        if (entity.level().isClientSide()) return;
        CelestialCore.HANDLER.toTrackingPlayers(new EntityIntDataSyncPacket(entity.getId(), flag, data), entity);
    }

    public static void addData(LivingEntity entity, String key, int value) {
        if (!entity.isDeadOrDying()) {
            entity.getCapability(CAPABILITY).resolve().ifPresent(e -> e.addData(key, value));
            syncData(entity, key, value);
        }
    }

    public static int getData(LivingEntity entity, String key) {
        if (!entity.isDeadOrDying()) {
            return entity.getCapability(CAPABILITY).resolve().map(e -> e.getData(key)).orElse(0);
        }
        return 0;
    }

    public static void removeData(LivingEntity entity, String key) {
        entity.getCapability(CAPABILITY).resolve().ifPresent(e -> e.removeData(key));
        syncData(entity, key, 0);
    }

    public static final Capability<EntityIntData> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static final GeneralCapabilityHolder<LivingEntity, EntityIntData> HOLDER =
            new GeneralCapabilityHolder<>(CelestialCore.loc("fire_data"), CAPABILITY, EntityIntData.class,
                    EntityIntData::new, LivingEntity.class, LivingEntity::isAlive);

    @SerialClass.SerialField
    private final Map<String, Integer> data = new HashMap<>();

    public void addData(String key, int value) {
        data.put(key, value);
    }

    public int getData(String key) {
        return data.get(key) == null ? 0 : data.get(key);
    }

    public void removeData(String key) {
        data.remove(key);
    }

    public static void register() {

    }

}
