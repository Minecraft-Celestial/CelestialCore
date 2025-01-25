package com.xiaoyue.celestial_core.content.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FishingCooldownModifier extends LootModifier {
    public static final Codec<FishingCooldownModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("condition").forGetter((m) -> m.condition))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("result").forGetter((m) -> m.result))
            .and(Codec.STRING.optionalFieldOf("cooldown").forGetter((m) -> Optional.ofNullable(m.cooldown)
                    .map(IntConfigValue::toData)))
            .and(Codec.STRING.optionalFieldOf("chance").forGetter((m) -> Optional.ofNullable(m.chance)
                    .map(DoubleConfigValue::toData)))
            .apply(inst, FishingCooldownModifier::new));

    private final Item condition, result;
    @Nullable
    private final IntConfigValue cooldown;
    @Nullable
    private final DoubleConfigValue chance;

    protected FishingCooldownModifier(LootItemCondition[] conditionsIn, Item condition, Item result, Optional<String> cooldown, Optional<String> chance) {
        super(conditionsIn);
        this.condition = condition;
        this.result = result;
        this.cooldown = cooldown.map(IntConfigValue::of).orElse(null);
        this.chance = chance.map(DoubleConfigValue::of).orElse(null);
    }

    public FishingCooldownModifier(Item condition, Item result, LootItemCondition... conditionsIn) {
        super(conditionsIn);
        this.condition = condition;
        this.result = result;
        this.cooldown = null;
        this.chance = null;
    }

    public FishingCooldownModifier(Item condition, Item result, IntConfigValue cooldown, DoubleConfigValue chance, LootItemCondition... conditionsIn) {
        super(conditionsIn);
        this.condition = condition;
        this.result = result;
        this.cooldown = cooldown;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!context.hasParam(LootContextParams.THIS_ENTITY)) {
            return generatedLoot;
        }
        if (context.getParam(LootContextParams.THIS_ENTITY) instanceof FishingHook hook) {
            Player player = hook.getPlayerOwner();
            if (player != null && !player.getCooldowns().isOnCooldown(condition)) {
                if (chance == null || context.getRandom().nextDouble() <= chance.get()) {
                    int cooldownTime = cooldown == null ? 0 : cooldown.get();
                    player.getCooldowns().addCooldown(condition, cooldownTime);
                    return ObjectArrayList.of(new ItemStack(result));
                }
            }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
