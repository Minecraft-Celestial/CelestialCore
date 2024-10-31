package com.xiaoyue.celestial_core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.xiaoyue.celestial_core.events.DamageItemEvent;
import com.xiaoyue.celestial_core.utils.CCUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	@WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;damageItem(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)I", remap = false), method = "hurtAndBreak")
	public int setHurtAndBroken(Item instance, ItemStack stack, int value, LivingEntity entity, Consumer consumer, Operation<Integer> original) {
		var event = new DamageItemEvent(entity, stack, value);
		return original.call(instance, stack, CCUtils.fireEvent(event).getAmount(), entity, consumer);
	}
}