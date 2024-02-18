package com.xiaoyue.celestial_core;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.data.CDamageTypeTags;
import com.xiaoyue.celestial_core.data.COLangData;
import com.xiaoyue.celestial_core.register.COAttributes;
import com.xiaoyue.celestial_core.register.COEffects;
import com.xiaoyue.celestial_core.register.COItems;
import com.xiaoyue.celestial_core.register.COLootModifier;
import dev.xkmc.l2library.base.L2Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CelestialCore.MODID)
@Mod.EventBusSubscriber(modid = CelestialCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CelestialCore {

	public static final String MODID = "celestial_core";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final RegistryEntry<CreativeModeTab> TAB =
			REGISTRATE.buildModCreativeTab("core", "Celestial Core",
					e -> e.icon(COItems.FIRE_ESSENCE::asStack));

	public CelestialCore() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		COItems.register();
		COEffects.register();
		COAttributes.ATTRIBUTES.register(modEventBus);
		COLootModifier.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

		REGISTRATE.addDataGenerator(ProviderType.LANG, COLangData::addLang);
	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			COEffects.registerBrewingRecipe();
		});
	}

	@SubscribeEvent
	public static void modifyAttribute(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, COAttributes.CRIT_RATE.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.CRIT_DAMAGE.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.REPLY_POWER.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.ARROW_DAMAGE.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.ARROW_SPEED.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.ARROW_KNOCK.get(), 1.0);
		event.add(EntityType.PLAYER, COAttributes.DAMAGE_REDUCTION.get(), 1.0);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		boolean included = event.includeServer();
		var provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		generator.addProvider(included, new CDamageTypeTags(output, provider, helper));
	}

}
