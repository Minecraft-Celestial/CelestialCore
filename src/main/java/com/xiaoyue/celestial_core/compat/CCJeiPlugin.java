package com.xiaoyue.celestial_core.compat;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCRecipes;
import dev.xkmc.l2library.util.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class CCJeiPlugin implements IModPlugin {

    public static final ResourceLocation ID = CelestialCore.loc("main");
    public static final ResourceLocation TEXTURE = CelestialCore.loc("textures/gui/jei.png");
    public final TransformationRecipeCategory TRANSFORMATION = new TransformationRecipeCategory();

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(TRANSFORMATION.init(registration.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Proxy.getClientWorld();
        assert level != null;
        registration.addRecipes(TRANSFORMATION.getRecipeType(), level.getRecipeManager().getAllRecipesFor(CCRecipes.RT_TRANSFORMATION.get()));
    }
}
