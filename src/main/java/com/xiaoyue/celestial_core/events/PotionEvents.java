package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.event.LHurtAfterArmorEvent;
import com.xiaoyue.celestial_core.register.COEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PotionEvents {

	@SubscribeEvent
	public static void onLivingHurtAfterArmor(LHurtAfterArmorEvent event) {
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker instanceof LivingEntity livingAttacker) {
			if (livingAttacker.hasEffect(COEffects.VIOLENT.get())) {
				event.setCanceled(true);
				event.setOriginalDamage();
			}
		}
	}

	@SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attacker = event.getSource().getEntity();
		Entity directEntity = event.getSource().getDirectEntity();

		if (entity.hasEffect(COEffects.SOUL_SHATTER.get())) {
			int amplifier = entity.getEffect(COEffects.SOUL_SHATTER.get()).getAmplifier();
			event.setAmount(event.getAmount() * (1 + (amplifier * 0.2f)));
		}

		if (directEntity instanceof LivingEntity living) {
			if (living.hasEffect(COEffects.BLADE_MODIFIER.get())) {
				int amplifier = living.getEffect(COEffects.BLADE_MODIFIER.get()).getAmplifier();
				event.setAmount(event.getAmount() + amplifier * 4);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingTeleport(EntityTeleportEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof LivingEntity living) {
			if (living.hasEffect(COEffects.ROTTEN_CURSE.get())) {
				event.setCanceled(true);
			}
		}
	}
}