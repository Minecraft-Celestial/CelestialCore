package com.xiaoyue.celestial_core.content.generic;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.List;
import java.util.function.Consumer;

public class CelestialEffect extends MobEffect {
    public final boolean showIcon;

    public CelestialEffect(MobEffectCategory pCategory, int pColor, boolean showIcon) {
        super(pCategory, pColor);
        this.showIcon = showIcon;
    }

    public CelestialEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.showIcon = true;
    }

    public boolean isFixed() {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        if (this.isFixed()) {
            return List.of();
        }
        return super.getCurativeItems();
    }

    @Override
    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInInventory(MobEffectInstance instance) {
                return showIcon;
            }

            @Override
            public boolean isVisibleInGui(MobEffectInstance instance) {
                return showIcon;
            }
        });
    }

    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return true;
    }

    public static MobEffectCategory getCategory(int i) {
        return switch (i) {
            case 0 -> MobEffectCategory.BENEFICIAL;
            case 1 -> MobEffectCategory.NEUTRAL;
            case 2 -> MobEffectCategory.HARMFUL;
            default -> throw new IllegalStateException("unknown mobEffect category value: " + i);
        };
    }
}
