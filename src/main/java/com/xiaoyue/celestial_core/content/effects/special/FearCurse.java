package com.xiaoyue.celestial_core.content.effects.special;

import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FearCurse extends MobEffect {
	public FearCurse() {
		super(MobEffectCategory.HARMFUL, 0xff9900);
	}

	private static final List<SourceTask> list = new ArrayList<>();

	public static void addEct(LivingEntity attacker, LivingEntity entity, int time, int level) {
		EntityUtils.addEct(entity, CCEffects.FEAR_CURSE.get(), time, level);
		list.add(new SourceTask(entity.getUUID(), attacker));
	}

	public static void addEct(LivingEntity attacker, LivingEntity entity, int time) {
		EntityUtils.addEct(entity, CCEffects.FEAR_CURSE.get(), time);
		list.add(new SourceTask(entity.getUUID(), attacker));
	}

	@Nullable
	public static LivingEntity getSource(LivingEntity entity) {
		for (SourceTask task : list) {
			if (task.uuid() == entity.getUUID()) {
				return task.source();
			}
		}
		return null;
	}

	public record SourceTask(UUID uuid, LivingEntity source) {
	}
}
