package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.BiFunction;

public class CCRecipeGen {

	public static void onRecipeGen(RegistrateRecipeProvider pvd) {
		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CCItems.EARTH_CORE, 1)::unlockedBy, Items.CRYING_OBSIDIAN)
				.pattern("FCF").pattern("AGE").pattern("DBD")
				.define('A', Items.DEEPSLATE)
				.define('B', Items.CRYING_OBSIDIAN)
				.define('C', Items.POLISHED_GRANITE)
				.define('D', Items.POLISHED_BLACKSTONE)
				.define('E', Items.POLISHED_DIORITE)
				.define('F', Items.MOSSY_COBBLESTONE)
				.define('G', Items.STONE)
				.save(pvd);
	}


	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
	}

}
