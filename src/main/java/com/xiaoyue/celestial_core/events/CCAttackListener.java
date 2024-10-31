package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
		if (attacker != null && attacker.hasEffect(CCEffects.FEAR_CURSE.get())) {
			int level = EntityUtils.getEffectLevel(attacker, CCEffects.FEAR_CURSE.get());
			LivingEntity mob = attacker.getLastHurtByMob();
			if (mob instanceof Player player) {
				attacker.hurt(attacker.damageSources().playerAttack(player), level * cache.getPreDamage() * 0.2f);
			} else if (mob != null) {
				attacker.hurt(attacker.damageSources().mobAttack(mob), level * cache.getPreDamage() * 0.2f);
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
