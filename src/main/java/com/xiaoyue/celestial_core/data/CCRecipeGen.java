package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CCMaterials;
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
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.function.BiFunction;

public class CCRecipeGen {

	private static String currentFolder = "";

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
				.save(pvd, getID(CCItems.GUARDIAN_OCEAN_INGOT, "_misc"));

		genStorage(pvd, CCItems.GUARDIAN_OCEAN_INGOT.get(), CCItems.OCEAN_NUGGET.get());
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

	public static ResourceLocation getID(String modId, Item item) {
		return new ResourceLocation(modId, currentFolder + ForgeRegistries.ITEMS.getKey(item).getPath());
	}

	private static ResourceLocation getID(Item item) {
		return getID(CelestialCore.MODID, item);
	}

	public static void genStorage(RegistrateRecipeProvider pvd, Item ingot, Item nugget) {
		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot, 1)::unlockedBy, nugget)
				.pattern("BBB").pattern("BBB").pattern("BBB")
				.define('B', nugget)
				.save(pvd, getID(ingot, "_storage"));
		unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)::unlockedBy, ingot)
				.requires(ingot)
				.save(pvd);
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
