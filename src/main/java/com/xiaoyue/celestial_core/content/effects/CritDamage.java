package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.COAttributes;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CritDamage extends MobEffect {
    public CritDamage() {
        super(MobEffectCategory.BENEFICIAL, 0xd9ac4a);
        this.addAttributeModifier(COAttributes.CRIT_DAMAGE.get(),
                MathHelper.getUUIDFromString("celestial_core:crit_damage_effect").toString(),
                0.1f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
