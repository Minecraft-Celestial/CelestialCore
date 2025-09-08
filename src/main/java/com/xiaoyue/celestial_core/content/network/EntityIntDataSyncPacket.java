package com.xiaoyue.celestial_core.content.network;

import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import dev.xkmc.l2library.util.Proxy;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class EntityIntDataSyncPacket extends SerialPacketBase {

    @SerialClass.SerialField
    public int entityId;

    @SerialClass.SerialField
    public String flag;

    @SerialClass.SerialField
    public int data;

    @Deprecated
    public EntityIntDataSyncPacket() {
    }

    public EntityIntDataSyncPacket(int entityId, String flag, int data) {
        this.entityId = entityId;
        this.flag = flag;
        this.data = data;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ClientLevel level = Proxy.getClientWorld();
        if (level == null) return;
        Entity e = level.getEntity(entityId);
        if (!(e instanceof LivingEntity le)) return;
        le.getCapability(EntityIntData.CAPABILITY).ifPresent(data -> data.addData(flag, this.data));
        context.setPacketHandled(true);
    }
}
