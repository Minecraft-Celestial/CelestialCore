package com.xiaoyue.celestial_core.content.recipes;

import com.xiaoyue.celestial_core.register.CCRecipes;
import dev.xkmc.l2library.serial.recipe.BaseRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TransformationRecipeBuilder extends BaseRecipeBuilder<TransformationRecipeBuilder, TransformationRecipe, TransformationRecipe, TransformationRecipe.Inv> {
    public TransformationRecipeBuilder(List<Ingredient> inputs, Block stat, ItemStack output, @Nullable Block after) {
        super(CCRecipes.RS_TRANSFORMATION.get());
        this.recipe.inputs = inputs;
        this.recipe.stat = stat;
        this.recipe.output = output;
        this.recipe.after = after;
    }

    public TransformationRecipeBuilder(List<Ingredient> inputs, Block stat, ItemStack output) {
        super(CCRecipes.RS_TRANSFORMATION.get());
        this.recipe.inputs = inputs;
        this.recipe.stat = stat;
        this.recipe.output = output;
        this.recipe.after = null;
    }
}
