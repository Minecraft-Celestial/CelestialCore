package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.recipes.TransformationRecipe;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

public class CCRecipes {

    public static final RegistryEntry<RecipeType<TransformationRecipe>> RT_TRANSFORMATION = CelestialCore.REGISTRATE.recipe("transformation");

    public static final RegistryEntry<BaseRecipe.RecType<TransformationRecipe, TransformationRecipe, TransformationRecipe.Inv>> RS_TRANSFORMATION =
            recipe("transformation", () -> new BaseRecipe.RecType<>(TransformationRecipe.class, RT_TRANSFORMATION));

    private static <A extends RecipeSerializer<?>> RegistryEntry<A> recipe(String id, NonNullSupplier<A> sup) {
        return CelestialCore.REGISTRATE.simple(id, ForgeRegistries.Keys.RECIPE_SERIALIZERS, sup);
    }

    public static void register() {
    }
}
