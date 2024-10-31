package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.events.VibrationEvent;
import com.xiaoyue.celestial_core.utils.CCUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(at = @At("HEAD"), method = "dampensVibrations", cancellable = true)
	public void setVibrationsDelegate(CallbackInfoReturnable<Boolean> cir) {
		Entity entity = (Entity) (Object) this;
		if (entity instanceof LivingEntity livingEntity) {
			var event = new VibrationEvent(livingEntity, livingEntity.level());
			if (CCUtils.fireEvent(event).isCanceled()) {
				cir.setReturnValue(true);
			}
		}
	}
}
