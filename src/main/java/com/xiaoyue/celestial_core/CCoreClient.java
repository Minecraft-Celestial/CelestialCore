package com.xiaoyue.celestial_core;

import com.xiaoyue.celestial_core.content.client.AVirtualGoldModel;
import com.xiaoyue.celestial_core.content.client.CCModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CelestialCore.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCoreClient {

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(CCModelLayers.VIRTUAL_GOLD_ARMOR_INNER_LAYER, AVirtualGoldModel::createBodyLayer);
		event.registerLayerDefinition(CCModelLayers.VIRTUAL_GOLD_ARMOR_OUTER_LAYER, AVirtualGoldModel::createBodyLayer);
	}

}
