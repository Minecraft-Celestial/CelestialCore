package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
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
	public void onAttack(AttackCache cache, ItemStack weapon) {
		var attacker = cache.getAttacker();
		if (attacker != null && attacker.hasEffect(CCEffects.HIDDEN.get())) {
			attacker.removeEffect(CCEffects.HIDDEN.get());
		}
	}
}
