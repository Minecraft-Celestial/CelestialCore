package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import com.xiaoyue.celestial_core.utils.EnchUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class EquipEnchCondition implements LootItemCondition {

    @SerialClass.SerialField
    public Item armor;

    @SerialClass.SerialField
    public boolean onFire;

    @Deprecated
    public EquipEnchCondition() {
    }

    public EquipEnchCondition(Item armor, boolean onFire) {
        this.armor = armor;
        this.onFire = onFire;
    }

    @Override
    public LootItemConditionType getType() {
        return CCLootModifier.EQUIP_ENCH.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!ctx.hasParam(LootContextParams.THIS_ENTITY)) return false;
        Entity entity = ctx.getParam(LootContextParams.THIS_ENTITY);
        if (entity instanceof ArmorStand) return false;
        if (entity instanceof LivingEntity liv) {
            for (ItemStack stack : liv.getArmorSlots()) {
                if (stack.is(armor) && EnchUtils.getEnchSize(stack) > 0) {
                    return !onFire || entity.isOnFire();
                }
            }
        }
        return false;
    }
}
