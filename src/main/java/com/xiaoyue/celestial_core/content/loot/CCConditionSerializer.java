package com.xiaoyue.celestial_core.content.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import dev.xkmc.l2serial.serialization.codec.JsonCodec;
import net.minecraft.world.level.storage.loot.Serializer;

import java.util.Objects;

public record CCConditionSerializer<T>(Class<T> cls) implements Serializer<T> {

	public CCConditionSerializer(Class<T> cls) {
		this.cls = cls;
	}

	public void serialize(JsonObject json, T conditions, JsonSerializationContext ctx) {
		JsonCodec.toJsonObject(conditions, json);
	}

	public T deserialize(JsonObject json, JsonDeserializationContext ctx) {
		return Objects.requireNonNull(JsonCodec.from(json, this.cls, null));
	}

}
