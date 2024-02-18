package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class EntityUtils {

    public static void spawnThunder(Entity target) {
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(target.level());
        if (lightningBolt != null) {
            lightningBolt.moveTo(target.getOnPos().getCenter());
            target.level().addFreshEntity(lightningBolt);
        }
    }

    public static AABB getAABB(LivingEntity center, float radius, float height) {
        return new AABB(center.blockPosition().getX() - radius, center.blockPosition().getY() - height, center.blockPosition().getZ() - radius,
                center.blockPosition().getX() + radius, center.blockPosition().getY() + height, center.blockPosition().getZ() + radius);
    }

    public static List<LivingEntity> getExceptForCentralEntity(LivingEntity center, float radius, float height, Predicate<LivingEntity> predicate) {
        List<LivingEntity> entities = center.level().getEntitiesOfClass(LivingEntity.class, EntityUtils.getAABB(center, radius, height), predicate);
        entities.remove(center);
        return entities;
    }

    public static List<LivingEntity> getExceptForCentralEntity(LivingEntity center, float radius, float height) {
        List<LivingEntity> entities = center.level().getEntitiesOfClass(LivingEntity.class, EntityUtils.getAABB(center, radius, height));
        entities.remove(center);
        return entities;
    }

    public static List<LivingEntity> getDelimitedMonster(LivingEntity center, int range) {
        List<LivingEntity> monster = center.level().getEntitiesOfClass(LivingEntity.class, center.getBoundingBox().inflate(range), entities -> entities instanceof Monster);
        return monster;
    }

    public static boolean isLookingBehindTarget(LivingEntity target, Vec3 attackerLocation) {
        if (attackerLocation != null) {
            Vec3 lookingVector = target.getViewVector(1.0F);
            Vec3 attackAngleVector = attackerLocation.subtract(target.position()).normalize();
            attackAngleVector = new Vec3(attackAngleVector.x, 0.0D, attackAngleVector.z);
            return attackAngleVector.dot(lookingVector) < -0.5D;
        }
        return false;
    }

    public static void setPlayerXpLevel(Player player, int level) {
        player.experienceLevel = level;
    }

    public static boolean isEndEntity(LivingEntity entity) {
        return entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Shulker || entity instanceof Endermite;
    }

    public static boolean hasArmor(LivingEntity livingEntity) {
        return !livingEntity.getItemBySlot(EquipmentSlot.HEAD).isEmpty() || !livingEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() || !livingEntity.getItemBySlot(EquipmentSlot.LEGS).isEmpty() || !livingEntity.getItemBySlot(EquipmentSlot.FEET).isEmpty();
    }

    public static int getHarmfulEffect(LivingEntity entity) {
        int he = 0;
        he += (int) entity.getActiveEffects().stream().filter(
            EffectInstance -> EffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).count();
        return he;
    }

    public static int getBeneficialEffect(LivingEntity entity) {
        int be = 0;
        be += (int) entity.getActiveEffects().stream().filter(
            EffectInstance -> EffectInstance.getEffect().getCategory() == MobEffectCategory.BENEFICIAL).count();
        return be;
    }

    public static void addEct(LivingEntity entity, MobEffect effect, int time, int level) {
        entity.addEffect(new MobEffectInstance(effect, time, level));
    }
}