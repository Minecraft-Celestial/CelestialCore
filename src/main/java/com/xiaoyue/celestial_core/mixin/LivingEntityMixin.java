package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.register.CCEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(at = @At("HEAD"), method = "canBeSeenAsEnemy", cancellable = true)
	public void celestial_core$canBeSeenAsEnemy$hidden(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (self.hasEffect(CCEffects.HIDDEN.get())) {
			cir.setReturnValue(false);
		}
	}

}
