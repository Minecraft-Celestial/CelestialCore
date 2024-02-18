package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.utils.AttributeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeEvents {

	public static boolean isCelestialCrit(double attr) {
		return Math.random() < attr;
	}

	@SubscribeEvent
	public static void onCriticalHit(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attacker = event.getSource().getEntity();
		if (attacker instanceof Player player) {
			double cr = AttributeUtils.getNewCR(player);
			double cd = AttributeUtils.getNewCD(player);
			if (isCelestialCrit(cr)) {
				event.setAmount((float) (event.getAmount() * cd));
				Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, ParticleTypes.CRIT);
				player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
						SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0f, 1.0f);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			double dr = AttributeUtils.getNewDR(player);
			event.setAmount((float) (event.getAmount() * (1 - dr)));
		}
	}

	@SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            double re = AttributeUtils.getRP(player);
            event.setAmount((float) (event.getAmount() * re));
        }
    }

    @SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof AbstractArrow arrow) {
			if (arrow.getOwner() instanceof Player player) {
				double ad = AttributeUtils.getAD(player);
				double as = AttributeUtils.getAS(player);
				double ak = AttributeUtils.getAK(player);
				if (!arrow.getPersistentData().getBoolean("arrow_speed")) {
					arrow.setDeltaMovement(arrow.getDeltaMovement().scale(as));
					arrow.getPersistentData().putBoolean("arrow_speed", true);
				}
				arrow.setBaseDamage((float) (arrow.getBaseDamage() * ad));
				arrow.setKnockback((int) (arrow.getKnockback() * ak));
			}
		}
	}
}