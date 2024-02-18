package com.xiaoyue.celestial_core;

import com.mojang.logging.LogUtils;
import com.xiaoyue.celestial_core.content.recipes.BrewingRecipe;
import com.xiaoyue.celestial_core.register.CAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.xiaoyue.celestial_core.register.CAttributes.ATTRIBUTES;
import static com.xiaoyue.celestial_core.register.CEffects.EFFECT;
import static com.xiaoyue.celestial_core.register.CLootModifier.LOOT_MODIFIER_SERIALIZERS;
import static com.xiaoyue.celestial_core.register.COGroups.CREATIVE_TAB;
import static com.xiaoyue.celestial_core.register.COItems.ITEM;
import static com.xiaoyue.celestial_core.register.CPotion.POTION;

@Mod(celestial_core.MODID)
public class celestial_core
{
    public static final String MODID = "celestial_core";
    private static final Logger LOGGER = LogUtils.getLogger();

    public celestial_core()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::modifierAttribute);
        modEventBus.addListener(this::onLoadCompleteEvt);

        ITEM.register(modEventBus);
        POTION.register(modEventBus);
        EFFECT.register(modEventBus);
        ATTRIBUTES.register(modEventBus);
        CREATIVE_TAB.register(modEventBus);
        LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onLoadCompleteEvt(FMLLoadCompleteEvent event) {
        BrewingRecipe.Brewing();
    }

    private void modifierAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, CAttributes.CRIT_RATE.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.CRIT_DAMAGE.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.REPLY_POWER.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.ARROW_DAMAGE.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.ARROW_SPEED.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.ARROW_KNOCK.get(), 1.0);
        event.add(EntityType.PLAYER, CAttributes.DAMAGE_REDUCTION.get(), 1.0);
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientMain {

    }
}
