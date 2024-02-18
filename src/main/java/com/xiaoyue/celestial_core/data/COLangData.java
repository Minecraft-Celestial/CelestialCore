package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.register.COEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;

public class COLangData {

	public static void addLang(RegistrateLangProvider pvd) {
		List<Item> list = List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
		for (RegistryEntry<? extends Potion> ent : COEffects.POTION_LIST) {
			for (Item item : list) {
				String pref = item.getDescriptionId();
				String[] prefs = pref.split("\\.");
				String str = ent.get().getName(item.getDescriptionId() + ".effect.");
				String[] ids = ent.get().getEffects().get(0).getDescriptionId().split("\\.");
				String id = ids[ids.length - 1];
				String name = RegistrateLangProvider.toEnglishName(id);
				String pref_name = RegistrateLangProvider.toEnglishName(prefs[prefs.length - 1]);
				if (item == Items.TIPPED_ARROW) pref_name = "Arrow";
				pvd.add(str, pref_name + " of " + name);
			}
		}
	}

}
