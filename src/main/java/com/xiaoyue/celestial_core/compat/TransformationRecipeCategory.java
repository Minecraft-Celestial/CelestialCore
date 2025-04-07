package com.xiaoyue.celestial_core.compat;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.client.render.GuiRenderHandler;
import com.xiaoyue.celestial_core.content.recipes.TransformationRecipe;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2library.serial.recipe.BaseRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class TransformationRecipeCategory extends BaseRecipeCategory<TransformationRecipe, TransformationRecipeCategory> {

    public IDrawable arrow;
    public IDrawable slotBackground;
    public IJeiHelpers jeiHelpers;

    public TransformationRecipeCategory() {
        super(CelestialCore.loc("special"), TransformationRecipe.class);
    }

    public TransformationRecipeCategory init(IJeiHelpers jeiHelpers) {
        this.jeiHelpers = jeiHelpers;
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        background = guiHelper.createBlankDrawable(166, 66);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, CCItems.EARTH_CORE.asStack());
        arrow = guiHelper.createDrawable(CCJeiPlugin.TEXTURE, 0, 17, 24, 17);
        slotBackground = guiHelper.createDrawable(CCJeiPlugin.TEXTURE, 0, 34, 18, 18);
        return this;
    }

    @Override
    public Component getTitle() {
        return CCLangData.TRANSFORMATION_RECIPE_TITLE.get();
    }

    private void renderInfo(GuiGraphics guiGraphics, List<Component> info, double mx, double my, double x, double y) {
        GuiRenderHandler.renderInfoFromMouse(guiGraphics, info, mx, my, x, y, 18, 18, 10, 10);
    }

    @Override
    public void draw(TransformationRecipe recipe, IRecipeSlotsView view, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 61, 23);
        arrow.draw(guiGraphics, 112, 23);
        IModIdHelper modIdHelper = jeiHelpers.getModIdHelper();
        List<Component> inputInfo = List.of(recipe.stat.getName(), Component.literal(modIdHelper.getFormattedModNameForModId(ForgeRegistries.BLOCKS.getKey(recipe.stat).getNamespace())));
        renderInfo(guiGraphics, inputInfo, mouseX, mouseY, 90, 22);
        if (recipe.stat.defaultBlockState().getFluidState().isEmpty()) {
            GuiRenderHandler.of(recipe.stat)
                    .rotateBlock(12.5, 202.5, 0)
                    .scale(14)
                    .lighting(GuiRenderHandler.BLOCK_LIGHTING)
                    .atLocal(0, 0.2, 0)
                    .at(90, 22)
                    .render(guiGraphics);
        } else {
            GuiRenderHandler.of(recipe.stat.defaultBlockState().getFluidState().getType())
                    .rotateBlock(12.5, 202.5, 0)
                    .scale(13)
                    .lighting(GuiRenderHandler.BLOCK_LIGHTING)
                    .atLocal(0, 0.2, 0)
                    .at(90, 22)
                    .render(guiGraphics);
        }
        if (recipe.after != null) {
            List<Component> afterInfo = List.of(recipe.after.getName(), Component.literal(modIdHelper.getFormattedModNameForModId(ForgeRegistries.BLOCKS.getKey(recipe.after).getNamespace())));
            renderInfo(guiGraphics, afterInfo, mouseX, mouseY, 142, 31);
            if (recipe.after.defaultBlockState().getFluidState().isEmpty()) {
                GuiRenderHandler.of(recipe.after)
                        .rotateBlock(12.5, 202.5, 0)
                        .scale(14)
                        .lighting(GuiRenderHandler.BLOCK_LIGHTING)
                        .atLocal(0, 0.2, 0)
                        .at(142, 31)
                        .render(guiGraphics);
            } else {
                GuiRenderHandler.of(recipe.after.defaultBlockState().getFluidState().getType())
                        .rotateBlock(12.5, 202.5, 0)
                        .scale(13)
                        .lighting(GuiRenderHandler.BLOCK_LIGHTING)
                        .atLocal(0, 0.2, 0)
                        .at(142, 31)
                        .render(guiGraphics);
            }
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TransformationRecipe recipe, IFocusGroup group) {
        var y = 5;
        var x = 5;
        for (int i = 0; i < 9; i++) {
            if (recipe.inputs.size() > i) {
                builder.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                        .setSlotName("input" + i)
                        .addIngredients(recipe.inputs.get(i));
            }
            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, x + 1, y + 1)
                    .setSlotName("background" + i)
                    .setBackground(slotBackground, -1, -1);
            y += 18;
            if (y >= 54) {
                y -= 54;
                x += 18;
            }
        }
        boolean hasAfter = recipe.after != null;
        builder.addSlot(RecipeIngredientRole.OUTPUT, 142, hasAfter ? 14 : 25)
                .setSlotName("output")
                .setBackground(slotBackground, -1, -1)
                .addItemStack(recipe.output);
        builder.addSlot(RecipeIngredientRole.CATALYST, 92, 24)
                .setBackground(slotBackground, -1, -1);
        if (!hasAfter) {
            return;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 34)
                .setBackground(slotBackground, -1, -1);
    }
}
