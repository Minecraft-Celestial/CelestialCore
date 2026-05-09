package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;

public class CCTagGen {

    public static final TagKey<Item> IS_POTION = ItemTags.create(CelestialCore.loc("is_potion"));

    public static void onAttrTagGen(RegistrateTagsProvider.IntrinsicImpl<Attribute> pvd) {
        pvd.addTag(L2DamageTracker.PERCENTAGE);
    }
}
