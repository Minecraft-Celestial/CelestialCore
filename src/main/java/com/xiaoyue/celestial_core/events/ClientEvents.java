package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.client.screen.FlameScreens;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderLiving(RenderLivingEvent.Post<?, ?> event) {
        LivingEntity entity = event.getEntity();
        if (EntityUtils.onBlackFlame(entity)) {
            var buffer = event.getMultiBufferSource();
            var matrixStack = event.getPoseStack();
            FlameScreens.renderEntityFlame(FlameScreens.BLACK_FIRE_LAYER_1, FlameScreens.BLACK_FIRE_LAYER_2, matrixStack, buffer, entity);
        }
    }
}
