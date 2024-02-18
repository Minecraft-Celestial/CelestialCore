package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.register.COAttributes;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ArrowDamage extends MobEffect {
    public ArrowDamage() {
        super(MobEffectCategory.BENEFICIAL, 0xff9900);
        this.addAttributeModifier(COAttributes.ARROW_DAMAGE.get(),
                MathHelper.getUUIDFromString("celestial_core:arrow_damage_effect").toString(),
                0.2f, AttributeModifier.Operation.ADDITION);
    }
}
