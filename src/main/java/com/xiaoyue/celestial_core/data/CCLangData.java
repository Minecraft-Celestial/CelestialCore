package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.events.CCGeneralEventHandler;
import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2library.util.Proxy;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;
import java.util.Locale;

public enum CCLangData {
	SIMPLE_DROP("%s chance to drop from %s", 2),
	AFTER_WITHER_DROP("After defeating %s, %s chance to drop from %s", 3),
	VOID_ESSENCE("Can be found in End City chests", 0),
	TREASURE_FRAGMENT("Can be found in Mineshaft chests", 0),
	DEATH_ESSENCE("%s chance to drop when mobs with at least %s health is killed by wither effect", 2),
	PURE_NETHER_STAR("Dropped from %s when killed by player with more than %s beneficial effects", 2),
	SHULKER_SCRAP("%s chance to drop when %s is killed by explosion", 2),
	SOARING_WINGS("Dropped when %s is killed above y=%s", 2),
	HEART_FRAGMENT("Dropped when %s is killed by charged Creeper", 1),
	SAKURA_FRAGMENT("%s chance to drop from breaking cherry blossom leaves", 1),
	VIRTUAL_GOLD_NUGGET("%s chance to drop from ignited mobs with enchanted gold armors", 1),
	OCEAN_TOOL("Cancel underwater dig speed penalty", 0),
	FULL_SET("Full set effect: %s", 1),
	SAKURA_SERIES("Restore 1 durability every %s seconds", 1),
	VIRTUAL_GOLD_TOOL("Increases attack damage and dig speed by %s for every enchantment it has", 1),
	VIRTUAL_GOLD_GENERAL("High enchantment affinity", 0);

	final String id;
	final String def;
	final int count;

	CCLangData(String def, int count) {
		this.id = CelestialCore.MODID + ".tooltip." + name().toLowerCase(Locale.ROOT);
		this.def = def;
		this.count = count;
	}

	public MutableComponent get(Object... objs) {
		if (objs.length != this.count) {
			throw new IllegalArgumentException("for " + name() + ": expect " + count + " parameters, got " + objs.length);
		} else {
			return Component.translatable(id, objs);
		}
	}

	public static MutableComponent chance(double v) {
		return Component.literal(((int) Math.round(v * 100)) + "%").withStyle(ChatFormatting.AQUA);
	}

	public static MutableComponent num(int v) {
		return Component.literal(v + "").withStyle(ChatFormatting.AQUA);
	}

	public static MutableComponent entity(EntityType<?> type) {
		return type.getDescription().copy().withStyle(ChatFormatting.AQUA);
	}

	public static MutableComponent eff(MobEffect eff) {
		return eff.getDisplayName().copy().withStyle(eff.getCategory().getTooltipFormatting());
	}

	public static MutableComponent simpleDrop(EntityType<?> type, double v) {
		return SIMPLE_DROP.get(chance(v), type.getDescription().copy().withStyle(ChatFormatting.AQUA));
	}

	public static MutableComponent witherDrop(EntityType<?> type, double v) {
		Player player = Proxy.getPlayer();
		boolean cleared = player != null && PlayerFlagData.HOLDER.get(player).hasFlag(CCGeneralEventHandler.NETHER_STAGE);
		return AFTER_WITHER_DROP.get(
				EntityType.WITHER.getDescription().copy().withStyle(cleared ? ChatFormatting.AQUA : ChatFormatting.RED),
				chance(v), type.getDescription().copy().withStyle(ChatFormatting.AQUA)
		);
	}

	public static MutableComponent deathEssence(double v, int i) {
		return DEATH_ESSENCE.get(chance(v), num(i));
	}

	public static void addLang(RegistrateLangProvider pvd) {
		for (var id : values()) {
			pvd.add(id.id, id.def);
		}

		List<Item> list = List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
		for (RegistryEntry<? extends Potion> ent : CCEffects.POTION_LIST) {
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
