package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ScheduleUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CCGeneralEventHandler {

    @SubscribeEvent
    public static void onUseTotem(LivingUseTotemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.getRandom().nextDouble() <= CCModConfig.COMMON.brokenTotemChance.get()) {
            player.addItem(CCItems.BROKEN_TOTEM.asStack());
        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) return;
        if (event.getEffect() instanceof CelestialEffect effect) {
            if (!effect.beRemove(instance, event.getEntity())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            ScheduleUtils.serverTick();
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.tickCount % 10 == 0) {
            if (EntityUtils.onBlackFlame(entity)) {
                int time = EntityUtils.getBlackFlameTime(entity);
                EntityUtils.setBlackFlameTime(entity, Math.max(0, time - 10));
                entity.hurt(CCDamageTypes.abyss(entity.level()), entity.getMaxHealth() * 0.01f);
            }
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
        if (event.getEntity() instanceof AbstractArrow arrow && arrow.getOwner() instanceof Player player) {
            double as = player.getAttributeValue(CCAttributes.ARROW_SPEED.get());
            double ak = player.getAttributeValue(CCAttributes.ARROW_KNOCK.get());
            if (!arrow.getTags().contains(CelestialFlags.ARROW_SPEED)) {
                arrow.setDeltaMovement(arrow.getDeltaMovement().scale(as));
                arrow.addTag(CelestialFlags.ARROW_SPEED);
            }
            if (!arrow.getTags().contains(CelestialFlags.ARROW_KNOCK)) {
                arrow.setKnockback(arrow.getKnockback() + (int) ak);
                arrow.addTag(CelestialFlags.ARROW_KNOCK);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTeleport(EntityTeleportEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity le && le.hasEffect(CCEffects.ROTTEN_CURSE.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        if (entity.hasEffect(CCEffects.UNYIELDING.get())) {
            event.setCanceled(true);
            entity.setHealth(1f);
        }
        if (attacker instanceof Player player) {
            var data = PlayerFlagData.HOLDER.get(player);
            if (entity instanceof WitherBoss) {
                if (!data.hasFlag(CelestialFlags.NETHER_STAGE)) {
                    data.addFlag(CelestialFlags.NETHER_STAGE);
                }
            }
        }
    }
}
