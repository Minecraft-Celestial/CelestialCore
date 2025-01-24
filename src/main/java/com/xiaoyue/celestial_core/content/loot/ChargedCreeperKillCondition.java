package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class ChargedCreeperKillCondition implements LootItemCondition {

    public ChargedCreeperKillCondition() {

    }

    @Override
    public LootItemConditionType getType() {
        return CCLootModifier.CHARGED_CREEPER.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!ctx.hasParam(LootContextParams.KILLER_ENTITY)) return false;
        var killer = ctx.getParam(LootContextParams.KILLER_ENTITY);
        return killer instanceof Creeper creeper && creeper.isPowered();
    }

}
