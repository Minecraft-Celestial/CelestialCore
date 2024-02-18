package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.register.COItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ModListUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MaterialEvents {

    public static String NETHER_STAGE = "nether_stage";

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getOriginal().getPersistentData().getBoolean(NETHER_STAGE)) {
            event.getEntity().getPersistentData().putBoolean(NETHER_STAGE, true);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();
        if (attacker instanceof Player player) {
            CompoundTag data = player.getPersistentData();
            if (data.getBoolean(NETHER_STAGE)) {

                if (entity instanceof Husk husk) {
                    if (0.05 > Math.random()) {
                        husk.spawnAtLocation(COItems.LIGHT_FRAGMENT.get());
                    }
                }

                if (entity instanceof Stray stray) {
                    if (0.05 > Math.random()) {
                        stray.spawnAtLocation(COItems.MIDNIGHT_FRAGMENT.get());
                    }
                }
            }

            if (entity instanceof WitherBoss witherBoss) {
                if (!data.getBoolean(NETHER_STAGE)) {
                    data.putBoolean(NETHER_STAGE, true);
                }

                if (EntityUtils.getBeneficialEffect(player) > 12) {
                    witherBoss.spawnAtLocation(COItems.PURE_NETHER_STAR.get());
                }
            }

            if (entity instanceof Blaze blaze) {
                if (0.08 > Math.random()) {
                    blaze.spawnAtLocation(COItems.FIRE_ESSENCE.get());
                }
            }

            if (entity instanceof Guardian guardian) {
                if (0.08 > Math.random()) {
                    guardian.spawnAtLocation(COItems.OCEAN_ESSENCE.get());
                }
            }

            if (entity instanceof Phantom phantom) {
                if (620 < phantom.getY()) {
                    phantom.spawnAtLocation(COItems.SOARING_WINGS.get());
                }
            }

            if (entity instanceof Warden warden) {
                if (0.5 > Math.random()) {
                    warden.spawnAtLocation(COItems.WARDEN_SCLERITE.get());
                }
            }

            if (ModListUtils.isLoader("celestial_artifacts")) {
                if (CurioUtiks.isCsOn(player)) {
                    if (entity instanceof Monster monster) {
                        if (0.02 > Math.random()) {
                            monster.spawnAtLocation(COItems.THE_END_DUST.get());
                        }
                    }
                }
            }
        }

        if (entity instanceof Shulker shulker) {
            if (source.is(DamageTypeTags.IS_EXPLOSION)) {
                if (0.5 > Math.random()) {
                    shulker.spawnAtLocation(COItems.SHULKER_SCRAP.get());
                }
            }
        }

        if (entity.getMaxHealth() > 100) {
            if (source.is(DamageTypes.WITHER)) {
                if (0.6 > Math.random()) {
                    entity.spawnAtLocation(COItems.DEATH_ESSENCE.get());
                }
            }
        }

        if (entity instanceof Pillager pillager) {
            if (attacker instanceof Creeper creeper) {
                if (creeper.isPowered()) {
                    pillager.spawnAtLocation(COItems.HEART_FRAGMENT.get());
                }
            }
        }
    }
}
