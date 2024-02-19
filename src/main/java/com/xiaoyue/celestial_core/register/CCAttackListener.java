package com.xiaoyue.celestial_core.register;

import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;

public class CCAttackListener implements AttackListener {

	@Override
	public void onCreateSource(CreateSourceEvent event) {
		if (event.getResult() != null &&
				event.getResult().toRoot().validState(DefaultDamageState.BYPASS_ARMOR) &&
				event.getAttacker().hasEffect(CCEffects.VIOLENT.get())) {
			event.enable(DefaultDamageState.BYPASS_ARMOR);
		}
	}

}
