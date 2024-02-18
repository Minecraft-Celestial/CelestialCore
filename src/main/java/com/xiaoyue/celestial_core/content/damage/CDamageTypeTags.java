package com.xiaoyue.celestial_core.content.damage;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.xiaoyue.celestial_core.celestial_core.MODID;

public class CDamageTypeTags extends DamageTypeTagsProvider {
    public CDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, completableFuture, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(CDamageTypes.VIOLENT_DAMAGE, CDamageTypes.IGNORE_ARMOR, CDamageTypes.IGNORE_EFFECT,
                CDamageTypes.IGNORE_ENCHANTMENT, CDamageTypes.IGNORE_SHIELD, CDamageTypes.IGNORE_INV_TIME);
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(CDamageTypes.IGNORE_ARMOR);
        this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(CDamageTypes.IGNORE_EFFECT);
        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(CDamageTypes.IGNORE_ENCHANTMENT);
        this.tag(DamageTypeTags.BYPASSES_SHIELD).add(CDamageTypes.IGNORE_SHIELD);
        this.tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(CDamageTypes.VIOLENT_DAMAGE);
    }
}
