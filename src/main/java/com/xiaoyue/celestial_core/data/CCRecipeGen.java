package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.BiFunction;

public class CCRecipeGen {

	public static void onRecipeGen(RegistrateRecipeProvider pvd) {
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
	}


	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
	}

}
