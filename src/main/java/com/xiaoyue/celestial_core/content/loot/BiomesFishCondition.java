package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class BiomesFishCondition implements LootItemCondition {

    @SerialClass.SerialField
    public TagKey<Biome> biomes;

    @Deprecated
    public BiomesFishCondition() {
    }

    public BiomesFishCondition(TagKey<Biome> biomes) {
        this.biomes = biomes;
    }

    @Override
    public LootItemConditionType getType() {
        return CCLootModifier.BIOMES_FISHING.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!ctx.hasParam(LootContextParams.THIS_ENTITY)) return false;
        Entity entity = ctx.getParam(LootContextParams.THIS_ENTITY);
        if (entity instanceof FishingHook hook) {
            Holder<Biome> biome = hook.level().getBiome(hook.getOnPos());
            return biome.is(this.biomes) && hook.isOpenWaterFishing();
        }
        return false;
    }
}
