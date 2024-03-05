package com.xiaoyue.celestial_core;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.data.*;
import com.xiaoyue.celestial_core.events.CCAttackListener;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CCLootModifier;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2library.base.L2Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
					e -> e.icon(CCItems.FIRE_ESSENCE::asStack));

	public CelestialCore() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		CCItems.register();
		CCEffects.register();
		PlayerFlagData.register();
		CCAttributes.ATTRIBUTES.register(modEventBus);
		CCLootModifier.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
		AttackEventHandler.register(3450, new CCAttackListener());

		REGISTRATE.addDataGenerator(ProviderType.LANG, CCLangData::addLang);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, CCRecipeGen::onRecipeGen);
		REGISTRATE.addDataGenerator(ProviderType.LOOT, CCLootTableGen::onLootGen);
	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			CCEffects.registerBrewingRecipe();
		});
	}

	@SubscribeEvent
	public static void modifyAttribute(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, CCAttributes.REPLY_POWER.get(), 1.0);
		event.add(EntityType.PLAYER, CCAttributes.ARROW_SPEED.get(), 1.0);
		event.add(EntityType.PLAYER, CCAttributes.ARROW_KNOCK.get(), 1.0);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		boolean included = event.includeServer();
		var provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		new CCDamageTypes(output, provider, helper).generate(included, generator);
		generator.addProvider(included, new CCGLMProvider(output));
		generator.addProvider(included, new CCL2ConfigGen(generator));
	}

	public static ResourceLocation loc(String id) {
		return new ResourceLocation(MODID, id);
	}

}
