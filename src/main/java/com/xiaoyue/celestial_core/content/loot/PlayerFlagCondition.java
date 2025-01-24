package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.register.CCLootModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class PlayerFlagCondition implements LootItemCondition {

    @SerialClass.SerialField
    public String flag;

    @Deprecated
    public PlayerFlagCondition() {

    }

    public PlayerFlagCondition(String flag) {
        this.flag = flag;
    }

    @Override
    public LootItemConditionType getType() {
        return CCLootModifier.PLAYER_FLAG.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!ctx.hasParam(LootContextParams.LAST_DAMAGE_PLAYER)) return false;
        var player = ctx.getParam(LootContextParams.LAST_DAMAGE_PLAYER);
        return PlayerFlagData.HOLDER.get(player).hasFlag(flag);
    }

}
