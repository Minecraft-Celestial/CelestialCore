package com.xiaoyue.celestial_core.content.recipes;

import com.xiaoyue.celestial_core.register.CCRecipes;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class TransformationRecipe extends BaseRecipe<TransformationRecipe, TransformationRecipe, TransformationRecipe.Inv> {

    @SerialClass.SerialField
    public List<Ingredient> inputs = new ArrayList<>();

    @SerialClass.SerialField
    public Block stat;

    @SerialClass.SerialField
    public ItemStack output;

    @SerialClass.SerialField
    @Nullable
    public Block after;

    public TransformationRecipe(ResourceLocation id) {
        super(id, CCRecipes.RS_TRANSFORMATION.get());
    }

    @Override
    public boolean matches(Inv inv, Level level) {
        return true;
    }

    @Override
    public ItemStack assemble(Inv inv, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output;
    }

    public static class Inv extends SimpleContainer implements RecInv<TransformationRecipe> {
        public Inv() {
            super(9);
        }
    }
}
