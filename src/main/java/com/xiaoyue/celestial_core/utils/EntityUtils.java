package com.xiaoyue.celestial_core.utils;

import dev.xkmc.l2damagetracker.contents.materials.api.IMatVanillaType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class EntityUtils {

	public static void spawnThunder(Level level, BlockPos pos) {
		LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
		if (lightningBolt != null) {
			lightningBolt.moveTo(pos.getCenter());
			level.addFreshEntity(lightningBolt);
		}
	}

	public static void spawnThunder(Entity target) {
		spawnThunder(target.level(), target.getOnPos());
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

	public static <I extends Item> int getSeriesArmorAmount(LivingEntity entity, I item) {
		int amount = 0;
		for (ItemStack armorItem : entity.getArmorSlots()) {
			if (armorItem.is(item))
				amount++;
		}
		return amount;
	}

	public static int getSeriesArmorAmount(LivingEntity entity, Item... items) {
		int amount = 0;
		for (ItemStack slot : entity.getArmorSlots()) {
			for (Item item : items) {
				if (slot.is(item)) {
					amount++;
				}
			}
		}
		return amount;
	}

	public static int getSeriesArmorAmount(LivingEntity entity, IMatVanillaType type) {
		return getSeriesArmorAmount(entity, type.getArmor(EquipmentSlot.HEAD), type.getArmor(EquipmentSlot.CHEST),
				type.getArmor(EquipmentSlot.LEGS), type.getArmor(EquipmentSlot.FEET));
	}

	public static void removeEffects(LivingEntity entity, MobEffect... effects) {
		for (MobEffect effect : effects) {
			if (entity.hasEffect(effect)) {
				entity.removeEffect(effect);
			}
		}
	}

	public static ItemEntity getItemEntity(ItemStack stack, Level level, BlockPos pos) {
		ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack);
		itemEntity.setPickUpDelay(10);
		return itemEntity;
	}

	public static ItemEntity getItemEntity(ItemStack stack, Entity target) {
		ItemEntity itemEntity = new ItemEntity(target.level(), target.getX(), target.getY(), target.getZ(), stack);
		itemEntity.setPickUpDelay(10);
		return itemEntity;
	}

	public static boolean isFullCharged(Player player) {
		return player.getAttackStrengthScale(0.5f) > 0.9f;
	}

	public static DamageSource getSource(DamageSource source, LivingEntity attacker) {
		return new DamageSource(source.typeHolder(), attacker);
	}

	public static DamageSource getSource(DamageSource source, Entity direct, LivingEntity attacker) {
		return new DamageSource(source.typeHolder(), direct, attacker);
	}

	public static float getPerMissHp(LivingEntity entity) {
		return 1 - entity.getHealth() / entity.getMaxHealth();
	}

	public static void setPlayerXpLevel(Player player, int level) {
		player.experienceLevel = level;
	}

	public static boolean isEndEntity(LivingEntity entity) {
		return entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Shulker
				|| entity instanceof Endermite;
	}

	public static boolean hasArmor(LivingEntity le) {
		return !le.getItemBySlot(EquipmentSlot.HEAD).isEmpty() || !le.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
				|| !le.getItemBySlot(EquipmentSlot.LEGS).isEmpty() || !le.getItemBySlot(EquipmentSlot.FEET).isEmpty();
	}

	public static boolean isEmptyInHand(LivingEntity entity) {
		return entity.getMainHandItem().isEmpty() && entity.getOffhandItem().isEmpty();
	}

	public static int getEffectLevel(LivingEntity entity, MobEffect effect) {
		MobEffectInstance instance = entity.getEffect(effect);
		return instance == null ? -1 : instance.getAmplifier();
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

	public static void addEct(LivingEntity entity, MobEffect effect, int time) {
		addEct(entity, effect, time, 0);
	}
}