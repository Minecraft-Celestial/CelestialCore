package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.CCUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CCAttackListener implements AttackListener {

    private Optional<AttributeInstance> getAttr(LivingEntity entity, Attribute attr) {
        if (entity instanceof Player player) {
            return Optional.ofNullable(player.getAttribute(attr));
        }
        return Optional.empty();
    }

    @Override
    public void onCreateSource(CreateSourceEvent event) {
        if (event.getResult() != null &&
                event.getResult().toRoot().validState(DefaultDamageState.BYPASS_ARMOR) &&
                event.getAttacker().hasEffect(CCEffects.VIOLENT.get())) {
            event.enable(DefaultDamageState.BYPASS_ARMOR);
        }
    }

    @Override
    public void onAttack(AttackCache cache, ItemStack weapon) {
        LivingEntity target = cache.getAttackTarget();
        LivingEntity attacker = cache.getAttacker();
        if (attacker == null) return;
        this.getAttr(target, Attributes.ARMOR).ifPresent(ins -> {
            double ap = attacker.getAttributeValue(CCAttributes.ARMOR_PENETRATION.get());
            ins.removeModifier(CCUtils.BYPASS_ARMOR_UUID);
            var modifier = new AttributeModifier(CCUtils.BYPASS_ARMOR_UUID, "celestial_bypass_armor", -ap, ItemUtils.getOperation(2));
            ins.addTransientModifier(modifier);
        });
        this.getAttr(target, Attributes.ARMOR_TOUGHNESS).ifPresent(ins -> {
            double tp = attacker.getAttributeValue(CCAttributes.TOUGHNESS_PENETRATION.get());
            ins.removeModifier(CCUtils.BYPASS_TOUGHNESS_UUID);
            var modifier = new AttributeModifier(CCUtils.BYPASS_TOUGHNESS_UUID, "celestial_bypass_toughness", -tp, ItemUtils.getOperation(2));
            ins.addTransientModifier(modifier);
        });
    }

    @Override
    public void onHurtMaximized(AttackCache cache, ItemStack weapon) {
        LivingEntity attacker = cache.getAttacker();
        if (attacker != null && attacker.hasEffect(CCEffects.FEAR_CURSE.get())) {
            int level = EntityUtils.getEffectLevel(attacker, CCEffects.FEAR_CURSE.get());
            EntityUtils.hurtByPlayerOrMob(attacker, attacker.getLastHurtByMob(), level * cache.getPreDamage() * 0.2f);
        }
    }

    @Override
    public void onDamageFinalized(AttackCache cache, ItemStack weapon) {
        var event = cache.getLivingDamageEvent();
        assert event != null;
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(CCEffects.UNYIELDING.get()) && event.getAmount() >= entity.getHealth()) {
            event.setCanceled(true);
        }
        LivingEntity attacker = cache.getAttacker();
        if (attacker != null && attacker.hasEffect(CCEffects.HIDDEN.get())) {
            attacker.removeEffect(CCEffects.HIDDEN.get());
        }
        this.getAttr(entity, Attributes.ARMOR).ifPresent(ins -> ins.removeModifier(CCUtils.BYPASS_ARMOR_UUID));
        this.getAttr(entity, Attributes.ARMOR_TOUGHNESS).ifPresent(ins -> ins.removeModifier(CCUtils.BYPASS_TOUGHNESS_UUID));
    }
}
