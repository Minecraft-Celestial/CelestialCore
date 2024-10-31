package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.ScheduleUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CCGeneralEventHandler {

	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			ScheduleUtils.serverTick();
		}
	}

	@SubscribeEvent
	public static void onLivingHeal(LivingHealEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player player) {
			double re = player.getAttributeValue(CCAttributes.REPLY_POWER.get());
			event.setAmount((float) (event.getAmount() * re));
		}
	}

	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof AbstractArrow arrow) {
			if (arrow.getOwner() instanceof Player player) {
				double as = player.getAttributeValue(CCAttributes.ARROW_SPEED.get());
				double ak = player.getAttributeValue(CCAttributes.ARROW_KNOCK.get());
				if (!arrow.getTags().contains("arrow_speed")) {
					arrow.setDeltaMovement(arrow.getDeltaMovement().scale(as));
					arrow.addTag("arrow_speed");
				}
				arrow.setKnockback(arrow.getKnockback() + (int) ak);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingTeleport(EntityTeleportEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.hasEffect(CCEffects.ROTTEN_CURSE.get())) {
				event.setCanceled(true);
			}
		}
	}

	public static String NETHER_STAGE = "nether_stage";

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onLivingDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker instanceof Player player) {
			var data = PlayerFlagData.HOLDER.get(player);
			if (entity instanceof WitherBoss) {
				if (!data.hasFlag(NETHER_STAGE)) {
					data.addFlag(NETHER_STAGE);
				}
			}
		}
	}
}
