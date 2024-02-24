package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2library.capability.player.PlayerCapabilityHolder;
import dev.xkmc.l2library.capability.player.PlayerCapabilityNetworkHandler;
import dev.xkmc.l2library.capability.player.PlayerCapabilityTemplate;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.TreeSet;

@SerialClass
public class PlayerFlagData extends PlayerCapabilityTemplate<PlayerFlagData> {

	public static final Capability<PlayerFlagData> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static final PlayerCapabilityHolder<PlayerFlagData> HOLDER = new PlayerCapabilityHolder<>(
			CelestialCore.loc("flags"), CAPABILITY, PlayerFlagData.class, PlayerFlagData::new,
			PlayerCapabilityNetworkHandler::new);

	@SerialClass.SerialField
	private final TreeSet<String> flags = new TreeSet<>();

	public void addFlag(String str) {
		flags.add(str);
		if (player instanceof ServerPlayer sp)
			HOLDER.network.toClientSyncAll(sp);
	}

	public boolean hasFlag(String flag){
		return flags.contains(flag);
	}

}
