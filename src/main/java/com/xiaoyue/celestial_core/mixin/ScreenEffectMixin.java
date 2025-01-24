package com.xiaoyue.celestial_core.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.xiaoyue.celestial_core.content.client.screen.FlameScreens;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isOnFire()Z"), method = "renderScreenEffect")
    private static void celestial_core$renderFireScreen(Minecraft pMinecraft, PoseStack pPoseStack, CallbackInfo ci) {
        if (pMinecraft.player != null) {
            if (EntityUtils.onBlackFlame(pMinecraft.player)) {
                FlameScreens.renderFlameScreen(FlameScreens.BLACK_FIRE_LAYER_2, pPoseStack);
            }
        }
    }
}
