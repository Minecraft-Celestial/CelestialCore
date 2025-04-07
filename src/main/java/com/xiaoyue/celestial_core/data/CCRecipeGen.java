package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.recipes.TransformationRecipeBuilder;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CCMaterials;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2damagetracker.contents.materials.api.IMatVanillaType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

public class CCRecipeGen {

    public static String currentFolder = "";

    public static void onRecipeGen(RegistrateRecipeProvider pvd) {
        unlock(pvd, new TransformationRecipeBuilder(List.of(Ingredient.of(Items.DIAMOND), Ingredient.of(CCItems.OCEAN_ESSENCE)), Blocks.WATER,
                Items.HEART_OF_THE_SEA.getDefaultInstance())::unlockedBy, Items.DIAMOND).save(pvd, getID(Items.HEART_OF_THE_SEA));

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CCItems.EARTH_CORE, 1)::unlockedBy, Items.CRYING_OBSIDIAN)
                .pattern("ABC").pattern("DEF").pattern("GHI")
                .define('A', Items.AMETHYST_BLOCK)
                .define('B', Items.CRYING_OBSIDIAN)
                .define('C', Items.QUARTZ_BLOCK)
                .define('D', ItemTags.DIAMOND_ORES)
                .define('E', Items.END_STONE)
                .define('F', ItemTags.EMERALD_ORES)
                .define('G', Items.LAPIS_BLOCK)
                .define('H', Items.GILDED_BLACKSTONE)
                .define('I', Items.REDSTONE_BLOCK)
                .save(pvd);

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TOTEM_OF_UNDYING, 1)::unlockedBy, CCItems.BROKEN_TOTEM.get())
                .pattern("EGE").pattern("GOG").pattern(" L ")
                .define('E', Items.EMERALD)
                .define('G', Items.GOLD_INGOT)
                .define('O', CCItems.BROKEN_TOTEM)
                .define('L', CCItems.LIGHT_FRAGMENT)
                .save(pvd, getID(Items.TOTEM_OF_UNDYING));

        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.NAME_TAG, 1)::unlockedBy, Items.PAPER)
                .requires(Items.PAPER).requires(Items.STRING).requires(Items.IRON_INGOT)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CRYING_OBSIDIAN, 1)::unlockedBy, Items.OBSIDIAN)
                .requires(Items.OBSIDIAN).requires(Items.GHAST_TEAR)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.EXPERIENCE_BOTTLE, 3)::unlockedBy, Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE).requires(CCItems.VIRTUAL_GOLD_NUGGET).requires(CCItems.LIGHT_FRAGMENT)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SCUTE, 2)::unlockedBy, CCItems.HEART_FRAGMENT.get())
                .requires(CCItems.OCEAN_ESSENCE).requires(CCItems.HEART_FRAGMENT).requires(Items.SEAGRASS)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CCItems.SAKURA_STEEL, 1)::unlockedBy, Items.IRON_INGOT)
                .requires(Items.COPPER_INGOT).requires(Items.GOLD_INGOT).requires(CCItems.SAKURA_FRAGMENT).requires(CCItems.SAKURA_FRAGMENT)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CCItems.OCEAN_INGOT, 1)::unlockedBy, Items.IRON_INGOT)
                .requires(Items.IRON_INGOT).requires(Items.PRISMARINE_CRYSTALS).requires(Items.PRISMARINE_CRYSTALS).requires(Items.PRISMARINE_SHARD)
                .requires(Items.PRISMARINE_SHARD)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CCItems.GUARDIAN_OCEAN_INGOT, 1)::unlockedBy, CCItems.OCEAN_INGOT.get())
                .requires(CCItems.OCEAN_INGOT).requires(CCItems.GUARDIAN_SPIKE).requires(CCItems.GUARDIAN_SPIKE).requires(CCItems.GUARDIAN_SPIKE)
                .requires(CCItems.GUARDIAN_SPIKE)
                .save(pvd);

        genStorage(pvd, CCItems.GUARDIAN_OCEAN_INGOT.get(), CCItems.GUARDIAN_OCEAN_NUGGET.get());
        genStorage(pvd, CCItems.VIRTUAL_GOLD_INGOT.get(), CCItems.VIRTUAL_GOLD_NUGGET.get());

        for (int i = 0; i < CCMaterials.values().length; i++) {
            CCMaterials mat = CCMaterials.values()[i];
            ItemEntry<?>[] arr = CCItems.GEN_ITEM[i];
            genTools(pvd, mat, arr);
        }
    }

    public static String getID(ItemLike item, String id) {
        return RecipeBuilder.getDefaultRecipeId(item) + id;
    }

    public static ResourceLocation getID(String modid, Item item) {
        return new ResourceLocation(modid, currentFolder + ItemUtils.getKey(item).getPath());
    }

    private static ResourceLocation getID(Item item) {
        return getID(CelestialCore.MODID, item);
    }

    public static void genStorage(RegistrateRecipeProvider pvd, Item ingot, Item nugget) {
        currentFolder = "storage/";
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot, 1)::unlockedBy, nugget)
                .pattern("BBB").pattern("BBB").pattern("BBB")
                .define('B', nugget)
                .save(pvd, getID(ingot));
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)::unlockedBy, ingot)
                .requires(ingot)
                .save(pvd, getID(nugget));
    }

    public static void genTools(RegistrateRecipeProvider pvd, IMatVanillaType mat, ItemEntry<?>[] arr) {
        currentFolder = "generated_tools/" + mat.getID().toLowerCase(Locale.ROOT) + "/craft/";
        {
            Item ingot = mat.getIngot();
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.COMBAT, arr[0].get(), 1)::unlockedBy, arr[0].get())
                    .pattern("A A").pattern("A A").define('A', ingot).save(pvd, getID(arr[0].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.COMBAT, arr[1].get(), 1)::unlockedBy, arr[1].get())
                    .pattern("AAA").pattern("A A").pattern("A A").define('A', ingot).save(pvd, getID(arr[1].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.COMBAT, arr[2].get(), 1)::unlockedBy, arr[2].get())
                    .pattern("A A").pattern("AAA").pattern("AAA").define('A', ingot).save(pvd, getID(arr[2].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.COMBAT, arr[3].get(), 1)::unlockedBy, arr[3].get())
                    .pattern("AAA").pattern("A A").define('A', ingot).save(pvd, getID(arr[3].get()));
        }
        {
            Item ingot = mat.getToolIngot();
            Item stick = mat.getToolStick();
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.COMBAT, arr[4].get(), 1)::unlockedBy, arr[4].get())
                    .pattern("A").pattern("A").pattern("B").define('A', ingot).define('B', stick).save(pvd, getID(arr[4].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.TOOLS, arr[5].get(), 1)::unlockedBy, arr[5].get())
                    .pattern("AA").pattern("AB").pattern(" B").define('A', ingot).define('B', stick).save(pvd, getID(arr[5].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.TOOLS, arr[6].get(), 1)::unlockedBy, arr[6].get())
                    .pattern("A").pattern("B").pattern("B").define('A', ingot).define('B', stick).save(pvd, getID(arr[6].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.TOOLS, arr[7].get(), 1)::unlockedBy, arr[7].get())
                    .pattern("AAA").pattern(" B ").pattern(" B ").define('A', ingot).define('B', stick).save(pvd, getID(arr[7].get()));
            unlock(pvd, new ShapedRecipeBuilder(RecipeCategory.TOOLS, arr[8].get(), 1)::unlockedBy, arr[8].get())
                    .pattern("AA").pattern(" B").pattern(" B").define('A', ingot).define('B', stick).save(pvd, getID(arr[8].get()));
        }
    }

    public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
        return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
    }
}
