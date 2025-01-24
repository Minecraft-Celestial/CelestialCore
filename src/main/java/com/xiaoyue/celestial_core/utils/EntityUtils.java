package com.xiaoyue.celestial_core.utils;

import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import dev.xkmc.l2damagetracker.contents.materials.api.IMatVanillaType;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class EntityUtils {

    public static void spawnThunder(Level level, BlockPos pos, boolean onlyShow) {
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
        if (lightningBolt != null) {
            lightningBolt.setVisualOnly(onlyShow);
            lightningBolt.moveTo(pos.getCenter());
            level.addFreshEntity(lightningBolt);
        }
    }

    public static void spawnThunder(Level level, BlockPos pos) {
        EntityUtils.spawnThunder(level, pos, false);
    }

    public static void spawnThunder(Entity target) {
        EntityUtils.spawnThunder(target.level(), target.getOnPos());
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

    public static boolean onBlackFlame(LivingEntity entity) {
        return EntityIntData.getData(entity, CelestialFlags.BLACK_FLAME) > 0;
    }

    public static int getBlackFlameTime(LivingEntity entity) {
        return EntityIntData.getData(entity, CelestialFlags.BLACK_FLAME);
    }

    public static void setBlackFlameTime(LivingEntity entity, int time) {
        EntityIntData.addData(entity, CelestialFlags.BLACK_FLAME, time);
    }

    public static void clearBlackFlame(LivingEntity entity) {
        EntityIntData.removeData(entity, CelestialFlags.BLACK_FLAME);
    }

    public static <I extends Item> int getSeriesArmorAmount(LivingEntity entity, I item) {
        int amount = 0;
        for (ItemStack armor : entity.getArmorSlots()) {
            if (armor.is(item)) amount++;
        }
        return amount;
    }

    public static int getSeriesArmorAmount(LivingEntity entity, Item... items) {
        int amount = 0;
        for (ItemStack armor : entity.getArmorSlots()) {
            amount += (int) Arrays.stream(items).filter(armor::is).count();
        }
        return amount;
    }

    public static int getSeriesArmorAmount(LivingEntity entity, IMatVanillaType type) {
        return getSeriesArmorAmount(entity, type.getArmor(EquipmentSlot.HEAD), type.getArmor(EquipmentSlot.CHEST),
                type.getArmor(EquipmentSlot.LEGS), type.getArmor(EquipmentSlot.FEET));
    }

    public static int getEquipArmorAmount(LivingEntity entity) {
        int amount = 0;
        for (ItemStack stack : entity.getArmorSlots()) {
            if (!stack.isEmpty()) amount++;
        }
        return amount;
    }

    public static void hurtByPlayerOrMob(LivingEntity target, @Nullable LivingEntity attacker, float damage) {
        if (attacker instanceof Player player) {
            target.hurt(player.damageSources().playerAttack(player), damage);
        } else if (attacker != null) {
            target.hurt(attacker.damageSources().mobAttack(attacker), damage);
        } else {
            target.hurt(target.damageSources().generic(), damage);
        }
    }

    public static boolean isFullCharged(Player player) {
        return player.getAttackStrengthScale(0.5f) > 0.9f;
    }

    public static float getPerMissHp(LivingEntity entity) {
        return 1 - entity.getHealth() / entity.getMaxHealth();
    }

    public static void setPlayerXpLevel(Player player, int level) {
        player.experienceLevel = level;
    }

    public static boolean isEndEntity(LivingEntity entity) {
        return entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Shulker || entity instanceof Endermite;
    }

    public static boolean nullArmor(LivingEntity entity) {
        return EntityUtils.getEquipArmorAmount(entity) == 0;
    }

    public static boolean hasArmor(LivingEntity entity) {
        return EntityUtils.getEquipArmorAmount(entity) > 0;
    }

    public static boolean isEmptyInHand(LivingEntity entity) {
        return entity.getMainHandItem().isEmpty() && entity.getOffhandItem().isEmpty();
    }

    public static int getEffectLevel(LivingEntity entity, MobEffect effect) {
        MobEffectInstance instance = entity.getEffect(effect);
        return instance == null ? -1 : instance.getAmplifier();
    }

    public static int getEffectCount(LivingEntity entity, MobEffectCategory category) {
        return (int) entity.getActiveEffects().stream().filter(e -> e.getEffect().getCategory().equals(category)).count();
    }

    public static int getHarmfulEffect(LivingEntity entity) {
        return EntityUtils.getEffectCount(entity, MobEffectCategory.HARMFUL);
    }

    public static int getBeneficialEffect(LivingEntity entity) {
        return EntityUtils.getEffectCount(entity, MobEffectCategory.BENEFICIAL);
    }

    public static void addEct(LivingEntity entity, MobEffect effect, int time, int level) {
        entity.addEffect(new MobEffectInstance(effect, time, level, false, false, true), entity);
    }

    public static void addEct(LivingEntity entity, MobEffect effect, int time) {
        entity.addEffect(new MobEffectInstance(effect, time, 0, false, false, true), entity);
    }
}