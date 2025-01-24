package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;

public class AttributeEffect extends CelestialEffect {
    public AttributeEffect(MobEffectCategory pCategory, int pColor, Attribute attr, String uid, double amount, int op) {
        super(pCategory, pColor);
        String uuid = MathHelper.getUUIDFromString(uid).toString();
        this.addAttributeModifier(attr, uuid, amount, ItemUtils.getOperation(op));
    }

    public AttributeEffect(int i, int pColor, Attribute attr, ResourceLocation uid, double amount, int op) {
        super(CelestialEffect.getCategory(i), pColor);
        String uuid = MathHelper.getUUIDFromString(uid.toString()).toString();
        this.addAttributeModifier(attr, uuid, amount, ItemUtils.getOperation(op));
    }

    @Override
    public boolean isFixed() {
        return false;
    }
}
