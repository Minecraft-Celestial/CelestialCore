package com.xiaoyue.celestial_core.utils;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class EntityUtils {

	public static void spawnThunder(Entity target) {
		LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(target.level());
		if (lightningBolt != null) {
			lightningBolt.moveTo(target.getOnPos().getCenter());
			target.level().addFreshEntity(lightningBolt);
		}
	}

	public static AABB getAABB(LivingEntity center, float radius, float height) {
		return center.getBoundingBox().inflate(radius, height, radius);
	}

	public static List<LivingEntity> getExceptForCentralEntity(LivingEntity center, float radius, float height, Predicate<LivingEntity> predicate) {
		List<LivingEntity> entities = center.level().getEntitiesOfClass(LivingEntity.class,
				EntityUtils.getAABB(center, radius, height), predicate);
		entities.remove(center);
		return entities;
	}

	public static List<LivingEntity> getExceptForCentralEntity(LivingEntity center, float radius, float height) {
		List<LivingEntity> entities = center.level().getEntitiesOfClass(LivingEntity.class,
				EntityUtils.getAABB(center, radius, height));
		entities.remove(center);
		return entities;
	}

	public static List<LivingEntity> getDelimitedMonster(LivingEntity center, int range) {
		return center.level().getEntitiesOfClass(LivingEntity.class,
				center.getBoundingBox().inflate(range), entities -> entities instanceof Enemy);
	}

	public static boolean isLookingBehindTarget(LivingEntity target, @Nullable Vec3 attackerLocation) {
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
		return entity instanceof EnderMan ||
				entity instanceof EnderDragon ||
				entity instanceof Shulker ||
				entity instanceof Endermite;
	}

	public static boolean hasArmor(LivingEntity le) {
		return !le.getItemBySlot(EquipmentSlot.HEAD).isEmpty() ||
				!le.getItemBySlot(EquipmentSlot.CHEST).isEmpty() ||
				!le.getItemBySlot(EquipmentSlot.LEGS).isEmpty() ||
				!le.getItemBySlot(EquipmentSlot.FEET).isEmpty();
	}

	public static int getEffectCount(LivingEntity entity, MobEffectCategory category) {
		return (int) entity.getActiveEffects().stream().filter(e -> e.getEffect().getCategory() == category).count();
	}

	public static int getHarmfulEffect(LivingEntity entity) {
		return getEffectCount(entity, MobEffectCategory.HARMFUL);
	}

	public static int getBeneficialEffect(LivingEntity entity) {
		return getEffectCount(entity, MobEffectCategory.BENEFICIAL);
	}

	public static void addEct(LivingEntity entity, MobEffect effect, int time, int level) {
		entity.addEffect(new MobEffectInstance(effect, time, level, false, false, true));
	}
}