package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.content.loot.*;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CCMaterials;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import com.xiaoyue.celestial_invoker.content.generic.generator.RegistrateLootModifierProvider;
import dev.xkmc.l2core.serial.loot.LootTableTemplate;
import net.minecraft.advancements.critereon.*;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

public class CCGLMProvider {

    public static void onModifierGen(RegistrateLootModifierProvider pvd) {
        pvd.add("chests/abandoned_mineshaft", new AddLootTableModifier(CCLootTableGen.MINESHAFT,
                new LootTableIdCondition.Builder(BuiltInLootTables.ABANDONED_MINESHAFT.location()).build()));
        pvd.add("chests/end_city_treasure", new AddLootTableModifier(CCLootTableGen.END_CITY,
                new LootTableIdCondition.Builder(BuiltInLootTables.END_CITY_TREASURE.location()).build()));
        pvd.add("chests/igloo_chest", new AddLootTableModifier(CCLootTableGen.IGLOO_CHEST,
                new LootTableIdCondition.Builder(BuiltInLootTables.IGLOO_CHEST.location()).build()));

        pvd.add("drops/fire_essence", new AddItemModifier(CCItems.FIRE_ESSENCE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.fireEssenceChance),
                entityType(EntityType.BLAZE), LootTableTemplate.byPlayer().build()));
        pvd.add("drops/ocean_essence", new AddItemModifier(CCItems.OCEAN_ESSENCE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.oceanEssenceChance),
                entityType(EntityType.GUARDIAN), LootTableTemplate.byPlayer().build()));
        pvd.add("drops/death_essence", new AddItemModifier(CCItems.DEATH_ESSENCE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.deathEssenceChance),
                damage(CCDamageTypes.WITHER),
                new EntityHealthCondition(IntConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.deathEssenceMinHealth))));
        pvd.add("drops/cursed_essence", new AddItemModifier(CCItems.CURSED_ESSENCE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.cursedEssenceChance),
                new PlayerFlagCondition(CelestialFlags.NETHER_STAGE), new CurseEnchCondition(EquipmentSlot.MAINHAND)));
        pvd.add("drops/warden_sclerite", new AddItemModifier(CCItems.WARDEN_SCLERITE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.wardenScleriteChance),
                entityType(EntityType.WARDEN), LootTableTemplate.byPlayer().build()));
        pvd.add("drops/shulker_scrap", new AddItemModifier(CCItems.SHULKER_SCRAP.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.shulkerScrapChance),
                entityType(EntityType.SHULKER), damage(DamageTypeTags.IS_EXPLOSION)));
        pvd.add("drops/light_fragment", new AddItemModifier(CCItems.LIGHT_FRAGMENT.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.lightFragmentChance),
                entityType(EntityType.HUSK), new PlayerFlagCondition(CelestialFlags.NETHER_STAGE)));
        pvd.add("drops/midnight_fragment", new AddItemModifier(CCItems.MIDNIGHT_FRAGMENT.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.midnightFragmentChance),
                entityType(EntityType.STRAY), new PlayerFlagCondition(CelestialFlags.NETHER_STAGE)));
        pvd.add("drops/soaring_wings", new AddItemModifier(CCItems.SOARING_WINGS.get(), null,
                entityType(EntityType.PHANTOM), LootTableTemplate.byPlayer().build(),
                entity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.atYLocation(MinMaxBounds.Doubles.atLeast(619))))));
        pvd.add("drops/pure_nether_star", new AddItemModifier(CCItems.PURE_NETHER_STAR.get(), null,
                entityType(EntityType.WITHER), new PlayerEffectCondition(MobEffectCategory.BENEFICIAL,
                IntConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.pureNetherStarEffectCount))));
        pvd.add("drops/heart_fragment", new AddItemModifier(CCItems.HEART_FRAGMENT.get(), null,
                entityType(EntityType.PILLAGER), new ChargedCreeperKillCondition()));
        pvd.add("drops/guardian_spike", new AddItemModifier(CCItems.GUARDIAN_SPIKE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.guardianSpikeChance),
                entityType(EntityType.GUARDIAN)));
        pvd.add("drops/elder_guardian_spike", new AddItemModifier(CCItems.GUARDIAN_SPIKE.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.guardianSpikeChance),
                entityType(EntityType.ELDER_GUARDIAN)));

        pvd.add("drops/sakura_fragment", new ExtraDropModifier(CCItems.SAKURA_FRAGMENT.get(),
                DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.sakuraFragmentChance),
                block(Blocks.CHERRY_LEAVES)));

        {
            var item = CCMaterials.VIRTUAL_GOLD.getNugget();
            DoubleConfigValue chance = DoubleConfigValue.of(CCModConfig.SERVER.getPath(), CCModConfig.SERVER.virtualGoldNuggetChance);
            pvd.add("drops/virtual_gold_head", new AddItemModifier(item, chance,
                    new EquipEnchCondition(Items.GOLDEN_HELMET, true)));
            pvd.add("drops/virtual_gold_chest", new AddItemModifier(item, chance,
                    new EquipEnchCondition(Items.GOLDEN_CHESTPLATE, true)));
            pvd.add("drops/virtual_gold_legs", new AddItemModifier(item, chance,
                    new EquipEnchCondition(Items.GOLDEN_LEGGINGS, true)));
            pvd.add("drops/virtual_gold_feet", new AddItemModifier(item, chance,
                    new EquipEnchCondition(Items.GOLDEN_BOOTS, true)));
        }
    }

    public static LootItemCondition entity(EntityPredicate.Builder builder) {
        return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, builder).build();
    }

    public static LootItemCondition entityType(EntityType<?> type) {
        return entity(EntityPredicate.Builder.entity().of(type));
    }

    public static LootItemCondition entityType(TagKey<EntityType<?>> tag) {
        return entity(EntityPredicate.Builder.entity().of(tag));
    }

    public static LootItemCondition damage(TagKey<DamageType> tag) {
        return DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType()
                .tag(TagPredicate.is(tag))).build();
    }

    public static LootItemCondition block(Block block) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).build();
    }
}
