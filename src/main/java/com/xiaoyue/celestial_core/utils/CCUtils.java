package com.xiaoyue.celestial_core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCUtils {

	public static RandomSource getRandom() {
		return RandomSource.create();
	}

	public static void meltingDropsInEvent(LivingDropsEvent event) {
		for (ItemEntity drop : new ArrayList<>(event.getDrops())) {
			ItemStack stack = drop.getItem();
			Level level = drop.level();
			Optional<SmeltingRecipe> optional = level.getRecipeManager().getRecipeFor(
					RecipeType.SMELTING, new SimpleContainer(stack), level);
			optional.ifPresent(recipe -> {
				ItemStack result = recipe.getResultItem(level.registryAccess());
				ItemStack copy = result.copyWithCount(stack.getCount() * result.getCount());
				event.getDrops().remove(drop);
				event.getDrops().add(EntityUtils.getItemEntity(copy, event.getEntity()));
			});
		}
	}

	public static void addFlySpeed(LivingEntity entity, float speed) {
		Vec3 look = entity.getLookAngle();
		Vec3 motion = entity.getDeltaMovement();
		entity.setDeltaMovement(motion.add(
				look.x * 0.1D + (look.x * speed - motion.x) * 0.5D,
				look.y * 0.1D + (look.y * speed - motion.y) * 0.5D,
				look.z * 0.1D + (look.z * speed - motion.z) * 0.5D
		));
	}

	public static int getLight(Level level, BlockPos pos) {
		if (level instanceof ServerLevel sl) {
			return sl.getMaxLocalRawBrightness(pos);
		}
		return level.getMaxLocalRawBrightness(pos, getSkyDarken(level));
	}

	public static int getSkyDarken(Level level) {
		double d0 = 1 - (level.getRainLevel(1) * 5) / 16;
		double d1 = 1 - (level.getThunderLevel(1) * 5) / 16;
		double d2 = 0.5 + 2 * Mth.clamp(Mth.cos(level.getTimeOfDay(1) * ((float) Math.PI * 2)), -0.25, 0.25);
		return (int) ((1 - d2 * d0 * d1) * 11);
	}

	public static void attractItem(LivingEntity entity, AABB range, float speed) {
		attractEntity(entity, ItemEntity.class, range, speed);
	}

	public static <E extends Entity> void attractEntity(LivingEntity entity, Class<E> targets, AABB range, float speed) {
		List<E> list = entity.level().getEntitiesOfClass(targets, range);
		for (E target : list) {
			if (target.isRemoved()) {
				continue;
			}
			Vec3 sub = entity.position().subtract(target.getX(), target.getY(), target.getZ()).normalize().scale(speed);
			if (target.isNoGravity()) {
				sub = sub.add(0, 0.04f, 0);
			}
			target.setDeltaMovement(sub);
		}
	}
}
