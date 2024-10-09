package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.effects.special.FearCurse;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class CCAttackListener implements AttackListener {

	@Override
	public void onCreateSource(CreateSourceEvent event) {
		if (event.getResult() != null &&
				event.getResult().toRoot().validState(DefaultDamageState.BYPASS_ARMOR) &&
				event.getAttacker().hasEffect(CCEffects.VIOLENT.get())) {
			event.enable(DefaultDamageState.BYPASS_ARMOR);
		}
	}

	@Override
	public void onHurtMaximized(AttackCache cache, ItemStack weapon) {
		LivingEntity attacker = cache.getAttacker();
		if (attacker != null) {
			if (attacker.hasEffect(CCEffects.FEAR_CURSE.get())) {
				int level = EntityUtils.getEffectLevel(attacker, CCEffects.FEAR_CURSE.get());
				LivingEntity source = FearCurse.getSource(attacker);
				if (source != null) {
					float damage = (float) (source.getAttributeValue(Attributes.ATTACK_DAMAGE) * level * 0.2f);
					attacker.hurt(EntityUtils.getSource(source.damageSources().magic(), source), damage);
				}
			}
		}
	}

	@Override
	public void onDamageFinalized(AttackCache cache, ItemStack weapon) {
		var attacker = cache.getAttacker();
		if (attacker != null && attacker.hasEffect(CCEffects.HIDDEN.get())) {
			attacker.removeEffect(CCEffects.HIDDEN.get());
		}
	}

}
