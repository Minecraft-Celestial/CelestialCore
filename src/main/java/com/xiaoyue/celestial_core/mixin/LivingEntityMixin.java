package com.xiaoyue.celestial_core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xiaoyue.celestial_core.events.CelestialHooks;
import com.xiaoyue.celestial_core.events.LivingJumpEvent;
import com.xiaoyue.celestial_core.register.CCEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(at = @At("HEAD"), method = "canBeSeenAsEnemy", cancellable = true)
	public void celestial_core$canBeSeenAsEnemy$hidden(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (self.hasEffect(CCEffects.HIDDEN.get())) {
			cir.setReturnValue(false);
		}
	}

	@ModifyReturnValue(at = @At("RETURN"), method = "getJumpPower")
	public float celestial_core$getJumpPower(float original) {
		LivingEntity self = (LivingEntity) (Object) this;
		var event = new LivingJumpEvent(self, original);
		CelestialHooks.post(event);
		if (event.isCanceled()) {
			return 0;
		}
		return event.getJumpPower();
	}

}
