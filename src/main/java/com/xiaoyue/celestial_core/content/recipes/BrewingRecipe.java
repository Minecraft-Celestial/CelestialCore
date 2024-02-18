package com.xiaoyue.celestial_core.content.recipes;

import com.xiaoyue.celestial_core.register.CPotion;
import com.xiaoyue.celestial_core.utils.IPotionUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class BrewingRecipe {

    public static void Brewing() {
        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(Potions.AWKWARD)),
                Ingredient.of(Items.AMETHYST_SHARD),
                IPotionUtils.setPotionStack(CPotion.CRIT_DAMAGE_BASE.get())
        ));

        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(CPotion.CRIT_DAMAGE_BASE.get())),
                Ingredient.of(Items.REDSTONE),
                IPotionUtils.setPotionStack(CPotion.CRIT_DAMAGE_LOG.get())
        ));

        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(Potions.AWKWARD)),
                Ingredient.of(Items.BLAZE_ROD),
                IPotionUtils.setPotionStack(CPotion.CRIT_RATE_BASE.get())
        ));

        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(CPotion.CRIT_RATE_BASE.get())),
                Ingredient.of(Items.REDSTONE),
                IPotionUtils.setPotionStack(CPotion.CRIT_RATE_LOG.get())
        ));

        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(Potions.AWKWARD)),
                Ingredient.of(Items.HONEY_BOTTLE),
                IPotionUtils.setPotionStack(CPotion.REPLY_POWER_BASE.get())
        ));

        BrewingRecipeRegistry.addRecipe(new net.minecraftforge.common.brewing.BrewingRecipe(
                Ingredient.of(IPotionUtils.setPotionStack(CPotion.REPLY_POWER_BASE.get())),
                Ingredient.of(Items.REDSTONE),
                IPotionUtils.setPotionStack(CPotion.REPLY_POWER_LOG.get())
        ));
    }
}
